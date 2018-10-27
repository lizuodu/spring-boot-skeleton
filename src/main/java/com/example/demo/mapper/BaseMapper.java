package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 抽象出来的数据库CRUD
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public interface BaseMapper<T> {

	/**
	 * 单条新增插入数据
	 * 
	 * @param model
	 * @throws Exception
	 */
	void insert(T model) throws Exception;

	/**
	 * 批量新增据插入数据
	 * 
	 * @param modelList
	 * @return
	 * @throws Exception
	 */
	int insertBatch(List<T> modelList) throws Exception;

	/**
	 * 更新数据
	 * 
	 * @param model
	 * @throws Exception
	 */
	int update(T model) throws Exception;

	/**
	 * 删除数据
	 * 
	 * @param model
	 * @throws Exception
	 */
	int delete(T model) throws Exception;

	/**
	 * 根据id查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	T findByPrimaryKey(@Param("id") long id);

	/**
	 * 根据对象查询多条记录
	 * 
	 * @param model
	 * @return
	 */
	List<T> findByModel(T model);

	/**
	 * 查询所有记录
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 查询记录数
	 * 
	 * @param model
	 * @return
	 */
	int count(T model);

}
