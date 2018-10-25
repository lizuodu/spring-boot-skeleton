package com.example.demo.cache.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.example.demo.cache.Cache;

/**
 * 基于Redis的Cache简单实现
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@Component("redisCache")
public class RedisCache implements Cache {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public void put(String key, String value, long expire, TimeUnit... timeUnits) {
		ValueOperations<String, String> valueOps = this.redisTemplate.opsForValue();
		if (timeUnits.length > 0) {
			valueOps.set(key, value, expire, timeUnits[0]);
		} else {
			valueOps.set(key, value, expire, TimeUnit.SECONDS);
		}
	}

	@Override
	public String get(String key) {
		ValueOperations<String, String> valueops = this.redisTemplate.opsForValue();
		String value = valueops.get(key);
		return value;
	}

	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
	}
	

}
