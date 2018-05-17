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

<title>消费记录</title>
<meta name="keywords" content="召集购油平台-消费记录">
<meta name="description" content="召集购油平台-消费记录">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/csscont.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/seed.css"/>
<script src="${pageContext.request.contextPath}/statics/app/js/jquery-1.11.0.min.js" type="text/javascript"></script>

<script type="text/javascript">


$(function(){
	var path="${cxt}";
	var gid=$("#gid").val();
	$.ajax({
		data:"GET",
		url:path+"/app/fee",
		data:{
			gid:gid
		},
		datatype:"json",
		success:function(data){
			var result=JSON.parse(data);
			if(result.msg==1){
			$.each(result.list,function(i,item){
				var html="<li><font>+"+result.list[i].income+"L/￥"+result.list[i].fee+"</font><time>"+result.list[i].operationTime+"</time>"+
				"<input type='hidden' id='id_"+ (i+1)+"'value=' "+result.list[i].id+"'/></li><br/>";
				$("#fee").append(html);
				});
			}else if(result.msg=="-1"){
				var html="<li><font>暂无数据</font></li>"
				$("#fee").append(html);
			}else if(result.msg=="400"){
				var html="<li><font>请求超时，请稍后重试!</font></li>"
				$("#fee").append(html);
			}
		}
	});
	$.ajax({
		data:"GET",
		url:path+"/app/feezong",
		data:{
			gid:gid
		},
		datatype:"json",
		success:function(data){
			var result=JSON.parse(data);
			if(result.msg==1){
				$("#feezong").html(result.list);
			}else if(result.msg=="-1"){
				$("#feezong").html("暂无数据");
			}else if(result.msg=="400"){
				$("#feezong").html("请求超时，请稍后重试");
			}
		}
	});
	
	
	$("input:button[name='button']").click(function(){
			var fee=$("#feezong").text();
			var gid=$("#gid").val();
			var id=[];
			$("input:hidden[id*='id']").each(function(index,item){
			var num=$('input:hidden[id=\'id_'+(index+1)+'\']').val();
				id.push(num);
			});
			id.pop();
			if(id.length>0){
			var idc=id.toString().replace(' ','');
				$.ajax({
					data:"GET",
					url:path+"/app/feeque",
					data:{
						idc:idc,
						gid:gid,
						fee:fee
					},
					datatype:"json",
					success:function(data){
						if(data.msg==1){
							alert("结算成功");
							/*url跳转到对应的站点首页  */
						}else if(data.msg=="2"){
							alert("目前该账户禁用中,无法结算");
						}else if(data.msg=="-1"){
							alert("结算失败，出现异常");
						}else if(data.msg=="400"){
							alert("请求超时，请稍后重试");
						}
					}
				});
				/* window.location.href=path+"/app/feeque?idc="+id+"&gid="+gid+"&fee="+fee; */
			}else{
				alert("无提交数据");
			}
		});
})
	
	
	
	
</script>

<style>
	ul li{
		width: 100%;
		height: 80px;
		line-height:80px;
		
		background: rgba(204,204,204,0.3);
	}
	li font{
		padding-left:25px;
		font-weight:600;
	}
	ul{font-size:18px}
	ul time{
		font-size:14px;
		padding-right:15px;
		float:right
	}
</style>
</head>

<body class="bgcolor">
 <div class="goback_class">
    	<a href="javascript:history.back();"><img src="${pageContext.request.contextPath}/statics/app/images/goback.png" height="32" width="32"/>返回</a>
	</div>
	<header >
         <div style="width: 100%;height: 20%; background:rgb(51,153,255)">
              <h1 id="feezong" style="font-size:45px;color:white; text-align: center;padding-top:15px "></h1>
              <h2 style="color:white; text-align: center;font-size:14px;padding-top:20px">代收取金额</h2>
         </div>
    </header>
    
    <section >
    <input type="hidden" id="gid" value="${gasstion.id }"/>
    <div style="height: 65%;">
          <ul id="fee">
 			
          </ul>
     </div>
     <div style="height: 15%;">
          <input type="button" name="button" value="确认" style="font-size:30px;color:white;width:100%;height: 80px;background: rgb(51,153,255);"/>
     </div>
    </section>
</body>
</html>
