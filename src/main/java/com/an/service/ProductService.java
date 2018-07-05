package com.an.service;

import java.util.List;

import com.an.entity.Product;

/**
 * 商品服务端接口
 * @author 疯狂的蜗牛君_
 *
 */
public interface ProductService {

	/**
	 * 初始查询总页数
	 * @param searchStr
	 * @return
	 */
	long selectCount(String searchStr);

	/**
	 * 根据每页数量和当前页码查询数据
	 * @param i
	 * @param page
	 * @param searchStr
	 * @return
	 */
	List<Product> findProductDataByPageAndCount(int i, int page, String searchStr);

	/**
	 * 根据名称查询一个商品
	 * @param proName
	 * @return
	 */
	Product findByProName(String proName);

	/**
	 * 添加一个商品
	 * @param product
	 * @return
	 */
	int saveProduct(Product product);

	/**
	 * 删除一个商品
	 * @param valueOf
	 * @return
	 */
	int deleteById(Integer valueOf);

	/**
	 * 根据id查询一个商品
	 * @param valueOf
	 * @return
	 */
	Product findById(Integer valueOf);

	/**
	 * 更新一个商品
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);

	/**
	 * 查询六件商品
	 * @return
	 */
	List<Product> findSixAll();

	/**
	 * 查询10件商品
	 * @return
	 */
	List<Product> findTenAll();

	/**
	 * 根据购物车详情商品id查询商品
	 * @param proId
	 * @return
	 */
	Product findByCartInfo(Integer proId);

}
