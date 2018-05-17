<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set value="${pageContext.request.contextPath}" var="cxt"></c:set>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">

<title>员工管理</title>
<meta name="keywords" content="员工管理">
<meta name="description" content="员工管理">

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


<script type="text/javascript">
	var path="${cxt}"

	$(function() {
		$('a.pageable').click(function() {
			var name = $(this).attr('name');
			var current = parseInt('${pageNo}');
			if (name == 'firstpage') {
				$('input:hidden[name=\'pageNo\']').val(1);
			} else if (name == 'nextpage') {
				$('input:hidden[name=\'pageNo\']').val(current + 1);
			} else if (name == 'prevpage') {
				$('input:hidden[name=\'pageNo\']').val(current - 1);
			} else if (name == 'lastpage') {
				$('input:hidden[name=\'pageNo\']').val('${totalPageNo}');
			} else {
				return;
			}
			var page = $('input:hidden[name=\'pageNo\']').val();
			window.location.href = path + "/findAllEmploye?pageNo=" + page;
		});
	});
</script>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/statics/css/demos/iconfont/iconfont.css">

</head>
<body>
	<%@include file="common/header.jsp"%>
	<div class="row wrapper border-bottom gray-bg page-heading">
		<div class="col-lg-12">
			<!-- <h2></h2>-->
			<ol class="breadcrumb gray-bg">
				<li><a href="${pageContext.request.contextPath}/backend/sumOil">首页</a></li>
				<li><a href="javascript:void(0)">站点管理</a></li>
				<li><label>部门/员工</label></li>
			</ol>
		</div>
		<div class="oli_site">
			<a href="javascript:void (0);">员工管理</a>
		</div>
	</div>
	<div class="wrapper wrapper-content animated">
		<div class="row white-bg">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title p0">
						<div class="fr">
							<a href="go" class="btn btn-sm btn-primary">+新增账号</a>
						</div>
					</div>
					<div class="ibox-content pt10">
						<input type='hidden' value='1' name='pageNo' />
						<table class="table table-bordered text-center">
							<thead>
								<tr>
									<th>序号</th>
									<th>用户名</th>
									<th>姓名</th>
									<th>职位</th>
									<th>部门</th>
									<th>手机</th>
									<th>邮箱</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${selectAllEmpPa}" var="emp" varStatus="nn">
									<tr>

										<td>${nn.index+1}</td>
										<td>${emp.loginName}</td>
										<td>${emp.userName}</td>
										<td>${emp.position}</td>
										<td>${emp.department}</td>
										<td>${emp.mobilePhone}</td>
										<td>${emp.email}</td>
										<td><c:set value="${emp.loginName}" var="name" />
										 	<c:set value="${userSession.loginName}" var="name1" /> 
											<c:if test="${name1=='admin' }">
											<a href="selectEmployeById/${emp.id }">编辑</a>&nbsp;
											<a onclick="del(${emp.id })">删除</a>
											</c:if> <c:if test="${name!='admin' and name1!='admin'}">
											<a href="selectEmployeById/${emp.id }">编辑</a>&nbsp;
											<a onclick="del(${emp.id })">删除</a>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr class="gray-bg">
									<td colspan="8"><c:choose>
											<c:when test="${totalPageNo==1}">
												<a name='firstpage' class='pageable'>首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    			<a class='pageable' href="javaScript:void(0)"
													style="color:gray">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    			<a href="javaScript:void(0)" style="color:gray"
													class='pageable'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    			<a name='lastpage' class='pageable'>尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			</c:when>
											<c:when test="${pageNo==1}">
												<a name='firstpage' class='pageable'>首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    										<a class='pageable' href="javaScript:void(0)"
													style="color:gray">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    										<a name='nextpage' class='pageable'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    										<a name='lastpage' class='pageable'>尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			</c:when>
											<c:when test="${pageNo==totalPageNo}">
												<a name='firstpage' class='pageable'>首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    				<a name='prevpage' class='pageable'>上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    				<a href="javaScript:void(0)" style="color:gray"
													class='pageable'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    				<a name='lastpage' class='pageable'>尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			</c:when>
											<c:otherwise>
												<a name='firstpage' class='pageable'>首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			<a name='prevpage' class='pageable'>上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			<a name='nextpage' class='pageable'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			<a name='lastpage' class='pageable'>尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			</c:otherwise>
										</c:choose> <%-- <c:choose>
													<c:when test="${pageNo==totalPageNo}">
														<td>首页</td>
														<td>上一页</td>
														<td>下一页</td>
														<td>尾页</td>
													</c:when>
													<c:when test="${pageNo==1}">
														<td>首页</td>
														<td>上一页</td>
														<td><a href="findAllEmploye?pageNo=${pageNo+1}">下一页</a>
														</td>
														<td><a href="findAllEmploye?pageNo=${totalPageNo}">尾页</a>
														</td>
													</c:when>
													<c:when test="${pageNo==totalPageNo}">
														<td><a href="findAllEmploye?pageNo=1">首页</a>
														</td>
														<td><a href="findAllEmploye?pageNo=${pageNo-1}">上一页</a>
														</td>
														<td>下一页</td>
														<td>尾页</td>
													</c:when>
													<c:otherwise>
														<td><a href="findAllEmploye?pageNo=1">首页</a>
														</td>
														<td><a href="findAllEmploye?pageNo=${pageNo-1}">上一页</a>
														</td>
														<td><a href="findAllEmploye?pageNo=${pageNo+1}">下一页</a>
														</td>
														<td><a href="findAllEmploye?pageNo=${totalPageNo}">尾页</a>
														</td>
													</c:otherwise>
												</c:choose> --%></td>
								</tr>
							</tfoot>
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


	<!-- iCheck -->
	<script
		src="${pageContext.request.contextPath}/statics/js/plugins/iCheck/icheck.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/statics/js/demos/peopleMana.js"></script>


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
						var args = arguments,
							guid = fn.guid
								|| $.guid++,
							i = 0,
							toggle = function(
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
