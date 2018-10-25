package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.example.demo.cache.Cache;
import com.example.demo.exception.BizException;
import com.example.demo.mapper.BaseMapper;
import com.example.demo.model.BaseModel;
import com.example.demo.service.DataBaseService;
import com.example.demo.util.ServletUtil;

/**
 * 数据库CRUD服务实现类
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public abstract class AbstractDataBaseServiceImpl<T> implements DataBaseService<T> {
	
	/**
	 * 得到子类具体的mapper实例
	 * @return
	 */
	public abstract BaseMapper<T> mapperInstance();
	private final static int CACHE_EXPIRE = 5;
	@Resource private Cache redisCache;
	
	protected void fillCreate(BaseModel baseModel) {
		baseModel.setFlag(0);
		baseModel.setCreateBy(100);
		baseModel.setCreateDate(new Date());
	}

	protected void fillUpdate(T model) {
		BaseModel baseModel = ((BaseModel) model);
		baseModel.setUpdateDate(new Date());
		baseModel.setUpdateBy(100);
	}
	
	/**
	 * 增加
	 */
	@Override
	public void insert(T model) throws Exception {
		BaseModel baseModel = ((BaseModel) model);
		String sessionId = ServletUtil.getSession().getId();
		String formId = baseModel.getFormId();
		String key = String.format("form:%s-%s", formId, sessionId);
		if (StringUtils.isNotBlank(formId)) {
			String value = this.redisCache.get(key);
			if (value != null) {
				throw new BizException("不能重复提交表单");
			}
		}
		this.fillCreate(baseModel);
		this.mapperInstance().insert(model);
		this.redisCache.put(key, "1", CACHE_EXPIRE);
	}

	/**
	 * 批量增加
	 */
	@Override
	public int insertBatch(List<T> modelList) throws Exception {
		return this.mapperInstance().insertBatch(modelList);
	}

	/**
	 * 更新
	 */
	@Override
	public void update(T model) throws Exception {
		this.fillUpdate(model);
		this.mapperInstance().update(model);
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(T model) throws Exception {
		this.mapperInstance().delete(model);
	}

	/**
	 * 查找一条记录
	 */
	@Override
	public T findByPrimaryKey(Long id) {
		return this.mapperInstance().findByPrimaryKey(id);
	}

	/**
	 * 查找记录
	 */
	@Override
	public List<T> findByModel(T model) {
		return this.mapperInstance().findByModel(model);
	}

	/**
	 * 所有记录
	 */
	@Override
	public List<T> findAll() {
		return this.mapperInstance().findAll();
	}
	
	/**
	 * 查询记录数
	 * @param model
	 * @return
	 */
	@Override
	public int count(T model) {
		return this.mapperInstance().count(model);
	}

}
