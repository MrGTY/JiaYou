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

<title>审批页面</title>
<meta name="keywords" content="审批页面">
<meta name="description" content="审批页面">
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
</script>
<!-- <script type="text/javascript">		$('a.pageable').click(function() {
			var name = $(this).attr('name');
			var current = parseInt('${pageNo}');
			if (name == 'firstpage') {
				$('input:hidden[name=\'pageNo\']').val(1);
			} else if (name == 'nextpage') {
				$('input:hidden[name=\'pageNo\']').val(current + 1);
			} else if (name =='prevpage') {
				$('input:hidden[name=\'pageNo\']').val(current - 1);
			} else if (name == 'lastpage') {
				$('input:hidden[name=\'pageNo\']').val('${totalPageNo}');
			}else {
				return;
			}
			$('#formvip').submit();
		});
	});
</script>
-->
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
	<!--录入凭证弹窗通过-->
	<form action="updateApply" method="get">
	<input type="hidden" value="通过" name='type'/>
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
							style="text-align: center;">录入凭证</h4>
					</div>
					<div class="modal-body"
						style="border:1px #e4e4e4 solid; margin-bottom: 10px; background-color: #e4e4e4;border-radius: 3px">
						<div class="form-group">
							<span style="color: #008ee0" id="code"></span><span
								style="color: #008ee0" id="businessType"></span> <input
								type="hidden" id="cid" />
						</div>
						<div class="form-group" style="margin-top: -20px;">
							<div class="form-group col-sm-12" style="margin-top: 10px;">
								<label class="col-md-2 col-sm-1 control-label sp_num">凭证编号:</label>
								<div class="col-sm-7">
									<textarea class="col-md-10col-sm-12 col-xs-12 " rows="1"  id="yes"
										onfocus="if(this.value=='') {this.value='';}this.style.color='#666';"
										onblur="if(this.value=='') {this.value='';this.style.color='#333';}" ></textarea>
										
								</div>

							</div>
							<div class="form-group col-sm-10">
								<div class=" col-sm-8" style="text-align: center">
									<input style="width: 30%" class="btn btn-md btn-primary"
										id="btn-primary" onclick="saveMoney2(this);" value="审批通过" />
									<input style="width: 30%;color: black;"
										class="btn btn-md btn-default" onclick="overMoney2(this);"
										value="取消" />
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</form>
	<!--录入凭证弹窗拒绝-->
	<form action="updateApply" method="get">
	<input type="hidden" value="拒绝" name='type2'/>
		<div class="modal fade" id="myModal1aa" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header" style="margin-bottom: -10px;">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel1a"
							style="text-align: center;">录入凭证</h4>
					</div>
					<div class="modal-body"
						style="border:1px #e4e4e4 solid; margin-bottom: 10px; background-color: #e4e4e4;border-radius: 3px">
						<div class="form-group">
							<span style="color: #008ee0" id="nocode"></span><span
								style="color: #008ee0" id="nobusinessType"></span> <input
								type="hidden" id="noid" />
						</div>
						<div class="form-group" style="margin-top: -20px;">
							<div class="form-group col-sm-12" style="margin-top: 10px;">
								<label class="col-md-2 col-sm-1 control-label sp_num">拒绝理由:</label>
								<div class="col-sm-7">
									<textarea class="col-md-10col-sm-12 col-xs-12" rows="1"
										onfocus="if(this.value=='') {this.value='';}this.style.color='#666';" id="no"
										onblur="if(this.value=='') {this.value='';this.style.color='#333';}"></textarea>
								</div>
							</div>
							<div class="form-group col-sm-10">
								<div class=" col-sm-8" style="text-align: center">
									<input style="width: 30%" class="btn btn-md btn-primary"
										id="btn-primary" onclick="saveMoney22(this);" value="审批拒绝" />
									<input style="width: 30%;color: black;"
										class="btn btn-md btn-default" onclick="overMoney22(this);"
										value="取消" />
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</form>
	<%@include file="common/header.jsp"%>
			<div class="row wrapper border-bottom gray-bg page-heading">
				<div class="col-lg-12">
					<ol class="breadcrumb gray-bg">
						<li><a href="${pageContext.request.contextPath}/backend/sumOil">首页</a>
						</li>
						<li><label>审批页面</label>
						</li>
					</ol>
				</div>
			</div>
			<div class="wrapper wrapper-content animated">
				<div class="row white-bg">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title p0">
								<form action="${pageContext.request.contextPath}/selectAllApply"
									method="post">
									<input type="hidden" name="pageNo" value="${pageNo}" />
									<div class="row" style="margin-left: 20px;">
										<div class="col-md-2 ">
											<div class="form-group">
												<input type="text" class="form-control" name="siteName"
													value="${siteName}" placeholder="站点名称">
											</div>
										</div>
										<div class="col-md-2">
											<select class="form-control" name="businessType">
												<option value="">业务类型</option>
												<option value=1
													<c:if test="${businessType==1}">selected</c:if>>轮胎申请</option>
												<option value=0
													<c:if test="${businessType==0}">selected</c:if>>油量申请</option>
											</select>
										</div>
										<div class="col-md-2">
											<select class="form-control" name="status">
												<option value="">审批状态</option>
												<option value="0" <c:if test="${status==0}">selected</c:if>>审核中</option>
												<option value="1" <c:if test="${status==1}">selected</c:if>>通过</option>
												<option value="2" <c:if test="${status==2}">selected</c:if>>拒绝</option>
											</select>
										</div>

										<div class="col-md-2">
											<input type="submit" class="btn btn-primary"
												value="&nbsp;&nbsp;&nbsp;搜&nbsp;索&nbsp;&nbsp;&nbsp;" />
										</div>

									</div>
								</form>
							</div>
							<div class="ibox-content pt10">
								<table class="table table-bordered text-center">
									<thead>
										<tr>
											<th>站点</th>
											<th>业务类型</th>
											<th>申请提取额</th>
											<th>剩余额</th>
											<th>申请人</th>
											<th>联系电话</th>
											<th>申请时间</th>
											<th>状态</th>
											<th>审批人</th>
											<th>凭证编号/拒绝理由</th>
											<th>审批时间</th>
											<th>备注</th>
											<th>操作</th>
										</tr>
									</thead>
									<c:if test="${totalPage==0}">
               						<tr>
               							<td colspan="13">暂无数据!</td>
               						</tr>
               					</c:if>   
               					 <c:if test="${totalPage>0}">
									<tbody>
										<c:forEach items="${selectAllApply}" var="ap">
											<tr>
												<td>${ap.gasstation.siteName}</td>
												<td>
												<c:choose>
													<c:when test="${ap.businessType==0}">提取油量</c:when>
													<c:when test="${ap.businessType==1}">提取金额</c:when>
												</c:choose>
												</td>
												<td>
												<c:if test="${ap.businessType==1}">￥</c:if>
												${ap.amountDrawn}
												<c:if test="${ap.businessType==0}">吨/${ap.amountDrawn*ptt}L</c:if>
												</td>
												<td>
												<c:if test="${ap.businessType==1}">￥</c:if>
												${ap.surplusBalance}
												<c:if test="${ap.businessType==0}">L</c:if>
												<%-- <c:choose>
														<c:when test="${ap.businessType==0}">L</c:when>
														<c:when test="${ap.businessType==1}">￥</c:when>
												</c:choose> --%>
												</td>
												<td>${ap.gasstation.code}</td>
												<td>${ap.telePhone}</td>
												<td><fmt:formatDate value="${ap.applicationTime}"
														pattern="yyyy-MM-dd" /></td>
												<td>
												<c:choose>
														<c:when test="${ap.status==0}">审核中</c:when>
														<c:when test="${ap.status==1}">通过</c:when>
														<c:when test="${ap.status==2}">拒绝</c:when>
												</c:choose>
												</td>
												<td>${ap.emplId}</td>
												<td>${ap.serialNum}</td>
												<td>
												<fmt:formatDate value="${ap.approveData}" pattern="yyyy-MM-dd" />
												</td>
												<td>
												<c:choose>
														<c:when test="${ap.note==1}">加油申请</c:when>
														<c:when test="${ap.note==2}">轮胎申情</c:when>
														<c:when test="${ap.note==3}">平台代理</c:when>
												</c:choose>
												</td>
												<td>
													<c:choose>
														<c:when test="${ap.status==0}"><a class="text-primary" onClick="ok(${ap.id});">通过</a>
													<a class="text-primary" onClick="no(${ap.id});">拒绝</a></c:when>
														<c:when test="${ap.status==1}">审批已通过</c:when>
														<c:when test="${ap.status==2}">审批已拒绝</c:when>
													</c:choose>
												</td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr class="gray-bg">
											<td colspan="5"><c:choose>
													<c:when test="${totalPageNo==1}">
														<td>首页</td>
														<td>上一页</td>
														<td>下一页</td>
														<td>尾页</td>
													</c:when>
													<c:when test="${pageNo==1}">
														<td>首页</td>
														<td>上一页</td>
														<td><a href="javascript:page_nav2(document.forms[2],${pageNo+1});">下一页</a>
														</td>
														<td><a href="javascript:page_nav2(document.forms[2],${totalPageNo});">尾页</a>
														</td>
													</c:when>
													<c:when test="${pageNo==totalPageNo}">
														<td><a href="javascript:page_nav2(document.forms[2],1);">首页</a>
														</td>
														<td><a href="javascript:page_nav2(document.forms[2],${pageNo-1});">上一页</a>
														</td>
														<td>下一页</td>
														<td>尾页</td>
													</c:when>
													<c:otherwise>
														<td><a href="javascript:page_nav2(document.forms[2],1);">首页</a>
														</td>
														<td><a href="javascript:page_nav2(document.forms[2],${pageNo-1});">上一页</a>
														</td>
														<td><a href="javascript:page_nav2(document.forms[2],${pageNo+1});">下一页</a>
														</td>
														<td><a href="javascript:page_nav2(document.forms[2],${totalPageNo});">尾页</a>
														</td>
													</c:otherwise>
												</c:choose></td>
											<td class="text-right" colspan="5"></td>
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/js/pagenav.js"></script>
	<script
		src="${pageContext.request.contextPath}/statics/js/demos/apply.js"></script>
	<!-- iCheck -->
	<script
		src="${pageContext.request.contextPath}/statics/js/plugins/iCheck/icheck.min.js"></script>
	<!--时间控件-->
	<script
		src="${pageContext.request.contextPath}/statics/js/plugins/laydate/laydate.js"></script>
	<script
		src="${pageContext.request.contextPath}/statics/js/demos/time.js"></script>

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
							$.fn.toggle = function(fn,fn2) {
								var args = arguments, guid = fn.guid
										|| $.guid++, i = 0,toggle = function(
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
