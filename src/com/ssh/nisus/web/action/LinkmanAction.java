package com.ssh.nisus.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.ssh.nisus.domain.Linkman;
import com.ssh.nisus.domain.beankit.PageBean;
import com.ssh.nisus.service.LinkmanService;
import com.ssh.nisus.utils.Log;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 联系人
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-12-06-21:51
 */
@Controller("linkmanAction")
@Scope("prototype")
public class LinkmanAction extends BaseAction implements ModelDriven{
	// 页面条件封装成对象
	private Linkman linkman = new Linkman();
	// 当前页
	private Integer currentPage;
	// 页大小
	private Integer pageSize;
	@Resource(name = "linkmanService")
	private LinkmanService linkmanService;
	
	/**
	 * 进入联系列表页
	 * 1. 点击菜单进入
	 * 2. 点击筛选
	 * 3. 点击前一页/后一页
	 * 4. 直接输入页号
	 *
	 * 页号, 页大小, 筛选条件(联系人各属性-->封装成LinkMan对象)
	 * @return
	 */
	public String goLinkmanUI() {
		Log.begin();
		DetachedCriteria dc = DetachedCriteria.forClass(Linkman.class);
		if (linkman.getLkm_name() != null && !"".equals(linkman.getLkm_name())) {
			dc.add(Restrictions.like("lkm_name", linkman.getLkm_name()));
		}
		// 所属客户
		if( linkman.getLkm_id() != null) {
			dc.add(Restrictions.eq("lkm_cust_id", linkman.getLkm_id()));
		}
		PageBean pb = linkmanService.getPageBean(currentPage, pageSize, dc);
		put("pageBean", pb);
		Log.end();
		return "goLinkmanUI";
	}
	
	
	@Override
	public Linkman getModel() {
		return linkman;
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
