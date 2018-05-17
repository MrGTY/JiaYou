package cn.zhaojisys.dao.app;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhaojisys.pojo.Shop;
import cn.zhaojisys.pojo.ShopModel;
import cn.zhaojisys.pojo.Tyredatails;

/**
 * 轮胎商品
 * @author 李杰
 *
 */
public interface ShopMapper {

	/**
	 * 删除对应列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delShopBy_Id(@Param("id")Integer id)throws Exception; 
	
	/**
	 * 根据品牌查找轮胎  精确查找
	 * @param brand 非必选
	 * @return
	 * @throws Exception
	 */
	public List<Shop> shopAllList(@Param("brand") String brand,
									@Param("specifications") String specifications,
									@Param("material") String material,
									@Param("currentPageNo")Integer currentPageNo,
									@Param("pageSize")Integer pageSize)throws Exception;
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
	public ShopModel getShopById(@Param("id")Integer id) throws Exception;
	/**
	 * 根据商品id修改商品
	 * @param id
	 * @return
	 */
	public int modifyShopById(Shop shop) throws Exception;
	/**
	 * 更新购买轮胎所需的剩余金额
	 * @param id
	 * @param money
	 * @return
	 * @throws Exception
	 */
	public int buyTyre(@Param("id")Integer id,@Param("money")Integer money)throws Exception;
	/**
	 * 添加轮胎购买记录
	 * @param tyredatails
	 * @return
	 * @throws Exception
	 */
	public int buyTyreHistory(Tyredatails tyredatails)throws Exception;
	
	/**
	 * 最后一次购买时间
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Date lastMaxBuyshopBy_Id(@Param("vipUserId")Integer vipUserId)throws Exception; 

}
