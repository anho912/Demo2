package com.an.vo;

import javax.annotation.Resource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.an.dao.UserMapper;
import com.an.entity.User;

/**
 * 
 * @author 
 * 测试类
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)	
public class UserMapperTest extends BaseTest{
	@Resource
    private UserMapper userMapper;
	@Test
    public void testSelectByLoginName() {
        User user = userMapper.findByTel("15600357509");
        System.err.println(user.toString());
    }

}
