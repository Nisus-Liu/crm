package com.ssh.nisus.web.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.nisus.constant.Constant;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.domain.User;
import com.ssh.nisus.service.CustomerService;
import com.ssh.nisus.service.UserService;
import com.ssh.nisus.service.impl.CustomerServiceImpl;
import com.ssh.nisus.service.impl.UserServiceImpl;
import com.ssh.nisus.utils.Debug;
import com.ssh.nisus.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.naming.Context;
import java.util.List;

/**
 * 用户登录注册退出相关
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@136.com
 * @date: 2017-11-24 21:01:23
 */

@Controller(value = "userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	private User user = new User();
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//	@Autowired
	@Resource(name = "userService")
	private UserService userService;
	
	/**
	 * 登录
	 */
	public String login() throws Exception {
		Log.begin();
		Log.trace(user);
		// 调用业务逻辑判断是否登录成功
		User user_db = null;
		Log.trace(userService);
		
		if (userService != null) {
			user_db = userService.login(user);
		} else {
			throw new RuntimeException("获取业务层对象失败!");
		}
		
		
		// 置为登录状态
		ActionContext.getContext().getSession().put(Constant.CURRENT_USER, user_db);
		Log.trace("user_db==" + user_db);
		Log.end();
		// 登录成功返回首页
		return "goHomeUI";
	}
	
	
	/**
	 * 退出登录
	 */
	public String logout() {
		// 删除session中的当前user对象
		ActionContext.getContext().getSession().remove(Constant.CURRENT_USER);
		
		
		// 跳转至首页
		
		return "goHomeUI";
	}
	
	
	/**
	 * 跳转至登录页面
	 *
	 * @return
	 */
	public String goLoginUI() {
		
		
		return "goLoginUI";
	}
	
	
	@Override
	public User getModel() {
		return user;
	}
}
