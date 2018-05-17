<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cxt"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>我的车辆</title>
<meta name="keywords" content="召集购油平台-我的车辆">
<meta name="description" content="召集购油平台-我的车辆">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/csscont.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/seed.css"/>
<script src="${pageContext.request.contextPath}/statics/app/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"  type="text/javascript"></script>
<style>
	.last_li{
		margin-bottom: 7rem;
	}
</style>
<script type="text/javascript">
	var path="${cxt}";
</script>
</head>
<body>
<div class="goback_class">
    <a href="javascript:history.back();"><img src="${pageContext.request.contextPath}/statics/app/images/goback.png" height="32" width="32"/>返回</a>
</div>
<input type="hidden" value="1" name="hidden" id="pageNo"/>
   <section class="w100 mycar" id="mycar">
         <ul class="ul" id="ul">
         </ul>
   </section>
<%@include file="../common2/foot.jsp"%>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/app/js/zepto.min.js"></script>
		<script src="${pageContext.request.contextPath}/statics/app/js/personal/mycar.js"    type="text/javascript"></script>
</body>
</html>