package cn.zhaojisys.controller.app;


import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.pojo.Shop;
import cn.zhaojisys.pojo.ShopModel;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.app.ShopService;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.PageSupport;

@Controller
@RequestMapping("/app")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@RequestMapping("/deloneshop")
	@ResponseBody
	// 删除商品
	public Object deloneshop(HttpSession session, @RequestParam(value = "id", required = true) String id) {
		int count = 0;
		JSONObject jsonObject = new JSONObject();
		try {
			count = shopService.delShopBy_Id(Integer.parseInt(id));
			if (count > 0) {
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");
			return jsonObject;
		}
		return jsonObject;
	}

	@RequestMapping(value="/shopAllList",method=RequestMethod.GET)
	@ResponseBody
	// 查询所有商品列表
	public Object shopAllList(HttpSession session,HttpServletRequest request, 
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "specifications", required = false) String specifications,
			@RequestParam(value = "material", required = false) String material,
			@RequestParam(value = "pageIndex", required = true) String pageIndex) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pageIndex);
		PageSupport pages = new PageSupport();
		pages.setPageSize(5);
		List<Shop> list = null;
		JSONObject jsonObject = new JSONObject();
		if (null == brand || "".equals(brand)) {
			brand = null;
		}
		if (null == specifications || "".equals(specifications)) {
			specifications = null;
		}
		if (null == material || "".equals(material)) {
			material = null;
		}
		try {
			list = shopService.shopAllList(brand,specifications,material, (Integer.parseInt(pageIndex) - 1) * pages.getPageSize(),pages.getPageSize());
			if (0 != list.size()) {
				StringBuffer url = request.getRequestURL(); 
				String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString(); 
				String logoPicPath = request.getContextPath() + "/statics/appimg/";
				jsonObject.put("list", list);
				jsonObject.put("msg", "1");
				jsonObject.put("imgurl", tempContextUrl+logoPicPath);
			} else {
				jsonObject.put("msg", "-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");
			return jsonObject;
		}
		return jsonObject;
	}

	@RequestMapping("/detail")
	@ResponseBody
	// 根据id，查询商品详情
	public Object getShopById(HttpSession session, @RequestParam(value = "id", required = true) int id) {
		JSONObject jsonObject = new JSONObject();
		ShopModel shop = null;
		try {
			shop = shopService.getShopById(id);
			if (shop != null) {
				jsonObject.fluentPut("shop", shop);
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");
		}
		return jsonObject;
	}

	@RequestMapping(value="/uploadshop",method=RequestMethod.GET)
	@ResponseBody
	// 商品上传
	public Object uploadshop(@RequestParam("uid")Integer uid, Shop shop,HttpServletRequest request) {
		int count = 0;
		JSONObject jsonObject = new JSONObject();
		if (null == shop.getMarque() || null == shop.getBrand() || null == shop.getMaterial()
				|| null == shop.getSpecifications() || null == shop.getPrice()) {
			jsonObject.put("msg", "-1");
			return jsonObject;
		}
		try {
			shop.setgId(uid);
			shop.setCreatData(new Date());
			count = shopService.addShop(shop);
			if (count > 0) {
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");
		}
		return jsonObject;
	}

	@RequestMapping("/mshop")
	@ResponseBody
	// 根据id，更新商品详情
	public Object mshop(HttpSession session, Shop shop,HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		int count = 0;
		if (null == shop.getMarque() || null == shop.getBrand() || null == shop.getMaterial()
				|| null == shop.getSpecifications() || null == shop.getPrice()) {
			jsonObject.put("msg", "-1");
			return jsonObject;
		}
		try {
			shop.setCreatData(new Date());
			count = shopService.modifyShopById(shop);
			if (count > 0) {
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");
		}
		return jsonObject;
	}

	@RequestMapping(value="/buyTyre",method=RequestMethod.POST)
	@ResponseBody
	// 购买轮胎
	public Object buyTyre(HttpSession session, @RequestParam("id") Integer id, @RequestParam("num") Integer num,
			@RequestParam("money") Integer money) {
		JSONObject json = new JSONObject();
		Vipuserinfo vipuserinfo = (Vipuserinfo)session.getAttribute(Constants.VIPUSERINFO);
		try {
			if (vipuserinfo.getStateTag()==1) {//个人禁用
				json.put("msg", "-20");
				return json;
			}
			json = shopService.buyTyre(vipuserinfo.getId(), id, num, money);//轮胎id
			int count = json.getInteger("msg");
			if (count > 0) {
				return json;
			} else if (count == -10) {
				json.put("msg", "-10");
				return json;
			}else if (count == -100) {
				json.put("msg", "-100");
				return json;
			}else if (count == -200) {
				json.put("msg", "-200");
				return json;
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "400");
			return json;
		}
		json.put("msg", "-1");
		return json;
	}
}
