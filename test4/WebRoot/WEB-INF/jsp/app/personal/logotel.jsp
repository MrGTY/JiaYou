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

<title>召集联盟账号登录</title>
<meta name="keywords" content="召集联盟账号登录">
<meta name="description" content="召集联盟账号登录">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/csscont.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/app.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/radio.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/statics/app/pc/js/layui/css/layui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/app/layui/layui.js"></script>
<style type="text/css">
#minut {
	display: none;
}
</style>
<script type="text/javascript">
	$(function() {
		var path = "${cxt}";
		var pattern = /^1[34578]\d{9}$/;
		layui.use('layer', function() {
			//失去焦点，判断账户是否被禁用
			$("#Numsf").blur(function() {
				var phoneNum = $("#Num").val();
				var pattern = /^1[23456789]\d{9}$/;
				if ($.trim(phoneNum) == "") {
					layer.tips('请输入手机号', '#Num', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else if (!pattern.test(phoneNum)) {
					layer.tips('手机号格式有误', '#Num', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else {
					$.ajax({
						url : path + '/app/codeIsOk',
						type : 'post',
						data : {
							phoneNum : phoneNum
						},
						dataType : 'json',
						success : function(data) {
							if (data.msg == -1) {
								$("#hqyzm").hide();
								layer.tips('该账户被禁用', '#Num', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
								return;
							} else if (data.msg == 1) {
								$("#hqyzm").show();
							} else {
								layer.tips('程序异常!请稍后重试', '.OKbtn', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
								return;
							}
						}
					});
				}
			});

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
				} else {
					$("#yzm").focus();
					layer.tips('验证码已发送,请注意查收', '#yzm', {
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
					$.ajax({
						url : "yzm",
						type : "GET",
						data : {
							mobile : phoneNum
						},
						dateType : "JSON",
						success : function(data) {
							var result = JSON.parse(data);
							if (result.msg == 1) {
								$("input:hidden[name='pyzm']").val(result.pyzm);

							} else if (result.msg == "-1") {
								$("#Num").focus();
								layer.tips('获取验证码失败!', '#Num', {
									tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
								return;
							}
						}
					});
				}
			})


			$("#queding").click(function() {
				var phoneNum = $("#Num").val();
				var loginType = $("input:radio[name='loginType']:checked").val();
				var yzm = $("input:text[name='yzm']").val();
				var pyzm = $("input:hidden[name='pyzm']").val();
				var mobilePhone = $("#Num").val();
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
				} else if ($.trim(yzm) == "") {
					$("#yzm").focus();
					layer.tips('请输入验证码', '#yzm', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else if (loginType == undefined) {
					$("#loginType").focus();
					layer.tips('请选择登录类型', '#r1', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else {
					$.ajax({
						url : path + "/loginphone",
						type : "GET",
						data : {
							phoneNum : phoneNum,
							loginType : loginType,
							mobilePhone : mobilePhone,
							yzm : yzm,
							pyzm : pyzm
						},
						dateType : "JSON",
						success : function(data) {
							if (data.msg == "11") {
								$("#queding").focus();
								layer.tips('您的账户已被禁用', '#queding', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
								return;
							} else if (data.msg == "-1") {
								$("#Num").focus();
								layer.tips('请输入手机号', '#Num', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
								return;
							} else if (data.msg == "400") {
								layer.tips('请求超时，请稍后重试!', '#queding', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								}) ;
								return;
							} else if (data.msg == "505") {
								$("#yzm").focus();
								layer.tips('您输入的验证码不正确!', '#yzm', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
								return;
							} else if (data.msg == "1") {
								window.location.href = path + "/app/shouye";
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
		<div class="logoimg">
			<img
				src="${pageContext.request.contextPath}/statics/app/images/676992733453837780.png" />
		</div>
		<form action="">
			<ul class=" w94 logotable">
				<li>
					<div class="border-r">
						<span>手机号码：</span><input name="phoneNum" class="comp_input"
							type="text" id="Num" />
					</div>
				</li>
				<li class="clearfix"><input type="hidden" name="pyzm"
					value="132" />
					<div class="left border-r  border-s">
						<span>验&nbsp;证&nbsp;码：</span><input class="comp_input" name="yzm"
							id="yzm" type="text" />
					</div>
					<div class="right colord tst" id="hqyzm">获取验证码</div>
					<div class="right colord tst" id="minut">60秒后重试</div></li>

				<li>
					<div class="col">
						<div class="opt">
							<input class="magic-radio" type="radio" name="loginType"
								checked="checked" id="r1" value="0"> <label for="r1">车主/物流公司</label>
						</div>
						<div class="opt">
							<input class="magic-radio" type="radio" name="loginType" id="r2"
								value="1"> <label for="r2">站点</label>
						</div>
					</div>
				</li>
			</ul>
			<div class=" w94">
				<input class="OKbtn" id="queding" type="button"
					value="确&nbsp;&nbsp;认" />
			</div>
		</form>
		<div class="w94 colord clearfix">
			<div class="left">
				<a href="${pageContext.request.contextPath}/app/login">召集联盟账号登录</a>
			</div>
		</div>

		<div class="logoa w94">
			<!-- <a href="gotoapps://register">快速注册</a><i>|</i> -->
			<a href="${pageContext.request.contextPath}/app/zhaohuipsd">忘记密码</a>
		</div>
	</div>
	</section>
</body>
</html>
