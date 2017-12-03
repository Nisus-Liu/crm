package com.ssh.nisus.utils;

import com.opensymphony.xwork2.ActionContext;
import com.ssh.nisus.service.CustomerService;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * 根据类名(简称)获取对象
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-11-26-17:44
 */
public class SpUtil {
	
	public static Object getBean(String beanName) {
		ServletContext sc = ServletActionContext.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sc);
		return  wac.getBean(beanName);
	}
}
