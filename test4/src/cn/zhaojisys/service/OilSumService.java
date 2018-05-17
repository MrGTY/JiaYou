package cn.zhaojisys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Oilrecord;

public interface OilSumService {
	//燃油站点查询当月燃油交易额和交易量，可提取额，待提取额
	Oilrecord findOilSum(Oilrecord oilrecord,
						@Param("gsId")Integer gsId,
						@Param("status")Integer status);
	//燃油站点查询全年燃油交易额和交易量，可提取额，待提取额
		Oilrecord findOilSumMonth(Oilrecord oilrecord,
							@Param("gsId")Integer gsId,
							@Param("status")Integer status);
		//燃油站点查询当月燃油交易额和交易量，可提取额，待提取额
		List<Oilrecord> findOilSum1(Oilrecord oilrecord,
							@Param("gsId")Integer gsId,
							@Param("status")Integer status);
		//燃油站点查询全年燃油交易额和交易量，可提取额，待提取额
		List<Oilrecord> findOilSumMonth1(Oilrecord oilrecord,
								@Param("gsId")Integer gsId,
								@Param("status")Integer status);
		//燃油站点查询全年燃油交易额和交易量，可提取额，待提取额
				List<Oilrecord> findOilSumYear(Oilrecord oilrecord,
									@Param("gsId")Integer gsId,
									@Param("status")Integer status);
				//燃油站点查询全年燃油交易额和交易量，可提取额，待提取额
				List<Oilrecord> findOilSumYear1(Oilrecord oilrecord,
										@Param("gsId")Integer gsId,
										@Param("status")Integer status);
	
}
