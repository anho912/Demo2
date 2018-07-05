package com.an.dao;

import java.util.List;

import com.an.entity.Cart;
import com.an.entity.CartInfo;
import com.an.vo.CartInfoVo;

public interface CartMapper {
    int deleteByPrimaryKey(Integer cartId);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer cartId);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    /**
     * 查询该用户的购物车
     * @param valueOf
     * @return
     */
	Cart findByUserId(Integer valueOf);

}