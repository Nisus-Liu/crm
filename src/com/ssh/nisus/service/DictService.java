package com.ssh.nisus.service;

import com.ssh.nisus.domain.BaseDict;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface DictService {
	List<BaseDict> getByType(DetachedCriteria dc);
}
