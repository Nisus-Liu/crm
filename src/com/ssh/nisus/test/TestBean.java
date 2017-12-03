package com.ssh.nisus.test;

import com.ssh.nisus.utils.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 测试spring是否服务器启动而启动, 且能够正确创建bean
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-11-29-17:13
 */
@Component("testBean")
public class TestBean {
	private String bname;
	// idea add
	private int bclass;
	
	public TestBean() {
		Log.begin("构造(测试spring是否能够随服务器启动而工作)");
		Log.end();
	}
	
	
	public String getBname() {
		return bname;
	}
	
	@Value("TestBean")
	public void setBname(String bname) {
		this.bname = bname;
	}
	
	@Override
	public String toString() {
		return "TestBean{" +
				"bname='" + bname + '\'' +
				'}';
	}
}
