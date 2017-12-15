package com.ssh.nisus.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/*

 `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
  ----外键
  `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
  `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
  `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
  ----
  `cust_address` varchar(128) DEFAULT NULL COMMENT '客户联系地址',
  `cust_phone` varchar(64) DEFAULT NULL COMMENT '客户联系电话',


 */

/**
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-12-05-0:00
 */
@Entity
@Table(name = "cst_customer", schema = "crm_ssh", catalog = "")
public class Customer {
	private long cust_id;
	private String cust_name;
	private String cust_address;
	private String cust_phone;
	
	// 外键
	private BaseDict cust_source;
	private BaseDict cust_industry;
	private BaseDict cust_level;
	
	// 从表bean
	private Set<Linkman> linkmen = new HashSet<Linkman>();
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")
	public Set<Linkman> getLinkmen() {
		return linkmen;
	}
	
	public void setLinkmen(Set<Linkman> linkmen) {
		this.linkmen = linkmen;
	}
	
	@Id
	@Column(name = "cust_id", nullable = false)
	@GenericGenerator(name="genid", strategy = "native")
	@GeneratedValue(generator = "genid")
	public long getCust_id() {
		return cust_id;
	}
	
	public void setCust_id(long custId) {
		this.cust_id = custId;
	}
	
	@Basic
	@Column(name = "cust_name", nullable = false, length = 32)
	public String getCust_name() {
		return cust_name;
	}
	
	public void setCust_name(String custName) {
		this.cust_name = custName;
	}
	
	@Basic
	@Column(name = "cust_address", nullable = true, length = 128)
	public String getCust_address() {
		return cust_address;
	}
	
	public void setCust_address(String custAddress) {
		this.cust_address = custAddress;
	}
	
	@Basic
	@Column(name = "cust_phone", nullable = true, length = 64)
	public String getCust_phone() {
		return cust_phone;
	}
	
	public void setCust_phone(String custPhone) {
		this.cust_phone = custPhone;
	}
	
	@ManyToOne(targetEntity = BaseDict.class)
	@JoinColumn(name = "cust_source", referencedColumnName = "dict_id")
	public BaseDict getCust_source() {
		return cust_source;
	}
	
	public void setCust_source(BaseDict cust_source) {
		this.cust_source = cust_source;
	}
	
	@ManyToOne(targetEntity = BaseDict.class)
	@JoinColumn(name="cust_industry", referencedColumnName = "dict_id")
	public BaseDict getCust_industry() {
		return cust_industry;
	}
	
	public void setCust_industry(BaseDict cust_industry) {
		this.cust_industry = cust_industry;
	}
	
	@ManyToOne(targetEntity = BaseDict.class)
	@JoinColumn(name="cust_level", referencedColumnName = "dict_id")
	public BaseDict getCust_level() {
		return cust_level;
	}
	
	public void setCust_level(BaseDict cust_level) {
		this.cust_level = cust_level;
	}
	
	@Override
	public String toString() {
		return "Customer{" +
				"cust_id=" + cust_id +
				", cust_name='" + cust_name + '\'' +
				", cust_address='" + cust_address + '\'' +
				", cust_phone='" + cust_phone + '\'' +
				", cust_source=" + cust_source +
				", cust_industry=" + cust_industry +
				", cust_level=" + cust_level +
				'}';
	}
}
