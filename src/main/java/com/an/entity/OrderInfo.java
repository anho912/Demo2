package com.an.entity;

public class OrderInfo {
    private Integer oInfoId;

    private Integer orderId;

    private Integer proId;

    private Integer proNum;

    private String receiveraddress;

    private String receiverphone;

    private String receivername;

    private Double oInfoMoney;

    private Integer paystate;

    public Integer getoInfoId() {
        return oInfoId;
    }

    public void setoInfoId(Integer oInfoId) {
        this.oInfoId = oInfoId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public String getReceiveraddress() {
        return receiveraddress;
    }

    public void setReceiveraddress(String receiveraddress) {
        this.receiveraddress = receiveraddress == null ? null : receiveraddress.trim();
    }

    public String getReceiverphone() {
        return receiverphone;
    }

    public void setReceiverphone(String receiverphone) {
        this.receiverphone = receiverphone == null ? null : receiverphone.trim();
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername == null ? null : receivername.trim();
    }

    public Double getoInfoMoney() {
        return oInfoMoney;
    }

    public void setoInfoMoney(Double oInfoMoney) {
        this.oInfoMoney = oInfoMoney;
    }

    public Integer getPaystate() {
        return paystate;
    }

    public void setPaystate(Integer paystate) {
        this.paystate = paystate;
    }
}