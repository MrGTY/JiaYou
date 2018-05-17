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
<script type="text/javascript">
	var path = "${cxt}";
</script>
<script type="text/javascript">

	$(document).ready(function() {
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
								$(".okchoose").hide();
								layer.tips('该账户被禁用', '#Num', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
								return;
							} else if (data.msg == 1) {
								$(".okchoose").show();
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
		});

		var strName = localStorage.getItem('keyName');
		var strPass = localStorage.getItem('keyPass');
		var r1 = localStorage.getItem('r1');
		var r2 = localStorage.getItem('r2');
		if (strName) {
			$('#Num').val(strName);
		}
		if (strPass) {
			$('#password').val(strPass);
		}
		if (r1) {
			$('#r1').attr('checked', 'checked');
		}
		if (r2) {
			$('#r2').attr('checked', 'checked');
		}
		var path = "${cxt}";
		$("#btn").click(function() {
			layui.use('layer', function() {
				var phoneNum = $("#Num").val();
				var password = $("input:password[name='password']").val();
				var loginType = $("input:radio[name='loginType']:checked").val();
				var code = $("#Num").val();
				var pattern = /^1[23456789]\d{9}$/;
				if (loginType == undefined) {
					layer.tips('请选择登录类型', '#r1', {
						tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
					});
					return;
				} else if (loginType == 0) {
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
					} else if ($.trim(password) == "") {
						layer.tips('请输入密码', '#password', {
							tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
						return;
					} else {
						$.ajax({
							url : path + "/loginyes",
							type : "GET",
							data : {
								phoneNum : phoneNum,
								password : password,
								loginType : loginType,
								code : code
							},
							dateType : "JSON",
							success : function(data) {
								if (data.msg == "11") {
									layer.tips('您的会员账户已被禁用,无法登陆', '.OKbtn', {
										tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
									});
									//$("#btn").attr("disabled",true);
									return;
								} else if (data.msg == "-1") {
									layer.tips('用户名或密码错误!', '.OKbtn', {
										tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
									});
									return;
								} else if (data.msg == "400") {
									layer.tips('程序异常!', '.OKbtn', {
										tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
									});
									return;
								} else if (data.msg == "1") {
									loginBtn_click();
									window.location.href = path + "/app/shouye";
								} else if (data.msg == "2") {
									loginBtn_click();
									window.location.href = path + "/app/ranshou";
								} else if (data.msg == "3") {
									loginBtn_click();
									window.location.href = path + "/app/taishou";
								}
							}
						});
					}
				} else if (loginType == 1) {
					if ($.trim(phoneNum) == "") {
						$("#Num").focus();
						layer.tips('请输入账号', '#Num', {
							tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
						return;
					} else if ($.trim(password) == "") {
						$("#Num").focus();
						layer.tips('请输入密码', '#password', {
							tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
						return;
					} else {
						$.ajax({
							url : path + "/loginyes",
							type : "GET",
							data : {
								phoneNum : phoneNum,
								password : password,
								loginType : loginType,
								code : code
							},
							dateType : "JSON",
							success : function(data) {
								if (data.msg == "11") {
									layer.tips('您的站点账户被禁用,无法登陆', '.OKbtn', {
										tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
									});
									//$("#btn").attr("disabled",true);
									return;
								} else if (data.msg == "-1") {
									layer.tips('用户名或密码错误!', '.OKbtn', {
										tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
									});
									return;
								} else if (data.msg == "400") {
									layer.tips('程序异常!', '.OKbtn', {
										tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
									});
									return;
								} else if (data.msg == "1") {
									loginBtn_click();
									window.location.href = path + "/app/shouye";
								} else if (data.msg == "2") {
									loginBtn_click();
									window.location.href = path + "/app/ranshou";
								} else if (data.msg == "3") {
									loginBtn_click();
									window.location.href = path + "/app/taishou";
								}
							}
						});
					}
				}
			})
		})


	});


	function loginBtn_click() {
		var strName = $('#Num').val();
		var strPass = $('#password').val();
		var r1 = $('#r1').val();
		var r2 = $('#r2').val();
		localStorage.setItem('keyName', strName);
		localStorage.setItem('r1', r1);
		localStorage.setItem('r2', r2);
		if ($('#c1').is(':checked')) {
			localStorage.setItem('keyPass', strPass);
		} else {
			localStorage.removeItem('keyPass');
		}
		if ($('#r1').is(':checked')) {
			localStorage.setItem('r1', r1);
		} else {
			localStorage.removeItem('r1');
		}
		if ($('#r2').is(':checked')) {
			localStorage.setItem('r2', r2);
		} else {
			localStorage.removeItem('r2');
		}
	}
	function pushGetOnlyPhone() {
		return $("#pushGetOnlyPhone").val();
	}
</script>

</head>
<body>
	<section class="w100">
	<div class="w100">
		<div class="logoimg">
			<img
				src="${pageContext.request.contextPath}/statics/app/images/676992733453837780.png" />
		</div>
		<form action="loginyes">
			<ul class=" w94 logotable">
				<li><input type="hidden" name="" id="pushGetOnlyPhone"
					value="${gasstion.mobilePhone }" />
					<div class="border-r">
						<span>账号：</span><input name="phoneNum" type="text" id="Num"
							value="" class="comp_input" />
					</div></li>
				<li>
					<div class="border-r">
						<span>密码：</span><input name="password" type="password" value=""
							id="password" class="comp_input" />
					</div>
				</li>

				<li>
					<div class="col">
						<div class="opt">
							<input class="magic-radio" type="radio" name="loginType" id="r1"
								checked="checked" value="0"> <label for="r1">车主/物流</label>
						</div>
						<div class="opt">
							<input class="magic-radio" type="radio" name="loginType" id="r2"
								value="1"> <label for="r2">站点</label>
						</div>
					</div>
				</li>
			</ul>
			<div class="w94 okchoose">
				<input class="OKbtn" type="button" id="btn" value="确&nbsp;&nbsp;认" />
			</div>
		</form>
		<div class="w94 logobox clearfix">
			<div class="left">
				<input class="magic-checkbox" type="checkbox" checked="checked"
					name="layout" id="c1"> &nbsp;<label class="colord" for="c1">记住密码</label>
			</div>
			<div class="right">
				<a href="${pageContext.request.contextPath}/app/logintel">短信快捷登录</a>
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
