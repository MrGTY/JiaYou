package cn.zhaojisys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhaojisys.dao.appversion.AppMapper;
import cn.zhaojisys.pojo.AppVersion;
import cn.zhaojisys.service.AppService;

@Service
public class AppServiceImpl implements AppService {

	@Autowired
	AppMapper appMapper;

	// 查询所有版本信息
	@Override
	public List<AppVersion> selectAllApp() throws Exception {
		return appMapper.selectAllApp();
	}

	// 上传版本信息
	@Override
	public void addApp(AppVersion appVersion) throws Exception {
		appMapper.addApp(appVersion);
	}

	// 删除版本信息 
	@Override
	public void deleteApp(String id) throws Exception {
		appMapper.deleteApp(id);
	}

	// 根据Id选择
	@Override
	public AppVersion selectAllAppById(String id) throws Exception {
		return appMapper.selectAllAppById(id);
	}

}
