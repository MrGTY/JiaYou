$(function() {
	jiazai();
	var id = $("#hidden").val();
	xuanze(id);

});
function jiazai() {
	$.ajax({
		url : path + '/vipInfo',
		type : 'get',
		data : {},
		dataType : 'json',
		success : function(data) {
			if (data.msg == 1) {
				var div = "";
				div += "<div class='header-txt-titl'>" +
					"<div class='mail right'>" +
					"<span></span>" +
					"</div>" +
					"<div>账户：" + data.phoneNum + "</div>" +
					"</div>" +
					"<div class='jzyuan'>" +
					"<div class='imghe'>" +
					"<img src='" + path + "/statics/app/images/yu.png'  />" +
					"<div class='fontnum'>" +
					"<span>剩余油量</span>" +
					"<span><font id='oilQuality'>" + data.oilMass + "</font>&nbsp;L</span>" +
					"</div>" +
					"</div>" +
					"</div>";
				$("#head").append(div);
			}
		}
	})
}
;
function xuanze(id) {
	$.ajax({
		url : path + '/vipInfo',
		type : 'get',
		data : {
			friendId : id
		},
		dataType : 'json',
		success : function(data) {
			var fenpei = "";
			fenpei += "<h3>" + data.userName + "（" + data.phoneNum + "）</h3>"
			$("#clearfix").after(fenpei);
		}
	})
}
;
function fenpeiqueding() {
	var oil = $("#text").val(); //用户输入的油量
	var id = $("#hidden").val();
	var oilQuality = $("#oilQuality").text();
	if (!isNaN(oil)) {//判断是否为数字
		if(!Number.isInteger(oil)){//判断是否为正整数	
		if (Number(oil) <= Number(oilQuality) && Number(oil) > 0) {
			$.ajax({
				url : path + '/giveOil',
				type : 'get',
				data : {
					OilQuantity : oil,
					ChooseId : id
				},
				dataType : 'json',
				success : function(data) {
					if (data.msg == 1) {
						//分配成功同时，建立好友关系
						$.ajax({
							url : path + '/buildMyFriend',
							type : 'get',
							data : {
								friendId : id
							},
							dataType : 'json',
							success : function(data) {
								if (data.msg == 1) {
									window.location.href = "chengong";
								}
							}
						})


					} else if (data.msg == -1) {
						layer.tips('分配失败！', '#text', {
							tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
					}else if (data.msg == -4) {
						layer.tips('十分钟之内无法再次分配油量', '.OKbtn', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
					}else if (data.msg == -5) {
						layer.tips('该好友账户已被禁用，无法分配！', '.OKbtn', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
					}

				}
			})
		}else if(Number(oil) > Number(oilQuality)){
			layer.tips('剩余油量不足！', '#text', {
				tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
			});
		} else {

			layer.tips('请输入大于零的数！', '#text', {
				tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
			});
		}
		
		}else{

			layer.tips('请输入大于零的正整数！', '#text', {
				tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
			});
		}

	} else {
		layer.tips('请输入数字！', '#text', {
			tips : [ 1, '#0FA6D8' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
		});
	}
}
layui.use('layer', function() {
	fenpeiqueding();
});