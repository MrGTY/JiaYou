<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<title>轮胎站点收支表</title>
<meta name="keywords" content="轮胎站点收支表">
<meta name="description" content="轮胎站点收支表">
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
                    <li>
                        <a href="${pageContext.request.contextPath}/backend/sumOil">首页</a>
                    </li>
                    <li>
                        <label>轮胎站点收支表</label>
                    </li>
                </ol>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div><h3>${gass.siteName}</h3></div>
                    <div>
                        <div class="col-md-4">联 系 人：${gass.contact}</div>
                        <div class="col-md-4">手机号码：${gass.mobilePhone}</div>
                        <div class="col-md-3">覆盖范围：${gass.coverRadius}</div>
                    </div>
                    <div>
                        <div class="col-md-8">地址：${gass.detaileAddress}</div>
                        <div class="col-md-3">状态：
                        				 <c:choose>
											<c:when test="${gass.status==0}">账户禁用中</c:when>
											<c:when test="${gass.status==1}">账户启动中</c:when>
										</c:choose></div>
                    </div>
                </div>
                <%--  <div style="float:left;width: 1px;height: 55px;margin-top: 3%; background: red;margin-right: 30px;"></div>
               <div class="col-md-2" style="margin-top: 20px;">
                    <div>可提取量(L)</div>
                    <div style="color: red;font-size: 30px;margin-top: -20px;">${gass.quota}</div>
                </div>
                <div class="col-md-2" style="margin-top: 20px;">
                    <div>站点累计提取量(L)</div>
                    <div style="color: red;font-size: 30px;margin-top: -20px;">${zhi}</div>
                </div> 
                <div class="col-md-1" style="margin-top: 20px;">
                    <div class="col-md-9">&nbsp;</div>
                    <div class="col-md-12 btn btn-primary" style="color:white;margin-top: -20px;">
                    	<a href="${pageContext.request.contextPath}/gasstation/commit?id=${gass.id}"  style="color:white;">申请提取<a/>
                    </div>
                </div>--%>
            </div>
        </div>
        <div class="wrapper wrapper-content animated">
            <div class="row white-bg" style="color:gray;">
                <div class="col-lg-12" id="jilu">
                    <div class="ibox float-e-margins" id="showORhidden">
                        <div class="ibox-title p0">
                            <form class="form-inline" method="post" action="tirenote">
                            	<input type="hidden" name="id" value="${gass.id}" /> 
                            	<input type="hidden" name="pageIndex" value="1" /> 
                                <select class="form-control" name="status">
                                    <option value="">收支类型</option>
                                    <option value="1"<c:if test="${status==1}">selected="selected"</c:if>>收入</option>
                                   	<!-- <option value="2"<c:if test="${status==2}">selected="selected"</c:if>>支出</option> -->
                                </select>
						        <div class="form-group">
                    				<label for="exampleInputName2">&nbsp;</label>
                    				<input type="text" name="begintime" value="${begintime}" class="form-control" id="test1" readonly placeholder="开始时间">
                				</div>
                				<div class="form-group">
                    				<label for="exampleInputName2">&nbsp;</label>
                    				<input type="text" name="endtime" value="${endtime}" class="form-control" id="test2" readonly placeholder="结束时间">
                				</div >
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">&nbsp;&nbsp;&nbsp;搜索&nbsp;&nbsp;&nbsp;</button>
                                </div>
                            </form>
                        </div>
                        <!--燃油收支明细-->
                        <div class="ibox-content pt10" >
                            <table class="table table-bordered text-center">
                                <thead>
                                <tr>
                                    <th>操作时间</th>
                                    <th>购胎会员账户</th>
                                    <th>购胎车主</th>
                                    <th>购胎车牌</th>
                                    <th>站点收入(￥)</th>
                                   <!--  <th>站点当前可提取量(L)</th> -->
                                </tr>
                                </thead>
                                <c:if test="${pages.totalCount==0}">
               						<tr>
               							<td colspan="7">暂无数据!</td>
               						</tr>
               					</c:if>   
               					 <c:if test="${pages.totalCount>0}">
                                <tbody>
                                <c:forEach var="s" items="${list}" varStatus="status">
                                	<tr>
                                		<c:set var="litime" value="${s.operationData}"></c:set>
                                    	<td>${fn:substring(litime, 0, 19)}</td>
                                    	<td>${s.vipuserinfo.phoneNum}</td>
                                    	<td>${s.vipuserinfo.userName}</td>
                                    	<td>${s.vipuserinfo.busNum}</td>
                                    	<td>${s.income}</td>
                                    	<%-- <td>${s.remaining}</td> --%>
                                	</tr>
                                 </c:forEach>
                                </tbody>
                                <tfoot>
                                	<tr class="gray-bg">
											<td colspan="4">
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