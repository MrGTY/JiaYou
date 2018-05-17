package cn.zhaojisys.dao.app;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.OilModel;
import cn.zhaojisys.pojo.Oilrecord;
/**
 * 
 * @author 李杰
 *
 */
public interface OilrecordAppMapper {

	/**
	 * 根据当前登录用户id查询加油记录
	 * @param gsId
	 * @return
	 */
	public List<OilModel> listOilrecordBy_gsId(@Param("gsId")Integer gsId,
												@Param("currentPageNo")Integer currentPageNo,
												@Param("pageSize")Integer pageSize)throws Exception;
	/**
	 * 根据当前登录用户id查询加油数量
	 * @param id
	 * @return
	 */
	public int countOilrecordBy_gsId(@Param("vipUserId")Integer vipUserId)throws Exception;
	
}
