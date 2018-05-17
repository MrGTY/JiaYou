package cn.zhaojisys.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.ZhandianSum.ZhandianSumMapper;
import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.service.ZhandianSumService;
import cn.zhaojisys.tools.ComparatorTest;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.ZhandianSumCompare;
import cn.zhaojisys.vo.ZhandianSumVo;

@Service
public class ZhandianSumServiceImpl implements ZhandianSumService {

	@Autowired
	private ZhandianSumMapper zhandianSumMapper;

	@Override
	public Oilrecord findOilSum(Integer gsId) throws Exception {
		return zhandianSumMapper.findOilSum(gsId);
	}

	@Override
	public ExtractApply findOilSumDrawMonth(Integer gsId) throws Exception {
		return zhandianSumMapper.findOilSumDrawMonth(gsId);
	}

	@Override
	public Oilrecord findOilSum2(Integer gsId) throws Exception {
		return zhandianSumMapper.findOilSum2(gsId);
	}

	@Override
	public ExtractApply findOilSumDrawMonth2(Integer gsId) throws Exception {
		return zhandianSumMapper.findOilSumDrawMonth2(gsId);
	}

	@Override
	public ZhandianSumVo sumVosMounth(Integer gsId) throws Exception {
		// 得到当前时间的天数
		Date date2 = new Date();
		SimpleDateFormat format = new SimpleDateFormat("d");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》9
		int day = Integer.parseInt(curdate);

		ZhandianSumVo sumVo = new ZhandianSumVo();
		List<Oilrecord> list1 = zhandianSumMapper.findOilSum3(gsId);
		List<Oilrecord> list11 = new ArrayList<Oilrecord>();
		for (int i = 0; i < list1.size(); i++) {
			Oilrecord oilrecord = new Oilrecord();
			oilrecord.setIncome(list1.get(i).getIncome());
			oilrecord.setId(list1.get(i).getId());
			oilrecord.setOperationTime(list1.get(i).getOperationTime());
			list11.add(oilrecord);
		}
		List<Oilrecord> list111 = Constants.muchDataIsOk(list1);

		for (int i = 0; i < list111.size(); i++) {
			Oilrecord oilrecord = new Oilrecord();
			oilrecord.setIncome(list111.get(i).getIncome());
			oilrecord.setId(list111.get(i).getId());
			oilrecord.setOperationTime(list111.get(i).getOperationTime());
			list11.add(oilrecord);
		}
		Collections.sort(list11, new ComparatorTest());
		sumVo.setOilrecords1(list11);
		List<ExtractApply> list2 = zhandianSumMapper.findOilSumDrawMonth3(gsId);
		List<ExtractApply> list22 = new ArrayList<ExtractApply>();
		for (int i = 0; i < list2.size(); i++) {
			ExtractApply apply = new ExtractApply();
			apply.setAmountDrawn(list2.get(i).getAmountDrawn());
			apply.setApproveData(list2.get(i).getApproveData());
			list22.add(apply);
		}
		List<ExtractApply> list222 = Constants.muchDataIsOkExtractApply(list2);

		for (int i = 0; i < list222.size(); i++) {
			ExtractApply oilrecord = new ExtractApply();
			oilrecord.setAmountDrawn(list222.get(i).getAmountDrawn());
			oilrecord.setApproveData(list222.get(i).getApproveData());
			list22.add(oilrecord);
		}
		Collections.sort(list22, new ZhandianSumCompare());
		sumVo.setApplies1(list22);
		return sumVo;
	}

	@Override
	public ZhandianSumVo sumVosYear(Integer gsId) throws Exception {
		ZhandianSumVo sumVo = new ZhandianSumVo();
		// 得到当前时间的天数
		Date date2 = new Date();
		SimpleDateFormat format = new SimpleDateFormat("d");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》9
		int day = Integer.parseInt(curdate);
		List<Oilrecord> list1 = zhandianSumMapper.findOilSum4(gsId);
		List<Oilrecord> list11 = new ArrayList<Oilrecord>();
		for (int i = 0; i < list1.size(); i++) {
			Oilrecord oilrecord = new Oilrecord();
			oilrecord.setIncome(list1.get(i).getIncome());
			oilrecord.setId(list1.get(i).getId());
			oilrecord.setOperationTime(list1.get(i).getOperationTime());
			list11.add(oilrecord);
		}
		List<Oilrecord> list111 = Constants.yearMuchDataIsOk(list1);

		for (int i = 0; i < list111.size(); i++) {
			Oilrecord oilrecord = new Oilrecord();
			oilrecord.setIncome(list111.get(i).getIncome());
			oilrecord.setId(list111.get(i).getId());
			oilrecord.setOperationTime(list111.get(i).getOperationTime());
			list11.add(oilrecord);

		}
		Collections.sort(list11, new ComparatorTest());
		sumVo.setOilrecords2(list11);

		List<ExtractApply> list2 = zhandianSumMapper.findOilSumDrawMonth4(gsId);
		List<ExtractApply> list22 = new ArrayList<ExtractApply>();
		for (int i = 0; i < list2.size(); i++) {
			ExtractApply apply = new ExtractApply();
			apply.setAmountDrawn(list2.get(i).getAmountDrawn());
			apply.setApproveData(list2.get(i).getApproveData());
			list22.add(apply);
		}
		List<ExtractApply> list222 = Constants.yearMuchDataIsOkExtractApply(list2);

		for (int i = 0; i < list222.size(); i++) {
			ExtractApply oilrecord = new ExtractApply();
			oilrecord.setAmountDrawn(list222.get(i).getAmountDrawn());
			oilrecord.setApproveData(list222.get(i).getApproveData());
			list22.add(oilrecord);
		}
		Collections.sort(list22, new ZhandianSumCompare());
		sumVo.setApplies2(list22);

		return sumVo;
	}

}
