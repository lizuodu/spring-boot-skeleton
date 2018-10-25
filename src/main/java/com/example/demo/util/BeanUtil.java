package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.example.demo.dto.PageBean;
import com.example.demo.model.BaseModel;

/**
 * 对Bean进行操作的一些方法封装工具类
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public final class BeanUtil {

	/**
	 * Model
	 * @param t model实例
	 * @param clazz dto类型
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T, D> D map(T t, Class<D> clazz) 
			throws InstantiationException, IllegalAccessException {
		D dto = clazz.newInstance();
		BeanUtils.copyProperties(t, dto);
		return dto;
	}
	
	/**
	 * List<Model>
	 * @param tList 
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T, D> List<D> mapList(List<T> tList, Class<D> clazz) 
			throws InstantiationException, IllegalAccessException {
		List<D> dList = new ArrayList<>();
		for (T t: tList) {
			D dto = map(t, clazz);
			dList.add(dto);
		}
		return dList;
	}
	
	/**
	 * PageBean<Model>
	 * @param tPageBean
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T, D> PageBean<D> mapPage(PageBean<T> tPageBean, Class<D> clazz) 
			throws InstantiationException, IllegalAccessException {
		List<T> tList = tPageBean.getList();
		List<D> dList = new ArrayList<>();
		for (T t: tList) {
			D dto = map(t, clazz);
			dList.add(dto);
		}
		@SuppressWarnings("unchecked")
		PageBean<D> dPageBean = map(tPageBean, new PageBean<>().getClass());
		dPageBean.setList(dList);
		return dPageBean;
	}
	
	// Map<String, List<Model>>
	
	// List<Map<String, Model>>
	
	// ...
	
	@SuppressWarnings({ "unchecked" })
	public static Object trans(Object dataObj, Class<?> dtoType) 
			throws InstantiationException, IllegalAccessException {
		Object dtoObj = null;
		if (dataObj instanceof BaseModel) {
			dtoObj = BeanUtil.map(dataObj, dtoType);
		} else if (dataObj instanceof List) {
			dtoObj = BeanUtil.mapList((List) dataObj, dtoType);
		} else if (dataObj instanceof PageBean) {
			dtoObj = BeanUtil.mapPage((PageBean) dataObj, dtoType);
		}
		return dtoObj;
	}

}
