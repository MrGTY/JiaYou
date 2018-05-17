<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">

    <title>召集油联盟管理系统登录</title>
    <meta name="keywords" content="召集购油平台管理系统登录">
    <meta name="description" content="召集购油平台管理系统登录">

    <link href="${pageContext.request.contextPath }/statics/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    
    <link href="${pageContext.request.contextPath }/statics/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/statics/css/style.css" rel="stylesheet">

</head>

<body class="login-bg">

  <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>
                <h5 class="logo-name">召集油联盟管理平台</h5>
            </div>
            <form class="m-t" role="form" action="loginu" method="post">
                <div class="form-login">
                    <label class="form-login-name">用户名：</label><input type="text" id="loginName" value="" name="loginName" class="form-control" placeholder="用户名" required="">
                </div>
                <div class="form-login">
                    <label class="form-login-name">密　码：</label><input type="password" id="password" value=""  name="password" class="form-control" placeholder="密码" required="">
                </div>
               <div class="info" style="color:red;">${message}</div>
                <div class="form-login text-left">
                    <div class="checkbox i-checks">
                        <label class="no-padding"><input type="checkbox">记住密码</label>
                        <small><a href="login.html#">忘记密码？</a></small>
                    </div>
                </div>
                

                <div class="form-login">
                    <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
                </div>

            </form>
        </div>
    </div>
    
    <div class="footer text-center">
        召集购油平台 &copy;版权所有
    </div>

    <!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath }/statics/js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/statics/js/bootstrap.min.js?v=3.4.0"></script>


</body>

</html>
