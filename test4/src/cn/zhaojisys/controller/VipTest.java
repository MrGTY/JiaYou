package cn.zhaojisys.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.zhaojisys.pojo.Gasstation;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.service.VipuserinfoService;
import cn.zhaojisys.service.app.ApppService;
import cn.zhaojisys.tools.Constants;

@Controller
@RequestMapping("/vip")
public class VipTest {
	private Logger logger = Logger.getLogger(VipTest.class);
	@Resource
	private VipuserinfoService vips;
	@Resource
	private ApppService apppService;
		
	@RequestMapping(value = "/fenpei", method = RequestMethod.GET)
	public String fenye() {
		return "vipaddoiljilu";
	}

	@RequestMapping(value = "/getvip.json")
	@ResponseBody
	public Object getVip(
			@RequestParam(value = "name", required = false) String name) {
		List<Vipuserinfo> list = null;
		try {
			list = vips.getVipuserinfoByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.toJSONString(list);
	}

	// 好友分配
	@RequestMapping(value = "/fenpei", method = RequestMethod.POST)
	public String fensave(
			HttpServletRequest request,
			@RequestParam(value = "vipname1", required = false) String vipname1,
			@RequestParam(value = "vipname2", required = false) String vipname2,
			@RequestParam(value = "fenpeilu", required = false) String sheng) {
		try {
			if (vips.getFenPei(request, vipname1, vipname2,
					Integer.parseInt(sheng))) {
				return "redirect:/vipuserinfo/vipindex";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "vipaddoiljilu";
	}

	@RequestMapping(value = "/jiayou", method = RequestMethod.GET)
	public String jiayou() {
		return "vipoiljilu";
	}

	@RequestMapping(value = "/getgass.json")
	@ResponseBody
	public Object getGass(
			@RequestParam(value = "name", required = false) String name) {
		List<Gasstation> list = null;
		try {
			list = vips.getGasstationByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.toJSONString(list);
	}

	// 加油
	@RequestMapping(value = "/jiayou", method = RequestMethod.POST)
	public String jiasave(
			HttpServletRequest request,
			@RequestParam(value = "vipname1", required = false) String vipname1,
			@RequestParam(value = "vipname2", required = false) String vipname2,
			@RequestParam(value = "fenpeilu", required = false) String sheng) {
		try {
			if (vips.getJiaYou(request, vipname1, vipname2,
					Integer.parseInt(sheng))) {
				return "redirect:/vipuserinfo/vipindex";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "vipoiljilu";
	}

	// 分类
	@RequestMapping(value = "/fenlei.json")
	@ResponseBody
	public Object getVipNameByType(
			@RequestParam(value = "name", required = false) String name) {
		List<Vipuserinfo> list = null;
		try {
			list = vips.getNewVipByUserType(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONArray.toJSONString(list);
	}

	// 注册会员
	@RequestMapping(value = "/sign", method = RequestMethod.GET)
	public String addVip(@RequestParam(value="id",required=false)Integer id,Model model) {
		Vipuserinfo vip=null;
		try {
			vip=vips.selectAllbyId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("vip", vip);
		model.addAttribute("id", id);
		return "vipsign";
	}

	// 注册会员保存
	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public String addVipSave(
			Vipuserinfo vipuserinfo,
			Integer id,
			Model model,
			HttpServletRequest request,
			@RequestParam("submit")String submit,
			@RequestParam(value = "logoPicPath", required = false) MultipartFile attach) {
		String logoLocPath = null;
		String newFileName = null;
		if (!attach.isEmpty()) {
			String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "appimg");
			logger.info("uploadFile path: " + path);
			String oldFileName = attach.getOriginalFilename();// 原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);// 原文件后缀
			Date date = new Date();
			newFileName = String.valueOf(date.getTime()) + oldFileName;
			int filesize = 3145728;// 1024*1024*3 上传大小不得超过 3M
			if (attach.getSize() > filesize) {// 上传大小不得超过 3M
				request.setAttribute("fileUploadError", " 上传大小不得超过 3M！ ");
				return "vipsign";
			} else if (prefix.equalsIgnoreCase("jpg")
					|| prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg")
					|| prefix.equalsIgnoreCase("pneg")
					|| prefix.equalsIgnoreCase("bmp")) {// 上传图片格式
				File targetFile = new File(path, newFileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("fileUploadError", " * 上传失败！");
					return "vipsign";
				}
				// logoPicPath =
				// request.getContextPath()+"/statics/uploadfiles/"+fileName;
				//logoLocPath = path + File.separator + newFileName;
				logoLocPath = "http://jy.jxgyl.com.cn/statics/appimg/"+ newFileName;
			} else {
				request.setAttribute("fileUploadError", " * 上传文件格式不正确！");
				return addVip(id,model);
			}
		}
		vipuserinfo.setCreateTime(Constants.getNewDate());
		vipuserinfo.setUploadImg(logoLocPath);
		vipuserinfo.setVipName(vipuserinfo.getUserName());
		try {
			if("保存".equals(submit)){
				if (vips.addVip(vipuserinfo) > 0) {
					return "redirect:/vipuserinfo/vipindex";
				}			
			}else if("修改".equals(submit)){
				if (vips.updateVip(vipuserinfo) > 0) {
					return "redirect:/vipuserinfo/vipindex";
				}	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addVip(id,model);
	}

	// 验证有木有
	@RequestMapping(value = "shuaige")
	@ResponseBody
	public Object getPhoe(@RequestParam("mobile") String mobile) {
		JSONObject jsonObject = new JSONObject();
		Vipuserinfo vipuserinfo = null;
		try {
			vipuserinfo = apppService.loginphone(mobile);
			if (vipuserinfo == null) {
				jsonObject.put("msg", "1");// 手机号已存在
			} else {
				jsonObject.put("msg", "505");// 手机号已存在
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonObject);
	}
}
