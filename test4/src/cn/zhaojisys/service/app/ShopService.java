package cn.zhaojisys.service.app;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.pojo.Shop;
import cn.zhaojisys.pojo.ShopModel;
import cn.zhaojisys.pojo.Tyredatails;

public interface ShopService {

	/**
	 * 删除对应列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delShopBy_Id(Integer id)throws Exception; 
	/**
	 * 根据品牌查找轮胎  精确查找
	 * @param brand    非必选
	 * @return
	 */
	public List<Shop> shopAllList(String brand,String specifications,String material,Integer currentPageNo,
			Integer pageSize)throws Exception;
	/**
	 * 上传商品
	 * @param gId  站点id
	 * @return
	 */
	public int addShop(Shop shop)throws Exception;
	/**
	 * 根据商品id查询信息
	 * @param id
	 * @return
	 */
	public ShopModel getShopById(@Param("id")Integer id)throws Exception;
	/**
	 * 根据商品id修改商品
	 * @param id
	 * @return
	 */
	public int modifyShopById(Shop shop) throws Exception;
	/**
	 * 更新购买轮胎所需的剩余金额
	 * 添加用户轮胎购买记录
	 * 添加轮胎站点卖出记录
	 * @param id
	 * @param money
	 * @return
	 * @throws Exception
	 */
	public JSONObject buyTyre(Integer sessionId,Integer id,Integer num,Integer money)throws Exception;
}
