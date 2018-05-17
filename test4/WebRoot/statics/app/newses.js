$(function(){
    var itemIndex = 0;
    var tab1LoadEnd = false;
    var tab2LoadEnd = false;
    var tab3LoadEnd = false;
    var tab4LoadEnd = false;
    var pageNo=0;
    // tab
    $('#click .item').on('click',function(){
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
        }else if(itemIndex == '2'){
            if(!tab3LoadEnd){
                // 解锁
                dropload.unlock();
                dropload.noData(false);
            }else{
                // 锁定
                dropload.lock('down');
                dropload.noData();
            }
        }else if(itemIndex == '3'){
            if(!tab4LoadEnd){
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
    var dropload = $('#clk1').dropload({
        scrollArea : window,
        loadDownFn : function(me){
        	pageNo++;
            // 加载菜单一的数据
            if(itemIndex == '0'){
                $.ajax({
                    type: 'GET',
                    url: path+'/findMeg',
                    data:{msgType:-1,pageNo:pageNo},
                    dataType: 'json',
                    success: function(data){
                    	console.log(data);
                    	if (data.msg == 1) {
            				var li = "";
            				$.each(data.list1, function(i, item) {
            					if(item.msgType==1){
            						li+="<li class='p94 bgcolor-f last_li'>"+
            		                "<div><font class='foncolor1'>"+"分配提醒"+"</font></div>"+
            		                "<div>"+item.content+"</div>"+
            		                "<time>"+item.operationTime+"</time>"+
            		            "</li>"
            					}else if(item.msgType==2){
            						li+="<li class='p94 bgcolor-f last_li'>"+
            		                "<div><font class='foncolor1'>"+"消费提醒"+"</font></div>"+
            		                "<div>"+item.content+"</div>"+
            		                "<time>"+item.operationTime+"</time>"+
            		            "</li>"
            					}else if(item.msgType==3){
            						li+="<li class='p94 bgcolor-f last_li'>"+
            		                "<div><font class='foncolor1'>"+"平台消息"+"</font></div>"+
            		                "<div>"+item.content+"</div>"+
            		                "<time>"+item.operationTime+"</time>"+
            		            "</li>"
            					}
            				});
            				setTimeout(function(){
            					$(".clk1").append(li);
                                // 每次数据加载完，必须重置
                                me.resetload();
                            },1000);
            			}else if(data.msg==-1){
            					li+="<li class='p94 bgcolor-f last_li' style='margin-bottom:8rem;'>暂无数据</li>";
            					$("#clk1").before(li);
            					// 锁定
                                dropload.lock('down');
                                dropload.noData();
                                me.resetload();
                                // 数据加载完
                                tab1LoadEnd = true;
            			}else if(data.msg==400){
            					$("#xfjl-ul").append("<li class='p94 bgcolor-f last_li'>请求超时，请稍后重试</li>");
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
                    type: 'GET',
                    url:path+'/findMeg',
                    data:{msgType:1,pageNo:pageNo},
                    dataType: 'json',
                    success: function(data){
                    	console.log(data);
                    	if (data.msg == 1) {
            				var li = "";
            				$.each(data.list1, function(i, item) {
            					li+="<li class='p94 bgcolor-f last_li'>"+
         		                "<div><font class='foncolor1'>"+"分配提醒"+"</font></div>"+
         		                "<div>"+item.content+"</div>"+
         		                "<time>"+item.operationTime+"</time>"+
         		            "</li>"
            				});
            				setTimeout(function(){
            					$(".clk2").append(li);
                                // 每次数据加载完，必须重置
                                me.resetload();
                            },1000);
            			}else if(data.msg==-1){
        					li+="<li class='p94 bgcolor-f last_li'>暂无数据</li>";
        					$("#xfjl-ul").append(li);
        					// 锁定
                            dropload.lock('down');
                            dropload.noData();
                            me.resetload();
                         // 数据加载完
                            tab2LoadEnd = true;
        			}else if(data.msg==400){
        					$("#xfjl-ul").append("<li class='p94 bgcolor-f last_li'>请求超时，请稍后重试</li>");
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
            }else if(itemIndex == '2'){
            	$.ajax({
                    type: 'GET',
                    url:path+'/findMeg',
                    data:{msgType:2,pageNo:pageNo},
                    dataType: 'json',
                    success: function(data){
                    	console.log(data);
                    	if (data.msg == 1) {
            				var li = "";
            				$.each(data.list1, function(i, item) {
            					li+="<li class='p94 bgcolor-f last_li'>"+
         		                "<div><font class='foncolor1'>"+"消费提醒"+"</font></div>"+
         		                "<div>"+item.content+"</div>"+
         		                "<time>"+item.operationTime+"</time>"+
         		            "</li>"
            				});
            				setTimeout(function(){
            					$(".clk3").append(li);
                                // 每次数据加载完，必须重置
                                me.resetload();
                            },1000);
            			}else if(data.msg==-1){
        					li+="<li class='p94 bgcolor-f last_li'>暂无数据</li>";
        					$("#xfjl-ul").append(li);
        					// 锁定
                            dropload.lock('down');
                            dropload.noData();
                            me.resetload();
                         // 数据加载完
                            tab3LoadEnd = true;
        			}else if(data.msg==400){
        				
        					$("#xfjl-ul").append("<li class='p94 bgcolor-f last_li'>请求超时，请稍后重试</li>");
        				
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
            }else if(itemIndex == '3'){
               	$.ajax({
                    type: 'GET',
                    url:path+'/findMeg',
                    data:{msgType:3,pageNo:pageNo},
                    dataType: 'json',
                    success: function(data){
                    	
                    	if (data.msg == 1) {
            				var li = "";
            				$.each(data.list1, function(i, item) {
            					li+="<li class='p94 bgcolor-f last_li'>"+
         		                "<div><font class='foncolor1'>"+"平台消息"+"</font></div>"+
         		                "<div>"+item.content+"</div>"+
         		                "<time>"+item.operationTime+"</time>"+
         		            "</li>"
            				});
            				setTimeout(function(){
            					$(".clk4").append(li);
                                // 每次数据加载完，必须重置
                                me.resetload();
                            },1000);
            			}else if(data.msg==-1){
        					li+="<li class='p94 bgcolor-f last_li'>暂无数据</li>";
        					$("#xfjl-ul").append(li);
        					// 锁定
                            dropload.lock('down');
                            dropload.noData();
                            me.resetload();
                         // 数据加载完
                            tab4LoadEnd = true;
        			}else if(data.msg==400){
        					$("#xfjl-ul").append("<li class='p94 bgcolor-f last_li'>请求超时，请稍后重试</li>");
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