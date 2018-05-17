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

<title>召集购油平台首页</title>
<meta name="keywords" content="召集购油平台首页">
<meta name="description" content="召集购油平台首页">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/csscont.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/app/css/gg.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/app/js/luntaizhuanqu/luntai.js"></script>
<script type="text/javascript">
	var path = "${cxt}";
</script>
<script type="text/javascript">
	$(function() {
		var id = $("input:hidden[name='id']").val();
		$.ajax({
			url : path + "/HomePage",
			type : "GET",
			data : {
				id : id
			},
			dataType : "JSON",
			success : function(data) {
				var html = "";
				if (data.msg == "-1") {
					var phoneNum = "账户：" + data.vipuserinfo.phoneNum
					//$(".zhanghu").append(phoneNum);
					html += "<span>余量</span><span><font>" + 0
						+ "</font>&nbsp;L</span>";
				} else if (data.msg == "1") {
					//var phoneNum = "账户：" + data.vipuserinfo.phoneNum
					if(data.headLine!=null){
						$("#list span").append(data.headLine.message);
					}else{
						$("#list span").append("今日暂无消息通知");
					}
					html += "<span>余量</span><span><font style='font-size:1.5rem;'>" + data.vipuserinfo.oilMass
						+ "</font>&nbsp;L</span>";
				}
				$(".fontnum").append(html);
			}
		});
		$.ajax({
			url : path + "/Information",
			type : "GET",
			data : {
				id : id
			},
			dataType : "JSON",
			success : function(data) {
			console.log(data.list);
				if (data.msg == "-1") {
					alert("程序异常");
				} else if (data.msg == "1") {
					var html = "";
					$.each(data.list, function(i, item) {
						if (data.list[i].gasstation.siteName != undefined) {
							html += "<div class='jyz'>"
								+ "<div class='w7 left jyz_w7_left'><img src='" + path + "/statics/app/images/ioce2.png' /></div>"
								+ " <div class=' w90 right'>"
								+ " <h3>" + data.list[i].gasstation.siteName + "</h3>"
								+ "<time>" + data.list[i].operationTime + "</time>"
								+ "</div><div class='clearfix'></div>"
								+ "<div class='jyz-tet'><span>已完成 <font style='font-size:3rem;'> " + data.list[i].expenditure + "</font>L<i><img src='" + path + "/statics/app/images/line.png' /></i><span>" + data.list[i].vipuserinfo.busNum + "</span></div>"
								+ "</div>";
						}
					});
					
					$(".gg").after(html);
				}
			}
		});
	})
</script>
</head>

<body class="bgcolor">
	<header>
	<div class="w100 header-mainsf">
		<div class="header-txt-titl">
			<div class="mail right">
				<a href="${pageContext.request.contextPath}/app/news"><span><img
						src="${pageContext.request.contextPath}/statics/app/images/E-mail.png" /><i></i></span></a>
			</div>
			<h3>召集联盟-车主端</h3>
		</div>
		<div id="wrap">
			<div id="wsrap">
			    <ul id="list">
			    	<li><span></span></li>
			    </ul>
			</div>
		</div>
		<div class="jzyuan">
			<div class="imghe">
				<img
					src="${pageContext.request.contextPath}/statics/app/images/yu.png" />
				<input type="hidden" name="id" value="${VipuserinfoSession.id }" />
				<div class="fontnum"></div>
			</div>
		</div>
		<div class="saopei">
			<span class="left"><a
				href='gotoapps://saoyisao?uid=${VipuserinfoSession.id }' >扫一扫</a></span> <span
				class="right"><a
				href="${pageContext.request.contextPath}/app/pageFriend">分配</a></span>
		</div>
	</div>
	</header>
	<section class="index-main w100">
	<div class="clearfix gg">
		<div class="posizion_div float_left" onclick="luntai();">
			<div class="gg_img_size">
				<img
					src="${pageContext.request.contextPath}/statics/app/images/ioce1.png" />
			</div>
			<div>
				<h3>轮胎专区</h3>
				<span>品牌轮胎召集专供</span>
			</div>
		</div>
		<div class="posizion_div float_right">
			<div class="gg_img_size">
				<img
					src="${pageContext.request.contextPath}/statics/app/images/dingwei.png" />
			</div>
			<div>
				<h3>定位查询</h3>
				<span>查询车辆定位信息</span>
			</div>
		</div>
	</div>
	</section>
	<%@include file="../common2/foot.jsp"%>
</body>
</html>
