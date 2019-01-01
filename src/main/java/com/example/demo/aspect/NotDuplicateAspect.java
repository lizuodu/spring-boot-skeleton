package com.example.demo.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.exception.BizException;
import com.example.demo.model.BaseModel;
import com.example.demo.util.RedisUtil;
import com.example.demo.util.ServletUtil;

/**
 * 保证方法不被重复调用
 * 
 * @author lizuodu
 * @date 2018年12月1日 上午11:31:09
 */
@Component
@Aspect
@Order(1)
public class NotDuplicateAspect {

	private final static int CACHE_EXPIRE = 5;

	/**
	 * 切点
	 */
	@Pointcut("@annotation(com.example.demo.annotation.NotDuplicate)")
	public void aopMethod() {
	}

	/**
	 * 前置通知
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before(value = "aopMethod()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		String sid = ServletUtil.getSession().getId();
		Object[] args = joinPoint.getArgs();
		for (Object o : args) {
			if (o instanceof BaseModel) {
				BaseModel model = (BaseModel) o;
				String formId = model.getFormId();
				String key = String.format("form:%s-%s", formId, sid);
				if (StringUtils.isNotBlank(formId)) {
					String value = RedisUtil.get(key);
					if (value != null) {
						throw new BizException("不能重复提交表单");
					}
					RedisUtil.put(key, "1", CACHE_EXPIRE);
				}
				break;
			}
		}
	}

}
