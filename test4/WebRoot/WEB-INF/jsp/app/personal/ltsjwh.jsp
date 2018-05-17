<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>召集购油平台-轮胎专区</title>
<meta name="keywords" content="召集购油平台-轮胎专区">
<meta name="description" content="召集购油平台-轮胎专区">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/csscont.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/seed.css"/>
<c:set value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}" var="cxt"/>
<c:set value="${pageContext.request.contextPath}" var="cxt2"/>
	<script type="text/javascript">
		var path="${cxt}";
		var path2="${cxt2}";
	</script>
</head>

<body class="bgcolor">
    <header>
       <div class="ltheader p94">
       		<div class="add-a"><a href=""><b>+</b></a></div>
       		<h3>商品列表</h3>
       </div>
    </header>
    <input type="hidden" name="pageNo" id="pageNo" value="1" />
    <input type="hidden" name="sessionId" id="sessionId" value="${gasstion.id }" />
    <section class="w100">
          <div class="ltlist">
               <ul id="item_ul" class="item_ul">
               </ul>
          </div>
    </section>
<%@include file="../common2/foot.jsp"%>
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/app/js/zepto.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/statics/app/js/personal/ltsjwh.js"></script>
</body>
</html>
