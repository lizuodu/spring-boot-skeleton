package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.constants.ConstantsResult;

//@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * 统一返回结果包装类
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public class Result<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean isSuccess = false;
	private int status;
	private String message;
	private T data;

	private Result() {
	}

	public static <T> Result<T> ok() {
		Result<T> rs = new Result<>();
		rs.setMessage("");
		rs.setStatus(ConstantsResult.SUCCESS.getValue());
		rs.setIsSuccess(true);
		return rs;
	}

	public static <T> Result<T> ok(String msg) {
		Result<T> rs = new Result<>();
		rs.setMessage(msg);
		rs.setStatus(ConstantsResult.SUCCESS.getValue());
		rs.setIsSuccess(true);
		return rs;
	}

	public static <T> Result<T> ok(T data) {
		Result<T> rs = new Result<>();
		if (data instanceof String) {
			rs.setMessage((String) data);
		}
		rs.setStatus(ConstantsResult.SUCCESS.getValue());
		rs.setIsSuccess(true);
		rs.setData(data);
		return rs;
	}

	public static <T> Result<T> ok(String msg, T data) {
		Result<T> rs = new Result<>();
		rs.setMessage(msg);
		rs.setStatus(ConstantsResult.SUCCESS.getValue());
		rs.setIsSuccess(true);
		rs.setData(data);
		return rs;
	}

	public static <T> Result<T> fail() {
		Result<T> rs = new Result<>();
		rs.setMessage("");
		rs.setStatus(ConstantsResult.FAILURE.getValue());
		rs.setIsSuccess(false);
		return rs;
	}

	public static <T> Result<T> fail(String msg) {
		Result<T> rs = new Result<>();
		rs.setMessage(msg);
		rs.setStatus(ConstantsResult.FAILURE.getValue());
		rs.setIsSuccess(false);
		return rs;
	}

	public static <T> Result<T> fail(Exception ex) {
		Result<T> rs = new Result<>();
		rs.setMessage(ex.getMessage());
		rs.setStatus(ConstantsResult.FAILURE.getValue());
		rs.setIsSuccess(false);
		return rs;
	}

	public static <T> Result<T> fail(Exception ex, T data) {
		Result<T> rs = new Result<>();
		rs.setMessage(ex.getMessage());
		rs.setStatus(ConstantsResult.FAILURE.getValue());
		rs.setIsSuccess(false);
		rs.setData(data);
		return rs;
	}

	public static <T> Result<T> fail(String exceptionMsg, T data) {
		Result<T> rs = new Result<>();
		rs.setMessage(exceptionMsg);
		rs.setStatus(ConstantsResult.FAILURE.getValue());
		rs.setIsSuccess(false);
		rs.setData(data);
		return rs;
	}

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
