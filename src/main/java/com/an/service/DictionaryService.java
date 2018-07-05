package com.an.service;

import java.util.List;

import com.an.entity.Dictionary;
import com.an.entity.Product;

/**
 * 类目服务端接口
 * @author 疯狂的蜗牛君_
 *
 */
public interface DictionaryService {

	/**
	 * 初始查询总条数
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
	List<Dictionary> findDicDataByPageAndCount(int i, int page, String searchStr);

	/**
	 * 根据类目名称查询数据
	 * @param ddName
	 * @return
	 */
	Dictionary findByDicName(String ddName);

	/**
	 * 添加一个商品类目
	 * @param dictionary
	 * @return
	 */
	int saveDictionary(Dictionary dictionary);

	/**
	 * 根据id删除一个商品类目
	 * @param valueOf
	 * @return
	 */
	int deleteById(Integer valueOf);

	/**
	 * 根据id查询一个商品类目
	 * @param valueOf
	 * @return
	 */
	Dictionary findById(Integer valueOf);

	/**
	 * 根据id更新一个商品类目
	 * @param dictionary
	 * @return
	 */
	int updateDic(Dictionary dictionary);

	/**
	 * 查询所有类目
	 * @return
	 */
	List<Dictionary> findAll();

}
