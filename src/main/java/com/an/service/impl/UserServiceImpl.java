package com.an.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.dao.UserMapper;
import com.an.entity.User;
import com.an.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.selectByUsername(username);
	}

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.insert(user);
	}

	@Override
	public long selectCount(String searchUserStr) {
		// TODO Auto-generated method stub
		return userMapper.selectPageCount(searchUserStr);
	}

	@Override
	public List<User> findUserDataByPageAndCount(int count, int page, String searchStr) {
		// TODO Auto-generated method stub
		if(page<1) {
			page=1;
		}
		return userMapper.findUserDataByPageAndCount(count, (page-1)*count, searchStr);
	}

	@Override
	public int deleteById(Integer valueOf) {
		// TODO Auto-generated method stub
		return userMapper.deleteById(valueOf);
	}

	@Override
	public User findById(Integer valueOf) {
		// TODO Auto-generated method stub
		return userMapper.findById(valueOf);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateById(user);
	}

	@Override
	public User findByTel(String userTel) {
		// TODO Auto-generated method stub
		return userMapper.findByTel(userTel);
	}

}
