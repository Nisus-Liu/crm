package com.ssh.nisus.dao;

import com.ssh.nisus.domain.User;

public interface UserDao {
	
	public User getByUserCode(String user_code);

}
