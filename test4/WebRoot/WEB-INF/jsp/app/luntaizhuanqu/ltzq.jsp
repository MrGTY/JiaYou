<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>召集购油平台-轮胎专区</title>
<meta name="keywords" content="召集购油平台-轮胎专区">
<meta name="description" content="召集购油平台-轮胎专区">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/csscont.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/seed.css" />
<script
	src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var path = "${cxt}";
</script>
<script>
	$(function() {
		$(".sxzq").click(function(evnet) {
			/* var e = window.event || event;
			 if (e.stopPropagation) {
			     e.stopPropagation();
			 } else {
			     e.cancelBubble = true;
			 }*/
			if ($(".screen-nav").css("display") == "none") {
				$(".screen-nav").show();
			} else {
				$(".screen-nav").hide();
			}
			/*$(".btn_right").click(function () {
			    $(".screen-nav").hide();
			});*/
			$("#ltlist").click(
				function() {
					$(".screen-nav").hide();
				});
			$("#fix").click(
				function() {
					$(".screen-nav").hide();
				});
		});
	})
</script>
</head>
<body class="bgcolor">
	<section class="w100">
	<div class="screen">
		<div class="sxzq p94">
			轮胎专供
			<div class="right">
				<a href="javascript:void(0)" onclick="xuanze();">筛选</a>
			</div>
		</div>
		<div class="screen-nav p94">
			<span>品牌筛选：</span>
			<ul class="clearfix" id="item_ul_li">
				<li><a onclick="getname(this);">测试</a></li>
				<li><a onclick="getname(this);">马上发那个价格</a></li>
			</ul>
			<span>材质筛选：</span>
			<ul class="clearfix" id="item_ul_li_two">
				<li><a href="javascript:void(0);" onclick="choosecz(this)">发送给</a></li>
			</ul>
			<span>规格筛选：</span>
			<ul class="clearfix" id="item_ul_li_three">
				<li><a href="javascript:void(0);" onclick="choosegg(this)">索菲特</a></li>
			</ul>
			<span>已选择信息：</span>
			<div class="clearfix">
				<div class="float_left btn_left">
					<div class="btn_left_one">
						<span>品牌:</span><span></span>
					</div>
					<div class="btn_left_two">
						<span>材质:</span><span></span>
					</div>
					<div class="btn_left_three">
						<span>规格:</span><span></span>
					</div>
				</div>
				<div class="float_right btn_right" id="btn_right_qd">确定</div>
				<div class="float_right btn_right" style="background-color:rgb(215,215,215)">&nbsp;</div>
				<div class="float_right btn_right" id="btn_right_sfmngg">重置</div>
			</div>
		</div>
	</div>
	<div class="ltlist" id="ltlist">
		<input type="hidden" name="pageNo" id="pageNo" value="1" />
		<ul id="item_ul">
		</ul>
	</div>
	<div class="fix" id="fix">
		<div class="num p94">
			<span class="num-jian">&nbsp;-&nbsp;</span>
			<!-- 个数 -->
			<input type="text" value="1" readonly="readonly" id="num_input"
				class="input-num" /> <span class="num-jia">&nbsp;+</span>
		</div>
		<div class=" w100 clearfix fkbtn">
			<div class="left" id="money">金额：￥0.00</div>
			<input type="hidden" value="0.00" id="trueprice" />
			<!-- 轮胎id -->
			<input type="hidden" value="" id="shopid" /> <input type="hidden"
				value="" id="gid" />
			<div class="right" disabled="disabled" id="ok" onclick="ok();">确认付款</div>
		</div>
	</div>
	</section>
	<%@include file="../common2/foot.jsp"%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/app/js/zepto.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/app/js/js.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/app/js/luntaizhuanqu/ltzq.js"></script>
</body>
</html>
