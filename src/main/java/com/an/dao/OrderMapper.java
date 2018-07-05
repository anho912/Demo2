package com.an.dao;

import java.util.List;

import com.an.entity.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * 查询总记录数
     * @param searchStr
     * @return
     */
	long selectCount(String searchStr);

	/**
	 * 分页查询
	 * @param i
	 * @param page
	 * @param searchStr
	 * @return
	 */
	List<Order> findOrderDataByPageAndCount(int i, int page, String searchStr);

	/**
	 * 根据id去查询一个订单
	 * @param valueOf
	 * @return
	 */
	Order findById(Integer valueOf);

	/**
	 * 更新订单状态
	 * @param order
	 * @return
	 */
	int updateOrder(Order order);

	/**
	 * 查询用户的所有订单
	 * @param valueOf
	 * @return
	 */
	List<Order> findOrderList(Integer valueOf);

	/**
	 * 查看用户所有订单
	 * @param valueOf
	 * @return
	 */
	List<Order> selectOrderByUserId(Integer valueOf);
}