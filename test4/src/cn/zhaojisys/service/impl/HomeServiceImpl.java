package cn.zhaojisys.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.gg.HomeMapper;
import cn.zhaojisys.pojo.Paramatersetting;
import cn.zhaojisys.service.GasstationService;
import cn.zhaojisys.service.HomeService;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.vo.HomeVo;

@Service
public class HomeServiceImpl implements HomeService{

	@Autowired
	private HomeMapper homeMapper;
	@Resource
	private GasstationService gasstationService;

	@Override
	public HomeVo getSum() throws Exception {
		HomeVo homeVo=new HomeVo();
		Paramatersetting ptt=null;
		ptt=gasstationService.getParamatersettingList(); 
		String completeQuantity=homeMapper.completeQuantity();
		homeVo.setCompleteQuantity(completeQuantity==null?"0":Constants.change(completeQuantity));
		
		String tiresMoney=homeMapper.tiresMoney();
		homeVo.setTiresMoney(tiresMoney==null?"0":Constants.change(tiresMoney));
		
		String oilExtraction=homeMapper.oilExtraction();
		if(oilExtraction!=null){
			Integer aInteger=Integer.parseInt(oilExtraction)*ptt.getUnitConversion();
			String a=String.valueOf(aInteger);
			String b=Constants.change(a);
			homeVo.setOilExtraction(b);
			//homeVo.setOilExtraction(Integer.toString(Integer.parseInt(oilExtraction)*ptt.getUnitConversion())==null?"0":Constants.change(Integer.toString(Integer.parseInt(oilExtraction)*ptt.getUnitConversion())));
		}else{
			homeVo.setOilExtraction("0");
		}
		
		String extract=homeMapper.extract();
		homeVo.setExtract(extract==null?"0":Constants.change(extract));
		
		String fuelSite=homeMapper.fuelSite();
		homeVo.setFuelSite(fuelSite==null?"0":Constants.change(fuelSite));
		
		String tyreSite=homeMapper.tyreSite();
		homeVo.setTyreSite(tyreSite==null?"0":Constants.change(tyreSite));
		
		String newAddVip=homeMapper.newAddVip();
		homeVo.setNewAddVip(newAddVip==null?"0":Constants.change(newAddVip));
		
		String czTotal=homeMapper.czTotal();
		homeVo.setCzTotal(czTotal==null?"0":Constants.change(czTotal));
		
		String comTotal=homeMapper.comTotal();
		homeVo.setComTotal(comTotal==null?"0":Constants.change(comTotal));
		
		if (czTotal==null) {
			czTotal="0";
		}
		if (comTotal==null) {
			comTotal="0";
		}
		String nocomTotal=String.valueOf(Integer.parseInt(czTotal)-Integer.parseInt(comTotal));
		homeVo.setNocomTotal(nocomTotal);
		
		String tqTotal=homeMapper.tqTotal();
		if(tqTotal!=null){
			homeVo.setTqTotal(Constants.change(String.valueOf(Integer.parseInt(tqTotal)*ptt.getUnitConversion())));
			//homeVo.setOilExtraction(Integer.toString(Integer.parseInt(oilExtraction)*ptt.getUnitConversion())==null?"0":Constants.change(Integer.toString(Integer.parseInt(oilExtraction)*ptt.getUnitConversion())));
		}else{
			homeVo.setTqTotal("0");
		}
		//homeVo.setTqTotal(Integer.toString(Integer.parseInt(tqTotal)*ptt.getUnitConversion())==null?"0":Constants.change(Integer.toString(Integer.parseInt(tqTotal)*ptt.getUnitConversion())));
		
		String wtqTotal=homeMapper.wtqTotal();
		homeVo.setWtqTotal(wtqTotal==null?"0":Constants.change(wtqTotal));
		
		String dtqTotal=homeMapper.dtqTotal();
		if(dtqTotal!=null){
			homeVo.setDtqTotal(Constants.change(String.valueOf(Integer.parseInt(dtqTotal)*ptt.getUnitConversion())));
			//homeVo.setOilExtraction(Integer.toString(Integer.parseInt(oilExtraction)*ptt.getUnitConversion())==null?"0":Constants.change(Integer.toString(Integer.parseInt(oilExtraction)*ptt.getUnitConversion())));
		}else{
			homeVo.setDtqTotal("0");
		}
		//homeVo.setDtqTotal(dtqTotal==null?"0":Constants.change(dtqTotal));
		
		String serviceTotal=homeMapper.serviceTotal();
		homeVo.setServiceTotal(serviceTotal==null?"0":Constants.change(serviceTotal));
		
		String vipTotal=homeMapper.vipTotal();
		homeVo.setVipTotal(vipTotal==null?"0":Constants.change(vipTotal));
		
		String logisticCompany=homeMapper.logisticCompany();
		homeVo.setLogisticCompany(logisticCompany==null?"0":Constants.change(logisticCompany));
		
		String persinoal=homeMapper.persinoal();
		homeVo.setPersinoal(persinoal==null?"0":Constants.change(persinoal));
		
		String ltMoney=homeMapper.ltMoney();
		homeVo.setLtMoney(ltMoney==null?"0":Constants.change(ltMoney));
		
		String lttqMoney=homeMapper.lttqMoney();
		homeVo.setLttqMoney(lttqMoney==null?"0":Constants.change(lttqMoney));
		
		String ltdtqMoney=homeMapper.ltdtqMoney();
		homeVo.setLtdtqMoney(ltdtqMoney==null?"0":Constants.change(ltdtqMoney));
		
		String ltzdNum=homeMapper.ltzdNum();
		homeVo.setLtzdNum(ltzdNum==null?"0":Constants.change(ltzdNum));
		
		
		
		
		
		return homeVo;
	}
	
	
	
}
