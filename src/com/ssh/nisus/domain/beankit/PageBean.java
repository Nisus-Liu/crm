package com.ssh.nisus.domain.beankit;

import java.util.List;

/**
 * 分页查询用的bean
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-12-03-19:47
 */
public class PageBean {
	// 前台输入:
	// 当前页
	private Integer currentPage;
	// 页大小
	private Integer pageSize;
	// 后台处理:
	// 总记录数
	private Integer rows;
	// 总页数
	private Integer totalPage;
	// 显示数据
	private List data;
	// 页大小下拉框选择器(页面通过下拉选择数字指定每页显示的页大小) | pageSize的整数倍
	private Integer[] pageSizeSelector = {3,6,9,12};
	
	// 不给空参构造, 必须要参数
	// 页面必须元素: 当期那页号, 总页数(构造内部计算), 进而需要总记录数和页大小
	// 所以以上参数必须传进来
	public PageBean(Integer currentPage, Integer pageSize, Integer rows) {
		this.currentPage = currentPage;         // 有可能为空或者不合规的数值
		this.pageSize = pageSize;               // 有可能为空或者不合规的数值
		this.rows = rows;
		
		if (currentPage == null || currentPage <= 0) {
			this.currentPage = 1;
		}
		if (pageSize == null || pageSize <= 0) {
			this.pageSize = 3;
		}
		
		// 计算出总页数
		this.totalPage = (this.rows+this.pageSize-1) / this.pageSize;
		
		if (this.currentPage > this.totalPage) {
			this.currentPage = this.totalPage;
		}
		
		// 生成页大小选择器   --bug: 页大小一旦动态指定的, 那么这个数组最小值始终是动态指定的那个页大小
//		for (int i = 0; i < this.pageSizeSelector.length; i++) {
//			this.pageSizeSelector[i] = this.pageSize * (i+1);
//		}
		
	}
	
	/**
	 * 计算出当前页起始索引
	 *
	 * @return
	 */
	public Integer getStart() {
		return (this.currentPage - 1) * this.pageSize;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getRows() {
		return rows;
	}
	
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public Integer getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	public List getData() {
		return data;
	}
	
	public void setData(List data) {
		this.data = data;
	}
	
	public Integer[] getPageSizeSelector() {
		return pageSizeSelector;
	}
	
	public void setPageSizeSelector(Integer[] pageSizeSelector) {
		this.pageSizeSelector = pageSizeSelector;
	}
	
	@Override
	public String toString() {
		return "PageBean{" +
				"currentPage=" + currentPage +
				", pageSize=" + pageSize +
				", rows=" + rows +
				", totalPage=" + totalPage +
				", data=" + data +
				'}';
	}
}
