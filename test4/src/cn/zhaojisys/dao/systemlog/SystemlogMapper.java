package cn.zhaojisys.dao.systemlog;
/**
 * ϵͳ��־���ݲ�ӿ�
 */
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Systemlog;

public interface SystemlogMapper {
	//��ѯϵͳ��־��Ϣ
	public List<Systemlog> getAllsystemlog(@Param("id") Integer id);
	
}
