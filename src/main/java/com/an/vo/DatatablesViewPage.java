package com.an.vo;

import java.util.List;

/**
 * 分页类
 * @author 疯狂的蜗牛君_
 *
 */
public class DatatablesViewPage {
	/**
	 * 当前页的数据
	 */
	private List data;
	/**
	 * 共多少页
	 */
	private long totalPage;
	/**
	 * 共多少条记录
	 */
	private long totalSize;
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public DatatablesViewPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DatatablesViewPage(List data, long totalPage, long totalSize) {
		super();
		this.data = data;
		this.totalPage = totalPage;
		this.totalSize = totalSize;
	}
	
}
