package cn.zhaojisys.service.app;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Shop;

public interface GasstationAppService {

	/**
	 * 根据轮胎站点登陆id，查询对应轮胎列表
	 * @param gId
	 * @return
	 */
	public List<Shop> shopList(@Param("gId")Integer gId,
								Integer currentPageNo,
								Integer pageSize)throws Exception;
}
