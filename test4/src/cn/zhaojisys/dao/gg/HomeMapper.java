package cn.zhaojisys.dao.gg;

/**
 * 首页汇总
 * 
 * @author 李杰
 *
 */
public interface HomeMapper {

	/**
	 * 本月完成量
	 * 
	 * @return
	 * @throws Exception
	 */
	// 完成量(L)
	public String completeQuantity() throws Exception;

	// 轮胎金额(元)
	public String tiresMoney() throws Exception;

	/**
	 * 本月提取总量
	 */
	// 燃油提取(吨)
	public String oilExtraction() throws Exception;

	// 轮胎提取(元)
	public String extract() throws Exception;
	
	/**
	 * 本月新增账户
	 */
	//燃油站点
	public String fuelSite() throws Exception;
	
	//轮胎站点
	public String tyreSite() throws Exception;
	
	
	//新增会员
	public String newAddVip() throws Exception;
	
	/**
	 * 平台汇总统计
	 */
	//充值总量:
	public String czTotal() throws Exception;
	//完成总量:
	public String comTotal() throws Exception;
	
	//提取总量:
	public String tqTotal() throws Exception;
	
	//未提取量:
	public String wtqTotal() throws Exception;
	
	//待提取量::
	public String dtqTotal() throws Exception;
	//服务点量(燃油)
	public String serviceTotal() throws Exception;
	
	//会员总量
	public String vipTotal() throws Exception;
	//物流公司
	public String logisticCompany() throws Exception;
	//车主
	public String persinoal() throws Exception;
	/**
	 * 轮胎
	 */
	//轮胎成交
	public String ltMoney() throws Exception;
	//轮胎提取量
	public String lttqMoney() throws Exception;
	//轮胎待提取量
	public String ltdtqMoney() throws Exception;
	
	//轮胎站点量
	public String ltzdNum() throws Exception;
	
	
}
