package cn.zhaojisys.service.app.impl;


import static org.hamcrest.CoreMatchers.nullValue;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.yun.push.model.PushMsgToTagResponse;

import cn.zhaojisys.dao.app.AddOilMapper;
import cn.zhaojisys.dao.app.RelationShipList;
import cn.zhaojisys.dao.vipuserinfo.VipuserinfoMapper;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Messages;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.push.PushMsgToTag;
import cn.zhaojisys.service.app.AddOilService;
import cn.zhaojisys.tools.Constants;

@Service
public class AddOilServiceImpl implements AddOilService {

	@Autowired
	private AddOilMapper addOilMapper;
	
	@Autowired
	private VipuserinfoMapper vipuserinfoMapper;
	@Autowired
	private RelationShipList relationShipList;
	
	@Override
	public Vipuserinfo getVipinfo(Integer vid) throws Exception {

		return addOilMapper.getVipinfo(vid);
	}

	@Override
	public Gasstation getGassinfo(Integer gid) throws Exception {

		return addOilMapper.getGassinfo(gid);
	}

	@Override
	public JSONObject vipAddoil(Integer sessionId, Integer gid, Integer preAddOil,String ph,Double money) throws Exception {
		Vipuserinfo vipuserinfo = null;
		JSONObject jsonObject = new JSONObject();
		if (preAddOil < 0) {
			jsonObject.put("msg", "-1");
			return jsonObject;
		}
		
		Gasstation gasstation=addOilMapper.getGassinfo(gid);
		if (gasstation.getStatus()==0) {//禁用
			jsonObject.put("msg", "-100");
			return jsonObject;
		}
		if (gasstation.getLogicId()==0) {//删除
			jsonObject.put("msg", "-200");
			return jsonObject;
		}
		vipuserinfo = addOilMapper.getVipinfo(sessionId);
		Integer oilMass = vipuserinfo.getOilMass() - preAddOil;// 剩余油量-预加油量
		
		Date date1=null;
		//最后一次购买时间
		Oilrecord oilrecord = addOilMapper.lastMaxBuyshopBy_Id(sessionId);
		if(oilrecord!=null){
			if (oilrecord.getOperationTime()!=null) {
				SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				date1=fmt.parse(oilrecord.getOperationTime());
			}
		}
		// 得到分钟
		long minutes = 10;
		if (date1 != null) {
			minutes=((new Date()).getTime()-date1.getTime())/1000/60;
		}
		if (oilMass < 0) {
			jsonObject.put("msg", "-1");
			return jsonObject;
		}/*else if(minutes<10){ 
			jsonObject.put("msg", "-10");
			return jsonObject;
		}*/else {
			Oilrecord vip = new Oilrecord();// 会员加油记录
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			vip.setGsId(gid);// 站点id
			vip.setOperationTime(format.format(date));
			vip.setVipUserId(sessionId);// 会员id
			vip.setIncome(0);// 收入（L）
			vip.setExpenditure(preAddOil);// 支出（L）
			vip.setRemaining(oilMass);// 剩余油量（L）
			vip.setOperationType(2);
			vip.setFriendId("");
			vip.setFee(money);
			
			Oilrecord zd = new Oilrecord();// 站点加油记录
			zd.setGsId(gid);// 站点id
			zd.setOperationTime(vip.getOperationTime());
			zd.setVipUserId(sessionId);// 会员id
			zd.setIncome(preAddOil);// 收入（L）
			zd.setExpenditure(0);// 支出（L）
			zd.setRemaining(0);// 剩余油量（L）
			zd.setOperationType(2);
			zd.setFriendId("");
			zd.setInfotype(2);
			vip.setFee(money);
			vip.setBalance(0);
			
			
			Messages messages=new Messages();
			 messages.setMsgType(2);
			 messages.setOperationTime(vip.getOperationTime());
			 messages.setGsId(gid);
			 messages.setVipId(sessionId);
			 messages.setContent("您在"+gasstation.getSiteName()+"完成:"+preAddOil+"L");
			int count1 = addOilMapper.vipAddoil(sessionId, oilMass);
			int count2 = addOilMapper.addOilHistory(vip);
			int count3 = addOilMapper.addOilHistory(zd);
			int count4= vipuserinfoMapper.updateGasstationQuota(gid, preAddOil);
			int count5=relationShipList.giveOilRecordMessage(messages);
			if (count1 > 0 && count2 > 0 && count3 > 0 && count4>0 && count5>0) {
				jsonObject.put("msg", "1");
				String only=gasstation.getMobilePhone();//对应唯一一台设备
				JSONObject object=new JSONObject();
				String usreName=vipuserinfo.getUserName();//名字
				String busNum=vipuserinfo.getBusNum();//车牌号
				String allMessage="加油： "+preAddOil+"L "+vip.getOperationTime();
				object.put("title", "加油:"+usreName+"("+busNum+")");
				object.put("description", allMessage);
				PushMsgToTag.pushMsgToTag(only, 1, null, object.toJSONString(), 3);
				return jsonObject;
			} else {
				jsonObject.put("msg", "-1");
				return jsonObject;
			}

		}
	}
}
