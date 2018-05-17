package cn.zhaojisys.controller.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.pojo.Messages;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.RelationShip;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.app.CarListService;
import cn.zhaojisys.service.app.RelationShipListService;
import cn.zhaojisys.service.app.TyreListService;
import cn.zhaojisys.tools.Constants;

@Controller
public class AppController2 {

	@Autowired
	private CarListService carListService;
	@Autowired
	private TyreListService tyreListService;
	@Autowired
	private RelationShipListService reShipService;

	// 车辆列表接口
	@RequestMapping("/carList")
	@ResponseBody
	public Object carList(HttpServletRequest request, @RequestParam("pageNo") int pageNo) {
		HttpSession session = request.getSession();
		Vipuserinfo vipuserinfo = (Vipuserinfo) session.getAttribute(Constants.VIPUSERINFO);
		JSONObject jsonObject = new JSONObject();
		if (vipuserinfo.getUserType() == 0) {
			// 车主登录后的车辆信息
			List<Vipuserinfo> list1 = carListService.carList(0, vipuserinfo.getId(), 0);// 第一个参数是物流公司的id，第二个参数是个人id
			if (list1.size() != 0) {
				jsonObject.put("msg", "1");
				jsonObject.put("list1", list1);
			} else {
				jsonObject.put("msg", "-1");
			}
		} else {
			// 物流公司登录后的车辆信息
			List<Vipuserinfo> list2 = carListService.carList(vipuserinfo.getId(), 0, (pageNo - 1) * 10);
			if (list2.size() != 0) {
				jsonObject.put("msg", "1");
				jsonObject.put("list2", list2);

			} else {
				jsonObject.put("msg", "-1");
			}

		}
		return jsonObject;
	}

	// 车主轮胎记录接口
	@RequestMapping("/tyreList")
	@ResponseBody
	public Object tyreList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Vipuserinfo vipuserinfo = (Vipuserinfo) session.getAttribute(Constants.VIPUSERINFO);
		List<Tyredatails> list1 = tyreListService.tyreList(vipuserinfo.getId());
		JSONObject jsonObject = new JSONObject();
		if (list1.size() != 0) {
			jsonObject.put("msg", "1");
			jsonObject.put("list1", list1);
		} else {
			jsonObject.put("msg", "-1");
		}
		return jsonObject;
	}

	// 消息查询接口
	@RequestMapping("/findMeg")
	@ResponseBody
	public Object findMeg(@RequestParam("pageNo") Integer pageNo, @RequestParam("msgType") Integer msgType,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Vipuserinfo vipuserinfo = (Vipuserinfo) session.getAttribute(Constants.VIPUSERINFO);
		List<Messages> list1;
		JSONObject jsonObject = new JSONObject();
		try {
			list1 = reShipService.findMsg(vipuserinfo.getId(), msgType, (pageNo - 1) * 10);
			if (list1.size() != 0) {
				jsonObject.put("msg", "1");
				jsonObject.put("list1", list1);
			} else {
				jsonObject.put("msg", "-1");
			}
		} catch (Exception e) {

			e.printStackTrace();
			jsonObject.put("msg", "400");
		}
		return jsonObject;

	}

	// 好友检索接口
	@RequestMapping("/findFriend")
	@ResponseBody
	public Object findFriend(HttpServletRequest request, @RequestParam("phoneNum") String phoneNum) {
		HttpSession session = request.getSession();
		Vipuserinfo vipuserinfo = (Vipuserinfo) session.getAttribute(Constants.VIPUSERINFO);
		JSONObject jsonObject = new JSONObject();
		Vipuserinfo vip;

		try {
			if (phoneNum.equals(vipuserinfo.getPhoneNum())) {
				jsonObject.put("msg", "-2");// 输入检索 的手机号是自己的手机号
			} else {
				vip = reShipService.relaShipList(phoneNum);
				if (vip != null) {
					jsonObject.put("vip", vip);
					jsonObject.put("msg", "1");

				} else {
					jsonObject.put("msg", "-1");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "400");
		}
		return jsonObject;

	}

	// 我的好友列表接口：
	// 查询当前用户的好友接口
	@RequestMapping("/findMyFriend")
	@ResponseBody
	public Object findMyFriend(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Vipuserinfo vipuserinfo = (Vipuserinfo) session.getAttribute(Constants.VIPUSERINFO);
		List<Vipuserinfo> list1;
		JSONObject jsonObject = new JSONObject();
		try {
			list1 = reShipService.friendList(vipuserinfo.getId());
			if (list1.size() != 0) {
				jsonObject.put("list1", list1);
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObject.put("msg", "400");
		}
		return jsonObject;

	}

	// 建立好友关系接口：
	@RequestMapping("/buildMyFriend")
	@ResponseBody
	public Object buildMyFriend(HttpServletRequest request, @RequestParam("friendId") int friendId) {
		HttpSession session = request.getSession();
		Vipuserinfo vipuserinfo = (Vipuserinfo) session.getAttribute(Constants.VIPUSERINFO);
		int a;
		JSONObject jsonObject = new JSONObject();
		try {
			List<RelationShip> rel = reShipService.isExistRelationShip(vipuserinfo.getId(), friendId);
			if (rel.size() > 0) {
				jsonObject.put("msg", "1");
			} else {
				a = reShipService.addFriend(vipuserinfo.getId(), friendId);

				if (a > 0) {
					jsonObject.put("msg", "1");
				} else {
					jsonObject.put("msg", "-1");// 没有添加成功的异常
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			return jsonObject.put("msg", "400");// 出现异常时
		}
		return jsonObject;
	}

	// 删除好友关系接口：
	@RequestMapping("/breakMyFriend")
	@ResponseBody
	public Object breakMyFriend(HttpServletRequest request, @RequestParam("friendId") int friendId) {
		HttpSession session = request.getSession();
		Vipuserinfo vipuserinfo = (Vipuserinfo) session.getAttribute(Constants.VIPUSERINFO);
		int a;
		JSONObject jsonObject = new JSONObject();
		try {
			a = reShipService.breakFriend(vipuserinfo.getId(), friendId);
			if (a > 0) {
				jsonObject.put("msg", "1");
			} else {
				jsonObject.put("msg", "-1");// 没有添加成功的异常
			}
		} catch (Exception e) {

			e.printStackTrace();
			return jsonObject.put("msg", "400");// 出现异常时
		}
		return jsonObject;
	}

	// 查询会员信息接口：
	@RequestMapping("/vipInfo")
	@ResponseBody
	public Object vipInfo(HttpServletRequest request,
			@RequestParam(value = "friendId", required = false) String friendId) {
		HttpSession session = request.getSession();
		Vipuserinfo vipuserinfo = (Vipuserinfo) session.getAttribute(Constants.VIPUSERINFO);

		Vipuserinfo vipInfo;
		Vipuserinfo vipInfo1;// 查询被选择的好友剩余油量信息

		JSONObject jsonObject = new JSONObject();
		try {
			vipInfo = reShipService.vipInfoList(vipuserinfo.getId());
			if (vipInfo != null) {
				jsonObject.put("msg", "1");
				jsonObject.put("oilMass", vipInfo.getOilMass());
				jsonObject.put("phoneNum", vipInfo.getPhoneNum());
			} else {
				jsonObject.put("msg", "-1");// 没有添加成功的异常
			}

			if (friendId != "" && null != friendId) {
				vipInfo1 = reShipService.vipInfoList(Integer.parseInt(friendId));
				if (vipInfo1 != null) {
					jsonObject.put("msg", "1");
					jsonObject.put("userName", vipInfo1.getUserName());
					jsonObject.put("phoneNum", vipInfo1.getPhoneNum());
					jsonObject.put("busNum", vipInfo1.getBusNum());
					jsonObject.put("oilMass", vipInfo1.getOilMass());
				} else {
					jsonObject.put("msg", "-1");// 没有添加成功的异常
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return jsonObject.put("msg", "400");// 出现异常时
		}
		return jsonObject;
	}

	// 油量分配接口接口：
	@RequestMapping("/giveOil")
	@ResponseBody
	public Object giveOil(HttpServletRequest request, @RequestParam("OilQuantity") int OilQuantity, // 分配量
			@RequestParam("ChooseId") int ChooseId) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject = reShipService.giveOil(OilQuantity, request, ChooseId);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return jsonObject; // 被选择好友的id

	}

	// 根据选择的id查询燃油记录
	@RequestMapping("/oilInfo")
	@ResponseBody
	public Object oilrecord(@RequestParam("vipUserId") int vipUserId) {
		JSONObject jsonObject = new JSONObject();
		List<Oilrecord> listOil = null;
		try {
			listOil = reShipService.saleRecord(vipUserId);
			if (listOil.size() != 0) {
				jsonObject.put("listOil", listOil);
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

	// 判断所查询手机号的会员和当前登录用户是否是好友接口+用户输入手机号查询是否是会员：
	@RequestMapping("/isExistFriends")
	@ResponseBody
	public Object isExistFriends(HttpServletRequest request, @RequestParam("phoneNum") String phoneNum) {
		HttpSession session = request.getSession();
		Vipuserinfo vipuserinfo = (Vipuserinfo) session.getAttribute(Constants.VIPUSERINFO);
		List<RelationShip> listRelationShips = null;
		Vipuserinfo vipInfo;
		JSONObject jsonObject = new JSONObject();
		try {
			vipInfo = reShipService.relaShipList(phoneNum);
			if (phoneNum.equals(vipuserinfo.getPhoneNum())) {
				jsonObject.put("msg", "-2");// 输入检索 的手机号是自己的手机号
			} else {
				vipInfo = reShipService.relaShipList(phoneNum);
				if (vipInfo != null) {
					// 判断是否是好友关系
					listRelationShips = reShipService.isExistRelationShip(vipuserinfo.getId(), vipInfo.getId());
					if (listRelationShips.size() > 0) {
						jsonObject.put("relation", "1");// 之间有好友关系

					} else {
						jsonObject.put("relation", "-1");// 之间没有好友关系
					}

					jsonObject.put("vip", vipInfo);
					jsonObject.put("msg", "1");

				} else {
					jsonObject.put("msg", "-1");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return jsonObject.put("msg", "400");// 出现异常时
		}
		return jsonObject;
	}
}
