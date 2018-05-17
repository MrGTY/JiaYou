/*点击审批弹窗begin*/
function ok(obj) {
	/* 点击div外不关闭 */
	$('#myModal1a').modal({
		backdrop : 'static'
	});
	$.ajax({
		type : "GET",
		url : path + "/selectApplyById",
		data : {
			id : obj
		},
		dataType : "json",
		success : function(data) {
			for (i in data) {
				$("#code").html("商户【" + data[i].gasstation.code + "】");
				if (data[i].businessType == 0) {
					$("#businessType").html("申请【加油】提取:");
				} else {
					$("#businessType").html("申请【轮胎】提取:");
				}
				$("#cid").val(data[i].id);
			}
		},
		error : function() {
			alter("获取数据失败！");
		}
	});
}
// 保存
function saveMoney2(obj) {
	$("#myModal1a").modal("hide");// 将 bootstrap模态框手动移除
	var serialNum = $("#yes").val();
	var type=$("input:hidden[name='type']").val();
	var id=$("input:hidden[id='cid']").val();
	$.ajax({
		type : "POST",
		url : path + "/updateApply",
		data : {	
			serialNum:serialNum,	
			type:type,
			id:id
		},
		dataType : "json",
		success : function(data) {
			if (data.mas == "success") {
				swal({title:'审核成功!',
     				 confirmButtonText: "确定",//确定按钮上面的文档
     				 closeOnConfirm: true
     			},function () {
     				window.location.href = path + '/selectAllApply';
     				});
			} else if (data.mas == "failure") {
				swal({title:'审核失败!',
    				 confirmButtonText: "确定",//确定按钮上面的文档
    				 closeOnConfirm: true
    			},function () {
    				window.location.href = path + '/selectAllApply';
    				});
			}
		},
		error : function() {
			alter("数据异常！");
		}
	});
}
// 取消
function overMoney2(obj) {
	$("#myModal1a").modal("hide");// 将 bootstrap模态框手动移除
}
/* 点击审批弹窗end */

/* 点击拒绝弹窗begin */
function no(obj) {
	/* 点击div外不关闭 */
	$('#myModal1aa').modal({
		backdrop : 'static'
	});
	$.ajax({
		type : "POST",
		url : path + "/selectApplyById",
		data : {
			id : obj
		},
		dataType : "json",
		success : function(data) {
			for (i in data) {
				$("#nocode").html("商户【" + data[i].gasstation.code + "】");
				if (data[i].businessType == 0) {
					$("#nobusinessType").html("申请【加油】提取:");
				} else {
					$("#nobusinessType").html("申请【轮胎】提取:");
				}
				$("#noid").val(data[i].id);
			}
		},
		error : function() {
			alter("获取数据失败！");
		}
	});
}
// 保存
function saveMoney22(obj) {
	$("#myModal1aa").modal("hide");// 将 bootstrap模态框手动移除
	var serialNum = $("#no").val();
	var type=$("input:hidden[name='type2']").val();
	var id=$("#noid").val();
	alert(type);
	$.ajax({
		type : "POST",
		url : path + "/updateApply",
		data : {
			type:type,
			serialNum : serialNum,
			id:id
		},
		dataType : "json",
		success : function(data) {
			if (data.mas == "success") {
				swal({title:'拒绝成功！',
    				 confirmButtonText: "确定",//确定按钮上面的文档
    				 closeOnConfirm: true
    			},function () {
    				window.location.href = path + '/selectAllApply';
    				});
			} else if (data.mas == "failure") {
				swal({title:'拒绝失败！',
   				 confirmButtonText: "确定",//确定按钮上面的文档
   				 closeOnConfirm: true
   			},function () {
   				window.location.href = path + '/selectAllApply';
   				});
			} else {
				swal({title:'数据有误！',
	   				 confirmButtonText: "确定",//确定按钮上面的文档
	   				 closeOnConfirm: true
	   			},function () {
	   				window.location.href = path + '/selectAllApply';
	   				});
			}
		},
		error : function() {
			alter("数据异常！");
		}
	});
}
// 取消
function overMoney22(obj) {
	$("#myModal1aa").modal("hide");// 将 bootstrap模态框手动移除
}
/* 点击拒绝弹窗end */