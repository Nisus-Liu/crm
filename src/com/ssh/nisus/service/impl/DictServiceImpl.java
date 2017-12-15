package com.ssh.nisus.service.impl;

import com.ssh.nisus.dao.DictDao;
import com.ssh.nisus.domain.BaseDict;
import com.ssh.nisus.service.DictService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-12-05-17:54
 */
@Service("dictService")
public class DictServiceImpl implements DictService {
	@Autowired
	private DictDao dictdao;
	
	@Override
	public List<BaseDict> getByType(DetachedCriteria dc) {
		
		return dictdao.getByCriteria(dc);
		
	}
}
