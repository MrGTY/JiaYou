<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="ctx1" value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>会员管理</title>
<meta name="keywords" content="轮胎站点">
<meta name="description" content="轮胎站点">

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
   		 var path ="${ctx}";
   		 var path1="${ctx1}";
    $(function(){
    	var mas="${massage}";
		if(mas!=""){
		setTimeout(function () {
			swal(mas);
		 }, 50);	
			}
			});
    </script>
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
<!--省市三级联动-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/js/jsAddress.js"></script>
<!--   <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/ljwrite.css">-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/statics/css/demos/iconfont/iconfont.css">
<script
	src="${pageContext.request.contextPath}/statics/js/demos/iconfont.js"></script>
<!--省市三级联动-->

</head>
<body>
	<!--充值-->





	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header" style="margin-bottom: -10px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel"
						style="text-align: center;">在线充值</h4>
				</div>



				<!--第一个充值-->
				<c:forEach items="${viplist}" var="list">
					<div class="modal-body" name="clone_${list.id}"
						style="border:1px #e4e4e4 solid;height: 340px; margin-bottom: 10px; background-color: #e4e4e4;border-radius: 3px">
						<div class="form-group">
							<span style="color: #008ee0" class="vipname">您正在为【${list.userName}】充值！</span>
							<input type="hidden" name="czid" value="${list.id}" />
						</div>
						<div class="form-group " style="margin-top: -20px;">
							<span class="col-lg-2">充值类型:</span> <span class=" col-sm-5">充值方式:</span>
						</div>
						<div class="form-group" style="margin-top: -20px;">
							<div class="col-lg-5 itit_radio">
								<label class="col-lg-5 radio-inline" ><input
									type="radio" value="0" name="options_${list.id}">油量充值</label> <label
									class="radio-inline "><input type="radio" value="1"
									name="options_${list.id}">轮胎储蓄充值</label>
							</div>
							<div class="form-group col-lg-7 itit_radio">
								<span class="col-lg-3 col-sm-5">&nbsp;</span> <label
									class="radio-inline"><input type="radio" value="0"
									name="optionsRad_${list.id}">现款充值</label> <label
									class="radio-inline"><input type="radio" value="1"
									name="optionsRad_${list.id}">预充值</label>
							</div>
							<div class="col-lg-10 form-group" style="margin-top: -20px;">
								<span class="col-lg-3">充值量:</span>
							</div>
							<div class="form-group col-sm-5" style="margin-top: -20px;">
								<span class="col-lg-3">金额:</span>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="money_${list.id}"
										placeholder="元">
								</div>
							</div>
							<div class="form-group col-sm-7 " id="youliang_${list.id}"
								style="margin-top: -20px;">
								<span class="col-lg-2">油量:</span>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="oil_${list.id}"
										placeholder="升">
								</div>
								<!--    <span class="col-lg-4">(1吨=1000元)</span>-->
							</div>
							<div class="form-group col-sm-12">
								<label class="col-md-1 col-sm-1 control-label">备注:</label>
								<div class="col-sm-7">
									<textarea class="col-md-10col-sm-12 col-xs-12 " rows="2"
										id="remark_${list.id}"
										onfocus="if(this.value=='') {this.value='';}this.style.color='#666';"
										onblur="if(this.value=='') {this.value='';this.style.color='#333';}"></textarea>
								</div>
							</div>
							<div class="form-group col-sm-10">
								<div class=" col-sm-6" style="text-align: center">
									<button style="width: 30%" class="btn btn-md btn-primary"
										onclick="saveMoney(this,${list.id});">保存</button>
									<button style="width: 30%;color: black;"
										class="btn btn-md btn-default"
										onclick="overMoney(this,${list.id});">取消</button>
									<div class='massage' style='color:red'></div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>


			</div>
		</div>
	</div>

	<!--收款-->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header" style="margin-bottom: -10px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1"
						style="text-align: center;">收款确认</h4>
				</div>
				<div class="modal-body"
					style="border:1px #e4e4e4 solid;height: 340px; margin-bottom: 10px; background-color: #e4e4e4;border-radius: 3px">
					<div class="form-group">
						<input type="hidden" value="" name="id" id="vipid" /> <span
							style="color: #008ee0" id="userName"></span><span
							style="color: red;" id="qkBalance"></span>
					</div>
					<div class="form-group " style="margin-top: -20px;">
						<span class="col-lg-5">收款类型:</span>

					</div>
					<div class="form-group" style="margin-top: -20px;">
						<div class="col-lg-7 itit_radio">
							<label class="col-lg-5 radio-inline" for="optionsRadios1a"><input
								type="radio" value="0" id="optionsRadios1a" name="oiloptions">油量充值收款</label>
							<label class="radio-inline "><input type="radio"
								value="1" id="optionsRadio1a" name="oiloptions">轮胎储蓄充值收款</label>
						</div>

						<div class="col-lg-10 form-group"
							style="margin-top: -10px; margin-bottom: 20px;">
							<span class="col-lg-3">收款金额:</span>
						</div>
						<div class="form-group col-sm-5" style="margin-top: -20px;">
							<span class="col-lg-3">金额:</span>
							<div class="col-sm-9">
								<input type="text" name="txt_departmentname"
									class="form-control" placeholder="不得大于欠款金额">
							</div>
						</div>
						<div class="form-group col-sm-12">
							<label class="col-md-1 col-sm-1 control-label">备注:</label>
							<div class="col-sm-7">
								<textarea class="col-md-10col-sm-12 col-xs-12" rows="2"
									id="skremark"
									onfocus="if(this.value=='') {this.value='';}this.style.color='#666';"
									onblur="if(this.value=='') {this.value='';this.style.color='#333';}"></textarea>
							</div>
						</div>
						<div class="form-group col-sm-10">
							<div class=" col-sm-6" style="text-align: center">
								<button style="width: 30%" class="btn btn-md btn-primary"
									onclick="saveMoney1(this);">保存</button>
								<button style="width: 30%;color: black;"
									class="btn btn-md btn-default" onclick="overMoney1(this);">取消
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--设置会员类型//分类-->
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
						style="text-align: center;">设置会员类型</h4>
				</div>
				<div class="modal-body"
					style="border:1px #e4e4e4 solid; margin-bottom: 10px; background-color: #e4e4e4;border-radius: 3px">
					<div class="form-group">
						<!-- 显示 -->
						<span style="color:#008ee0" id="uName"></span><span
							style="color:#008ee0" id="busNum"></span> <input type="hidden"
							name="fid" id="vid" />
					</div>
					<div class="form-group " style="margin-top: -20px;">
						<span class="col-lg-4">设置会员分类:</span>
					</div>
					<div class="form-group" style="margin-top: -20px;">
						<div class="col-lg-5 itit_radio">
							<label class="col-lg-5 radio-inline"><input type="radio"
								value="1" id="optionsRadios1" name="userType">物流公司</label>
								 <label class="radio-inline "><input type="radio" value="0"
								id="optionsRadio2" name="userType">车主/个人</label>
						</div>
						<div class="col-lg-7 " style="position: relative;">
							<form class="form-inline" id="fenform">
							  	<div class="col-lg-8">
							  		<input type="hidden" value="" name="exampid" id="exampid"/>
							    	<input type="text" class="form-control" name="vname" value="" id="examp" placeholder="请输入物流公司名称">
							    </div>
							  <button type="button" onclick="sososo(this);" class="btn btn-primary  btn-sm">搜索</button>
							</form>
							<div id="showdiv" style="border:1px solid #A9A9A9;max-height:150px;width:100%;overflow:scroll;position: absolute;z-index: 1;display: none;">
							   		<!-- <div onclick="choose(this);" class="vipdiv" style="background-color: #DCDCDC;border-bottom: 1px solid;cursor: pointer;">
							   			<input type="hidden" value="df"/>
							   		</div> -->
							  </div>
						</div>
						
</form>
						<div class="form-group col-sm-12" style="margin-top: 10px;">
							<label class="col-md-1 col-sm-1 control-label">备注:</label>
							<div class="col-sm-7">
								<textarea class="col-md-10col-sm-12 col-xs-12" rows="2"
									id="textarea"
									onfocus="if(this.value=='') {this.value='';}this.style.color='#666';"
									onblur="if(this.value=='') {this.value='';this.style.color='#333';}"></textarea>
							</div>
						</div>
						<div class="form-group col-sm-10">
							<div class=" col-sm-6" style="text-align: center">
								<button style="width: 30%" class="btn btn-md btn-primary"
									onclick="saveMoney2(this);">保存</button>
								<button style="width: 30%;color: black;"
									class="btn btn-md btn-default" onclick="overMoney2(this);">取消
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--修改密码-->
	<div class="modal fade" id="Modal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header" style="margin-bottom: -10px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" style="text-align: center;">修改密码</h4>
				</div>
				<div class="modal-body"
					style="border:1px #e4e4e4 solid;height: 200px; margin-bottom: 10px; background-color: #e4e4e4;border-radius: 3px">
					<div class="row" style="margin-top: -20px;">
						<form>
							<div class="col-lg-8 col-md-9  p20">
								<div class="form-group">
									<label class="col-sm-3 control-label"><span
										class="text-danger">*</span>新密码:</label>
									<div class="col-sm-8">
										<input type="text" name="" id="pwd1" class="form-control"
											placeholder="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label"><span
										class="text-danger">*</span>确认密码:</label>
									<div class="col-sm-8">
										<input type="text" name="" id="pwd2" class="form-control"
											placeholder="">
									</div>
								</div>
								<div class="form-group col-sm-10">
									<div class=" col-sm-8" style="text-align: center">
										<button type="button" style="width: 30%"
											class="btn btn-md btn-primary" onclick="saveok12();">保存
										</button>
										<button type="button" style="width: 30%;color: black;"
											class="btn btn-md btn-default" onclick="quxiao(this);">取消
										</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
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
							<img  width="700" height="370" id="change">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../common/header.jsp"%>
	<div class="row wrapper border-bottom gray-bg page-heading">
		<div class="col-lg-12">
			<ol class="breadcrumb gray-bg">
				<li><a href="${pageContext.request.contextPath}/backend/sumOil">首页</a>
				</li>
				<li><a href="javascript:void(0)">站点管理</a></li>
				<li><label>会员列表</label></li>
			</ol>
		</div>
	</div>
	<div class="wrapper wrapper-content animated">
		<div class="row white-bg">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<form
						action="${pageContext.request.contextPath}/vipuserinfo/vipindex"
						method="post" id='formvip'>
						<input type='hidden' value='1' name='pageNo' />
						<input type="hidden" name='mycarId' value="${user.mycarId}">
						<div class="row col-md-12">
							<div class="col-md-2">
								<input type="text" name="vipName" value="${user.vipName}"
									class="form-control" placeholder="会员名称">
							</div>
							<div class="col-md-2">
								<input type="text" name="userName" value="${user.userName}"
									class="form-control" placeholder="联系人">
							</div>
							<div class="col-md-2">
								<input type="text" name="busNum" value="${user.busNum}"
									class="form-control" placeholder="车牌号">
							</div>
							<div class="col-md-2">
								<select name="userType" class="form-control userType">
									<option value="">会员类型</option>
									<option value=1
										<c:if test="${user.userType==1}"> selected</c:if>>物流公司</option>
									<option value=0
										<c:if test="${user.userType==0}"> selected</c:if>>车主/个人</option>
								</select>
							</div>
							<div class="col-md-2">
								<input type="submit" class="btn btn-primary submit" value="搜索">&nbsp;&nbsp;
								&nbsp;&nbsp;
							</div>
							<!-- <button type="button" class="btn btn-primary">新增</button> -->
						</div>
					</form>
					<div class="ibox-content pt10">
						<form method="post"
							action="${pageContext.request.contextPath}/vipuserinfo/userChange"
							id='formvip'>
							<input type='hidden' value='${pageNo}' name='pageNo1' id='pageNo' />
							<input type="hidden" value='${user.vipName}' name='vipName' /> <input
								type="hidden" value='${user.userName}' name='userName' /> <input
								type="hidden" value='${user.busNum}' name='busNum' /> <input
								type="hidden" value='${user.userType}' name='userType' />
							<table class="table table-bordered text-center">
								<thead>
									<tr>
										<th>复选框</th>
										<th>会员名称</th>
										<th>姓名</th>
										<th>手机号码</th>
										<th>车牌号</th>
										<th>注册时间</th>
										<th>会员类型</th>
										<th>所属物流公司</th>
										<th>剩余油量</th>
										<th>轮胎余额</th>
										<th>欠款金额</th>
										<th>行驶证</th>
										<th>状态</th>
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
									<c:forEach items="${viplist}" var="list">
										<tr<c:if test="${list.stateTag==1}"> style='background-color:rgba(204,204,204,0.6)'</c:if>  >
											<td><input type="checkbox" name="id" id='box_${list.id}'
												value='${list.id}'></td>
											<td>${list.vipName}</td>
											<td>${list.userName}</td>
											<td>${list.phoneNum}</td>
											<td>${list.busNum}</td>
											<td>${list.createTime}</td>
											<td  <c:if test="${list.userType==1}">style="color: #c6f;cursor: pointer;font-weight:600"  onClick=mycar("${list.vipName}")</c:if>>
											<c:if test="${list.userType==0}">车主/个人</c:if> 
											<c:if test="${list.userType==1}">物流公司</c:if></td>
											<td style="color: #930;font-weight:600"><c:if test="${list.mycarId!=null}">${list.mycarId}</c:if></td>
											<td>${list.oilMass}</td>
											<td>${list.tyreBalance}</td>
											<td>${list.qkBalance}</td>
											<td><c:if test="${list.xszimg==''}">未上传</c:if> <c:if
													test="${list.xszimg!=''}">
													<a style="color: #008ee0" xszimg="${list.uploadImg}"
														onclick="upload(this);">已上传</a>
												</c:if></td>
											<td  <c:if test="${list.stateTag==1}"> style='color:red'</c:if>  >
											<c:if test="${list.stateTag==0}">已启用</c:if> <c:if
													test="${list.stateTag==1}">已禁用</c:if></td>
											<td><a class="text-primary" onClick="del(${list.id});"
												 vipName="${list.vipName}">删除</a>
													<a class="text-primary change" changeid="${list.id}">更改 </a>
												 <a
												class="text-primary" onClick="moreMoney(${list.id});"
												 vipName="${list.vipName}">充值</a> <a
												class="text-primary" onClick="giveMoney(${list.id});">收款</a>
												<a class="text-primary" onClick="changeType(${list.id});">分类</a>
												<a class="text-primary"
												href="${pageContext.request.contextPath}/vipuserinfo/selectrecord?id=${list.id}">记录</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr class="gray-bg">
										<td class="text-left" colspan="5"><input type="submit"
											name="change"
											style="border: 0px;background-color:rgba(204, 204,204,0)"
											class="text-primary" value="禁用账户" /> <svg class="icon"
												aria-hidden="true"> <use
												xlink:href="#icon-daibanshixiang"></use> </svg>&nbsp; <input
											type="submit" name="change" class="text-primary"
											style="border: 0px;background-color:rgba(204, 204,204,0)"
											value="启用账户"> <svg class="icon" aria-hidden="true">
											<use xlink:href="#icon-daibanshixiang"></use> </svg>&nbsp; <a
											class="text-primary" onClick="moreMoney();">批量充值</a> <svg
												class="icon" aria-hidden="true"> <use
												xlink:href="#icon-daibanshixiang"></use> </svg>&nbsp; <a
											class="text-primary" onClick="upPwd(this);">修改密码</a> <svg
												class="icon" aria-hidden="true"> <use
												xlink:href="#icon-daibanshixiang"></use> </svg></td>
										<td colspan="6"><c:choose>
												<c:when test="${totalPageNo==1}">
													<a href="javaScript:void(0)" style="color:gray"
														class='pageable'>首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    			<a class='pageable' href="javaScript:void(0)"
														style="color:gray">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    			<a href="javaScript:void(0)" style="color:gray"
														class='pageable'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    			<a href="javaScript:void(0)" style="color:gray"
														class='pageable'>尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			</c:when>
												<c:when test="${pageNo==1}">
													<a href="javaScript:void(0)" style="color:gray"
														class='pageable'>首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
						    				<a href="javaScript:void(0)" style="color:gray"
														class='pageable'>尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			</c:when>
												<c:otherwise>
													<a name='firstpage' class='pageable'>首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			<a name='prevpage' class='pageable'>上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			<a name='nextpage' class='pageable'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			<a name='lastpage' class='pageable'>尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						    			</c:otherwise>
											</c:choose></td>
										<td class="text-right" colspan="4">共&nbsp;${totalPage}&nbsp;条/&nbsp;${totalPageNo}&nbsp;页&nbsp;&nbsp;每页显示&nbsp;${pageSize}&nbsp;条&nbsp;&nbsp;第&nbsp;${pageNo}&nbsp;页
										</td>
									</tr>
								</tfoot>
								</c:if>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../common/foot.jsp"%>
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
		src="${pageContext.request.contextPath}/statics/js/demos/vip.js"></script>
	<script>
   $(document).ready(function () {
        //checkbox美化样式框
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
        //toggle兼容高版本jquery
        $.fn.toggle = function (fn, fn2) {
            var args = arguments, guid = fn.guid || $.guid++, i = 0,
                toggle = function (event) {
                    var lastToggle = ($._data(this, "lastToggle" + fn.guid) || 0) % i;
                    $._data(this, "lastToggle" + fn.guid, lastToggle + 1);
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
        $("#btn-upper").toggle(function () {
            $("#ser_main").slideDown(500);
        }, function () {
            $("#ser_main").slideUp(500);
        });
        //合同日期起止日期
        $('#data_5 .input-daterange').datepicker({
            keyboardNavigation: false,
            forceParse: false,
            autoclose: true
        });
    });
</script>
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
</body>

</html>
