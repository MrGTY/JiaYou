package cn.zhaojisys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Systemlog;

/**
 * 系统业务层接口
 * @author user
 */
public interface SystemlogService {
		//查询系统日志信息
		public List<Systemlog> selectSystemlog(@Param(value="id") Integer id);
}
