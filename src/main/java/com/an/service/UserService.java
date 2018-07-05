package com.an.service;

import java.util.List;

import com.an.entity.User;

/**
 * 用户服务端接口
 * @author 疯狂的蜗牛君_
 *
 */
public interface UserService {

	/**
	 * 根据用户名称去查询用户
	 * @param username
	 * @return
	 */
	User findByUsername(String username);

	/**
	 * 添加一个用户
	 * @param user
	 * @return
	 */
	int saveUser(User user);

	/**
	 * 初始查询总页数
	 * @param searchUserStr
	 * @return
	 */
	long selectCount(String searchUserStr);
	
	/**
	 * 根据每页数量和当前页码查询数据
	 * @param count
	 * @param page
	 * @param searchUserStr 
	 * @return
	 */
	List<User> findUserDataByPageAndCount(int count, int page, String searchUserStr);

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
	 * 根据id更新一个会员信息
	 * @param user
	 * @return
	 */
	int updateUser(User user);

	/**
	 * 根据手机号去查询一个会员信息
	 * @param userTel
	 * @return
	 */
	User findByTel(String userTel);

}
