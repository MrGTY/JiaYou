<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
               	<li>
                    <a href="${pageContext.request.contextPath}/backend/selectOil"><i class="icon_nav nav_i_10"></i><span class="nav-label">首页</span></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/site/dearselect"><i class="icon_nav nav_i_02"></i><span class="nav-label">我的</span></a>
                <li>
                    <a href="${pageContext.request.contextPath}/site/showyou"><i class="icon_nav nav_i_01"></i><span class="nav-label">收支记录</span> </a>
                <li>
                    <a href="${pageContext.request.contextPath}/site/fuwu"><i class="icon_nav nav_i_01"></i><span class="nav-label">服务费记录</span> </a>
                <li>
                    <a href="${pageContext.request.contextPath}/site/showdealer"><i class="icon_nav nav_i_01"></i><span class="nav-label">提取申请表</span></a>
                <li>
                    <a href="javaScript:void(0)"><i class="icon_nav nav_i_03"></i><span class="nav-label">客服热线</span></a>
            </ul>

        </div>
    </nav>
    <div id="page-wrapper" class="white-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <span class="navbar-logo" style="width:100px;"><img src="${pageContext.request.contextPath}/statics/img/20180326150324.png" width="100" height="50"/></span>
                    <span class="navbar-tit"><a href="${pageContext.request.contextPath}/backend/selectOil">召集购油管理平台</a></span>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <span class="m-r-sm text-muted"><a href="javaScript:void(0)"><i class="fa fa-phone-square"></i></a>${devUserSession.mobilePhone}</span>
                    </li>
                    <li>
                        <span class="m-r-sm text-muted"><a href="${pageContext.request.contextPath}/backend/selectOil" title="返回首页"><i class="fa fa-user"></i></a>${devUserSession.siteName}</span>
                    </li>
                    <li class="mr20">
                        <span class="m-r-sm text-muted"><a class="text-muted" href="${pageContext.request.contextPath}/backend/zdlogout"><i
                                class="fa fa-sign-out"></i>退出</a></span>
                    </li>
                     <li>
                       <a data-toggle="dropdown" class="dropdown-toggle" href="SetUp.html">
                            <span class="m-r-sm text-muted ">设置<i class="fa fa-angle-down"></i></span>
                        </a>
                       <!--  <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="SetPlevel.html">经销商级别管理</a></li>
                            <li><a href="CompanyUser.html">部门与员工</a></li>
                            <li><a href="OperationLog.html">操作日志</a></li>
                            <li class="divider"></li>
                            <li><a href="">安全退出</a></li>
                        </ul>  -->
                    </li> 
                    <!-- <li>
                        <span class="m-r-sm text-muted"><a href="javaScript:void(0)" class="mr">反馈</a></span>
                    </li> -->
                </ul>

            </nav>
        </div>