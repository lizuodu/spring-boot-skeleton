package com.example.demo.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 对Servlet对象操作封装工具类
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public final class ServletUtil {
	
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
        HttpServletRequest request = attributes.getRequest();  
        return request;
	}
	
	public static HttpServletResponse getResponse() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();
		return response;
	}
	
	public static HttpSession getSession() {
		return getRequest().getSession();
	}
	
	public static void response(String msg, MediaType mediaType) {
		HttpServletResponse response = getResponse();
		PrintWriter writer;
		try {
			writer = response.getWriter();
			response.setContentType(mediaType.toString());
			writer.write(msg);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
