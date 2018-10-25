package com.example.demo.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件映射类
 * @author lizuodu
 * @date   2018年10月27日
 */
@Component
public class Config {
	
	@Value("${server.port}")
	private int port; 
	
	@Value("${server.servlet.context-path}")
	private String contextPath;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

}
