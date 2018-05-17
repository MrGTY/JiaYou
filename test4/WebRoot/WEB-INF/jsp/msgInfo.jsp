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

    <title>设置-操作日志</title>

    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.css?v=2.2.0" rel="stylesheet">

</head>

<body>
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
                    <a href="${pageContext.request.contextPath}/gasstation/showtire"><i class="icon_nav nav_i_02"></i><span class="nav-label">轮胎站点</span></a>
                    
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
                <!-- <li>
                    <a href="javaScript:void(0)"><i class="icon_nav nav_i_07"></i><span class="nav-label">数据分析</span></a>
                </li> -->
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
						</ul></li>
					<!-- <li><span class="m-r-sm text-muted"><a href="#"
							class="mr"></a>反馈</span></li> -->
				</ul>
				</nav>
        </div>
            <div class="row wrapper border-bottom gray-bg page-heading">
                <div class="col-lg-12">
                    <h2></h2>
                    <ol class="breadcrumb gray-bg">
                        <li>
                            <a href="index.html">主页</a>
                        </li>
                        <li>
                            <a href="index.html">设置</a>
                        </li>
                        <li>
                            <label>操作日志</label>
                        </li>
                    </ol>
                </div>
                
            </div>
            <div class="wrapper wrapper-content animated">
                <div class="panel blank-panel">
                    
                    <div class="row">
                        <div class="col-lg-12">
                            <h2 class="text-primary">操作日志</h2>   
                            <div class="row plr30">
                               <label class="fl mr20">筛选:</label>
                               <div class="fl">
                                   <select class="" name="">
                                      <option selected="selected" value="1">全部时间</option>
                                      <option value="2">今天</option>
                                      <option value="3">近三天</option>
                                      <option value="4">近一周</option>
                                      <option value="5">近半月</option>
                                      <option value="6">近一月</option>
                                      <option value="7">近三个月</option>
                                      <option value="8">近半年</option>
                                      <option value="9">近一年</option>
                                   </select>
                                   <select class="" name="">
                                      <option selected="selected" value="1">本人</option>
                                      <option value="2">管理员</option>
                                      <option value="2">管理员</option>
                                   </select>                                
                               </div> 
                                
                            </div>           
                            <div class="widget row ptb0">
                                <table class="full-width table-hover">
                                    <thead>
                                        <tr class="gray-bg">
                                            <th>操作人</th>
                                            <th>时间</th>
                                            <th>类型</th>
                                            <th>操作日志(显示10条)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>王磊/网络技术部</td> 
                                            <td>2016-07-29 16:43:27</td>
                                            <td>修改用户</td>
                                            <td>修改用户：310091153</td>
                                        </tr>
                                        <tr>
                                            <td>王磊/网络技术部</td> 
                                            <td>2016-07-29 16:43:27</td>
                                            <td>系统登录</td>
                                            <td>修改用户：310091153</td>
                                        </tr>
                                        <tr>
                                            <td>王磊/网络技术部</td> 
                                            <td>2016-07-29 16:43:27</td>
                                            <td>新增收藏商品</td>
                                            <td>修改用户：310091153</td>
                                        </tr>
                                        <tr>
                                            <td>王磊/网络技术部</td> 
                                            <td>2016-07-29 16:43:27</td>
                                            <td>导入快报</td>
                                            <td>修改用户：310091153</td>
                                        </tr>
                                        <tr>
                                            <td>王磊/网络技术部</td> 
                                            <td>2016-07-29 16:43:27</td>
                                            <td>系统登录</td>
                                            <td>修改用户：310091153</td>
                                        </tr>
                                        <tr>
                                            <td>王磊/网络技术部</td> 
                                            <td>2016-07-29 16:43:27</td>
                                            <td>修改用户</td>
                                            <td>修改用户：310091153</td>
                                        </tr>
                                        <tr>
                                            <td>王磊/网络技术部</td> 
                                            <td>2016-07-29 16:43:27</td>
                                            <td>系统登录</td>
                                            <td>修改用户：310091153</td>
                                        </tr>
                                        <tr>
                                            <td>王磊/网络技术部</td> 
                                            <td>2016-07-29 16:43:27</td>
                                            <td>新增收藏商品</td>
                                            <td>修改用户：310091153</td>
                                        </tr>
                                        <tr>
                                            <td>王磊/网络技术部</td> 
                                            <td>2016-07-29 16:43:27</td>
                                            <td>导入快报</td>
                                            <td>修改用户：310091153</td>
                                        </tr>
                                        <tr>
                                            <td>王磊/网络技术部</td> 
                                            <td>2016-07-29 16:43:27</td>
                                            <td>系统登录</td>
                                            <td>修改用户：310091153</td>
                                        </tr>
                                        
                                    </tbody>
                                    <tfoot>
                                       <tr class="gray-bg text-right">
                                          <td colspan="4">共201条/5页&nbsp;&nbsp;每页显示10条&nbsp;&nbsp;第3页</td>
                                       </tr>
                                    </tfoot>
                                </table>
                            </div>
        
                                
                         </div>
        
                    </div>
                    
                </div>
            </div>
            
            
            
            
            <div class="row">
                <div class="footer">
                    <div class="pull-right">
                        By：<a href="http://www.muyimall.com/" target="_blank">木一商城</a>
                    </div>
                    <div>
                        <label>Copyright</label> &copy; 木一商城 2016 All Rights Reserved.
                    </div>
                </div>
            </div>
            
            
        </div>
        
    </div>



    <!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath}/statics/js/jquery-2.1.1.min.js"></script>
</body>

</html>
