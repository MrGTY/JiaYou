package cn.zhaojisys.dao.ZhandianSum;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Oilrecord;

public interface ZhandianSumMapper {

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
	 * 燃油站点查询每天燃油交易额，交易量,可提取額 
	 * @param gsId
	 * @return
	 * @throws Exception
	 */
	public List<Oilrecord> findOilSum3(@Param("gsId")Integer gsId)throws Exception;
	
	
	/**
	 * 燃油站点查询每天燃油可提取額
	 * @param gsId
	 * @return
	 * @throws Exception
	 */
	public List<ExtractApply> findOilSumDrawMonth3(@Param("gsId")Integer gsId)throws Exception;
	
	/**
	 * 燃油站点查询年燃油交易额，交易量,可提取額
	 * @param gsId
	 * @return
	 * @throws Exception
	 */
	public List<Oilrecord> findOilSum4(@Param("gsId")Integer gsId)throws Exception;
	
	/**
	 * 燃油站点查询年燃油可提取額 
	 * @param gsId
	 * @return
	 * @throws Exception
	 */
	public List<ExtractApply> findOilSumDrawMonth4(@Param("gsId")Integer gsId)throws Exception;
	
	
	
}
