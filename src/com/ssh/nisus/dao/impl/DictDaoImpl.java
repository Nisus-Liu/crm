package com.ssh.nisus.dao.impl;

import com.ssh.nisus.dao.DictDao;
import com.ssh.nisus.domain.BaseDict;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-12-05-17:59
 */
@Repository("dictDao")
public class DictDaoImpl extends BaseDaoImpl<BaseDict> implements DictDao {
	@Override
	@Autowired
	public void setSessionFactoryToHibernateDaoSupport(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void save(BaseDict baseDict) {
	
	}
	
	@Override
	public void deleteAll(BaseDict baseDict) {
	
	}
	
	@Override
	public void deleteById(Serializable id) {
	
	}
	
	@Override
	public void update(BaseDict baseDict) {
	
	}
	
	@Override
	public BaseDict getById(Serializable id) {
		return null;
	}
	
	
	
	@Override
	public List<BaseDict> getByCriteria(DetachedCriteria dc) {
		List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);
		return list;
	}
}
