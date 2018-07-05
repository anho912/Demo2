package com.an.vo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	/**
	 * 获取订单信息
	 * @param orderId  订单编号或者订单id
	 * @return
	 */
	@RequestMapping("/路径")
	@ResponseBody
	public Map<String, Object> getOrderInfo(Integer orderId) {
		//建一个集合
		Map<String, Object> map=new HashMap<>();
		//根据订单id或者编号去订单表查询订单信息（订单号码，日期，支付方式，订单状态）封装成一个对象
		
		//再根据订单id去订单详情表查商品信息（商品的数量，单价，计算总额）封装成一个对象
		
		//最后剩下支付时间范围，三分钟或者五分钟什么的，看看你们是数据库取还是在这里写死
		
		//三个都查出来了，就添加到map集合中，return就行了
		
		return map;
	}
	
}
