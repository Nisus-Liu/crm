package com.ssh.nisus.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/*

 `dict_id` varchar(32) NOT NULL COMMENT '数据字典id(主键)',
  `dict_type_code` varchar(10) NOT NULL COMMENT '数据字典类别代码',
  `dict_type_name` varchar(64) NOT NULL COMMENT '数据字典类别名称',
  `dict_item_name` varchar(64) NOT NULL COMMENT '数据字典项目名称',
  `dict_item_code` varchar(10) DEFAULT NULL COMMENT '数据字典项目(可为空)',
  `dict_sort` int(10) DEFAULT NULL COMMENT '排序字段',
  `dict_enable` char(1) NOT NULL COMMENT '1:使用 0:停用',
  `dict_memo` varchar(64) DEFAULT NULL COMMENT '备注',

*/
/**
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-12-05-0:00
 */
@Entity
@Table(name = "base_dict", schema = "crm_ssh", catalog = "")
@GenericGenerator(name="myuuid", strategy="uuid")
public class BaseDict {
	private String dictId;
	private String dictTypeCode;
	private String dictTypeName;
	private String dictItemName;
	private String dictItemCode;
	private Integer dictSort;
	private String dictEnable;
	private String dictMemo;
	// 从表bean
	private Set<Customer> cust_source = new HashSet<Customer>();
	
	@Id
	@Column(name = "dict_id", nullable = false, length = 32)
	@GeneratedValue(generator = "myuuid")
	public String getDictId() {
		return dictId;
	}
	
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	
	@Basic
	@Column(name = "dict_type_code", nullable = false, length = 10)
	public String getDictTypeCode() {
		return dictTypeCode;
	}
	
	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode;
	}
	
	@Basic
	@Column(name = "dict_type_name", nullable = false, length = 64)
	public String getDictTypeName() {
		return dictTypeName;
	}
	
	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}
	
	@Basic
	@Column(name = "dict_item_name", nullable = false, length = 64)
	public String getDictItemName() {
		return dictItemName;
	}
	
	public void setDictItemName(String dictItemName) {
		this.dictItemName = dictItemName;
	}
	
	@Basic
	@Column(name = "dict_item_code", nullable = true, length = 10)
	public String getDictItemCode() {
		return dictItemCode;
	}
	
	public void setDictItemCode(String dictItemCode) {
		this.dictItemCode = dictItemCode;
	}
	
	@Basic
	@Column(name = "dict_sort", nullable = true)
	public Integer getDictSort() {
		return dictSort;
	}
	
	public void setDictSort(Integer dictSort) {
		this.dictSort = dictSort;
	}
	
	@Basic
	@Column(name = "dict_enable", nullable = false, length = 1)
	public String getDictEnable() {
		return dictEnable;
	}
	
	public void setDictEnable(String dictEnable) {
		this.dictEnable = dictEnable;
	}
	
	@Basic
	@Column(name = "dict_memo", nullable = true, length = 64)
	public String getDictMemo() {
		return dictMemo;
	}
	
	public void setDictMemo(String dictMemo) {
		this.dictMemo = dictMemo;
	}
	
	// ?对应同一从表的多个外键, 怎么配置?
	
	@Override
	public String toString() {
		return "BaseDict{" +
				"dictId='" + dictId + '\'' +
				", dictTypeCode='" + dictTypeCode + '\'' +
				", dictTypeName='" + dictTypeName + '\'' +
				", dictItemName='" + dictItemName + '\'' +
				", dictItemCode='" + dictItemCode + '\'' +
				'}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		BaseDict baseDict = (BaseDict) o;
		
		if (dictId != null ? !dictId.equals(baseDict.dictId) : baseDict.dictId != null) return false;
		if (dictTypeCode != null ? !dictTypeCode.equals(baseDict.dictTypeCode) : baseDict.dictTypeCode != null)
			return false;
		if (dictTypeName != null ? !dictTypeName.equals(baseDict.dictTypeName) : baseDict.dictTypeName != null)
			return false;
		if (dictItemName != null ? !dictItemName.equals(baseDict.dictItemName) : baseDict.dictItemName != null)
			return false;
		if (dictItemCode != null ? !dictItemCode.equals(baseDict.dictItemCode) : baseDict.dictItemCode != null)
			return false;
		if (dictSort != null ? !dictSort.equals(baseDict.dictSort) : baseDict.dictSort != null) return false;
		if (dictEnable != null ? !dictEnable.equals(baseDict.dictEnable) : baseDict.dictEnable != null) return false;
		if (dictMemo != null ? !dictMemo.equals(baseDict.dictMemo) : baseDict.dictMemo != null) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = dictId != null ? dictId.hashCode() : 0;
		result = 31 * result + (dictTypeCode != null ? dictTypeCode.hashCode() : 0);
		result = 31 * result + (dictTypeName != null ? dictTypeName.hashCode() : 0);
		result = 31 * result + (dictItemName != null ? dictItemName.hashCode() : 0);
		result = 31 * result + (dictItemCode != null ? dictItemCode.hashCode() : 0);
		result = 31 * result + (dictSort != null ? dictSort.hashCode() : 0);
		result = 31 * result + (dictEnable != null ? dictEnable.hashCode() : 0);
		result = 31 * result + (dictMemo != null ? dictMemo.hashCode() : 0);
		return result;
	}
}
