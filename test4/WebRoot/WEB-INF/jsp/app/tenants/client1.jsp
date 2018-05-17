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

<title>召集联盟-商户端</title>
<meta name="keywords" content="召集购油平台-消费记录">
<meta name="description" content="召集购油平台-消费记录">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/csscont.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/seed.css"/>
<script src="${pageContext.request.contextPath}/statics/app/js/jquery-1.11.0.min.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	var path="${cxt}";
	var id=$("#gid").val();
			//显示商户信息
		$.ajax({
			type:"GET",
			url:path+"/app/ImgName",
			data:{
				gid:id
			},
			datatype:"json",
			success:function(data){
			var result=JSON.parse(data);
			if(result.msg=="1"){
				$("#code").html("账户:"+result.gasstation.code);
				$("#siteName").html(result.gasstation.siteName);
				var html="<img src='"+path+"/statics/qrcode/"+result.gasstation.qrCodeImg+"'>";
				$(".img").append(html);
				}
			}
		});
		
		//显示商户轮胎信息
		$.ajax({
			type:"GET",
			url:path+"/app/shtyre",
			data:{
				id:id
			},
			datatype:"json",
			success:function(data){
			var result=JSON.parse(data);
			console.log(result);
			if(result.msg=="1"){
			$.each(result.list,function(i,item){
				if(i<3){
				var html="<li class='p94 bgcolor-f'><div class='xfnum'><span>品牌: "+result.list[i].typrSpec +"</span><span>规格:"+result.list[i].typrMaterial+"</span></div>"+
                   "<div class='xfnum'><span>数量:"+result.list[i].quantity+" 条</span><span>金额:"+result.list[i].income+"</span></div><time>"+result.list[i].operationData+"</time> </li>";
					$(".shtyre").append(html);
					
			}else{
				return;
					}
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
	function pushGetOnlyPhone(){
		return $("#pushGetOnlyPhone").val();
	}
</script>


</head>

<body class="bgcolor">
	<header class="header-main"><!-- header_cla -->
	 <div class="sfsfsferwer">
	 <input type="hidden" name="" id="pushGetOnlyPhone" value="${gasstion.mobilePhone }" />
             <a id="code" href="${pageContext.request.contextPath}/app/shanghuwo"></a>
             <a href="gotoapps://xylt?uid=${gasstion.id }">轮胎数据维护</a>
    </div>
        <div class="client_div">商户端</div>
        <input type="hidden" value="${gasstion.id}" id="gid"/>
        <div class="img">           
        </div>
        <div class="master">扫一扫</div>
    </header>
    
    <section>
        <div class="addoil">
            <span>购买记录:</span><span><a href="${pageContext.request.contextPath}/app/luntaijilu">〓</a></span>
        </div>
          <ul class="xfjl-ul shtyre">
          </ul>
    </section>
    <nav class="nav_wrap">
        <div id="siteName">
            
        </div>
    </nav>
</body>
</html>
