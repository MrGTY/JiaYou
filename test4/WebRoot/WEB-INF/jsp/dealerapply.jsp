<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<title>油量申请</title>
	    <meta name="keywords" content="油量申请">
    <meta name="description" content="油量申请">

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/demos/nice.css">
</head>
<body>
	<%@include file="common/header.jsp"%>
        <div class="row wrapper border-bottom gray-bg page-heading">
            <div class="col-lg-12">
                <!-- <h2></h2>-->
                <ol class="breadcrumb gray-bg">
                    <li>
                        <a href="${pageContext.request.contextPath}/backend/sumOil">首页</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">站点管理</a>
                    </li>
                    <li>
                        <label>油量申请</label>
                    </li>
                </ol>
            </div>
        </div>
       <form action="commitsave" method="post">
       	<input type="hidden" name="id" value="${gass.id}"/>
        <div class="wrapper wrapper-content animated">
            <div class="row row-all">
                <h3>油量提取申请：</h3>
                <div class="col-md-8 row_all">
                    <div >${gass.siteName}</div>
                    <div >油量余额(L)：</div>
                    <div>
                        <span>${gass.quota}</span>&nbsp;
                        最多可提取：${ton} 吨 (${all} 升)
                    </div>
                    <div class="ibox-title">
                            <div class="form-group ">
                                <div class="col-md-12 form-group-all">
                                    <label class="col-md-2 dant" for="exampleInputEmail1">提取量:</label>
                                    <div class="col-md-2"><input type="text" class="form-control" name="ton" id="ton" 
                                    leave="${leave}" ton="${ton}" quota="${gass.quota}" con="${ptt.unitConversion}" value="${ton}"></div>
                                    <label class="col-md-1">吨</label>
                                    <label class="col-md-1 dant1" for="exampleInputEmail2">余:</label>
                                    <div class="col-md-2"><input type="text" readonly="readonly" class="form-control" name="leave" id="leave" value="${leave}"></div>
                                    <label class="col-md-1 ">升</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-1">&nbsp;</div>
                                <div class="col-md-6">* 单位换算 1吨 =${ptt.unitConversion}升</div>
                            </div>
                            <button type="subimt" id="ok" class="btn btn-primary">申请提交</button><div id="messgae" style="color:red;font-size:18px;">${message}</div>
                    </div>
                </div>
            </div>
        </div>
       </form>
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
<!--弹窗-->
<script src="${pageContext.request.contextPath}/statics/js/demos/Dealer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/extractioninfo.js"></script>

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