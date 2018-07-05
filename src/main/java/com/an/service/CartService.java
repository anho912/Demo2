package com.an.service;

import java.util.List;

import com.an.entity.Cart;
import com.an.entity.CartInfo;
import com.an.entity.Product;
import com.an.entity.User;
import com.an.vo.CartInfoVo;

/**
 * 购物车服务端接口
 * @author 疯狂的蜗牛君_
 *
 */
public interface CartService {

	/**
	 * 查看该用户的购物车
	 * @param userId
	 * @return
	 */
	Cart findByUserId(Integer userId);

	/**
	 * 创建购物车
	 * @param userId
	 * @return
	 */
	int insertByUserId(Long userId);

	/**
	 * 添加到购物车
	 * @param user
	 * @param product
	 * @return
	 */
	int insertProByUser(User user, Product product);

	/**
	 * 查询总记录数
	 * @param userId
	 * @return
	 */
	long selectCount(Integer userId);

	/**
	 * 分页查询
	 * @param i
	 * @param page
	 * @param userId
	 * @return
	 */
	List<CartInfoVo> findDataByPageAndCount(int i, int page, Integer cartId);

	/**
	 * 根据商品删除购物车详情
	 * @param proId
	 * @return
	 */
	int delectByProid(Integer proId);

	/**
	 * 根据购物车查询购物车详情列表
	 * @param cartId
	 * @return
	 */
	List<CartInfo> findByCartId(Integer cartId);

	/**
	 * 插入购物车
	 * @param cartInfo
	 */
	void save(CartInfo cartInfo);

}
