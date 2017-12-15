package com.ssh.nisus.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.nisus.domain.BaseDict;
import com.ssh.nisus.service.DictService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.List;

/**
 * 基础字典action
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-12-06-20:58
 */
@Controller("baseDictAction")
public class BaseDictAction extends ActionSupport {
	@Autowired
	private DictService dictService;
	private String dictTypeCode;
	
	@Override
	public String execute() throws IOException {
		DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);
		// 查询dict_type_code为?的所有分类
		dc.add(Restrictions.eq("dictTypeCode", dictTypeCode));
		List<BaseDict> types = dictService.getByType(dc);
		for (BaseDict type : types) {
			System.out.println(type);
		}
		String json = JSONArray.fromObject(types).toString();
		
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		
		return null;
		
	}
	
	
	public String getDictTypeCode() {
		return dictTypeCode;
	}
	
	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode;
	}
}
