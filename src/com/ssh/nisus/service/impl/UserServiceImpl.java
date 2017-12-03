package com.ssh.nisus.service.impl;

import com.opensymphony.xwork2.ActionContext;
import com.ssh.nisus.dao.UserDao;
import com.ssh.nisus.dao.impl.UserDaoImpl;
import com.ssh.nisus.domain.User;
import com.ssh.nisus.service.UserService;
import com.ssh.nisus.utils.HibUtil;
import com.ssh.nisus.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现类
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-11-24-22:01
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	
	public UserServiceImpl() {
		Log.begin("构造userService");
		Log.end();
	}
	
	@Autowired
	private UserDao userDao;
	
	
	/* 考虑到ssh整合后, Service层方法命名应该和aop中配置的一致, 否则无法织入事务管理通知, 因此, 有两种思路:
	 * 1. 只要用到dao层方法的方法均设为合适的名字
	 * 2. 为了表明业务含义, 依然用业务相关名称命名对应的方法, 但是方法内部涉及dao层的代码抽取成私有的方法*/
	
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@Override
	public User login(User user) {
		User user_db = null;
		
		user_db = getUserByCode(user.getUser_code());
		
		
		
		
//		^ 替换
//		try {
//			HibUtil.start();
//			user_db = userDao.getByUserCode(user.getUser_code());
//			HibUtil.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			HibUtil.rollback();
//		}
//		$ 替换
		
		// 没有找到用户则说明用户不存在
		if (user_db == null) {
			// 注: 由于异常映射的机制, 设置的request域的信息会消失
//			ActionContext.getContext().put("loginResult", "此账户不存在!");
			throw new RuntimeException("此账户不存在!");
		}
		// 查出对应的user则判断密码是否一致
		if (!user.getUser_password().equals(user_db.getUser_password())) {
			ActionContext.getContext().put("loginResult", "密码错误!");
			throw new RuntimeException("密码错误!");
		}

		// 异常在页面去异常对象中取
//		ActionContext.getContext().put("loginResult", "登录成功!");
		
		return user_db;
	}
	
	public User getUserByCode(String user_code) {
		// 调用dao层方法获取user
		
		return userDao.getByUserCode(user_code);
	}
	
	public void fun() {
		System.out.println("service fun()");
	}
}
