package com.an.dao;

import java.util.List;

import com.an.entity.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer proId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer proId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);

    /**
     * 初始查询符合条件的总条数
     * @param searchStr
     * @return
     */
	long selectCount(String searchStr);

	/**
	 * 根据分页条件查询数据
	 * @param i
	 * @param j
	 * @param searchStr
	 * @return
	 */
	List<Product> findProductDataByPageAndCount(int i, int j, String searchStr,Integer del);

	/**
	 * 根据商品名称查询一个商品
	 * @param proName
	 * @return
	 */
	Product findByProName(String proName);

	/**
	 * 根据Id删除一个商品
	 * @param valueOf
	 * @return
	 */
	int deleteById(Integer valueOf);

	/**
	 * 根据Id查询一个商品
	 * @param valueOf
	 * @return
	 */
	Product findById(Integer valueOf);

	/**
	 * 根据Id更新一个商品
	 * @param product
	 * @return
	 */
	int updateById(Product product);

	/**
	 * 查询所有商品
	 * @return
	 */
	List<Product> findAll();
}