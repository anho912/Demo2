package com.an.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.dao.OrderInfoMapper;
import com.an.dao.OrderMapper;
import com.an.dao.ProductMapper;
import com.an.dao.UserMapper;
import com.an.entity.Order;
import com.an.entity.OrderInfo;
import com.an.entity.Product;
import com.an.entity.User;
import com.an.service.OrderService;
import com.an.vo.OrderVo;
import com.an.vo.ProductVo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Override
	public long selectCount(String searchStr) {
		// TODO Auto-generated method stub
		return orderMapper.selectCount(searchStr);
	}

	@Override
	public List<OrderVo> findOrderDataByPageAndCount(int i, int page, String searchStr) {
		// TODO Auto-generated method stub
		List<Order> orders = orderMapper.findOrderDataByPageAndCount(i, page, searchStr);
		List<OrderVo> orderVos = new ArrayList<>();
		for (Order order : orders) {
			User user = userMapper.findById(order.getUserId());
			OrderVo orderVo = new OrderVo();
			orderVo.setUserName(user.getUserName());
			orderVo.setOrderId(order.getOrderId());
			orderVo.setOrderType(order.getOrderType());
			orderVo.setIsAccept(order.getIsAccept());
			orderVo.setCreateDate(order.getCreateDate());
			orderVos.add(orderVo);
		}
		return orderVos;
	}

	@Override
	public int updateOrder(Integer valueOf) {
		// TODO Auto-generated method stub
		Order order = orderMapper.findById(valueOf);
		order.setIsAccept(1);
		order.setOrderType(1);
		return orderMapper.updateOrder(order);
	}

	@Override
	public List<OrderInfo> findSixProduct() {
		// TODO Auto-generated method stub
		List<OrderInfo> orderInfos = orderInfoMapper.findAll();
		return orderInfos;
	}

	@Override
	public int saveOrder(Order order) {
		// TODO Auto-generated method stub
		return orderMapper.insert(order);
	}

	@Override
	public Order selectAllByUserId(Long userId) {
		// TODO Auto-generated method stub
		List<Order> orders = orderMapper.findOrderList(Integer.valueOf(userId + ""));
		return orders.get(orders.size() - 1);
	}

	@Override
	public void saveOrderInfo(OrderInfo orderInfo) {
		// TODO Auto-generated method stub
		orderInfoMapper.insert(orderInfo);
	}

	@Override
	public List<OrderInfo> selectAllByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return orderInfoMapper.selectAllByOrderId(orderId);
	}

	@Override
	public void updateOrderInfo(OrderInfo orderInfo2) {
		// TODO Auto-generated method stub
		orderInfoMapper.updateByPrimaryKey(orderInfo2);
	}

	@Override
	public List<OrderInfo> findByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return orderInfoMapper.selectAllByOrderId(orderId);
	}

	@Override
	public void deleteInfoById(Integer getoInfoId) {
		// TODO Auto-generated method stub
		orderInfoMapper.deleteByPrimaryKey(getoInfoId);
	}

	@Override
	public void deleteOrderById(Integer orderId) {
		// TODO Auto-generated method stub
		orderMapper.deleteByPrimaryKey(orderId);
	}

	@Override
	public List<Order> selectOrderByUserId(String userId) {
		// TODO Auto-generated method stub
		return orderMapper.findOrderList(Integer.valueOf(userId));
	}

	@Override
	public List<ProductVo> findOrderByPageAndCount(int i, int page, String userId) {
		// TODO Auto-generated method stub
		// 查询该用户的所有订单
		List<Order> orders = orderMapper.findOrderList(Integer.valueOf(userId));
		// 所有订单详情
		List<OrderInfo> orderInfos = new ArrayList<>();
		for (Order order : orders) {
			List<OrderInfo> orderInfos2 = orderInfoMapper.selectAllByOrderId(order.getOrderId());
			for (OrderInfo orderInfo : orderInfos2) {
				orderInfos.add(orderInfo);
			}
		}
		List<OrderInfo> orderInfos2=new ArrayList<>();
		if(page<1) {
			page=1;
		}
		int num=(page-1)*i;
		for(int m=num;m<orderInfos.size();m++) {
			if(m<num+i) {
				orderInfos2.add(orderInfos.get(m));
			}
		}
		List<ProductVo> productVos=new ArrayList<>();
		for(OrderInfo orderInfo:orderInfos2) {
			Order order=orderMapper.findById(orderInfo.getOrderId());
			ProductVo productVo=new ProductVo();
			Product product=productMapper.findById(orderInfo.getProId());
			productVo.setProId(product.getProId());
			productVo.setOrderinfoId(orderInfo.getoInfoId());
			productVo.setOrderId(orderInfo.getOrderId());
			productVo.setProNum(orderInfo.getProNum());
			productVo.setProName(product.getProName());;
			productVo.setProPrice(product.getProPrice());
			productVo.setProMoney(orderInfo.getProNum()*product.getProPrice());
			productVo.setProImg(product.getProImg());
			productVo.setIsAccept(order.getIsAccept());
			productVo.setCreateDate(order.getCreateDate());
			productVos.add(productVo);
		}
		return productVos;
	}

	@Override
	public OrderInfo findByOrderInfoId(Integer orderinfo) {
		// TODO Auto-generated method stub
		return orderInfoMapper.selectByPrimaryKey(orderinfo);
	}

	@Override
	public Order findByOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return orderMapper.findById(orderId);
	}

}
