package cn.zhaojisys.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONArray;
import cn.zhaojisys.pojo.AppVersion;
import cn.zhaojisys.service.AppService;
import cn.zhaojisys.tools.Constants;

@Controller
public class AppController {
	private Logger logger=Logger.getLogger(AppController.class);
	@Autowired
	AppService appService;

	// 查询所有版本信息
	@RequestMapping(value = "selectAllApp")
	public String selectAllApp(Model model) {
		List<AppVersion> list = new ArrayList<AppVersion>();
		try {
			list = appService.selectAllApp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("lis", list);
		return "app";
	}

	// 根据Id选择
	@RequestMapping("/selectAllAppById/{id}")
	@ResponseBody
	public String selectAllAppById(@PathVariable String id, Model model) {
		AppVersion appVersion = null;
		try {
			appVersion = (AppVersion) appService.selectAllAppById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("app", appVersion);
		return "11";
	}

	// 上传版本信息
	@RequestMapping(value="/addApp",method=RequestMethod.POST)
	public String add(AppVersion appVersion,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value="a_downloadLink" , required = false) MultipartFile attach,Model model){
		String fileName=null;
		if(!attach.isEmpty()){//判断文件是否为空
			String path= request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			System.out.println("++++++++++++++++++>"+path);
			String oldFileName = attach.getOriginalFilename();//获得用户上传的文件名
			String prefix=FilenameUtils.getExtension(oldFileName);//通过用户上传的文件名得到他的后缀  
			int fileSize=10485760;
			if(attach.getSize()>fileSize){
				model.addAttribute("message", "文件太大了");
				return selectAllApp(model);
			}else if(prefix.equalsIgnoreCase("apk")){
				fileName=appVersion.getVersionNumber()+".apk";//得到我的新文件名
				File file =new File(path,fileName);//通过路劲加文件名得到file对象
				if(!file.exists()){//如果文件不存在 我给他创建
					file.mkdirs();
				}
				//执行保存
				try {
					attach.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "上传失败!");
					return selectAllApp(model);
				}
			}else{
				model.addAttribute("message", "文件格式错误!请上传APK文件!");
				return selectAllApp(model);
			}
		}else{//如果是空，就返回原来的增加页面
			model.addAttribute("message", "文件不能为空");
			return selectAllApp(model);
		}
		appVersion.setDownloadLink(fileName);
		try {
			appService.addApp(appVersion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "上传成功！");
		return "redirect:/selectAllApp";
		
	}
	// 删除版本信息
	@RequestMapping(value = "deleteApp",method=RequestMethod.GET)
	@ResponseBody
	public Object deleteApp(String id,HttpSession session,HttpServletRequest request) {
		String downloadLink=null;
		AppVersion appVersion=null;
		String path= request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
		Map<String, String> map= new HashMap<String, String>();
		try {
			appVersion=appService.selectAllAppById(id);
			downloadLink=path+File.separator+appVersion.getDownloadLink();
			if(Constants.deleteQrcode(downloadLink)){
				appService.deleteApp(id);
				map.put("mas", "success");
			}else{
				map.put("mas", "path");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mas", "failure");
		}
		return JSONArray.toJSONString(map);
	}
}
