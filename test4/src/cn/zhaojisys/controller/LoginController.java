package cn.zhaojisys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zhaojisys.pojo.EmployeInfo;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.service.SumService;
import cn.zhaojisys.service.UserService;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.UserAgentGetter;

@Controller
public class LoginController {
	@Autowired
	UserService uService;
	@Autowired
	SumService sumService;

	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String loginzd() {
		return "login";
	}

	// 站点登陆
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(Gasstation gasstation, HttpSession session,
			HttpServletRequest request) {
		try {
			Gasstation gasstation1 = uService.zdLogin(gasstation);
			if (null != gasstation1) {
				session.setAttribute(Constants.DEV_USER_SESSION, gasstation1);
				return "redirect:/backend/selectOil";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("message", "用户名或者密码不正确！");
		return "login";
	}

	// 站点登录成功跳转到首页
	@RequestMapping(value = "zd")
	public String zd(HttpSession session) {
		if (session.getAttribute(Constants.USER_SESSION) == null) {
			return "loginzd";
		}
		return "zd";
	}
	@RequestMapping(value="loginu",method=RequestMethod.GET)
	public String loginuser() {
		return "loginuser";
	}

	// 管理平台登陆
	@RequestMapping(value="/loginu",method=RequestMethod.POST)
	public String loginu(EmployeInfo employeInfo, HttpSession session,
			HttpServletRequest request) {
		try {
			EmployeInfo emp = uService.userLogin(employeInfo);
			if (null != emp) {
				session.setAttribute(Constants.USER_SESSION, emp);
				return "redirect:/backend/sumOil";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("message", "用户名或者密码不正确！");
		return "loginuser";
	}

	// 管理平台登录成功跳转到首页
	@RequestMapping(value = "user")
	public String user(HttpSession session) {
		if (session.getAttribute(Constants.USER_SESSION) == null) {
			return "loginuser";
		}
		return "user";
	}
	
	
}
