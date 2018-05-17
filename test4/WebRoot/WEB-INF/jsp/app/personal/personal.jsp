<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cxt"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>召集购油平台-个人中心</title>
<meta name="keywords" content="召集购油平台-个人中心">
<meta name="description" content="召集购油平台-个人中心">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/csscont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/seed.css" />
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function(){
	var path="${cxt}";
	var id=$("input:hidden[name='id']").val();
	$.ajax({
		url:path+"/geren",
			type:"GET",
			data:{
				id:id
			},
			dateType:"JSON",
			success:function(data){
				if(data.msg=="-1"){
					alert("暂无数据");
				}else if(data.msg=="1"){
					if(data.vipuserinfo.tyreBalance==undefined && data.vipuserinfo.oilMass==undefined){
						var phoneNum="账户："+data.vipuserinfo.phoneNum
						$(".zhanghu").append(phoneNum);
					
						var fontnum="<span>轮胎余额</span><span><font>"+0
						+"</font>&nbsp;￥</span>";
						$("#fontnum").append(fontnum);
					
						var font="<span>余量</span><span><font>"+0
						+"</font>&nbsp;L</span>";
						$("#font").append(font);
					
						var wodezhanghu="我的账户：<font>"+data.vipuserinfo.phoneNum+"</font>";
						$("#wodezhanghu").append(wodezhanghu);
					
					}else if(data.vipuserinfo.tyreBalance==undefined){
						var phoneNum="账户："+data.vipuserinfo.phoneNum
						$(".zhanghu").append(phoneNum);
					
						var fontnum="<span>轮胎余额</span><span><font>"+0
						+"</font>&nbsp;￥</span>";
						$("#fontnum").append(fontnum);
					
						var font="<span>余量</span><span><font>"+0
						+"</font>&nbsp;L</span>";
						$("#font").append(font);
					
						var wodezhanghu="我的账户：<font>"+data.vipuserinfo.phoneNum+"</font>";
						$("#wodezhanghu").append(wodezhanghu);
						
					}else if(data.vipuserinfo.oilMass==undefined){
						var phoneNum="账户："+data.vipuserinfo.phoneNum
						$(".zhanghu").append(phoneNum);
					
						var fontnum="<span>轮胎余额</span><span><font>"+0
						+"</font>&nbsp;￥</span>";
						$("#fontnum").append(fontnum);
					
						var font="<span>余量</span><span><font>"+data.vipuserinfo.oilMass
						+"</font>&nbsp;L</span>";
						$("#font").append(font);
					
						var wodezhanghu="我的账户：<font>"+data.vipuserinfo.phoneNum+"</font>";
						$("#wodezhanghu").append(wodezhanghu);
					}else{
						var phoneNum="账户："+data.vipuserinfo.phoneNum
						$(".zhanghu").append(phoneNum);
					
						var fontnum="<span>轮胎余额</span><span><font>"+data.vipuserinfo.tyreBalance
						+"</font>&nbsp;￥</span>";
						$("#fontnum").append(fontnum);
					
						var font="<span>余量</span><span><font>"+data.vipuserinfo.oilMass
						+"</font>&nbsp;L</span>";
						$("#font").append(font);
					
						var wodezhanghu="我的账户：<font>"+data.vipuserinfo.phoneNum+"</font>";
						$("#wodezhanghu").append(wodezhanghu);
					}
					
				}
				
			}
	}); 
		$.ajax({
			url:"gerencheliang",
			type:"GET",
			data:{
				id:id,
			},
			dataType:"JSON",
			success:function(data){
				if(data.msg=="-1"){
					$("#count").html("0");
				}else if(data.msg=="1"){
					$("#count").html(data.vipuserinfo);
				}
			}
		});
})
</script>

</head>
<body class="bgcolor">
	<header>
	<div class="header-mainsf w100">
		<div class="header-txt-titl">
			<div class="mail right">
				<a href="${pageContext.request.contextPath}/app/news"><span><img src="${pageContext.request.contextPath}/statics/app/images/E-mail.png" /></a>
				<i class="none" ></i> </span> <span>
				</span>
			</div>
			<h3>个人中心</h3>
		</div>
		<div class="zhanghu">
		</div>
		<input type="hidden" name="id" value=""/>
		<div class="jzyuan1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="right" width="45%">
						<div class="imghe">
							<img
								src="${pageContext.request.contextPath}/statics/app/images/yu.png" />
							<div class="fontnum" id="font">
								
							</div>
						</div>
					</td>
					<td width="10%"></td>
					<td align="left">
						<div class="imghe">
							<img
								src="${pageContext.request.contextPath}/statics/app/images/yu.png" />
							<div class="fontnum" id="fontnum">
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</header>

	<section class="seed-main w100">
	<div class="p94 bgcolor-f borerb">
		<a href="${pageContext.request.contextPath}/app/zhanghu"> 
			<i class="tu1">&nbsp;</i> <span id="wodezhanghu"></span> </a>
		<a href="${pageContext.request.contextPath}/app/mycar"> 
			<i class="tu2">&nbsp;</i> <span id="wodecheliang">我的车辆：<font>下辖<b id="count"></b>辆车</font></span> </a>
		<%-- <a href="gotoapps://xylt?uid=${gasstion.id }"> 
			<i class="tu3">&nbsp;</i> <span id="shnagping">商品维护：<font>轮胎数据维护</font></span> </a> --%>
	</div>
	<div class="p94 bgcolor-f">
		<a href="${pageContext.request.contextPath}/app/gouyou"> 
			<i class="tu4">&nbsp;</i><span>燃油记录</span>  
		</a> 
		
		<a href="${pageContext.request.contextPath}/app/gouyou?index=1"> 
			<i class="tu5">&nbsp;</i> <span>轮胎记录</span> 
		</a>
	</div>
	<div class="p94 bgcolor-f">
		<a href="${pageContext.request.contextPath}/app/zhaohuipsd"> 
			<i class="tu6">&nbsp;</i> <span>修改密码</span> </a> 
		<!-- <a href="javascript:void(0)">
			<i class="tu7">&nbsp;</i> <span>版本（&nbsp;XXXXXXX&nbsp;）</span> </a> -->
	</div>
	<div class="p94 bgcolor-f">
		<a href="${pageContext.request.contextPath}/app/userlogout" class="outbtn">退出登录</a>
	</div>
	</section>
<%@include file="../common2/foot.jsp"%>
</body>
</html>
