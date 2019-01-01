package com.example.demo.aspect;

/**
 * 
 * @author lizuodu
 * @date 2018年12月4日 下午9:23:01
 */
public class BaseAspect {
	
	/**
	 * 验证状态(表单)
	 */
	public static ThreadLocal<Boolean> valid = new ThreadLocal<>();
	
	static {
		valid.set(true);
	}

}
