<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cxt"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>召集购油平台-消息中心</title>
<meta name="keywords" content="召集购油平台-消息中心">
<meta name="description" content="召集购油平台-消息中心">

<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/statics/app/css/csscont.css" />
<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/statics/app/css/seed.css" />
<style type="text/css">
.last_li:last-child {
	margin-bottom: 8.5rem;
}
</style>
<script src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/app/js/dropload.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var path="${cxt}";
</script>
<script type="text/javascript">
			$(function() {
						$(".tab-nav span").click(function() {
							$(this).addClass("sed").siblings().removeClass("sed"); //切换选中的按钮高亮状态
							var index = $(this).index(); //获取被按下按钮的索引值，需要注意index是从0开始的
							$(this).parents(".tab").find(".cr-cont").eq(index).show().siblings().hide(); //在按钮选中时在下面显示相应的内容，同时隐藏不需要的框架内容
						});
					});
				</script>
</head>

<body class="bgcolor">
	<section class="w100">
	<div class="tab">
		<div class="tab-nav p94" id="click">
			<span class="sed item">全 部</span> <span class="item">分配提醒</span> <span class="item">消费提醒</span>
			<span class="item">系 统</span>
		</div>
		<div class="tab_b">
			<div class="cr-cont" >
				<ul class="xfjl-ul clk1" >
				</ul>
			</div>
			<div class="cr-cont" >
				<ul class="xfjl-ul clk2">
				</ul>
			</div>
			<div class="cr-cont"  >
				<ul class="xfjl-ul clk3">
				</ul>
			</div>
			<div class="cr-cont" >
				<ul class="xfjl-ul clk4">
				</ul>
			</div>
			
	</div>
	</section>
<%@include file="../common2/foot.jsp"%>
	<script
		src="${pageContext.request.contextPath}/statics/app/newses.js"></script>
</body>
</html>
