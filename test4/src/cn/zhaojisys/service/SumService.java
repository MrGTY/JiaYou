package cn.zhaojisys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.pojo.VipuserinfoModel;

public interface SumService {
	
		//查询首页燃油收油量，提取额
		Oilrecord findOil(Oilrecord oilrecord); 	
		//查询首页轮胎收油量，提取额
		Tyredatails findTyre(Tyredatails tyredatails);
		//查询站点个数
		int numGasstation(int gsType);
		//查询新增会员个数
		int numEmp();
		//首页查询总收油量
		int sumOil();
		//首页查询总收油量
		int sumTyre();
		//首页查询燃油提取量
		int sumOilTyreDraw(@Param("businessType")int businessType,@Param("status")int status);
		//首页查询燃油或者轮胎站点个数
		int sumOilTyreStation(int gsType);
		//查询会员总量
		int sumVipUser(int userType);
		//首页查询本月日期和轮胎金额
		List<Tyredatails> benyue();
		//首页本月燃油量汇总 
		List<Oilrecord> oilMonSum();
		//首页本月燃油提取额汇总 
		List<Oilrecord> oilDraWMonSum();
		//首页本月轮胎提取额汇总 
		List<Tyredatails> tyreDraWMonSum();
		//首页本月新增会员个数
		List<VipuserinfoModel> newVipNum();
		//首页本月燃油交易量
		List<Oilrecord> oilSumDealNum();
		//首页本月轮胎交易量
		List<Tyredatails> tyreSumDealNum();
		//
		//
		//首页年度轮胎金额汇总 
		List<Tyredatails> yearTyreSum();
		//首页本月燃油量汇总 
		List<Oilrecord> yearOilMonSum();
		//首页本月燃油提取额汇总 
		List<Oilrecord> yearOilDraWMonSum();
		//首页本月轮胎提取额汇总 
		List<Tyredatails> yearTyreDraWMonSum();
		//首页本月新增会员个数
		List<Vipuserinfo> yearNewVipNum();
		//首页本月燃油交易量
		List<Oilrecord> yearOilSumDealNum();
		//首页本月轮胎交易量
		List<Tyredatails> yearTyreSumDealNum();
		
		//查充值油
		public Integer chong();
		//查本月提取ranyou
		public Integer tiqu();
		//查本月提取tantai
		public Integer tiqu2();
		//查询所有未提取的站点的油量总和
		public Integer getSumQuota();
}
