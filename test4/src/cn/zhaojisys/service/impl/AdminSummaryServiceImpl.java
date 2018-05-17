package cn.zhaojisys.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.zhaojisys.dao.gg.AdminSummaryMapper;
import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.AdminSummaryService;
import cn.zhaojisys.tools.ComparatorTest;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.TyreDatailsCompare;
import cn.zhaojisys.tools.VipUser;
import cn.zhaojisys.tools.VipUserModel;
import cn.zhaojisys.tools.ZhandianSumCompare;
import cn.zhaojisys.vo.AdminSummaryVo;

@Service
public class AdminSummaryServiceImpl implements AdminSummaryService {

	@Autowired
	private AdminSummaryMapper adminSummaryMapper;

	@Override
	public AdminSummaryVo mounth() throws Exception {
		AdminSummaryVo summaryVo=new AdminSummaryVo();
		int day=AdminSummaryServiceImpl.ab();
		// 燃油总收油量 和次数
		List<Oilrecord> oilList1 = adminSummaryMapper.oilList1();
		List<Oilrecord> oilList11 = new ArrayList<Oilrecord>();
		oilList11.addAll(oilList1);
		if (day!=oilList1.size()) {
			List<Oilrecord> oilList111=Constants.muchDataIsOk(oilList1);
			oilList11.addAll(oilList111);
		}
		Collections.sort(oilList11, new ComparatorTest());
		summaryVo.setOilList1(oilList11);
		//轮胎总收油量 和次数
		 List<Tyredatails> trilList1=adminSummaryMapper.trilList1();
		 List<Tyredatails> trilList11=new ArrayList<Tyredatails>();
		 trilList11.addAll(trilList1);
		if (day!=trilList11.size()) {
			List<Tyredatails> trilList111=Constants.changeTyredatails(trilList11);
			trilList11.addAll(trilList111);
		}
		trilList11.addAll(trilList1);
		Collections.sort(trilList11, new TyreDatailsCompare());
		summaryVo.setTrilList1(trilList11);
		
		//燃油提取
		List<ExtractApply> oilextractapply1=adminSummaryMapper.oilextractapply1();
		List<ExtractApply> oilextractapply11=new ArrayList<ExtractApply>();
		oilextractapply11.addAll(oilextractapply1);
		if (day!=oilextractapply11.size()) {
			List<ExtractApply> oilextractapply111=Constants.muchDataIsOkExtractApply(oilextractapply11);
			oilextractapply11.addAll(oilextractapply111);
		}
		oilextractapply11.addAll(oilextractapply1);
		Collections.sort(oilextractapply11,new ZhandianSumCompare());
		summaryVo.setOilextractapply1(oilextractapply11);
		//轮胎提取
		List<ExtractApply> tractapply1=adminSummaryMapper.tractapply1();
		List<ExtractApply> tractapply11=new ArrayList<ExtractApply>();
		tractapply11.addAll(tractapply1);
		if (day!=tractapply11.size()) {
			List<ExtractApply> tractapply111=Constants.muchDataIsOkExtractApply(tractapply11);
			tractapply11.addAll(tractapply111);
		}
		tractapply11.addAll(tractapply1);
		Collections.sort(tractapply11,new ZhandianSumCompare());
		summaryVo.setTractapply1(tractapply11);
		//会员个数
		List<Vipuserinfo> vipConut1=adminSummaryMapper.vipConut();
		List<Vipuserinfo> vipConut11=new ArrayList<Vipuserinfo>();
		vipConut11.addAll(vipConut1);
		if (day!=vipConut1.size()) {
			List<Vipuserinfo> vipConut111=Constants.changeVipuserinfos(vipConut11);
			vipConut11.addAll(vipConut111);
		}
		vipConut11.addAll(vipConut1);
		Collections.sort(vipConut11, new VipUser());
		summaryVo.setVipConut(vipConut11);
		return summaryVo;
	}

	
	private static int ab() {
		// 得到当前时间的天数
		Date date2 = new Date();
		SimpleDateFormat format = new SimpleDateFormat("d");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》9
		int day = Integer.parseInt(curdate);
		return day;

	}


	@Override
	public AdminSummaryVo year() throws Exception {
		AdminSummaryVo summaryVo=new AdminSummaryVo();
		int day=AdminSummaryServiceImpl.ab();
		//燃油总收油量 和次数
		List<Oilrecord> yearOilClosedTotal=adminSummaryMapper.yearOilClosedTotal();
		List<Oilrecord> yearOilClosedTotal1=new ArrayList<Oilrecord>();
		yearOilClosedTotal1.addAll(yearOilClosedTotal);
		if (day!=yearOilClosedTotal1.size()) {
			List<Oilrecord> yearOilClosedTotal11=Constants.yearMuchDataIsOk(yearOilClosedTotal1);
			yearOilClosedTotal1.addAll(yearOilClosedTotal11);
		}
		yearOilClosedTotal1.addAll(yearOilClosedTotal);
		Collections.sort(yearOilClosedTotal1, new ComparatorTest());
		summaryVo.setYearOilClosedTotal(yearOilClosedTotal1);
		
		
		
		
		//轮胎总收油量 和次数
		List<Tyredatails> yearOilextractTotal=adminSummaryMapper.yearOilextractTotal();
		List<Tyredatails> yearOilextractTotal1=new ArrayList<Tyredatails>();
		yearOilextractTotal1.addAll(yearOilextractTotal);
		if (day!=yearOilextractTotal1.size()) {
			List<Tyredatails> yearOilextractTotal11=Constants.yearChangeTyredatails(yearOilextractTotal1);
			yearOilextractTotal.addAll(yearOilextractTotal11);
		}
		yearOilextractTotal1.addAll(yearOilextractTotal);
		Collections.sort(yearOilextractTotal, new TyreDatailsCompare());
		summaryVo.setYearOilextractTotal(yearOilextractTotal);
		
		//燃油提取
		List<ExtractApply> yearOilTradingCount=adminSummaryMapper.yearOilTradingCount();
		List<ExtractApply> yearOilTradingCount1=new ArrayList<ExtractApply>();
		yearOilTradingCount1.addAll(yearOilTradingCount);
		if (day!=yearOilTradingCount1.size()) {
			List<ExtractApply> yearOilTradingCount11=Constants.yearMuchDataIsOkExtractApply(yearOilTradingCount1);
			yearOilTradingCount1.addAll(yearOilTradingCount11);
		}
		yearOilTradingCount1.addAll(yearOilTradingCount);
		Collections.sort(yearOilTradingCount1,new ZhandianSumCompare());
		summaryVo.setYearOilTradingCount(yearOilTradingCount1);
		//轮胎提取
		List<ExtractApply> yeartireCount=adminSummaryMapper.yeartireCount();
		List<ExtractApply> yeartireCount1=new ArrayList<ExtractApply>();
		yeartireCount1.addAll(yeartireCount);
		if (day!=yeartireCount1.size()) {
			List<ExtractApply> yeartireCount11=Constants.yearMuchDataIsOkExtractApply(yeartireCount1);
			yeartireCount1.addAll(yeartireCount11);
		}
		yeartireCount1.addAll(yeartireCount);
		Collections.sort(yeartireCount1,new ZhandianSumCompare());
		summaryVo.setYeartireCount(yeartireCount1);
		//会员个数
		List<Vipuserinfo> yeartireextractCount=adminSummaryMapper.yeartireextractCount();
		List<Vipuserinfo> yeartireextractCount1=new ArrayList<Vipuserinfo>();
		yeartireextractCount1.addAll(yeartireextractCount);
		if (day!=yeartireextractCount1.size()) {
			List<Vipuserinfo> yeartireextractCount11=Constants.yearChangeVipuserinfos(yeartireextractCount1);
			yeartireextractCount1.addAll(yeartireextractCount11);
		}
		yeartireextractCount1.addAll(yeartireextractCount);
		Collections.sort(yeartireextractCount1, new VipUser());
		summaryVo.setYeartireextractCount(yeartireextractCount1);
		return summaryVo;
	}

}
