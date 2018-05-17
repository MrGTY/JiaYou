package cn.zhaojisys.controller.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Shop;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.SiteService;
import cn.zhaojisys.service.app.SelectTyre;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.app.sendsms;

@Controller
@RequestMapping("app")
public class Appcontroller {

	@Resource
	SelectTyre styre = null;
	@Resource
	private SiteService siteService;

	// 查询轮胎收支记录
	@RequestMapping("tyre")
	@ResponseBody
	public Object getTyrelist(@RequestParam("id") Integer id,
							  @RequestParam("pageNo") Integer pageNo,
							  HttpSession session) {
		List<Tyredatails> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			//session在页面
			int pageSize=Constants.pageSize;
			list = styre.getTyrelist(id,(pageNo-1)*pageSize, pageSize);
			if (list.size() != 0) {
				jsonObject.put("list", list);
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");// 查询失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 查询列表异常
		}
		return jsonObject;
	}

	// 查询燃油收支记录
	@RequestMapping("oil")
	@ResponseBody
	public Object getOillist(@RequestParam("id") Integer id,
			  				 @RequestParam("pageNo") Integer pageNo
			  				 ) {
		List<Oilrecord> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			int pageSize=Constants.pageSize;
			list = styre.getOilrelist(id,(pageNo-1)*pageSize, pageSize);
			if (list.size() != 0) {
				jsonObject.put("list", list);
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");// 查询失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 查询列表异常
		}
		return jsonObject;
	}

	// 查询加油站点列表
	@RequestMapping("gassoil")
	@ResponseBody
	public Object getGassOil(@RequestParam("type") Integer type,@RequestParam("pageNo")Integer pageNo) {
		List<Gasstation> list = null;
		JSONObject jsonObject = new JSONObject();
		int pageSize=Constants.pageSize;
		try {
			list = styre.getGassOlist(type,(pageNo-1)*pageSize,pageSize);
			if (list.size() != 0) {
				jsonObject.put("list", list);
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");// 查询失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			return jsonObject.put("msg", "400");// 查询列表异常
		}
		return jsonObject;
	}

	// 查询轮胎站点列表
	@RequestMapping("gasstyre")
	@ResponseBody
	public Object getGassTyre(@RequestParam("type") Integer type,@RequestParam("pageNo")Integer pageNo) {
		List<Gasstation> list = null;
		JSONObject jsonObject = new JSONObject();
		int pageSize=Constants.pageSize;
		try {
			list = styre.getGassTlist(type,(pageNo-1)*pageSize,pageSize);
			if (list.size() != 0) {
				jsonObject.put("list", list);
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");// 查询失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 查询列表异常
		}
		return jsonObject;
	}

	// 查询商户轮胎收支记录
	@RequestMapping("shtyre")
	@ResponseBody
	public Object getStyrelist(@RequestParam("id") Integer id) {
		List<Tyredatails> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = styre.getStyrelist(id);
			if (list.size() != 0) {
				jsonObject.put("list", list);
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");// 查询失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 查询列表异常
		}
		return jsonObject;
	}

	// 查询轮胎商品信息
	@RequestMapping("tyreinfo")
	@ResponseBody
	public Object gettyreinfo(@RequestParam("id") Integer id,
			@RequestParam("quantity") Integer quantity) {
		Shop shop = null;
		int price = 0;
		JSONObject jsonObject = new JSONObject();
		try {
			shop = styre.getTyreinfo(id);
			if (shop != null) {
				price = Integer.parseInt(shop.getPrice()) * quantity;
				jsonObject.put("quantity", quantity);
				jsonObject.put("shop", shop);
				jsonObject.put("price", price);
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");// 查询失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 查询列表异常
		}
		return jsonObject;
	}

	// 查询会员信息 查询站点信息
	@RequestMapping("buytyre")
	@ResponseBody
	public Object buytyre(@RequestParam("vid") Integer vid,
			@RequestParam("gid") Integer gid) {
		JSONObject jsonObject = new JSONObject();
		Vipuserinfo vipuserinfo = null;
		Gasstation gasstation = null;

		try {
			vipuserinfo = styre.getVipinfo(vid);
			gasstation = styre.getGassinfo(gid);
			if (vipuserinfo != null && gasstation != null) {
				jsonObject.put("vipuserinfo", vipuserinfo);
				jsonObject.put("gasstation", gasstation);
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");// 查询失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 查询列表异常
		}
		return jsonObject;
	}

	// 获取验证码
	@RequestMapping("yzm")
	@ResponseBody
	public Object getYzm(@RequestParam("mobile") String mobile) {
		JSONObject jsonObject = new JSONObject();
		Vipuserinfo vipuserinfo = null;
		try {
			vipuserinfo = styre.selectVip(mobile);
			if (vipuserinfo != null) {
				// 获取平台验证码
				int pyzm = sendsms.getyzm(mobile);
				if (pyzm != 0) {
					jsonObject.put("pyzm", pyzm);// 返回验证码
					jsonObject.put("msg", 1);
				} else {
					jsonObject.put("msg", "-1"); // 获取验证码失败！
				}
			} else {
				jsonObject.put("msg", "400"); // 手机号未注册！
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	// 修改密码
	@RequestMapping("updatepwd")
	@ResponseBody
	public Object updatepwd(
			@RequestParam("mobile") String mobile,
			@RequestParam("pyzm") Integer pyzm, // 获取平台发送的验证码
			@RequestParam("password1") String password1,
			@RequestParam("password2") String password2,
			@RequestParam("yzm") Integer yzm // 用户输入的验证码
	) {
		JSONObject jsonObject = new JSONObject();
		// 与输入验证码比较
		if (!yzm.equals(pyzm)) {
			jsonObject.put("msg", "505"); // 505:您输入的验证码不正确！
		} else if (password1.length() < 6) {
			jsonObject.put("msg", "401");// 401:密码长度不得小于6位！
		} else if (!password1.equals(password2)) {
			jsonObject.put("msg", "404");// 404:输入的两次密码不一致！
		} else {
			try {
				int num = styre.updatePwd(password1, mobile);
				if (num > 0) {
					jsonObject.put("msg", "1");// 修改密码成功！
				} else {
					jsonObject.put("msg", "-1");// -1:插入失败！
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("msg", "400");// -1:插入异常！
			}
		}
		return jsonObject;
	}

	// 好友赠送消息插入
	// 被赠送消息
	@RequestMapping("updatefriend")
	@ResponseBody
	public Object updatefriend(
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "userPhone", required = false) String userPhone,
			@RequestParam(value = "vipId", required = false) Integer vipId,
			@RequestParam(value = "fId", required = false) Integer fId,
			@RequestParam(value = "OilQuantity", required = false) Integer OilQuantity,
			@RequestParam(value = "type", required = false) Integer type,// 赠送与被赠送者区分
																			// 1
																			// 为被赠送者
																			// 2
																			// 为赠送者
			HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		Date date = new Date();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sp.format(date);
		int num = 0;
		// try {
		// Vipuserinfo vipinfo=styre.getVipinfo(vipId);
		// request.getSession().setAttribute(Constants.VIPUSERINFO, vipinfo);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }

		Vipuserinfo vipuserinfo = (Vipuserinfo) request.getSession()
				.getAttribute(Constants.VIPUSERINFO);
		try {
			if (type == 1) { // 以被赠者为主 插入消息
				String content1 = "您的好友" + vipuserinfo.getVipName() + " "
						+ vipuserinfo.getUserName() + "于" + time + "赠送给您"
						+ OilQuantity + "L" + "请及时查收！";
				num = styre.insertinfo(1, content1, time, fId, -1);
			} else if (type == 2) { // 以赠者为主 插入消息
				String content2 = "您于" + time + "赠送给您的好友" + userName + " "
						+ userPhone + "油量" + OilQuantity + "L";
				num = styre.insertinfo(1, content2, time, vipId, -1);
			}
			if (num > 0) {
				jsonObject.put("msg", "1");// 插入消息成功！
			} else {
				jsonObject.put("msg", "-1");// 插入消息失败！
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 插入消息异常！
		}
		return jsonObject;
	}

	// 站点消费消息插入
	@RequestMapping("updatezd")
	@ResponseBody
	public Object updatezd(
			@RequestParam(value = "siteName", required = false) String siteName, // 站点名
			@RequestParam(value = "vipName", required = false) String vipName, // 被加油者名称
			@RequestParam(value = "vipId", required = false) Integer vipId, // 被加油者id
			@RequestParam(value = "preAddOil", required = false) Integer preAddOil, // 加油量
			@RequestParam(value = "gsId", required = false) Integer gsId, // 站点id
			@RequestParam(value = "type", required = false) Integer type // 会员与站点区分
																			// 1
																			// 会员
																			// 2
																			// 站点
	) {
		JSONObject jsonObject = new JSONObject();
		Date date = new Date();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sp.format(date);
		int num = 0;
		try {
			if (type == 1) { // 以加油者为主 向加油者 推送加油消息 会员在某站点加油多少L
				String content1 = "您于" + time + "获得" + siteName + "油量充值"
						+ preAddOil + "L" + "请及时查收！";
				num = styre.insertinfo(2, content1, time, vipId, gsId);
			} else if (type == 2) { // 以站点为主 向站点推送消息 本站点为 某会员充值多少油
				String content2 = siteName + "于" + time + "为会员" + vipName
						+ "充值油量" + preAddOil + "L";
				num = styre.insertinfo(2, content2, time, vipId, gsId);
			}
			if (num > 0) {
				jsonObject.put("msg", "1");// 插入消息成功！
			} else {
				jsonObject.put("msg", "-1");// 插入消息失败！
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 插入消息异常！
		}
		return jsonObject;
	}

	// 查询站点二维码以及账户名
	@RequestMapping("ImgName")
	@ResponseBody
	public Object getImgName(
			@RequestParam(value = "gid", required = false) Integer gid) {
		JSONObject jsonObject = new JSONObject();
		Gasstation gasstation = null;
		try {
			gasstation = styre.getQrCodeImg(gid);
			if (gasstation != null) {
				jsonObject.put("gasstation", gasstation);
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");// 查询失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 查询列表异常
		}
		return jsonObject;
	}

	// 商户获取验证码
	@RequestMapping("shyzm")
	@ResponseBody
	public Object getshYzm(
			@RequestParam(value = "mobile", required = false) String mobile) {
		JSONObject jsonObject = new JSONObject();
		Gasstation gasstation = null;
		try {
			gasstation = styre.selectGass(mobile);
			if (gasstation != null) {
				// 获取平台验证码
				int pyzm = sendsms.getyzm(mobile);
				if (pyzm != 0) {
					jsonObject.put("pyzm", pyzm);// 返回验证码
					jsonObject.put("msg", 1);
				} else {
					jsonObject.put("msg", "-1"); // 获取验证码失败！
				}
			} else {
				jsonObject.put("msg", "400"); // 手机号未注册！
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	// 商户修改密码
	@RequestMapping("shupdatepwd")
	@ResponseBody
	public Object shupdatepwd(
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "pyzm", required = false) Integer pyzm, // 获取平台发送的验证码
			@RequestParam(value = "password1", required = false) String password1,
			@RequestParam(value = "password2", required = false) String password2,
			@RequestParam(value = "yzm", required = false) Integer yzm // 用户输入的验证码){
	) {
		JSONObject jsonObject = new JSONObject();
		// 与输入验证码比较
		if (!yzm.equals(pyzm)) {
			jsonObject.put("msg", "505"); // 505:您输入的验证码不正确！
		} else if (password1.length() < 6) {
			jsonObject.put("msg", "401");// 401:密码长度不得小于6位！
		} else if (!password1.equals(password2)) {
			jsonObject.put("msg", "404");// 404:输入的两次密码不一致！
		} else {
			try {
				int num = styre.shupdatePwd(password1, mobile);
				if (num > 0) {
					jsonObject.put("msg", "1");// 修改密码成功！
				} else {
					jsonObject.put("msg", "-1");// -1:插入失败！
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("msg", "400");// -1:插入异常！
			}
		}
		return jsonObject;
	}
	//查询加油量与服务费
	@RequestMapping("fee")
	@ResponseBody
	public Object getAllfee(@RequestParam(value="gid",required=false)Integer gid){
			List<Oilrecord> list=null;
			JSONObject jsonObject=new JSONObject();
			try {
				list=styre.getfee(gid);
				if(list.size()!=0){
					jsonObject.put("list",list);
					jsonObject.put("msg", "1");
				}else{
					 jsonObject.put("msg","-1");//查询失败
				}	
			} catch (Exception e) {
					e.printStackTrace();
					jsonObject.put("msg","400");//查询列表异常
			}
			return jsonObject;
	}
	//查未结算的服务费总额
	@RequestMapping("feezong")
	@ResponseBody
	public Object getFeeZong(@RequestParam(value="gid",required=false)Integer gid){
		Double money=null;
		JSONObject jsonObject=new JSONObject();
			try {
				money=siteService.getFeeAll(gid);
				if(money!=null){
					jsonObject.put("list",money);
					jsonObject.put("msg", "1");
				}else{
					 jsonObject.put("msg","-1");//查询失败
				}	
			} catch (Exception e) {
					e.printStackTrace();
					jsonObject.put("msg","400");//查询列表异常
			}
			return jsonObject;
	}
	//点击确定插入记录
	@RequestMapping("feeque")
	@ResponseBody
	public Object feeQue(@RequestParam(value="gid")Integer gid,@RequestParam(value="idc")String idc,
			@RequestParam(value="fee")Double fee){
		String s=idc.replace(" ","");
		Map<String,Object> jsonObject=new HashMap<String,Object>();
		try {
			jsonObject=styre.feeAdd(s.toString(),gid,fee);
		} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("msg","400");//查询列表异常
		}
		return JSONArray.toJSONString(jsonObject);
	}
}
