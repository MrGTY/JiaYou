package cn.zhaojisys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Test {

	@RequestMapping("/htmltest")
	public ModelAndView test(){
		ModelAndView mv=new ModelAndView();
				mv.setViewName("test");
		return mv;
	}
}
