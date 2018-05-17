<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="cxt" />
<c:set var ="ctx1" value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">

<title>新增会员</title>
<meta name="keywords" content="新增会员">
<meta name="description" content="新增会员">

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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/statics/css/alert/sweet-alert.css">

<style type="text/css">
.oli_site a:nth-of-type(1) {
	border: 1px white solid;
	background-color: #9F88FF;
	color: white;
}

.oli_site a:nth-of-type(2) {
	border: 1px white solid;
}
</style>

<script type="text/javascript">
	var path = "${cxt}";
	var path1="${ctx1}";
	var id="${id}";	
</script>
</head>

<body>
	<!--查看上传图片-->
	<div class="modal fade" id="Modal12" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width: 750px;">
				<div class="modal-header" style="margin-bottom: -10px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" style="text-align: center;">查看行驶证</h4>
				</div>
				<div class="modal-body"
					style="border:1px #e4e4e4 solid; margin-bottom: 10px; background-color: #e4e4e4;border-radius: 3px">
					<div class="row">
						<div class="col-md-12">
							<img src="" width="700" height="370" id="change">
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
				<li><a href="${pageContext.request.contextPath}/backend/sumOil">首页</a>
				</li>
				<li><a href="javascript:void(0)">会员管理</a></li>
				<c:if test="${id==null}">
					<li style="color: #169bd5"><label>新增会员</label></li>
				</c:if>
				<c:if test="${id!=null}">
					<li style="color: #169bd5"><label>修改会员</label></li>
				</c:if>
			</ol>
		</div>
		<div class="oli_site">
		
				<c:if test="${id==null}">
					<a href="javascript:void(0)">新增会员</a>
				</c:if>
				<c:if test="${id!=null}">
					<a href="javascript:void(0)">修改会员</a>					
				</c:if>
			
		</div>
	</div>
	<div class="wrapper wrapper-content animated">
		<div class="white-bg">
			<div class="row ibox-content p20">
				<form action="sign" method="post" class="form-horizontal"
					enctype="multipart/form-data">
                        <div class="col-lg-12 bordered">
                            <div class="col-lg-8 col-md-9  p20">
                                <div class="form-group">
                                	<input type="hidden" name="id" value="${id}">
                                    <label class="col-sm-2 control-label"><span
                                            class="text-danger"></span>真实姓名:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="userName" required id="uname" value="${vip.userName}" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger"></span>手机:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="phoneNum" class="form-control" value="${vip.phoneNum}" required pattern="^1[123456789]\d{9}$"
                                        	<c:if test="${vip.phoneNum!=null}"> readonly </c:if>
                                       		<c:if test="${vip.phoneNum==null}">id="phone"</c:if>                                                                                
                                         >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger"></span>密码:</label>
                                    <div class="col-sm-8">
                                        <input type="password" name="password" value="${vip.password}" class="form-control" required pattern="^\w{6,10}$" id="pwd">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">车牌:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="busNum" value="${vip.busNum}" class="form-control"  placeholder="车牌非必填">
                                    </div>
                                </div>
								<!-- <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>会员分类:</label>
                                    <div class="col-lg-5 itit_radio">
                                        <label class="col-lg-4 radio-inline"><input type="radio" checked value="0"
                                                                                    id="optionsRadios1"
                                                                                    name="userType">个人车主</label>
                                        <label class="radio-inline"><input type="radio" value="1"
                                                                           id="optionsRadios2"
                                                                           name="userType">物流公司</label>
                                	 </div>
                                </div>-->
                                <div class="form-group">                           
            						<label class="col-sm-2 control-label" for="name">行驶证： <span class="required"></span>
            						</label>
            						<div class="col-md-6 col-sm-6 col-xs-12">
            							<input type="file" class="form-control col-md-7 col-xs-12" name="logoPicPath"  value="${vip.xszimg}" required="required" id="logoPicPath"/>
            						<span style="color: red;">${fileUploadError }</span> 
            						</div>
            						<c:if test="${id!=null}">
            							
										<span xszimg="${vip.uploadImg} " onclick="upload(this);" style="color:red; cursor: pointer;">点击查看行驶证</span>
            						</c:if>            	
          						</div>
          						<c:if test="${id==null}">
          							<div class="form-group col-sm-12">
                                    <div class=" col-sm-6" style="text-align: center">
                                        <input type="submit"  name="submit" value="保存" style="width: 30%" id="saves" class="btn btn-md btn-primary"/>
                                        <a href="${pageContext.request.contextPath}/vipuserinfo/vipindex" style="width: 30%" class="btn btn-md btn-default" >取消</a>
                                    </div>
                                	</div>
          						</c:if>
                                <c:if test="${id!=null}">
                                	<div class="form-group col-sm-12">
                                    <div class=" col-sm-6" style="text-align: center">
                                        <input type="submit" name="submit" value="修改" style="width: 30%" id="changes" class="btn btn-md btn-primary"/>
                                        <a href="${pageContext.request.contextPath}/vipuserinfo/vipindex" style="width: 30%" class="btn btn-md btn-default" >取消</a>
                                    </div>
                                	</div>                        
                                </c:if>
                            </div>
                        </div>
                    </form>
                </div>
<%@include file="common/foot.jsp"%>
            </div>
        </div>
    </div>
</div>
<!-- Mainly scripts -->
<script src="${pageContext.request.contextPath}/statics/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.4.0"></script>
<script src="${pageContext.request.contextPath}/statics/js/public.js"></script>
<!-- JSKnob -->
<script src="${pageContext.request.contextPath}/statics/js/plugins/jsKnob/jquery.knob.js"></script>

<!-- Data picker -->
<script src="${pageContext.request.contextPath}/statics/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/demos/vipsign.js"></script>

<!-- iCheck -->
<script src="${pageContext.request.contextPath}/statics/js/plugins/iCheck/icheck.min.js"></script>
<script
		src="${pageContext.request.contextPath}/statics/js/demos/vip.js"></script>
<script
		src="${pageContext.request.contextPath}/statics/js/plugins/alert/sweet-alert.js"></script>
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
							var args = arguments, guid = fn.guid || $.guid++, i = 0, toggle = function(
									event) {
								var lastToggle = ($._data(this, "lastToggle"
										+ fn.guid) || 0)
										% i;
								$._data(this, "lastToggle" + fn.guid,
										lastToggle + 1);
								event.preventDefault();
								return args[lastToggle].apply(this, arguments) || false;
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
