package com.an.dao;

import java.util.List;

import com.an.entity.CartInfo;

public interface CartInfoMapper {
    int deleteByPrimaryKey(Integer cInfoId);

    int insert(CartInfo record);

    int insertSelective(CartInfo record);

    CartInfo selectByPrimaryKey(Integer cInfoId);

    int updateByPrimaryKeySelective(CartInfo record);

    int updateByPrimaryKey(CartInfo record);

    /**
     * 根据购物车id查询购物车详情
     * @param cartId
     * @return
     */
	List<CartInfo> findByCartId(Integer cartId);

	/**
	 * 根据商品id查询是否存在该商品
	 * @param proId
	 * @return
	 */
	CartInfo findByProduct(Integer proId);

	/**
	 * 分页查询
	 * @param i
	 * @param j
	 * @param cartId
	 * @return
	 */
	List<CartInfo> findDataByPageAndCount(int i, int j, Integer cartId);

	/**
	 * 查询总记录数
	 * @param cartId
	 * @return
	 */
	long selectPageCount(Integer cartId);

	/**
	 * 根据详情id更新购物车
	 * @param cartInfos
	 * @return
	 */
	int updateByCartId(CartInfo cartInfos);

	/**
	 * 根据商品删除购物车清单
	 * @param proId
	 * @return
	 */
	int delectByPro(Integer proId);
}