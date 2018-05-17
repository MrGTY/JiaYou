package cn.zhaojisys.service.app;

import org.apache.ibatis.annotations.Param;

public interface CodeIsOkService {

	/**
	 * 根据手机号码查询账户是否被禁用掉
	 * @param phoneNum
	 * @return
	 * @throws Exception
	 */
	public int CodeIsOk(@Param("phoneNum")String phoneNum)throws Exception;
}