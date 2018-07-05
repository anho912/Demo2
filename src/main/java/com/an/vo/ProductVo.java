package com.an.vo;

import java.util.Date;

/**
 * 商品扩展类
 * 
 * @author 商品
 *
 */
public class ProductVo {

	// 订单详情id
	private Integer orderinfoId;
	// 订单id
	private Integer orderId;
	// 商品id
	private Integer proId;
	//商品名称
	private String proName;
	// 商品图片
	private String proImg;
	// 商品价格
	private double proPrice;
	// 商品数量
	private Integer proNum;
	// 商品总价
	private double proMoney;
	//商品状态  0.未受理 1.已受理'
	private Integer isAccept;
	//创建日期
	private Date createDate;
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public Integer getOrderinfoId() {
		return orderinfoId;
	}
	public void setOrderinfoId(Integer orderinfoId) {
		this.orderinfoId = orderinfoId;
	}
	public String getProImg() {
		return proImg;
	}
	public void setProImg(String proImg) {
		this.proImg = proImg;
	}
	public double getProPrice() {
		return proPrice;
	}
	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}
	public Integer getProNum() {
		return proNum;
	}
	public void setProNum(Integer proNum) {
		this.proNum = proNum;
	}
	public double getProMoney() {
		return proMoney;
	}
	public void setProMoney(double proMoney) {
		this.proMoney = proMoney;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(Integer isAccept) {
		this.isAccept = isAccept;
	}
	
	
	
}
