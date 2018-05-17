$(function(){
	fenpeitixing();
	
})
function fenpeitixing(){
	$("#xfjl-ul").empty();
	$.ajax({
		url:path+'/findMeg',
		type:'get',
		data:'',
		dataType:'json',
		success:function(data){
			var li='';
			
			$.each(data.list1,function(i,item){
				if(item.msgType==1){
					console.log(i);
					li+="<li class='p94 bgcolor-f last_li'>"+
	                "<div><font class='foncolor1'>"+"分配提醒"+"</font></div>"+
	                "<div>"+item.content+"</div>"+
	                "<time>"+item.operationTime+"</time>"+
	            "</li>"
				}
			})
			$("#xfjl-ul").append(li);
		}
	})
}


function xiaofei(){
	$("#xfjl-ul").empty();
	$.ajax({
		url:path+'/findMeg',
		type:'get',
		data:'',
		dataType:'json',
		success:function(data){
			var li='';			
			$.each(data.list1,function(i,item){
				if(item.msgType==2){
					li+="<li class='p94 bgcolor-f last_li'>"+
	                "<div><font class='foncolor1'>"+"消费提醒"+"</font></div>"+
	                "<div>"+item.content+"</div>"+
	                "<time>"+item.operationTime+"</time>"+
	            "</li>"
				}
			})
			$("#xfjl-ul").append(li);
		}
	})
}

function xitong(){
	$("#xfjl-ul").empty();
	$.ajax({
		url:path+'/findMeg',
		type:'get',
		data:'',
		dataType:'json',
		success:function(data){
			var li='';			
			$.each(data.list1,function(i,item){
				if(item.msgType==3){
					li+="<li class='p94 bgcolor-f last_li'>"+
	                "<div><font class='foncolor1'>"+"平台消息"+"</font></div>"+
	                "<div>"+item.content+"</div>"+
	                "<time>"+item.operationTime+"</time>"+
	            "</li>"
				}
			})
			$("#xfjl-ul").append(li);
		}
	})
}
