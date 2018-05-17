<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">

<title>平台首页</title>
<meta name="keywords" content="平台首页">
<meta name="description" content="平台首页">

<link
	href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/statics/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/statics/css/plugins/datapicker/datepicker3.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/statics/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/statics/css/style.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/statics/js/jquery-1.9.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/statics/js/plugins/alert/sweet-alert.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/statics/css/alert/sweet-alert.css">


<style type="text/css">
.col-lg-6_a span {
	color: red;
}

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
	<div class="row wrapper border-bottom gray-bg page-heading wrapper_top">
		<div class="row ">
			<div class="col-md-4">
				<h3>本月完成量</h3>
				<div class="col-md-6" style="margin-top: 20px;">
					<div>完成量(L)</div>
					<div style="color: red;font-size: 30px;margin-top: -20px;">${homeVo.completeQuantity}</div>
				</div>
				<div class="col-md-6" style="margin-top: 20px;">
					<div>轮胎金额(元)</div>
					<div style="color: red;font-size: 30px;margin-top: -20px;">${homeVo.tiresMoney}</div>
				</div>
			</div>
			<div class="col-md-4">
				<h3>本月提取总量</h3>
				<div class="col-md-6" style="margin-top: 20px;">
					<div>燃油提取(吨)</div>
					<div style="color: red;font-size: 30px;margin-top: -20px;">${homeVo.oilExtraction}</div>
				</div>
				<div class="col-md-6" style="margin-top: 20px;">
					<div>轮胎提取(元)</div>
					<div style="color: red;font-size: 30px;margin-top: -20px;">${homeVo.extract}</div>
				</div>
			</div>
			<div class="col-md-4">
				<h3>本月新增账户</h3>
				<div class="col-md-4" style="margin-top: 20px;">
					<div>燃油站点</div>
					<div style="color: red;font-size: 30px;margin-top: -20px;">${homeVo.fuelSite}</div>
				</div>
				<div class="col-md-4" style="margin-top: 20px;">
					<div>轮胎站点</div>
					<div style="color: red;font-size: 30px;margin-top: -20px;">${homeVo.tyreSite}</div>
				</div>
				<div class="col-md-4" style="margin-top: 20px;">
					<div>新增会员</div>
					<div style="color: red;font-size: 30px;margin-top: -20px;">${homeVo.newAddVip}</div>
				</div>
			</div>
		</div>
	</div>
	<div class="wrapper wrapper-content animated">
		<div class="row white-bg row_top">
			<%-- <div class="col-lg-12">
				<div class="col-md-1">
					<img src="${pageContext.request.contextPath }/statics/img/u222.png">
				</div>
				<div class="col-md-3">这里是通知公告的第一条信息</div>
				<div class="col-md-1">
					<img src="${pageContext.request.contextPath }/statics/img/u226.png"
						width="42" height="17">
				</div>
				<div class="col-md-3">这里是通知公告的第二条信息</div>
				<div class="col-md-3">这里是通知公告的第三条信息</div>
				<div class="col-md-1">更多>></div>
			</div> --%>
			<div class="col-lg-12">
				<div class="col-lg-5 col-lg-5_a">
					<h3>平台汇总统计</h3>
					<div class="col-lg-6 col-lg-6_a">
						<div>
							充值总量:<span>${homeVo.czTotal}</span>L
						</div>
						<div>
							完成总量:<span>${homeVo.comTotal}</span>L
						</div>
						<div>
							未完成量:<span>${homeVo.nocomTotal}</span>L
						</div>
						<div>
							提取总量:<span>${homeVo.tqTotal}</span>L
						</div>
						<div>
							未提取量:<span>${homeVo.wtqTotal}</span>L
						</div>
						<div>
							待审批量:<span>${homeVo.dtqTotal}</span>L
						</div>
						<div>
							服务点量:<span>${homeVo.serviceTotal}</span>个
						</div>
						<div>
							会员总量:<span>${homeVo.vipTotal}</span>个
						</div>
					</div>
					<div class="col-lg-6 col-lg-6_a">
						<div>
							轮胎成交:<span>${homeVo.ltMoney}</span>元
						</div>
						<div>
							轮胎提取量:<span>${homeVo.lttqMoney}</span>元
						</div>
						<div>
							轮胎待提取量:<span>${homeVo.ltdtqMoney}</span>元
						</div>
						<div>
							轮胎站点量:<span>${homeVo.ltzdNum}</span>个
						</div>
					</div>
					<div class="col-lg-12 col-lg-6_a">
						物流公司<span>${homeVo.logisticCompany}</span>个&nbsp;车主<span>${homeVo.persinoal}</span>个
					</div>
				</div>
				<div class="col-lg-7 col-lg-7_sf">
					<div id="conta" style="min-height:292px;"></div>
				</div>
			</div>

			<div class="col-lg-12 col-lg-12_sf">
				<div id="container" style="min-height:270px;"></div>
			</div>
		</div>
	</div>
	<%@include file="common/foot.jsp"%>
	</div>
</div>

	<!-- Mainly scripts -->
	<script
		src="${pageContext.request.contextPath }/statics/js/jquery-2.1.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/statics/js/bootstrap.min.js?v=3.4.0"></script>

	<!-- JSKnob -->
	<script
		src="${pageContext.request.contextPath }/statics/js/plugins/jsKnob/jquery.knob.js"></script>

	<!-- Data picker -->
	<script
		src="${pageContext.request.contextPath }/statics/js/plugins/datapicker/bootstrap-datepicker.js"></script>


	<!-- iCheck -->
	<script
		src="${pageContext.request.contextPath }/statics/js/plugins/iCheck/icheck.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/statics/js/demos/mouthEcharts.js"></script>
	<script
		src="${pageContext.request.contextPath }/statics/js/demos/yearEcharts.js"></script>
	<script
		src="${pageContext.request.contextPath }/statics/js/echarts.common.min.js"></script>

	<script>
		$(document)
				.ready(
						function() {
							//checkbox美化样式框
							$('.i-checks').iCheck({
								checkboxClass : 'icheckbox_square-green',
								radioClass : 'iradio_square-green',
							});
							//toggle兼容高版本jquery
							$.fn.toggle = function(fn, fn2) {
								var args = arguments, guid = fn.guid
										|| $.guid++, i = 0, toggle = function(
										event) {
									var lastToggle = ($._data(this,
											"lastToggle" + fn.guid) || 0)
											% i;
									$._data(this, "lastToggle" + fn.guid,
											lastToggle + 1);
									event.preventDefault();
									return args[lastToggle].apply(this,
											arguments) || false;
								};
								toggle.guid = guid;
								while (i < args.length) {
									args[i++].guid = guid;
								}
								return this.click(toggle);
							};
							//高级搜索滑动菜单
							$("#btn-upper").toggle(function() {
								$("#ser_main").slideDown(500);
							}, function() {
								$("#ser_main").slideUp(500);
							});
							//合同日期起止日期
							$('#data_5 .input-daterange').datepicker({
								keyboardNavigation : false,
								forceParse : false,
								autoclose : true
							});

						});
	</script>

</body>

</html>
