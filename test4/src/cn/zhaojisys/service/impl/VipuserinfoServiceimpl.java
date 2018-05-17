package cn.zhaojisys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.gasstation.GasstationMapper;
import cn.zhaojisys.dao.vipuserinfo.VipuserinfoMapper;
import cn.zhaojisys.pojo.EmployeInfo;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Gathering;
import cn.zhaojisys.pojo.Messages;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.VipuserinfoService;
import cn.zhaojisys.tools.Constants;


@Service("VipuserinfoServiceimpl")
public class VipuserinfoServiceimpl implements VipuserinfoService {
	
	@Resource
	VipuserinfoMapper mapper=null;
	
	@Resource
	private GasstationMapper gasstationMapper;
		
	//按条件查询用户信息
	@Override
	public List<Vipuserinfo> selectVipbyCondition(String vipName,
								String userName, String busNum, Integer userType,String mycarId,Integer pageNo, Integer pageSize)throws Exception {
		return mapper.getAllbyCondition(vipName, userName, busNum, userType,mycarId,pageNo,pageSize);
	}
	
	//根据id查询用户所信息
	@Override
	public Vipuserinfo selectAllbyId(Integer id) throws Exception {
			return mapper.getAllbyId(id);
	}
	//分页查找总计路数
	@Override
	public int selectCount(String vipName,
						   String userName, String busNum, Integer userType,String mycarId)throws Exception {
		return mapper.getAllcount(vipName,userName,busNum,userType,mycarId);
	}


	//根据id修改会员信息
	@Override
	public int updataUser(Integer id, Integer stateTag, Integer oilMass,
			Integer tyreBalance, String password) throws Exception {
		return mapper.updataUser(id, stateTag, oilMass, tyreBalance, password);
	}

	//根据id删除用户
	@Override
	public int deleteUser(Integer id) throws Exception {
		return mapper.deleteUser(id);
	}

	//查询燃油收支明细
	@Override
	public List<Oilrecord> getOilrecord(Integer paymentType,
			Integer operationType, String startTime, String endTime,Integer id,Integer pageNo, Integer pageSize)
			throws Exception {
		return mapper.getOilrelist(paymentType, operationType, startTime, endTime,id,pageNo,pageSize);
	}

	//查询站点名称
		@Override
		public Oilrecord getName(Integer id) throws Exception {
			return mapper.getName(id);
		}
	
	//查询燃油收支明细总记录数
	@Override
	public int getoilCount(Integer paymentType, Integer operationType,
			String startTime, String endTime, Integer id) throws Exception {
		return mapper.getoilCount(paymentType, operationType, startTime, endTime, id);
	}

	//查询轮胎收支明细
	@Override
	public List<Tyredatails> getTyrerelist(Integer paymentType, String startTime,
			String endTime, Integer id, Integer pageNo, Integer pageSize)
			throws Exception {
		return mapper.getTyrerelist(paymentType, startTime, endTime, id, pageNo, pageSize);
	}

	//查询轮胎收支明细总记录数
	@Override
	public int getTyreCount(Integer paymentType, String startTime,
			String endTime, Integer id) throws Exception {
		return mapper.getTyreCount(paymentType, startTime, endTime, id);
	}

	//修改密码
	@Override
	public int updatepwd(Integer id, String password) throws Exception {
		
		return mapper.updatepwd(id, password);
	}
	//改变状态
	@Override
	public int changetype(Integer id, Integer userType,String remark,Integer logicId,String mycarId) throws Exception {
		return mapper.changetype(id, userType,remark,logicId,mycarId);
	}

	//查询收款记录明细
	@Override
	public List<Gathering> getCollist(Integer id, String vipName,
			String startTime, String endTime, Integer pageNo, Integer pageSize)
			throws Exception {
		
		return mapper.getCollist(id, vipName, startTime, endTime, pageNo, pageSize);
	}
	//查询收款记录明细总记录数
	@Override
	public int getcollCount(Integer id, String vipName, String startTime,
			String endTime) throws Exception {
		
		return mapper.getcollCount(id, vipName, startTime, endTime);
	}

	//插入收款记录
	@Override
	public int skRecord1(Integer vipId, Integer empId, Integer type,
			Integer Payamount, String remark, String createTime,Integer ljqk)
			throws Exception {
		
		return mapper.skRecord(vipId, empId, type, Payamount, remark, createTime,ljqk,null);
	}

	//更新欠款金额
	@Override
	public int qkupdate(Integer qkBalance, Integer id) throws Exception {
		
		return mapper.qkupdate(qkBalance, id);
	}
	
	//更新欠款金额
	@Override
	public Map<String, String> getGathering(Integer id, Integer oiltype, Integer Payamount,
			String remark, HttpSession session) throws Exception {
		Vipuserinfo vipuserinfo=null;	
		Map<String, String> map=new HashMap<String, String>();
		
			//获取当前会员欠款金额
			vipuserinfo=mapper.getAllbyId(id);
			Integer arrears=vipuserinfo.getQkBalance(); //当前欠款
			if(oiltype==null||Payamount==null){
				map.put("mas","failure");
			}else if(arrears<Payamount){
				map.put("mas", "greater");
			}else if(Payamount<0){
				map.put("mas", "positive");
			}else{
			arrears= arrears-Payamount;
			//更新当前用户欠款金额	
			int num1=mapper.qkupdate(arrears, id);
			if(num1<0){
				map.put("mas", "error");
			}
			
			//在收款记录中插入记录
			Date today=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime=df.format(today);
			Integer empId=((EmployeInfo)session.getAttribute(Constants.USER_SESSION)).getId();
			vipuserinfo=mapper.getAllbyId(id);			
			int num=mapper.skRecord(id, empId, oiltype, Payamount, remark, createTime,vipuserinfo.getQkBalance(),null);
			if(num<0){
				map.put("mas", "error");
				}else{	
					map.put("qk", "剩余欠款"+vipuserinfo.getQkBalance());
//					throw new RuntimeException("!!!!");
				}
			}
		return map;
	}

	//保存燃油记录
	@Override
	public int oilinsert(Integer id, String operationTime, Integer moneyname,
			Integer oilname, Integer options, String type) throws Exception {
		return mapper.oilinsert(id, operationTime, moneyname, oilname, options);
	}

	//保存轮胎记录
	@Override
	public int tyerinsert(Integer id, String operationData, Integer moneyname,
			Integer balance, Integer options, String type) throws Exception {
		return mapper.tyerinsert(id, operationData, moneyname, balance, options, type);
	}


	//充值交易
	@Override
	public Map<String, String> getRecharge(Integer id, Integer moneyname,
			Integer oilname, Integer options, Integer optionsRad, String remark, HttpSession session)
			throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		Vipuserinfo vipuserinfo=null;
		vipuserinfo=mapper.getAllbyId(id);
		//获取当前燃油余量
		int oldOil=vipuserinfo.getOilMass();
		//获取当前欠款
		int oldQk=vipuserinfo.getQkBalance();
		//获取当前轮胎余额
		int oldtyer=vipuserinfo.getTyreBalance();
		int newOil=0;
		int newQk=0;
		int newtyer=0;
		if(id==null){
			map.put("mas", "failure");
		}else if((moneyname==null&&oilname==null)||options==null||optionsRad==null){
			map.put("mas", "choose");
		}else{	
			if(oilname==null){  //更新燃油余量
				newOil=oldOil;
			}else{	
				newOil=oldOil+oilname;
			}if(moneyname==null){  
				newtyer=oldtyer;	//更新轮胎余额
			}else{
				newtyer=oldtyer+moneyname;
			}
			if(optionsRad==1&&moneyname!=null){		//更新当前欠款
				newQk=oldQk+moneyname;
			}else{
				newQk=oldQk;
			}
		//更新用户燃油或轮胎余额
		mapper.czupdate(id, options, optionsRad, newQk, newOil, remark,newtyer);
		//获取当前事件
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String operationTime=df.format(new Date());
		Integer empId=((EmployeInfo)session.getAttribute(Constants.USER_SESSION)).getId();
		if(options==0){
			if(oilname==null||moneyname==null){
				map.put("mas", "choose");
			}else if(oilname<0||moneyname<0){
				map.put("mas", "positive");
			}else{
				//向燃油记录表中插入记录
				mapper.oilinsert(id, operationTime, oilname, newOil, options);
				mapper.skRecord(id, empId, options, moneyname, remark, operationTime, newQk,oilname);	
				map.put("mas","success");
			}
		}else{
		//向轮胎记录表中插入记录
			if(moneyname==null){
				map.put("mas", "choose");
			}else if(moneyname<0){
				map.put("mas", "positive");
			}else{
				mapper.tyerinsert(id, operationTime, moneyname, newtyer, options, "召集平台充值");
				mapper.skRecord(id,empId, options, moneyname, remark, operationTime, newQk,null);
				map.put("mas","success");
				}
			}	
		}
		return map;
	}
	@Override
	public List<Vipuserinfo> getVipuserinfoByName(String name) throws Exception {
		return mapper.getVipuserinfoByName(name);
	}
	public List<Gasstation> getGasstationByName(String name) throws Exception {
		return mapper.getGasstationByName(name);
	}
	//好友分配
	public boolean getFenPei(HttpServletRequest request,String vipname1,String vipname2,Integer sheng) throws Exception {
		Vipuserinfo v1=null;
		Vipuserinfo v2=null;
		Oilrecord o1=new Oilrecord();
		Oilrecord o2=new Oilrecord();
		Messages m1=new Messages();
		Messages m2=new Messages();
		Integer yu1=null;
		Integer yu2=null;
		if(mapper.getVipuserinfoByNameCount(vipname1)>0){
			v1=mapper.getNewVipuserinfoByName(vipname1);
			if(v1.getOilMass()<sheng){
				request.setAttribute("messgae", "余额油量不足");
				return false;
			}else if(v1.getStateTag()==1){
				request.setAttribute("messgae", "该分配会员账户禁用中");
				return false;
			}
		}else{
			request.setAttribute("messgae", "没有该分配会员用户");
			return false;
		}
		if(mapper.getVipuserinfoByNameCount(vipname2)>0){
			v2=mapper.getNewVipuserinfoByName(vipname2);
			if(v2.getStateTag()==1){
				request.setAttribute("messgae", "该收油会员账户禁用中");
				return false;
			}
		}else{
			request.setAttribute("messgae", "没有该收油会员用户");
			return false;
		}
		if(vipname1.equals(vipname2)){
			request.setAttribute("messgae", "收油会员不能是自己");
			return false;
		}
		if(mapper.updateVipuserinfoOilMassJian(v1.getId(), sheng)>0){
			if(mapper.updateVipuserinfoOilMassJia(v2.getId(), sheng)>0){
				String date=Constants.getNewDate();
				//消息记录
				o1.setOperationTime(date);
				o1.setVipUserId(v1.getId());
				o1.setIncome(0);
				o1.setExpenditure(sheng);
				yu1=v1.getOilMass()-sheng;
				o1.setRemaining(yu1);
				o1.setOperationType(1);
				o1.setFriendId(v2.getPhoneNum());
				gasstationMapper.addOilrecord(o1);
				//消息推送
				m1.setMsgType(1);//1为分配
				String content1="您于"+date+"赠送给您的好友"+v2.getPhoneNum()+" "+v2.getUserName()+"油量"+sheng+"L";
				m1.setContent(content1);
				m1.setOperationTime(date);
				m1.setVipId(v1.getId());
				gasstationMapper.addMessages(m1);
				//消息记录
				o2.setOperationTime(date);
				o2.setVipUserId(v2.getId());
				yu2=v2.getOilMass()+sheng;
				o2.setIncome(sheng);
				o2.setExpenditure(0);
				o2.setRemaining(yu2);
				o2.setOperationType(1);
				o2.setFriendId(v1.getPhoneNum());
				gasstationMapper.addOilrecord(o2);
				//消息推送
				m2.setMsgType(1);
				String content2="您的好友"+v1.getPhoneNum()+" "+v1.getUserName()+"于"+date+"赠送给您"+sheng+"L,"+"请及时查收！";
				m2.setContent(content2);
				m2.setOperationTime(date);
				m2.setVipId(v2.getId());
				gasstationMapper.addMessages(m2);
				//int i=1/0;
				return true;
			}
		}
		request.setAttribute("messgae", "交易异常");
		return false;
	}
	//加油站充油
	public boolean getJiaYou(HttpServletRequest request,String vipname1,String vipname2,Integer sheng) throws Exception {
		Vipuserinfo v1=null;
		Oilrecord o1=new Oilrecord();
		Oilrecord o2=new Oilrecord();
		Messages m1=new Messages();
		Messages m2=new Messages();
		Integer yu1=null;
		Integer yu2=null;
		//String a=Constants.getValueByString(vipname2);
		//System.out.println(a);
		Gasstation v2=mapper.getNewGasstationByName(Constants.getValueByString(vipname2));
		if(mapper.getVipuserinfoByNameCount(vipname1)>0){
			v1=mapper.getNewVipuserinfoByName(vipname1);
			if(v1.getOilMass()<sheng){
				request.setAttribute("messgae", "余额油量不足");
				return false;
			}if(v1.getStateTag()==1){
				request.setAttribute("messgae", "该充油会员账户禁用中");
				return false;
			}
		}else{
			request.setAttribute("messgae", "没有该分配会员用户");
			return false;
		}
		if(null==v2){
			request.setAttribute("messgae", "没有该站点");
			return false;
		}
		if(v2.getStatus()==0){
			request.setAttribute("messgae", "该站点目前被禁用中，无法交易");
			return false;
		}
		if(mapper.updateVipuserinfoOilMassJian(v1.getId(), sheng)>0){
			if(mapper.updateGasstationQuota(v2.getId(), sheng)>0){
				String date=Constants.getNewDate();
				Double fuwufei=sheng*v2.getCharge();
				//消息记录
				o1.setOperationTime(date);
				o1.setGsId(v2.getId());
				o1.setVipUserId(v1.getId());
				o1.setIncome(0);
				o1.setExpenditure(sheng);
				yu1=v1.getOilMass()-sheng;
				o1.setRemaining(yu1);
				o1.setOperationType(2);
				o1.setFriendId("");
				o1.setFee(fuwufei);
				gasstationMapper.addOilrecord(o1);
				//消息推送
				m1.setMsgType(2);//2为消费
				String content1="您于"+date+"获得"+v2.getSiteName()+"油量充值"+sheng+"L"+"请及时查收！";
				m1.setContent(content1);
				m1.setOperationTime(date);
				m1.setVipId(v1.getId());
				gasstationMapper.addMessages(m1);
				//消息记录
				o2.setGsId(v2.getId());
				o2.setOperationTime(date);
				o2.setVipUserId(v1.getId());
				yu2=v2.getQuota()+sheng;
				o2.setIncome(sheng);
				o2.setExpenditure(0);
				o2.setRemaining(yu2);
				o2.setOperationType(2);
				o2.setFriendId("");
				o2.setInfotype(2);//加油时插2
				o2.setBalance(0);
				o2.setFee(fuwufei);
				gasstationMapper.addOilrecord(o2);
				//消息推送
				m2.setMsgType(2);
				String content2=v2.getSiteName()+"于"+date+"为会员"+v1.getPhoneNum()+"充值油量"+sheng+"L";
				m2.setContent(content2);
				m2.setOperationTime(date);
				m2.setGsId(v2.getId());
				gasstationMapper.addMessages(m2);
				//int i=1/0;
				return true;
			}
		}
		request.setAttribute("messgae", "交易异常");
		return false;
	}

	@Override
	public List<Vipuserinfo> getNewVipByUserType(String name) throws Exception {
		return mapper.getNewVipByUserType(name);
	}

	@Override
	public Vipuserinfo getVipuserinfoBylogicId(Integer logicId)
			throws Exception {
		return mapper.getVipuserinfoBylogicId(logicId);
	}

	@Override
	public int addVip(Vipuserinfo vipuserinfo) throws Exception {
		
		return mapper.addVip(vipuserinfo);
	}

	@Override
	public int updateVip(Vipuserinfo vipuserinfo) throws Exception {
		
		return mapper.updateVip(vipuserinfo);
	}

	
	}
