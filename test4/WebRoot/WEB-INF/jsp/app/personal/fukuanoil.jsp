<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cxt" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<title>加油（扫商户码进入）</title>
		<meta name="keywords" content="召集购油平台-加油">
			<meta name="description" content="召集购油平台-加油">

				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/statics/app/css/csscont.css" />
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/statics/app/css/seed.css" />
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/statics/app/layui/css/layui.css" />
				<script
					src="${pageContext.request.contextPath}/statics/app/js/jquery-1.11.0.min.js"
					type="text/javascript"></script>
				<script
					src="${pageContext.request.contextPath}/statics/app/layui/layui.js"
					type="text/javascript"></script>
</head>
<body>
	<header>
	<div class="header-mainsffghsf w100">
		<div class="header-txt-titl">
			<input type="hidden" value="${uid }" id="vid" /> <input
				type="hidden" value="${gid }" id="gid" />
			<div class="mail right"></div>
			<div id="vipName"></div>
		</div>
		<div class="jzyuan">
			<div class="imghe">
				<img
					src="${pageContext.request.contextPath}/statics/app/images/yu.png" />
				<div class="fontnum">
					<span>余量</span> <span><font id="oilMass" style="font-size: 1.5rem;"></font>&nbsp;L</span>
				</div>
			</div>
		</div>
		<div class="clearfix jymap w94">
			<div class="left">
				<img
					src="${pageContext.request.contextPath}/statics/app/images/u130.png" />
			</div>
			<!-- <div style="text-align: center;">
				<h6 style="font-size:13px;color:black">
					服务费:<font id="charge"></font>元/L
				</h6>
			</div> -->
			<div class="right" style="line-height:28px">
				<span>服务网点：<span style="font-size: 2rem;"  id="siteName"></span></span></span>
				<H4 id="siteName"></H4>
			</div>
		</div>
	</div>
	</header>

	<div class="p94 friends mapsfng">
		<div class="clearfix">
			<div class="left">当前车辆：<span style="font-size: 2rem;" id="busNum"></span></span></div>
		</div>
		<h3 id="busNum"></h3>
		<div><!-- 预加油量： --></div>
		<div class="f-num">
			<span><input type="text" name="oil" id="addoil" value=""
				style="width: 40px;;font-size: 2rem;">L</span>
		</div>
		<!-- <div>
			<font>合计服务费:</font><font id="money">0</font>元
		</div> -->
		<br />
		<div>
			<input name="" type="button" class="OKbtn" value="确&nbsp;&nbsp;定" />
		</div>
	</div>

	<%@include file="../common2/foot.jsp"%>
	<script type="text/javascript">
		//查询站点以及用户信息
		$(function() {
			var vid = $("#vid").val();
			var gid = $("#gid").val();
			var path = "${cxt}";
			$.ajax({
				type : "GET",
				url : path + "/app/buytyre",
				data : {
					vid : vid,
					gid : gid
				},
				datatype : "json",
				success : function(data) {
					var result = JSON.parse(data);
					$("#vipName").html("账户:" + result.vipuserinfo.phoneNum + "");
					$("#oilMass").html(result.vipuserinfo.oilMass);
					$("#siteName").html(result.gasstation.siteName);
					$("#busNum").html(result.vipuserinfo.busNum);
					$("#charge").html(result.gasstation.charge);
				},
			});
	
			$("input:text[name=oil]").blur(function() {
				var oil = $("input:text[name=oil]").val();
				var charge = Number($("#charge").text());
				var money = oil * charge;
				$("#money").html(money);
			});
			layui.use('layer', function() {
				$(".OKbtn").on("click", function() {
					var oilMa = $("#oilMass").text(); //余量
					var addoil = $("#addoil").val(); //预加油量
					var money = $("#money").text(); //合计服务费
					var gid = $("#gid").val(); //站点id
	
					if (addoil == null || addoil == "") {
						layer.tips('预加油量不能为空', '.OKbtn', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
						return;
					}
					if (isNaN(addoil)) {
						layer.tips('请输入数字', '.OKbtn', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
						return;
					} else if (Number(addoil) < 0) {
						layer.tips('请输入大于0的数', '.OKbtn', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
						return;
					} else if (Math.floor(addoil) != Number(addoil)) {
						layer.tips('请输入一个整数', '.OKbtn', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
						return;
					}
	
					if (Number(oilMa) < Number(addoil)) {
						layer.tips('您的余额不足！', '.OKbtn', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
						return;
					} else {
						$.ajax({
							url : path + '/app/addoil',
							type : 'get',
							data : {
								gid : gid,
								preAddOil : addoil,
								fee : money
							},
							dataType : 'json',
							success : function(data) {
							console.log(data.msg);
								if (data.msg == 1) {
									window.location.href = path + "/app/buyoilsuccess?zhi="+addoil;
								}/* else if (data.msg == -10) {
									layer.tips('十分钟之内无法再次购买', '.OKbtn', {
										tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
									});
									return;
								}  */ else if (data.msg == -20) {
									layer.tips('您的账户被禁用，请解锁后重试', '.OKbtn', {
										tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
									});
									return;
								}else if (data.msg == -200) {
									layer.tips('该站点已被删除,您无法进行加油', '.OKbtn', {
										tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
									});
									return;
								}  else if (data.msg == 400) {
									layer.tips('请求超时，请稍后重试', '.OKbtn', {
										tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
									});
									return;
								} else if (data.msg == -100) {
									layer.tips('该燃油站点已被禁用，购买失败', '.OKbtn', {
										tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
									});
									return;
								}else if (data.msg = -1) {
									layer.tips('支付失败', '.OKbtn', {
										tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
									});
									return;
								}
							}
						});
					}
				});
			});
		});
	</script>
</body>
</html>
