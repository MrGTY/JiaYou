package cn.zhaojisys.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.pojo.EchartsMonth;
import cn.zhaojisys.pojo.EchartsYear;
import cn.zhaojisys.pojo.EmployeInfo;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Paramatersetting;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.pojo.VipuserinfoModel;
import cn.zhaojisys.service.AdminSummaryService;
import cn.zhaojisys.service.GasstationService;
import cn.zhaojisys.service.HomeService;
import cn.zhaojisys.service.SumService;
import cn.zhaojisys.service.UserService;
import cn.zhaojisys.tools.ComparatorTest;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.TyreDate;
import cn.zhaojisys.tools.UserAgentGetter;
import cn.zhaojisys.tools.VipUser;
import cn.zhaojisys.tools.VipUserModel;
import cn.zhaojisys.vo.AdminSummaryVo;
import cn.zhaojisys.vo.HomeVo;

@Controller
@RequestMapping("/backend")
public class AdminController {
	@Autowired
	UserService uService;
	@Autowired
	SumService sumService;
	@Resource
	private GasstationService gasstationService;

	
	
	// 老大哥/李杰
	@Autowired
	private HomeService homeService;

	@Autowired
	private AdminSummaryService AdminSu;
	// 退出系统
	@RequestMapping(value = "/userlogout")
	public String userlogout(HttpSession session, HttpServletRequest request) throws Exception {
		// 清除Session
		session.invalidate();

		return "redirect:/loginu";
	}

	// 退出系统
	@RequestMapping(value = "/zdlogout")
	public String zdlogout(HttpSession session) throws Exception {
		// 清除Session

		session.invalidate();
		return "redirect:/login";
	}

	// 加载首页燃油,轮胎收油量，提取额
	@RequestMapping(value = "/sumOil")
	public Object sumOil(Oilrecord oilrecord, Tyredatails tyredatails, Model model) {
		/*
		 * Oilrecord oilrec = sumService.findOil(oilrecord); Tyredatails tyre =
		 * sumService.findTyre(tyredatails); int sumOil = sumService.sumOil();
		 * int sumTyre = sumService.sumTyre(); //查询会员充值了多少油 Integer
		 * zong=sumService.chong(); Paramatersetting ptt=null; try {
		 * ptt=gasstationService.getParamatersettingList(); } catch (Exception
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * //查询提取量 Integer tiqu=sumService.tiqu(); //查询luntai Integer
		 * tiqu2=sumService.tiqu2(); //查询所有未提取的站点的油量总和 Integer
		 * sumQuota=sumService.getSumQuota();
		 * 
		 *//**
			 * 糖糖的
			 *//*
			 * // 查询首页燃油提取量 int sumOilDraw = sumService.sumOilTyreDraw(0, 1); //
			 * 查询首页燃油待提取量 int sumOilWaitDraw = sumService.sumOilTyreDraw(0, 0);
			 * // 查询首页轮胎提取量 int sumTyreDraw = sumService.sumOilTyreDraw(1, 1);
			 * // 查询首页轮胎待提取量 int sumTyreWaitDraw = sumService.sumOilTyreDraw(1,
			 * 0); // 首页查询燃油或者轮胎站点个数 int sumOilStation =
			 * sumService.sumOilTyreStation(0); // 首页查询燃油或者轮胎站点个数 int
			 * sumTyreStation = sumService.sumOilTyreStation(1); // 查询会员总量 int
			 * sumVipUser = sumService.sumVipUser(0) + sumService.sumVipUser(1);
			 * // 首页查询物流公司个数 int logisCompany = sumService.sumVipUser(1); //
			 * 首页查询车主个数 int driver = sumService.sumVipUser(0); // 首页燃油收油量 String
			 * oilrecIncome; if(oilrec!=null){ oilrecIncome=
			 * Constants.change(oilrec.getSumIncome()).toString(); }else{
			 * oilrecIncome="0"; }
			 * 
			 * // 首页燃油提取额 String oilrecSumExpenditure; if (oilrec!=null) {
			 * oilrecSumExpenditure=
			 * Constants.change(oilrec.getSumExpenditure()).toString(); }else {
			 * oilrecSumExpenditure="0"; }
			 * 
			 * // 首页首页轮胎收油量 String tyreSumIncome;
			 * 
			 * if (tyre!=null) { tyreSumIncome=
			 * Constants.change(tyre.getSumIncome()).toString(); }else {
			 * tyreSumIncome="0"; }
			 * 
			 * // 首页首页轮胎提取额 String tyreSumExpenditure; if (tyre!=null) {
			 * tyreSumExpenditure=
			 * Constants.change(tyre.getSumExpenditure()).toString(); }else {
			 * tyreSumExpenditure="0"; }
			 * 
			 * 
			 * int countOil = sumService.numGasstation(0); int countTyre =
			 * sumService.numGasstation(1); int countEmp = sumService.numEmp();
			 * model.addAttribute("oilrecIncome", oilrecIncome);
			 * model.addAttribute("oilrecSumExpenditure", oilrecSumExpenditure);
			 * model.addAttribute("tyreSumIncome", tyreSumIncome);
			 * model.addAttribute("tyreSumExpenditure", tyreSumExpenditure);
			 * model.addAttribute("countOil", countOil);
			 * model.addAttribute("countTyre", countTyre);
			 * model.addAttribute("countEmp", countEmp);
			 * model.addAttribute("sumOil", sumOil);
			 * model.addAttribute("sumTyre", sumTyre);
			 * model.addAttribute("sumOilDraw", sumOilDraw);
			 * model.addAttribute("sumOilWaitDraw", sumOilWaitDraw);
			 * model.addAttribute("sumTyreDraw", sumTyreDraw);
			 * model.addAttribute("sumTyreWaitDraw", sumTyreWaitDraw);
			 * model.addAttribute("sumOilStation", sumOilStation);
			 * model.addAttribute("sumTyreStation", sumTyreStation);
			 * model.addAttribute("sumVipUser", sumVipUser);
			 * model.addAttribute("logisCompany", logisCompany);
			 * model.addAttribute("driver", driver);
			 * model.addAttribute("zong", zong); model.addAttribute("ptt", ptt);
			 * model.addAttribute("tiqu", tiqu); model.addAttribute("tiqu2",
			 * tiqu2); model.addAttribute("sumQuota", sumQuota); // benyue //
			 * Date
			 * shijian=Constants.changeDate(tyredatails.getOperationData()); //
			 * tyredatails.setDate(shijian); List<Tyredatails> tr =
			 * sumService.benyue(); model.addAttribute("tr", tr);
			 */

		// 老大哥/李杰
		HomeVo homeVo=null;
		try {
			homeVo=homeService.getSum();
//			JSONObject jsonObject=new JSONObject();
//			jsonObject.put("homeVo", homeVo);
			
			model.addAttribute("homeVo",homeVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "user";
	}

	@RequestMapping(value = "/benyue")
	@ResponseBody
	public Object month() {
		/*EchartsMonth month = new EchartsMonth();
		// 得到当前时间的天数
		Date date2 = new Date();
		SimpleDateFormat format = new SimpleDateFormat("d");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》8
		int day = Integer.parseInt(curdate);
		// 首页查询本月轮胎总交易额
		List<Tyredatails> list = sumService.benyue();
		List<Tyredatails> list2 = sumService.benyue();
		// 集合大小 !=当前天数，进行匹配
		if (list.size() != day) {
			List<Tyredatails> list3 = Constants.changeTyredatails(list);
			for (int i = 0; i < list3.size(); i++) {
				Tyredatails tyredatails = new Tyredatails();
				tyredatails.setOperationData(list3.get(i).getOperationData());
				tyredatails.setIncome(list3.get(i).getIncome());
				list2.add(tyredatails);
			}
		}
		list2.addAll(list);
		Collections.sort(list2, new TyreDate());
		month.setList2(list2);

		// 首页查询本月燃油总收油量begin
		List<Oilrecord> list1 = sumService.oilMonSum();
		List<Oilrecord> oilrecordlist11 = sumService.oilMonSum();
		// 集合大小 !=当前天数，进行匹配
		if (list1.size() != day) {
			List<Oilrecord> list3 = Constants.muchDataIsOk(list1);
			for (int i = 0; i < list3.size(); i++) {
				Oilrecord oilrecord = new Oilrecord();
				oilrecord.setOperationTime(list3.get(i).getOperationTime());
				oilrecord.setIncome(list3.get(i).getIncome());
				oilrecordlist11.add(oilrecord);
			}
		}
		oilrecordlist11.addAll(list1);
		Collections.sort(oilrecordlist11, new ComparatorTest());
		month.setList3(oilrecordlist11);
		// end
		// 首页查询本月燃油总提取量
		List<Oilrecord> list4 = sumService.oilDraWMonSum();
		List<Oilrecord> list5 = sumService.oilDraWMonSum();
		// 集合大小 !=当前天数，进行匹配
		if (list4.size() != day) {
			List<Oilrecord> list55 = Constants.muchDataIsOk(list4);
			for (int i = 0; i < list55.size(); i++) {
				Oilrecord oilrecord = new Oilrecord();
				oilrecord.setOperationTime(list55.get(i).getOperationTime());
				oilrecord.setIncome(list55.get(i).getIncome());
				oilrecord.setExpenditure(list55.get(i).getExpenditure());
				list5.add(oilrecord);
			}
		}
		list5.addAll(list4);
		Collections.sort(list5, new ComparatorTest());
		for (Oilrecord oilrecord : list5) {
			System.out.println(oilrecord.getIncome());
		}
		month.setList5(list5);

		// 首页查询本月轮胎总提取量
		List<Tyredatails> list6 = sumService.tyreDraWMonSum();
		List<Tyredatails> list7 = sumService.tyreDraWMonSum();
		if (list6.size() != day) {
			List<Tyredatails> list77 = Constants.changeTyredatails(list6);
			for (int i = 0; i < list77.size(); i++) {
				Tyredatails tyredatails = new Tyredatails();
				tyredatails.setOperationData(list77.get(i).getOperationData());
				tyredatails.setExpenditure(list77.get(i).getExpenditure());
				list7.add(tyredatails);
			}
		}
		list7.addAll(list6);
		Collections.sort(list7, new TyreDate());
		month.setList7(list7);
		// 首页查询本月新增会员个数
		List<VipuserinfoModel> list8 = sumService.newVipNum();
		List<VipuserinfoModel> list9 = sumService.newVipNum();
		if (list8.size() != day) {
			List<VipuserinfoModel> list99 = Constants.changeVipuserinfosModel(list8);
			for (int i = 0; i < list99.size(); i++) {
				VipuserinfoModel vipuserinfo = new VipuserinfoModel();
				vipuserinfo.setCreateTime(list99.get(i).getCreateTime());
				vipuserinfo.setId(list99.get(i).getId());
				list9.add(vipuserinfo);
			}
		}
		list9.addAll(list8);
		Collections.sort(list9, new VipUserModel());
		month.setList9(list9);
		// 首页本月燃油交易量
		List<Oilrecord> list10 = sumService.oilSumDealNum();
		List<Oilrecord> list11 = sumService.oilSumDealNum();
		if (list10.size() != day) {
			List<Oilrecord> list111 = Constants.muchDataIsOk(list10);
			for (int i = 0; i < list111.size(); i++) {
				Oilrecord oilrecord = new Oilrecord();
				oilrecord.setOperationTime(list111.get(i).getOperationTime());
				oilrecord.setIncome(list111.get(i).getIncome());
				oilrecord.setId(list111.get(i).getId());
				list11.add(oilrecord);
			}
		}
		list11.addAll(list10);
		Collections.sort(list11, new ComparatorTest());
		month.setList11(list11);

		// 首页本月轮胎交易量
		List<Tyredatails> list12 = sumService.tyreSumDealNum();
		List<Tyredatails> list13 = sumService.tyreSumDealNum();
		if (list12.size() != day) {
			List<Tyredatails> list33 = Constants.changeTyredatails(list12);
			for (int i = 0; i < list33.size(); i++) {
				Tyredatails tyredatails = new Tyredatails();
				tyredatails.setOperationData(list33.get(i).getOperationData());
				tyredatails.setId(list33.get(i).getId());
				list12.add(tyredatails);
			}
		}
		list13.addAll(list12);
		Collections.sort(list13, new TyreDate());
		month.setList13(list13);*/
		JSONObject jsonObject=new JSONObject();
		AdminSummaryVo month=null;
		try {
			month = AdminSu.mounth();
			jsonObject.put("month", month);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	@RequestMapping(value = "/year")
	@ResponseBody
	public Object year() {
		/*EchartsYear year = new EchartsYear();
		// 得到当前时间的月份
		Date date2 = new Date();
		SimpleDateFormat format = new SimpleDateFormat("M");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》9
		int day = Integer.parseInt(curdate);
		// 首页查询年度轮胎总交易额
		List<Tyredatails> list = sumService.yearTyreSum();
		List<Tyredatails> list2 = sumService.yearTyreSum();
		if (list.size() != day) {
			List<Tyredatails> list22 = Constants.yearChangeTyredatails(list);
			for (int i = 0; i < list22.size(); i++) {
				Tyredatails tyredatails = new Tyredatails();
				tyredatails.setId(list22.get(i).getId());
				tyredatails.setIncome(list22.get(i).getIncome());
				tyredatails.setOperationData(list22.get(i).getOperationData());
				list2.add(tyredatails);
			}
			list2.addAll(list);
		}
		Collections.sort(list2, new TyreDate());
		year.setList2(list2);
		// 首页查询年度燃油总收油量
		List<Oilrecord> list1 = sumService.yearOilMonSum();
		List<Oilrecord> list3 = sumService.yearOilMonSum();
		if (list1.size() != day) {
			List<Oilrecord> list33 = Constants.yearMuchDataIsOk(list1);
			for (int i = 0; i < list33.size(); i++) {
				Oilrecord oilrecord = new Oilrecord();
				oilrecord.setIncome(list33.get(i).getIncome());
				oilrecord.setOperationTime(list33.get(i).getOperationTime());
				list3.add(oilrecord);
			}
			list3.addAll(list1);
		}
		Collections.sort(list3, new ComparatorTest());
		year.setList3(list3);
		// 首页查询年度燃油总提取量
		List<Oilrecord> list4 = sumService.yearOilDraWMonSum();
		List<Oilrecord> list5 = sumService.yearOilDraWMonSum();
		if (list4.size() != day) {
			List<Oilrecord> list55 = Constants.yearMuchDataIsOk(list4);
			for (int i = 0; i < list55.size(); i++) {
				Oilrecord oilrecord = new Oilrecord();
				oilrecord.setOperationTime(list55.get(i).getOperationTime());
				oilrecord.setExpenditure(list55.get(i).getExpenditure());
				list5.add(oilrecord);
			}
			list5.addAll(list4);
		}
		Collections.sort(list5, new ComparatorTest());
		year.setList5(list5);
		// 首页查询年度轮胎总提取量
		List<Tyredatails> list6 = sumService.yearTyreDraWMonSum();
		List<Tyredatails> list7 = sumService.yearTyreDraWMonSum();
		if (list6.size() != day) {
			List<Tyredatails> list77 = Constants.yearChangeTyredatails(list6);
			for (int i = 0; i < list77.size(); i++) {
				Tyredatails tyredatails = new Tyredatails();
				tyredatails.setId(list77.get(i).getId());
				tyredatails.setOperationData(list77.get(i).getOperationData());
				tyredatails.setExpenditure(list77.get(i).getExpenditure());
				list7.add(tyredatails);
			}
			list7.addAll(list6);
		}
		Collections.sort(list7, new TyreDate());
		year.setList7(list7);
		// 首页查询年度新增会员个数
		List<Vipuserinfo> list8 = sumService.yearNewVipNum();
		List<Vipuserinfo> list9 = sumService.yearNewVipNum();
		if (list8.size() != day) {
			List<Vipuserinfo> list99 = Constants.yearChangeVipuserinfos(list8);
			for (int i = 0; i < list99.size(); i++) {
				Vipuserinfo vipuserinfo = new Vipuserinfo();
				vipuserinfo.setCreateTime(list99.get(i).getCreateTime());
				vipuserinfo.setId(list99.get(i).getId());
				list9.add(vipuserinfo);
			}
			list9.addAll(list8);
		}
		Collections.sort(list9, new VipUser());
		year.setList9(list9);
		// 首页年度燃油交易量
		List<Oilrecord> list10 = sumService.yearOilSumDealNum();
		List<Oilrecord> list11 = sumService.yearOilSumDealNum();
		if (list10.size() != day) {
			List<Oilrecord> list111 = Constants.yearMuchDataIsOk(list10);
			for (int i = 0; i < list111.size(); i++) {
				Oilrecord oilrecord = new Oilrecord();
				oilrecord.setOperationTime(list111.get(i).getOperationTime());
				oilrecord.setId(list111.get(i).getId());
				list11.add(oilrecord);
			}
			list11.addAll(list10);
		}
		Collections.sort(list11, new ComparatorTest());
		year.setList11(list11);
		// 首页年度轮胎交易量
		List<Tyredatails> list12 = sumService.yearTyreSumDealNum();
		List<Tyredatails> list13 = sumService.yearTyreSumDealNum();
		if (list12.size() != day) {
			List<Tyredatails> list133 = Constants.yearChangeTyredatails(list12);
			for (int i = 0; i < list133.size(); i++) {
				Tyredatails tyredatails = new Tyredatails();
				tyredatails.setOperationData(list133.get(i).getOperationData());
				tyredatails.setId(list133.get(i).getId());
				list13.add(tyredatails);
			}
			list13.addAll(list12);
		}
		Collections.sort(list13, new TyreDate());
		year.setList13(list13);*/
		JSONObject year=new JSONObject();
		AdminSummaryVo summaryVo=null;
		try {
			summaryVo=AdminSu.year();
			year.put("summaryVo", summaryVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return year;
	}

}
