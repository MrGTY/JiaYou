package cn.zhaojisys.service.app;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Tyredatails;

public interface TyreListService {
	//车主轮胎记录接口
		List<Tyredatails> tyreList(
				@Param("mycarId") Integer mycarId);

}
