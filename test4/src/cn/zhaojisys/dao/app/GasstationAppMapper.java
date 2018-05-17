package cn.zhaojisys.dao.app;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Shop;

/**
 * 
 * @author 李杰
 *
 */
public interface GasstationAppMapper {

	/**
	 * 根据轮胎站点登陆id，查询对应轮胎列表
	 * @param gId
	 * @return
	 */
	public List<Shop> shopList(@Param("gId")Integer gId,
			@Param("currentPageNo")Integer currentPageNo,
			@Param("pageSize")Integer pageSize)throws Exception;
}
