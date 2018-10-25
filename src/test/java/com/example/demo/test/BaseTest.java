package com.example.demo.test;

import java.net.URL;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * 测试基础类
 * @author lizuodu
 * @date   2018年10月27日
 */
@Component
public class BaseTest {
	
	/**
	 * 配置文件
	 */
	@Resource
	public Config config;
	
	/**
	 * 打印请求URI和结果
	 * @param url
	 * @param result
	 */
	public void dumpResult(URL url, Object result) {
		System.out.println("=======================[TEST]==========================");
        System.out.println("请求：" + url.toString());
        System.out.println("结果：" + result);
        System.out.println("=======================================================");
	}

}