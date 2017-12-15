package com.ssh.nisus.service.impl;

import com.ssh.nisus.dao.BaseDao;
import com.ssh.nisus.dao.impl.BaseDaoImpl;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.domain.beankit.PageBean;
import com.ssh.nisus.service.BaseService;
import com.ssh.nisus.utils.Log;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-12-06-22:37
 */
@Service("baseService")
public class BaseServiceImpl<T> implements BaseService {
	@Resource(name = "baseDao")
	private BaseDao baseDao = new BaseDaoImpl() {
		@Override
		public void setSessionFactoryToHibernateDaoSupport(SessionFactory sessionFactory) {
		
		}
	};
	
	/**
	 * 通用分页方法
	 * @param currentPage
	 * @param pageSize
	 * @param dc
	 * @return
	 */
	@Override
	public PageBean getPageBean(Integer currentPage, Integer pageSize, DetachedCriteria dc) {
		// 得到总记录数
		Integer rows = baseDao.getRows(dc);
		Log.trace("总行数: "+rows);
		PageBean pb = new PageBean(currentPage, pageSize, rows);
		
		// 查询数据
		List<T> customers = baseDao.getByPage(dc, pb.getStart(), pb.getPageSize());
		// 封装数据
		pb.setData(customers);
		// 返回
		return pb;
		
	}
}
