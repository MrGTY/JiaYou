/*删除*/
function del(obj) {
	swal({
		title : "操作提示", // 弹出框的title
		text : "确定删除吗？", // 弹出框里面的提示文本
		type : "warning", // 弹出框类型
		showCancelButton : true, // 是否显示取消按钮
		confirmButtonColor : "#DD6B55",// 确定按钮颜色
		cancelButtonText : "取消",// 取消按钮文本
		confirmButtonText : "是的，确定删除！",// 确定按钮上面的文档
		closeOnConfirm : true
	}, function() {
		$.ajax({
	    	   type:"GET",
	   			url:path+"/deleteEmploye",
	   			data:{id:obj},
	   			dataType:"json",
	   			success:function(data){		
	   				if(data.mas=='success'){
	   					setTimeout(function () {
	   					swal({title:"删除成功！",
	   						confirmButtonText : "确定",// 确定按钮上面的文档
	   						closeOnConfirm : true
	   						},function(){
	   							window.location.href=path+'/findAllEmploye';
	   						});
	   				 }, 500);
	   				}else if(data.mas=='failure'){
	   					setTimeout(function () {
	   					swal({title:"删除失败！",
	   						confirmButtonText : "确定",// 确定按钮上面的文档
	   						closeOnConfirm : true
	   						},function(){
	   							window.location.href=path+'/findAllEmploye';
	   						});
	   				 }, 500);
	   				}else if(data.mas=='wrong'){
	   					setTimeout(function () {
	   					swal({title:"数据有误！",
	   						confirmButtonText : "确定",// 确定按钮上面的文档
	   						closeOnConfirm : true
	   						},function(){
	   							window.location.href=path+'/findAllEmploye';
	   						});
	   				 }, 500);
	   				}	
	   			},
	   			error:function(){
	   				swal("数据有误！");
	   			}
	       });
	});
};