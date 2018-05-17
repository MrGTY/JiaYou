<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cxt" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>召集购油平台-付款结果</title>
<meta name="keywords" content="召集购油平台-付款结果">
<meta name="description" content="召集购油平台-付款结果">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/csscont.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/seed.css" />
<style>
.testp94 {
	margin-bottom: 7rem;
}
</style>
<script type="text/javascript">
	var path = "${cxt}";
</script>
</head>

<body class="bgcolor">
	<header class="w100">
	<div class="search clearfix p94">
		<c:if test="${VipuserinfoSession.userType==1 }">
			<div class=" left zs">
				<input name="" type="button" class="search-btn" /> <input name=""
					type="text" name="" class="search-input search-input_color"
					id="search-input" placeholder="请输入好友电话" />
			</div>
			<div id="img" class="img_sfmgjgjgdf">
				<div class="right soso_right">搜索</div>
			</div>
		</c:if>
		<div class="left zs">
	</header>

	<section class="w100">
	<div class="p94 haoy">我的好友</div>
	<div class="friends-main">
		<div class="p94 testp94">
			<ul id="ul">
			</ul>
		</div>
	</div>
	</section>
	<%@include file="../common2/foot.jsp"%>
	<script
		src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/js/jquery-1.9.1.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/app/layui/layui.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/app/js/friend/friends.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/app/mobile/layer.js"
		type="text/javascript"></script>
</body>
</html>
