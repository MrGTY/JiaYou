package cn.zhaojisys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.zhaojisys.pojo.EmployeInfo;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.tools.Constants;

public class SysInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		EmployeInfo employeInfo = (EmployeInfo) session.getAttribute(Constants.USER_SESSION);
		Gasstation gasstation = (Gasstation) session.getAttribute(Constants.DEV_USER_SESSION);

		if (null != employeInfo) { // dev SUCCESS
			return true;
		} else if (null != gasstation) { // backend SUCCESS
			return true;
		} else {
			String path = request.getContextPath();  
		     String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
			response.sendRedirect(basePath);
			return false;
		}

	}
}
