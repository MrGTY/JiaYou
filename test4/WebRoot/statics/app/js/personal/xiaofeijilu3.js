$(function() {
	var a = $("#userinfo").val();
	mycarinfo(a);
	oilInfo(a);

})
function mycarinfo(a) {
	$.ajax({
		url : path + "/vipInfo",
		type : "get",
		data : {
			friendId : a
		},
		dataType : "json",
		success : function(data) {
			var head = "";
			head += "<h3>用户：" + data.phoneNum + "(" + data.busNum + ")</h3>" +
				"<span class='numl'><font>" + data.oilMass + "</font>&nbsp;L</span>" +
				"<span>账户余量</span>"

			$("#mycar-header").append(head);
		}
	})
}

function oilInfo(a) {
	$.ajax({
		url : path + "/oilInfo",
		type : "get",
		data : {
			vipUserId : a
		},
		dataType : "json",
		success : function(data) {
			var li = "";
			if (data.msg == 1) {
				$.each(data.listOil, function(i, item) {
					if (item.operationType == 1) {
						if (item.income != 0) {
							li += "<li class='p94 bgcolor-f'>" +
								"<div>收入油量</div>" +
								"<div><span>朋友" + item.friendId + "分配</span></div>" +
								"<div class='xfnum'><span>赠送：+" + item.income + " L  </span><span> 余量：" + item.remaining + " L</span></div>" +
								"<time>" + item.operationTime + "</time>" +
								"</li>"
						} else {
							li += "<li class='p94 bgcolor-f'>" +
								"<div>支出油量</div>" +
								"<div><span>赠送好友" + item.friendId + "</span></div>" +
								"<div class='xfnum'><span>赠送：-" + item.expenditure + " L  </span><span> 余量：" + item.remaining + " L</span></div>" +
								"<time>" + item.operationTime + "</time>" +
								"</li>"
						}
					} else if (item.operationType == 2) {
						if (item.income != 0) {
							li += "<li class='p94 bgcolor-f'>" +
								"<div>收入油量</div>" +
								"<div><span>" + item.gasstation.siteName + "</span></div>" +
								"<div class='xfnum'><span>充值量：+" + item.income + " L  </span><span> 余量：" + item.remaining + " L</span></div>" +
								"<time>" + item.operationTime + "</time>" +
								"</li>"
						} else {
							li += "<li class='p94 bgcolor-f'>" +
								"<div>支出油量</div>" +
								"<div><span>" + item.gasstation.siteName + "</span></div>" +
								"<div class='xfnum'><span>加油量：-" + item.expenditure + " L  </span><span> 余量：" + item.remaining + " L</span></div>" +
								"<time>" + item.operationTime + "</time>" +
								"</li>"
						}
					} else if (item.operationType == 0) {
						if (item.income != 0) {
							li += "<li class='p94 bgcolor-f'>" +
								"<div>收入油量</div>" +
								"<div><span>平台充值</span></div>" +
								"<div class='xfnum'><span>充值量：+" + item.income + " L  </span><span> 余量：" + item.remaining + " L</span></div>" +
								"<time>" + item.operationTime + "</time>" +
								"</li>"
						}
					}
				})
				$("#xfjl-ul").append(li);
			} else if (data.msg == -1) {
				li+="<li class='p94 bgcolor-f'>暂无数据！</li>"
					$("#xfjl-ul").append(li);
			}else if (data.msg ==400) {
				li+="<li class='p94 bgcolor-f'>请求超时！</li>"
					$("#xfjl-ul").append(li);
			}
		}
	})
}