package cn.zhaojisys.service;

import cn.zhaojisys.vo.AdminSummaryVo;

public interface AdminSummaryService {

	/**
	 * 月汇总
	 * @return
	 * @throws Exception
	 */
	public AdminSummaryVo mounth()throws Exception;
	/**
	 * 月汇总
	 * @return
	 * @throws Exception
	 */
	public AdminSummaryVo year()throws Exception;
	
	
}
