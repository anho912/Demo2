package com.an.entity;

import java.util.Date;

public class CartInfo {
    private Integer cInfoId;

    private Integer cartId;

    private Integer proId;

    private Integer proNum;

    private Date createDate;

    public Integer getcInfoId() {
        return cInfoId;
    }

    public void setcInfoId(Integer cInfoId) {
        this.cInfoId = cInfoId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getProNum() {
        return proNum;
    }

    public void setProNum(Integer proNum) {
        this.proNum = proNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}