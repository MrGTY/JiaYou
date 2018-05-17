<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cxt" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>召集购油平台-分配</title>
<meta name="keywords" content="召集购油平台-分配">
<meta name="description" content="召集购油平台-分配">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/csscont.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/seed.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/layui/css/layui.css"/>
<script type="text/javascript">
	var path="${cxt}";
</script>
</head>

<body class="bgcolor">
  <header>
        <div class="header-mainsffghsf w100" id="head">
        </div>
   </header>
   <div class="p94 friends">
   		<input type="hidden" value="${id}" id="hidden" name="hidden">
        <div class="clearfix" id="clearfix">
           <div class="left">预分配给：</div>
           <a href="pageFriend" class="right"><div class="qh">切换</div></a>
           
        </div>
        <div>预分配油量：</div>
        <div class="f-num"><span><font><input type="text" name="text" style="background:transparent; text-align: center;font-size: 3rem;width:8rem;" id="text" value=""></font>L</span></div>
        <div onclick="fenpeiqueding();"><input name="" type="button"  class="OKbtn" value="确&nbsp;&nbsp;定" /></div>
   </div>
<%@include file="../common2/foot.jsp"%>
<script src="${pageContext.request.contextPath}/statics/app/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"  type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/app/layui/layui.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/app/js/friend/fenpei.js"    type="text/javascript"></script>
</body>
</html>
