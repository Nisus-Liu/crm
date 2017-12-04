package com.ssh.nisus.dao.impl;

import com.ssh.nisus.dao.CustomerDao;
import com.ssh.nisus.domain.Customer;
import com.ssh.nisus.utils.HibUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@136.com
 * @date: 2017-11-20-22:28
 */
@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
//    @Resource(name = "hibernateTemplate")
    private HibernateTemplate hitp = getHibernateTemplate();
    
//    @Override
//    public List<Customer> findAll() {
//
//        Criteria criteria = HibUtil.getCurrentSession().createCriteria(Customer.class);
//        List list = criteria.list();
//
//        return list;
//    }
    
    
    

    public void dao() {
        System.out.println("dao层方法可行");
    }
    
    @Override
    public Customer selectById(Long cust_id) {
        Customer c = null;
        Session session = HibUtil.getCurrentSession();
        c = session.get(Customer.class, cust_id);
    
        return c;
    }
    
    @Override
    public boolean deleteOneById(Customer c) {
        Session session = HibUtil.getCurrentSession();
        session.delete(c);
        return true;
    }
    
    @Override
    public boolean add(Customer model) {
        Session session = HibUtil.getCurrentSession();
        Long save = (Long) session.save(model);
        System.out.println("保存返回结果==" + save);
        if (save <= 0) {
            throw new RuntimeException("保存用户失败:" + model);
        }
        return true;
    }
    
//    /**获取总行数
//     * @param dc
//     * @return
//     */
//    @Override
//    public Integer getRows(DetachedCriteria dc) {
//        // 设置聚合查询
//        dc.setProjection(Projections.rowCount());
//
//        List<Long> res = (List<Long>) hitp.findByCriteria(dc);
//        // 查完了一定要置空, 因为离线对象会被其他方法共享!!!!!!
//        dc.setProjection(null);
//        if (res != null && res.size() > 0) {
//            // long -> integer
//            return res.get(0).intValue();
//        } else {
//            return null;
//        }
//
//    }
    
    /**
     * 分页查询客户
     * @param dc
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Customer> getCustomerByPage(DetachedCriteria dc, Integer start, Integer pageSize) {
        List<Customer> res = (List<Customer>) hitp.findByCriteria(dc, start, pageSize);
    
        return res;
    }
    
    @Override
    @Resource(name = "sessionFactory")
    public void setSessionFactoryToHibernateDaoSupport(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
