package cn.zhaojisys.service.app.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import cn.zhaojisys.dao.app.RelationShipList;
import cn.zhaojisys.pojo.Messages;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.RelationShip;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.push.PushMsgToTag;
import cn.zhaojisys.service.app.RelationShipListService;
import cn.zhaojisys.tools.Constants;



@Service
public class RelationShipListServiceImpl implements RelationShipListService {
	@Autowired
	RelationShipList resList;

	@Override
	public Vipuserinfo relaShipList(String phoneNum) throws Exception {
		return resList.relaShipList(phoneNum);
	}

	@Override
	public List<Vipuserinfo> friendList(int id) throws Exception {
		return resList.friendList(id);
	}

	@Override
	public int addFriend(int vId, int vipId) throws Exception {
		return resList.addFriend(vId, vipId);
	}

	@Override
	public int breakFriend(int vId, int vipId) throws Exception {
		return resList.breakFriend(vId, vipId);
	}

	@Override
	public Vipuserinfo vipInfoList(int id) throws Exception {
		return resList.vipInfoList(id);
	}

	@Override
	public JSONObject giveOil(int OilQuantity, HttpServletRequest request,
			int ChooseId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		int OilMass = 0;
		int vipId=0;
		Vipuserinfo vipu=(Vipuserinfo)request.getSession().getAttribute(Constants.VIPUSERINFO);
		// 通过方法获得当前登录用户的剩余油量
		OilMass = resList.vipInfoList(vipu.getId()).getOilMass();
		
		if (OilQuantity > 0 && OilMass > 0) {
			// 判断被选分配好友燃油记录表中的最后一次购买时间
			Oilrecord oil = resList.isExistRecord(ChooseId);
			Date date1=null;
			if (oil!=null) {
				vipId=oil.getVipUserId();//获取最大操作时间的会员id 
				//日期String转Date
				String date2=oil.getOperationTime();			
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				date1 = formatter.parse(date2);
			}
			
			/*//查询燃油记录表前十分钟是否有交易记录的发起交易人的手机号码
			PhoneNum=resList.isExistRecordFriendid(Integer.parseInt(vipu.getPhoneNum()));*/
			// 得到分钟			
			long minutes = 10;
			if (date1 != null) {
				minutes=((new Date()).getTime()-date1.getTime())/1000/60;				
			}			
			// 判断10分钟之内如果没有交易记录，执行分配操作
			if (minutes >= 10&&vipId!=vipu.getId()) {

				if (OilQuantity <= OilMass) {
					HttpSession session = request.getSession();
					Vipuserinfo vipuserinfo = (Vipuserinfo) session
							.getAttribute(Constants.VIPUSERINFO);
					Vipuserinfo vipInfo1;
					//获取当前时间
					Date date =new Date();
					SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time=sp.format(date);
					vipInfo1 = resList.vipInfoList(ChooseId);// 查询被选择好友的信息
					//查询被选择好友账户的状态，如果是禁用状态，则无法分配！
					if(vipInfo1.getStateTag()!=1){
					if (vipuserinfo.getId()!= ChooseId) {
						// 更新当前用户的油量（-）
						int a = OilMass - OilQuantity;
						int b = resList.giveOil(a, vipuserinfo.getId());
						// 更新被分配者的油量（+）
						int c = OilQuantity + vipInfo1.getOilMass();
						int d = resList.giveOil(c, ChooseId);
						if (b > 0 && d > 0) {
							//分配油量往消息表插入两条数据
							Messages messages1=new Messages();//分配好友的消息对象
							Messages messages2=new Messages();//被分配好友的消息对象
							messages1.setMsgType(1);
							messages1.setContent("您于"+time+"赠送给您的好友"+vipInfo1.getUserName()+"("+vipInfo1.getPhoneNum()+")油量"+OilQuantity+"L");// 以赠者为主    插入消息   
							messages1.setOperationTime(time);
							messages1.setVipId(vipuserinfo.getId());//取得当前登录用户的id
							//被分配好友的message
							messages2.setMsgType(1);
							messages2.setContent("您的好友"+vipuserinfo.getUserName()+"于"+time+"赠送给您"+OilQuantity+"L"+"请及时查收");
							messages2.setOperationTime(time);
							messages2.setVipId(vipInfo1.getId());//取得被赠者的id
							//分配油量往消息表中添加两条记录】 
							int aa=resList.giveOilRecordMessage(messages1);
							int bb=resList.giveOilRecordMessage(messages2);
							//分配油量往燃油记录表插入两条数据
							Oilrecord oilrecord1=new Oilrecord();
							Oilrecord oilrecord2=new Oilrecord();
							oilrecord1.setGsId(0);
							oilrecord1.setOperationTime(time);
							oilrecord1.setVipUserId(vipuserinfo.getId());//vipuserinfo.getId()
							oilrecord1.setIncome(0);
							oilrecord1.setExpenditure(OilQuantity);
							oilrecord1.setRemaining(a);
							oilrecord1.setOperationType(1);
							oilrecord1.setFriendId(vipInfo1.getPhoneNum());
							//被分配好友的燃油记录
							oilrecord2.setGsId(0);
							oilrecord2.setOperationTime(time);
							oilrecord2.setVipUserId(ChooseId);//vipuserinfo.getId()
							oilrecord2.setIncome(OilQuantity);
							oilrecord2.setExpenditure(0);
							oilrecord2.setRemaining(c);
							oilrecord2.setOperationType(1);
							oilrecord2.setFriendId(vipuserinfo.getPhoneNum());//vipuserinfo.getPhoneNum()
							//分配油量往燃油记录表中添加两条记录
							int cc=resList.giveOilRecord(oilrecord1);
							int dd=resList.giveOilRecord(oilrecord2);
							if (aa>0 && bb>0 && cc>0 && dd>0) {
								jsonObject.put("msg", "分配成功！");
								jsonObject.put("msg", "1");
								String only=vipInfo1.getPhoneNum();//对应唯一一台设备
								JSONObject object=new JSONObject();
								String usreName=vipu.getUserName();//名字
								String busNum=vipu.getBusNum();//车牌号
								String allMessage="赠送给您："+OilQuantity+" L油 "+time;
								object.put("title", "赠送油量："+"好友("+usreName+":"+busNum+")");
								object.put("description", allMessage);
								PushMsgToTag.pushMsgToTag(only, 1, null, object.toJSONString(), 3);
								
								
							}else {
								jsonObject.put("msg", "-1");
							}
							
						} else {
							jsonObject.put("msg", "-1");// 没有添加成功的异常
						}
					} else {
						jsonObject.put("msg", "-2");//无法给自己分配油量！

					}
					}else{
						jsonObject.put("msg", "-5");//该好友账户已被禁用，无法分配！
					}

					return jsonObject;
				} else {
					jsonObject.put("msg", "-3");//油量不足，无法分配！
				}
			} else {
				jsonObject.put("msg", "-4");//10分钟内已有交易记录
			}
		}
		return jsonObject;

	}

	@Override
	public List<Messages> findMsg(Integer vipId,Integer msgType,Integer pageNo)
			throws Exception {
		// TODO Auto-generated method stub
		List<Messages> list = null;
		list = resList.findMsg(vipId,msgType,pageNo);
		return list;
	}

	@Override
	public List<Oilrecord> saleRecord(int vipUserId) throws Exception {
		// TODO Auto-generated method stub
		return resList.saleRecord(vipUserId);
	}

	@Override
	public List<RelationShip> isExistRelationShip(int vId, int vipId) throws Exception {
		// TODO Auto-generated method stub
		return resList.isExistRelationShip(vId, vipId);
	}

	

}
