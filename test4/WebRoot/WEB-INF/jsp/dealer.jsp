<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div class=" modal fade " id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog " role="document" style="width: 300px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">查看二维码</h4>
				</div>
				<div class="modal-body">
					<div class="form-group mtb0" style="position: relative">
						<div class="col-sm-3 ">
							<img id="change"
								src="${pageContext.request.contextPath}/statics/img/test/1516255090.png"
								height="150" width="150" />
						</div>
						<div
							style="cursor: pointer;position: absolute;left:160px;top:20px;">
							<div class="col-sm-1 ">&nbsp;</div>
							<div class="col-sm-7 ">
								<a id="downimg" srcimg="" downimg="" onClick="downimg(this);">下载</a>
							</div>
							<div class="col-sm-1 ">&nbsp;</div>
							<div class="col-sm-1 ">&nbsp;</div>
							<input type="text" style="height: 0; position:absolute;top:300px" id="copy-input" value="test"/> 
							<div class="col-sm-11 ">
								<a id="bbb" class="aaa" data-clipboard-target="#copy-input" data-clipboard-action="copy"
									data-clipboard-text="">复制链接</a>
							</div>
						</div>
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
						<li><a href="javascript:void(0)">站点管理</a></li>
						<li><label>燃油站点列表</label></li>
					</ol>
				</div>
				<div class="oli_site">
					<a href="javascript:void(0);">加油站点</a><a href="${pageContext.request.contextPath}/gasstation/showtire">轮胎站点</a>
				</div>
			</div>
			<div class="wrapper wrapper-content animated">

				<div class="row white-bg">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title p0">
								<form class="form-inline" method="post" action="showlist">
									<input type="hidden" name="pageIndex" value="1" /> 
									<input type="hidden" name="gsType" value="0" />
									<div class="form-group">
										<label for="exampleInputName2">站点名称:</label> <input
											type="text" name="siteName" class="form-control"
											value="${siteName}"> <label for="exampleInputName2">联系人:</label>
										<input type="text" name="contact" class="form-control"
											value="${contact}"> <select class="form-control"
											name="status">
											<option value="">账户状态</option>
											<option value="0"
												<c:if test="${status==0}">selected="selected"</c:if>>已禁用</option>
											<option value="1"
												<c:if test="${status==1}">selected="selected"</c:if>>已启用</option>
										</select>
										<button type="submit" class="btn btn-primary ">&nbsp;&nbsp;搜索&nbsp;&nbsp;</button>
									</div>
									<div class="fr">
										<a href="javascript:" id="gasstationadd"
											class="btn  btn-primary">+新增</a>
									</div>
								</form>
							</div>
							<div class="ibox-content pt10">
								<table class="table table-bordered text-center">
									<thead>
										<tr>
											<th>站点名称</th>
											<th>地区</th>
											<th>详细地址</th>
											<th>经纬度</th>
											<th>覆盖半径</th>
											<th>联系人</th>
											<th>联系方式</th>
											<th>加油总量(L)</th>
											<th>可提取量(L)</th>
											<th>状态</th>
											<th>操作</th>
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
												<td>${s.siteName}</td>
												<td>${s.province}${s.city}${s.county}</td>
												<td>${s.detaileAddress}</td>
												<td><span>经度:${s.longitude}</span><br> <span>纬度:${s.latitude}</span>
												</td>
												<td>${s.coverRadius}km</td>
												<td>${s.contact}</td>
												<td>${s.mobilePhone}</td>
												<td>${s.zong}</td>
												<td>${s.quota}</td>
												<td><c:choose>
														<c:when test="${s.status==0}">禁用</c:when>
														<c:when test="${s.status==1}">启用</c:when>
													</c:choose></td>
												<td><a class="text-primary" href="javascript:"
													gassid="${s.id}" onClick="updategass(this);">编辑</a> <a
													class="text-primary" gassid="${s.id}"
													index="${pages.currentPageNo}" href="javascript:"
													onClick="del(this);">删除</a> <a class="text-primary"
													href="javascript:" gassid="${s.id}"
													onClick="selectgass(this);">查看</a> <a class="text-primary"
													gassimg="${s.qrCodeImg}" onClick="showMa(this);">二维码</a> <a
													class="text-primary" status="${s.status}" gassid="${s.id}"
													index="${pages.currentPageNo}" href="javascript:"
													onClick="fob(this);"><c:choose>
															<c:when test="${s.status==0}">启用</c:when>
															<c:when test="${s.status==1}">禁用</c:when>
														</c:choose></a> <a class="text-primary" onClick="commit(this);"
													gassid="${s.id}" href="javascript:">提取</a>
													<a class="text-primary"
													onClick="note(this);" gassid="${s.id}" href="javascript:">记录</a></td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr class="gray-bg">
											<td colspan="9">
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
											<td class="text-right" colspan="3">共${pages.totalCount
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
		src="${pageContext.request.contextPath}/statics/js/demos/Dealer.js"></script>
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