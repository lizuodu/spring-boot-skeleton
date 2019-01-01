package com.example.demo.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.annotation.DTO;
import com.example.demo.dto.Result;
import com.example.demo.util.BeanUtil;

/**
 * 将结果封装成DTO对象
 * 
 * @author lizuodu
 * @date 2018年12月4日 下午9:16:45
 */
@Component
@Aspect
@Order(10000)
public class DTOAspect extends BaseAspect {
	
	/**
	 * 切点
	 */
	@Pointcut("execution(* com.example.demo.web.api.*.*(..))")
	public void aopMethod() {
	}
	
	/**
	 * 后置返回通知
	 * 
	 * @param joinPoint
	 * @param retObj
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@AfterReturning(pointcut = "aopMethod() && @annotation(com.example.demo.annotation.DTO)", returning = "retObj")
	public Object afterReturning(JoinPoint joinPoint, Object retObj) {
		try {
			if (valid.get() != null && valid.get()) {
				// 获取参数的类型
				Method method = null;
				Signature signature = joinPoint.getSignature();
				MethodSignature methodSignature = null;
				if (signature instanceof MethodSignature) {
					methodSignature = (MethodSignature) signature;
					method = methodSignature.getMethod();
					DTO dto = method.getAnnotation(DTO.class);
					Result result = (Result) retObj;
					Object dataObj = result.getData();
					Object dtoObj = BeanUtil.trans(dataObj, dto.toType());
					result.setData(dtoObj);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			valid.set(true);
		}
		return retObj;
	}

}
