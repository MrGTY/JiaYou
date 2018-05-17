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

		<title>召集购油平台-付款</title>
		<meta name="keywords" content="召集购油平台-付款">
			<meta name="description" content="召集购油平台-付款">

				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/statics/app/css/csscont.css" />
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/statics/app/css/seed.css" />
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/statics/app/layui/css/layui.css" />
				<script
					src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"
					type="text/javascript"></script>
				<script
					src="${pageContext.request.contextPath}/statics/app/layui/layui.js"
					type="text/javascript"></script>
</head>

<body>
	<header>
	<div class="header-mainsf w100">
		<div class="header-txt-titl">
			<input type="hidden" value="${VipuserinfoSession.id}" name="vid"
				id="vid" /> <input type="hidden" value="${gid}" name="gid" id="gid" />
			<input type="hidden" value="${shopid}" id="id" /> <input
				type="hidden" value="${geshu}" id="quantity" />
			<div class="mail right">
				<span><img
					src="${pageContext.request.contextPath}/statics/app/images/more.png" /></span>
			</div>
			<div id="vipName"></div>
		</div>
		<div class="jzyuan">
			<div class="imghe">
				<img
					src="${pageContext.request.contextPath}/statics/app/images/yu.png" />
				<div class="fontnum">
					<span>余额</span> <span><font id="oilMass"></font>&nbsp;元</span>
				</div>
			</div>
		</div>
		<div class="clearfix jymap w94">
			<div class="left">
				<img
					src="${pageContext.request.contextPath}/statics/app/images/u130.png" />
			</div>
			<div class="right">
				<span>服务网点：</span>
				<h4 id="siteName"></h4>
			</div>
		</div>
	</div>
	</header>

	<section class="index-main w100 ">
	<div class="p94 fktxt">
		<div id="error">
			<h3>购买轮胎信息：</h3>
			<p>
				<span id="brand"></span>
			</p>
			<p class="clearfix">
				<span class="left" id="material"></span><span class="right"
					id="quantity1"></span>
			</p>
			<p class="clearfix">
				<span class="left" id="marque"></span><span class="right"
					id="specifications">/span> 
			</p>
		</div>
		<div>
			<h3>待支付金额：</h3>
			<div class="yuan">
				<span><font id="price"></font>元</span>
			</div>
		</div>
	</div>
	<div class="p94">
		<input class="OKbtn" name="" type="button" value="确&nbsp;&nbsp;认" />
	</div>
	</section>
	<%@include file="../common2/foot.jsp"%>
	<script type="text/javascript">
		$(function() {
			var vid = $("input:hidden[name='vid']").val();
			var gid = $("input:hidden[name='gid']").val();
			var id = $("#id").val();
			var quantity = $("#quantity").val();
			var path = "${cxt}";
			layui.use('layer', function() {
				$(".OKbtn").click(function() {
					var vsprice = $("#price").text(); //轮胎总价
					var vsoilMass = $("#oilMass").text(); //会员轮胎余额
					var quaty = $("#quantity").val(); //轮胎数量
					if (Number(vsprice) > Number(vsoilMass)) {
						layer.tips('您的余额不足', '.OKbtn', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
						return;
					}
					$.ajax({
						url : path + "/app/buyTyre",
						type : 'post',
						data : {
							"id" : id,
							"num" : quaty,
							"money" : vsprice
						},
						dataType : "json",
						success : function(data) {
							if (data.msg == 1) {
								window.location.href = path + "/app/paysuccess";
							} else if (data.msg == -10) {
								layer.tips('十分钟之内请勿重复购买轮胎', '.OKbtn', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
	
							} else if (data.msg == -1) {
								layer.tips('余额不足', '.OKbtn', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
							} else if (data.msg == -200) {
								layer.tips('该轮胎站点已被禁用，您无法进行购买', '.OKbtn', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
							} else if (data.msg == 400) {
								layer.tips('请求超时，请稍后重试', '.OKbtn', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
							} else if (data.msg == -20) {
								layer.tips('您的账户被禁用，请解锁后重试', '.OKbtn', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
							} else if (data.msg == -100) {
								layer.tips('该轮胎站点已被禁用，无法购买！', '.OKbtn', {
									tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
								});
							}
						}
					});
				});
			});
			var vid = $("input:hidden[name='vid']").val();
			var gid = $("input:hidden[name='gid']").val();
			$.ajax({
				type : "GET",
				url : path + "/app/buytyre",
				data : {
					vid : vid,
					gid : gid
				},
				datatype : "json",
				success : function(data) {
					console.log(data);
					var result = JSON.parse(data);
					if (result.msg == 1) {
						if (result.vipuserinfo.vipName == undefined) {
							$("#vipName").html("账户:" + result.vipuserinfo.vipName + "");
							//$("#vipName").html("账户:" + result.vipuserinfo.phoneNum + "");
							$("#oilMass").html("0");
							$("#siteName").html(result.gasstation.siteName);
						} else {
							$("#vipName").html("账户:" + result.vipuserinfo.vipName + "");
							$("#oilMass").html(result.vipuserinfo.tyreBalance);
							$("#siteName").html(result.gasstation.siteName);
						}
					} else if (result.msg == -1) {
						if (result.vipuserinfo.vipName == undefined) {
							$("#vipName").html("账户:无");
							$("#oilMass").html("0");
							$("#siteName").html("无");
						} else {
							$("#vipName").html("账户:" + result.vipuserinfo.vipName + "");
							$("#oilMass").html(result.vipuserinfo.tyreBalance);
							$("#siteName").html(result.gasstation.siteName);
						}
					} else if (result.msg == 400) {
						if (result.vipuserinfo.vipName == undefined) {
							$("#vipName").html("账户:无");
							$("#oilMass").html("0");
							$("#siteName").html("无");
						} else {
							$("#vipName").html("账户:" + result.vipuserinfo.vipName + "");
							$("#oilMass").html(result.vipuserinfo.tyreBalance);
							$("#siteName").html(result.gasstation.siteName);
						}
					}
	
	
				},
				error : function(data) {}
			});
	
			$.ajax({
				type : "GET",
				url : path + "/app/tyreinfo",
				data : {
					id : id,
					quantity : quantity
				},
				datatype : "json",
				success : function(data) {
					var result = JSON.parse(data);
					if (result.msg == 1) {
						$("#brand").html("品牌:" + result.shop.brand);
						$("#material").html("材质:" + result.shop.material);
						$("#marque").html("型号:" + result.shop.marque);
						$("#quantity1").html("数量:" + result.quantity + "条");
						$("#specifications").html("规格:" + result.shop.specifications);
						$("#price").html(result.price);
					} else {
	
						$("#error").html("数据加载异常!");
					}
				},
				error : function(data) {}
			});
	
		});
	</script>
</body>
</html>


