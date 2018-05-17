$(function() {
	
});
//购买轮胎
function paymoney() {
	var gid = $("#gid").val(); //站点id
	var id = $("#id").val(); //轮胎id
	var quantity = $("#quantity").val(); //数量
	var price = $("#price"); //总价
	$.ajax({
		url : 'buyTyre',
		type : 'get',
		data : {
			id : id,
			num : quantity,
			money : price
		},
		dataType : 'json',
		success : function(data) {
			if (data.msg == 1) {
				windows.location.href = "";
			} else if (data.msg == -10) {
				//
			} else if (data.msg == -1) {
				//支付失败
			} else if (data.msg == 400) {
				//系统异常,请刷新后重试
			}

		}
	});
}