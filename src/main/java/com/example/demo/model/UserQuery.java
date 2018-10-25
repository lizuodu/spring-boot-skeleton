package com.example.demo.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 包装查询User所需属性的类
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public class UserQuery {

	private Long id;

	@NotNull(message = "姓名不能为空白")
	@Size(min = 1, max = 10, message = "姓名长度{min}~{max}")
	private String name;

	@NotNull(message = "年龄不能为空白")
	@DecimalMin(value = "0", message = "年龄不能小于{value}")
	@DecimalMax(value = "200", message = "年龄不能大于{value}")
	private Integer age;

	private String formId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

}
