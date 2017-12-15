package com.ssh.nisus.test.test;

/**
 * 测试父类的final方法
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-11-28-21:16
 */
public class Demo01 {
	
	public static void main(String[] args) {
		Zi zi = new Zi();
		zi.setA(34);
		System.out.println("zi.getA()==" + zi.getA());
		zi.fun();
	}

}
