package cn.zhaojisys.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Session;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.pojo.EchartsGasstationMonth;
import cn.zhaojisys.pojo.EchartsGasstationYear;
import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Paramatersetting;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.service.GasstationService;
import cn.zhaojisys.service.OilSumService;
import cn.zhaojisys.service.OilrecordServiceMapper;
import cn.zhaojisys.service.ZhandianSumService;
import cn.zhaojisys.tools.ComparatorTest;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.vo.ZhandianSumVo;

@Controller
@RequestMapping("/backend")
public class OilrecordConroller {

	@Autowired
	private OilSumService osm;

	@Autowired
	private OilrecordServiceMapper oilrecordServiceMapper;
	@Autowired
	private ZhandianSumService zhandianSumService;
	@Resource
	private GasstationService gasstationService;

	@RequestMapping(value = "/Oilrecords1")
	@ResponseBody
	public Object Oilrecords1(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		HttpSession session = request.getSession();
		Gasstation gas = (Gasstation) session.getAttribute(Constants.DEV_USER_SESSION);
		try {
			Paramatersetting ptt = null;
			ptt = gasstationService.getParamatersettingList();
			ZhandianSumVo sumVo = zhandianSumService.sumVosMounth(gas.getId());
			sumVo.setParamatersetting(ptt);
			jsonObject.put("sumVo", sumVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	@RequestMapping(value = "/oilrecords2")
	@ResponseBody
	public Object oilrecords2(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		HttpSession session = request.getSession();
		Gasstation gas = (Gasstation) session.getAttribute(Constants.DEV_USER_SESSION);
		try {
			Paramatersetting ptt = null;
			ptt = gasstationService.getParamatersettingList();
			ZhandianSumVo sumVo = zhandianSumService.sumVosYear(gas.getId());
			sumVo.setParamatersetting(ptt);
			jsonObject.put("sumVo", sumVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	@RequestMapping(value = "/selectOil")
	public String sumOil(Oilrecord oilrecord, Tyredatails tyredatails, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Gasstation gas = (Gasstation) session.getAttribute(Constants.DEV_USER_SESSION);
		/*
		 * Oilrecord oilr = osm.findOilSum(oilrecord, gas.getId(),1); Oilrecord
		 * oilr1 = osm.findOilSum(oilrecord, gas.getId(),0); Oilrecord oilrYear
		 * = osm.findOilSumMonth(oilrecord, gas.getId(),1); Oilrecord oilrYear1
		 * = osm.findOilSumMonth(oilrecord, gas.getId(),0); // 燃油站点首页燃油收油量 if
		 * (oilr !=null) { String oilSumMoney =
		 * Constants.change(oilr.getIncome()==null?0:oilr.getIncome())
		 * .toString(); String
		 * oilOrderNum=Constants.change(oilr.getId()==null?0:oilr.getId())
		 * .toString(); String
		 * oilOrderRemaining=Constants.change(oilr.getRemaining()==null?0:oilr.
		 * getRemaining()) .toString(); String
		 * oilOrderRemaining1=Constants.change(oilr1.getRemaining()==null?0:
		 * oilr1.getRemaining()) .toString();
		 * 
		 * model.addAttribute("oilSumMoney", oilSumMoney);
		 * model.addAttribute("oilOrderNum", oilOrderNum);
		 * model.addAttribute("oilOrderRemaining", oilOrderRemaining);
		 * model.addAttribute("oilOrderRemaining1", oilOrderRemaining1); }else {
		 * model.addAttribute("oilSumMoney", 0);
		 * model.addAttribute("oilOrderNum", 0);
		 * model.addAttribute("oilOrderRemaining", 0);
		 * model.addAttribute("oilOrderRemaining1", 0); }
		 * 
		 * // 燃油站点首页燃油收油量汇总 if (oilrYear !=null) { String oilSumMoneyYear =
		 * Constants.change(oilrYear.getIncome()==null?0:oilrYear.getIncome())
		 * .toString(); String
		 * oilOrderNumYear=Constants.change(oilrYear.getId()==null?0:oilrYear.
		 * getId()) .toString(); String
		 * oilOrderRemainingYear=Constants.change(oilrYear.getRemaining()==null?
		 * 0:oilrYear.getRemaining()) .toString(); String
		 * oilOrderRemainingYear1=Constants.change(oilrYear1.getRemaining()==
		 * null?0:oilrYear1.getRemaining()) .toString();
		 * 
		 * model.addAttribute("oilSumMoneyYear", oilSumMoneyYear);
		 * model.addAttribute("oilOrderNumYear", oilOrderNumYear);
		 * model.addAttribute("oilOrderRemainingYear", oilOrderRemainingYear);
		 * model.addAttribute("oilOrderRemainingYear1", oilOrderRemainingYear1);
		 * }else { model.addAttribute("oilSumMoneyYear", 0);
		 * model.addAttribute("oilOrderNumYear", 0);
		 * model.addAttribute("oilOrderRemainingYear", 0);
		 * model.addAttribute("oilOrderRemainingYear1", 0); }
		 */
		Paramatersetting ptt = null;
		try {
			ptt = gasstationService.getParamatersettingList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 当月燃油交易额、本月交易次数
			Oilrecord oilrecord1 = zhandianSumService.findOilSum(gas.getId());
			String income=null;
			if (oilrecord1 != null) {
				// 当月燃油交易额
				income = Constants.change(oilrecord1.getIncome() == null ? 0 : oilrecord1.getIncome())
						.toString();
				// 本月交易次数
				String id = Constants.change(oilrecord1.getId() == null ? 0 : oilrecord1.getId()).toString();
				model.addAttribute("income", income);
				model.addAttribute("id", id);
			} else {
				model.addAttribute("income", 0);
				model.addAttribute("id", 0);
			}
			// 燃油站点查询当月燃油可提取額
			ExtractApply apply = zhandianSumService.findOilSumDrawMonth(gas.getId());
			if (apply != null) {
				// 燃油站点查询当月燃油可提取額
				String amountDrawn = Constants.change(apply.getAmountDrawn() == null ? 0 : apply.getAmountDrawn()*ptt.getUnitConversion())
						.toString();
				String  jian=Constants.change((oilrecord1.getIncome() == null ? 0 : oilrecord1.getIncome())-(apply.getAmountDrawn() == null ? 0 : apply.getAmountDrawn()*ptt.getUnitConversion())).toString();
				model.addAttribute("jian", jian);
				model.addAttribute("amountDrawn", amountDrawn);
			} else {
				model.addAttribute("amountDrawn", 0);
				model.addAttribute("jian", income);
			}
			
			Oilrecord oilrecord2 = zhandianSumService.findOilSum2(gas.getId());
			String income2=null;
			if (oilrecord2 != null) {
				// 当月燃油交易额
				income2 = Constants.change(oilrecord2.getIncome() == null ? 0 : oilrecord1.getIncome())
						.toString();
				// 本月交易次数
				String id2 = Constants.change(oilrecord2.getId() == null ? 0 : oilrecord1.getId()).toString();
				model.addAttribute("income2", income2);
				model.addAttribute("id2", id2);
			} else {
				model.addAttribute("income2", 0);
				model.addAttribute("id2", 0);
			}
			ExtractApply apply2 = zhandianSumService.findOilSumDrawMonth2(gas.getId());
			if (apply2 != null) {
				// 燃油站点查询当月燃油可提取額
				String amountDrawn = Constants.change(apply2.getAmountDrawn() == null ? 0 : apply2.getAmountDrawn()*ptt.getUnitConversion())
						.toString();
				String jian2= Constants.change((oilrecord2.getIncome() == null ? 0 : oilrecord1.getIncome())-(apply2.getAmountDrawn() == null ? 0 : apply2.getAmountDrawn()*ptt.getUnitConversion())).toString();
				model.addAttribute("amountDrawn2", amountDrawn);
				model.addAttribute("jian2", jian2);
			} else {
				model.addAttribute("amountDrawn2", 0);
				model.addAttribute("jian2", income2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("ptt", ptt);
		return "zd";
	}

	@RequestMapping(value = "/MonthOilInfo")
	@ResponseBody
	public Object MonthOilInfo(Oilrecord oilrecord, Model model, HttpServletRequest request) {
		EchartsGasstationMonth ecMonth = new EchartsGasstationMonth();
		// 得到当前时间的天数
		Date date2 = new Date();
		SimpleDateFormat format = new SimpleDateFormat("d");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》9
		int day = Integer.parseInt(curdate);
		HttpSession session = request.getSession();
		Gasstation gas = (Gasstation) session.getAttribute(Constants.DEV_USER_SESSION);
		// 查询本月燃油交易额
		/*
		 * List<Oilrecord> oilder6 = osm.findOilSum1(oilrecord, gas.getId(), 1);
		 * List<Oilrecord> oilder66 = osm.findOilSum1(oilrecord, gas.getId(),1);
		 * 
		 * // 集合大小 !=当前天数，进行匹配 if (oilder6.size() != day) { List<Oilrecord>
		 * list666 = Constants.muchDataIsOk(oilder6); for (int i = 0; i <
		 * list666.size(); i++) { Oilrecord oilrecord1 = new Oilrecord();
		 * oilrecord1.setOperationTime(list666.get(i).getOperationTime());
		 * oilrecord1.setRemaining(list666.get(i).getRemaining());
		 * oilrecord1.setIncome(list666.get(i).getIncome());
		 * oilder66.add(oilrecord1); } oilder66.addAll(oilder6); }
		 * 
		 * Collections.sort(oilder66, new ComparatorTest());
		 * ecMonth.setOilder6(oilder66);
		 */

		// 燃油站点首页交易量
		List<Oilrecord> oilder7 = osm.findOilSum1(oilrecord, gas.getId(), 1);
		List<Oilrecord> oilder77 = osm.findOilSum1(oilrecord, gas.getId(), 1);
		if (oilder7.size() != day) {
			List<Oilrecord> list777 = Constants.muchDataIsOk(oilder7);
			for (int i = 0; i < list777.size(); i++) {
				// e.amountDrawn as remaining,o.operationTime as operationTime
				Oilrecord oilrecord1 = new Oilrecord();
				oilrecord1.setOperationTime(list777.get(i).getOperationTime());
				oilrecord1.setRemaining(list777.get(i).getRemaining());
				oilrecord1.setId(list777.get(i).getId());
				oilrecord1.setIncome(list777.get(i).getIncome());
				oilder77.add(oilrecord1);
			}
			oilder77.addAll(oilder7);
		}

		Collections.sort(oilder77, new ComparatorTest());
		ecMonth.setOilder7(oilder77);
		// 燃油站点首页燃油可提取量
		/*
		 * List<Oilrecord> oilder8 = osm.findOilSum1(oilrecord, gas.getId(), 1);
		 * List<Oilrecord> oilder88 = osm.findOilSum1(oilrecord, gas.getId(),
		 * 1);
		 * 
		 * if (oilder8.size() != day) { List<Oilrecord> list888 =
		 * Constants.muchDataIsOk(oilder8); for (int i = 0; i < list888.size();
		 * i++) { Oilrecord oilrecord1 = new Oilrecord();
		 * oilrecord1.setOperationTime(list888.get(i).getOperationTime());
		 * oilrecord1.setRemaining(list888.get(i).getRemaining());
		 * oilder88.add(oilrecord1); } oilder88.addAll(oilder8); }
		 * 
		 * Collections.sort(oilder88, new ComparatorTest());
		 * ecMonth.setOilder8(oilder88);
		 */

		return JSONArray.toJSONString(ecMonth);
	}

	@RequestMapping(value = "/YearOilInfo")
	@ResponseBody
	public String YearOilInfo(Oilrecord oilrecord, Model model, HttpServletRequest request) {
		EchartsGasstationYear ecYear = new EchartsGasstationYear();
		// 得到当前时间的月份
		Date date2 = new Date();
		SimpleDateFormat format = new SimpleDateFormat("M");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》9
		int day = Integer.parseInt(curdate);
		HttpSession session = request.getSession();
		Gasstation gas = (Gasstation) session.getAttribute(Constants.DEV_USER_SESSION);
		// 首页查询年度燃油总交易额
		List<Oilrecord> OilYear1 = osm.findOilSumYear(oilrecord, gas.getId(), 1);
		List<Oilrecord> OilYear11 = osm.findOilSumYear(oilrecord, gas.getId(), 1);
		if (OilYear1.size() != day) {
			List<Oilrecord> OilYear111 = Constants.yearChangeOilrecordsinfos(OilYear1);
			for (int i = 0; i < OilYear111.size(); i++) {
				Oilrecord oilrecord1 = new Oilrecord();
				oilrecord1.setId(OilYear111.get(i).getId());
				oilrecord1.setIncome(OilYear111.get(i).getIncome());
				oilrecord1.setOperationTime(OilYear111.get(i).getOperationTime());
				oilrecord1.setRemaining(OilYear111.get(i).getRemaining());
				OilYear11.add(oilrecord1);
			}
			OilYear11.addAll(OilYear1);
		}
		Collections.sort(OilYear11, new ComparatorTest());
		ecYear.setOilder1(OilYear11);

		/*
		 * // 首页查询年度燃油总交易量 List<Oilrecord> OilYear2 =
		 * osm.findOilSumYear(oilrecord, gas.getId(), 1); List<Oilrecord>
		 * OilYear22 = osm.findOilSumYear(oilrecord, gas.getId(), 1); if
		 * (OilYear2.size() != day) { List<Oilrecord> OilYear222 =
		 * Constants.yearChangeOilrecordsinfos(OilYear2); for (int i = 0; i <
		 * OilYear222.size(); i++) { Oilrecord oilrecord1 = new Oilrecord();
		 * oilrecord1.setId(OilYear222.get(i).getId());
		 * oilrecord1.setOperationTime(OilYear222.get(i).getOperationTime());
		 * oilrecord1.setRemaining(OilYear222.get(i).getRemaining());
		 * OilYear22.add(oilrecord1); } OilYear22.addAll(OilYear2); }
		 * Collections.sort(OilYear22, new ComparatorTest());
		 * ecYear.setOilder2(OilYear22);
		 * 
		 * // 燃油站点首页全年了提取额 List<Oilrecord> OilYear3 =
		 * osm.findOilSumYear1(oilrecord, gas.getId(), 1); List<Oilrecord>
		 * OilYear33 = osm.findOilSumYear1(oilrecord, gas.getId(), 1); if
		 * (OilYear3.size() != day) { List<Oilrecord> OilYear333 =
		 * Constants.yearChangeOilrecordsinfos(OilYear3); for (int i = 0; i <
		 * OilYear333.size(); i++) { Oilrecord oilrecord1 = new Oilrecord();
		 * oilrecord1.setId(OilYear333.get(i).getId());
		 * oilrecord1.setRemaining(OilYear333.get(i).getRemaining()); oilrecord1
		 * .setOperationTime(OilYear333.get(i).getOperationTime());
		 * OilYear33.add(oilrecord1); } OilYear33.addAll(OilYear3); }
		 * Collections.sort(OilYear33, new ComparatorTest());
		 * ecYear.setOilder3(OilYear33);
		 */

		return JSONArray.toJSONString(ecYear);
	}

	@RequestMapping("gosao")
	@ResponseBody
	public Object saoyisaoParam(@RequestParam("gid") String gid, @RequestParam("gsType") String gsType) {
		JSONObject jsonObject = new JSONObject();
		if (gid != null || gid != "" || gsType != null && gsType != "") {
			try {
				Gasstation gasstation = oilrecordServiceMapper.getGasstationBy_idAndType(gid, gsType);
				jsonObject.put("msg", 1);
				jsonObject.put("gasstation", gasstation);
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("msg", -1);
			}
		}
		return jsonObject;
	}
}