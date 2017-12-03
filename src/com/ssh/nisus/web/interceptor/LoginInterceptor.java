package com.ssh.nisus.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ssh.nisus.constant.Constant;
import com.ssh.nisus.utils.Log;

/**
 * 操作权限拦截: 判断是否登录, 登录后才能进行某些操作!
 *
 * @version:
 * @author: Nisus-Liu
 * @email: liuhejunlj@163.com
 * @date: 2017-11-25-0:34
 */
public class LoginInterceptor extends MethodFilterInterceptor {
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		String result = null;
		// 前处理
		// 判断是否在登录状态
		Object crt_user = ActionContext.getContext().getSession().get(Constant.CURRENT_USER);
		if (crt_user == null) {
			result = "goLoginUI";
		} else {
			// 放行
			result = invocation.invoke();
			Log.trace(result);
		}
		
		
		// 后处理
		
		
		
		return result;
	}
}
