<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set value="${pageContext.request.contextPath}" var="cxt" />
<c:set value="<%=basePath %>" var="cxt2"/>
<script type="text/javascript">
	var path = "${cxt}";
	var htp="${cxt2}"
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
</style>
</head>
<body>
	<%@include file="common/header.jsp"%>
			<div class="row wrapper border-bottom gray-bg page-heading">
				<div class="col-lg-12">
					<ol class="breadcrumb gray-bg">
						<li><a href="${pageContext.request.contextPath}/backend/sumOil">首页</a></li>
						<li><a href="javascript:void(0)">客户信息</a></li>
					</ol>
				</div>
				<div class="oli_site">
					<a href="javascript:void(0);">客户信息展示</a>
				</div>
			</div>
			<div class="wrapper wrapper-content animated">
				<div class="row white-bg">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title p0">
								<form class="form-inline" method="post" action="showhu">
									<input type="hidden" name="pageIndex" value="1" /> 
									<div class="form-group">
										<label for="exampleInputName2">客户姓名:</label> <input
											type="text" name="name" class="form-control"
											value="${name}">
										<button type="submit" class="btn btn-primary ">&nbsp;&nbsp;搜索&nbsp;&nbsp;</button>
									</div>
								</form>
							</div>
							<div class="ibox-content pt10">
								<table class="table table-bordered text-center">
									<thead>
										<tr>
											<th>客户姓名</th>
											<th>手机号码</th>
											<th>详细地址</th>
											<th>公司名称</th>
											<th>创建时间</th>
										</tr>
									</thead>
									<c:if test="${pages.totalCount==0}">
               							<tr>
               								<td colspan="10">暂无数据!</td>
               							</tr>
               						</c:if>   
               					 	<c:if test="${pages.totalCount>0}">
									<tbody>
										<c:forEach var="s" items="${list}" varStatus="status">
											<tr>
												<td>${s.name}</td>
												<td>${s.phone}</td>
												<td>${s.address}</td>
												<td>${s.company}</td>
												<td><fmt:formatDate value="${s.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr class="gray-bg">
											<td colspan="4">
												<c:choose>
													<c:when test="${pages.totalPageCount== 1}">
														<a href="javaScript:void(0)" style="color:gray">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        				<a href="javaScript:void(0)" style="color:gray">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        				<a href="javaScript:void(0)" style="color:gray">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                       		 			<a href="javaScript:void(0)" style="color:gray">尾页</a>
													</c:when>
                                    				<c:when test="${pages.currentPageNo== 1}">
                                    					<a href="javaScript:void(0)" style="color:gray">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        				<a href="javaScript:void(0)" style="color:gray">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        				<a href="javascript:page_nav(document.forms[0],${pages.currentPageNo+1});">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        				<a href="javascript:page_nav(document.forms[0],${pages.totalPageCount});">尾页</a>
                                    				</c:when>
                                    				<c:when test="${pages.currentPageNo ==pages.totalPageCount }">
                                    					<a href="javascript:page_nav(document.forms[0],1);">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        				<a href="javascript:page_nav(document.forms[0],${pages.currentPageNo-1});">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        				<a href="javaScript:void(0)" style="color:gray">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                       		 			<a href="javaScript:void(0)" style="color:gray">尾页</a>
                                    				</c:when>
                                    				<c:otherwise>
                                    					<a href="javascript:page_nav(document.forms[0],1);">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                       		 		<a href="javascript:page_nav(document.forms[0],${pages.currentPageNo-1});">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                       		 		<a href="javascript:page_nav(document.forms[0],${pages.currentPageNo+1});">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                       		 		<a href="javascript:page_nav(document.forms[0],${pages.totalPageCount});">尾页</a>
                                    				</c:otherwise>
                                    			</c:choose>
											</td>
											<td class="text-right" colspan="1">共${pages.totalCount
												}条/${pages.totalPageCount}页&nbsp;&nbsp;每页显示${pages.pageSize}条&nbsp;&nbsp;第${pages.currentPageNo}页</td>
										</tr>
									</tfoot>
									</c:if> 
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
	<!--弹窗-->
	<script
	<script src="${pageContext.request.contextPath}/statics/js/download.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/js/clipboard.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/js/rollpage.js"></script>

	<script>
		$(document)
				.ready(
						function() {
							var bbb = document.getElementById("bbb");
							var clipboard = new Clipboard(bbb);
							clipboard.on('success', function(e) {
								console.info('Action:', e.action);
								console.info('Text:', e.text);
								console.info('Trigger:', e.trigger);
								alert("复制成功");
								e.clearSelection();
							});
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