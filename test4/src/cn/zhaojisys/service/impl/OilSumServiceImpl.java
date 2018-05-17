package cn.zhaojisys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.user.OilSumMapper;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.service.OilSumService;
@Service
public class OilSumServiceImpl implements OilSumService {
	@Autowired OilSumMapper oilSum;

	@Override
	public Oilrecord findOilSum(Oilrecord oilrecord, Integer gsId,
			Integer status) {
		// TODO Auto-generated method stub
		return oilSum.findOilSum(oilrecord, gsId, status);
	}

	@Override
	public Oilrecord findOilSumMonth(Oilrecord oilrecord, Integer gsId,
			Integer status) {
		// TODO Auto-generated method stub
		return oilSum.findOilSumMonth(oilrecord, gsId, status);
	}

	@Override
	public List<Oilrecord> findOilSum1(Oilrecord oilrecord, Integer gsId,
			Integer status) {
		// TODO Auto-generated method stub
		return oilSum.findOilSum1(oilrecord, gsId, status);
	}

	@Override
	public List<Oilrecord> findOilSumMonth1(Oilrecord oilrecord, Integer gsId,
			Integer status) {
		// TODO Auto-generated method stub
		return oilSum.findOilSumMonth1(oilrecord, gsId, status);
	}

	@Override
	public List<Oilrecord> findOilSumYear(Oilrecord oilrecord, Integer gsId,
			Integer status) {
		// TODO Auto-generated method stub
		return oilSum.findOilSumYear(oilrecord, gsId, status);
	}

	@Override
	public List<Oilrecord> findOilSumYear1(Oilrecord oilrecord, Integer gsId,
			Integer status) {
		// TODO Auto-generated method stub
		return oilSum.findOilSumYear1(oilrecord, gsId, status);
	}

	


}
