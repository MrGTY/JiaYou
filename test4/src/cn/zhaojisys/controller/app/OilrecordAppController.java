package cn.zhaojisys.controller.app;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.OilModel;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.service.app.OilrecordAppService;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.PageSupport;

@Controller
@RequestMapping("/app")
public class OilrecordAppController {

	@Autowired
	private OilrecordAppService appService;

	@RequestMapping(value = "/record", method = RequestMethod.GET)
	@ResponseBody
	//根据当前登录燃油站点用户id查询加油记
	public Object record(HttpSession session, @RequestParam(value = "pageIndex", required = false) String pageIndex) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageIndex);
		PageSupport pages = new PageSupport();
		pages.setPageSize(2);
		List<OilModel> list = null;
		JSONObject jsonObject=new JSONObject();
		Gasstation gasstation = (Gasstation)session.getAttribute(Constants.GASSTION);
		try {
			int totalCount = appService.countOilrecordBy_gsId(1);
			if (isNum.matches()) {
				pages.setCurrentPageNo((Integer.parseInt(pageIndex) - 1) * 1);
				pages.setTotalCount(totalCount);
				//测试数据id为2 
				list = appService.listOilrecordBy_gsId(gasstation.getId(), (Integer.parseInt(pageIndex) - 1) * pages.getPageSize(),
						pages.getPageSize());
				if (list.size()!=0) {
					jsonObject.put("list", list);
					jsonObject.put("msg", "1");
				}else {
					jsonObject.put("msg", "-1");
				}
			} else {
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
