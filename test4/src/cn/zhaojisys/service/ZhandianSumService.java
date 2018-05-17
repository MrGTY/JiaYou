package cn.zhaojisys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.vo.ZhandianSumVo;

public interface ZhandianSumService {

	/**
	 * 燃油站点查询当月燃油交易额，交易量,可提取額
	 * @param gsId  站点id
	 * @return
	 * @throws Exception
	 */
	public Oilrecord findOilSum(@Param("gsId")Integer gsId)throws Exception;
	
	/**
	 * 燃油站点查询当月燃油可提取額
	 * @param gsId
	 * @return
	 * @throws Exception
	 */
	public ExtractApply findOilSumDrawMonth(@Param("gsId")Integer gsId)throws Exception;
	
	/**
	 * 燃油站点查询总燃油交易额，交易量,可提取額
	 * @param gsId
	 * @return
	 * @throws Exception
	 */
	public Oilrecord findOilSum2(@Param("gsId")Integer gsId)throws Exception;
	
	/**
	 * 燃油站点查询总燃油可提取額 
	 * @param gsId
	 * @return
	 * @throws Exception
	 */
	public ExtractApply findOilSumDrawMonth2(@Param("gsId")Integer gsId)throws Exception;
	
	/**
	 * 月交易统计
	 * @param gsId
	 * @return
	 * @throws Exception
	 */
	public ZhandianSumVo sumVosMounth(Integer gsId)throws Exception;
	/**
	 * 月交易统计
	 * @param gsId
	 * @return
	 * @throws Exception
	 */
	public ZhandianSumVo sumVosYear(Integer gsId)throws Exception;
	
}
