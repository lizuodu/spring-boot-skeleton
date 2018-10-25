package com.example.demo.cache;

import java.util.concurrent.TimeUnit;

/**
 * Cache
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public interface Cache {
	
	/**
	 * 增加
	 * @param key
	 * @param value
	 * @param expire
	 * @param timeUnits
	 */
	void put(String key, String value, long expire, TimeUnit... timeUnits);
	
	/**
	 * 获取
	 * @param key
	 * @return
	 */
	String get(String key);
	
	/**
	 * 删除
	 * @param key
	 */
	void delete(String key);

}
