package com.an.dao;

import java.util.List;

import com.an.entity.OrderInfo;;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer oInfoId);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer oInfoId);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    /**
     * 查询所有订单详情表
     * @return
     */
	List<OrderInfo> findAll();

	/**
	 * 查询订单详情
	 * @param orderId
	 * @return
	 */
	List<OrderInfo> selectAllByOrderId(Integer orderId);
}