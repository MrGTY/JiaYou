var count = 0;
$(function() {
	var sessionId=$("#sessionId").val();
	ltsjwh(1,sessionId);
});

function ltsjwh(index,sessionId) {
	//根据当前登录用户的id查找对应轮胎列表
	$.ajax({
		url : 'shopList',
		type : 'get',
		data : {
			pageIndex : index,
			uid:sessionId
		},
		dataType : 'json',
		success : function(data) {
			if (data.msg == 1) {
				var html = "";
				$.each(data.list, function(i, item) {
					html += "<li class='p94 bgcolor-f'>" + "<div class='clearfix border-bc'>"
						+ "<div class='left'><img src='" + path2 + "/statics/app/images/lt.jpg'  /></div>"
						+ "<div class='right'>"
						+ "<font class='foncolor1'>型号：" + item.marque + "</font>"
						+ "<div class='clearfix lr'><span class='left'>品牌：" + item.brand + "</span><span class='right'>规格：" + item.specifications + "</span></div>"
						+ "<div class='clearfix lr'><span class='left'>材质：" + item.material + "</span><span class='right'>单价：<font class='fontcolor2'>￥" + item.price + "/只</font></span></div>"
						+ "</div></div>"
						+ "<div class='clearfix yx'>"
						+ "<div class='right'><a href='editshop' onclick='edit(this);'>编辑<input type='hidden' value='"+item.id+"'/></a><a onclick='del(this);' class='seled'>删除<input type='hidden' value='"+item.id+"'/></a></div>"
						+ "</div></li>";
				});
			} else if (data.msg == -1) {
				if (count != -1) {
					html = "<li class='p94 bgcolor-f 'style='text-align:center'>暂无数据</li>"
					$("#item_ul").append(html);
				}
				count = -1;
				return;
			}else if(data.msg==400){
				if (count != -1) {
					html = "<li class='p94 bgcolor-f 'style='text-align:center'>请求超时，请稍后重试</li>"
					$("#item_ul").append(html);
				}
				count = -1;
				return;
			}
			$("#item_ul").append(html);
		}
	});
}
//删除
function del(obj){
	var idshop=$(obj).children().val();
	$.ajax({
		url : 'deloneshop',
		type : 'get',
		data : {id : idshop},
		dataType : 'json',
		success : function(data) {
			if(data.msg==1){
				alert(1);
				window.location.href="ltsjwh";
				//删除成功
			}else if(data.msg==-1){
				//删除失败
			}else if(data.msg==400){
				//系统超时,请稍后重试
			}
		}
	});
}
Zepto(function($) {
	$(window).scroll(function() {
		if ( ($(window).scrollTop() + $(window).height() >= $(document).height()) ) {
			setTimeout(function() {
				var a = $("#pageNo").val(parseInt($("#pageNo").val()) + 1);
				var b = $("#pageNo").val();
				var sessionId=$("#sessionId").val();
				ltsjwh(b,sessionId);
			}, 10);
		}
	});
})