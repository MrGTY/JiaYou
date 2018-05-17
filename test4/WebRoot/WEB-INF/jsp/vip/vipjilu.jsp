<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'vip1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/statics/js/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/plugins/alert/sweet-alert.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/alert/sweet-alert.css">

    <style type="text/css">
        .oli_site a:nth-of-type(1) {
            border: 1px white solid;
            background-color: #008ee0;
            color: white;
        }

        .oli_site a:nth-of-type(2) {
            border: 1px white solid;
            color: #000000;
        }
    </style>
    <!--省市三级联动-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jsAddress.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/ljwrite.css">
  </head>
  
  <body>
	<%@include file="../common/header.jsp"%>
        <div class="row wrapper border-bottom gray-bg page-heading">
            <div class="col-lg-12">
                <ol class="breadcrumb gray-bg">
                    <li>
                        <a href="${pageContext.request.contextPath}/backend/sumOil">首页</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">站点管理</a>
                    </li>
                    <li>
                        <label>会员记录</label>
                    </li>
                </ol>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div><h3>${vipUser.vipName}</h3></div>
                    <div>
                        <div class="col-md-4">联 系 人：${vipUser.userName}</div>
                        <div class="col-md-4">手机号码：${vipUser.phoneNum}</div>
                        <div class="col-md-3">车牌号：${vipUser.busNum}</div>

                    </div>
                    <div>
                        <div class="col-md-4">会员类型:
                        	<c:if test="${vipUser.userType==0}">车主/个人</c:if>
    						<c:if test="${vipUser.userType==1}">物流公司</c:if></div>
                        <div class="col-md-4">注册时间:${vipUser.createTime}</div>
                        <div class="col-md-2">状态：
                        <c:if test="${vipUser.stateTag==0}">启用</c:if>
    					<c:if test="${vipUser.stateTag==1}">禁用</c:if></div>
                    </div>
                </div>
                <div style="float:left;width: 1px;height: 55px;margin-top: 3%; background: red;margin-right: 30px;"></div>
                <div class="col-md-2" style="margin-top: 20px;">
                    <div>剩余油量(吨)</div>
                    <div style="color: red;font-size: 30px;margin-top: -20px;">${vipUser.oilMass}</div>
                </div>
                <div class="col-md-2" style="margin-top: 20px;">
                    <div>轮胎余额(元)</div>
                    <div style="color: red;font-size: 30px;margin-top: -20px;">${vipUser.tyreBalance}</div>
                </div>
                <div class="col-md-1" style="margin-top: 20px;">
                    <div>欠款金额(元)</div>
                    <div style="color: red;font-size: 30px;margin-top: -20px;">${vipUser.qkBalance}</div>
                </div>
            </div>
        </div>
        <div class="wrapper wrapper-content animated">
            <div class="row white-bg">
                <div class="col-lg-12" id="jilu">
                    <div class="col-lg-1"  style="margin-right: 30px;">
                        <a type="button" href="${pageContext.request.contextPath}/vipuserinfo/selectUserandOil?id=${vipUser.id}" target="mainFrame" class="btn btn-primary">燃油收支明细</a>
                    </div>
                    <div class="col-lg-1"  style="margin-right: 30px;">
                        <a  href="${pageContext.request.contextPath}/vipuserinfo/selectUserandTyre?id=${vipUser.id}" target="mainFrame" type="button" class="btn btn-primary">轮胎收支明细</a>
                    </div>
                    <div class="col-lg-1"  style="margin-right: 30px;">
                        <a href="${pageContext.request.contextPath}/vipuserinfo/Gathering?id=${vipUser.id}" target="mainFrame" type="button" class="btn btn-primary">收款记录</a>
                    </div>
                    <div class="ibox float-e-margins" id="showORhidden">
                        <iframe src="${pageContext.request.contextPath}/vipuserinfo/selectUserandOil?id=${vipUser.id}" name="mainFrame" width="100%" height="700px" frameborder="0"></iframe>
                    </div>
                </div>
            </div>
        </div>
         <%@include file="../common/foot.jsp"%>
        </div>
</div>

<!-- Mainly scripts -->
<script src="${pageContext.request.contextPath}/statics/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.4.0"></script>

<!-- JSKnob -->
<script src="${pageContext.request.contextPath}/statics/js/plugins/jsKnob/jquery.knob.js"></script>

<!-- Data picker -->
<script src="${pageContext.request.contextPath}/statics/js/plugins/datapicker/bootstrap-datepicker.js"></script>


<!-- iCheck -->
<script src="${pageContext.request.contextPath}/statics/js/plugins/iCheck/icheck.min.js"></script>
<!--<script src="js/demos/vipjilu.js"></script>-->
<!--时间控件-->
<script src="${pageContext.request.contextPath}/statics/js/plugins/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/demos/time.js"></script>

<script>
    $(document).ready(function () {
        //checkbox美化样式框
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
        //toggle兼容高版本jquery
        $.fn.toggle = function (fn, fn2) {
            var args = arguments, guid = fn.guid || $.guid++, i = 0,
                toggle = function (event) {
                    var lastToggle = ($._data(this, "lastToggle" + fn.guid) || 0) % i;
                    $._data(this, "lastToggle" + fn.guid, lastToggle + 1);
                    event.preventDefault();
                    return args[lastToggle].apply(this, arguments) || false;
                };
            toggle.guid = guid;
            while (i < args.length) {
                args[i++].guid = guid;
            }
            return this.click(toggle);
        };
        //高级搜索滑动菜单
        $("#btn-upper").toggle(function () {
            $("#ser_main").slideDown(500);
        }, function () {
            $("#ser_main").slideUp(500);
        });
        //合同日期起止日期
        $('#data_5 .input-daterange').datepicker({
            keyboardNavigation: false,
            forceParse: false,
            autoclose: true
        });


    });
</script>

</body>
</html>
