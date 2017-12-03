package com.ssh.nisus.web.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.nisus.dao.CustomerDao;
import com.ssh.nisus.dao.impl.CustomerDaoImpl;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.factory.BeanFactory;
import com.ssh.nisus.service.CustomerService;
import com.ssh.nisus.service.impl.CustomerServiceImpl;
import com.ssh.nisus.utils.Debug;
import com.ssh.nisus.utils.SpUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 客户
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@136.com
 * @date: 2017-11-20-21:13
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
    
    
    // 获取service对象
//    private CustomerService customerService = (CustomerService) BeanFactory.getBean("CustomerService");
//    private CustomerService customerService = new CustomerServiceImpl();
    private Customer model = new Customer();
    private CustomerService customerService = (CustomerService) SpUtil.getBean("customerService");
    
    public String list() {
        Debug.log("进入list方法");
    
    
        // 查询所有客户
        List<Customer> list = customerService.findAll();

        // 返回值
//        HttpServletRequest request = ServletActionContext.getRequest();
//        request.setAttribute("list", list);
        
        // ognl玩法
        ActionContext.getContext().put("list", list);
        System.out.println(list.size());
        Debug.log("退出list方法");
        return "list";
    }
    

    
    
    /**
     * 删除一个客户
     * @return
     */
    public String deleteOne() {
        Debug.log("START: deleteOne");
        System.out.println("获取的model=="+model);
        boolean flag = customerService.deleteOneById(model);
        Debug.log("END: deleteOne");
        if (flag) {
            return "goCustomerList";
        } else {
            return ERROR;
        }
    }
    
    /**
     * 修改某个客户
     * @return
     */
    public String edit() {
    
        return SUCCESS;
    }
    
    /**
     * 跳转到添加页面
     *
     * @return
     */
    public String goAddUI() {
        
        return "goAddUI";
    }
    /**
     * 添加一个客户
     *
     * @return
     */
    public String add() {
        Debug.log("进入add方法");
        System.out.println("model=="+model);
        boolean flag = customerService.add(model);
    
        Debug.log("退出add方法");
        return "goCustomerList";
    }
    
    @Override
    public Customer getModel() {
    
        return model;
    }
}
