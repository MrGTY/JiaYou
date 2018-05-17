<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<title>我的燃油站点</title>
	<meta name="keywords" content="我的燃油站点">
    <meta name="description" content="我的燃油站点">

    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet">
    <style type="text/css">
        .oli_site a:nth-of-type(1) {
            border: 1px white solid;
            background-color: #9F88FF;
            color: white;
        }

        .oli_site a:nth-of-type(2) {
            border: 1px white solid;
        }
    </style>
</head>
<body>
	<%@include file="common/siteheader.jsp"%>
        <div class="row wrapper border-bottom gray-bg page-heading">
            <div class="col-lg-12">
                <ol class="breadcrumb gray-bg">
                    <li>
                        <a href="${pageContext.request.contextPath}/backend/selectOil">首页</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">站点管理</a>
                    </li>
                    <li>
                        <label>我的燃油站点</label>
                    </li>
                </ol>
            </div>
        </div>
        <div class="wrapper wrapper-content animated">
            <div class="white-bg">
                <div class="row ibox-content p20">
                    	<input type="hidden" name="id" value="${gass.id}" />
                    	<input type="hidden" name="qrCodeImg" value="${gass.qrCodeImg}"/>
                    	<input type="hidden"  id="base_case_2" value="${gass.description}"/>
                        <div class="col-lg-12 bordered">
                            <h3 class="text-info">基础资料</h3>
                            <div class="col-lg-8 col-md-9  p20">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>站点名称:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="siteName" value="${gass.siteName}" id="zdname" readonly="readonly"
                                               class="form-control">
                                    </div>
                                </div>
                                <div>
                            </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>省市县:</label>
                                    <div class="col-sm-9 help-block">
                                        <input type="text" value="${gass.province}${gass.city}${gass.county}" readonly="readonly" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>详细地址:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="detaileAddress" value="${gass.detaileAddress}" id="address" readonly="readonly" class="form-control" placeholder="请输入详细地址">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label class="col-md-2 col-sm-2 control-label"><span
                                                class="text-danger">*</span>经度:</label>
                                        <div class="col-md-2 col-sm-10 ">
                                            <input type="text" value="${gass.longitude}" id="jd" readonly="readonly" name="longitude" class="form-control" placeholder="">
                                        </div>
                                        <label class=" col-sm-1 control-label"><span
                                                class="text-danger">*</span>纬度:</label>
                                        <div class="col-md-2 col-sm-10 ">
                                            <input type="text" name="latitude" value="${gass.latitude}" id="wd" readonly="readonly" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label class="col-md-2 col-sm-2 control-label"><span class="text-danger">*</span>商户覆盖半径:</label>
                                        <div class="col-md-2 col-sm-10 ">
                                            <input type="text" name="coverRadius" value="${gass.coverRadius}" id="sh" readonly="readonly" class="form-control" placeholder="KM">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label class="col-md-2 col-sm-2 control-label"><span class="text-danger">*</span>联系人:</label>
                                        <div class="col-md-3 col-sm-10 ">
                                            <input type="text" name="contact" value="${gass.contact}" id="lxr" readonly="readonly" class="form-control" placeholder="请输入联系人">
                                        </div>
                                        <label class=" col-sm-1 control-label"><span class="text-danger">*</span>手机:</label>
                                        <div class="col-md-3 col-sm-10 ">
                                            <input type="text" name="mobilePhone" value="${gass.mobilePhone}" readonly="readonly" id="phone" pattern="^1[123456789]\d{9}$" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>初始油量:</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="initialOil" value="${gass.initialOil}" readonly="readonly" id="cs" class="form-control" placeholder="吨">
                                    </div>
                                    <label class="col-sm-2 control-label"><span class="text-danger"></span>服务费:</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="charge" value="${gass.charge}" readonly="readonly" id="fuwu" class="form-control" placeholder="元/L">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 col-sm-2 control-label">备注:</label>
                                    <div class="col-sm-7">
                                    <textarea class="col-md-10col-sm-12 col-xs-12" readonly="readonly" name="description" rows="3" id="base_case"
                                              onfocus="if(this.value=='') {this.value='';}this.style.color='#666';"
                                              onblur="if(this.value=='') {this.value='';this.style.color='#333';}"></textarea>
                                    </div>
                                </div>
                                <h3 class="text-info">账号信息</h3>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>账号:</label>
                                    <div class="col-sm-5">
                                        <input type="text" name="code" value="${gass.code}" readonly="readonly" id="uname" class="form-control" placeholder="请输入账号">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>密码:</label>
                                    <div class="col-sm-5">
                                        <input type="text" name="password" value="${gass.password}" readonly="readonly" id="pwd" class="form-control" placeholder="请输入密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>账户状态:</label>
                                    <div class="col-lg-5 itit_radio">
                                        <c:if test="${gass.status==0}">
                                        	禁用中
                                        </c:if>
                                        <c:if test="${gass.status==1}">
                                        	启用中
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group mtb0" >
                                <h3 class="text-info" style="margin-top: 25px">燃油站点二维码</h3>
                                <div class="col-sm-5 "><img alt="" src="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}/statics/qrcode/${gass.qrCodeImg}" width="200" height="200"></div>
                                <div class="col-sm-5">
                                	<div class="col-sm-12 ">&nbsp;</div>
                                	<div class="col-sm-12 ">&nbsp;</div>
                                	<div class="col-sm-12 "><div class="col-sm-1">&nbsp;</div><a srcimg="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}/statics/qrcode/${gass.qrCodeImg}" downimg="${gass.qrCodeImg}" onClick="downimg(this);">下载</a></div>
                                	<div class="col-sm-12 "><div class="col-sm-1">&nbsp;</div><a class="copyurl" data-clipboard-action="copy" data-clipboard-text="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}/statics/qrcode/${gass.qrCodeImg}">复制链接</a></div>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
        </div>
        <%@include file="common/sitefoot.jsp"%>
    </div>
</div>

<!-- Mainly scripts -->
<script src="${pageContext.request.contextPath}/statics/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.4.0"></script>

<!-- JSKnob -->
<script src="${pageContext.request.contextPath}/statics/js/plugins/jsKnob/jquery.knob.js"></script>

<!-- Data picker -->
<script src="${pageContext.request.contextPath}/statics/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/clipboard.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/download.js"></script>
<!-- iCheck -->
<script src="${pageContext.request.contextPath}/statics/js/plugins/iCheck/icheck.min.js"></script>
<script>
    $(document).ready(function () {
    	document.getElementById("base_case").value=document.getElementById("base_case_2").value;
    	var clipboard = new Clipboard('.copyurl');
		clipboard.on('success', function(e) {
			console.info('Action:', e.action);
			console.info('Text:', e.text);
			console.info('Trigger:', e.trigger);
			alert("复制成功");
			e.clearSelection();
		});
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
</script
</body>
</html>