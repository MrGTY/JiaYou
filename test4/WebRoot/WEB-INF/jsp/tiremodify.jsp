<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
	<title>修改轮胎站点</title>
	<meta name="keywords" content="修改轮胎站点">
    <meta name="description" content="修改轮胎站点">
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/statics/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3&ak=6qgpMsvLLUrNmlHEjSb6mSNC73wmj2fV"></script>
    <script src="${pageContext.request.contextPath}/statics/js/plugins/alert/sweet-alert.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/alert/sweet-alert.css">
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
    <!--省市三级联动-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jsAddress.js"></script>
</head>
<body>
<div id="container"
         style="display: none;">
    </div>
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
                        <label>修改轮胎站点</label>
                    </li>
                </ol>
            </div>
        </div>
        <div class="wrapper wrapper-content animated">
            <div class="white-bg">
                <div class="row ibox-content p20">
                    <form method="post" class="form-horizontal" action="tiremodifysave">
                    	<input type="hidden" name="id" value="${gass.id}" />
                    	<input type="hidden"  id="base_case_2" value="${gass.description}" />
                        <div class="col-lg-12 bordered">
                            <h3 class="text-info">基础资料</h3>
                            <div class="col-lg-8 col-md-9  p20">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span
                                            class="text-danger">*</span>站点名称:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="siteName" value="${gass.siteName}" class="form-control" id="zdname" required
                                               placeholder="（站点名称必须唯一，长度<45个字符）">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>省市县:</label>
                                    <div class="col-sm-9 help-block">
                                        <select id="cmbProvince" name="province" class="col-sm-3 form-city"></select>&nbsp;
                                        <select id="cmbCity" name="city" class="col-sm-3 form-city"></select>&nbsp;
                                        <select id="cmbArea" name="county" class="col-sm-3 form-city"></select>
                                        <input type="hidden" id="province" value="${gass.province }">
                                        <input type="hidden" id="city" value="${gass.city }">
                                        <input type="hidden" id="county" value="${gass.county }">
                                        <script type="text/javascript">
                                        	var province=document.getElementById("province").value;
	                                        var city=document.getElementById("city").value;
	                                        var county=document.getElementById("county").value;
                                            addressInit('cmbProvince', 'cmbCity', 'cmbArea', province,city,county);
                                        </script>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span
                                            class="text-danger">*</span>详细地址:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="detaileAddress" value="${gass.detaileAddress}" id="address" required class="form-control" placeholder="请输入详细地址">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label class="col-md-2 col-sm-2 control-label"><span
                                                class="text-danger">*</span>经度:</label>
                                        <div class="col-md-2 col-sm-10 ">
                                            <input type="text" value="${gass.longitude}" name="longitude" id="jd" required class="form-control" placeholder="">
                                        </div>
                                        <label class=" col-sm-1 control-label"><span
                                                class="text-danger">*</span>纬度:</label>
                                        <div class="col-md-2 col-sm-10 ">
                                            <input type="text" value="${gass.latitude}" name="latitude" id="wd" required class="form-control" placeholder="">
                                        </div>
                                        <div class="col-md-2 col-sm-10 ">
                                            <input type="button" onclick="searchByStationName();"   class="form-control" style="background-color: #008ee0;color: white" value="自动获取">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label class="col-md-2 col-sm-2 control-label"><span
                                                class="text-danger">*</span>联系人:</label>
                                        <div class="col-md-3 col-sm-10 ">
                                            <input type="text" name="contact" value="${gass.contact}" id="lxr" required  class="form-control" placeholder="">
                                        </div>
                                        <label class=" col-sm-1 control-label"><span
                                                class="text-danger">*</span>手机:</label>
                                        <div class="col-md-3 col-sm-10 ">
                                            <input type="text" name="mobilePhone" value="${gass.mobilePhone}" required id="phone" pattern="^1[123456789]\d{9}$" class="form-control" placeholder="">

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span
                                            class="text-danger">*</span>初始金额:</label>
                                    <div class="col-sm-2">
                                        <input type="text" name="initialOil" value="${gass.initialOil}" readonly="readonly" id="cs" class="form-control" placeholder="元">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label class="col-sm-2 control-label"><span
                                                class="text-danger">*</span>轮胎品牌:</label>
                                        <div class="col-lg-8 itit_radio">
                                        <c:set var="tyreBrandstatus" value="${gass.tyreBrand}"></c:set>
                                        	<c:forEach var="s1" items="${tyreBrands}">
                                        		<c:set var="s1" value="${s1}"></c:set>
                                        		<label class="radio-inline"> <input type="checkbox"  value="${s1}"  <c:if test="${fn:contains(tyreBrandstatus,s1)}">checked='checked'</c:if> name="optionsRadios1">${s1}</label>
                                        	</c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label class="col-sm-2 control-label"><span
                                                class="text-danger">*</span>轮胎规格:</label>
                                        <div class="col-lg-8 itit_radio">
                                         <c:set var="typrSpecstatus" value="${gass.typrSpec}"></c:set>
                                        	<c:forEach var="s2" items="${typrSpecs}">
                                        		<c:set var="s2" value="${s2}"></c:set>
                                            	<label class="radio-inline"> <input type="checkbox"  value="${s2}" <c:if test="${fn:contains(typrSpecstatus,s2)}">checked='checked'</c:if> name="optionsRadios2">${s2}</label>
                                        	</c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label class="col-sm-2 control-label"><span
                                                class="text-danger">*</span>轮胎材质:</label>
                                        <div class="col-lg-8 itit_radio">
                                        <c:set var="typrMaterialstatus" value="${gass.typrMaterial}"></c:set>
                                            <c:forEach var="s3" items="${typrMaterials}">
                                            <c:set var="s3" value="${s3}"></c:set>
                                            	<label class="radio-inline"> <input type="checkbox"  value="${s3}" <c:if test="${fn:contains(typrMaterialstatus,s3)}">checked='checked'</c:if>  name="optionsRadios3">${s3}</label>
                                        	</c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 col-sm-2 control-label">备注:</label>
                                    <div class="col-sm-7">
                                    <textarea class="col-md-10col-sm-12 col-xs-12" rows="3" name="description" id="base_case"
                                              onfocus="if(this.value=='') {this.value='';}this.style.color='#666';"
                                              onblur="if(this.value=='') {this.value='';this.style.color='#333';}"></textarea>
                                    </div>
                                </div>
                                <h3 class="text-info">账号信息</h3>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>账号:</label>
                                    <div class="col-sm-5">
                                        <input type="text" name="code" id="codename" readonly="readonly" value="${gass.code}" required class="form-control" placeholder="请输入账号">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>密码:</label>
                                    <div class="col-sm-5">
                                        <input type="text" name="password" value="${gass.password}" id="pwd" class="form-control" placeholder="请输入密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span
                                            class="text-danger">*</span>账户状态:</label>
                                    <div class="col-lg-5 itit_radio">
                                    	<c:set var="radiostatus" value="${gass.status}"></c:set>
                                        <label class="col-lg-4 radio-inline"><input type="radio" value="1" <c:if test="${fn:contains(radiostatus,'1')}">checked='checked'</c:if>
                                                                                    id="optionsRadios1"
                                                                                    name="status">正常</label>
                                        <label class="radio-inline"><input type="radio" value="0"<c:if test="${fn:contains(radiostatus,'0')}">checked="checked"</c:if>
                                                                           id="optionsRadios2"
                                                                           name="status">关闭</label>
                                    </div>
                                </div>
                                <div class="form-group col-sm-12">
                                    <div class=" col-sm-6" style="text-align: center">
                                        <input style="width: 30%" class="btn btn-md btn-primary" id="saves" type="submit" value="保存">
                                        <a style="width: 30%" class="btn btn-md btn-default" onClick="backlist(this);">取消</a><br>
                                        <div>${error}</div>
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
<script src="${pageContext.request.contextPath}/statics/js/demos/baiduMap.js"></script>
<!-- Data picker -->
<script src="${pageContext.request.contextPath}/statics/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/demos/tiremodify.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/download.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/clipboard.min.js"></script>
<!-- iCheck -->
<script src="${pageContext.request.contextPath}/statics/js/plugins/iCheck/icheck.min.js"></script>
<script>
    $(document).ready(function () {
    	document.getElementById("base_case").value=document.getElementById("base_case_2").value; 
        var clipboard = new Clipboard('.copyurl');
		clipboard.on('success', function(e) {
			console.log(e);
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
</script>
</body>
</html>