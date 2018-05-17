$(function(){

	 mycar(1);
	
})
var count=0;
function mycar(b){
	
	$.ajax({
		url:path+"/carList",
		type:"get",
		data:{pageNo:b},
		dataType:"json",
		success:function(data){
			var li="";
			if(data.msg==1){
				if(data.list1!=undefined){
					$.each(data.list1,function(i,item){
						 li+="<li class='p94 last_li bgcolor-b clearfix' onclick='tiaozhuan("+item.id+");'>"+
		                 "<div class='left abc_left'><span><i></i>车辆"+(10*b+i-9)+"</span><span>"+item.busNum+"("+item.userName+")</span>"+
		                 "<span>手机号码："+item.phoneNum+"</span></div>"+
		                 "<div class='right abc_right'><span class='right '>余量</span><font>"+item.oilMass+"L</font></div>"+
		                 "</li>";
					})
					$("#ul").append(li);
				}else if(data.list2!=undefined){
					$.each(data.list2,function(i,item){
						 li+="<li class='p94 bgcolor-b last_li clearfix' onclick='tiaozhuan("+item.id+");'>"+
		                 "<div class='left abc_left'><span><i></i>车辆"+(10*b+i-9)+"</span><span>"+item.busNum+"("+item.userName+")</span>" +
		                 		"<span>手机号码："+item.phoneNum+"</span></div>"+
		                 "<div class='right abc_right'><span>余量</span><font>"+item.oilMass+"L</font></div>"+
		                 "</li>";
					})
					$("#ul").append(li);
				}
			}else if(data.msg==-1){
				if(count==0){
					li+="<li class='p94 bgcolor-b clearfix last_li'>暂无车辆</li>";
					 $("#ul").append(li);
				}
				count++;
			}
		}
	})
}

function tiaozhuan(a){
	window.location.href="xiaofeijilu?a="+a;
}
Zepto(function($) {
	$(window).scroll(function() {
		if ( ($(window).scrollTop() + $(window).height() >= $(document).height()) ) {
			setTimeout(function() {
				var a = $("#pageNo").val(parseInt($("#pageNo").val()) + 1);
				var b = $("#pageNo").val();
				mycar(b);
			}, 10);
		}
	});
})
