<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cxt" value="${pageContext.request.contextPath}"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>找回密码</title>
<meta name="keywords" content="找回密码">
<meta name="description" content="找回密码">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/csscont.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/app.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/seed.css"/>
<script src="${pageContext.request.contextPath}/statics/app/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/app/layui/layui.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		var path="${cxt}";
	layui.use('layer', function() {
		$("#yam").click(function(){
			var mobile=$("input:text[name='mobile']").val();
			if(mobile==""||mobile==null){
				layer.tips('手机号不能为空!', '.OKbtn1', {
					tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
				});
			}else{
			$.ajax({
				type:"GET",
				url:path+"/app/shyzm",
				data:{
					mobile:mobile
				},
				datatype:"json",
				success: function(data){
				var result=JSON.parse(data);
				if(result.msg==1){
					$("#pyzm").val(result.pyzm);
						alert($("#pyzm").val());
					}else if(result.msg==400){
						layer.tips('手机号未注册！', '.OKbtn1', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
							});
						}
					},
				});
			}
		});
		$(".OKbtnsf").click(function(){
			var mobile=$("input:text[name='mobile']").val();
			var pyzm=$("#pyzm").val();
			var password1=$("#password1").val();
			var password2=$("#password2").val();
			var yzm=$("input:text[name='yzm']").val();
			if(mobile==""||yzm==""||password1==""||password2==""){
				layer.tips('请完善填写信息!', '.logotable', {
					tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
				});
		}else{
			$.ajax({
				type:"GET",
				url:path+"/app/shupdatepwd",
				data:{
					mobile:mobile,
					pyzm:pyzm,
					password1:password1,
					password2:password2,
					yzm:yzm
				},
				datatype:"json",
				success: function(data){
				var result=JSON.parse(data);
				if(result.msg==505){
					layer.tips('您输入的验证码不正确!', '.yzm', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
					}else if(result.msg==401){
					layer.tips('密码长度不得小于6位!', '#password1', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
					}else if(result.msg==404){
					layer.tips('输入的两次密码不一致!', '#password2', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
					}else if(result.msg==-1){
					layer.tips('服务器异常 密码修改失败!', '.OKbtnsf', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
					}else{
						window.history.back();location.reload();
					}
				},
			});	
			
			}
			
	});	
	$("#gobacksf").on("click",function(){
			window.history.back(); 
//			window.location.href=path+"/app/shouye";
		});
	});
});
</script>
</head>

<body>
	 <section class="w100">
       <div class="w100">
            <ul class=" w94 logotable">
                <li>
                    <div class="border-r"><span>手机号码：</span><input name="mobile" type="text" class="comp_input OKbtn1" /></div>
                </li>
                 <li class="clearfix">
                   	<div class="left border-r  border-s"><span>验&nbsp;证&nbsp;码：</span><input name="yzm" type="text" class="comp_input yzm"/></div>
                    <input value="" id="pyzm" type="hidden"/>
                    <div class="right colord tst"><a id="yam">获取验证码</a></div>
                </li>
                <li>
                    <div class="border-r"><span>新&nbsp;密&nbsp;&nbsp;码：</span><input id="password1"  type="password" class="comp_input" /></div>
                </li>
               <li>
                    <div class="border-r"><span>确认密码：</span><input id="password2"  type="password" class="comp_input" /></div>
                </li>
                
            </ul>
            <div class="w94">
            	<input class="OKbtns" name="" type="button" id="gobacksf" value="返&nbsp;&nbsp;回" />
            	<!-- <input class="OKbtn" name="" type="button" value="确&nbsp;&nbsp;认" /></div> -->
            	 <input class="OKbtnsf" name="" type="button" value="确&nbsp;&nbsp;认" />
            
            <div class="logoa w94 colord"><a href="">客服热线:400-168-9878</a></div>
     </div>
   </section>
</body>
</html>
