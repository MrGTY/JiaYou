/*删除*/
function del(dang){
	var obj=$(dang);
    swal({
        title: "操作提示",      //弹出框的title
        text: "确定删除吗？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，确定删除！",//确定按钮上面的文档
        closeOnConfirm: true
    }, function () {
    	$.ajax({
			type : "GET",
			url : "delgass.json",
			data : {
				id : obj.attr("gassid")
			},
			dataType : "json",
			success : function(data) {
				if (data.delResult == "true") {// 删除成功：移除删除行
					obj.parents("tr").remove();
					swal({
			            title:'自动关闭弹窗',
			            text:'设置弹窗在2秒后关闭',
			            timer:2000,
			            showConfirmButton:false
			        });
					page_nav(document.forms[0], obj.attr("index"));
				} else if (data.delResult == "false") {// 删除失败
					swal("对不起，删除失败");
				} else if (data.delResult == "notexist") {
					swal("对不起，不存在");
				}
			},
			error : function(data) {
				swal("对不起，删除失败");
			}
		});
    });
};
/*禁用启用*/
function fob(dang) {
	var obj = $(dang);
    swal({
        title: "操作提示",      //弹出框的title
        text: "确定修改用户状态用吗？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，修改用户状态！",//确定按钮上面的文档
        closeOnConfirm: true
    }, function () {
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
					//obj.html("禁用");
					//obj.parents("td").prev("td").html("启用");
					page_nav(document.forms[0], obj.attr("index"));
				} else if (data.delResult == "close") {
					//obj.html("开启");
					//obj.parents("td").prev("td").html("禁用");
					page_nav(document.forms[0], obj.attr("index"));
				} else if (data.delResult == "false") {// 删除失败
					swal("对不起修改失败");
				} else if (data.delResult == "notexist") {
					swal("对不起，不存在");
				}
			},
			error : function(data) {
				swal("对不起，修改失败");
			}
		});
    });
}
/*查看二维码*/
function showMa(imgg) {
	$("#myModalLabel").text("查看二维码");
	var obj=$(imgg);
	var copyimg=path+"/statics/qrcode/"+obj.attr("gassimg");
	$('#copy-input').val(copyimg);
	$("#change").attr("src",path+"/statics/qrcode/"+obj.attr("gassimg"));
	$("#downimg").attr("srcimg",path+"/statics/qrcode/"+obj.attr("gassimg"));
	$("#downimg").attr("downimg",obj.attr("gassimg"));
	//$('#myModal').modal({backdrop: 'static',keyboard: true});
	$('#myModal').modal({backdrop: 'true',keyboard: true});
}
function addtire(dang){
	var obj=$(dang);
	window.location.href="tireadd"
};
function commit(dang){
	var obj=$(dang);
	window.location.href="tirecommit?id="+obj.attr("gassid")
};
function updategass(dang){
	var obj=$(dang);
	window.location.href="tiremodify?id="+obj.attr("gassid")
};
function selectgass(dang){
	var obj=$(dang);
	window.location.href="tireselectid?id="+obj.attr("gassid")
};
function note(dang){
	var obj=$(dang);
	window.location.href="tirenote?id="+obj.attr("gassid")
};
function note(dang){
	var obj=$(dang);
	window.location.href="tirenote?id="+obj.attr("gassid")
};