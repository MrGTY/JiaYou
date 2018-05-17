package cn.zhaojisys.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.EmployeInfo;
import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Oilrecord;

public interface ApplyService {
	// ͨ通过Id查询来选择通过还是拒绝
	public List<ExtractApply> selectAllApplyById(@Param("id") String id)
			throws Exception;

	// 查询所有申请信息并分页
	public List<ExtractApply> selectAllApply(@Param("pageNo") Integer pageNo,
			@Param("pageSize") Integer pageSize,
			@Param("pageSize") String siteName,
			@Param("pageSize") Integer businessType,
			@Param("pageSize") Integer status) throws Exception;

	// 总数
	public int count(String siteName,Integer businessType,Integer status) throws Exception;

	// 审批
	public int updateApply(@Param("emplId") String emplId,
			@Param("serialNum") String serialNum,
			@Param("approveData") Date approveData,
			@Param("status") String status,
			@Param("id") Integer id
			) throws Exception;
	public ExtractApply selectExtractApplyById(Integer id) throws Exception;
	
	// 通过或拒绝申请
	public Object getResult(String serialNum,String type,Integer id,HttpSession session) throws Exception;
}
