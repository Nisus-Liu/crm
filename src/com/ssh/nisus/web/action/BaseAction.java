package com.ssh.nisus.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 提供基本的操作, 以供具体业务action继承
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-12-06-0:42
 */
public class BaseAction extends ActionSupport{
	
	/**
	 * 将键值对存入context中
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}
	
	/**
	 * 压入值栈
	 * @param value
	 */
	public void push(Object value) {
		ActionContext.getContext().getValueStack().push(value);
	}
	
	
}
