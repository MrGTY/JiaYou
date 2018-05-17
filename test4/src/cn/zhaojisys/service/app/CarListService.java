package cn.zhaojisys.service.app;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import cn.zhaojisys.pojo.Vipuserinfo;

public interface CarListService {
	//车辆列表接口
		List<Vipuserinfo> carList(@Param("logicId") Integer logicId,
									@Param("id") Integer id,
									@Param("pageNo") Integer pageNo);
}
