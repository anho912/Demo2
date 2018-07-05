package com.an.service;

import java.util.List;

import com.an.entity.Order;
import com.an.entity.OrderInfo;
import com.an.vo.OrderVo;
import com.an.vo.ProductVo;

/**
 * 订单服务端接口
 * @author 疯狂的蜗牛君_
 *
 */
public interface OrderService {

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
	List<OrderVo> findOrderDataByPageAndCount(int i, int page, String searchStr);

	/**
	 * 受理一个订单
	 * @param valueOf
	 * @return
	 */
	int updateOrder(Integer valueOf);

	/**
	 * 查询热销前六的商品订单
	 * @return
	 */
	List<OrderInfo> findSixProduct();

	/**
	 * 创建一个订单
	 * @param order
	 * @return
	 */
	int saveOrder(Order order);

	/**
	 * 查询用户最近一笔订单
	 * @param userId
	 * @return
	 */
	Order selectAllByUserId(Long userId);

	/**
	 * 生成订单详情
	 * @param orderInfo
	 */
	void saveOrderInfo(OrderInfo orderInfo);

	/**
	 * 查询订单详情
	 * @param orderId
	 * @return
	 */
	List<OrderInfo> selectAllByOrderId(Integer orderId);

	/**
	 * 支付
	 * @param orderInfo2
	 */
	void updateOrderInfo(OrderInfo orderInfo2);

	/**
	 * 根据订单id查询订单详情集合
	 * @param orderId
	 * @return
	 */
	List<OrderInfo> findByOrderId(Integer orderId);

	/**
	 * 根据id删除订单详情
	 * @param getoInfoId
	 */
	void deleteInfoById(Integer getoInfoId);

	/**
	 * 根据id删除订单
	 * @param orderId
	 */
	void deleteOrderById(Integer orderId);

	/**
	 * 查询订单
	 * @param userId
	 * @return
	 */
	List<Order> selectOrderByUserId(String userId);

	/**
	 * 查看订单详情
	 * @param i
	 * @param page
	 * @param userId
	 * @return
	 */
	List<ProductVo> findOrderByPageAndCount(int i, int page, String userId);

	/**
	 * 查询一个订单详情信息
	 * @param orderinfo
	 * @return
	 */
	OrderInfo findByOrderInfoId(Integer orderinfo);

	/**
	 * 查询一个订单信息
	 * @param orderId
	 * @return
	 */
	Order findByOrder(Integer orderId);

}
