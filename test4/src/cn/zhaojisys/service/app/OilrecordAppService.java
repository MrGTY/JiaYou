package cn.zhaojisys.service.app;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.OilModel;
import cn.zhaojisys.pojo.Oilrecord;

public interface OilrecordAppService {

	/**
	 * 根据当前登录用户id查询加油记录
	 * @param gsId
	 * @return
	 */
	public List<OilModel> listOilrecordBy_gsId(Integer id,Integer currentPageNo,
												Integer pageSize)throws Exception;
	/**
	 * 根据当前登录用户id查询加油数量
	 * @param id
	 * @return
	 */
	public int countOilrecordBy_gsId(Integer id)throws Exception;
}
