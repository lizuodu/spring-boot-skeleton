package com.example.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 供所有model继承
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public class BaseModel {

	/**
	 * 1:删除
	 */
	protected Integer flag;
	
	/**
	 * 创建者id
	 */
	protected Integer createBy;
	
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date createDate;
	
	/**
	 * 修改者
	 */
	protected Integer updateBy;
	
	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date updateDate;
	
	/**
	 * 表单id
	 */
	private String formId;

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createAt) {
		this.createBy = createAt;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateAt) {
		this.updateBy = updateAt;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

}
