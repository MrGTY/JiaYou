<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cxt"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>召集联盟注册账号</title>
<meta name="keywords" content="召集联盟注册账号">
<meta name="description" content="召集联盟注册账号">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/csscont.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/app.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/statics/app/pc/js/layui/css/layui.css">
<style type="text/css">
#minut {
	display: none;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/app/layui/layui.js"></script>
<script type="text/javascript">
	var path = "${cxt}";
</script>
<script type="text/javascript">
	$(function() {
		var path = "${cxt}";
		var pattern = /^1[34578]\d{9}$/;
		layui.use('layer', function() {
			$("#hqyzm").click(function() {
				var phoneNum = $("#Num").val();
				if ($.trim(phoneNum) == "") {
					$("#Num").focus();
					layer.tips('请输入手机号', '#Num', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else if (!pattern.test(phoneNum)) {
					$("#Num").focus();
					layer.tips('手机号格式有误', '#Num', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				}else{
					$.ajax({
						url : path+"/Zcyzm",
						type : "GET",
						data :{
							mobile : phoneNum
						},
						dataType : "JSON",
						success : function(data) {
							$("input:hidden[name='pyzm']").val(data.pyzm);
							if (data.msg == "505") {
								$("#Num").focus();
								layer.tips('手机号已存在', '#Num', {
									tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
								return;
							} else if (data.msg == "-1") {
								$("#Num").focus();
								layer.tips('获取验证码失败!', '#Num', {
									tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
								return;
							} else if (data.msg == "1") {
								$("#Zcyzm").focus();
								layer.tips('验证码已发送,请注意查收', '#Zcyzm', {
									tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
								$("#hqyzm").hide();
								$("#minut").show();
								var times = 60;
								var timer = null;
								// 计时开始
								timer = setInterval(function() {
									times--;
									if (times < 0) {
										$("#hqyzm").show();
										$("#minut").hide();
										clearInterval(timer);
										times = 60;
									} else {
										$("#minut").text(times + "秒后重试");
									}
								}, 1000);
							}
						}
					});
				}
			})


			$("#queding").click(function() {
				var userName = $("input:text[name='userName']").val();
				var vipName = $("input:text[name='vipName']").val();
				var phoneNum = $("#Num").val();
				var password = $("input:password[name='password']").val();
				var busNum = $("input:text[name='busNum']").val();
				var Zcyzm = $("input:text[name='Zcyzm']").val();
				var pyzm = $("input:hidden[name='pyzm']").val();
				var checkbox = $("input:checkbox[name='layout']:checked").val();
				var hiddend=$("#hiddend").val();
				if(hiddend==null || $.trim(hiddend)==""){
					$("#Zcyzm").focus();
					layer.tips('请获取验证码', '#Zcyzm', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else if ($.trim(userName) == "") {
					$("input:text[name='userName']").focus();
					layer.tips('请输入用户名', '#userName', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else if ($.trim(phoneNum) == "") {
					$("#Num").focus();
					layer.tips('请输入手机号', '#Num', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else if (!pattern.test(phoneNum)) {
					$("#Num").focus();
					layer.tips('手机号格式有误', '#Num', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else if ($.trim(password) == "") {
					$("#password").focus();
					layer.tips('请输入密码', '#password', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else if ($.trim(password).length < 6) {
					$("#password").focus();
					layer.tips('密码至少为6位数', '#password', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else if ($.trim(busNum) == "") {
					$("#busNum").focus();
					layer.tips('请输入车牌号', '#busNum', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else if ($.trim(Zcyzm) == "") {
					$("#Zcyzm").focus();
					layer.tips('请输入验证码', '#Zcyzm', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else if (checkbox==undefined) {
					$("#c1").focus();
					layer.tips('请同意集卡购油平台协议', '#c1', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else {
					$.ajax({
						url : path + "/addVipuserinfo",
						type : "POST",
						data : {
							userName : userName,
							vipName : vipName,
							phoneNum : phoneNum,
							password : password,
							busNum : busNum,
							Zcyzm : Zcyzm,
							pyzm : pyzm
						},
						dateType : "JSON",
						success : function(data) {
							if (data.msg == "-1") {
								layer.tips('注册失败!', '#queding', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
								return;
							} else if (data.msg == "401") {
								layer.tips('密码长度不得少于6位!', '#queding', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								}) ;
							} else if (data.msg == "505") {
								$("#Zcyzm").focus();
								layer.tips('您输入的验证码不正确!', '#queding', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								}) ;
								return;
							} else if (data.msg == "400") {
								layer.tips('请求超时，请稍后重试!', '#queding', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								}) ;
								return;
							} else if (data.msg == "1") {
								window.location.href = "login";
							}
						}
					});
				}

			})
		})
	});
</script>
</head>

<body>
	<section class="w100">
	<div class="w100">
		<ul class=" w94 logotable">
			<li>
				<div class="border-r">
					<span>会员昵称：</span><input class="comp_input" name="vipName" id="vipName" type="text" class="comp_input_class" />
				</div>
			</li>
			<li>
				<li>
					<div class="border-r">
						<span>真实姓名：</span><input name="userName" id="userName" type="text" class="comp_input_class" />
					</div>
			</li>
				<li>
					<div class="border-r">
						<span>手机号码：</span><input class="comp_input_class" name="phoneNum" type="text" id="Num" />
					</div>
			</li>
				<li>
					<div class="border-r">
						<span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input
							name="password" type="password" id="password" class="comp_input_class" />
					</div>
			</li>
				<li>
					<div class="border-r">
						<span>车牌号：</span><input class="comp_input_class" name="busNum" type="text" id="busNum" />
					</div>
			</li>
				<li class="clearfix"><input type="hidden" name="pyzm" id="hiddend" value="" />
					<div class="left border-r  border-s">
						<span>验&nbsp;证&nbsp;码：</span><input class="comp_input_class" name="Zcyzm" type="text"
							id="Zcyzm" />
					</div>
					<div class="right colord tst" id="hqyzm">获取验证码</div>
					<div class="right colord tst" id="minut">60秒后重试</div></li>
		</ul>
		
		 <div class="w94 logobox">
                <div><input class="magic-checkbox" type="checkbox" name="layout" value="0" id="c1">
                &nbsp;<label class="colord" for="c1">我已阅读并同意</label><a href="${pageContext.request.contextPath}/xieyi">《集卡购油平台协议》</a></div>
            </div>
		<div class="w94">
			<input class="OKbtn" type="button" id="queding"
				value="注&nbsp;&nbsp;册" />
		</div>

		<div class="logoa w94 colord">
			已有账号&nbsp;&nbsp;<a
				href="${pageContext.request.contextPath}/app/login">登录&nbsp;></a>
		</div>
	</div>
	</section>
	<script
		src="${pageContext.request.contextPath}/statics/app/pc/js/jquery-1.9.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/statics/app/pc/js/layui/layui.js"></script>
	<script
		src="${pageContext.request.contextPath}/statics/app/pc/js/index.js"></script>
</body>
</html>
