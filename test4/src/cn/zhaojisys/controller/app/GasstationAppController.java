package cn.zhaojisys.controller.app;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Shop;
import cn.zhaojisys.service.app.GasstationAppService;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.PageSupport;

@Controller
@RequestMapping("/app")
public class GasstationAppController {

	@Autowired
	private GasstationAppService appService;
	
	@RequestMapping("/shopList")
	@ResponseBody
	//轮胎列表页面给sessionId
	public Object shopList(@RequestParam(value="uid",required=true)Integer uid,
							@RequestParam(value = "pageIndex", required = true) String pageIndex,HttpServletRequest request){//轮胎列表
		PageSupport pages = new PageSupport();
		pages.setPageSize(4);
		List<Shop> list=null;
		JSONObject jsonObject=new JSONObject();
		try {
			list=appService.shopList(uid,(Integer.parseInt(pageIndex) - 1) * pages.getPageSize(),pages.getPageSize());
			if (list.size()!=0) {
				StringBuffer url = request.getRequestURL(); 
				String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString(); 
				String logoPicPath = request.getContextPath() + "/statics/appimg/";
				jsonObject.put("list", list);
				jsonObject.put("msg", "1");
				jsonObject.put("imgurl", tempContextUrl+logoPicPath);
			}else{
				jsonObject.put("msg", "-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");
			return jsonObject;
		}
		return JSONObject.toJSON(jsonObject);
	}
}
