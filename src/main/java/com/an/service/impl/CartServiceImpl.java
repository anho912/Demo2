package com.an.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.dao.CartInfoMapper;
import com.an.dao.CartMapper;
import com.an.dao.ProductMapper;
import com.an.entity.Cart;
import com.an.entity.CartInfo;
import com.an.entity.Product;
import com.an.entity.User;
import com.an.service.CartService;
import com.an.vo.CartInfoVo;
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private CartInfoMapper cartInfoMapper;
	
	@Autowired
	private ProductMapper ProductMapper;

	@Override
	public Cart findByUserId(Integer userId) {
		// TODO Auto-generated method stub
		Cart cart=cartMapper.findByUserId(userId);
		return cart;
	}

	@Override
	public int insertByUserId(Long userId) {
		// TODO Auto-generated method stub
		Cart cart=new Cart();
		cart.setUserId(Integer.valueOf(userId+""));
		cart.setCreateDate(new Date(System.currentTimeMillis()));
		return cartMapper.insert(cart);
	}

	@Override
	public int insertProByUser(User user, Product product) {
		// TODO Auto-generated method stub
		Cart cart=cartMapper.findByUserId(Integer.valueOf(user.getUserId()+""));
		CartInfo cartInfos=cartInfoMapper.findByProduct(product.getProId());
		int num=0;
		if(cartInfos!=null) {
			cartInfos.setProNum(cartInfos.getProNum()+1);
			num=cartInfoMapper.updateByCartId(cartInfos);
		}else {
			CartInfo cartInfo=new CartInfo();
			cartInfo.setProId(product.getProId());
			cartInfo.setProNum(1);
			cartInfo.setCartId(cart.getCartId());
			cartInfo.setCreateDate(new Date(System.currentTimeMillis()));
			num=cartInfoMapper.insert(cartInfo);
		}
		return num;
	}

	@Override
	public long selectCount(Integer cartId) {
		// TODO Auto-generated method stub
		return cartInfoMapper.selectPageCount(cartId);
	}

	@Override
	public List<CartInfoVo> findDataByPageAndCount(int i, int page, Integer cartId) {
		// TODO Auto-generated method stub
		if(page<1) {
			page=1;
		}
		List<CartInfoVo> cartInfoVos=new ArrayList<>();
		List<CartInfo> cartInfos=cartInfoMapper.findDataByPageAndCount(i, (page-1)*i, cartId);
		for(CartInfo cartInfo:cartInfos) {
			Product product=ProductMapper.findById(cartInfo.getProId());
			CartInfoVo cartInfoVo=new CartInfoVo();
			cartInfoVo.setProId(product.getProId());
			cartInfoVo.setProImg(product.getProImg());
			cartInfoVo.setProPrice(product.getProPrice());
			cartInfoVo.setProName(product.getProName());
			cartInfoVo.setProNum(cartInfo.getProNum());
			double money=cartInfo.getProNum()*product.getProPrice();
			cartInfoVo.setProMoney(money);
			cartInfoVos.add(cartInfoVo);
		}
		return cartInfoVos;
	}

	@Override
	public int delectByProid(Integer proId) {
		// TODO Auto-generated method stub
		return cartInfoMapper.delectByPro(proId);
	}

	@Override
	public List<CartInfo> findByCartId(Integer cartId) {
		// TODO Auto-generated method stub
		return cartInfoMapper.findByCartId(cartId);
	}

	@Override
	public void save(CartInfo cartInfo) {
		// TODO Auto-generated method stub
		cartInfoMapper.insert(cartInfo);
	}
	
}
