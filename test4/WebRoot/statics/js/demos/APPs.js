/*点击上传弹窗begin*/
function ok(obj) {
    /*点击div外不关闭*/
    $('#myModal1a').modal({backdrop: 'static'});
}
//保存
function saveMoney2(obj) {
    // 将 bootstrap模态框手动移除
	$("#myModal1a").modal("hide");
	alert($('#exampleInputFile').val());
}
//取消
function overMoney2(obj) {
        // 将 bootstrap模态框手动移除
        $("#myModal1a").modal("hide");
}
/*点击上传弹窗end*/
/*删除*/
function del(obj){
    swal({
        title:"操作提示",      //弹出框的title
        text:"确定删除吗？",   //弹出框里面的提示文本
        type:"warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor:"#DD6B55",//确定按钮颜色
        cancelButtonText:"取消",//取消按钮文本
        confirmButtonText:"确定删除！",//确定按钮上面的文档
        closeOnConfirm:true
    },function () {
       $.ajax({
    	   	type:"GET",
   			url:"deleteApp",
   			data:{id:obj},
   			dataType:"json",
   			success:function(data){
   				if(data.mas=='success'){
   					setTimeout(function () {
   						swal({title:"删除成功!",
   							confirmButtonText: "确定",//确定按钮上面的文档
   					        closeOnConfirm: true
   						},function(){
   							window.location.href="selectAllApp";	
   						}); 
   					 }, 100);
   				}else if(data.mas=='path'){
   					setTimeout(function () {
   						swal({title:"找不到文件!",
   							confirmButtonText: "确定",//确定按钮上面的文档
   					        closeOnConfirm: true
   						},function(){
   							window.location.href="selectAllApp";	
   						}); 
   					 }, 100);
   				}else if(data.mas=='failure'){
   					setTimeout(function () {
   						swal({title:"数据有误!",
   							confirmButtonText: "确定",//确定按钮上面的文档
   					        closeOnConfirm: true
   						},function(){
   							window.location.href="selectAllApp";	
   						}); 
   					 }, 100);
   				}	
   			},
   			error:function(){
   				swal("数据有误！");
   			}
       });
    });
}