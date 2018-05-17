package cn.zhaojisys.controller.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;

import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.push.PushMessageNew;
import cn.zhaojisys.service.app.AddOilService;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.UserAgentGetter;

@Controller
@RequestMapping("/app")
public class AddOilController {

	@Autowired
	private AddOilService addOilService;

	@RequestMapping("addoil")
	@ResponseBody
	// 加油接口
	public Object addoil(HttpSession session,HttpServletRequest request, @RequestParam("gid") Integer gid,
			@RequestParam("preAddOil") Integer preAddOil, @RequestParam("fee") Double fee) {
		JSONObject jsonObject = new JSONObject();
		Vipuserinfo vipuserinfo = (Vipuserinfo) session.getAttribute(Constants.VIPUSERINFO);
		try {
			JSONObject json = null;
			if (vipuserinfo.getStateTag() == 1) {// 个人禁用
				jsonObject.put("msg", "-20");
				return jsonObject;
			}
			if (vipuserinfo != null) {
				json = addOilService.vipAddoil(vipuserinfo.getId(), gid, preAddOil, vipuserinfo.getPhoneNum(), fee);
				if (json.getInteger("msg") > 0) {
					return json;
				} else if (json.getInteger("msg") == -10) {
					jsonObject.put("msg", "-10");
					return jsonObject;
				} else if (json.getInteger("msg") == -100) {
					jsonObject.put("msg", "-100");
					return jsonObject;
				}else if (json.getInteger("msg") == -200) {
					jsonObject.put("msg", "-200");
					return jsonObject;
				} else {
					jsonObject.put("msg", "-1");
					return jsonObject;
				}
			} else {
				jsonObject.put("msg", "400");
				return jsonObject;
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");
			return jsonObject;
		}
	}
}
