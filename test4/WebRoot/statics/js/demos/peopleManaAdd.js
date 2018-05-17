$(function () {
    $("#saves").click(function () {
        //登录账号
        var zdname = document.getElementById("zdname");
        if (zdname.validity.valueMissing == true) {
            //自定义提示信息
            zdname.setCustomValidity("登录账号不能为空");
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
        //确认密码
        var rePassword = document.getElementById("repwd");
        if (rePassword.validity.valueMissing == true) {
            rePassword.setCustomValidity("确认密码不能为空");
        } else if (rePassword.validity.patternMismatch == true) {
            rePassword.setCustomValidity("确认密码长度为6-10位！");
        }else  if(password.value!=rePassword.value){
            rePassword.setCustomValidity("两次密码输入不一致");
        } else {
            rePassword.setCustomValidity("");
        }
        //姓名
        var lxr = document.getElementById("uname");
        if (lxr.validity.valueMissing == true) {
            lxr.setCustomValidity("请输入姓名");
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
});