package cn.zhaojisys.dao.app;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import cn.zhaojisys.pojo.AppVersion;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Vipuserinfo;

public interface ApppMapper {
	// 账号密码登录
	public Vipuserinfo login(@Param("phoneNum") String phoneNum,
			@Param("password") String password) throws Exception;
	
	//查询帐号密码登录类型
	public Gasstation loginType(@Param("code") String code,
			@Param("password") String password) throws Exception;
	
	// 短信验证码登录
	public Vipuserinfo loginphone(@Param("phoneNum") String phoneNum)throws Exception;
	
	//查询短信验证码登录类型
	public Gasstation loginType2(@Param("mobilePhone") String mobilePhone) throws Exception;
	
	// 交易信息接口--首页
	public List<Oilrecord> Information(@Param("id") Integer id)throws Exception;
	
	//记住密码接口
	
	//注册接口
	public int addVipuserinfo(@Param("userName")String userName,
			@Param("vipName")String vipName,
			@Param("phoneNum")String phoneNum,
			@Param("password")String password,
			@Param("busNum")String busNum,
			@Param("createTime")String createTime,
			@Param("uploadImg")String uploadImg)throws Exception;
	
	//数据统计接口
	public Oilrecord statistics(@Param("id")Integer id)throws Exception;
	
	//版本查询接口
	public AppVersion Version(@Param("id")Integer id)throws Exception;
	
	//首页
	public Vipuserinfo HomePage(@Param("id") Integer id)throws Exception;
	
	//我的账户
	public Vipuserinfo uName(@Param("id") Integer id)throws Exception;
	
	//个人中心
	public Vipuserinfo geren(@Param("id") Integer id)throws Exception;
	
	//个人中心物流车辆
		public int gerencheliang(@Param("id") Integer id)throws Exception;
		//个人中心我的车辆
		public int vipCheLiang(@Param("id") Integer id)throws Exception;
		public int vipCheLiang2(@Param("id") Integer id)throws Exception;
	//商户帐户
	public Gasstation gName(@Param("id") Integer id)throws Exception;
}
