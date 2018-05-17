package cn.zhaojisys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.user.SumMapper;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.pojo.VipuserinfoModel;
import cn.zhaojisys.service.SumService;
@Service
public class SumServiceImpl implements SumService{
	@Autowired SumMapper sm;
	@Override
	public Oilrecord findOil(Oilrecord oilrecord) {
		// TODO Auto-generated method stub
		return sm.findOil(oilrecord);
	}


	@Override
	public Tyredatails findTyre(Tyredatails tyredatails) {
		
		return sm.findTyre(tyredatails);
	}


	@Override
	public int numGasstation(int gsType) {
		// TODO Auto-generated method stub
		return sm.numGasstation(gsType);
	}


	@Override
	public int numEmp() {
		// TODO Auto-generated method stub
		return sm.numEmp();
	}


	@Override
	public int sumOil() {
		// TODO Auto-generated method stub
		return sm.sumOil();
	}


	@Override
	public int sumTyre() {
		// TODO Auto-generated method stub
		return sm.sumTyre();
	}


	@Override
	public int sumOilTyreDraw(int businessType, int status) {
		// TODO Auto-generated method stub
		return sm.sumOilTyreDraw(businessType, status);
	}


	@Override
	public int sumOilTyreStation(int gsType) {
		// TODO Auto-generated method stub
		return sm.sumOilTyreStation(gsType);
	}


	@Override
	public int sumVipUser(int userType) {
		// TODO Auto-generated method stub
		return sm.sumVipUser(userType);
	}


	@Override
	public List<Tyredatails> benyue() {
		// TODO Auto-generated method stub
		return sm.benyue();
	}


	@Override
	public List<Oilrecord> oilMonSum() {
		// TODO Auto-generated method stub
		return sm.oilMonSum();
	}


	@Override
	public List<Oilrecord> oilDraWMonSum() {
		// TODO Auto-generated method stub
		return sm.oilDraWMonSum();
	}


	@Override
	public List<Tyredatails> tyreDraWMonSum() {
		// TODO Auto-generated method stub
		return sm.tyreDraWMonSum();
	}


	@Override
	public List<VipuserinfoModel> newVipNum() {
		// TODO Auto-generated method stub
		return sm.newVipNum();
	}


	@Override
	public List<Oilrecord> oilSumDealNum() {
		// TODO Auto-generated method stub
		return sm.oilSumDealNum();
	}


	@Override
	public List<Tyredatails> tyreSumDealNum() {
		// TODO Auto-generated method stub
		return sm.tyreSumDealNum();
	}


	@Override
	public List<Tyredatails> yearTyreSum() {
		// TODO Auto-generated method stub
		return sm.yearTyreSum();
	}


	@Override
	public List<Oilrecord> yearOilMonSum() {
		// TODO Auto-generated method stub
		return sm.yearOilMonSum();
	}


	@Override
	public List<Oilrecord> yearOilDraWMonSum() {
		// TODO Auto-generated method stub
		return sm.yearOilDraWMonSum();
	}


	@Override
	public List<Tyredatails> yearTyreDraWMonSum() {
		// TODO Auto-generated method stub
		return sm.yearTyreDraWMonSum();
	}


	@Override
	public List<Vipuserinfo> yearNewVipNum() {
		// TODO Auto-generated method stub
		return sm.yearNewVipNum();
	}


	@Override
	public List<Oilrecord> yearOilSumDealNum() {
		// TODO Auto-generated method stub
		return sm.yearOilSumDealNum();
	}


	@Override
	public List<Tyredatails> yearTyreSumDealNum() {
		// TODO Auto-generated method stub
		return sm.yearTyreSumDealNum();
	}


	public Integer chong() {
		return sm.chong();
	}
	public Integer tiqu() {
		// TODO Auto-generated method stub
		return sm.tiqu();
	}
	public Integer tiqu2() {
		// TODO Auto-generated method stub
		return sm.tiqu2();
	}


	@Override
	public Integer getSumQuota() {
		return sm.getSumQuota();
	}



	
}
