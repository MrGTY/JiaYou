package cn.zhaojisys.dao.systemlog;
/**
 * 系统日志数据层接口
 */
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Systemlog;

public interface SystemlogMapper {
	//查询系统日志信息
	public List<Systemlog> getAllsystemlog(@Param("id") Integer id);
	
}
