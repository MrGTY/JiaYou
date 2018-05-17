<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cxt" value="${pageContext.request.contextPath}"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>找回密码</title>
<meta name="keywords" content="找回密码">
<meta name="description" content="找回密码">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/csscont.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/app.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/seed.css"/>
<script src="${pageContext.request.contextPath}/statics/app/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/app/layui/layui.js" type="text/javascript"></script>
<style type="text/css">
#minut {
	display: none;
}
</style>
<script type="text/javascript">
var path="${cxt}";
</script>
</head>

<body>
	 <section class="w100">
       <div class="w100">
            <ul class=" w94 logotable">
                <li>
                    <div class="border-r"><span>手机号码：</span><input name="mobile" type="text" class="comp_input OKbtn1" /></div>
                </li>
                 <li class="clearfix">
                   	<div class="left border-r  border-s"><span>验&nbsp;证&nbsp;码：</span><input name="yzm" type="text" class="comp_input yzm"/></div>
                    <input value="" id="pyzm" type="hidden"/>
                   <!--  <div class="right colord tst"><a id="yam">获取验证码</a></div> -->
                    <div class="right colord tst" id="yam">获取验证码</div>
					<div class="right colord tst" id="minut">60秒后重试</div></li>
                </li>
                <li>
                    <div class="border-r"><span>新&nbsp;密&nbsp;&nbsp;码：</span><input id="password1"  type="password" class="comp_input" /></div>
                </li>
               <li>
                    <div class="border-r"><span>确认密码：</span><input id="password2"  type="password" class="comp_input" /></div>
                </li>
                
            </ul>
            <div class="w94" >
            	<input class="OKbtns" name="" type="button" id="gobacksf" value="返&nbsp;&nbsp;回" />
            <input class="OKbtnsf" name="" type="button" value="确&nbsp;&nbsp;认" />
            				</div>
            
            <!-- <div class="logoa w94 colord"><a href="">客服热线:400-168-9878</a></div> -->
     </div>
   </section>
   <script src="${pageContext.request.contextPath}/statics/app/js/new/zhaohuipsd.js"></script>
</body>
</html>
