package cn.zhaojisys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Systemlog;

/**
 * ϵͳҵ���ӿ�
 * @author user
 */
public interface SystemlogService {
		//��ѯϵͳ��־��Ϣ
		public List<Systemlog> selectSystemlog(@Param(value="id") Integer id);
}
