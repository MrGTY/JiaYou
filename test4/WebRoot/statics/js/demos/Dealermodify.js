function back1(){
	window.history.back(-1);
};
function backlist(){
	window.location.href="showlist"
};
$(function() {
	$("#phone").bind("blur", function() {
		// ajax后台验证--code是否已存在
		var uname=$("#phone").val();
		$.ajax({
			type : "GET",// 请求类型
			url : "uname.json",// 请求的url
			data : {
				uname : $("#phone").val()
			},// 请求参数
			dataType : "json",// ajax接口（请求url）返回的数据类型
			success : function(data) {// data：返回数据（json对象）
				if (data.code == "exist") {// 账号不可用，错误提示
					swal("帐号已存在，不能使用！");
					$("#phone").val("");
				} else if (data.code == "noexist") {// 账号可用，正确提示
					$("#codename").val(uname);
				}
			},
			error : function(data) {// 当访问时候，404，500 等非200的错误状态码
				swal("请求错误！");
			}
		});
	});
	$("#saves").click(function() {

		// 站点名称
		var zdname = document.getElementById("zdname");
		if (zdname.value.trim().length > 45) {
			zdname.setCustomValidity("站点名称长度<45个字符）");
		} else if (zdname.validity.valueMissing == true) {
			// 自定义提示信息
			zdname.setCustomValidity("站点名称不能为空");
		} else if (!zdname.value) {
			zdname.setCustomValidity("站点名称已存在");
		} else {
			zdname.setCustomValidity("");
		}
		// 详细地址
		var address = document.getElementById("address");
		if (address.validity.valueMissing == true) {
			address.setCustomValidity("详细地址不能为空");
		} else {
			address.setCustomValidity("");
		}

		// 经度
		var jd = document.getElementById("jd");
		if (jd.validity.valueMissing == true) {
			jd.setCustomValidity("经度不能为空，请获取经度！");
		} else if (isNaN(jd.value)) {
			jd.setCustomValidity("经度必须是数字");
		} else {
			jd.setCustomValidity("");
		}
		// 纬度
		var wd = document.getElementById("wd");
		if (wd.validity.valueMissing == true) {
			wd.setCustomValidity("纬度不能为空，请获取纬度！");
		} else if (isNaN(wd.value)) {
			wd.setCustomValidity("纬度必须是数字");
		} else {
			wd.setCustomValidity("");
		}
		// 商户覆盖半径
		var sh = document.getElementById("sh");
		if (sh.validity.valueMissing == true) {
			sh.setCustomValidity("商户覆盖半径不能为空");
		} else if (isNaN(sh.value)) {
			sh.setCustomValidity("商户覆盖半径必须是数字");
		} else {
			sh.setCustomValidity("");
		}
		// 联系人
		var lxr = document.getElementById("lxr");
		if (lxr.validity.valueMissing == true) {
			lxr.setCustomValidity("请输入联系人");
		} else {
			lxr.setCustomValidity("");
		}
		// 手机号
		var phone = document.getElementById("phone");
		if (phone.validity.valueMissing == true) {
			phone.setCustomValidity("请输入手机号");
		} else if (phone.validity.patternMismatch == true) {
			phone.setCustomValidity("手机号格式有误");
		} else {
			phone.setCustomValidity("");
		}
		// 初始油量
		var cs = document.getElementById("cs");
		if (cs.validity.valueMissing == true) {
			cs.setCustomValidity("请输入初始油量");
		} else if (isNaN(cs.value)) {
			cs.setCustomValidity("请输入数字");
		} else {
			cs.setCustomValidity("");
		}
		//元/L
		var fuwu = document.getElementById("fuwu");
		if (fuwu.validity.valueMissing == true) {
			fuwu.setCustomValidity("请输出初始服务费(例:0.1)");
		}else if (isNaN(fuwu.value)) {
			fuwu.setCustomValidity("请输入数字");
		}else if(fuwu.value<=0){
			fuwu.setCustomValidity("服务费单价必须大于0");
		}else {
			fuwu.setCustomValidity("");
		}
		// 请输入账号
		var uname = document.getElementById("uname2");
		if (uname.validity.valueMissing == true) {
			uname.setCustomValidity("请输入账号");
		} else {
			uname.setCustomValidity("");
		}
		// 请输入密码
		var pwd = document.getElementById("pwd");
		if (pwd.validity.valueMissing == true) {
			pwd.setCustomValidity("请输入密码");
		} else if (pwd.value.trim().length < 6) {
			pwd.setCustomValidity("密码至少为6位");
		} else {
			pwd.setCustomValidity("");
		}
		$("#saves").submit();
	});
});