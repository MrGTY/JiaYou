package cn.zhaojisys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONArray;
import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Paramatersetting;
import cn.zhaojisys.service.ApplyService;
import cn.zhaojisys.service.GasstationService;
import cn.zhaojisys.tools.Constants;

@Controller
public class ApplyController {
	@Autowired
	ApplyService aService;
	
	@Resource
	private GasstationService gasstationService;
	Logger logger=Logger.getLogger(ApplyController.class);
	// 查询所有用户提取申请的状态
	@RequestMapping(value = "selectAllApply")
	public ModelAndView selectAllApply(
			HttpSession httpSession,
			@RequestParam(value="pageNo",required=false) Integer pageNo,
			@RequestParam(value="siteName",required=false) String siteName,
			@RequestParam(value="businessType",required=false) Integer businessType,
			@RequestParam(value ="status",required=false) Integer status,
			Model model) {
		Paramatersetting ptt=null;
		try {
			ptt=gasstationService.getParamatersettingList();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ModelAndView mav=new ModelAndView();
		try {
			mav.setViewName("apply");
			int newPageNo=1;
			int totalPageNo;
			int totalPage=aService.count(siteName,businessType,status);
			if (pageNo!=null&&pageNo!=0) {
				newPageNo=pageNo;
			}
			int pageSize=Constants.pageSize;
			List<ExtractApply> selectAllApply=aService.selectAllApply((newPageNo-1)*pageSize,pageSize,siteName,businessType,status);
			totalPageNo=totalPage%pageSize==0?totalPage/pageSize:totalPage/pageSize+1;
			logger.info("-------------------------"+totalPageNo);
			model.addAttribute("totalPage",totalPage);
			model.addAttribute("selectAllApply",selectAllApply);
			model.addAttribute("pageNo", newPageNo);
			model.addAttribute("businessType", businessType);
			model.addAttribute("siteName", siteName);
			model.addAttribute("status", status);
			model.addAttribute("totalPageNo", totalPageNo);
			model.addAttribute("ptt", ptt.getUnitConversion());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	// 通过Id查询到时哪一位用户申请提取
	@RequestMapping(value = "selectApplyById")
	@ResponseBody
	public Object selectApplyById(@RequestParam(value = "id", required = false)String id){
		List<ExtractApply> list=null;
		try {
			list= aService.selectAllApplyById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 通过或拒绝申请
	@RequestMapping(value = "updateApply")
	@ResponseBody
	public Object updateApply(
			@Param("serialNum") String serialNum,
			@Param("type") String type,
			@Param("id") Integer id,
			HttpSession session
			) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			return aService.getResult(serialNum, type, id, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("mas","failure");
		return JSONArray.toJSONString(map);
	}
}
