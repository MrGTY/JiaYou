$(".deleteById").on("click", function() {
	var obj = $(this);
	if (confirm("你确定要删除【" + obj.attr("gassname") + "】吗？")) {
		$.ajax({
			type : "GET",
			url : "delgass.json",
			data : {
				id : obj.attr("gassid")
			},
			dataType : "json",
			success : function(data) {
				if (data.delResult == "true") {// 删除成功：移除删除行
					alert("删除成功");
					obj.parents("tr").remove();
				} else if (data.delResult == "false") {// 删除失败
					alert("对不起，删除【" + obj.attr("gassname") + "】失败");
				} else if (data.delResult == "notexist") {
					alert("对不起，【" + obj.attr("gassname") + "】不存在");
				}
			},
			error : function(data) {
				alert("对不起，删除失败");
			}
		});
	}
});
$(".updateStatus").on("click", function() {
	var obj = $(this);
	if (confirm("你确定要修改状态吗？")) {
		$.ajax({
			type : "GET",
			url : "updgass.json",
			data : {
				id : obj.attr("gassid"),
				status : obj.attr("status")
			},
			dataType : "json",
			success : function(data) {
				if (data.delResult == "open") {// 开启
					obj.html("禁用");
					obj.parents("td").prev("td").html("已启用");
					page_nav(document.forms[0], obj.attr("index"));
				} else if (data.delResult == "close") {
					obj.html("开启");
					obj.parents("td").prev("td").html("已禁用");
					page_nav(document.forms[0], obj.attr("index"));
				} else if (data.delResult == "false") {// 删除失败
					alert("对不起修改失败");
				} else if (data.delResult == "notexist") {
					alert("对不起，不存在");
				}
			},
			error : function(data) {
				alert("对不起，修改失败");
			}
		});
	}
});
$(".commit").on("click", function(){
	var obj = $(this);
	window.location.href="commit?id="+obj.attr("gassid")
});
