package cn.zhaojisys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.zhaojisys.service.HomeService;

@Controller
public class HomeController {

	@Autowired
	private HomeService homeService;
	
	
	
}
