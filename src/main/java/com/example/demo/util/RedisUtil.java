package com.example.demo.util;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * 基于Redis的Cache简单实现
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public class RedisUtil {
	
	private static RedisTemplate<String, String> redisTemplate;
	
	static {
		redisTemplate = SpringContextUtil.getBean("redisTemplate");
	}
	
	public static void put(String key, String value, long expire, TimeUnit... timeUnits) {
		ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
		if (timeUnits.length > 0) {
			valueOps.set(key, value, expire, timeUnits[0]);
		} else {
			valueOps.set(key, value, expire, TimeUnit.SECONDS);
		}
	}

	public static String get(String key) {
		ValueOperations<String, String> valueops = redisTemplate.opsForValue();
		String value = valueops.get(key);
		return value;
	}

	public static void delete(String key) {
		redisTemplate.delete(key);
	}
	

}
