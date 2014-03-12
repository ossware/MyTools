/**  
 * @Title: Paging.java
 * @Package com.ehome.webapp.bean
 * @ClassName: Paging
 * @Description: 分页组件
 * @author xiaolei-0228@163.com
 * @date 2014年3月8日 下午3:30:41
 * @version V1.0  
 */ 
package com.xiaolei.webapp.bean;

import java.io.Serializable;

public class Paging implements Serializable {
	private static final long serialVersionUID = -1549675405703658625L;
	
	private int currentRecord;		// 当前记录
	private int currentPage;		// 当前页
	private int pageSize;			// 页长（一页显示多少条数据）
	private long totalRecords;		// 总记录数
	private long totalPages;		// 总页数
	
	/**********************************************************************************************/
	public int getCurrentRecord() {
		return currentRecord;
	}
	public void setCurrentRecord(int currentRecord) {
		this.currentRecord = currentRecord;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
	
	
	
	
}
