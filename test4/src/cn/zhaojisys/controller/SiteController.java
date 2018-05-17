package cn.zhaojisys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Feetable;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Paramatersetting;
import cn.zhaojisys.service.GasstationService;
import cn.zhaojisys.service.SiteService;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.PageSupport;

@Controller
@RequestMapping("/site")
public class SiteController {
	private Logger logger=Logger.getLogger(SiteController.class);
	@Resource
	private GasstationService gasstationService;
	@Resource
	private SiteService siteService;
	//我的信息
	@RequestMapping(value="/dearselect",method=RequestMethod.GET)
	public String selectGasstation(Model  model,HttpSession session){
		Gasstation gasstation=null;
		Integer id=((Gasstation)session.getAttribute(Constants.DEV_USER_SESSION)).getId();
		try {
			gasstation=gasstationService.getGasstationById(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("gass",gasstation);
		return "sitedealer";
	}
	//审批表多条件查询页
	@RequestMapping(value="/showdealer")
	public String getExtractApplyByCon(Model model,HttpSession session,
			@RequestParam(value="begintime",required=false) String _begintime,
			@RequestParam(value="endtime",required=false) String _endtime,
			@RequestParam(value="status",required=false) String _status,
			@RequestParam(value="pageIndex",required=false) String pageIndex){
		//取session站点的id
		Integer id=((Gasstation)session.getAttribute(Constants.DEV_USER_SESSION)).getId();
		Gasstation gasstation=null;
		List<ExtractApply> list=null;
		//查吨换L的参数
		Paramatersetting ptt=null;
		try {
			ptt=gasstationService.getParamatersettingList();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//页面容量
		int pageSize = Constants.pageSize;
		//当前页码
		Integer currentPageNo = 1;
		if(pageIndex != null){
			currentPageNo = Integer.valueOf(pageIndex);
		}
		Integer status=null;
		if(_status!=null&&_status!="")
			status=Integer.parseInt(_status);
		int totalCount = 0;
		try {
			totalCount=siteService.getExtractApplyCount(status,Constants.getStringDate(_begintime),Constants.getStringDate(_endtime),id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		//控制首页和尾页
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		try {
			list=siteService.getExtractApplyByCondition(status,Constants.getStringDate(_begintime),Constants.getStringDate(_endtime),id,currentPageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//查该站点
		try {
			gasstation=gasstationService.getGasstationById(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("gass",gasstation);
		model.addAttribute("list", list);
		model.addAttribute("pages", pages);
		model.addAttribute("status", status);
		model.addAttribute("begintime",_begintime);
		model.addAttribute("endtime",_endtime);
		model.addAttribute("ptt", ptt.getUnitConversion());
		return "mechange";
	}
	//燃油申请提交页面
	@RequestMapping(value="/dealercommit",method=RequestMethod.GET)
	public String commit(@RequestParam(value="error",required= false)String
			error,Model model,HttpSession session){
		//取session站点的id
		Integer id=((Gasstation)session.getAttribute(Constants.DEV_USER_SESSION)).getId();
		Gasstation gass=null;
		Paramatersetting ptt=null;
		if(null != error && error.equals("error")){
			error = "账户禁用中";
		}
		if(null != error && error.equals("error2")){
			error = "上次审批还未通过";
		}
		if(null != error && error.equals("error3")){
			error = "保存失败";
		}
		try {
			gass=gasstationService.getGasstationById(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			}
		try {
			ptt=gasstationService.getParamatersettingList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", error);
		model.addAttribute("ton", Constants.getTon(gass.getQuota(), ptt.getUnitConversion()));
		model.addAttribute("leave", Constants.getLeave(gass.getQuota(), ptt.getUnitConversion()));
		model.addAttribute("all", Constants.getAll(gass.getQuota(), ptt.getUnitConversion()));
		model.addAttribute("ptt", ptt);
		model.addAttribute("gass",gass);
		return "dealercommit";
	}
	//燃油申请提交到数据库
	@RequestMapping(value="/dealercommit",method=RequestMethod.POST)
	public String commitsave(
			@RequestParam(value="ton",required=false) String ton,
			@RequestParam(value="leave",required=false) String leave,HttpSession session){
		//取session站点的id
		Integer siteid=((Gasstation)session.getAttribute(Constants.DEV_USER_SESSION)).getId();
		Gasstation gass=null;
		ExtractApply extractApply=new ExtractApply();
		try {
			gass=gasstationService.getGasstationById(siteid);
			if(!gasstationService.getStatusById(siteid)){
				return "redirect:/site/dealercommit?error=error";
			}
			if(gasstationService.getExtractApplyStatusById(siteid)){
				return "redirect:/site/dealercommit?error=error2";
			}
			extractApply.setGsId(siteid);
			extractApply.setBusinessType(0);
			extractApply.setSurplusBalance(Integer.parseInt(leave));
			extractApply.setAmountDrawn(Integer.parseInt(ton));
			extractApply.setApplicationTime(new Date());
			extractApply.setStatus(0);
			extractApply.setNote(1);
			extractApply.setTelePhone(gass.getMobilePhone());
			if(!gasstationService.addExtractApply(extractApply)){
				return "redirect:/site/dealercommit?error=error3";
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "redirect:/site/showdealer";
	}
	//动态判断提取值
	@RequestMapping(value="/dealercommit.json")
	@ResponseBody
	public Object commitPrice(@RequestParam String price,@RequestParam String ton){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(price)||StringUtils.isNullOrEmpty(price)){
			resultMap.put("price", "empty");
		}else{
			try {
				if(Integer.parseInt(price)<=0){		
					resultMap.put("price", "shao");
				}else if(Integer.parseInt(price)>Integer.parseInt(ton)){
					resultMap.put("price", "chao");
				}else{
					resultMap.put("price", "ok");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
	//站点收支油表多条件查询页
	@RequestMapping(value="/showyou")
	public String getOilrecordByCon(Model model,HttpSession session,
			@RequestParam(value="begintime",required=false) String _begintime,
			@RequestParam(value="endtime",required=false) String _endtime,
			@RequestParam(value="status",required=false) String _status,
			@RequestParam(value="pageIndex",required=false) String pageIndex){
		//取session站点的id
		Integer id=((Gasstation)session.getAttribute(Constants.DEV_USER_SESSION)).getId();
		Gasstation gasstation=null;
		List<Oilrecord> list=null;
		//页面容量
		int pageSize = Constants.pageSize;
		//当前页码
		Integer currentPageNo = 1;
		if(pageIndex != null){
			currentPageNo = Integer.valueOf(pageIndex);
		}
		Integer status=null;
		if(_status!=null&&_status!="")
			status=Integer.parseInt(_status);
		int totalCount = 0;
		try {
			totalCount=siteService.getOilrecordByCount(id, status,Constants.getStringDate(_begintime),Constants.getStringDate(_endtime));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		//控制首页和尾页
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		try {
			list=siteService.getOilrecordByCondition(id, status,Constants.getStringDate(_begintime),Constants.getStringDate(_endtime), currentPageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//查该站点
		try {
			gasstation=gasstationService.getGasstationById(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer zhi=null;
		try {
			 zhi=siteService.getOilAll(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("zhi", zhi);
		model.addAttribute("gass",gasstation);
		model.addAttribute("list", list);
		model.addAttribute("pages", pages);
		model.addAttribute("status", status);
		model.addAttribute("begintime",_begintime);
		model.addAttribute("endtime",_endtime);
		return "siteNote";
	}
	//查看已结算的服务费
		@RequestMapping(value="/fuwu")
		public String getFuWu(Model model,HttpSession session,
				@RequestParam(value="begintime",required=false) String _begintime,
				@RequestParam(value="endtime",required=false) String _endtime,
				@RequestParam(value="pageIndex",required=false) String pageIndex){
			//取session站点的id
			Integer id=((Gasstation)session.getAttribute(Constants.DEV_USER_SESSION)).getId();
			Gasstation gasstation=null;
			List<Feetable> list=null;
			//页面容量
			int pageSize = Constants.pageSize;
			//当前页码
			Integer currentPageNo = 1;
			if(pageIndex != null){
				currentPageNo = Integer.valueOf(pageIndex);
			}
			int totalCount = 0;
			try {
				totalCount=siteService.getFeetableCount(id, Constants.getStringDate(_begintime), Constants.getStringDate(_endtime));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//总页数
			PageSupport pages = new PageSupport();
			pages.setCurrentPageNo(currentPageNo);
			pages.setPageSize(pageSize);
			pages.setTotalCount(totalCount);
			int totalPageCount = pages.getTotalPageCount();
			//控制首页和尾页
			if(currentPageNo < 1){
				currentPageNo = 1;
			}else if(currentPageNo > totalPageCount){
				currentPageNo = totalPageCount;
			}
			try {
				list=siteService.getFeetable(id,Constants.getStringDate(_begintime), Constants.getStringDate(_endtime), currentPageNo, pageSize);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//查该站点
			try {
				gasstation=gasstationService.getGasstationById(id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//查未结算的服务费
			Double money=null;
			try {
				money=siteService.getFeeAll(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("money",money);
			model.addAttribute("gass",gasstation);
			model.addAttribute("list", list);
			model.addAttribute("pages", pages);
			model.addAttribute("begintime",_begintime);
			model.addAttribute("endtime",_endtime);
			return "fuwu";
		}
		//相关站点燃油服务费收支明细
		@RequestMapping(value="/fuwuranyou")
		public String fuwuRanyou(Model model,HttpSession session,
				@RequestParam(value="begintime",required=false) String _begintime,
				@RequestParam(value="endtime",required=false) String _endtime,
				@RequestParam(value="idc",required=false) String idc,
				@RequestParam(value="pageIndex",required=false) String pageIndex){
			//取session站点的id
			Integer id=((Gasstation)session.getAttribute(Constants.DEV_USER_SESSION)).getId();
			System.out.println("------------------------------------"+idc);
			Gasstation gasstation=null;
			List<Oilrecord> list=null;
			//页面容量
			int pageSize = Constants.pageSize;
			//当前页码
			Integer currentPageNo = 1;
			if(pageIndex != null){
				currentPageNo = Integer.valueOf(pageIndex);
			}
			int totalCount = 0;
			Map<String,Object> map1=new HashMap<String,Object>();
			map1.put("gsId",id);
			map1.put("idc",Constants.splitString(idc));
			map1.put("begintime",Constants.getStringDate(_begintime));
			map1.put("endtime",Constants.getStringDate(_endtime));
			try {
				totalCount=siteService.getOilrecordCountByFee(map1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//总页数
			PageSupport pages = new PageSupport();
			pages.setCurrentPageNo(currentPageNo);
			pages.setPageSize(pageSize);
			pages.setTotalCount(totalCount);
			int totalPageCount = pages.getTotalPageCount();
			//控制首页和尾页
			if(currentPageNo < 1){
				currentPageNo = 1;
			}else if(currentPageNo > totalPageCount){
				currentPageNo = totalPageCount;
			}
			map1.put("from",(currentPageNo-1)*pageSize);
			map1.put("pageSize", pageSize);
			try {
				list=siteService.getOilrecordByFee(map1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//查该站点
			try {
				gasstation=gasstationService.getGasstationById(id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("gass",gasstation);
			model.addAttribute("list", list);
			model.addAttribute("pages", pages);
			model.addAttribute("idc", idc);
			model.addAttribute("begintime",_begintime);
			model.addAttribute("endtime",_endtime);
			return "fuwuranyou";
		}
}
