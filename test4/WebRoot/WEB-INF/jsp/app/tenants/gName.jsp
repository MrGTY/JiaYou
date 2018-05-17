<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="cxt"></c:set>
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

<title>召集购油平台-我的账户</title>
<meta name="keywords" content="召集购油平台-我的账户">
<meta name="description" content="召集购油平台-我的账户">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/csscont.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/seed.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function(){
	var path="${cxt}";
	var id=$("input:hidden[name='id']").val();
	$.ajax({
		url:path+"/gName",
		type:"GET",
		data:{id:id},
		dataType:"JSON",
		success:function(data){
			if(data.msg=="-1"){
				alert("暂无数据");
			}else if(data.msg=="1"){
					var html="<h2>"+data.gasstation.siteName+"</h2><dl class='leirong'><dd><font class='foncolor1'>真实姓名:</font>"+data.gasstation.contact
					+"</dd><dd><font class='foncolor1'>工作手机:</font>"+data.gasstation.mobilePhone
					+"</dd><dd><font class='foncolor1'>商户地址:</font>"+data.gasstation.detaileAddress
					+"</dd></dl>";
					$(".myzh").append(html);
			}
		}
	});
})
</script>
</head>
<body>
	 <header>
	 <div class="goback_class">
    	<a href="javascript:history.back();"><img src="${pageContext.request.contextPath}/statics/app/images/goback.png" height="32" width="32"/>返回</a>
	</div>
        <div class="header1">
            <div class="logoimg"><img src="${pageContext.request.contextPath}/statics/app/images/logo.jpg" /></div>
        </div>
     </header>
     <section>
     <input type="hidden" name="id" value=""/>
          <div class="myzh">
              
          </div>
          <div class="div_ul_li">
             <a href="${pageContext.request.contextPath}/app/shanghumi"><span>修改密码</span><span>></span></a>
         </div>
        <div class="kefu">客服热线:344</div>
     </section>
</body>
</html>
