package cn.zhaojisys.dao.applymapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;


import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Oilrecord;

public interface ApplyMapper {
	// 通过Id查询来选择通过还是拒绝
		public List<ExtractApply> selectAllApplyById(@Param("id") String id)
				throws Exception;

		// 查询所有申请信息并分页
		public List<ExtractApply> selectAllApply(@Param("pageNo") Integer pageNo,
				@Param("pageSize") Integer pageSize,
				@Param("siteName") String siteName,
				@Param("businessType") Integer businessType,
				@Param("status") Integer status) throws Exception;

		// 总数
		public int count(@Param("siteName") String siteName,
				@Param("businessType") Integer businessType,
				@Param("status") Integer status) throws Exception;

		// 审批
		public int updateApply(@Param("emplId") String emplId,
				@Param("serialNum") String serialNum,
				@Param("approveData") Date approveData,
				@Param("status") String status,
				@Param("id") Integer id
				) throws Exception;
		//根据ID找Oilrecord记录
		public ExtractApply selectExtractApplyById(@Param("id") Integer id) throws Exception;
}
