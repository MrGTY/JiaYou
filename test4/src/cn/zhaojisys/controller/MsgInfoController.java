package cn.zhaojisys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backend")
public class MsgInfoController {

	//跳转
	@RequestMapping("msgInfo")
	public String msgInfo(){
		return "msgInfo";
	}
}
