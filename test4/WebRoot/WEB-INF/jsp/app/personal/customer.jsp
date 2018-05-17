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

<title>召集油联盟客户登记</title>
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
.logotable_w94{
	margin-top: 2rem;
}
#queding {
	display: inline-block;
	border: 1px solid #FFB3FF;
	padding: 0.6rem;
	border-radius: 5px;
	color: #fff;
	font-size: 1.5rem;
	background-color: #CCCCFF;
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
		var pattern = /^1[123456789]\d{9}$/;
		layui.use('layer', function() {
			$("#queding").click(function() {
				var vipName = $("#vipName").val();
				var phoneNum = $("#Num").val();
				var address = $("#address").val();
				var company = $("#company").val();
				if ($.trim(vipName) == "") {
					$("#vipName").focus();
					layer.tips('请输入联系人', '#vipName', {
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
				} else {
					$.ajax({
						url : path + "/app/addCtm",
						type : "POST",
						data : {
							name : vipName,
							phone : phoneNum,
							address : address,
							company : company
						},
						dateType : "JSON",
						success : function(data) {
							var a = JSON.parse(data)
							console.log(a.msg);
							if (a.msg == -1) {
								layer.tips('注册失败!', '#queding', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
								return;
							} else if (a.msg == 400) {
								layer.tips('请求超时，请稍后重试!', '#queding', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								}) ;
								return;
							} else if (a.msg == 200) {
								$("#queding").hide();
								$("#ok").show();

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
		<ul class=" w94 logotable logotable_w94">
			<li>
				<div class="border-r">
					<span>联系人：</span><input class="comp_input" name="name" id="vipName"
						type="text" class="comp_input_class" />
				</div>
			</li>
			<li>
				<div class="border-r">
					<span>手机号码：</span><input class="comp_input_class" name="phone"
						type="text" id="Num" />
				</div>
			</li>
			<li>
				<div class="border-r">
					<span>详细地址：</span><input class="comp_input_class" name="address"
						type="text" id="address" />
				</div>
			</li>
			<li>
				<div class="border-r">
					<span>公司名称：</span><input class="comp_input_class" name="company"
						type="text" id="company" />
				</div>
			</li>
		</ul>
		<div class="logoa w94 colord">
			<a id="queding">&nbsp;&nbsp;确&nbsp;&nbsp;定&nbsp;&nbsp;</a> <a id="ok"
				style="display: none;">登记成功！我们的客户经理会尽快与您取得联系！</a>
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
