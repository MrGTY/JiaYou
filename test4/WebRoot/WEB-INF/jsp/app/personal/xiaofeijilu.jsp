<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cxt" value="${pageContext.request.contextPath}"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<title>商户轮胎消费记录</title>
<meta name="keywords" content="召集购油平台-商户消费记录">
<meta name="description" content="召集购油平台-商户消费记录">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/csscont.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/seed.css"/>
<script src="${pageContext.request.contextPath}/statics/app/js/jquery-1.11.0.min.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function(){
		var path="${cxt}";
		var id=$("#id").val();
		$.ajax({
			type:"GET",
			url:path+"/app/shtyre",
			data:{
				id:id
			},
			datatype:"json",
			success:function(data){
			var result=JSON.parse(data);
			if(result.msg=="1"){
			$.each(result.list,function(i,item){
				var html="<li class='p94 bgcolor-f'><div><span>用户:"+result.list[i].vipuserinfo.vipName+"</span></div><div class='xfnum'><span>品牌: "+result.list[i].typrSpec +"</span><span>规格:"+result.list[i].typrMaterial+"</span></div>"+
                   "<div class='xfnum'><span>数量:"+result.list[i].quantity+" 条</span><span>金额:"+result.list[i].income+"</span></div><time>"+result.list[i].operationData+"</time> </li>";
				$(".shtyre").append(html);
			});
			}else if(result.msg=="-1"){
				var html="<li class='p94 bgcolor-f' style='text-align:center'>暂无数据</li>";
				$(".shtyre").append(html);
			}else if(result.msg=="400"){
				var html="<li class='p94 bgcolor-f' style='text-align:center'>请求超时，请稍后重试</li>";
				$(".shtyre").append(html);
				}
			}

		});
	});
</script>
</head>

<body class="bgcolor">
<div class="goback_class">
    <a href="${pageContext.request.contextPath}/app/taishou"><img src="${pageContext.request.contextPath}/statics/app/images/goback.png" height="32" width="32"/>返回</a>
</div>
	<header>
         <div class="mycar-header p94">
              <h1>商户记录</h1>
         </div>
    </header>
    
    <section>
    <input type="hidden" id="id" value="${gasstion.id}"/>
          <ul class="xfjl-ul shtyre" >
          </ul>
    </section>
</body>
</html>
