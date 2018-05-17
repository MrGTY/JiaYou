package cn.zhaojisys.service.app.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.dao.app.AddOilMapper;
import cn.zhaojisys.dao.app.ShopMapper;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Shop;
import cn.zhaojisys.pojo.ShopModel;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.app.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopMapper shopMapper;

	@Autowired
	private AddOilMapper addOilMapper;
	
	@Override
	public int delShopBy_Id(Integer id) throws Exception {
		return shopMapper.delShopBy_Id(id);
	}

	@Override
	public List<Shop> shopAllList(String brand, String specifications,String material,Integer currentPageNo, Integer pageSize) throws Exception {
		return shopMapper.shopAllList(brand,specifications,material, currentPageNo, pageSize);
	}

	@Override
	public int addShop(Shop shop) throws Exception {
		return shopMapper.addShop(shop);
	}

	@Override
	public ShopModel getShopById(Integer id) throws Exception {
		return shopMapper.getShopById(id);
	}

	@Override
	public int modifyShopById(Shop shop) throws Exception {
		return shopMapper.modifyShopById(shop);
	}


	@Override
	public JSONObject buyTyre(Integer sessionId,Integer id, Integer num, Integer money) throws Exception {
		JSONObject jsonObject = new JSONObject();
		ShopModel shop = null;
			Vipuserinfo vipuserinfo = addOilMapper.getVipinfo(sessionId);// 会员id
			shop = shopMapper.getShopById(id);
			if (shop.getStatus()==0) {//账户被禁用
				jsonObject.put("msg", "-100");
				 return jsonObject;
			}
			if (shop.getLogicId()==0) {//站点被删除被禁用
				jsonObject.put("msg", "-200");
				return jsonObject;
			}
			
			//轮胎余额-购买轮胎价格
			int balance = vipuserinfo.getTyreBalance() - (Integer.parseInt(shop.getPrice()) * num);
			//最后一次购买时间
			Date date1 = shopMapper.lastMaxBuyshopBy_Id(sessionId);
			//得到分钟
			long minutes=10;
			if (date1!=null) {
				minutes=((new Date()).getTime()-date1.getTime())/1000/60;
			}
			//(Integer.parseInt(shop.getPrice()) * num)  数据库查询到的单价*数量      money==>购买价格   
			if ((Integer.parseInt(shop.getPrice()) * num) != money || balance < 0) {
				 jsonObject.put("msg", "-1");
				 return jsonObject;
			}else if (minutes<10) {
				jsonObject.put("msg", "-10");
				 return jsonObject;
			} else {
				Date date = new Date();
				Tyredatails tyredatails=new Tyredatails();// 车主
				tyredatails.setOperationData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));// 操作时间
				tyredatails.setVipUserId(sessionId);// 会员id
				tyredatails.setTyprSpec(shop.getBrand());// 轮胎品牌
				tyredatails.setTireModel(shop.getMarque());// 轮胎型号
				tyredatails.setTyprMaterial(shop.getSpecifications());// 轮胎规格
				tyredatails.setQuantity(num);// 数量（支）
				tyredatails.setIncome(0);// 收入（元）
				tyredatails.setExpenditure(Integer.parseInt(shop.getPrice()) * num);// 支出（元）
				tyredatails.setBalance(balance);// 剩余金额（元）
				tyredatails.setOperationType(2);// 操作类型
				tyredatails.setZdid(shop.getgId());// 关联轮胎站点id
				
				Tyredatails tyredatail=new Tyredatails();// 轮胎站点
				tyredatail.setOperationData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));// 操作时间
				tyredatail.setVipUserId(sessionId);// 会员id
				tyredatail.setTyprSpec(shop.getBrand());// 轮胎品牌
				tyredatail.setTireModel(shop.getMarque());// 轮胎型号
				tyredatail.setTyprMaterial(shop.getSpecifications());// 轮胎规格
				tyredatail.setQuantity(num);// 数量（支）
				tyredatail.setIncome(Integer.parseInt(shop.getPrice()) * num);// 收入（元）
				tyredatail.setExpenditure(0);// 支出（元）
				tyredatail.setBalance(0);// 剩余金额（元）
				tyredatail.setOperationType(2);// 操作类型
				tyredatail.setZdid(shop.getgId());// 关联轮胎站点id
				tyredatail.setInfotype(2);
				int count1=shopMapper.buyTyre(sessionId, balance);
				int count2=shopMapper.buyTyreHistory(tyredatails);
				int countt3=shopMapper.buyTyreHistory(tyredatail);
				if (count1 > 0 && count2>0 && countt3>0) {
					 jsonObject.put("msg", "1");
					return jsonObject;
				}else{
					jsonObject.put("msg", "-1");
					return jsonObject;
				}
			}
	}

}
