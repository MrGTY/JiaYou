package cn.zhaojisys.dao.app;


import java.util.Date;
import java.util.List;


import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Messages;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.RelationShip;
import cn.zhaojisys.pojo.Vipuserinfo;

public interface RelationShipList {
	//消息查询接口
	List<Messages> findMsg(@Param("vipId")Integer vipId,
			@Param("msgType")Integer msgType,
			@Param("pageNo")Integer pageNo) throws Exception;
	// 好友检索功能接口
	Vipuserinfo relaShipList(@Param("phoneNum")String phoneNum)
			throws Exception;

	// 我的好友列表接口
		List<Vipuserinfo> friendList(int id) throws Exception;

	// 添加好友接口
	int addFriend(@Param("vId")int vId,@Param("vipId")int vipId)
			throws Exception;

	// 删除好友接口
	int breakFriend( @Param("vId")int vId,@Param("vipId")int vipId)
			throws Exception;
	
	
	//查询会员信息接口
	Vipuserinfo vipInfoList(int id) throws Exception;
	//油量分配接口
	int giveOil(@Param("OilQuantity")int OilQuantity,@Param("ChooseId")int ChooseId) 
					throws Exception;
	//查询燃油记录表前十分钟是否有交易记录接口
	Oilrecord isExistRecord(int id) throws Exception;
	//分配油量往消息表中添加两条记录
	int giveOilRecordMessage(Messages messages) throws Exception ;
	//分配油量往燃油记录表中添加两条记录
	int giveOilRecord(Oilrecord oilrecord) throws Exception;
	
	//根据id查询消费记录
	List<Oilrecord> saleRecord(@Param("vipUserId") int vipUserId) throws Exception;
	//查询好友关系是否已存在
	List<RelationShip> isExistRelationShip(@Param("vId") int vId,@Param("vipId") int vipId) throws Exception;
	
}
