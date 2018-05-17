package cn.zhaojisys.service.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;


import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.pojo.Messages;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.RelationShip;
import cn.zhaojisys.pojo.Vipuserinfo;

public interface RelationShipListService {
	// 好友检索功能接口
	Vipuserinfo relaShipList(String phoneNum)
			throws Exception;

	// 我的好友列表接口
	List<Vipuserinfo> friendList(int id) throws Exception;

	// 添加好友接口
	int addFriend(int vId,int vipId)
			throws Exception;

	// 删除好友接口
	int breakFriend( int vId,int vipId)
			throws Exception;
	//查询会员信息接口
	Vipuserinfo vipInfoList(int id) throws Exception;
	//油量分配接口
	JSONObject giveOil(int OilQuantity,HttpServletRequest request,int ChooseId) 
					throws Exception;
	//消息查询接口
	List<Messages> findMsg(Integer vipId,Integer msgType,Integer pageNo) throws Exception;
	
	//根据id查询消费记录
	List<Oilrecord> saleRecord(@Param("vipUserId") int vipUserId) throws Exception;
	//查询好友关系是否已存在
	List<RelationShip> isExistRelationShip(@Param("vId") int vId,@Param("vipId") int vipId) throws Exception;
}
