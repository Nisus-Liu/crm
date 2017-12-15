package com.ssh.nisus.web.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.ssh.nisus.dao.CustomerDao;
import com.ssh.nisus.dao.impl.CustomerDaoImpl;
import com.ssh.nisus.domain.BaseDict;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.domain.beankit.PageBean;
import com.ssh.nisus.factory.BeanFactory;
import com.ssh.nisus.service.CustomerService;
import com.ssh.nisus.service.DictService;
import com.ssh.nisus.service.impl.CustomerServiceImpl;
import com.ssh.nisus.utils.Debug;
import com.ssh.nisus.utils.Log;
import com.ssh.nisus.utils.SpUtil;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

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
public class CustomerAction extends BaseAction implements ModelDriven<Customer>{
    
    
    // 获取service对象
//    private CustomerService customerService = (CustomerService) SpUtil.getBean("customerService");
//  private CustomerService customerService = (CustomerService) BeanFactory.getBean("CustomerService");
//  private CustomerService customerService = new CustomerServiceImpl();
    private Customer customer = new Customer();
    @Autowired
	private CustomerService customerService;
	private Integer currentPage;
	private Integer pageSize;
	@Autowired
	private DictService dictService;


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
	
	// 只需要前台页面提交一个名称一致的input标签
	// struts2 自动封装成file对象
	private File file;
	// input标签name属性加FileName后缀, 即可获取源文件名
	private String fileFileName;
	// 后缀ContentType: 封装文件的MiMe类型
	private String fileContentType;
	
	public String getFileContentType() {
		return fileContentType;
	}
	
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	public String getFileFileName() {
		return fileFileName;
	}
	
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * 文件上传测试
	 * @return
	 */
	public String fileUpload() {
		Log.begin();
		if (file != null) {
			// 获取真实路径
			String realPath = ServletActionContext.getServletContext().getRealPath("WEB-INF/upload");
			File dir = new File(realPath);
			// 目标路径不存在就创建一个
			if (!dir.exists()) {
				dir.mkdirs();
			}
			// 生成目的文件
			Log.trace(fileFileName);
			File file2 = new File(dir.getAbsolutePath() + "/" + fileFileName);
//			realPath += "/xxx";     // windows下/无效
			Log.trace(file2.getAbsolutePath());
			// 剪切至目标路径
			this.file.renameTo(file2);
			
		}
		Log.end();
		return "goHomeUI";
	}
	
	
	
	
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
     * 获取Customer ID, 跳转至编辑页面, 实现数据回填.
     * @return
     */
    public String goEditUI() {
    	Log.begin();
	    // 调用业务逻辑, 获取本客户信息
	    customer = customerService.get(customer);
	    // 跳转页面, 数据回填
	    ActionContext context = ActionContext.getContext();
//	    context.put("customer",customer);
	    ValueStack vsk = context.getValueStack();
	    vsk.push(customer);
	
	    // ----获取各个分类列表----
	    putDictList();
	
	    Log.end();
        return "goEditUI";
    }
	
	/**
	 * 获取用户字段分类列表, 并存入context中
	 */
	private void putDictList() {
		
		
		// 客户级别: 006
		// 提供一个单例的离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);
		dc.add(Restrictions.eq("dictTypeCode", "006"));
		List<BaseDict> cust_level_list = dictService.getByType(dc);
		
		
		// 清除条件
		SpUtil.eraseCriteria(dc);
		
		// 客户信息来源: 002
		dc.add(Restrictions.eq("dictTypeCode", "002"));
		List<BaseDict> cust_source_list = dictService.getByType(dc);
		
		
		// 所属行业: 001
		SpUtil.eraseCriteria(dc);
		dc.add(Restrictions.eq("dictTypeCode", "001"));
		List<BaseDict> cust_industry_list = dictService.getByType(dc);
		
		
		put("cust_level_list", cust_level_list);
		put("cust_source_list", cust_source_list);
		put("cust_industry_list", cust_industry_list);
	}
	
	/**
	 * 将修改的客户信息更新至数据库
	 * @return
	 */
	public String update() {
    	Log.trace(customer);
		Boolean flag = customerService.update(customer);
		if (flag) {
			return "goCustomerList";

		}else{
			ActionContext.getContext().put("error", "更新客户信息失败!");
			return "error";
		}
	}

	
	/**
     * 跳转到添加页面
     *
     * @return
     */
    public String goAddUI() {
    	// 获取分类信息, 回显数据
    	// 重复方法-->抽取
	    putDictList();
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
	    if (flag) {
		    return "goCustomerList";
		
	    } else {
		    put("error", "新增客户失败!");
		    return "error";
	    }
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
