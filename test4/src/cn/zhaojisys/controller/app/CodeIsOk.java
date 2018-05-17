package cn.zhaojisys.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.service.app.CodeIsOkService;

@Controller
@RequestMapping("/app")
public class CodeIsOk {

	@Autowired
	private CodeIsOkService codeIsOkService;
	
	@RequestMapping("/codeIsOk")
	@ResponseBody
	public Object codeIsOk(@RequestParam("phoneNum")String phoneNum){
		JSONObject jsonObject=new JSONObject();
		
		try {
			int count=codeIsOkService.CodeIsOk(phoneNum);
			if (count>0) {//账户没有被禁用
				jsonObject.put("msg", 1);
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
