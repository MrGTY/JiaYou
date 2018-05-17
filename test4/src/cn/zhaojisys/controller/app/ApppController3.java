package cn.zhaojisys.controller.app;

import static org.hamcrest.CoreMatchers.nullValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.HeadLine;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.ParamatersettingService;
import cn.zhaojisys.service.app.ApppService;
import cn.zhaojisys.tools.Constants;
import cn.zhaojisys.tools.app.sendsms;

@Controller
public class ApppController3 {

	@Autowired
	ApppService apppService;
	@Autowired
	ParamatersettingService paramatersettingService;

	// 首页
	@RequestMapping(value = "HomePage")
	@ResponseBody
	public Object HomePage(
			@RequestParam(value = "id", required = false) Integer id,
			HttpSession session) {
		Vipuserinfo vipuse=(Vipuserinfo)session.getAttribute(Constants.VIPUSERINFO);
		Vipuserinfo vipuserinfo = null;
		JSONObject jsonObject = new JSONObject();
		try {
			vipuserinfo = apppService.HomePage(vipuse.getId());
			HeadLine headLine=paramatersettingService.chaLine();
			if (vipuserinfo == null) {
				jsonObject.put("msg", "-1");// 失败
			} else {
				jsonObject.put("vipuserinfo", vipuserinfo);
				jsonObject.put("headLine", headLine);
				jsonObject.put("msg", "1");// 成功
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 程序异常
		}

		return JSON.toJSONString(jsonObject);

	}

	// 交易信息接口--首页
	@RequestMapping(value = "Information")
	@ResponseBody
	public Object Information(
			@RequestParam(value = "id", required = false) Integer id,
			HttpSession session) {
		Vipuserinfo vipuse=(Vipuserinfo)session.getAttribute(Constants.VIPUSERINFO);
		List<Oilrecord> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = apppService.Information(vipuse.getId());
			if (list != null) {
				jsonObject.put("list", list);
				jsonObject.put("msg", "1");// 成功
			} else {
				jsonObject.put("msg", "-1");// 失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 程序异常
		}
		return JSON.toJSONString(jsonObject);
	}

	// 账号密码登录
	@RequestMapping(value = "/loginyes")
	@ResponseBody
	public Object login(
			HttpSession session,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "loginType", required = false) Integer loginType,
			@RequestParam(value = "code", required = false) String code) {
		JSONObject jsonObject = new JSONObject();
		Vipuserinfo vipuserinfo = null;
		Gasstation gasstation = null;
		try {
			if (loginType == 0) {
				vipuserinfo = apppService.login(phoneNum, password);
				if (vipuserinfo != null) {
					if (vipuserinfo.getStateTag()==1) {
						//账户被禁用
						jsonObject.put("msg", "11");
					}else{
						session.setAttribute(Constants.VIPUSERINFO, vipuserinfo);
						jsonObject.put("vipuserinfo", vipuserinfo);
						//车主 物流
						jsonObject.put("msg", "1");
					}
				} else {
					jsonObject.put("msg", "-1");
				}
			} else {
				gasstation = apppService.loginType(code, password);
				if (gasstation != null) {
					if (gasstation.getStatus()==0) {
						//账户被禁用
						jsonObject.put("msg", "11");
					}else{
						session.setAttribute(Constants.GASSTION, gasstation);
						jsonObject.put("gasstation", gasstation);
						if(gasstation.getGsType()==0){
							//返回燃油
							jsonObject.put("msg", "2");
						}else{
							//轮胎
							jsonObject.put("msg", "3");
						}
					}
				} else {
					jsonObject.put("msg", "-1");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");
		}
		return JSON.toJSONString(jsonObject);
	}

	// 短信验证码登录
	@RequestMapping(value = "loginphone")
	@ResponseBody
	public Object loginphone(
			HttpSession session,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "mobilePhone", required = false) String mobilePhone,
			@RequestParam(value = "loginType", required = false) Integer loginType,
			@RequestParam("yzm") Integer yzm, // 用户输入的验证码
			@RequestParam("pyzm") Integer pyzm // 获取平台发送的验证码
	) {
		Vipuserinfo vipuserinfo = null;
		Gasstation gasstation = null;
		JSONObject jsonObject = new JSONObject();
		if (loginType == 0) {
			if (!yzm.equals(pyzm)) {
				jsonObject.put("msg", "505"); // 您输入的验证码不正确！
			} else {
				try {
					vipuserinfo = apppService.loginphone(phoneNum);
					if (vipuserinfo != null) {
						if (vipuserinfo.getStateTag()==1) {
							//账户被禁用
							jsonObject.put("msg", "11");
						}else{
							session.setAttribute(Constants.VIPUSERINFO, vipuserinfo);
							jsonObject.put("vipuserinfo", vipuserinfo);
							jsonObject.put("msg", "1");// 登录成功
						}
					} else {
						jsonObject.put("msg", "-1");// 手机号码不存在
					}
				} catch (Exception e) {
					e.printStackTrace();
					jsonObject.put("msg", "400");// 程序异常
				}
			}
		} else {
			if (!yzm.equals(pyzm)) {
				jsonObject.put("msg", "505"); // 400:您输入的验证码不正确！
			} else {
				try {
					gasstation = apppService.loginType2(mobilePhone);
					if (gasstation != null) {
						if (gasstation.getStatus()==0) {
							//账户被禁用
							jsonObject.put("msg", "11");
						}else{
							session.setAttribute(Constants.GASSTION, gasstation);
							jsonObject.put("gasstation", gasstation);
							jsonObject.put("msg", "1");
						}
					} else {
						jsonObject.put("msg", "-1");
					}
				} catch (Exception e) {
					e.printStackTrace();
					jsonObject.put("msg", "400");
				}
			}
		}
		return JSON.toJSONString(jsonObject);
	}

	// 我的账户
	@RequestMapping(value = "uNamepwd")
	@ResponseBody
	public Object uName(HttpSession session,
			@RequestParam(value = "id", required = false) Integer id) {
		JSONObject jsonObject = new JSONObject();
		Vipuserinfo vipuse=(Vipuserinfo)session.getAttribute(Constants.VIPUSERINFO);
		Vipuserinfo vipuserinfo = null;
		try {
			vipuserinfo = apppService.uName(vipuse.getId());
			if (vipuserinfo != null) {
				jsonObject.put("vipuserinfo", vipuserinfo);
				jsonObject.put("msg", "1");// 成功
			} else {
				jsonObject.put("msg", "-1");// 失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 程序异常
		}
		return JSON.toJSONString(jsonObject);
	}

	// 注册接口
	@RequestMapping(value = "addVipuserinfo")
	@ResponseBody
	public Object addVipuserinfo(
			HttpSession session,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "vipName", required = false) String vipName,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "busNum") String busNum,
			@RequestParam("Zcyzm") Integer Zcyzm, // 用户输入的验证码
			@RequestParam("pyzm") Integer pyzm,
			@RequestParam("uploadImg") String uploadImg// 获取平台发送的验证码
	) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = dateFormat.format(new Date());
		JSONObject jsonObject = new JSONObject();
		if (!Zcyzm.equals(pyzm)) {
			jsonObject.put("msg", "505"); // 505:您输入的验证码不正确！
		} else if (password.length() < 6) {
			jsonObject.put("msg", "401");// 401:密码长度不得小于6位！
		} else {
			try {
				int vip = apppService.addVipuserinfo(userName, userName, phoneNum, password, busNum, createTime,uploadImg);
				if (vip > 0) {
					jsonObject.put("msg", "1");// 注册成功
				} else {
					jsonObject.put("msg", "-1");// 注册失败
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("msg", "400");// 程序异常
			}
		}
		return JSON.toJSONString(jsonObject);

	}

	//登录
	@RequestMapping(value = "yzm")
	@ResponseBody
	public Object getYzm(@RequestParam("mobile") String mobile) {
		JSONObject jsonObject = new JSONObject();
		Vipuserinfo vipuserinfo = null;
		// 获取平台验证码
		try {
			vipuserinfo = apppService.loginphone(mobile);
			if (vipuserinfo != null) {
				int pyzm = sendsms.getyzm(mobile);
				if (!equals(mobile)) {
					if (pyzm != 0) {
						jsonObject.put("pyzm", pyzm);
						jsonObject.put("msg", "1");
					} else {
						jsonObject.put("msg", "-1"); // 获取验证码失败！
					}
				} else {
					jsonObject.put("msg", "401");// 手机号不能为空
				}
			} else {
				jsonObject.put("msg", "505");// 手机号不存在
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 程序异常
		}
		return JSON.toJSONString(jsonObject);
	}

	//注册验证码
	@RequestMapping(value = "Zcyzm")
	@ResponseBody
	public Object getZcYzm(@RequestParam("mobile") String mobile) {
		JSONObject jsonObject = new JSONObject();
		Vipuserinfo vipuserinfo = null;
		// 获取平台验证码
		try {
			vipuserinfo = apppService.loginphone(mobile);
			if (vipuserinfo == null) {
				int pyzm = sendsms.getyzm(mobile);
				if (!equals(mobile)) {
					if (pyzm != 0) {
						jsonObject.put("pyzm", pyzm);
						jsonObject.put("msg", "1");
					} else {
						jsonObject.put("msg", "-1"); // 获取验证码失败！
					}
				} else {
					jsonObject.put("msg", "401");// 验证码为空
				}
			} else {
				jsonObject.put("msg", "505");// 手机号已存在
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 程序异常
		}
		return JSON.toJSONString(jsonObject);
	}

	// 个人中心
	@RequestMapping(value = "geren")
	@ResponseBody
	public Object geren(@RequestParam("id") Integer id,HttpSession session) {
		Vipuserinfo vipuse=(Vipuserinfo)session.getAttribute(Constants.VIPUSERINFO);
		JSONObject jsonObject = new JSONObject();
		Vipuserinfo vipuserinfo = null;
		try {
			vipuserinfo = apppService.geren(vipuse.getId());
			if (vipuserinfo != null) {
				jsonObject.put("vipuserinfo", vipuserinfo);
				jsonObject.put("msg", "1");// 成功
			} else {
				jsonObject.put("msg", "-1");// 失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 程序异常
		}
		return JSON.toJSONString(jsonObject);
	}

	//个人中心物流与会员车辆
		@RequestMapping(value ="gerencheliang")
		@ResponseBody
		public Object gerencheliang(@RequestParam("id")Integer id,HttpSession session){
			Vipuserinfo vipuse=(Vipuserinfo)session.getAttribute(Constants.VIPUSERINFO);
			JSONObject jsonObject = new JSONObject();
			int vipuserinfo=0;
			int userType=0;
			try {
				userType=apppService.vipCheLiang(vipuse.getId());
				if(userType==0){
					vipuserinfo=apppService.vipCheLiang2(vipuse.getId());
					if (vipuserinfo != 0) {
						jsonObject.put("vipuserinfo",vipuserinfo);
						jsonObject.put("msg", "1");//成功
					} else {
						jsonObject.put("msg", "-1");//失败
					}
				}else{
					vipuserinfo=apppService.gerencheliang(vipuse.getId());
					if (vipuserinfo != 0) {
						jsonObject.put("vipuserinfo",vipuserinfo);
						jsonObject.put("msg", "1");//成功
					} else {
						jsonObject.put("msg", "-1");//失败
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("msg", "400");//程序异常
			}
			return JSON.toJSONString(jsonObject);
		}

	// 商户账户
	@RequestMapping(value = "gName")
	@ResponseBody
	public Object gName(HttpSession session,
			@RequestParam(value = "id", required = false) Integer id) {
		Gasstation gass=(Gasstation)session.getAttribute(Constants.GASSTION);
		JSONObject jsonObject = new JSONObject();
		Gasstation gasstation = null;
		try {
			gasstation = apppService.gName(gass.getId());
			if (gasstation != null) {
				jsonObject.put("gasstation", gasstation);
				jsonObject.put("msg", "1");// 成功
			} else {
				jsonObject.put("msg", "-1");// 失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");// 程序异常
		}
		return JSON.toJSONString(jsonObject);
	}
}
