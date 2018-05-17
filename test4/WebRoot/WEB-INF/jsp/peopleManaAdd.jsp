<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <c:set value="${pageContext.request.contextPath}" var="cxt"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>新增员工</title>
    <meta name="keywords" content="新增员工">
    <meta name="description" content="新增员工">

    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet">
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
		var path="${cxt}";
	</script>
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
                        <a href="javascript:void(0)">站点管理</a>
                    </li>
                    <li>
                        <label>部门/员工</label>
                    </li>
                    <li style="color: #169bd5">
                        <label>新增员工</label>
                    </li>
                </ol>
            </div>
            <div class="oli_site">
                <a href="Dealer.html">新增员工</a>
            </div>
        </div>
        <div class="wrapper wrapper-content animated">
            <div class="white-bg">
                <div class="row ibox-content p20">
                    <form action="${pageContext.request.contextPath}/addemploye" method="post" class="form-horizontal">
                        <div class="col-lg-12 bordered">
                            <div class="col-lg-8 col-md-9  p20">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span
                                            class="text-danger">*</span>登录账号:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="loginName" required id="zdname" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>密码:</label>
                                    <div class="col-sm-8">
                                        <input type="password" name="password" class="form-control" required id="pwd" pattern="^\w{6,10}$" placeholder="请输入密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="text-danger">*</span>确认密码:</label>
                                    <div class="col-sm-8">
                                        <input type="password" name="password2" class="form-control" required id="repwd" pattern="^\w{6,10}$" placeholder="请确认密码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span
                                            class="text-danger">*</span>姓名:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="userName" required id="uname" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">部门:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="department" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">职位:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="position" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span
                                            class="text-danger">*</span>手机:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="mobilePhone" id="phone" required pattern="^1[123456789]\d{9}$" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">邮箱:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="email" class="form-control" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">QQ:</label>
                                    <div class="col-sm-8">
                                        <input type="text" name="QQ" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group col-sm-12">
                                    <div class=" col-sm-6" style="text-align: center">
                                        <input type="submit" value="保存" style="width: 30%" id="saves" class="btn btn-md btn-primary"/>
                                        <button type="button" name="button" style="width: 30%" class="btn btn-md btn-default" >取消</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="common/foot.jsp"%>
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
<script src="${pageContext.request.contextPath}/statics/js/demos/peopleManaAdd.js"></script>

<!-- iCheck -->
<script src="${pageContext.request.contextPath}/statics/js/plugins/iCheck/icheck.min.js"></script>

<script
		src="${pageContext.request.contextPath}/statics/js/plugins/alert/sweet-alert.js"></script>

	<script type="text/javascript">
		var path = "${cxt}";
		$(function() {
			$("#zdname").bind("blur", function() {
				// ajax后台验证--uname是否已存在
				$.ajax({
					type : "GET",// 请求类型
					url : "selectName",// 请求的url
					data : {
						loginName : $("#zdname").val()
					},// 请求参数
					dataType : "json",// ajax接口（请求url）返回的数据类型
					success : function(data) {// data：返回数据（json对象）
						if (data.mas == "-1") {// 账号不可用，错误提示
							swal("登录名已存在，不能使用！");
							$("#zdname").val("");
						}
					},
					error : function(data) {// 当访问时候，404，500 等非200的错误状态码
						swal("请求错误！");
					}
				});
			});
			$("#phone").bind("blur", function() {
				// ajax后台验证--phone是否已存在
				$.ajax({
					type : "GET",// 请求类型
					url : "selectPhone",// 请求的url
					data : {
						mobilePhone : $("#phone").val()
					},// 请求参数
					dataType : "json",// ajax接口（请求url）返回的数据类型
					success : function(data) {// data：返回数据（json对象）
						if (data.mas == "-1") {// 账号不可用，错误提示
							swal("手机号码已存在，不能使用！");
							$("#phone").val("");
						}
					},
					error : function(data) {// 当访问时候，404，500 等非200的错误状态码
						swal("请求错误！");
					}
				});
			});
		});
	</script>
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

</body>
</html>
