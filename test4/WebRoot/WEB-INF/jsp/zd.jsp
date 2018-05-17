<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>燃油站点首页</title>
    <meta name="keywords" content="燃油站点首页">
    <meta name="description" content="燃油站点首页">

    <link href="${pageContext.request.contextPath }/statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/statics/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/statics/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/statics/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/statics/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/statics/js/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/statics/js/plugins/alert/sweet-alert.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/statics/css/alert/sweet-alert.css">

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
    <script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jsAddress.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/statics/css/demos/nice.css">
    
</head>
<body>
<%@include file="common/siteheader.jsp"%>
        <div class="row wrapper border-bottom gray-bg page-heading wrapper_top">
            <div class="row ">
                <div class="col-md-3">
                    <div class="col-md-6" style="margin-top: 20px;">
                        <div>本月交易额(L)</div>
                        <div style="color:red;font-size: 30px;margin-top: -20px;">${income}</div>
                    </div>
                    <div class="col-md-6" style="margin-top: 20px;">
                        <div>本月成交量(个)</div>
                        <div style="color:red;font-size: 30px;margin-top: -20px;">${id}</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="col-md-6" style="margin-top: 20px;">
                        <div>本月燃油提取(L)</div>
                        <div style="color:red;font-size: 30px;margin-top: -20px;">${amountDrawn}</div>
                    </div>
                    <div class="col-md-6" style="margin-top: 20px;">
                        <div>本月燃油待提取(L)</div>
                        <div style="color:red;font-size: 30px;margin-top: -20px;">${jian}</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="col-md-6" style="margin-top: 20px;">
                        <div>总交易额(L)</div>
                        <div style="color:red;font-size: 30px;margin-top: -20px;">${income2}</div>
                    </div>
                    <div class="col-md-6" style="margin-top: 20px;">
                        <div>总交易量(个)</div>
                        <div style="color:red;font-size: 30px;margin-top: -20px;">${id2}</div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="col-md-6" style="margin-top: 20px;">
                        <div>总燃油提取(L)</div>
                        <div style="color:red;font-size: 30px;margin-top: -20px;">${amountDrawn2}</div>
                    </div>
                     <div class="col-md-6" style="margin-top: 20px;">
                        <div>总燃油待提取(L)</div>
                        <div style="color:red;font-size: 30px;margin-top: -20px;">${jian2}</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="wrapper wrapper-content animated">
            <div class="row white-bg row_top">
                <%-- <div class="col-lg-12">
                    <div class="col-md-1"><img src="${pageContext.request.contextPath }/statics/img/u222.png"></div>
                    <div class="col-md-3">这里是通知公告的第一条信息</div>
                    <div class="col-md-1"><img src="${pageContext.request.contextPath }/statics/img/u226.png" width="42" height="17"></div>
                    <div class="col-md-3">这里是通知公告的第二条信息</div>
                    <div class="col-md-3">这里是通知公告的第三条信息</div>
                    <div class="col-md-1">更多>></div>
                </div> --%>
                <div class="col-lg-12">
                    <div id="containerMounth" style="min-height:270px;"></div>
                </div>
                    
                </div>

                <div class="col-lg-12 col-lg-12_sf">
                <div id="containerYear" style="min-height:270px;"></div>
                </div>
                    
        </div>
	<%@include file="common/sitefoot.jsp"%>
    </div>
</div>
            </div>
            </div>
        </div>

<!-- Mainly scripts -->
<script src="${pageContext.request.contextPath }/statics/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/bootstrap.min.js?v=3.4.0"></script>

<!-- JSKnob -->
<script src="${pageContext.request.contextPath }/statics/js/plugins/jsKnob/jquery.knob.js"></script>

<!-- Data picker -->
<script src="${pageContext.request.contextPath }/statics/js/plugins/datapicker/bootstrap-datepicker.js"></script>


<!-- iCheck -->
<script src="${pageContext.request.contextPath }/statics/js/plugins/iCheck/icheck.min.js"></script>
<!--弹窗-->
<script src="${pageContext.request.contextPath }/statics/js/demos/vipjilu.js"></script>
<!--时间控件-->
<script src="${pageContext.request.contextPath }/statics/js/plugins/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/demos/time.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/echarts.common.min.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/demos/zyearEcharts.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/demos/zechartsMouth.js"></script>

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