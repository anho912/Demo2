package com.an.vo;

/**
 * 用户登录扩展类
 * @author 疯狂的蜗牛君_
 *
 */
public class LoginUser {

	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 是否记住我  0 记住  1 不记住
	 */
	private Integer remember;

	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginUser(String username, String password, Integer remember) {
		super();
		this.username = username;
		this.password = password;
		this.remember = remember;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRemember() {
		return remember;
	}

	public void setRemember(Integer remember) {
		this.remember = remember;
	}
	
	
}
