$(function(){
	jiazaishuju();
				
	})
	//页面加载数据
	function jiazaishuju(){
	$.ajax({
		url:path+'/findMyFriend',
		type:'get',
		data:'',
		dataType:'json',
		success:function(data){
			var li="";
			if(data.msg==1){
				$.each(data.list1,function(i,item){
					if(item.usrName==null || item.usrName.length==0){
						
					}
				li+="<li class='clearfix last_div_li'>"+
	                "<div class='left clearfix '>"+
	                     "<div class='left'><i>"+item.userName+"</i></div>"+
	                     "<div class='right' >"+
	                         "<h3 id='phoneNum'>"+item.phoneNum+"</h3>"+
	                         "<div class='foncolor1'><span>备注："+item.userName+"</span></div>"+
	                         "<div class='foncolor1'><span>车牌号："+item.busNum+"</span></div>"+
	                     "</div>"+
	               "</div>"+
	                "<div class='right fr'>"+
	                    "<a href='fenpei?id="+item.id+"' class='a-pf'><i class='a-pff'></i>分配</a><a href='javascript:void(0);' onclick='del("+item.id+");' class='a-sc'><i class='a-scc'></i>删除</a>"+
	                "</div>"+
	            "</li>";
	             
				})
			}else if(data.msg==-1){
				li+="<li class='clearfix last_div_li'>暂无数据</li>";
			}else if(data.msg==400){
				li+="<li class='clearfix last_div_li'>请求超时</li>";
			}
			$("#ul").append(li);
		}
	})
	}
	//手机输入框校验手机格式	
    layui.use('layer', function (){
    	//给图片加绑定点击事件	
    	$("#img").on("click",function(){
    		var phoneNum=$("#search-input").val();
    		if(!(/^1[123456789]\d{9}$/.test(phoneNum))){ 
            	layer.tips('手机号码有误，请重填', '#search-input', {
                    tips: [1, '#0FA6D8'], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
                });
            }else{
    		$.ajax({
    			url:path+'/isExistFriends',
    			type:'get',
    			data:{phoneNum:phoneNum},
    			dataType:'json',
    			success:function(data){
    			if(data.msg==1){
    			if(data.relation==1){
    			$('#ul').html("");	
    			var searchli='';
    			searchli+="<li class='clearfix last_div_li'>"+
                    "<div class='left clearfix'>"+
                         "<div class='left'><i>"+data.vip.userName+"</i></div>"+
                         "<div class='right'>"+
                             "<h3>"+data.vip.phoneNum+"</h3>"+
                             "<div class='foncolor1'><span>备注："+data.vip.userName+"</span></div>"+
                             "<div class='foncolor1'><span>车牌号："+data.vip.busNum+"</span></div>"+
                         "</div>"+
                   "</div>"+
                    "<div class='right fr'>"+
                      "<a href='fenpei?id="+data.vip.id+"' class='a-pf'><i class='a-pff'></i>分配</a><a href='javascript:void(0);'onclick='del("+data.vip.id+");' class='a-sc'><i class='a-scc'></i>删除</a>"+
                    "</div>"+
                "</li>"
               $("#ul").append(searchli);  
    			}else if(data.relation==-1){
    				$('#ul').html("");	
        			var searchli='';
        			searchli+="<li class='clearfix last_div_li'>"+
                        "<div class='left clearfix'>"+
                             "<div class='left'><i>"+data.vip.userName+"</i></div>"+
                             "<div class='right'>"+
                                 "<h3>"+data.vip.phoneNum+"</h3>"+
                                 "<div class='foncolor1'><span>备注："+data.vip.userName+"</span></div>"+
                                 "<div class='foncolor1'><span>车牌号："+data.vip.busNum+"</span></div>"+
                             "</div>"+
                       "</div>"+
                        "<div class='right fr'>"+
                          "<a href='fenpei?id="+data.vip.id+"' class='a-pf'><i class='a-pff'></i>分配</a>"+
                        "</div>"+
                    "</li>"
                   $("#ul").append(searchli);  
    			}
    			}else if(data.msg==-1){
    				layer.tips('查无此人！', '#search-input', {
                        tips: [1, '#0FA6D8'], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
                    });
    			}else if(data.msg==-2){
    				layer.tips('您输入的是自己的手机号！', '#search-input', {
                        tips: [1, '#0FA6D8'], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
                    });
    			}
    			else if(data.msg==400){
    				layer.tips('程序异常', '#search-input', {
                        tips: [1, '#0FA6D8'], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
                    });
    			}
    		}
    			})}
    		})
    	
        
        
          
        }) 
       
	
	
	


function del(id){
    	var content="";	
 $.ajax({
	  url:path+'/vipInfo',
	  type:'get',
	  data:{friendId:id},
	  dataType:'json',
	  success:function(data){
		  if(data.msg==1){
		  content='确定删除好友'+data.userName+'('+data.phoneNum+')'+'吗？';
		  layer.open({
	            content: content,
	            btn: ['确定', '取消'],
	            yes: function(index){
	            	$.ajax({
	        			url:path+'/breakMyFriend',
	        			type:'get',
	        			data:{friendId:id},
	        			dataType:'json',
	        			success:function(data){
	        				if(data.msg==1){
	        					$("#ul").empty();
	        					jiazaishuju();
	        				}
	        			}
	        		})
	                layer.close(index)
	            }
	        });
		  }else{
			  layer.open({
		            content: "程序异常！",
		            btn: [ '取消'],
		            yes: function(index){
		            
		                layer.close(index)
		            }
		        }); 
		  }
	  }
  })
    	
	

}


