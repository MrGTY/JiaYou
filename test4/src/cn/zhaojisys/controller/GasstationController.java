package cn.zhaojisys.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.PageSupport;
import cn.zhaojisys.pojo.EmployeInfo;
import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Paramatersetting;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.service.GasstationService;
import cn.zhaojisys.service.SiteService;

@Controller
@RequestMapping("/gasstation")
public class GasstationController {
	private Logger logger=Logger.getLogger(GasstationController.class);
	@Resource
	private GasstationService gasstationService;
	@Resource
	private SiteService siteService;
	//燃油多条件查询页
	@RequestMapping(value="/showlist")
	public String getGasstationListByCon(Model model,HttpSession session,
			@RequestParam(value="siteName",required=false) String siteName,
			@RequestParam(value="contact",required=false) String contact,
			@RequestParam(value="status",required=false) String _status,
			@RequestParam(value="pageIndex",required=false) String pageIndex,
			@RequestParam(value="gsType",required=false) String _gsType
			){
		List<Gasstation> list=null;
		//页面容量
		int pageSize = Constants.pageSize;
		//当前页码
		Integer currentPageNo = 1;
		if(pageIndex != null){
			try{
				currentPageNo = Integer.valueOf(pageIndex);
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		//定义为加油站
		Integer gsType=0;
		if(_gsType!=null&&_gsType!="")
			gsType=Integer.parseInt(_gsType);
		Integer status=null;
		if(_status!=null&&_status!="")
			status=Integer.parseInt(_status);
		int totalCount = 0;
		try {
			totalCount=gasstationService.getGasstationCount(siteName, contact, gsType, status);
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
			list=gasstationService.getGasstationListByCondition(siteName, contact, gsType, status, currentPageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		model.addAttribute("pages", pages);
		model.addAttribute("siteName", siteName);
		model.addAttribute("contact", contact);
		model.addAttribute("status", status);
		return "dealer";
	}
	//动态逻辑删除燃油站点
	@RequestMapping(value="/delgass.json")
	@ResponseBody
	public Object delGass(@RequestParam String id){
		logger.debug("del===================== "+id);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(id)){
			resultMap.put("delResult", "notexist");
		}else{
			try {
				if(gasstationService.updateByLogicId(Integer.parseInt(id)))
					resultMap.put("delResult", "true");
				else
					resultMap.put("delResult", "false");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
	//修改燃油账户状态
	@RequestMapping(value="/updgass.json")
	@ResponseBody
	public Object updGass(@RequestParam String id,@RequestParam String status){
		logger.debug("upd=== "+id+"==="+status);
		int stu=0;
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(id)||StringUtils.isNullOrEmpty(status)){
			resultMap.put("delResult", "notexist");
		}else{
			try {
				if(Integer.parseInt(status)==0){
					//当状态为0禁用时 改为启动1
					stu=1;
					if(gasstationService.updateStatusById(Integer.parseInt(id),stu)){
						resultMap.put("delResult", "open");
					}else
						resultMap.put("delResult", "false");
				}else if(Integer.parseInt(status)==1){
					stu=0;
					if(gasstationService.updateStatusById(Integer.parseInt(id),stu)){
						resultMap.put("delResult", "close");
					}else
						resultMap.put("delResult", "false");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
	//燃油申请提交页面
	@RequestMapping(value="/commit",method=RequestMethod.GET)
	public String commit(String id,@RequestParam(value="error",required= false)String
			error,Model model){
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
		if(null != error && error.equals("error4")){
			error = "提取的值必须大于0";
		}
		try {
			gass=gasstationService.getGasstationById(Integer.parseInt(id));
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
		return "dealerapply";
	}
	//燃油申请提交到数据库
	@RequestMapping(value="/commitsave",method=RequestMethod.POST)
	public String commitsave(@RequestParam(value="id",required=false) String id,
			@RequestParam(value="ton",required=false) Integer ton,
			@RequestParam(value="leave",required=false) Integer leave,HttpSession session){
		//取当前操作员工
		EmployeInfo emp=((EmployeInfo)session.getAttribute(Constants.USER_SESSION));
		Gasstation gass=null;
		ExtractApply extractApply=new ExtractApply();
		try {
			gass=gasstationService.getGasstationById(Integer.parseInt(id));
			if(ton<=0){
				return "redirect:/gasstation/tirecommit?id="+gass.getId()+"&error=error4";
			}
			if(!gasstationService.getStatusById(Integer.parseInt(id))){
				return "redirect:/gasstation/commit?id="+gass.getId()+"&error=error";
			}
			if(gasstationService.getExtractApplyStatusById(Integer.parseInt(id))){
				return "redirect:/gasstation/commit?id="+gass.getId()+"&error=error2";
			}
			extractApply.setGsId(gass.getId());
			extractApply.setBusinessType(0);
			extractApply.setSurplusBalance(leave);
			extractApply.setAmountDrawn(ton);
			extractApply.setApplicationTime(new Date());
			extractApply.setStatus(0);
			extractApply.setNote(3);
			extractApply.setApplyUserId(emp.getId());
			extractApply.setTelePhone(emp.getMobilePhone());
			if(!gasstationService.addExtractApply(extractApply)){
				return "redirect:/gasstation/commit?id="+gass.getId()+"&error=error3";
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "redirect:/gasstation/showlist";
	}
	//动态判断提取值
	@RequestMapping(value="/commit.json")
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
	@RequestMapping(value="/gasstationadd",method=RequestMethod.GET)
	public String addGasstation(@ModelAttribute("gasstation") Gasstation gasstation,
			HttpServletRequest request,Model model){
		return "dealeradd";
	}
	@RequestMapping(value="/gasstationadd",method=RequestMethod.POST)
	public String addGasstationSave(Gasstation gasstation,Model model,HttpServletRequest request,
			@RequestParam(value="coverRadius") Integer coverRadius,
			@RequestParam(value="status") String status,
			@RequestParam(value="initialOil") Integer initialOil){
		String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"qrcode");
		//String delpath=null;
		//String path=request.getContextPath()+"/statics/qrcode/";
		String locpath=null; 
		gasstation.setCreatData(new Date());
		gasstation.setLogicId(1);
		gasstation.setGsType(0);
		gasstation.setQuota(initialOil);
		gasstation.setCoverRadius(coverRadius);
		gasstation.setStatus(Integer.parseInt(status));
		gasstation.setInitialOil(initialOil);
		try {
			if(gasstationService.addGasstation(gasstation)){
				//通过当前站点数据再次查找自己来查到id
				gasstation=gasstationService.getGasstationByMoney(gasstation.getCreatData(), gasstation.getSiteName());
				//添加成功才生成二维码
				String build=Constants.getStringBuffer$(String.valueOf(gasstation.getId()),String.valueOf(gasstation.getGsType()));
				locpath=Constants.getEncode(path,build);
				gasstationService.updateGasstationQRcode(gasstation.getId(), locpath);
				//test是否能删文件
				//delpath = path+File.separator+locpath;
				//logger.info("kan======"+path);
				//if(Constants.deleteQrcode(delpath)){
				//	logger.info("=======================删除成功");
				//}
				return "redirect:/gasstation/showlist";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("gass",gasstation);
		request.setAttribute("error", "保存失败");
		return "dealeradd";
	}
	@RequestMapping(value="/gasstationmodify",method=RequestMethod.GET)
	public String modifyGasstation(@RequestParam(value="id") String id,Model  model){
		Gasstation gasstation=null;
		try {
			gasstation=gasstationService.getGasstationById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("gass",gasstation);
		return "dealermodify";
	}
	@RequestMapping(value="/gasstationmodifysave",method=RequestMethod.POST)
	public String modifyGasstationSave(Gasstation gasstation,Model model,HttpServletRequest request){
		gasstation.setCreatData(new Date());
		try {
			if(gasstationService.modifyGasstation(gasstation)){
				return "redirect:/gasstation/showlist";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("gass",gasstation);
		request.setAttribute("error", "修改失败");
		return "dealermodify";
	}
	@RequestMapping(value="/deleteCode",method=RequestMethod.GET)
	public String deleteQrcode(@RequestParam(value="filename") String fileName){
		Constants.deleteQrcode(fileName);
		return "redirect:/gasstation/showlist";
	}
	@RequestMapping(value="/gasstationselectid",method=RequestMethod.GET)
	public String selectGasstation(@RequestParam(value="id") String id,Model  model){
		Gasstation gasstation=null;
		try {
			gasstation=gasstationService.getGasstationById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("gass",gasstation);
		return "dealerselect";
	}
	@RequestMapping(value="/uname.json",method=RequestMethod.GET)
	@ResponseBody
	public Object apkNameIsExit(@RequestParam String uname){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(uname)){
			resultMap.put("code", "empty");
		}else{
				try {
					if(gasstationService.getGasstationCode(uname)){
						resultMap.put("code", "exist");
					}
					else
						resultMap.put("code", "noexist");
				} catch (Exception e) {
					e.printStackTrace();
				}
		}	
		return JSONArray.toJSONString(resultMap);
	}
	@RequestMapping(value="/showtire")
	public String getGasstationListByTire(Model model,HttpSession session,
			@RequestParam(value="siteName",required=false) String siteName,
			@RequestParam(value="contact",required=false) String contact,
			@RequestParam(value="status",required=false) String _status,
			@RequestParam(value="pageIndex",required=false) String pageIndex,
			@RequestParam(value="gsType",required=false) String _gsType
			){
		List<Gasstation> list=null;
		//页面容量
		int pageSize = Constants.pageSize;
		//当前页码
		Integer currentPageNo = 1;
		if(pageIndex != null){
			try{
				currentPageNo = Integer.valueOf(pageIndex);
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		//定义为轮胎站点
		Integer gsType=1;
		if(_gsType!=null&&_gsType!="")
			gsType=Integer.parseInt(_gsType);
		Integer status=null;
		if(_status!=null&&_status!="")
			status=Integer.parseInt(_status);
		int totalCount = 0;
		try {
			totalCount=gasstationService.getGasstationCount(siteName, contact, gsType, status);
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
			list=gasstationService.getGasstationListByCondition(siteName, contact, gsType, status, currentPageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		model.addAttribute("pages", pages);
		model.addAttribute("siteName", siteName);
		model.addAttribute("contact", contact);
		model.addAttribute("status", status);
		return "tire";
	}
	@RequestMapping(value="/tireadd",method=RequestMethod.GET)
	public String addTire(@ModelAttribute("gasstation") Gasstation gasstation,
			HttpServletRequest request,Model model){
		Paramatersetting ptt=null;
		try {
			ptt=gasstationService.getParamatersettingList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] typrSpecs=Constants.splitString(ptt.getTyprSpec());
		String[] typrMaterials=Constants.splitString(ptt.getTyprMaterial());
		String[] tyreBrands=Constants.splitString(ptt.getTyreBrand());
		model.addAttribute("typrSpecs", typrSpecs);
		model.addAttribute("typrMaterials", typrMaterials);
		model.addAttribute("tyreBrands", tyreBrands);
		return "tireadd";
	}
	@RequestMapping(value="/tireadd",method=RequestMethod.POST)
	public String addTireSave(Gasstation gasstation,HttpServletRequest request,
			@RequestParam(value="optionsRadios1",required=false) String[] tyreBrand,
			@RequestParam(value="optionsRadios2",required=false) String[] typrSpec,
			@RequestParam(value="optionsRadios3",required=false) String[] typrMaterial,Model model){
		Paramatersetting ptt=null;
		String locpath=null; 
		String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"qrcode");
		gasstation.setCreatData(new Date());
		gasstation.setLogicId(1);
		gasstation.setGsType(1);
		gasstation.setQuota(gasstation.getInitialOil());
		if(tyreBrand!=null&&tyreBrand.length>0){
			gasstation.setTyreBrand(Constants.getStringarray(tyreBrand));
		}
		if(typrSpec!=null&&typrSpec.length>0){
			gasstation.setTyprSpec(Constants.getStringarray(typrSpec));
		}
		if(typrMaterial!=null&&typrMaterial.length>0){
			gasstation.setTyprMaterial(Constants.getStringarray(typrMaterial));
		}
		try {
			if(gasstationService.addGasstation(gasstation)){
				gasstation=gasstationService.getGasstationByMoney(gasstation.getCreatData(), gasstation.getSiteName());
				//添加成功才生成二维码
				String build=Constants.getStringBuffer$(String.valueOf(gasstation.getId()),String.valueOf(gasstation.getGsType()));
				locpath=Constants.getEncode(path,build);
				gasstationService.updateGasstationQRcode(gasstation.getId(), locpath);
				return "redirect:/gasstation/showtire";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ptt=gasstationService.getParamatersettingList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] typrSpecs=Constants.splitString(ptt.getTyprSpec());
		String[] typrMaterials=Constants.splitString(ptt.getTyprMaterial());
		String[] tyreBrands=Constants.splitString(ptt.getTyreBrand());
		model.addAttribute("typrSpecs", typrSpecs);
		model.addAttribute("typrMaterials", typrMaterials);
		model.addAttribute("tyreBrands", tyreBrands);
		model.addAttribute("gass",gasstation);
		request.setAttribute("error", "保存失败");
		return "tireadd";
	}
	@RequestMapping(value="/tiremodify",method=RequestMethod.GET)
	public String modifyTire(@RequestParam(value="id") String id,Model  model){
		Paramatersetting ptt=null;
		Gasstation gasstation=null;
		try {
			ptt=gasstationService.getParamatersettingList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] typrSpecs=Constants.splitString(ptt.getTyprSpec());
		String[] typrMaterials=Constants.splitString(ptt.getTyprMaterial());
		String[] tyreBrands=Constants.splitString(ptt.getTyreBrand());
		try {
			gasstation=gasstationService.getGasstationById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("typrSpecs", typrSpecs);
		model.addAttribute("typrMaterials", typrMaterials);
		model.addAttribute("tyreBrands", tyreBrands);
		model.addAttribute("gass",gasstation);
		return "tiremodify";
	}
	@RequestMapping(value="/tiremodifysave",method=RequestMethod.POST)
	public String modifyTireSave(Gasstation gasstation,HttpServletRequest request,
			@RequestParam(value="optionsRadios1",required=false) String[] tyreBrand,
			@RequestParam(value="optionsRadios2",required=false) String[] typrSpec,
			@RequestParam(value="optionsRadios3",required=false) String[] typrMaterial,Model model){
		Paramatersetting ptt=null;
		if(tyreBrand!=null&&tyreBrand.length>0){
			gasstation.setTyreBrand(Constants.getStringarray(tyreBrand));
		}
		if(typrSpec!=null&&typrSpec.length>0){
			gasstation.setTyprSpec(Constants.getStringarray(typrSpec));
		}
		if(typrMaterial!=null&&typrMaterial.length>0){
			gasstation.setTyprMaterial(Constants.getStringarray(typrMaterial));
		}
		gasstation.setCreatData(new Date());
		try {
			if(gasstationService.modifyGasstation(gasstation)){
				return "redirect:/gasstation/showtire";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ptt=gasstationService.getParamatersettingList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] typrSpecs=Constants.splitString(ptt.getTyprSpec());
		String[] typrMaterials=Constants.splitString(ptt.getTyprMaterial());
		String[] tyreBrands=Constants.splitString(ptt.getTyreBrand());
		model.addAttribute("typrSpecs", typrSpecs);
		model.addAttribute("typrMaterials", typrMaterials);
		model.addAttribute("tyreBrands", tyreBrands);
		request.setAttribute("error", "修改失败");
		model.addAttribute("gass",gasstation);
		return "tiremodify";
	}
	@RequestMapping(value="/tireselectid",method=RequestMethod.GET)
	public String selectTire(@RequestParam(value="id") String id,Model  model){
		Gasstation gasstation=null;
		try {
			gasstation=gasstationService.getGasstationById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("gass",gasstation);
		return "tireselect";
	}
	@RequestMapping(value="/tirecommit",method=RequestMethod.GET)
	public String tireCommit(String id,@RequestParam(value="error",required= false)String
			error,Model model){
		Gasstation gass=null;
		if(null != error && error.equals("error")){
			error = "账户禁用中";
		}
		if(null != error && error.equals("error2")){
			error = "上次审批还未通过";
		}
		if(null != error && error.equals("error3")){
			error = "保存失败";
		}
		if(null != error && error.equals("error4")){
			error = "提取的值必须大于0";
		}
		try {
			gass=gasstationService.getGasstationById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("money",gass.getQuota());
		model.addAttribute("yu",0);
		model.addAttribute("message", error);
		model.addAttribute("gass",gass);
		return "tireapply";
	}
	@RequestMapping(value="/tirecommitsave",method=RequestMethod.POST)
	public String tireCommitsave(@RequestParam(value="id",required=false) String id,
			@RequestParam(value="money",required=false) Integer money,
			@RequestParam(value="yu",required=false) String yu,HttpSession session){
		//取当前操作员工
		EmployeInfo emp=((EmployeInfo)session.getAttribute(Constants.USER_SESSION));
		Gasstation gass=null;
		ExtractApply extractApply=new ExtractApply();
		try {
			gass=gasstationService.getGasstationById(Integer.parseInt(id));
			if(money<=0){
				return "redirect:/gasstation/tirecommit?id="+gass.getId()+"&error=error4";
			}
			if(!gasstationService.getStatusById(Integer.parseInt(id))){
				return "redirect:/gasstation/tirecommit?id="+gass.getId()+"&error=error";
			}
			if(gasstationService.getExtractApplyStatusById(Integer.parseInt(id))){
				return "redirect:/gasstation/tirecommit?id="+gass.getId()+"&error=error2";
			}
			extractApply.setGsId(gass.getId());
			extractApply.setBusinessType(1);
			extractApply.setSurplusBalance(Integer.parseInt(yu));
			extractApply.setAmountDrawn(money);
			extractApply.setApplicationTime(new Date());
			extractApply.setStatus(0);
			extractApply.setNote(3);
			extractApply.setApplyUserId(emp.getId());
			extractApply.setTelePhone(gass.getMobilePhone());
			if(!gasstationService.addExtractApply(extractApply)){
				return "redirect:/gasstation/tirecommit?id="+gass.getId()+"&error=error3";
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "redirect:/gasstation/showtire";
	}
	@RequestMapping(value="/tirecommit.json")
	@ResponseBody
	public Object commitTire(@RequestParam String price,@RequestParam String zong){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(price)||StringUtils.isNullOrEmpty(price)){
			resultMap.put("price", "empty");
		}else{
			try {
				if(Integer.parseInt(price)<=0){		
					resultMap.put("price", "shao");
				}else if(Integer.parseInt(price)>Integer.parseInt(zong)){
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
	@RequestMapping(value="/dealernote")
	public String getDealerNote(Model model,HttpSession session,
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="begintime",required=false) String _begintime,
			@RequestParam(value="endtime",required=false) String _endtime,
			@RequestParam(value="status",required=false) String _status,
			@RequestParam(value="pageIndex",required=false) String pageIndex){
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
			totalCount=siteService.getOilrecordByCount(Integer.parseInt(id), status,Constants.getStringDate(_begintime),Constants.getStringDate(_endtime));
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
			list=siteService.getOilrecordByCondition(Integer.parseInt(id), status,Constants.getStringDate(_begintime),Constants.getStringDate(_endtime), currentPageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//查该站点
		try {
			gasstation=gasstationService.getGasstationById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer zhi=null;
		try {
			 zhi=siteService.getOilAll(Integer.parseInt(id));
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
		return "dealernote";
	}
	@RequestMapping(value="/tirenote")
	public String getDealerNote2(Model model,HttpSession session,
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="begintime",required=false) String _begintime,
			@RequestParam(value="endtime",required=false) String _endtime,
			@RequestParam(value="status",required=false) String _status,
			@RequestParam(value="pageIndex",required=false) String pageIndex){
		Gasstation gasstation=null;
		List<Tyredatails> list=null;
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
			totalCount=siteService.getTyredatailsByCount(Integer.parseInt(id), status,Constants.getStringDate(_begintime),Constants.getStringDate(_endtime));
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
			list=siteService.getTyredatailsByCondition(Integer.parseInt(id), status,Constants.getStringDate(_begintime),Constants.getStringDate(_endtime), currentPageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//查该站点
		try {
			gasstation=gasstationService.getGasstationById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
/*		Integer zhi=null;
		try {
			 zhi=siteService.getOilAll(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("zhi", zhi);*/
		model.addAttribute("gass",gasstation);
		model.addAttribute("list", list);
		model.addAttribute("pages", pages);
		model.addAttribute("status", status);
		model.addAttribute("begintime",_begintime);
		model.addAttribute("endtime",_endtime);
		return "tirebao";
	}
}
