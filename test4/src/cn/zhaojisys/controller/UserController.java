package cn.zhaojisys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import cn.zhaojisys.pojo.EmployeInfo;
import cn.zhaojisys.service.EmployeService;
import cn.zhaojisys.tools.Constants;

@Controller
public class UserController {
	@Autowired
	EmployeService eService;
	Logger logger=Logger.getLogger(ApplyController.class);
	// 查询员工信息
	@RequestMapping(value = "findAllEmploye")
	public ModelAndView selectAll(HttpSession httpSession,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			Model model) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("peopleMana");
			int newPageNo = 1;
			int totalPageNo;
			int totalPage = eService.count();
			if (pageNo != null && pageNo != 0) {
				newPageNo = pageNo;
			}
			int pageSize=Constants.pageSize;
			List<EmployeInfo> selectAllEmpPa=eService.findAllEmploye((newPageNo-1)*pageSize,pageSize);
			totalPageNo = totalPage % pageSize == 0 ? totalPage / pageSize: totalPage / pageSize + 1;
			logger.info("-------------------------"+totalPageNo);
			model.addAttribute("selectAllEmpPa",selectAllEmpPa);
			model.addAttribute("pageNo",newPageNo);
			model.addAttribute("totalPageNo",totalPageNo);
		} catch (Exception e){
			e.printStackTrace();
		}
		return mav;
	}

	// 新增员工信息
	@RequestMapping(value = "addemploye")
	public String addEmploye(EmployeInfo employeInfo,Model model) {
		try {
			if(eService.addEmploye(employeInfo)){
				return "redirect:/findAllEmploye";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("message", "添加失败");
		return "peopleMana";
	}

		/*@RequestMapping(value = "addemploye")
		@ResponseBody
		public String addEmploye(EmployeInfo employeInfo) {
			Map<String, String> map = new HashMap<String, String>();
			try {
				eService.addEmploye(employeInfo);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mas", "400");
			}
			return JSON.toJSONString(map);
		}
*/
		// 判断员工手机号码是否存在
		@RequestMapping(value = "selectPhone")
		@ResponseBody
		public String selectPhone(@RequestParam("mobilePhone") String mobilePhone) {
			Map<String, String> map = new HashMap<String, String>();
			EmployeInfo employeInfo = null;
			try {
				employeInfo = eService.selectPhone(mobilePhone);
				if (employeInfo != null) {
					map.put("mas", "-1");
				} else {
					map.put("mas", "1");
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mas", "400");
			}
			return JSON.toJSONString(map);
		}

		// 判断员工登录名是否存在
		@RequestMapping(value = "selectName")
		@ResponseBody
		public String selectName(@RequestParam("loginName") String loginName) {
			Map<String, String> map = new HashMap<String, String>();
			EmployeInfo employeInfo = null;
			try {
				employeInfo = eService.selectName(loginName);
				if (employeInfo != null) {
					map.put("mas", "-1");
				} else {
					map.put("mas", "1");
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mas", "400");
			}
			return JSON.toJSONString(map);
		}

	// 通过Id查询员工信息并点击编辑
	@RequestMapping(value = "selectEmployeById/{id}")
	public String selectEmployeById(@PathVariable String id, Model model) {
		List<EmployeInfo> list=new ArrayList<EmployeInfo>();
		//EmployeInfo employeInfo=null;
		try {
			list=eService.selectEmployeById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("emp",list.get(0));
		return "updatepeopleMana";
	}

	// 编辑员工信息
	@RequestMapping(value = "/updateEmploye",method=RequestMethod.POST)
	public String updateEmploye(EmployeInfo employeInfo) {
		try {
			eService.updateEmploye(employeInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/findAllEmploye";
	}

	// 删除员工信息
	@RequestMapping(value = "deleteEmploye",method=RequestMethod.GET)
	@ResponseBody
	public Object deleteEmploye(String id) {
		List<EmployeInfo> list=new ArrayList<EmployeInfo>();
		//EmployeInfo employeInfo=null;
		Map<String, String> map= new HashMap<String, String>();
		try {
			list=eService.selectEmployeById(id);
			if(!equals(list)){
				eService.deleteEmploye(id);
				map.put("mas","success");
			}else{
				map.put("mas", "failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mas", "wrong");
		}
		return JSONArray.toJSONString(map);
	}

	// 增加员工页面跳转
	@RequestMapping("/go")
	public String go() {
		return "peopleManaAdd";
	}
}
