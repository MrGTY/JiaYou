<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'a.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/statics/js/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/plugins/alert/sweet-alert.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/alert/sweet-alert.css">
    <style>
        body{
            background-color: white;
        }
    </style>
    <script type='text/javascript'>
	$(function(){
		$('a.pageable').click(function() {
			var name = $(this).attr('name');
			var current = parseInt('${pageNo}');
			if (name == 'firstpage') {
				$('#formvip :hidden[name=\'pageNo\']').val(1);
			} else if (name == 'nextpage') {
				$('#formvip :hidden[name=\'pageNo\']').val(current + 1);
			} else if (name =='prevpage') {
				$('#formvip :hidden[name=\'pageNo\']').val(current - 1);
			} else if (name == 'lastpage') {
				$('#formvip :hidden[name=\'pageNo\']').val('${totalPageNo}');
			}else {
				return;
			}
			$('#formvip').submit();
		});
	});	
	</script>
  </head>
  <body>
   <div class="row white-bg">
    <div class="ibox-title p0" style="margin-left: 15px;">
        <form class="form-inline" action="${pageContext.request.contextPath}/vipuserinfo/selectUserandTyre" method="post" id="formvip">
            <input type="hidden" name="id" value="${vipUser.id}">
   			<input type='hidden' value='1' name='pageNo'/>
            <select class="form-control" name="paymentType">
                <option value="">收支类型</option>
                <option value=0 <c:if test="${Tyre.paymentType==0}"> selected</c:if>>收入</option>
                <option value=1 <c:if test="${Tyre.paymentType==1}"> selected</c:if>>支出</option>
            </select>
            <div class="form-group">
                <input type="text" class="form-control" name="startTime" value="${Tyre.startTime}" id="test1" readonly placeholder="开始时间">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="endTime" id="test2" value="${Tyre.endTime}" readonly placeholder="结束时间">
            </div>
            <div class="form-group">
                <input  type="submit" value="查询" class="btn btn-primary">
            </div>
        </form>
    </div>
    <!--燃油收支明细-->
    <div class="ibox-content pt10">
        <table class="table table-bordered text-center">
            <thead>
            <tr>
                  		      
                        <th>收/支时间</th>
                        <th>操作事件</th>
                        <th>站点名</th>
                        <th>收入(元)</th>
                        <th>支出(元)</th>
                        <th>剩余金额(元)</th>
                <!--<th>操作</th>-->
            </tr>
            </thead>
            <c:if test="${totalPage==0}">
               			<tr>
               				<td colspan="6">暂无数据!</td>
               			</tr>
            </c:if>  
            
            <c:if test="${totalPage>0}">
            <tbody>
           <c:forEach items="${TyredatailsList}" var="Tyredatails">
    		<tr>
    			 <c:set var="litime" value="${Tyredatails.operationData}"></c:set>
    			<th>${fn:substring(litime,0,19)}</th>
    			<th><c:if test="${Tyredatails.operationType==0}">好友赠送</c:if>
    				<c:if test="${Tyredatails.operationType==1}">平台充值</c:if>
    			    <c:if test="${Tyredatails.operationType==2}">站点购买</c:if></th>
    			<th>${Tyredatails.gasstation.siteName}</th>
    			<th>${Tyredatails.income}</th>
    			<th>${Tyredatails.expenditure}</th>
    			<th>${Tyredatails.balance}</th>
    		</tr>
    		</c:forEach>
            </tbody>
            <tfoot>
            <tr class="gray-bg">
                <td colspan="3">
                   <c:choose>   
                   <c:when test="${totalPageNo==1}">
					<a href="javaScript:void(0)" style="color:gray" class='pageable'>首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a class='pageable'  href="javaScript:void(0)" style="color:gray">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javaScript:void(0)" style="color:gray" class='pageable'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javaScript:void(0)" style="color:gray" class='pageable'  >尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:when>
    				<c:when test="${pageNo==1}">
    				<a href="javaScript:void(0)" style="color:gray" class='pageable' >首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    				<a class='pageable'  href="javaScript:void(0)" style="color:gray">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    				<a name='nextpage' class='pageable'  >下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    				<a name='lastpage' class='pageable'  >尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:when>
					<c:when test="${pageNo==totalPageNo}">
						<a name='firstpage' class='pageable'  >首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a name='prevpage' class='pageable'  >上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javaScript:void(0)" style="color:gray" class='pageable'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javaScript:void(0)" style="color:gray" class='pageable'  >尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:when>
						<c:otherwise>
						<a name='firstpage' class='pageable'  >首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a name='prevpage' class='pageable'  >上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a name='nextpage' class='pageable'  >下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 <a name='lastpage' class='pageable'  >尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:otherwise>
    				</c:choose>
                </td>
                <td class="text-right" colspan=2>共&nbsp;${totalPage}&nbsp;条/&nbsp;${totalPageNo}&nbsp;页&nbsp;&nbsp;每页显示&nbsp;${pageSize}&nbsp;条&nbsp;&nbsp;第&nbsp;${pageNo}&nbsp;页</td>
                <td colspan="1">&nbsp;</td>
            </tr>
            </tfoot>
             </c:if> 
        </table>
    </div>
</div>
<!--时间控件-->
<script src="${pageContext.request.contextPath}/statics/js/plugins/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/demos/time.js"></script>
  </body>
</html>
