package com.ssh.nisus.domain;

/*

user_id` bigint(32) NO
user_code` varchar(32)
user_name` varchar(64)
user_password` varchar
user_state` char(1) NO

 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户bean
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-11-24-21:49
 */
@Entity
@Table(name = "sys_user", schema = "crm_hibernate")
@GenericGenerator(name = "genID", strategy = "native")
public class User {
	
	
	/**
	 * 用户状态为正常
	 */
	public final static int NORMAL = 1;
	
	/**
	 * 用户状态为暂停
	 */
	public final static int PAUSE = 0;
	
	@Id
	@GeneratedValue(generator = "genID")
	private Long user_id;       // 用户ID
	private String user_code;      // 用户名称
	private String user_name;       // 用户昵称
	private String user_password;   // 用户密码
	private String user_state;      // 用户状态 '1:正常,0:暂停'
	
	public User() {
	}
	
	public User(Long user_id, String user_code, String user_name, String user_password, String user_state) {
		this.user_id = user_id;
		this.user_code = user_code;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_state = user_state;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_code() {
		return user_code;
	}
	
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getUser_password() {
		return user_password;
	}
	
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	public String getUser_state() {
		return user_state;
	}
	
	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
	
	@Override
	public String toString() {
		return "User{" +
				"user_id=" + user_id +
				", user_code='" + user_code + '\'' +
				", user_name='" + user_name + '\'' +
				", user_password='" + user_password + '\'' +
				", user_state='" + user_state + '\'' +
				'}';
	}
}
