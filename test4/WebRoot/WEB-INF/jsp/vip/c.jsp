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
    
    <title>My JSP 'c.jsp' starting page</title>
    
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
    <div class="col-lg-12" id="jilu">
        <div class="ibox float-e-margins" id="showORhidden">
            <div class="ibox-title p0" style="margin-left: 15px;">
                <form class="form-inline" action="${pageContext.request.contextPath}/vipuserinfo/Gathering" method="post" id="formvip">
                   	  <input type="hidden" name="id" value="${vipUser.id}">
   					  <input type='hidden' value='1' name='pageNo'/>
                   	<div class="form-group">
                        <input type="text" class="form-control" name="vipName" value="${user.vipName}"  placeholder="会员">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="startTime" value="" id="test1" readonly placeholder="开始时间">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="endTime" id="test2" readonly placeholder="结束时间">
                    </div>
                    <div class="form-group">
                        <input  type="submit" value="查询" class="btn btn-primary">
                    </div>
                </form>
            </div>
            <!--轮胎收支明细-->
            <div class="ibox-content pt10">
                <table class="table table-bordered text-center">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>商户名称</th>
                        <th>收款金额(元)</th>
                        <th>收款人</th>
                        <th>剩余欠款</th>
                        <th>收款时间</th>
                    </tr>
                    </thead>
                   <c:if test="${totalPage==0}">
               			<tr>
               				<td colspan="6">暂无数据!</td>
               			</tr>
               		</c:if>  
                    <c:if test="${totalPage>0}">
                    <tbody>
                    <c:forEach items="${lisGatherings}" var="list" varStatus="lis">
                    <tr>
                        <td>${lis.index+1}</td>
                        <td>${list.vipuserinfo.vipName} </td>
                        <td>${list.payamount}</td>
                        <td>${list.employeInfo.loginName}</td>
                        <td>${list.ljqk}</td>
                        <c:set var="litime" value="${list.createTime}"></c:set>
                        <td>${fn:substring(litime,0,19)}</td>
                    </tr>
                   	</c:forEach>
                    </tbody>
                    <tfoot>
                    <tr class="gray-bg">
                   <td colspan="4">
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
                        <td class="text-right" colspan=5>共&nbsp;${totalPage}&nbsp;条/&nbsp;${totalPageNo}&nbsp;页&nbsp;&nbsp;每页显示&nbsp;${pageSize}&nbsp;条&nbsp;&nbsp;第&nbsp;${pageNo}&nbsp;页</td>
                    </tr>
                    </tfoot>
                    </c:if> 
                </table>
             	</div> 
            </div>
       </div>              
</div>
<!--时间控件-->
<script src="${pageContext.request.contextPath}/statics/js/plugins/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/demos/time.js"></script>
  </body>
</html>
