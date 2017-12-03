package com.ssh.nisus.test.dao;

import com.ssh.nisus.domain.User;

public interface TstUserDao {
	
	void saveUser(User user) ;
	
	void getUserById(Long user_id);
}
