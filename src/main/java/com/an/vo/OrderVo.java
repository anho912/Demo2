package com.an.vo;

import java.util.Date;

/**
 * 订单扩展类
 * 
 * @author 疯狂的蜗牛君_
 *
 */
public class OrderVo {
	
	private Integer orderId;

	private String userName;

	private Date createDate;

	private Integer orderType;
	
	private Integer isAccept;

	
	public Integer getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(Integer isAccept) {
		this.isAccept = isAccept;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	
}
