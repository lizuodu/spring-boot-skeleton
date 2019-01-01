package com.example.demo.aspect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.dto.Result;
import com.example.demo.util.ServletUtil;

/**
 * com.example.demo.api包aop
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@Component
@Aspect
@Order(2)
public class InParameterValidateAspect extends BaseAspect {

	/**
	 * 切点
	 */
	@Pointcut("execution(* com.example.demo.web.api..*(..))")
	public void aopMethod() {
	}

	/**
	 * 前置通知
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("aopMethod()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
//		HttpServletRequest request = ServletUtil.getRequest();
//		System.out.println("IP : " + request.getRemoteAddr());
	}

	/**
	 * 环绕通知
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("aopMethod()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable { /* 连接点 */
		BindingResult bindingResult = null;
		for (Object arg : joinPoint.getArgs()) {
			if (arg instanceof BindingResult) {
				bindingResult = (BindingResult) arg;
			}
		}
		if (bindingResult != null) {
			if (bindingResult.hasErrors()) {
				valid.set(false);
				List<FieldError> errors = bindingResult.getFieldErrors();
				Map<String, String> mapErrors = new HashMap<>(errors.size());
				for (FieldError error : errors) {
					mapErrors.put(error.getField(), error.getDefaultMessage());
				}
				return Result.fail("valid failure", mapErrors);
			} else {
				valid.set(true);
			}
		} else {
			valid.set(true);
		}
		return joinPoint.proceed();
	}

	/**
	 * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
	 * 
	 * @param joinPoint
	 */
	@After("aopMethod()")
	public void finalAfter(JoinPoint joinPoint) {

	}

}
