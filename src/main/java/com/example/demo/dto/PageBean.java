package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * 分页PageBean，简化分页控件自带{@link com.github.pagehelper.PageInfo }
 * @param <T>
 * @author lizuodu
 * @date 2018/09/27
 */
public class PageBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 总记录数
	 */
	private long total;

	/**
	 * 结果集
	 */
	private List<T> list;

	/**
	 * 第几页
	 */
	private int pageNum;

	/**
	 * 每页记录数
	 */
	private int pageSize;

	/**
	 * 总页数
	 */
	private int pages;

	/**
	 * 当前页的数量 <= pageSize
	 */
	private int size;

	public PageBean() {
	}

	public PageBean(List<T> list) {
		if (list instanceof Page) {
			Page<T> page = (Page<T>) list;
			this.pageNum = page.getPageNum();
			this.pageSize = page.getPageSize();
			this.total = page.getTotal();
			this.pages = page.getPages();
			this.list = page;
			this.size = page.size();
		}
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "PageBean [total=" + total + ", list=" + list + ", pageNum=" + pageNum + ", pageSize=" + pageSize
				+ ", pages=" + pages + ", size=" + size + "]";
	}

}
