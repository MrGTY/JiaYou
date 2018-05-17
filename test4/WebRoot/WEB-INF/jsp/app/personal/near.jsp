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

<title>附近</title>
<meta name="keywords" content="召集购油平台-加油">
<meta name="description" content="召集购油平台-加油">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/csscont.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/seed.css"/>
<script src="${pageContext.request.contextPath}/statics/app/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
 $(".map-nav a").click(function(){
 $(this).addClass("mn").siblings().removeClass("mn"); //切换选中的按钮高亮状态
 var index=$(this).index(); //获取被按下按钮的索引值，需要注意index是从0开始的
 $(this).parents(".map-main").find(".m-li").eq(index).show().siblings().hide(); //在按钮选中时在下面显示相应的内容，同时隐藏不需要的框架内容
 });
});
</script>

<script type="text/javascript">
	$(function(){
		var path="${cxt}";
		$.ajax({
				type:"GET",
				url:path+"/app/gassoil",
				data:{
					type: 0,
					pageNo:pageNo
				},
				datatype:"json",
				success: function(data){
				var result=JSON.parse(data);
				if(result.msg==1){
					$.each(result.list,function(i,item){
					var html="<li class='clearfix'><div class='left'><h3>"+result.list[i].siteName +"</h3><span>覆盖半径："+result.list[i].coverRadius+" 公里</span>"+
                              "</div><div class='right'><a href='tel:"+result.list[i].mobilePhone+"' class='''><i class='tel'></i>电话</a><a href='gotoapps://osaoyisao?uid=123&otherone=xxx' class='''><i class='jiayou'></i>加油</a></div></li>";
					
					$(".gassoil").append(html);
				});
						}
					},
				});
				
			$.ajax({
				type:"GET",
				url:path+"/app/gasstyre",
				data:{
					type: 1,
					pageNo:pageNo
				},
				datatype:"json",
				success: function(data){
				var result=JSON.parse(data);
				if(result.msg==1){
					$.each(result.list,function(i,item){
					var html="<li class='clearfix'><div class='left'><h3>"+result.list[i].siteName +"</h3><span>覆盖半径："+result.list[i].coverRadius+" 公里</span>"+
                              "</div><div class='right'><a href='tel:"+result.list[i].mobilePhone+"' class=''><i class='tel'></i>电话</a><a href='gotoapps://gsaoyisao?uid=123&otherone=xxx' class=''><i class='jiayou'></i>加油</a></div></li>";
					
					$(".gasstyre").append(html);
				});
						}
					},
				});	
				
				
				
				
				
					
		});
	
	
	
	
	
		
</script>


</head>

<body>
    <header class="w100">
         <div class="map"><img src="${pageContext.request.contextPath}/statics/app/images/map.png"/></div>
    </header>
    
   <section class="w100">
      <div class="map-main p94">
            <div class="map-nav clearfix">
                <a href="javascript:void(0)" class="mn">加油站点</a>
                <a href="javascript:void(0)">轮胎站点</a>
            </div>
            <div class="tab_map">
                 <div class="m-li">
                      <ul class="gassoil">
                          
                      </ul>
                 </div>
                 <div class="m-li">
                      <ul class="gasstyre">
                          
                      </ul>
                 </div>
            </div>
      </div>
   </section>
<%@include file="../common2/foot.jsp"%>
</body>
</html>
