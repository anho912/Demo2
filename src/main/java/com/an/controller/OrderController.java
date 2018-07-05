package com.an.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.an.entity.Cart;
import com.an.entity.CartInfo;
import com.an.entity.Order;
import com.an.entity.OrderInfo;
import com.an.entity.Product;
import com.an.entity.User;
import com.an.service.CartService;
import com.an.service.OrderService;
import com.an.service.ProductService;
import com.an.utils.Results;
import com.an.vo.CartInfoVo;
import com.an.vo.DatatablesViewPage;
import com.an.vo.OrderVo;
import com.an.vo.ProductVo;

/**
 * 订单控制器
 * 
 * @author 疯狂的蜗牛君_
 *
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService ProductService;
	@Autowired
	private CartService cartService;

	/**
	 * 创建订单
	 * 
	 * @param productIds
	 * @return
	 */
	@RequestMapping(value = "/createOrder")
	ModelAndView createOrder(String[] productIds, String username,String phone,String address,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) session.getAttribute("session_user");
		// 获取该用户的购物车
		Cart cart = cartService.findByUserId(Integer.valueOf(user.getUserId() + ""));
		// 获取购物详情列表
		List<CartInfo> cartInfos = cartService.findByCartId(cart.getCartId());
		List<CartInfo> cartInfos2 = new ArrayList<>();
		for (String proid : productIds) {
			for (CartInfo cartInfo : cartInfos) {
				if (cartInfo.getProId() == Integer.valueOf(proid)) {
					cartInfos2.add(cartInfo);
				}
			}
		}
		// 遍历购物车详情   获取要结算的商品集合
		List<CartInfoVo> products = new ArrayList<>();
		for (CartInfo cartInfo : cartInfos2) {
			Product product = ProductService.findByCartInfo(cartInfo.getProId());
			CartInfoVo cartInfoVo = new CartInfoVo();
			cartInfoVo.setProId(product.getProId());
			cartInfoVo.setProImg(product.getProImg());
			cartInfoVo.setProPrice(product.getProPrice());
			cartInfoVo.setProName(product.getProName());
			cartInfoVo.setProNum(cartInfo.getProNum());
			double money = cartInfo.getProNum() * product.getProPrice();
			cartInfoVo.setProMoney(money);
			products.add(cartInfoVo);
			//查看商品库存是否充足   否则失败
			if(product.getProNum()<cartInfo.getProNum()) {
				modelAndView.addObject("data", product.getProName()+" 库存不足，生成订单失败！");
				modelAndView.setViewName("jiesuan");
				return modelAndView;
			}
		}
		//创建订单
		Order order = new Order();
		order.setUserId(Integer.valueOf(user.getUserId() + ""));
		order.setCreateDate(new Date(System.currentTimeMillis()));
		order.setIsAccept(0);
		order.setOrderType(0);
		int num = orderService.saveOrder(order);
		if (num == 1) {
			Order order2 = orderService.selectAllByUserId(user.getUserId());
			session.setAttribute("order", order2);
			double sumMoney = 0;
			// 生成订单详情
			for (CartInfoVo cartInfoVo : products) {
				Product product=ProductService.findById(cartInfoVo.getProId());
				product.setProNum(product.getProNum()-cartInfoVo.getProNum());
				ProductService.updateProduct(product);
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setOrderId(order2.getOrderId());
				orderInfo.setProId(cartInfoVo.getProId());
				orderInfo.setProNum(cartInfoVo.getProNum());
				orderInfo.setoInfoMoney(cartInfoVo.getProMoney());
				orderInfo.setReceiveraddress(address);
				orderInfo.setReceiverphone(phone);
				orderInfo.setReceivername(username);
				orderInfo.setPaystate(0);
				orderService.saveOrderInfo(orderInfo);
				sumMoney += cartInfoVo.getProMoney();
				//清除购物车详情中该商品信息
				cartService.delectByProid(cartInfoVo.getProId());
			}
			modelAndView.addObject("sumMoney", sumMoney);
			modelAndView.setViewName("businessMoney");
		} else {
			modelAndView.addObject("data", "生成订单失败！");
			modelAndView.setViewName("jiesuan");
		}
		return modelAndView;
	}

	/**
	 * 支付
	 * @param products
	 * @return
	 */
	@RequestMapping(value="/payOrder")
	ModelAndView payOrder(HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		User user = (User) session.getAttribute("session_user");
		//获取最近一笔订单
		Order order=orderService.selectAllByUserId(user.getUserId());
		//获取订单详情
		List<OrderInfo> orderInfo=orderService.selectAllByOrderId(order.getOrderId());
		for(OrderInfo orderInfo2:orderInfo) {
			orderInfo2.setPaystate(1);
			orderService.updateOrderInfo(orderInfo2);
		}
		modelAndView.addObject("data", "支付成功");
		modelAndView.setViewName("gouwuche");
		return modelAndView;
	}
	
	/**
	 * 取消支付
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/cancelPay")
	ModelAndView cancelPay(String userId,HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		Order order=(Order)session.getAttribute("order");
		// 获取该用户的购物车
		Cart cart = cartService.findByUserId(Integer.valueOf(userId));
		//查询订单详情   获取商品id和数量  放回购物车
		List<OrderInfo> orderInfos=orderService.findByOrderId(order.getOrderId());
		List<CartInfoVo> cartInfoVos=new ArrayList<>();
		for(OrderInfo orderInfo:orderInfos) {
			//获取订单商品
			CartInfoVo cartInfoVo=new CartInfoVo();
			cartInfoVo.setProId(orderInfo.getProId());
			cartInfoVo.setProNum(orderInfo.getProNum());
			cartInfoVos.add(cartInfoVo);
			//删除订单详情表数据
			orderService.deleteInfoById(orderInfo.getoInfoId());
		}
		//删除订单
		orderService.deleteOrderById(order.getOrderId());
		//加回购物车
		for(CartInfoVo cartInfoVo:cartInfoVos) {
			CartInfo cartInfo=new CartInfo();
			cartInfo.setCartId(cart.getCartId());
			cartInfo.setProId(cartInfoVo.getProId());
			cartInfo.setProNum(cartInfoVo.getProNum());
			cartInfo.setCreateDate(new Date(System.currentTimeMillis()));
			//放回购物车
			cartService.save(cartInfo);
			//商品库存数量追回
			Product product=ProductService.findById(cartInfoVo.getProId());
			product.setProNum(product.getProNum()+cartInfoVo.getProNum());
			ProductService.updateProduct(product);
		}
		modelAndView.addObject("data", "已取消交易");
		modelAndView.setViewName("gouwuche");
		return modelAndView;
	}
	
	
	/**
	 * 查询订单
	 * 
	 * @param searchOrderStr
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/orderTable", method = RequestMethod.GET)
	@ResponseBody
	Results orderTable(String userid, int page) {
		//查看用户所有订单
		List<Order> orders=orderService.selectOrderByUserId(userid);
		//查看订单详情
		List<OrderInfo> orderInfos=new ArrayList<>();
		for(Order order:orders) {
			List<OrderInfo> orderInfos2=orderService.findByOrderId(order.getOrderId());
			for(OrderInfo orderInfo:orderInfos2) {
				orderInfos.add(orderInfo);
			}
		}
		// 获取符合条件的总条数   订单总数
		long total = orderInfos.size();
		// 计算总页数
		long totalPage = total % 2 == 0 ? total / 2 : total / 2 + 1;
		// 查询当页数据   商品图片  订单详情编号  数量   总额   创建时间 
		List<ProductVo> product = orderService.findOrderByPageAndCount(2, page,userid);
		DatatablesViewPage datatablesViewPage = new DatatablesViewPage(product, totalPage, total);
		return Results.ok("查询成功").put("data", datatablesViewPage);
	}
	
	
	/**
	 * 收货信息
	 * @param orderinfo
	 * @return
	 */
	@RequestMapping(value="/receiveInfo")
	ModelAndView receiveInfo(Integer orderinfo) {
		ModelAndView modelAndView=new ModelAndView();
		OrderInfo orderInfo2=orderService.findByOrderInfoId(orderinfo);
		Order order=orderService.findByOrder(orderInfo2.getOrderId());
		modelAndView.setViewName("dingdaninfo");
		modelAndView.addObject("orderInfo", orderInfo2);
		String date=order.getCreateDate().toLocaleString();
		modelAndView.addObject("createDate",date);
		return modelAndView;
	}
	

	/**
	 * 处理订单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/editOrder", method = RequestMethod.GET)
	@ResponseBody
	Results editOrder(@RequestParam("selectFlags") String[] ids) {
		for (String id : ids) {
			int num = orderService.updateOrder(Integer.valueOf(id));
			if (num != 1) {
				return Results.error("订单处理失败，编号为:" + id);
			}
		}
		return Results.ok("处理成功");
	}
}
