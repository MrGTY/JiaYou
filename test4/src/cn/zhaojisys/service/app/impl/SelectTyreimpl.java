package cn.zhaojisys.service.app.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.app.SelectTyreMapper;
import cn.zhaojisys.dao.gasstation.GasstationMapper;
import cn.zhaojisys.pojo.Feetable;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Shop;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.app.SelectTyre;
import cn.zhaojisys.tools.Constants;

@Service("selectTyreimpl")
public class SelectTyreimpl implements SelectTyre{

	@Resource
	SelectTyreMapper mapper=null;
	@Resource
	private GasstationMapper gasstationMapper;
	
	@Override
	public List<Tyredatails> getTyrelist(Integer id,Integer pageNo,Integer pageSize)throws Exception {
		
		return mapper.getTyrelist(id,pageNo,pageSize);
	}

	@Override
	public List<Oilrecord> getOilrelist(Integer id,Integer pageNo,Integer pageSize)throws Exception {
		
		return mapper.getOilrelist(id,pageNo,pageSize);
	}

	@Override
	public List<Gasstation> getGassOlist(Integer type,Integer pageNo,Integer pageSize)throws Exception {
		
		return mapper.getGassOlist(type,pageNo,pageSize);
	}

	@Override
	public List<Gasstation> getGassTlist(Integer type,Integer pageNo,Integer pageSize) throws Exception {
		
		return mapper.getGassTlist(type,pageNo,pageSize);
	}

	@Override
	public List<Tyredatails> getStyrelist(Integer id) throws Exception {
		
		return mapper.getStyrelist(id);
	}

	@Override
	public Shop getTyreinfo(Integer id) throws Exception {
	
		return mapper.getTyreinfo(id);
	}

	@Override
	public Vipuserinfo getVipinfo(Integer vid) throws Exception {
		
		return mapper.getVipinfo(vid);
	}

	@Override
	public Gasstation getGassinfo(Integer gid) throws Exception {
		
		return mapper.getGassinfo(gid);
	}

	@Override
	public int updatePwd(String password, String mobile) throws Exception {
		
		return mapper.updatePwd(password, mobile);
	}

	@Override
	public int insertinfo(Integer msgType, String content,
			String operationTime, Integer vipId,Integer gsId) throws Exception {
		
		return mapper.insertinfo(msgType, content, operationTime, vipId ,gsId);
	}

	@Override
	public Vipuserinfo selectVip(String mobile) throws Exception {
		
		return mapper.selectVip(mobile);
	}
	public Gasstation getQrCodeImg(Integer gid) throws Exception {
		return mapper.getQrCodeImg(gid);
	}
	@Override
	public int shupdatePwd(String password, String mobile) throws Exception {
		
		return mapper.shupdatePwd(password, mobile);
	}

	@Override
	public Gasstation selectGass(String mobile) throws Exception {
		
		return mapper.selectGass(mobile);
	}
	public List<Oilrecord> getfee(Integer gid) throws Exception {
		
		return mapper.getfee(gid);
	}

	@Override
	public Map<String,Object> feeAdd(String idc, Integer gassid, Double fee) throws Exception {
		Map<String,Object> map1=new HashMap<String,Object>();
		Map<String,Object> jsonObject=new HashMap<String,Object>();
		Feetable f=new Feetable();
		f.setCreatetime(new Date());
		f.setGassid(gassid);
		f.setIdc(idc);
		f.setChargefee(fee);
		f.setState(1);
		Gasstation gass=gasstationMapper.getGasstationById(gassid);
		//判断站点是否禁用
		if(gass.getStatus()==0){
			jsonObject.put("msg",2);
			return jsonObject;
		}
		double oilmass=fee/gass.getCharge();
		f.setOilmass((int)oilmass);
		if(mapper.feeAdd(f)>0){
			map1.put("idc", Constants.splitString(idc));
			if(mapper.updateBalance(map1)>0){
				jsonObject.put("msg",1);
				return jsonObject;
			}
			jsonObject.put("msg",-1);
			return jsonObject;
		}
		jsonObject.put("msg",-1);
		return jsonObject;
	}
	
}
