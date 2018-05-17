package cn.zhaojisys.service.app;



import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Vipuserinfo;

public interface AddOilService {
	/**
	 * 车主加油
	 * 
	 * @param id
	 * @param oilMass
	 * @return
	 */
	public JSONObject vipAddoil(Integer sessionId, Integer gid,Integer preAddOil,String phone,Double money) throws Exception;

	// 查询会员信息
	public Vipuserinfo getVipinfo(Integer vid) throws Exception;

	// 查询站点信息
	public Gasstation getGassinfo(Integer gid) throws Exception;
	
}
