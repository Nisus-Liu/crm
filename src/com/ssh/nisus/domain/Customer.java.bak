package com.ssh.nisus.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/*

customer.cust_name
customer.cust_level
customer.cust_source
customer.cust_linkman
customer.cust_phone
customer.cust_mobile

 */


/**
 * 客户bean
 *
 * 客户 1:n 联系人
 */
@Entity        // 声明这是个实体(需要和数据表对应)
@Table(name = "cst_customer", schema = "crm_hibernate")     //指定表名
@GenericGenerator(name = "genID", strategy = "native")      // 通用生成器\\这里面支持native, 但是jpa的GeneratedValue标签不支持native
public class Customer {
	// 2017年11月15日 下午3:34:15 Nisus
	// 引用通用生成器的, 也可以这里直接定义策略.
	@Id
	@GeneratedValue(generator = "genID")
	private Long cust_id; // 主键
	private String cust_name; // '客户名称(公司名称)'
	private String cust_source; // '客户信息来源',
	private String cust_level; // '客户级别',
	private String cust_linkman; // '联系人',
	private String cust_phone; // '固定电话',
	private String cust_mobile; // '移动电话',

	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "customer", fetch = FetchType.LAZY)
	private Set<Linkman> linkMen = new HashSet<Linkman>();

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_source() {
		return cust_source;
	}

	public void setCust_source(String cust_source) {
		this.cust_source = cust_source;
	}

	public String getCust_level() {
		return cust_level;
	}

	public void setCust_level(String cust_level) {
		this.cust_level = cust_level;
	}

	public String getCust_linkman() {
		return cust_linkman;
	}

	public void setCust_linkman(String cust_linkman) {
		this.cust_linkman = cust_linkman;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	public String getCust_mobile() {
		return cust_mobile;
	}

	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}

	public Set<Linkman> getLinkMen() {
		return linkMen;
	}

	public void setLinkMen(Set<Linkman> linkMen) {
		this.linkMen = linkMen;
	}
	
	@Override
	public String toString() {
		return "Customer{" +
				"cust_id=" + cust_id +
				", cust_name='" + cust_name + '\'' +
				", cust_source='" + cust_source + '\'' +
				", cust_level='" + cust_level + '\'' +
				", cust_linkman='" + cust_linkman + '\'' +
				", cust_phone='" + cust_phone + '\'' +
				", cust_mobile='" + cust_mobile + '\'' +
				'}';
	}
}