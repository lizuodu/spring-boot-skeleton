package com.example.demo.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 保证方法不被重复调用注解
 *  
 * @author lizuodu
 * @date 2018年12月31日
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface NotDuplicate {

}
