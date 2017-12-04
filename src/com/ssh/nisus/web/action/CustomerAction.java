package com.ssh.nisus.web.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.nisus.dao.CustomerDao;
import com.ssh.nisus.dao.impl.CustomerDaoImpl;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.domain.beankit.PageBean;
import com.ssh.nisus.factory.BeanFactory;
import com.ssh.nisus.service.CustomerService;
import com.ssh.nisus.service.impl.CustomerServiceImpl;
import com.ssh.nisus.utils.Debug;
import com.ssh.nisus.utils.Log;
import com.ssh.nisus.utils.SpUtil;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 客户
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@136.com
 * @date: 2017-11-20-21:13
 */
@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
    
    
    // 获取service对象
//    private CustomerService customerService = (CustomerService) SpUtil.getBean("customerService");
//  private CustomerService customerService = (CustomerService) BeanFactory.getBean("CustomerService");
//  private CustomerService customerService = new CustomerServiceImpl();
    private Customer customer = new Customer();
    @Autowired
	private CustomerService customerService;
	private Integer currentPage;
	private Integer pageSize;
	
	
//    :: 分页查询替换
//    public String list() {
//        Debug.log("进入list方法");
//
//
//        // 查询所有客户
//        List<Customer> list = customerService.findAll();
//
//        // 返回值
////        HttpServletRequest request = ServletActionContext.getRequest();
////        request.setAttribute("list", list);
//
//        // ognl玩法
//        ActionContext.getContext().put("list", list);
//        System.out.println(list.size());
//        Debug.log("退出list方法");
//        return "list";
//    }
	
	
	public String list() {
		Log.begin();
		// 跟踪前台获取的参数
		Log.trace(currentPage);
		Log.trace(pageSize);
		Log.trace(customer.getCust_name());
		
		// 跳转进入客户列表  --第一页, 默认页大小, 无查询条件
		// 点击客户列表  --同上
		// 输入条件(客户姓名模糊查询), 点击筛选  --有条件
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		// 设置查询条件
		String cust_name = customer.getCust_name();
		if (customer != null && cust_name!=null && !"".equals(cust_name)) {
			dc.add(Restrictions.like("cust_name", "%"+cust_name+"%"));
		}
		
		PageBean pb = customerService.getPageBean(currentPage, pageSize, dc);
		Log.trace(Arrays.toString(pb.getPageSizeSelector()));
		ActionContext.getContext().put("pageBean", pb);
		return "list";
	}
	
	
	/**
     * 删除一个客户
     * @return
     */
    public String deleteOne() {
        Debug.log("START: deleteOne");
        System.out.println("获取的model=="+customer);
        boolean flag = customerService.deleteOneById(customer);
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
        System.out.println("model=="+customer);
        boolean flag = customerService.add(customer);
    
        Debug.log("退出add方法");
        return "goCustomerList";
    }
    
    @Override
    public Customer getModel() {
    
        return customer;
    }
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
