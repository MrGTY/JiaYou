var ab = "";
var count = 0;
$(function() {
	ltsjwh(1, "");
	 //确定
    $("#btn_right_qd").click(function () {
        $(".screen-nav").hide();
        count=0;
    	$("#item_ul").empty();
    	$(".screen-nav").hide();
    	var name=$(".btn_left_one span:nth-child(2)").text();
    	var specifications=$(".btn_left_two span:nth-child(2)").text();
    	var material=$(".btn_left_three span:nth-child(2)").text();
    	ltsjwh(1, name,specifications,material);
        
    });
});
//轮胎
function getname(obj) {
    $(".btn_left_one span:nth-child(2)").text($(obj).text());
}
//材质
function choosecz(obj) {
    $(".btn_left_two span:nth-child(2)").text($(obj).text());
}
//规格
function choosegg(obj) {
    $(".btn_left_three span:nth-child(2)").text($(obj).text());
}
//重置
$("#btn_right_sfmngg").click(function() {
	 $(".btn_left_one span:nth-child(2)").text("");
	 $(".btn_left_two span:nth-child(2)").text("");
	 $(".btn_left_three span:nth-child(2)").text("");
})


//根据轮胎品牌筛选
function xuanze() {
	$.ajax({
		url : 'brand',
		type : 'post',
		data : '',
		dataType : 'json',
		success : function(data) {
			$("#item_ul_li").empty();
			$("#item_ul_li_two").empty();
			$("#item_ul_li_three").empty();
			var html="";
			console.log(data);
			if (data.msg == 1) {
				if(data.tyreBrand!=null){//品牌
					$.each(data.tyreBrand,function(i,item){
						html+="<li><a  onclick='getname(this);'>"+item+"</a></li>";
					});
				}
				if(data.spec!=null){//轮胎规格
					var htmlTwo='';
					$.each(data.spec,function(i,item){
						htmlTwo+="<li><a  onclick='choosecz(this);'>"+item+"</a></li>";
					});
					$("#item_ul_li_three").append(htmlTwo);
				}
				//轮胎材料
				if(data.material!=null){
					var htmlThree='';
					$.each(data.material,function(i,item){
						htmlThree+="<li><a  onclick='choosegg(this);'>"+item+"</a></li>";
					});
					$("#item_ul_li_two").append(htmlThree);
				}
				
			} else if (data.msg == -1) {
				html = "<li><a  onclick='getname(this);'>暂无数据</a></li>";
				$("#item_ul_li").append(html);
			} else if (data.msg == 400) {
				html = "<li><a  onclick='getname(this);'>请求超时，请稍后重试</a></li>";
				$("#item_ul_li").append(html);
			}
			$("#item_ul_li").append(html);
		}
	});
}

function getnamesf(obj) {
	
}
//ok
function ok(){
	var shopid=$("#shopid").val();//轮胎id
	var geshu=$("#num_input").val();//数量
	var gid=$("#gid").val();//轮胎站点id
	if(shopid=="" || shopid==null){
		return;
	}
	window.location.href=path+"/app/fukuan?shopid="+shopid+"&geshu="+geshu+"&gid="+gid
}
//加载轮胎列表
 
function ltsjwh(index, name,specifications,material) {
	console.log(name+specifications+material);

	$.ajax({
		url : 'shopAllList',
		type : 'get',
		data : {
			pageIndex : index,
			brand : name,
			specifications : specifications,
			material : material
		},
		dataType : 'json',
		success : function(data) {
			var html = "";
			if (data.msg == 1) {
				$.each(data.list, function(i, item) {
				html+="<li class='p94 bgcolor-f ' id='test'>"
                        +"<div class='clearfix border-bc'>"
                         +"    <div class='left'  width='2rem' height='2rem'><img class='img_size' src='"+item.pathimg+"'  /></div>"
                           +"  <div class='right'>"
                             +"   <font class='foncolor1'>型号："+item.marque+"</font>"
                              +"  <span>品牌：" + item.brand + "</span>"
                               +"  <div class='clearfix lr'><span class='left'>材质：" + item.material + "</span>" 
                               +"<span class='right'>规格：" + item.specifications + "</span></div>"
                             +"</div></div>"
                        +"<div class='clearfix yx'>"
                        +"    <div class='left'>单价：<font class='fontcolor2'>￥" + item.price + "/只</font></div>"
                         +"   <div class='right choosed'><a href='javascript:void(0);' onclick='choosed(this);' class='unseled'>已选</a></div>"
                         +"   <div class='right choose'><a href='javascript:void(0);' " +
                         		"onclick='choose(this,"+item.id+","+item.gId+");' class='seled'>购买</a></div>"
                        +"</div></li>";
				});
			} else if (data.msg == -1) {
				if (count != -1) {
					html = "<li class='p94 bgcolor-f 'style='text-align:center'>暂无数据</li>"
				}
				count = -1;
			} else if (data.msg == 400) {
				if (count != -1) {
					html = "<li class='p94 bgcolor-f 'style='text-align:center'>请求超时，请稍后重试</li>"
				}
				count = -1;
			}
			$("#item_ul").append(html);
		}
	});
}
;
Zepto(function($) {
	$(window).scroll(function() {
		if ( ($(window).scrollTop() + $(window).height() >= $(document).height()) ) {
			setTimeout(function() {
				var a = $("#pageNo").val(parseInt($("#pageNo").val()) + 1);
				var b = $("#pageNo").val();
				var name=$(".btn_left_one span:nth-child(2)").text();
		    	var specifications=$(".btn_left_two span:nth-child(2)").text();
		    	var material=$(".btn_left_three span:nth-child(2)").text();
				ltsjwh(b, name,specifications,material);
			}, 10);
		}
	});
})