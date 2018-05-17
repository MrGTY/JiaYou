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

<title>召集购油平台-我的账户</title>
<meta name="keywords" content="召集购油平台-我的账户">
<meta name="description" content="召集购油平台-我的账户">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/csscont.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/seed.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		var path = "${cxt}";
		var id = $("input:hidden[name='id']").val();
		$.ajax({
			url : path + "/uNamepwd",
			type : "GET",
			data : {
				id : id
			},
			dataType : "JSON",
			success : function(data) {
				if (data.msg == "-1") {
					alert("暂无数据");
				} else if (data.msg == "1") {
					if (data.vipuserinfo.userType == 1) {
						var html = "<h2>"+data.vipuserinfo.vipName+"</h2><dl class='leirong'>"
							+ "<dd><font class='foncolor1'>真实姓名:</font>" + data.vipuserinfo.userName
							+ "</dd><dd><font class='foncolor1'>工作手机:</font>" + data.vipuserinfo.phoneNum
							+ "</dd><dd><font class='foncolor1'>车辆信息:</font>" + data.vipuserinfo.busNum
							+ "</dd></dl>";
						$(".myzh").append(html);
					}else{
						var html;
						if(data.vipuserinfo.mycarId!=null){
							var html = "<h2>召集油联盟</h2><dl class='leirong'>"
							+ "<dd><font class='foncolor1'>真实姓名:</font>" + data.vipuserinfo.userName
							+ "</dd><dd><font class='foncolor1'>工作手机:</font>" + data.vipuserinfo.phoneNum
							+ "</dd><dd><font class='foncolor1'>车辆信息:</font>" + data.vipuserinfo.busNum
							+ "</dd><dd><font class='foncolor1'>所属物流公司:</font>" + data.vipuserinfo.mycarId
							+ "</dd></dl>";
						}else{
							var html = "<h2>召集油联盟</h2><dl class='leirong'>"
							+ "<dd><font class='foncolor1'>真实姓名:</font>" + data.vipuserinfo.userName
							+ "</dd><dd><font class='foncolor1'>工作手机:</font>" + data.vipuserinfo.phoneNum
							+ "</dd><dd><font class='foncolor1'>车辆信息:</font>" + data.vipuserinfo.busNum
							+ "</dd>[7/]</dl>";
						}
						$(".myzh").append(html);
					}
					
				}
			}
		});
	})
</script>
</head>
<body>
	<header>
	<div class="goback_class">
		<a href="javascript:history.back(-1);"><img
			src="${pageContext.request.contextPath}/statics/app/images/goback.png"
			height="32" width="32" />返回</a>
	</div>
	<div class="header1">
		<div class="logoimg">
			<img
				src="${pageContext.request.contextPath}/statics/img/20180326150324.png" />
		</div>
	</div>
	</header>
	<section> <input type="hidden" name="id" value="" />
	<div class="myzh"></div>
	<div class="div_ul_li">
		<a href="${pageContext.request.contextPath}/app/zhaohuipsd"><span>修改密码</span><span>></span></a>
		<a><span>版本(V1.0)</span><span>></span></a>
	</div>
	<div class="kefu">客服热线:344</div>
	</section>
</body>
</html>
