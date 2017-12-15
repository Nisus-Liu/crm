package com.ssh.nisus.utils;

import com.opensymphony.xwork2.ActionContext;
import com.ssh.nisus.service.CustomerService;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-11-26-17:44
 */
public class SpUtil {
	
	/**
	 * 根据类名(简称)获取对象
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		ServletContext sc = ServletActionContext.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sc);
		return  wac.getBean(beanName);
	}
	
	/**
	 * 利用反射清空hibernate离线查询对象中的所有条件
	 * 推荐思路二, 简单.
	 * @param dc
	 */
	public static void eraseCriteria(DetachedCriteria dc) {
		try {
			Field impl = dc.getClass().getDeclaredField("impl");
			impl.setAccessible(true);
			// 得到实现类
			CriteriaImpl cimpl = (CriteriaImpl) impl.get(dc);
			
			// 思路一: 遍历criterionEntries, 清空所有
			// 利用实现类的共有方法获取迭代器
//			Iterator<CriteriaImpl.CriterionEntry> criterionEntryIterator = cimpl.iterateExpressionEntries();
//			while (criterionEntryIterator.hasNext()) {
//				System.out.println("表达式: "+criterionEntryIterator.next());
//				criterionEntryIterator.remove();
//			}
			
			// 思路二: 再次反射, 直接将criterionEntries置空.
			Field criterionEntries = cimpl.getClass().getDeclaredField("criterionEntries");
			criterionEntries.setAccessible(true);
			// 重置条件list
			criterionEntries.set(cimpl, new ArrayList());
			
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
}
