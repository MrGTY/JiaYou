package cn.zhaojisys.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.app.ApppMapper;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.app.ApppService;
@Service
public class ApppServiceImpl implements ApppService {

	@Autowired
	ApppMapper appmapper;

	// 交易信息接口--首页
	@Override
	public List<Oilrecord> Information(Integer id) throws Exception {
		return appmapper.Information(id);
	}

	// 账号密码登录
	@Override
	public Vipuserinfo login(String phoneNum, String password)
			throws Exception {
		return appmapper.login(phoneNum, password);
	}

	// 短信验证码登录
	@Override
	public Vipuserinfo loginphone(String phoneNum)throws Exception {
		return appmapper.loginphone(phoneNum);
	}

	//数据统计接口
	@Override
	public Oilrecord statistics(Integer id) throws Exception {
		return appmapper.statistics(id);
	}

	// 注册接口
	@Override
	public int addVipuserinfo(String userName, String vipName, String phoneNum, String password, String busNum,
			String createTime,String uploadImg) throws Exception {
		return appmapper.addVipuserinfo(userName, vipName, phoneNum, password, busNum, createTime,uploadImg);
	}

	//查询登录类型
	@Override
	public Gasstation loginType(String code, String password) throws Exception {
		return appmapper.loginType(code, password);
	}

	// 查询短信验证码登录类型
	@Override
	public Gasstation loginType2(String mobilePhone) throws Exception {
		return appmapper.loginType2(mobilePhone);
	}
	
	//首页
	@Override
	public Vipuserinfo HomePage(Integer id) throws Exception {
		return appmapper.HomePage(id);
	}
	
	//我的账户
	@Override
	public Vipuserinfo uName(Integer id) throws Exception {
		return appmapper.uName(id);
	}
	
	//个人中心
	@Override
	public Vipuserinfo geren(Integer id) throws Exception {
		return appmapper.geren(id);
	}
	
	
	
	//商户帐户
	@Override
	public Gasstation gName(Integer id) throws Exception {
		return appmapper.gName(id);
	}
	//个人中心物理车辆
	@Override
	public int gerencheliang(Integer id) throws Exception {
		return appmapper.gerencheliang(id);
	}
	//个人中心我的车辆
	@Override
	public int vipCheLiang(Integer id) throws Exception {
		return appmapper.vipCheLiang(id);
	}

	@Override
	public int vipCheLiang2(Integer id) throws Exception {
		return appmapper.vipCheLiang2(id);
	}

	
	
}
