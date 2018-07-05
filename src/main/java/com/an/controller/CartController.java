package com.an.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.an.entity.Cart;
import com.an.entity.CartInfo;
import com.an.entity.Product;
import com.an.entity.User;
import com.an.service.CartService;
import com.an.service.ProductService;
import com.an.utils.Results;
import com.an.vo.CartInfoVo;
import com.an.vo.DatatablesViewPage;

/**
 * 购物车控制器
 * 
 * @author 疯狂的蜗牛君_
 *
 */
@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService ProductService;

	/**
	 * 查看当前用户的购物车
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/MyCart")
	ModelAndView MyCart(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) session.getAttribute("session_user");
		// 查询该用户的购物车
		// List<CartInfo> cartInfo=cartService.findByUserId(user.getUserId());
		// if(cartInfo.size()>0) {
		modelAndView.addObject("User", user);
		modelAndView.setViewName("gouwuche");
		return modelAndView;
	}

	/**
	 * 加载到购物车
	 * 
	 * @param proId
	 * @return
	 */
	@RequestMapping(value = "/loadingCart", method = RequestMethod.GET)
	@ResponseBody
	Results loadingCart(String proId, HttpSession session) {
		User user = (User) session.getAttribute("session_user");
		Product product = ProductService.findById(Integer.valueOf(proId));
		int num = 0;
		if (product.getProNum() > 0) {
			num = cartService.insertProByUser(user, product);
			if (num == 1) {
				return Results.ok("添加购物车成功");
			} else {
				return Results.ok("添加购物车失败");
			}
		} else {
			return Results.error("商品库存不足，添加失败");
		}
	}
	
	/**
	 * 查询购物车商品列表
	 * @param userId
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/CartInfoData",method=RequestMethod.GET)
	@ResponseBody
	Results CartInfoData(Integer userId, int page) {
		//获取该用户的购物车
		Cart cart=cartService.findByUserId(userId);
		// 获取符合条件的总条数
		long total = cartService.selectCount(cart.getCartId());
		// 计算总页数
		long totalPage = total % 2 == 0 ? total / 2 : total / 2 + 1;
		// 查询当页数据
		List<CartInfoVo> products = cartService.findDataByPageAndCount(2, page, cart.getCartId());
		DatatablesViewPage datatablesViewPage = new DatatablesViewPage(products, totalPage, total);
		return Results.ok("查询成功").put("data", datatablesViewPage);
	}
	
	/**
	 * 删除购物车商品
	 * @param proId
	 * @return
	 */
	@RequestMapping(value="/deleteCartInfo",method=RequestMethod.GET)
	@ResponseBody
	Results deleteCartInfo(Integer proId) {
		int num=cartService.delectByProid(proId);
		if(num>0) {
			return Results.ok("删除成功");
		}else {
			return Results.error("删除失败");
		}
	}
	
	/**
	 * 去结算购物车商品
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/qujiesuan")
	ModelAndView qujiesuan(@RequestParam("selectFlags") String ids) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("productId", ids);
		modelAndView.setViewName("jiesuan");
		return modelAndView;
	}
	
	/**
	 * 查询订单商品列表
	 * @param userId
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/OrderInfoData",method=RequestMethod.GET)
	@ResponseBody
	Results OrderInfoData(String[] productIds, int page,HttpSession session) {
		//根据商品查看在该用户的购物车情况
		User user = (User) session.getAttribute("session_user");
		//获取该用户的购物车
		Cart cart=cartService.findByUserId(Integer.valueOf(user.getUserId()+""));
		//获取购物详情列表
		List<CartInfo> cartInfos=cartService.findByCartId(cart.getCartId());
		List<CartInfo> cartInfos2=new ArrayList<>();
		for(String proid:productIds) {
			for(CartInfo cartInfo:cartInfos) {
				if(cartInfo.getProId()==Integer.valueOf(proid)) {
					cartInfos2.add(cartInfo);
				}
			}
		}
		//遍历购物车详情
		List<CartInfoVo> products=new ArrayList<>();
		for(CartInfo cartInfo:cartInfos2) {
			Product product=ProductService.findByCartInfo(cartInfo.getProId());
			CartInfoVo cartInfoVo=new CartInfoVo();
			cartInfoVo.setProId(product.getProId());
			cartInfoVo.setProImg(product.getProImg());
			cartInfoVo.setProPrice(product.getProPrice());
			cartInfoVo.setProName(product.getProName());
			cartInfoVo.setProNum(cartInfo.getProNum());
			double money=cartInfo.getProNum()*product.getProPrice();
			cartInfoVo.setProMoney(money);
			products.add(cartInfoVo);
		}
		// 获取符合条件的总条数
		long total = products.size();
		// 计算总页数
		long totalPage = total % 2 == 0 ? total / 2 : total / 2 + 1;
		DatatablesViewPage datatablesViewPage = new DatatablesViewPage(products, totalPage, total);
		return Results.ok("查询成功").put("data", datatablesViewPage);
	}
}
