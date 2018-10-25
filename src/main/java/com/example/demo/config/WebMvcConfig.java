package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.TestInterceptor;

/**
 * MVC 设置
 * 
 * @author lizuodu
 * @date 2018/10/07
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Bean
	public TestInterceptor tokenVerifyInterceptor() {
		return new TestInterceptor();
	}

	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns("/test/*");
	}

}
