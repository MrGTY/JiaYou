<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set value="${pageContext.request.contextPath}" var="cxt" />
<c:set value="<%=basePath%>" var="cxt2" />
<script type="text/javascript">
	var path = "${cxt}";
	var htp = "${cxt2}"
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<title>燃油站点列表</title>
<meta name="keywords" content="燃油站点列表">
<meta name="description" content="燃油站点列表">

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
.oli_site a:nth-of-type(1) {
	border: 1px white solid;
	background-color: #008ee0;
	color: white;
}

.oli_site a:nth-of-type(2) {
	border: 1px white solid;
	color: #000000;
}

textarea {
	　　resize: none;
}
</style>
</head>
<body>
	<%@include file="common/header.jsp"%>
	<div class="row wrapper border-bottom gray-bg page-heading">
		<div class="col-lg-12">
			<ol class="breadcrumb gray-bg">
				<li><a href="${pageContext.request.contextPath}/backend/sumOil">首页</a></li>
				<li><label>后台信息发布</label></li>
			</ol>
		</div>
		<div class="oli_site">
			<a href="javascript:void(0);">信息发布</a>
		</div>
	</div>
	<div class="wrapper wrapper-content animated">
		<div class="row white-bg">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title p0">
						<form class="form-inline" method="post" action="updateheadline">

							<div class="col-sm-12" style="margin-bottom: 10px;">
								<input type="hidden" id="base_case_2"
									value="${headLine.message}" /> <label
									class="col-md-1 col-sm-1 control-label">上次信息:</label>
								<textarea class="col-md-5col-sm-5 col-xs-5" rows="3"
									id="base_case" readonly="readonly" disabled
									onfocus="if(this.value=='') {this.value='';}this.style.color='#666';"
									onblur="if(this.value=='') {this.value='';this.style.color='#333';}"></textarea>
							</div>
							<div class="col-sm-12">
								<label class="col-md-1 col-sm-1 control-label">今日信息:</label>
								<textarea class="col-md-5 col-sm-5 col-xs-5" name="message"
									rows="3" 
									onfocus="if(this.value=='') {this.value='';}this.style.color='#666';"
									onblur="if(this.value=='') {this.value='';this.style.color='#333';}"></textarea>
							</div>
					</div>
					<div class="form-group">
					<div class="col-sm-3">&nbsp;</div>
						<div class="col-sm-3">
							<button type="submit" class="btn btn-primary ">&nbsp;&nbsp;发布&nbsp;&nbsp;</button>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="common/foot.jsp"%>
	</div>
	</div>
	<!-- Mainly scripts -->
	<script
		src="${pageContext.request.contextPath}/statics/js/jquery-2.1.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.4.0"></script>

	<!-- JSKnob -->
	<script
		src="${pageContext.request.contextPath}/statics/js/plugins/jsKnob/jquery.knob.js"></script>

	<!-- Data picker -->
	<script
		src="${pageContext.request.contextPath}/statics/js/plugins/datapicker/bootstrap-datepicker.js"></script>


	<!-- iCheck -->
	<script
		src="${pageContext.request.contextPath}/statics/js/plugins/iCheck/icheck.min.js"></script>
	<!--弹窗-->
	<script
		src="${pageContext.request.contextPath}/statics/js/demos/Dealer.js"></script>
	<script src="${pageContext.request.contextPath}/statics/js/download.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/js/clipboard.min.js"></script>

	<script>
		$(document)
				.ready(
						function() {
							document.getElementById("base_case").value = document
									.getElementById("base_case_2").value;
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