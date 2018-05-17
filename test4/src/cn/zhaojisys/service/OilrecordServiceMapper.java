package cn.zhaojisys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;

public interface OilrecordServiceMapper {
	//��ѯȫ��
	public List<Oilrecord> selectAll()throws Exception;
	/**
	 * 得到扫一扫的参数
	 * @param id  站点id
	 * @param gsType  站点类型
	 * @return
	 * @throws Exception
	 */
	public Gasstation getGasstationBy_idAndType(String id,String gsType)throws Exception;

}
