<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">

    <title>召集油联盟管理平台</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel="stylesheet">
    
    <link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet">
<style type="text/css">
	.msmfmg{
		text-align: center;
	}
</style>
</head>
<body class="default-bg">
<div class="pt_bj">
    <div class="df_top">
        <span class="fl font-s-30 mr10">召集油联盟</span>
        <span class="fl font-s-16">管理系统</span>
    </div>
    <div class="df_mid msmfmg">
    <a>&nbsp;</a>
    	<a  title=""><div class="col-lg-3  df_01"></div></a>
        <a href="login" title="站点 入口"><div class="col-lg-3 df_icon df_02">站点 入口</div></a>
        <a href="loginu" title="后台管理系统 入口"><div class="col-lg-3 df_icon df_03">后台管理系统 入口</div></a>
         <a title=""><div class="col-lg-3  df_04"></div></a>
    </div>
</div>
    <!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath}js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}js/bootstrap.min.js?v=3.4.0"></script>
</body>

</html>
