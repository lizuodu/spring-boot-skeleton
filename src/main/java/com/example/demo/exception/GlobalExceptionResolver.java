package com.example.demo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.example.demo.dto.Result;
import com.example.demo.util.ServletUtil;

/**
 * 全局异常捕获
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		LOGGER.error(request.getRequestURI() + " 错误信息:" + ex.getMessage());
		try {
			ServletUtil.response(JSON.toJSONString(Result.fail(ex.getMessage())), MediaType.APPLICATION_JSON_UTF8);
		} catch (Exception e) {
			LOGGER.error("Exception:", e);
		}
		return new ModelAndView();
	}

}
