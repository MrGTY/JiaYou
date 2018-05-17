/*删除*/
function del(id) {
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
//    	var obj = $(this);
    	var a=info();
    	$.ajax({
			type:"GET",
			url:path+"/vipuserinfo/delete",
			data:{id:id},
			dataType:"json",
			success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				setTimeout(function () {
					swal({title:"删除成功!",
						confirmButtonText: "确定",//确定按钮上面的文档
				        closeOnConfirm: true
					},function(){
						window.location.href=path+"/vipuserinfo/vipindex?vipName="+a.vipName+"&userName="+a.userName+"&busNum="+a.busNum+"&pageNo="+a.pageNo+"&userType="+a.userType;	
					}); 
				 }, 500);		
			}else if(data.delResult == "false"){//删除失败
				swal("对不起，删除会员【"+obj.attr("vipName")+"】失败");
			}else if(data.delResult == "notexist"){
				swal("对不起，会员【"+obj.attr("vipName")+"】不存在");
			}
		},	
			error:function(data){
			if(data.delResult == "error"){
				swal("对不起，删除失败");
			}	
			
				}
    	});
    });
};

$(function(){
	$(".change").click(function(){
		var obj=$(this);		
		window.location.href=path1+"/vip/sign?id="+obj.attr("changeid");				
	});

$(".submit").click(function() {
		
		$("input:hidden[name='mycarId']").val("");
	});		
});

function mycar(mycarid) {
	$("input:text[name='vipName']").val("");
	$("input:text[name='userName']").val("");
	$("input:text[name='busNum']").val("");
	$(".userType").val("0");
	$("input:hidden[name='mycarId']").val(mycarid);
	$('#formvip').submit();
}

function info(){
	var ua=new Object();
	/*var vipName=$("input:text[name='vipName']").val();
    var userName=$("input:text[name='userName']").val();
    var busNum=$("input:text[name='busNum']").val();
    var userType=$(".userType").val();
    var pageNo=$("input:hidden[name='pageNo']").val();*/
	ua.vipName=$("input:text[name='vipName']").val();
	ua.userName=$("input:text[name='userName']").val();
	ua.busNum=$("input:text[name='busNum']").val();
	ua.userType=$(".userType").val();
	ua.pageNo=$('input:hidden[id=\'pageNo\']').val();
	return ua;
}

/*禁用*/
function fob1(obj) {
   
   	window.location.href=path+"/vipuserinfo/userChange";
 
}
/*启用*/
function op(obj) {
    swal({
        title: "操作提示",      //弹出框的title
        text: "确定启用吗？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，确定启用！",//确定按钮上面的文档
        closeOnConfirm: true
    }, function () {
        $.ajax({
            type: "post",
            url: "",
            data: {"": JSON.stringify("")},
            success: function (data, status) {
                if (status == "success") {
                }
            },
            error: function () {
            },
            complete: function () {

            }

        });
    });
}


$(function(){
	$('#myModal').on('hidden.bs.modal',function(){
		$(':checkbox[name=\'id\']').each(function(index,item){
			if($('div [name=\'clone_'+$(this).val()+'\']').css('display')  == 'none'){
				$('div [name=\'clone_'+$(this).val()+'\']').css('display',''); 
			}
		});
	});
});


/*点击充值弹窗begin*/
function moreMoney(id) {
	if(id){
		if($(':checkbox[name=\'id\']:checked').size()>0){
			swal("无需勾选!!");
		}else{
			$('div [name*=\'clone_\']').css('display','none');
			$('div [name=\'clone_'+id+'\']').css('display','block');
			$('#myModal').modal({backdrop: 'static'});	
		}	
	}else{
		if($(':checkbox[name=\'id\']:checked').size()>0){
			$(':checkbox[name=\'id\']:not(:checked)').each(function(){
				$('div [name=\'clone_'+$(this).val()+'\']').css('display','none');
			});
			 $('#myModal').modal({backdrop: 'static'});
		}else {
			swal('必须勾选需要充值的会员!');
		}		
	}    
}
//保存
function saveMoney(obj,id) {
	var a=info();
	var  options=$('input:radio[name=\'options_'+id+'\']:checked').val();	//充值类型
    var  optionsRad=$('input:radio[name=\'optionsRad_'+id+'\']:checked').val();//充值方式
    var  moneyname=$("#money_"+id).val();  //充值金额
    var  oilname=$("#oil_"+id).val();//充值油量
    var  remark=$("#remark_"+id).val();//备注
    var  pageNo=$('input:hidden[id=\'pageNo\']').val();//页码
//    alert(id);
//    alert(pageNo);
    //在下面执行确定操作
//    alert($('div [name=\'clone_'+id+'\']').css('display'));
    $.ajax({
    	type:"GET",
    	url:path+"/vipuserinfo/recharge",
    	data:{
    		options:options,
    		optionsRad:optionsRad,
			id:id,
			moneyname:moneyname,
			oilname:oilname,
			remark:remark
		},
		dataType:"json",
		success:function(data){
			if(data.mas=="failure"){
				 swal("连接超时！")
				 $("#myModal").modal("hide");
			}else if(data.mas=="positive"){
				swal({title:"填写数据不能为负数！",
					  confirmButtonText: "确定",//确定按钮上面的文档
   				  closeOnConfirm: true
				},function () {
					if($(':checkbox[name=\'id\']:checked').size()>0){
							$('div [name*=\'clone_\']').css('display','none');
						$(':checkbox[name=\'id\']:checked').each(function(){
							$('div [name=\'clone_'+$(this).val()+'\']').css('display','block');
						});
					}else{
						$('div [name*=\'clone_\']').css('display','none');
						$('div [name=\'clone_'+id+'\']').css('display','block');
					}
					$('#myModal').modal({backdrop: 'static'});	
				})
				$("#myModal").modal("hide");
				
				
				
			}else if(data.mas=="choose"){
				swal({title:"请填写或勾选必填项！",
					  confirmButtonText: "确定",//确定按钮上面的文档
     				  closeOnConfirm: true
				},function () {
					if($(':checkbox[name=\'id\']:checked').size()>0){
							$('div [name*=\'clone_\']').css('display','none');
						$(':checkbox[name=\'id\']:checked').each(function(){
							$('div [name=\'clone_'+$(this).val()+'\']').css('display','block');
						});
					}else{
						$('div [name*=\'clone_\']').css('display','none');
						$('div [name=\'clone_'+id+'\']').css('display','block');
					}
					$('#myModal').modal({backdrop: 'static'});	
				})
				$("#myModal").modal("hide");
			}else if(data.mas=="Excep"){
				 swal("交易异常!");
				 $("#myModal").modal("hide");
			}else if(data.mas=="success"){
				if($(':checkbox[name=\'id\']:checked').size()>0){
					$(':checkbox[name=\'id\']:not(:checked)').each(function(){
						$('div [name=\'clone_'+$(this).val()+'\']').remove();
					});
				var len = $(obj).parent().parent().parent().parent().siblings().length;
				console.log(len);
				$('div [name=\'clone_'+id+'\']').remove();
				$('#box_'+id+'').attr('checked',false);
					 if (len==1) {
					        // 将 bootstrap模态框手动移除
					        $("#myModal").modal("hide");
					        swal({title:'充值成功!',
			      				 confirmButtonText: "确定",//确定按钮上面的文档
			      				 closeOnConfirm: true
			      			},function () {
			      				window.location.href=path+"/vipuserinfo/back?vipName="+a.vipName+"&userName="+a.userName+"&busNum="+a.busNum+"&pageNo="+a.pageNo+"&userType="+a.userType;
			      				});
					    } 
				}else{
					$("#myModal").modal("hide");
					swal({title:'充值成功!',
	      				 confirmButtonText: "确定",//确定按钮上面的文档
	      				 closeOnConfirm: true
	      			},function () {
	      				window.location.href=path+"/vipuserinfo/back?vipName="+a.vipName+"&userName="+a.userName+"&busNum="+a.busNum+"&pageNo="+a.pageNo+"&userType="+a.userType;
	      				});
				}
			}
		},
		error :function(data){
			swal({title:"数据有误!",
				  confirmButtonText: "确定",//确定按钮上面的文档
				  closeOnConfirm: true
			},function () {
				$('div [name*=\'clone_\']').css('display','none');
				$('div [name=\'clone_'+id+'\']').css('display','block');
				$('#myModal').modal({backdrop: 'static'});	
			})
			$("#myModal").modal("hide");
		}	
    });   
}    
//取消
function overMoney(obj,id) {
    var len = $(obj).parent().parent().parent().parent().siblings().length;
    var  pageNo=$('input:hidden[id=\'pageNo\']').val();//页码
    console.log(len);
    	var i=0;
    	if($(':checkbox[name=\'id\']:checked').size()>0){
    		$('div [name=\'clone_'+id+'\']').css('display','none');	
    		$('#box_'+id+'').attr('checked',false);
    		$(':checkbox[name=\'id\']:checked').each(function(){
				if($('div [name=\'clone_'+$(this).val()+'\']').css('display')=='none'){
					i++;
				}
			});
    		if(i>=$(':checkbox[name=\'id\']:checked').size()){
    			$("#myModal").modal("hide");
    			window.location.href=path+"/vipuserinfo/vipindex?pageNo="+pageNo;
    		}	
    	}else{
    		$("#myModal").modal("hide");
    	}
    //在下面执行取消操作
}
/*点击充值弹窗end*/


/*点击收款弹窗begin*/
function giveMoney(obj) {
    /*点击div外不关闭*/
	 var Payamount=$("input:text[name='txt_departmentname']").val('');
	 var remark=$("#skremark").val('');
    $('#myModal1').modal({backdrop: 'static'});
    $.ajax({
    	type:"GET",
		url:path+"/vipuserinfo/getinfo",
		data:{id:obj},
		dataType:"json",
    	success:function(data){
    		$("#vipid").val(data.id);
    		$("#userName").html("会员【"+data.userName+"】欠款金额:");
			$("#qkBalance").html("￥"+data.qkBalance);
		},
		error :function(data){
			swal("数据有误");
		}
    });
  
}
//保存
function saveMoney1(obj) {
        // 将 bootstrap模态框手动移除
     $("#myModal1").modal("hide");
     var id= $("#vipid").val();   
     var a=info();
     var oiltype=$("input:radio[name='oiloptions']:checked").val();
     var Payamount=$("input:text[name='txt_departmentname']").val();
     var remark=$("#skremark").val();
        
    $.ajax({
    	type:"GET",
    	url:path+"/vipuserinfo/gathering",
    	data:{
    		id:id,
    		oiltype:oiltype,
    		Payamount:Payamount,
    		remark:remark
    	},
    	dataType:"json",
    	success:function(data){
    		if(data.mas=="failure"){
    			swal({title:'请检查是否勾选类型或是否输入金额!',
     				 confirmButtonText: "确定",//确定按钮上面的文档
     				 closeOnConfirm: true
     			},function () {
     				 $('#myModal1').modal({backdrop: 'static'});
     			});
    		}else if(data.mas=="greater"){
    			swal({title:'充值金额不得大于欠款金额!',
    				 confirmButtonText: "确定",//确定按钮上面的文档
    				 closeOnConfirm: true
    			},function () {
    				 $('#myModal1').modal({backdrop: 'static'});
    			});
    		}else if(data.mas=="positive"){
    			
    			swal({title:'充值金额不得为负数!',
   				 confirmButtonText: "确定",//确定按钮上面的文档
   				 closeOnConfirm: true
   			},function () {
   				 $('#myModal1').modal({backdrop: 'static'});
   			});
    		}else if(data.mas=="error"){
    			swal({title:'数据错误!',
   				     confirmButtonText: "确定",//确定按钮上面的文档
   				     closeOnConfirm: true
   			},function () {
   			 $('#myModal1').modal({backdrop: 'static'});
   			});
    		}else if(data.mas=="Excep"){
    			swal("交易异常!");
    		}else{
    			swal({title:data.qk,
    				 confirmButtonText: "确定",//确定按钮上面的文档
    				 closeOnConfirm: true
    			},function () {
    				window.location.href=path+"/vipuserinfo/back?vipName="+a.vipName+"&userName="+a.userName+"&busNum="+a.busNum+"&pageNo="+a.pageNo+"&userType="+a.userType;
    			});
    		}	
    	},
    	error:function(data){
    		swal("数据异常!");
    		}	
    });
        
        
        
        
        
}
//取消
function overMoney1(obj) {
        // 将 bootstrap模态框手动移除
        $("#myModal1").modal("hide");
}
/*点击收款弹窗end*/


/*点击分类弹窗begin*/
function changeType(obj) {
    /*点击div外不关闭*/
	var remark=$("#textarea").val('');
	$("#fenform").hide();
	$('#myModal1a').modal({backdrop: 'static'});
    $.ajax({
    	type:"GET",
		url:path+"/vipuserinfo/getinfo",
		data:{id:obj},
		dataType:"json",
    	success:function(data){
    		$("#vid").val(data.id);
    		$("#uName").html("会员【"+ data.userName +"】");
			$("#busNum").html("车牌号:"+data.busNum);
		},
		error :function(data){
			swal("数据有误");
		}
    });
    
}
//保存分类
function saveMoney2(obj) {
	var a=info();
    var len = $(obj).parent().parent().parent().parent().siblings().length;
    if (len == 1) {
        // 将 bootstrap模态框手动移除
        $("#myModal1a").modal("hide");
    }
    var usertype=$("input:radio[name='userType']:checked").val();
    var id=$("input:hidden[name='fid']").val();
    var remark=$("#textarea").val();
    var logicId=$("#exampid").val();
    if(usertype==null || remark==null|| remark==""){
    	debugger;
    	swal({title:"请检查类型或备注是否填写！",
			  confirmButtonText: "确定",//确定按钮上面的文档
			  closeOnConfirm: true
		},function () {
			$('#myModal1a').modal({backdrop: 'static'});
		});
    }else{
    	$.ajax({
        	type:"POST",
    		url:path+"/vipuserinfo/changeType",
    		data:{
    			id:id,
    			userType:usertype,
    			remark:remark,
    			logicId:logicId
    		},
    		dataType:"json",
        	success:function(data){
        		if(data.mas=="failure"){
        			swal("修改失败！！！")
        		}else if(data.mas=="success"){
        			swal({title:'修改成功!',
          				 confirmButtonText: "确定",//确定按钮上面的文档
          				 closeOnConfirm: true
          			},function () {
          				window.location.href=path+"/vipuserinfo/back?vipName="+a.vipName+"&userName="+a.userName+"&busNum="+a.busNum+"&pageNo="+a.pageNo+"&userType="+a.userType;
          				});
        		}
    		},
    		error :function(data){
    			swal({title:"数据有误！",
    				  confirmButtonText: "确定",//确定按钮上面的文档
    				  closeOnConfirm: true
    			},function () {
    				$('#myModal1a').modal({backdrop: 'static'});
    			})
    		}
        });
    }    
}
//取消
function overMoney2(obj) {
    var len = $(obj).parent().parent().parent().parent().siblings().length;
    if (len == 1) {
        // 将 bootstrap模态框手动移除
        $("#myModal1a").modal("hide");
    }
    
  
    
    
}
/*点击分类弹窗end*/
/*点击修改密码弹窗begin*/
function upPwd(obj) {
    /*点击div外不关闭*/
	id=$('input:checkbox[name="id"]:checked').val();
	 password=$("#pwd1").val('');
     password1=$("#pwd2").val('');
	if($(':checkbox[name=\'id\']:checked').size()>1){
	   swal("只能勾选一名会员！");
	}else if(id==null){
 	   swal("必须勾选需要修改密码的会员！");
    }else{
    	 $('#Modal1').modal({backdrop: 'static'});
    }
   
    
    
}


//保存
function saveok12() {
        // 将 bootstrap模态框手动移除
        $("#Modal1").modal("hide");
       var id=$('input:checkbox[name="id"]:checked').val();
       var a=info();
        password=$("#pwd1").val();
        password1=$("#pwd2").val();
        if(password==""){
    	   swal({title:"密码不能为空!",
				  confirmButtonText: "确定",//确定按钮上面的文档
				  closeOnConfirm: true
			},function () {
				 $('#Modal1').modal({backdrop: 'static'});
			})
       }else if(password!=password1){
    	   swal({title:"两次输入的密码不一致！",
				  confirmButtonText: "确定",//确定按钮上面的文档
				  closeOnConfirm: true
			},function () {
				 $('#Modal1').modal({backdrop: 'static'});
			})
        }else{
        	$.ajax({
            	type:"GET",
        		url:path+"/vipuserinfo/updatepwd",
        		data:{
        			id:id,
        			password:password
        		},
        		dataType:"json",
            	success:function(data){
            		if(data.mas=="failure"){
            			swal("修改失败！！！")
            		}else if(data.mas=="success"){
            		swal({title:'修改成功!',
           				 confirmButtonText: "确定",//确定按钮上面的文档
           				 closeOnConfirm: true
           			},function () {
           				window.location.href=path+"/vipuserinfo/back?vipName="+a.vipName+"&userName="+a.userName+"&busNum="+a.busNum+"&pageNo="+a.pageNo+"&userType="+a.userType;
           				});
            		}
        		},
        		error :function(data){
        			swal("数据有误");
        		}
            });
        }
}
//取消
function quxiao(obj) {
        // 将 bootstrap模态框手动移除
        $("#Modal1").modal("hide");
}
/*点击修改密码弹窗end*/


/*点击查看上传图片弹窗begin*/
function upload(img) {
    /*点击div外不关闭*/
	var obj=$(img);
	console.log("obj==="+obj.attr('xszimg'));
	$("#change").attr("src",obj.attr('xszimg'));
    $('#Modal12').modal({backdrop: 'static'});
}
//保存
function saveMoney12(obj) {
        // 将 bootstrap模态框手动移除
        $("#Modal12").modal("hide");
}
//取消
function overMoney12(obj) {
        // 将 bootstrap模态框手动移除
        $("#Modal12").modal("hide");
}
/*点击查看上传图片弹窗end*/
/*$("#showdiv>.vipdiv").hover(function(){
	$("#showdiv>.vipdiv").css("background-color","#F5F5DC");
},function(){
	$("#showdiv>.vipdiv").css("background-color","#DCDCDC");
});*/
//点击物流公司为hidden赋值(为该物流公司id)
function choose(obj){
	$("#examp").val($(obj).text());
	$("#exampid").val($(obj).children().val());
	//var idshop=$(obj).children().val();
	$("#showdiv").hide();
}
//物流公司模糊查询
function sososo(obj){
	$.ajax({
		type : "GET",
		url : "vip/fenlei.json",
		data : {
			name : $("#examp").val()
		},
		dataType : "json",
		success : function(data) {
			$("#showdiv").children().detach();
			var str=null;
			$(data).each(function(){
				str="<div onclick='choose(this);' class='vipdiv' style='background-color: #DCDCDC;border-bottom: 1px solid;cursor: pointer;'>"+this.vipName+
				"<input type='hidden' value='"+this.id+"'/></div>";
				$("#showdiv").append(str);
				$("#showdiv").show();
			})
		},
		error : function(data) {
			alter("查找有误");
		}
	});
}
$("#optionsRadios1").click(function(){
	$("#fenform").hide();
	$("#examp").val("");
	$("#exampid").val(0);
});
$("#optionsRadio2").click(function(evnet){
	var e=window.event || event;
    if(e.stopPropagation){
        e.stopPropagation();
    }else{
        e.cancelBubble = true;
    }
	$("#fenform").show();
	$("#examp").val("");
	$("#exampid").val(0);
})
	document.onclick = function(){
	$("#showdiv").hide();
    };