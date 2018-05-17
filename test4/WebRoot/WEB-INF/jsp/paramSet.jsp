<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>
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

    <title>参数设置</title>
    <meta name="keywords" content="参数设置">
    <meta name="description" content="参数设置">

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
    <script type="text/javascript">
    	$(function(){
    	var msg="${msg}";
		if(msg!=""){
		setTimeout(function () {
			swal(msg);
		 }, 50);	
			}
			});
    </script>
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
                        <a href="javascript:void(0)">参数设置</a>
                    </li>
                </ol>
            </div>
        </div>
        <div class="wrapper wrapper-content animated">

            <div class="row white-bg">
                <div class="col-lg-12">
                    <h3 style="color:#008ee0">参数设置</h3>
                    <form  method="post" >
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">最低提取量:</label>
                                <div class="col-sm-3">
                               <c:if test="${lis.size()!=0}">
                                	<c:forEach items="${lis}" var="lis">
                                    <input  name='minimunWithDrawal' value="${lis.minimunWithDrawal}" class="form-control"/>
                                	</c:forEach>
                                </c:if>
                               <c:if test="${lis.size()==0}">
                                    <input  name='minimunWithDrawal' value="" class="form-control"/>
                                </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">&nbsp;</label>
                                <label class="col-sm-6 control-label"><span class="text-danger">*</span>最低提取量为空或0，则默认为1吨</label>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">提取单位换算:&nbsp;1&nbsp;吨&nbsp;=</label>
                                <div class="col-sm-3">
                                <c:if test="${lis.size()!=0}">
                                	 <c:forEach items="${lis}" var="lis">
                                    <input name='unitConversion' value="${lis.unitConversion}" class="form-control"/>
                                	</c:forEach>
                                </c:if>
                                <c:if test="${lis.size()==0}">
                                    <input name='unitConversion' value="" class="form-control"/>
                                </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">&nbsp;</label>
                                <label class="col-sm-6 control-label"><span class="text-danger">*</span> 为空或0，则默认为1180吨</label>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">轮胎品牌:</label>
                                <div class="col-sm-9">
                                <c:if test="${lis.size()!=0}">
                                	<c:forEach items="${lis}" var="lis">
                                    <input  name='tyreBrand' value="${lis.tyreBrand}" class="form-control"/>
                                	</c:forEach>
                                </c:if>
                                <c:if test="${lis.size()==0}">
                                    <input  name='tyreBrand' value="" class="form-control"/>
                                </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">&nbsp;</label>
                                <label class="col-sm-6 control-label"><span class="text-danger">*</span>
                                    多个规格间用","分割</label>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">轮胎规格:</label>
                                <div class="col-sm-9">
                                <c:if test="${lis.size()!=0}">
                                	<c:forEach items="${lis}" var="lis">
                                    <input name='typrSpec' value="${lis.typrSpec}" class="form-control"/>
                                	</c:forEach>
                                </c:if>
                                <c:if test="${lis.size()==0}">
                                    <input name='typrSpec' value="" class="form-control"/>
                                </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">&nbsp;</label>
                                <label class="col-sm-6 control-label"><span class="text-danger">*</span>
                                    多个品牌用","分割</label>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">轮胎材质:</label>
                                <div class="col-sm-9">
                                <c:if test="${lis.size()!=0}">
                                	<c:forEach items="${lis}" var="lis">
                                    <input  name='typrMaterial' value="${lis.typrMaterial}" class="form-control"/>
                                	</c:forEach>
                                </c:if>
                                <c:if test="${lis.size()==0}">
                                    <input  name='typrMaterial' value="" class="form-control"/>
                                </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">&nbsp;</label>
                                <label class="col-sm-6 control-label"><span class="text-danger">*</span>
                                    多个材质用","分割</label>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-2">&nbsp;</div>
                                <div class="col-sm-9">
                                    <input type="submit" class="btn btn-primary btn-lg" value="确定"/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
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
<!--弹窗-->
<script src="${pageContext.request.contextPath}/statics/js/demos/Dealer.js"></script>


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
