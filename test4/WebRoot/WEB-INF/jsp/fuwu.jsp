<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<title>服务费结算</title>
<meta name="keywords" content="服务费结算">
<meta name="description" content="服务费结算">
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/demos/nice.css">

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
	<%@include file="common/siteheader.jsp"%>
	<div class="row wrapper border-bottom gray-bg page-heading">
            <div class="col-lg-12">
                <ol class="breadcrumb gray-bg">
                    <li>
                        <a href="${pageContext.request.contextPath}/backend/selectOil">首页</a>
                    </li>
                   <!--  <li>
                        <a href="javascript:void(0)">站点管理</a>
                    </li> -->
                    <li>
                        <label>已结算服务费</label>
                    </li>
                </ol>
            </div>
            <form class="form-inline" method="post" action="fuwu">
            	<input type="hidden" name="pageIndex" value="1" /> 
                <div class="form-group">
                    <label for="exampleInputName2">&nbsp;</label>
                    <input type="text" name="begintime" value="${begintime}" class="form-control" id="test1" readonly placeholder="开始时间">
                </div>
                <div class="form-group">
                    <label for="exampleInputName2">&nbsp;</label>
                    <input type="text" name="endtime" value="${endtime}" class="form-control" id="test2" readonly placeholder="结束时间">
                </div >
                <label for="exampleInputName2">&nbsp;</label>
                <button type="submit" class="btn btn-primary btn-primary">搜索</button>
                <div class="form-group daiti">
                    <div class="num">
                        <div class="oli">
                            <div>未结算额</div>
                            <div><span>￥<c:if test="${empty money}">0</c:if>${money}</span></div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
	<div class="wrapper wrapper-content animated">
		<div class="row white-bg">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content pt10">
                            <table class="table table-bordered text-center">
                                <thead>
                                <tr>
                                    <th>结算时间</th>
                                    <th>加油量</th>
                                    <th>服务费结算额</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <c:if test="${pages.totalCount==0}">
               						<tr>
               							<td colspan="5">暂无数据!</td>
               						</tr>
               					</c:if>   
               					 <c:if test="${pages.totalCount>0}">
                                <tbody>
                                <c:forEach var="s" items="${list}" varStatus="status">
                                	<tr>
                                   	 	<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${s.createtime}"/></td>
                                  	  	<td>${s.oilmass}L</td>
                                   		<td>￥${s.chargefee}</td>
                                   		<td>
                                   		<c:if test="${s.state==1}">
                                   			已结算
                                   		</c:if>
                                   		</td>
                                   		<td><a href="${pageContext.request.contextPath}/site/fuwuranyou?idc=${s.idc}">查看明细</a></td>
                                	</tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                	<tr class="gray-bg">
											<td colspan="2">
												<c:choose>
													<c:when test="${pages.totalPageCount==1}">
														<a href="javaScript:void(0)" style="color:gray">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        				<a href="javaScript:void(0)" style="color:gray">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        				<a href="javaScript:void(0)" style="color:gray">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                       		 			<a href="javaScript:void(0)" style="color:gray">尾页</a>
													</c:when>
                                    				<c:when test="${pages.currentPageNo==1}">
                                    					<a href="javaScript:void(0)" style="color:gray">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        				<a href="javaScript:void(0)" style="color:gray">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        				<a href="javascript:page_nav(document.forms[0],${pages.currentPageNo+1});">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        				<a href="javascript:page_nav(document.forms[0],${pages.totalPageCount});">尾页</a>
                                    				</c:when>
                                    				<c:when test="${pages.currentPageNo==pages.totalPageCount}">
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
		<%@include file="common/sitefoot.jsp"%>
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
	<script src="${pageContext.request.contextPath}/statics/js/download.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/js/clipboard.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/statics/js/rollpage.js"></script>
	<!--时间控件-->
	<script src="${pageContext.request.contextPath}/statics/js/plugins/laydate/laydate.js"></script>
	<script src="${pageContext.request.contextPath}/statics/js/demos/time.js"></script>
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