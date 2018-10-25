package com.example.demo.model;

/**
 * User class
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public class User extends BaseModel {

	private Long id;
	
	private String name;
	
	private Integer age;

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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", del=" + flag + ", createAt=" + createBy
				+ ", createDate=" + createDate + ", updateAt=" + updateBy + ", updateDate=" + updateDate + "]";
	}


}