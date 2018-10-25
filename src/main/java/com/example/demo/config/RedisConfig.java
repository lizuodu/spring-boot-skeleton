package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

/**
 * Redis相关配置
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@Configuration
@EnableRedisHttpSession
public class RedisConfig {

	@Bean(value = "redisTemplate")
	public RedisTemplate<Object, Object> template(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);

		redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
		redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());

		redisTemplate.afterPropertiesSet();
				
		return redisTemplate;
	}

}
