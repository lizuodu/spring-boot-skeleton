package com.example.demo.exception;

/**
 * 业务异常，Spring事务回滚需要RuntimeException类型的异常
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BizException(String message) {
		super(message);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

}
