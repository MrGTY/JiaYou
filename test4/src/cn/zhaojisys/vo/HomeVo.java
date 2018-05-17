package cn.zhaojisys.vo;

public class HomeVo {

	/**
	 * 本月完成量
	 * 
	 * @return
	 * @throws Exception
	 */
	// 完成量(L)
	private String completeQuantity;

	// 轮胎金额(元)
	private String tiresMoney;

	/**
	 * 本月提取总量
	 */
	// 燃油提取(吨)
	private String oilExtraction;

	// 轮胎提取(元)
	private String extract;
	
	/**
	 * 本月新增账户
	 */
	//燃油站点
	private String fuelSite;
	
	//轮胎站点
	private String tyreSite;
	
	
	//新增会员
	private String newAddVip;
	
	/**
	 * 平台汇总统计
	 */
	//充值总量:
	private String czTotal;
	//完成总量:
	private String comTotal;
	//未完成量
	private String nocomTotal;
	//提取总量:
	private String tqTotal;
	
	//未提取量:
	private String wtqTotal;
	
	//待提取量::
	private String dtqTotal;
	//服务点量(燃油)
	private String serviceTotal;
	
	//会员总量
	private String vipTotal;
	//物流公司
	private String logisticCompany;
	//车主
	private String persinoal;
	/**
	 * 轮胎
	 */
	//轮胎成交
	private String ltMoney;
	//轮胎提取量
	private String lttqMoney;
	//轮胎待提取量
	private String ltdtqMoney;
	
	//轮胎站点量
	private String ltzdNum;

	
	
	
	public String getNocomTotal() {
		return nocomTotal;
	}

	public void setNocomTotal(String nocomTotal) {
		this.nocomTotal = nocomTotal;
	}

	public String getCompleteQuantity() {
		return completeQuantity;
	}

	public void setCompleteQuantity(String completeQuantity) {
		this.completeQuantity = completeQuantity;
	}

	public String getTiresMoney() {
		return tiresMoney;
	}

	public void setTiresMoney(String tiresMoney) {
		this.tiresMoney = tiresMoney;
	}

	public String getOilExtraction() {
		return oilExtraction;
	}

	public void setOilExtraction(String oilExtraction) {
		this.oilExtraction = oilExtraction;
	}

	public String getExtract() {
		return extract;
	}

	public void setExtract(String extract) {
		this.extract = extract;
	}

	public String getFuelSite() {
		return fuelSite;
	}

	public void setFuelSite(String fuelSite) {
		this.fuelSite = fuelSite;
	}

	public String getTyreSite() {
		return tyreSite;
	}

	public void setTyreSite(String tyreSite) {
		this.tyreSite = tyreSite;
	}

	public String getNewAddVip() {
		return newAddVip;
	}

	public void setNewAddVip(String newAddVip) {
		this.newAddVip = newAddVip;
	}

	public String getCzTotal() {
		return czTotal;
	}

	public void setCzTotal(String czTotal) {
		this.czTotal = czTotal;
	}

	public String getComTotal() {
		return comTotal;
	}

	public void setComTotal(String comTotal) {
		this.comTotal = comTotal;
	}

	public String getTqTotal() {
		return tqTotal;
	}

	public void setTqTotal(String tqTotal) {
		this.tqTotal = tqTotal;
	}

	public String getWtqTotal() {
		return wtqTotal;
	}

	public void setWtqTotal(String wtqTotal) {
		this.wtqTotal = wtqTotal;
	}

	public String getDtqTotal() {
		return dtqTotal;
	}

	public void setDtqTotal(String dtqTotal) {
		this.dtqTotal = dtqTotal;
	}

	public String getServiceTotal() {
		return serviceTotal;
	}

	public void setServiceTotal(String serviceTotal) {
		this.serviceTotal = serviceTotal;
	}

	public String getVipTotal() {
		return vipTotal;
	}

	public void setVipTotal(String vipTotal) {
		this.vipTotal = vipTotal;
	}

	public String getLogisticCompany() {
		return logisticCompany;
	}

	public void setLogisticCompany(String logisticCompany) {
		this.logisticCompany = logisticCompany;
	}

	public String getPersinoal() {
		return persinoal;
	}

	public void setPersinoal(String persinoal) {
		this.persinoal = persinoal;
	}

	public String getLtMoney() {
		return ltMoney;
	}

	public void setLtMoney(String ltMoney) {
		this.ltMoney = ltMoney;
	}

	public String getLttqMoney() {
		return lttqMoney;
	}

	public void setLttqMoney(String lttqMoney) {
		this.lttqMoney = lttqMoney;
	}

	public String getLtdtqMoney() {
		return ltdtqMoney;
	}

	public void setLtdtqMoney(String ltdtqMoney) {
		this.ltdtqMoney = ltdtqMoney;
	}

	public String getLtzdNum() {
		return ltzdNum;
	}

	public void setLtzdNum(String ltzdNum) {
		this.ltzdNum = ltzdNum;
	}
	
	
}
