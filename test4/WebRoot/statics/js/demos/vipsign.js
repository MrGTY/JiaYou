$(function () {
    $("#saves").click(function () {
        //登录账号
        var zdname = document.getElementById("uname");
        if (zdname.validity.valueMissing == true) {
            //自定义提示信息
            zdname.setCustomValidity("真实姓名不能为空");
        }else {
            zdname.setCustomValidity("");
        }
        //设置密码
        var password = document.getElementById("pwd");
        if (password.validity.valueMissing == true) {
            password.setCustomValidity("密码不能为空！");
        } else if (password.validity.patternMismatch == true) {
            password.setCustomValidity("密码长度必须为6-10位！");
        } else {
            password.setCustomValidity("");
        }
        //姓名
        var lxr = document.getElementById("logoPicPath");
        if (lxr.validity.valueMissing == true) {
            lxr.setCustomValidity("请上传行驶证");
        }
        else{
            lxr.setCustomValidity("");
        }
        //手机号
        var phone = document.getElementById("phone");
        if (phone.validity.valueMissing == true) {
            phone.setCustomValidity("请输入手机号");
        }else if (phone.validity.patternMismatch == true) {
            phone.setCustomValidity("手机号格式有误");
        }
        else{
            phone.setCustomValidity("");
        }
    });
    $("#phone").bind("blur", function() {
		// ajax后台验证--code是否已存在
		var uname=$("#phone").val();
		$.ajax({
			type : "GET",// 请求类型
			url : "shuaige",// 请求的url
			data : {
				mobile : uname
			},// 请求参数
			dataType : "json",// ajax接口（请求url）返回的数据类型
			success : function(data) {// data：返回数据（json对象）
				if (data.msg == "505") {// 账号不可用，错误提示
					swal("帐号已存在，不能使用！");
					$("#phone").val("");
					$("#phone").focus();
				}
			},
			error : function(data) {// 当访问时候，404，500 等非200的错误状态码
				swal("请求错误！");
			}
		});
	});
});