package com.example.demo.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 返回Dto注解
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Dto {
	
	Class<?> toType();

}
