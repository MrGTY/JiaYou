package cn.zhaojisys.dao.appversion;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.AppVersion;

public interface AppMapper {
	// 查询所有版本信息
	public List<AppVersion> selectAllApp() throws Exception;

	// 上传版本信息 
	public void addApp(AppVersion appVersion) throws Exception;

	// 删除版本信息 
	public void deleteApp(@Param("id") String id) throws Exception;
	
	// 根据Id选择
	public AppVersion selectAllAppById(@Param("id") String id)
			throws Exception;
}
