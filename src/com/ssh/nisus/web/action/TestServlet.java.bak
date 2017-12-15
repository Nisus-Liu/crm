package com.ssh.nisus.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.factory.BeanFactory;
import com.ssh.nisus.service.CustomerService;
import com.ssh.nisus.utils.Debug;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 * 客户
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@136.com
 * @date: 2017-11-20-21:13
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
    private CustomerService customerService = (CustomerService) BeanFactory.getBean("CustomerService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Debug.log("进入Servlet");
        // 查询所有客户
        List<Customer> list = customerService.findAll();


        Debug.log("Servlet执行结束");
    }

}


//    //    private CustomerService customerService = new CustomerServiceImpl();
//    public String list() {
//        Debug.log("进入list方法");
//        // 查询所有客户
//        List<Customer> list = customerService.findAll();
//
//        // 返回值
//        HttpServletRequest request = ServletActionContext.getRequest();
////        request.setAttribute("list", list);
//        Debug.log("退出list方法");
//        return SUCCESS;
//    }
//



