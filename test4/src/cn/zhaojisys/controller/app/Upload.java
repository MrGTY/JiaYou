package cn.zhaojisys.controller.app;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/app")
public class Upload {

	@RequestMapping(value = "/imgupload", method = RequestMethod.POST)
	@ResponseBody
	public Object addSave(HttpSession session, HttpServletRequest request,Model model,
			@RequestParam(value = "file", required = false) MultipartFile attach) {
		JSONObject jsonObject=new JSONObject();
		//相对路径
		String logoPicPath = null;
		//绝地路径
		String logoLocPath = null;
		if (!attach.isEmpty()) {
			// 获得项目的路径+上传位置
			String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "appimg");
			System.out.println("path=="+path);
			// 原文件名
			String oldFileName = attach.getOriginalFilename();
			// 新文件名
			Date date=new Date();
			String newFileName = String.valueOf(date.getTime()) + oldFileName;
			// 原文件后缀
			String prefix = FilenameUtils.getExtension(oldFileName);
			int filesize = 3145728;//1024*1024*3   上传大小不得超过 3M
			if (attach.getSize() > filesize) {// 上传大小不得超过 3M
				request.setAttribute("fileUploadError", " * 上传文件过大！");
				jsonObject.put("msg", "-3");
				return jsonObject;
			} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")
					|| prefix.equalsIgnoreCase("bmp")) {// 上传图片格式
				String fileName = newFileName;// 上传LOGO图片命名:apk名称.apk
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					StringBuffer url = request.getRequestURL(); 
					//获取域名，如：http://f0rb.iteye.com/ 
					String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString(); 
//					String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString(); 
					//获取带部署环境上下文的域名，如： http://www.iteye.com/admin/ 
//					String tempContextUrltest = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();  
					//相对路径
					logoPicPath = request.getContextPath() + "/statics/appimg/" + fileName;
					attach.transferTo(targetFile);
//					jsonObject.put("pathimg", logoPicPath);
					jsonObject.put("pathimg", tempContextUrl+logoPicPath);
					jsonObject.put("msg", 1);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("fileUploadError", " * 上传失败！");
					jsonObject.put("msg", 400);
					return jsonObject;
				}
				//相对路径
				logoPicPath = request.getContextPath() + "/statics/appimg/" + fileName;
				//绝地路径
				logoLocPath = path + File.separator + File.separator;
				model.addAttribute("logoLocPath",logoPicPath);
			} else {
				request.setAttribute("fileUploadError", " * 上传文件格式不正确！");
				jsonObject.put("msg", -2);
				return jsonObject;
			}
		}
		return jsonObject;
	}
	/**
	 * 列出所有的图片
	 */
	@RequestMapping("/listFile")
	public String listFile(HttpServletRequest request,
			HttpServletResponse response) {
		//相对路径
		String realpath = request.getContextPath() + "/statics/uploadfiles/" ;
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		
		//绝地路径
		String logoLocPath = path + File.separator;
		System.out.println("logoLocPath=="+logoLocPath);
		// 上传位置
		String path1 = "statics" + File.separator + "uploadfiles/3675cdfb-43c7-49d6-b33a-50ec53e50934serv生命周期2.png";
		request.setAttribute("fileNameMap", path1);
		return "imglistFile";
	}
}
