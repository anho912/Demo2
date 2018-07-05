package com.an.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.an.entity.User;
import com.an.service.CartService;
import com.an.service.UserService;
import com.an.utils.Results;
import com.an.vo.DatatablesViewPage;
import com.an.vo.LoginUser;

/**
 * 用户控制器
 * 
 * @author 疯狂的蜗牛君_
 *
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private CartService CartService;
	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	ModelAndView userlogin(LoginUser loginUser, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.findByUsername(loginUser.getUsername());
		if (user != null) {
			if (user.getUserRole() != null && user.getUserRole().equals("member")) {
				if (user.getPassword().equals(loginUser.getPassword())) {
					session.setAttribute("session_user", user);
					modelAndView.setViewName("index");
					return modelAndView;
				} else {
					modelAndView.addObject("info", "密码错误，登录失败！");
					modelAndView.setViewName("login");
					return modelAndView;
				}
			}
			modelAndView.addObject("info", "用户无权限登录!");
			modelAndView.setViewName("login");
			return modelAndView;
		}
		modelAndView.addObject("info", "用户不存在!");
		modelAndView.setViewName("login");
		return modelAndView;
	}

	/**
	 * 退出登录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	String logout(HttpSession session) {
		// 移除session
		session.removeAttribute("session_user");
		return "redirect:/login";
	}

	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/userRegist",method=RequestMethod.POST)
	ModelAndView userRegist(User user) {
		ModelAndView modelAndView=new ModelAndView();
		user.setCreateDate(new Timestamp(System.currentTimeMillis()));
		user.setUserRole("member");
		User user2=userService.findByUsername(user.getUserName());
		if(user2==null) {
			User user3=userService.findByTel(user.getUserTel());
			if(user3==null) {
				int num=userService.saveUser(user);
				//创建购物车
				User user4=userService.findByTel(user.getUserTel());
				int num2=CartService.insertByUserId(user4.getUserId());
				if(num==1) {
					modelAndView.setViewName("login");
					return modelAndView;
				}else {
					modelAndView.addObject("info", "注册失败！");
					modelAndView.setViewName("register");
					return modelAndView;
				}
			}else {
				modelAndView.addObject("info", "手机号已被其他用户绑定，请更换！");
				modelAndView.setViewName("register");
				return modelAndView;
			}
		}else {
			modelAndView.addObject("info", "用户名已存在，添加失败！");
			modelAndView.setViewName("register");
			return modelAndView;
		}
	}
	
	/**
	 * 查看自己的個人信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/userInfo",method=RequestMethod.GET)
	ModelAndView userInfo(HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		User user=(User)session.getAttribute("session_user");
		User user2=userService.findById(Integer.valueOf(user.getUserId()+""));
		modelAndView.addObject("users", user2);
		modelAndView.setViewName("self_info");
		return modelAndView;
	}
	
	/**
	 * 获取信息去编辑
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getOneUserInfo",method=RequestMethod.GET)
	ModelAndView getOneUserInfo(HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		User user=(User)session.getAttribute("session_user");
		User user2=userService.findById(Integer.valueOf(user.getUserId()+""));
		modelAndView.addObject("users", user2);
		modelAndView.setViewName("edit_userInfo");
		return modelAndView;
	}
	
	/**
	 * 编辑个人信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/editUserInfo",method=RequestMethod.POST)
	@ResponseBody
	Results editUserInfo(User user) {
		int num=userService.updateUser(user);
		if(num==1) {
			return Results.ok("编辑成功");
		}
		return Results.error("编辑失败");
	}
}
