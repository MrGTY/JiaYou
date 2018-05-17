package cn.zhaojisys.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.pojo.Customer;
import cn.zhaojisys.service.app.CustomerService;

@Controller
@RequestMapping("/app")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	
	@RequestMapping("addCtm")
	@ResponseBody
	public Object addCtm(Customer customer){
		JSONObject jsonObject=new JSONObject();
		try {
			int count=customerService.addCtm(customer);
			if (count>0) {
				jsonObject.put("msg", 200);
			}else {
				jsonObject.put("msg", -1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", 400);
		}
		return jsonObject;
	}
}
