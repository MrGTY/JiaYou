<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set value="${pageContext.request.contextPath}" var="cxt"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">

<title>APP版本管理</title>
<meta name="keywords" content="APP版本管理">
<meta name="description" content="APP版本管理">
<script
	src="${pageContext.request.contextPath}/statics/js/jquery-1.9.1.min.js"></script>
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
<link
	href="${pageContext.request.contextPath}/statics/css/alert/sweet-alert.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/statics/js/plugins/alert/sweet-alert.js"></script>
<script type="text/javascript">
	var path="${cxt}";
	var mas="${message}";
	if(mas!=''){
	setTimeout(function () {
			swal(mas);
		 }, 50);	
			}
</script>
	
<style type="text/css">
.oli_site a:nth-of-type(2) {
	border: 1px white solid;
	background-color: #9F88FF;
	color: white;
}

.oli_site a:nth-of-type(1) {
	border: 1px white solid;
}
</style>

</head>

<body>
	<!--APP删除弹窗-->
	<div class="modal fade" id="myModal1a" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header" style="margin-bottom: -10px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1a"
						style="text-align: center;">APP上传</h4>
				</div>
				<div class="modal-body"
					style="border:1px #e4e4e4 solid; margin-bottom: 10px; background-color: #e4e4e4;border-radius: 3px">
					<div class="form-group" style="text-align: center">
						<form enctype="multipart/form-data" action="addApp" method="post">
							<div class="form-group">
								<label for="exampleInputEmail1">版本类型</label> <input type="text"
									class="form-control" name="versionType" required/>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1a">版本号</label> <input type="text"
									class="form-control" name="versionNumber" required/>
							</div>
							<div class="form-group">
								<label for="exampleInputFile"></label> 
								<input type="file" name="a_downloadLink"/>
							</div>
							<input class="btn btn-primary btn-lg" type="submit" value="上传"/>
							<button type="button" class="btn btn-default btn-lg"
								onclick="overMoney2(this);">取消</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="common/header.jsp"%>
			<div class="row wrapper border-bottom gray-bg page-heading">
				<div class="col-lg-12">
					<ol class="breadcrumb gray-bg">
						<li><a href="${pageContext.request.contextPath}/backend/sumOil">首页</a></li>
						<li><label>APP版本管理</label></li>
					</ol>
				</div>
			</div>
			<div class="wrapper wrapper-content animated">
				<div class="row white-bg">
					<div class="row">
						<div class="col-md-10"></div>
						<div class="col-md-2">
							<a class=" btn btn-primary" onClick="ok(this);">&nbsp;&nbsp;上&nbsp;传&nbsp;&nbsp;</a>
						</div>
					</div>
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-content pt10">
								<table class="table table-bordered text-center">
									<thead>
										<tr>
											<th>序号</th>
											<th>版本类型</th>
											<th>版本号</th>
											<th>上传时间</th>
											<th>下载链接</th>
											<th>操作</th>
										</tr>
									</thead>
									<c:if test="${empty lis}">
               							<tr>
               								<td colspan="6">暂无版本!</td>
               							</tr>
               						</c:if>   
									<tbody>
										<c:forEach items="${lis}" var="li">
											<tr>
												<td>${li.id}</td>
												<td>${li.versionType}</td>
												<td>${li.versionNumber}</td>
												<td>${li.uploadTime}</td>
												<td>${li.downloadLink}</td>
												<td><a class="text-primary"
													onclick="del(${li.id});">删除</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
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

	<script
		src="${pageContext.request.contextPath}/statics/js/demos/apply.js"></script>
	<!-- iCheck -->
	<script
		src="${pageContext.request.contextPath}/statics/js/plugins/iCheck/icheck.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/statics/js/demos/APPs.js"></script>
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
						});
					
	</script>

</body>

</html>
