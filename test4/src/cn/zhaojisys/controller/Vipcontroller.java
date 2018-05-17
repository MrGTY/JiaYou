package cn.zhaojisys.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
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
import cn.zhaojisys.model.Public;
import cn.zhaojisys.model.User;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Gathering;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.VipuserinfoService;
import cn.zhaojisys.tools.Constants;

@Controller
@RequestMapping("vipuserinfo")
public class Vipcontroller {
	
	@Resource
	VipuserinfoService service=null;
	
	private Logger log=Logger.getLogger(Vipcontroller.class);
	//按条件查询会员信息
	@RequestMapping(value="vipindex")
	public String getViplistbyCondition(@ModelAttribute("user") User user,
										@RequestParam(value="pageNo",required=false)Integer pageNo,
										Model model
			){
		int newPageNo=1;
		int totalPageNo;
		
		if (pageNo!=null&&pageNo!=0) {
			newPageNo=pageNo;
		}
		int pageSize=Constants.pageSize;
		List<Vipuserinfo> viplist=new ArrayList<Vipuserinfo>();
		int totalPage;
		try {
			totalPage = service.selectCount(user.getVipName(), user.getUserName(), user.getBusNum(), user.getUserType(),user.getMycarId());
			totalPageNo= totalPage%pageSize==0? totalPage/pageSize:totalPage/pageSize+1;
			if(pageNo!=null&&totalPageNo!=0&&pageNo>totalPageNo){
				newPageNo=totalPageNo;
			}
			viplist=service.selectVipbyCondition(user.getVipName(), user.getUserName(), user.getBusNum(), user.getUserType(),user.getMycarId(),(newPageNo-1)*pageSize, pageSize);
			log.info("userType=======================================>"+user.getUserType());
			log.info("totalPageNo=======================================>"+totalPageNo);
			log.info("newPageNo=======================================>"+newPageNo);
			model.addAttribute("viplist", viplist);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("pageNo", newPageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("user", user);
			model.addAttribute("totalPageNo",totalPageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/vip/vip";
	}
	
	
	@RequestMapping(value="back")
	public Object bacKinfo(@RequestParam("vipName")String vipName,
							@RequestParam("userName")String userName,
							@RequestParam("busNum")String busNum,
							@RequestParam("userType")Integer userType,
							@RequestParam("pageNo")Integer pageNo,
							Model model
							){
				User user=new User();
				user.setVipName(vipName);
				user.setUserName(userName);
				user.setBusNum(busNum);
				user.setUserType(userType);
		
			return getViplistbyCondition(user, pageNo, model);
	}
		
	//显示收支记录
	@RequestMapping("selectrecord")
	public String selectrecord(@RequestParam(value="id" ,required=false)Integer id,Model model){
		//id查询用户所有信息
		try {
			Vipuserinfo vipuserinfo=service.selectAllbyId(id);
			model.addAttribute("vipUser", vipuserinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "vip/vipjilu";
	}
	
	//根据id查询用户所信息与 根据条件查询燃油收支明细
	@RequestMapping(value="selectUserandOil")
	public String selectUser(@RequestParam(value="id" ,required=false)Integer id,
							 @RequestParam(value="pageNo",required=false)Integer pageNo,	
							 @ModelAttribute("Public")Public oilrecord,
							 @RequestParam(value="startTime" ,required=false)String startTime,
							 @RequestParam(value="endTime" ,required=false)String endTime,
							 Model model){
		try {
			int newPageNo=1;
			int totalPageNo;
			int totalPage;
			if (pageNo!=null&&pageNo!=0) {
				newPageNo=pageNo;
			}
			int pageSize=Constants.pageSize;
			//id查询用户所有信息
			Vipuserinfo vipuserinfo=service.selectAllbyId(id);
			model.addAttribute("vipUser", vipuserinfo);
			
			totalPage = service.getoilCount(oilrecord.getPaymentType(), oilrecord.getOperationType(), startTime, endTime, id);
			totalPageNo= totalPage%pageSize==0? totalPage/pageSize:totalPage/pageSize+1;
			//根据条件查询燃油明细
			List<Oilrecord> oilrecordList=service.getOilrecord(oilrecord.getPaymentType(), oilrecord.getOperationType(), startTime, endTime,id,(newPageNo-1)*pageSize,pageSize);
			log.info("totalPageNo=======================================>"+totalPageNo);
			log.info("paymentType=======================================>"+oilrecord.getPaymentType());
			log.info("newPageNo=======================================>"+newPageNo);
			model.addAttribute("oilrecordList", oilrecordList);
			model.addAttribute("pageNo", newPageNo);
			model.addAttribute("oilrecord", oilrecord);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("totalPageNo", totalPageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "vip/a";
	}
	
	
	

	//根据id查询用户所信息与 根据条件查询轮胎收支明细
		@RequestMapping(value="selectUserandTyre")
		public String selectTyre(@RequestParam(value="id" ,required=false)Integer id,
								 @RequestParam(value="pageNo",required=false)Integer pageNo,	
								 @ModelAttribute("Public")Public Tyre,
								 Model model){
			try {
				int newPageNo=1;
				int totalPageNo;
				int totalPage;
				if (pageNo!=null&&pageNo!=0) {
					newPageNo=pageNo;
				}
				int pageSize=Constants.pageSize;
				//id查询用户所有信息
				Vipuserinfo vipuserinfo=service.selectAllbyId(id);
				totalPage = service.getTyreCount(Tyre.getPaymentType(), Tyre.getStartTime(), Tyre.getEndTime(), id);
				totalPageNo= totalPage%pageSize==0? totalPage/pageSize:totalPage/pageSize+1;
				model.addAttribute("vipUser", vipuserinfo);
				
				//根据条件查询燃油明细
				List<Tyredatails> TyredatailsList=service.getTyrerelist(Tyre.getPaymentType(), Tyre.getStartTime(), Tyre.getEndTime(), id, (newPageNo-1)*pageSize, pageSize);
				log.info("totalPageNo=======================================>"+totalPageNo);
				log.info("newPageNo=======================================>"+newPageNo);
				model.addAttribute("TyredatailsList", TyredatailsList);
				model.addAttribute("pageNo", newPageNo);
				model.addAttribute("totalPage", totalPage);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("Tyre", Tyre);
				model.addAttribute("totalPageNo", totalPageNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "vip/b";
		}
	
	//显示收款记录	
		@RequestMapping("Gathering")
		public String collection(@RequestParam(value="id" ,required=false)Integer id,
								 @ModelAttribute("user")User user,
								 @ModelAttribute("public")Public pub,
								 @RequestParam(value="pageNo",required=false)Integer pageNo,
								 Model model){
			try {
				int newPageNo=1;
				int totalPageNo;
				int totalPage;
				if (pageNo!=null&&pageNo!=0) {
					newPageNo=pageNo;
				}
				int pageSize=Constants.pageSize;
				Vipuserinfo vipuserinfo=service.selectAllbyId(id);
				model.addAttribute("vipUser", vipuserinfo);
				List<Gathering> lisGatherings=new ArrayList<Gathering>();
				//按条件查询或获取所有记录
				lisGatherings=service.getCollist(id,user.getVipName(), pub.getStartTime(),pub.getEndTime(), (newPageNo-1)*pageSize, pageSize);
				//按条件查询或查询所有记录数
				totalPage=service.getcollCount(id,user.getVipName(), pub.getStartTime(),pub.getEndTime());
				totalPageNo= totalPage%pageSize==0? totalPage/pageSize:totalPage/pageSize+1;
				log.info("totalPageNo=======================================>"+totalPageNo);
				log.info("newPageNo=======================================>"+newPageNo);
				model.addAttribute("pageNo", newPageNo);
				model.addAttribute("lisGatherings", lisGatherings);
				model.addAttribute("totalPage", totalPage);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("user", user);
				model.addAttribute("totalPageNo", totalPageNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "vip/c";
		}
		
		
	//根据id修改会员信息
	@RequestMapping(value="userChange")
	public Object changeUserinfo(@ModelAttribute("user")User user,
								 @RequestParam(value="pageNo1",required=false)Integer pageNo,
								 @RequestParam("change")String change,Model model
			){
		log.info("pageNo=========================================>"+pageNo);
		if(user.getId()==null){
			model.addAttribute("massage", "必须选中启用或禁用账户");
		}else{
			if("禁用账户".equals(change)){
				user.setStateTag(1);
				for (int i = 0; i < user.getId().length; i++) {
					try {
						service.updataUser(user.getId()[i], user.getStateTag(), user.getOilMass(), user.getTyreBalance(), user.getPassword());
						
					} catch (Exception e) {
						e.printStackTrace();	
					}
				}
			}else if("启用账户".equals(change)){
				user.setStateTag(0);
				for (int i = 0; i < user.getId().length; i++) {
					try {
						service.updataUser(user.getId()[i], user.getStateTag(), user.getOilMass(), user.getTyreBalance(), user.getPassword());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return getViplistbyCondition(user,pageNo, model);
	}
	
	//根据id删除用户
	@RequestMapping(value="delete")
	@ResponseBody
	public Object deleteUser(@RequestParam("id")Integer id){
		int num=0;
		log.info("id==========================================>"+id);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(id==null){
			resultMap.put("delResult", "notexist");
		}else{
			try {
				num=service.deleteUser(id);
				if(num>0){
					resultMap.put("delResult","true");
				}else{
					resultMap.put("delResult","false");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
	
	//修改密码
	@RequestMapping("updatepwd")
	@ResponseBody
	public Object updatepwd(@RequestParam("id")Integer id,
							@RequestParam("password")String password){
		Map<String, String> map=new HashMap<String, String>();
		int num=0;
		try {
			num=service.updatepwd(id, password);
			if(num>0){
				map.put("mas", "success");
			}else{
				 map.put("mas", "failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.toJSONString(map);
	}
	
	//修改分类
	@RequestMapping("changeType")
	@ResponseBody
	public Object changeType(@RequestParam("id")Integer id,
							@RequestParam("userType")Integer userType,
							@RequestParam(value="remark",required=false)String remark,
							@RequestParam(value="logicId",required=false)Integer logicId){
		Map<String, String> map=new HashMap<String, String>();
		Vipuserinfo vip=null;
		int num=0;
		try {
			//先靠id找物流名
			vip=service.getVipuserinfoBylogicId(logicId);
			//改
			if(vip!=null){
				num=service.changetype(id, userType, remark, logicId,vip.getVipName());
			}else{
					num=service.changetype(id, userType, remark, null,null);
			}
			if(num>0){
				 map.put("mas", "success");
			}else{
				 map.put("mas", "failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.toJSONString(map);
	}
	
	//ajax
	@RequestMapping("getinfo")
	@ResponseBody
	public Object getinfo(@RequestParam(value="id",required=false)Integer id
								){
		Vipuserinfo vipuserinfo=null;
		try {
			vipuserinfo=service.selectAllbyId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vipuserinfo;
	}
	
	//ajax
	@RequestMapping("info")
	@ResponseBody
	public Object getvip(@RequestParam(value="check",required=false)Integer[] check,
								@RequestParam(value="id",required=false)Integer id
								){
		Vipuserinfo vipuserinfo=null;
		List<Vipuserinfo> list=new ArrayList<Vipuserinfo>();
		
		if(id!=null){
			try {
				vipuserinfo=service.selectAllbyId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return vipuserinfo;
		}
		for (int i = 0; i < check.length; i++) {
			try {
				vipuserinfo=service.selectAllbyId(check[i]);
				list.add(vipuserinfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return list;	
	}
	
	@RequestMapping("list")
	public String getlist(@RequestParam(value="check",required=false)Integer[] check,
								@RequestParam(value="id",required=false)Integer id,
								Model model){
		Vipuserinfo vipuserinfo=null;
		List<Vipuserinfo> list=new ArrayList<Vipuserinfo>();
		for (int i = 0; i < check.length; i++) {
			try {
				vipuserinfo=service.selectAllbyId(check[i]);
				list.add(vipuserinfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		model.addAttribute("list", list);						
		return "vip/vip";
	}
	
	//充值
	@RequestMapping("recharge")
	@ResponseBody
	public Object recharge(@RequestParam(value="options",required=false)Integer options,
							@RequestParam(value="optionsRad",required=false)Integer optionsRad,
							@RequestParam(value="id",required=false)Integer id,
							@RequestParam(value="moneyname",required=false)Integer moneyname,
							@RequestParam(value="oilname",required=false)Integer oilname,
							@RequestParam(value="remark" ,required=false)String remark,
							HttpSession session){
		Map<String, String> map=new HashMap<String, String>();
		
		try {
			map=service.getRecharge(id, moneyname, oilname, options, optionsRad, remark,session);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mas","Excep");
		}
		
		return JSONArray.toJSONString(map);
	}
	
	//收款
	@RequestMapping("gathering")
	@ResponseBody
	public Object gathering(@RequestParam(value="id",required=false )Integer id,
							@RequestParam(value="oiltype",required=false )Integer oiltype,
							@RequestParam(value="Payamount",required=false )Integer Payamount,
							@RequestParam(value="remark",required=false )String remark,
							HttpSession session
							){
	
		Map<String, String> map=new HashMap<String, String>();
		try {
			map=service.getGathering(id, oiltype, Payamount, remark, session);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mas", "Excep");
		}
			return JSONArray.toJSONString(map);
	}

}