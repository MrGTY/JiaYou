package cn.zhaojisys.controller.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.pojo.Paramatersetting;
import cn.zhaojisys.service.app.ParamService;
import cn.zhaojisys.tools.Constants;

@Controller
@RequestMapping("/app")
public class Param {

	@Autowired
	private ParamService paramService;
	@RequestMapping("/brand")
	@ResponseBody
	public Object brand(){//轮胎品牌
		JSONObject jsonObject=new JSONObject();
		try {
			Paramatersetting paramat=paramService.param();
			if (null!=paramat) {
				//轮胎品牌
				jsonObject.put("tyreBrand", Constants.splitString(paramat.getTyreBrand()));
				//轮胎规格
				jsonObject.put("spec", Constants.splitString(paramat.getTyprSpec()));
				//轮胎材料
				jsonObject.put("material", Constants.splitString(paramat.getTyprMaterial()));
				jsonObject.put("msg", "1");
			}else{
				jsonObject.put("msg", "-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");
			return jsonObject;
		}
		return jsonObject;
	}
	@RequestMapping("/spec")
	@ResponseBody
	public Object spec(){//轮胎规格
		JSONObject jsonObject=new JSONObject();
		try {
			Paramatersetting paramat=paramService.param();
			if (null!=paramat) {
				jsonObject.put("typrSpec", Constants.splitString(paramat.getTyprSpec()));
				jsonObject.put("msg", "1");
			}else {
				jsonObject.put("msg", "-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");
			return jsonObject;
		}
		return jsonObject;
	}
	@RequestMapping("/material")
	@ResponseBody
	public Object material(){//轮胎材料
		JSONObject jsonObject=new JSONObject();
		try {
			Paramatersetting paramat=paramService.param();
			if (null!=paramat) {
				jsonObject.put("typrMaterial", Constants.splitString(paramat.getTyprMaterial()));
				jsonObject.put("msg", "1");
			}else {
				jsonObject.put("msg", "-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");
			return jsonObject;
		}
		return jsonObject;
	}
}
