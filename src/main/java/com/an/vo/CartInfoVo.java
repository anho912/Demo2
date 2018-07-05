package com.an.vo;

/**
 * 购物车扩展类
 * @author 疯狂的蜗牛君_
 *
 */
public class CartInfoVo {
	//商品id
	private Integer proId;
	//商品图片
	private String proImg;
	//商品名称
	private String proName;
	//商品价格
	private double proPrice;
	//商品数量
	private Integer proNum;
	//商品总价
	private double proMoney;
	
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public String getProImg() {
		return proImg;
	}
	public void setProImg(String proImg) {
		this.proImg = proImg;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
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
	
}
