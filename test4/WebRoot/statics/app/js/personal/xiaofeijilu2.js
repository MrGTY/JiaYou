$(function() {
	//站点燃油记录接口 
	$.ajax({
		url : 'record',
		type : 'get',
		data : {
			pageIndex : "2"
		},
		dataType : 'json',
		success : function(data) {
			var html = "";
			if (data.msg == 1) {
				$.each(data.list, function(i, item) {
					if (item.income > 0) {
						html += "<li class='p94 bgcolor-f'>"
							+ "<div  style='color:black;'>用户:" + item.phoneNum.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2') + "</div>"
							+ "<div>完成量:<span style='color:red;'>" + item.income + "L</span></div>"
							+ "<div class='xfnum'><span>车辆：" + item.busNum + "</span><span></span></div>"
							+ "<time>" + item.operationTime + "</time></li>";
					}
				});
			} else if (data.msg == -1) {
				html = "<li class='p94 bgcolor-f 'style='text-align:center'>暂无数据</li>"
			} else if (data.msg = 400) {
				html = "<li class='p94 bgcolor-f 'style='text-align:center'>请求超时，请稍后重试</li>"
				return;
			}
			$("#item_ul").append(html);
		}
	});
});