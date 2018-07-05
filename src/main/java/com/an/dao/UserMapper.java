package com.an.dao;

import java.util.List;

import com.an.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名称去查询用户对象
     * @param username
     * @return
     */
	User selectByUsername(String username);
	/**
	 * 初始查询符合条件的总条数
	 * @param searchUserStr
	 * @return
	 */
	long selectPageCount(String searchUserStr);
	
	/**
	 * 根据分页条件查询数据
	 * @param count
	 * @param page
	 * @param searchUserStr
	 * @return
	 */
	List<User> findUserDataByPageAndCount(int count, int page, String searchStr);

	/**
	 * 根据id删除会员
	 * @param valueOf
	 * @return
	 */
	int deleteById(Integer valueOf);
	/**
	 * 根据id去查询一个会员
	 * @param valueOf
	 * @return
	 */
	User findById(Integer valueOf);

	/**
	 * 根据id去更新一个会员
	 * @param user
	 * @return
	 */
	int updateById(User user);

	/**
	 * 根据手机号去查询一个会员信息
	 * @param userTel
	 * @return
	 */
	User findByTel(String userTel);
}