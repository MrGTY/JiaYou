package cn.zhaojisys.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.zhaojisys.pojo.Customer;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.HeadLine;
import cn.zhaojisys.pojo.Paramatersetting;
import cn.zhaojisys.service.ParamatersettingService;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.PageSupport;

@Controller
public class ParamatersettingController {
	
	@Autowired
	ParamatersettingService paramatersettingService;
	
	//查询参数
	@RequestMapping(value="updateParam",method=RequestMethod.GET)
	public ModelAndView selectParama(@ModelAttribute("paramatersetting")Paramatersetting paramatersetting,Model model){
		ModelAndView mav=new ModelAndView();
		List<Paramatersetting> list= new ArrayList<Paramatersetting>();
		try {
			list=paramatersettingService.getParamatersettingList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("lis", list);
		mav.setViewName("paramSet");
		return mav;
	}
	//更新参数
	@RequestMapping(value="updateParam",method=RequestMethod.POST)
	public Object updateParam(Paramatersetting paramatersetting,Model model){
		int num=0;
		try {
			num=paramatersettingService.updateListById(paramatersetting);
			if(num>0){
				model.addAttribute("msg", "更新成功！");
			}else{
				model.addAttribute("msg", "更新失败！");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return selectParama(paramatersetting,model);
	}
	//发布信息
	@RequestMapping(value="updateheadline",method=RequestMethod.GET)
	public String addLine(@ModelAttribute("headLine")HeadLine headLine,Model model){
		try {
			headLine=paramatersettingService.chaLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("headLine", headLine);
		return "fabu";
	}
	//发布信息
	@RequestMapping(value="updateheadline",method=RequestMethod.POST)
	public String addLineSave(HeadLine headLine,Model model){
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		headLine.setCreateTime(date);
		headLine.setMessage(format.format(date)+" : "+headLine.getMessage());
		try {
			if(paramatersettingService.addLine(headLine)){
				return "redirect:/backend/sumOil";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fabu";
	}
	@RequestMapping(value="/showhu")
	public String getGasstationListByCon(Model model,HttpSession session,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="pageIndex",required=false) String pageIndex
			){
		List<Customer> list=null;
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
		int totalCount = 0;
		try {
			totalCount=paramatersettingService.getCustomerCount(name);
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
			list=paramatersettingService.getCustomer(name, currentPageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		model.addAttribute("pages", pages);
		model.addAttribute("name", name);
		return "kehu";
	}
}
