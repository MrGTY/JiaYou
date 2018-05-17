<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>消费记录</title>
<meta name="keywords" content="召集购油平台-消费记录">
<meta name="description" content="召集购油平台-消费记录">

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
<div class="goback_class">
    <a href="${pageContext.request.contextPath}/app/ranshou"><img src="${pageContext.request.contextPath}/statics/app/images/goback.png" height="32" width="32"/>返回</a>
</div>
	<header>
         <div class="mycar-header p94">
              <h3></h3>
              <span class="numl"><font>燃油记录</font>&nbsp;</span>
              <span></span>
         </div>
    </header> 
    
    <section>
          <ul class="xfjl-ul" id="item_ul">
          </ul>
    </section>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/app/js/personal/xiaofeijilu2.js"></script>
</body>
</html>
