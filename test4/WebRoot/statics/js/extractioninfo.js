$(function(){ 
	$("#ton").bind("blur",function(){
		var obj=$("#ton");
		var quota=parseInt(obj.attr("quota"));
		var ton=$("#ton").val();
		var zong=obj.attr("con")*ton;
		var a=quota-zong;
		$.ajax({
			type:"GET",// 请求类型
			url:"commit.json",// 请求的url
			data:{price:$("#ton").val(),ton:obj.attr("ton")},// 请求参数
			dataType:"json",// ajax接口（请求url）返回的数据类型
			success:function(data){// data：返回数据（json对象）
				if(data.price == "empty"){// 参数为空，错误提示
					alert("提取的值不能为空，至少为1吨");
					//$("#messgae").html("提取的值不能为空，至少为1吨");
					//$("#ton").val(obj.attr("ton"));
					//$("#leave").val(obj.attr("leave"));
				}else if(data.price == "chao"){// 超额，错误提示
					//$("#messgae").html("提取的值不能超可提取");
					$("#ton").val("");
					$("#leave").val("");
					alert("提取的值不能超可提取");
				}else if(data.price == "shao"){// 不能为0，正确提示
					//$("#messgae").html("提取量必须大于0");
					alert("提取量必须大于0");
				}else if(data.price == "ok"){
					$("#messgae").html("*可以提取");
					$("#leave").val(a);
				}
			},
			error:function(data){// 当访问时候，404，500 等非200的错误状态码
				alert("请求错误！");
			}
		});
	});
	$("#money").bind("blur",function(){
		var obj=$("#money");
		var quota=parseInt(obj.attr("quota"));
		var money=$("#money").val();
		var a=quota-money;
		$.ajax({
			type:"GET",// 请求类型
			url:"tirecommit.json",// 请求的url
			data:{price:$("#money").val(),zong:obj.attr("quota")},// 请求参数
			dataType:"json",// ajax接口（请求url）返回的数据类型
			success:function(data){// data：返回数据（json对象）
				if(data.price == "empty"){// 参数为空，错误提示
					alert("提取的值不能为空");
					//$("#messgae").html("提取的值不能为空");
					//$("#money").val(obj.attr("money"));
					//$("#yu").val(obj.attr("yu"));
				}else if(data.price == "chao"){// 超额，错误提示
					//$("#messgae").html("提取的值不能超可提取");
					$("#money").val("");
					$("#yu").val("");
					alert("提取的值不能超可提取");
				}else if(data.price == "shao"){// 不能为0，正确提示
					alert("提取的值必须大于0");
					//$("#messgae").html("提取量必须大于0");
				}else if(data.price == "ok"){
					$("#messgae").html("可以提取");
					$("#yu").val(a);
				}
			},
			error:function(data){// 当访问时候，404，500 等非200的错误状态码
				alert("请求错误！");
			}
		});
	});
	$("#ok").click(function(){
		var jd = document.getElementById("ton");
		if (jd.validity.valueMissing == true) {
			jd.setCustomValidity("提取量不能为空");
		} else if (isNaN(jd.value)) {
			jd.setCustomValidity("提取量必须是数字");
		}else if(jd.value<=0){
			jd.setCustomValidity("提取量必须大于0");
		} else {
			jd.setCustomValidity("");
		}
		$("#ok").submit();
	});
	$("#oks").click(function(){
		var jd = document.getElementById("money");
		if (jd.validity.valueMissing == true) {
			jd.setCustomValidity("提取量不能为空");
		} else if (isNaN(jd.value)) {
			jd.setCustomValidity("提取量必须是数字");
		}else if(jd.value<=0){
			jd.setCustomValidity("提取量必须大于0");
		}else {
			jd.setCustomValidity("");
		}
		$("#oks").submit();
	});
	
	
});