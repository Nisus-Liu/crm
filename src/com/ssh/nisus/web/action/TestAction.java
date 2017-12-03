package com.ssh.nisus.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.nisus.dao.CustomerDao;
import com.ssh.nisus.factory.BeanFactory;
import com.ssh.nisus.service.CustomerService;
import com.ssh.nisus.service.UserService;
import com.ssh.nisus.utils.Log;
import org.springframework.stereotype.Controller;

/**
 * 测试action类
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@136.com
 * @date: 2017-11-21-15:51
 */
//@Controller("testAction")
public class TestAction extends ActionSupport{
    private CustomerService factService = (CustomerService) BeanFactory.getBean("CustomerService");
    private CustomerDao factDao = (CustomerDao) BeanFactory.getBean("CustomerDao");
    // 等待spring注入
    private UserService userService;
    
    public void fun01() {
        System.out.println("请求到达TestAction");
        // 测试Service层是否可以正常说去工厂对象
        factService.service();
    
        // 测试action类里的工厂对象是否可以正常获取
        System.out.println("----TestAction类里工厂dao对象开始使用:");
        factDao.dao();
        System.out.println("//结束使用TestAction类里工厂dao对象----");
    }
    
    public String fun02() {
        Log.begin();
        ActionContext.getContext().put("lj", "刘和骏");
        Log.trace();
        return SUCCESS;
    }
    
    
    /**
     * 测试struts2与spring整合方式一: struts自己生成action, 但由spring注入属性, 即有spring维护依赖关系
     * @return
     */
    public String fun03() {
        Log.trace("userService="+userService);
        userService.fun();
        return SUCCESS;
    }
    
    
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
