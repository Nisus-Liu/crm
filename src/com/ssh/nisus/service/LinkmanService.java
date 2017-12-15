package com.ssh.nisus.service;

import com.ssh.nisus.domain.beankit.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface LinkmanService {
	PageBean getPageBean(Integer currentPage, Integer pageSize, DetachedCriteria dc);
}
