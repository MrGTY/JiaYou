package cn.zhaojisys.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Xieyi {

	// 协议
	@RequestMapping("/xieyi")
	public String xieyi() {
		return "app/personal/xieyi";
	}

	/*// 登陆页(密码)
	@RequestMapping("/login")
	public String denglu() {
		return "app/personal/logoname";
	}*/
}
