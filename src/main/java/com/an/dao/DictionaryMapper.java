package com.an.dao;

import java.util.List;

import com.an.entity.Dictionary;
import com.an.entity.Product;

public interface DictionaryMapper {
    int deleteByPrimaryKey(Integer ddId);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Integer ddId);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);

    /**
     * 初始查询总数量
     * @param searchStr
     * @return
     */
	long selectCount(String searchStr);

	/**
	 * 分页查询数据
	 * @param i
	 * @param j
	 * @param searchStr
	 * @return
	 */
	List<Dictionary> findDataByPageAndCount(int i, int j, String searchStr);

	/**
	 * 根据名称查询
	 * @param ddName
	 * @return
	 */
	Dictionary findByDicName(String ddName);

	/**
	 * 根据id删除
	 * @param valueOf
	 * @return
	 */
	int deleteById(Integer valueOf);

	/**
	 * 根据id查询
	 * @param valueOf
	 * @return
	 */
	Dictionary findById(Integer valueOf);

	/**
	 * 根据id更新
	 * @param dictionary
	 * @return
	 */
	int updateById(Dictionary dictionary);

	/**
	 * 查询所有类目
	 * @return
	 */
	List<Dictionary> findAll();
}