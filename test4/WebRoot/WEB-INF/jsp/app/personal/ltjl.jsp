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

<title>召集购油平台-轮胎记录</title>
<meta name="keywords" content="召集购油平台-轮胎记录">
<meta name="description" content="召集购油平台-轮胎记录">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/csscont.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/app/css/seed.css"/>
<style >
	.last_li{
		margin-bottom: 7rem;
	}
</style>

<script src="${pageContext.request.contextPath}/statics/app/js/jquery-1.8.3.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/statics/app/js/dropload.min.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
 $(".tab_m ul li").click(function(){
 $(this).addClass("on").siblings().removeClass("on"); //切换选中的按钮高亮状态
 var index=$(this).index(); //获取被按下按钮的索引值，需要注意index是从0开始的
 $(this).parents(".tab").find(".cr-cont").eq(index).show().siblings().hide(); //在按钮选中时在下面显示相应的内容，同时隐藏不需要的框架内容
 });
});
</script >
<script type="text/javascript">
$(function(){

	var id=$("#id").val(); 
	var path="${cxt}";
	var itemIndex = 0;
    var tab1LoadEnd = false;
    var tab2LoadEnd = false;
    var choose =".oillist";
    var pageNo=0;
	if($("#ltt").val()==1){
		$(".cr-cont").eq(1).show();
		$("#choose li").eq(1).addClass("on"); 
		$(".cr-cont").eq(0).hide();
		$("#choose li").eq(0).removeClass("on");
		itemIndex = 1;
		var choose =".tyrelist";
	}

    // tab
    $("#choose .item").on("click",function(){
    	pageNo=0;
        var $this = $(this);
        itemIndex = $this.index();
        // 如果选中菜单一
        if(itemIndex == '0'){
            // 如果数据没有加载完
            if(!tab1LoadEnd){
                // 解锁
                dropload.unlock();
                dropload.noData(false);
            }else{
                // 锁定
                dropload.lock('down');
                dropload.noData();
            }
        // 如果选中菜单二
        }else if(itemIndex == '1'){
            if(!tab2LoadEnd){
                // 解锁
                dropload.unlock();
                dropload.noData(false);
            }else{
                // 锁定
                dropload.lock('down');
                dropload.noData();
            }
        }
        // 重置
        dropload.resetload();
    });
    // dropload
   
    var dropload = $(choose).dropload({
        scrollArea : window,
        loadDownFn : function(me){
        	pageNo++;
            // 加载菜单一的数据
            if(itemIndex == '0'){
            	$.ajax({
            		type:"GET",
            		url:path+"/app/oil",
            		data:{
            			id:id,
            			pageNo:pageNo
            		},
            		datatype:"json",
            		success: function(data){
            		var result=JSON.parse(data);
					var html="";
            			if(result.msg==1){
            				$.each(result.list,function(i,item){
            				if(result.list[i].friendId!=null && result.list[i].friendId!=""){
            					if(result.list[i].income!=0){
            					html+="<li class='p94 bgcolor-f last_li'><div>收入油量</div><div><span>朋友"+result.list[i].friendId+"分配</span>"+
            					"</div><div class='xfnum'><span>赠送: +"+result.list[i].income+"L"+"</span><span> 余量："+result.list[i].remaining +"L"+"</span></div><time>"+result.list[i].operationTime+"</time> </li>";
            					
            					}else {
            					html+="<li class='p94 bgcolor-f'><div>支出油量</div><div><span>赠送给"+result.list[i].friendId+"油量</span>"+
            					"</div><div class='xfnum'><span>赠送: -"+result.list[i].expenditure+"L"+"</span><span> 余量："+result.list[i].remaining +"L"+"</span></div><time>"+result.list[i].operationTime+"</time> </li>";
            					
            					}
            				}
            			if(result.list[i].gasstation!=undefined){
            				if(result.list[i].income!=0){
            				html+="<li class='p94 bgcolor-f last_li'><div>收入油量</div><div><span>"+result.list[i].gasstation.siteName+"</span>"+
            					"</div><div class='xfnum'><span>充值: +"+result.list[i].income+"L"+"</span><span> 余量："+result.list[i].remaining +"L"+"</span></div><time>"+result.list[i].operationTime+"</time> </li>";
            					
            				}else{
            				html+="<li class='p94 bgcolor-f last_li'><div>支出油量</div><div><span>"+result.list[i].gasstation.siteName+"</span>"+
            					"</div><div class='xfnum'><span>加油: -"+result.list[i].expenditure+"L"+"</span><span> 余量："+result.list[i].remaining +"L"+"</span></div><time>"+result.list[i].operationTime+"</time> </li>";
            	
            					}
            				}
            			if(result.list[i].friendId==undefined&&result.list[i].gasstation==undefined){
            				html+="<li class='p94 bgcolor-f last_li'><div>收入油量</div><div><span>召集平台充值</span>"+
            				"</div><div class='xfnum'><span>充值: +"+result.list[i].income+"L"+"</span><span> 余量："+result.list[i].remaining +"L"+"</span></div><time>"+result.list[i].operationTime+"</time> </li>";
            					
            				}
            				});
            				/*$("#xfjl-ul").append(li);*/
            				setTimeout(function(){
            					$(".oillist").append(html);
                                // 每次数据加载完，必须重置
                                me.resetload();
                            },1000);
            			}else if(result.msg=="-1" && pageNo==1){
            					html+="<li class='p94 bgcolor-f last_li' style='text-align:center'>暂无数据！</li>";
            					$(".oillist").append(html);
            					// 锁定
                                dropload.lock('down');
                                dropload.noData();
                                me.resetload();
                                // 数据加载完
                                tab1LoadEnd = true;
            			}else if(result.msg=="-1" && pageNo>1){
            					// 锁定
                                dropload.lock('down');
                                dropload.noData();
                                me.resetload();
                                // 数据加载完
                                tab1LoadEnd = true;
            			}else if(result.msg=="400"){
            					html+="<li class='p94 bgcolor-f last_li' style='text-align:center'>请求超时，请稍后重试</li>";
            					$(".oillist").append(html);
            					
            				// 锁定
                            dropload.lock('down');
                            dropload.noData();
                            me.resetload();
            			}
                    },
                    error: function(xhr, type){
                        // 锁定
                        dropload.lock('down');
                        dropload.noData();
                        // 即使加载出错，也得重置
                        me.resetload();
                        return;
                    }
                });
            // 加载菜单二的数据
            }else if(itemIndex == '1'){
            	$.ajax({
            		type:"GET",
            		url:path+"/app/tyre",
            		data:{
            			id:id,
            			pageNo:pageNo
            		},
            		datatype:"json",
            		success: function(data){
            		var result=JSON.parse(data);
            		var html="";
            			if(result.msg=="1"){
            				$.each(result.list,function(i,item){
            					if(result.list[i].gasstation!=undefined){
            					html+="<li class='p94 bgcolor-f last_li'><div> "+result.list[i].gasstation.siteName +"</div><div class='xfnum'><span>品牌: "+result.list[i].typrSpec +"</span><span>规格:"+result.list[i].typrMaterial+"</span></div>"+
                               	"<div class='xfnum'><span>数量:"+result.list[i].quantity+" 条</span><span>金额:"+result.list[i].expenditure+"</span></div><time>"+result.list[i].operationData+"</time> </li>";
            					
            					}
            				});
            				
            				setTimeout(function(){
            					$(".tyrelist").append(html);
                                // 每次数据加载完，必须重置
                                me.resetload();
                            },1000);
            			}else if(result.msg=="-1" && pageNo==1){
            					html+="<li class='p94 bgcolor-f last_li' style='text-align:center'>暂无数据！</li>";
            					$(".tyrelist").append(html);
            					// 锁定
                                dropload.lock('down');
                                dropload.noData();
                                me.resetload();
                                // 数据加载完
                                tab2LoadEnd = true;
            			}else if(result.msg=="-1" && pageNo>1){
        					// 锁定
                            dropload.lock('down');
                            dropload.noData();
                            me.resetload();
                            // 数据加载完
        				  	tab2LoadEnd = true;
        			}else if(result.msg=="400"){
        				html+="<li class='p94 bgcolor-f last_li' style='text-align:center'>请求超时，请稍后重试</li>";
        				$(".tyrelist").append(html);
        				
        				// 锁定
                        dropload.lock('down');
                        dropload.noData();
                        me.resetload();
        			}
                    },
                    error: function(xhr, type){
                        // 锁定
                        dropload.lock('down');
                        dropload.noData();
                        // 即使加载出错，也得重置
                        me.resetload();
                        return;
                    }
                });
              }
           }
       });
});
</script>
</head>

<body class="bgcolor">
<div class="goback_class">
    	<a href="javascript:history.back();"><img src="${pageContext.request.contextPath}/statics/app/images/goback.png" height="32" width="32"/>返回</a>
	</div>

  <section class="w100">
       <div class="tab">
            <div class="tab_m clearfix">
                 <ul id="choose">
                     <li class="on item">购油记录</li>
                     <li class="item">轮胎咨询记录</li>
                 </ul>
            </div>
            <div class="tab_b">
                 <div class="cr-cont">
                 <input type="hidden" value="${VipuserinfoSession.id}" id="id"/>
                 <input type="hidden" value="${ltt}" id="ltt"/>
                        <ul class="xfjl-ul oillist">    
                      	</ul> 
                 </div>
                 <div class="cr-cont">
                      <ul class="xfjl-ul tyrelist">
                          
                      </ul> 
                 </div>
             </div> 
       </div>
  </section>
<%@include file="../common2/foot.jsp"%>
</body>
</html>
