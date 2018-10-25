package com.example.demo.constants;

/**
 * 返回结果常量
 * 
 * @author lizuodu
 * @date 2018/09/29
 */
public enum ConstantsResult {
	
	FAILURE(0),
	SUCCESS(1);
	
	private int value;
		
	ConstantsResult(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

}
