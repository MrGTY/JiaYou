<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
	<title>好友分配</title>
	<meta name="keywords" content="好友分配">
    <meta name="description" content="好友分配">

    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/statics/js/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/plugins/alert/sweet-alert.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/alert/sweet-alert.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/demos/nice.css">
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
</head>

<body>
	<%@include file="common/header.jsp"%>
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
                        <label>好友分配</label>
                    </li>
                </ol>
            </div>
            <div class="oli_site">
                <a href="javascript:void (0);">添加加油分配量</a>
            </div>
        </div>
        <div class="wrapper wrapper-content animated">
            <div class="row white-bg">
                <form method="post" action="fenpei">
                    <div class="col-md-11 fa_fa">
                        <div class="form-group col-md-11">
                            <label  class="col-md-1"><span class="text-danger">*</span>分拨会员:</label>
                            <div class="col-md-8 fa_jilu12">
                                <input type="text" class="form-control" name="vipname1" required id="vipvalue1"  placeholder="输入会员名称，支持模糊搜索">
                            </div>
                            <div class="form-group col-md-1">
                                <button type="button" class="btn btn-primary" id="searchshow" >搜索</button>
                            </div>
                        </div>
                        <div class="form-group col-md-7 col-md-7_jilu12" id="papa" >
                            <div id="vipname1" class="col-md-12 jilu12" >
                                <div onclick="getword(this);">中国石化有限公司</div>
                            </div>
                        </div>
                        <div class="form-group col-md-11">
                            <label  class="col-md-1"><span class="text-danger">*</span>收油会员:</label>
                            <div class="col-md-8 fa_jilu122"><input type="text" required name="vipname2" class="form-control" id="vipvalue2" id="" placeholder="输入站点名称，支持模糊搜索">
                            </div>
                            <div class="form-group col-md-1">
                                <button type="button" class="btn btn-primary" id="searchshow2">搜索</button>
                            </div>
                        </div>
                        <div class="form-group col-md-7 col-md-7_jilu12a" id="papa2">
                            <div id="vipname2" class="col-md-12 jilu12a" >
                                <div onclick="getword2(this);">中国石化有限公司</div>
                            </div>
                        </div>
                        <div class="form-group col-md-11">
                            <label  class="col-md-1"><span class="text-danger">*</span>分配量(L):</label>
                            <div class="col-md-3"><input type="text" id="allocation" required value="" name="fenpeilu" class="form-control"></div>
                        </div>
                        <div class="form-group col-md-11">
                            <div class="form-group col-md-2">
                                 <button  id="fenpeisave" class="btn btn-primary btn-lg" style="text-align: center;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确定&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
                            </div>
                            <div style="color:red;font-size: 18px;">${messgae}</div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
		<%@include file="common/foot.jsp"%>
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
<script src="${pageContext.request.contextPath}/statics/js/demos/vipAddjilu.js"></script>

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