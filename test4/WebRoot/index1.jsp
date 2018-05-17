<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>召集油联盟管理平台</title>
    <link href="statics/css/bootstrap.min.css" rel="stylesheet">
</head>
<style>
    .wrap{
       position: relative;
        left: 50%;
        margin-left: -20%;
        width: 40%;
        margin-top: 10%;
    }
    .item{
        text-align: center;
        display: inline-block;
        border: 1px silver solid;
        width: 40%;
        height: 20%;
        margin-left: 7%;
        padding-top: 10%;
        padding-bottom: 10%;
        border-radius: 10px;
    }
    .item:hover{
        box-shadow: 0 10px 10px rgba(0,0,0,.5);
        transform:translate(0,-5px);
        background-color: #d9edf7;
        border: 1px white solid;
    }
    .item a{
        font-size: 20px;
    }
    h3{
        position: relative;
        left: 50%;
        margin-left: -135px;
        margin-bottom: 50px;
        opacity: 0.7;
        filter:Alpha(opacity=70);
    }
</style>
<body style="background-color: #F7F7F7">
<div class="wrap">
    <h3>召集油联盟管理平台</h3>
    <div class="item row">
        <a href="login" class="btn btn-link navbar">站点 入口</a>
    </div>
    <div class="item row">
        <a href="loginu" class="btn btn-link navbar">后台管理系统 入口</a>
    </div>
</div>
</body>
</html>