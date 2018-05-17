package cn.zhaojisys.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.tools.Constants;

@Controller
@RequestMapping("/app")
public class UrlController {

	// 根据当前登录用户的id查找对应轮胎列表
	@RequestMapping("/ltsjwh")
	public String ltsjwh() {
		return "app/personal/ltsjwh";
	}

	// 购买轮胎列表
	@RequestMapping("/ltzq")
	public String ltzq() {
		return "app/luntaizhuanqu/ltzq";
	}

	// 修改轮胎(原声)
	@RequestMapping("/editshop")
	public String editshop() {
		return "app/luntaizhuanqu/test";
	}

	// 登陆页(密码)
	@RequestMapping("/login")
	public String denglu() {
		return "app/personal/logoname";
	}

	// 登录页(短信)
	@RequestMapping("/logintel")
	public String denglu2() {
		return "app/personal/logotel";
	}

	// 注册页
	/*@RequestMapping("/zhuce")
	public String zhuce() {
		return "app/personal/register";
	}*/
	@RequestMapping("/customer")
	public String customer() {
		return "app/personal/customer";
	}
	// 登陆成功进首页
	@RequestMapping("/shouye")
	public String shouye(RedirectAttributes attributes,HttpServletRequest request) {
		Vipuserinfo vipuserinfo=(Vipuserinfo)request.getSession().getAttribute(Constants.VIPUSERINFO);
		attributes.addAttribute("onlyPhone",vipuserinfo.getPhoneNum());
		return "redirect:/app/reshouye";
	}
	@RequestMapping("/reshouye")
	public String reshouye() {
		return "app/personal/index";
	}

	// 点我的进我的首页
	@RequestMapping("/geren")
	public String geren() {
		return "app/personal/personal";
	}

	// 个人中心(我的)进入我的账户
	@RequestMapping("/zhanghu")
	public String zhanghu() {
		return "app/personal/uName";
	}

	// 点附近
	@RequestMapping("/fujin")
	public String fujin() {
		return "app/personal/near";
	}

	// 找密码
	@RequestMapping("/zhaohuipsd")
	public String zhaohuipsd() {
		return "app/personal/zhaohuipsd";
	}

	// 点商品点付款
	@RequestMapping("/fukuan")
	public String fukuan(@RequestParam(value = "shopid") Integer shopid, @RequestParam(value = "geshu") Integer geshu,
			@RequestParam("gid") Integer gid, Model model) {
		model.addAttribute("shopid", shopid);
		model.addAttribute("geshu", geshu);
		model.addAttribute("gid", gid);
		return "app/personal/fukuan";
	}

	// 购油记录or轮胎记录
	@RequestMapping("/gouyou")
	public String gouyou(@RequestParam(value = "index", required = false) Integer ltt, Model model) {
		model.addAttribute("ltt", ltt);
		return "app/personal/ltjl";
	}

	// 商户端 我的账户
	@RequestMapping("/myshanghu")
	public String myzhanghu() {
		return "app/tenants/gName";
	}

	// 手机端我的车辆点击跳转到消费记录
	@RequestMapping("/xiaofeijilu")
	public String xiaofeijilu(@RequestParam(value = "a") int a, Model model) {

		model.addAttribute("a", a);

		return "app/personal/xiaofeijilu3";
	}

	// 手机端朋友页输入手机号查询
	@RequestMapping("/friend")
	public String friends(@RequestParam("phoneNum") String phoneNum, Model model) {
		model.addAttribute("phoneNum", phoneNum);
		return "app/friend/friends";
	}

	// 手机端分配
	@RequestMapping("/fenpei")
	public String fenpei(@RequestParam("id") int id, Model model) {

		model.addAttribute("id", id);
		return "app/friend/fenpei";
	}

	// 手机端删除
	@RequestMapping("/shanchu")
	public String shanchu(@RequestParam("id") int id, Model model) {

		model.addAttribute("id", id);
		return "app/friend/fenpei";
	}

	// 跳转到分配成功页
	@RequestMapping("/chengong")
	public String chengong() {

		return "app/friend/fenpeisuccess";
	}

	// 访问朋友页面
	@RequestMapping("/pageFriend")
	public String pageFriend() {
		return "app/friend/friends";
	}

	// 访问消息中心
	@RequestMapping("/news")
	public String news() {
		return "app/personal/news";
	}

	// 访问我的车辆页面
	@RequestMapping("/mycar")
	public String mycar() {
		return "app/personal/mycar";
	}

	// 登陆成功进燃油首页
	@RequestMapping("/ranshou")
	public String ranshou(RedirectAttributes attributes,HttpServletRequest request) {
		Gasstation gasstation=(Gasstation)request.getSession().getAttribute(Constants.GASSTION);
		attributes.addAttribute("onlyPhone",gasstation.getMobilePhone());
		return "redirect:/app/reranshou";
	}
	@RequestMapping("/reranshou")
	public String reranshou() {
		return "app/tenants/client";
	}

	// 燃油消费记录
	@RequestMapping("/ranyoujilu")
	public String ltjl() {
		return "app/personal/xiaofeijilu2";
	}

	// 登陆成功进轮胎首页
	@RequestMapping("/taishou")
	public String taishou(RedirectAttributes attributes,HttpServletRequest request) {
		Gasstation gasstation=(Gasstation)request.getSession().getAttribute(Constants.GASSTION);
		attributes.addAttribute("onlyPhone",gasstation.getMobilePhone());
		return "redirect:/app/retaishou";
	}
	@RequestMapping("/retaishou")
	public String retaishou() {
		return "app/tenants/client1";
	}

	// 商户记录
	@RequestMapping("/luntaijilu")
	public String shanghu() {
		return "app/personal/xiaofeijilu";
	}

	// 商户端进入我的
	@RequestMapping("/shanghuwo")
	public String shanghuwo() {
		return "app/tenants/gName";
	}

	// 商户端进入找回密码
	@RequestMapping("/shanghumi")
	public String shanghumi() {
		return "app/tenants/shanghuzhaohuipsd";
	}

	// 扫一扫二维码
	@RequestMapping("/flicking")
	public String flicking(Model model, @RequestParam("gid") String gid, @RequestParam("gsType") String gsType,
			@RequestParam(value = "uid", required = true) String uid) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (gid != null || gid != "" || gsType != null && gsType != "") {
				model.addAttribute("gid", gid);
				model.addAttribute("uid", uid);
				model.addAttribute("gsType", gsType);
			}
		} catch (Exception e) {
			jsonObject.put("msg", 400);
			e.printStackTrace();
			return "";
		}
		return "app/personal/fukuanoil";
	}

	// 轮胎付款成功
	@RequestMapping("/paysuccess")
	public String paysuccess() {

		return "/app/luntaizhuanqu/paysuccess";
	}

	// 手机端退出系统
	@RequestMapping(value = "/userlogout")
	public String userlogout(HttpSession session) throws Exception {
		// 清除Session
		session.invalidate();

		return "app/personal/logoname";
	}

	// 扫一扫 购买成功跳转到成功页面

	@RequestMapping("/buyoilsuccess")
	public String buyoilsuccess(@RequestParam("zhi") Integer zhi, Model model) {
		model.addAttribute("zhi", zhi);
		return "/app/luntaizhuanqu/buyoilsuccess";
	}
	
	// 扫一扫 跳转到购买界面
	
	@RequestMapping("/fukuanoil")
	public String fukuanoil() {
		
		return "/app/personal/fukuanoil";
	}
	//点击服务费进入服务费明细
	@RequestMapping("/fuwufei")
	public String fuwufei() {
		return "app/tenants/fuwujl";
	}

}
