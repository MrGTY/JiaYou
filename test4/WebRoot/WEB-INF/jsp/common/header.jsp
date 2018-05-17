<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/backend/sumOil"><i class="icon_nav nav_i_10"></i><span class="nav-label">首页</span></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/gasstation/showlist"><i class="icon_nav nav_i_01"></i><span class="nav-label">燃油站点</span> </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/gasstation/showtire"><i class="icon_nav nav_i_01"></i><span class="nav-label">轮胎站点</span></a>
                    
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/vipuserinfo/vipindex"><i class="icon_nav nav_i_03"></i><span class="nav-label">会员管理</span></a>
                	<ul class="nav nav-second-level"> 
                            <li><a href="${pageContext.request.contextPath}/vip/fenpei" >+好友分配</a></li>
                            <li><a href="${pageContext.request.contextPath}/vip/jiayou" >+加油</a></li>
                            <li><a href="${pageContext.request.contextPath}/vip/sign" >+注册会员</a></li>
                        </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/selectAllApply"><i class="icon_nav nav_i_04"></i><span class="nav-label">审批管理</span></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/showhu"><i class="icon_nav nav_i_01"></i><span class="nav-label">客户信息</span></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/selectAllApp"><i class="icon_nav nav_i_06"></i><span class="nav-label">APP版本</span></a>
                </li>
            </ul>
        </div>
    </nav>
    <div id="page-wrapper" class="white-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
				<div class="navbar-header">
					<span class="navbar-logo" style="width:100px;"><img
						src="${pageContext.request.contextPath}/statics/img/20180326150324.png"
						width="100" height="50" /></span> <span class="navbar-tit"><a
						href="${pageContext.request.contextPath}/backend/sumOil">召集油联盟管理平台</a></span>
				</div>
				<ul class="nav navbar-top-links navbar-right">
					<li><span class="m-r-sm text-muted"><a
							href="javaScript:void(0)"><i class="fa fa-phone-square"></i></a>${userSession.mobilePhone}</span>
					</li>
					<li><span class="m-r-sm text-muted"><a
							href="${pageContext.request.contextPath}/backend/sumOil" title="返回首页"><i class="fa fa-user"></i></a>您好，${userSession.userName}</span>
					</li>
					<li class="mr20"><span class="m-r-sm text-muted"><a
							class="text-muted" href="${pageContext.request.contextPath}/backend/userlogout"><i
								class="fa fa-sign-out"></i>退出</span></a></li>
					<li><a data-toggle="dropdown" class="dropdown-toggle"
						href="SetUp.html"> <span class="m-r-sm text-muted ">设置<i
								class="fa fa-angle-down"></i></span>
					</a>
						<ul class="dropdown-menu animated fadeInRight m-t-xs">
							<li><a href="${pageContext.request.contextPath}/findAllEmploye">员工账号</a></li>
							<li><a href="${pageContext.request.contextPath}/updateParam">参数设置</a></li>
							<li><a href="${pageContext.request.contextPath}/updateheadline">信息发布</a></li>
						</ul></li>
					<!-- <li><span class="m-r-sm text-muted"><a href="#"
							class="mr"></a>反馈</span></li> -->
				</ul>
				</nav>
        </div>