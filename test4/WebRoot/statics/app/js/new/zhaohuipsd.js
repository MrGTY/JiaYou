$(function(){
	layui.use('layer', function() {
		$("#yam").click(function(){
			var mobile=$("input:text[name='mobile']").val();
			if(mobile==""||mobile==null){
				layer.tips('手机号不能为空!', '.OKbtn1', {
					tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
				});
			}else{
			$.ajax({
				type:"GET",
				url:path+"/app/yzm",
				data:{
					mobile:mobile
				},
				datatype:"json",
				success: function(data){
					$("#yam").hide();
					$("#minut").show();
					var times = 60;
					var timer = null;
					// 计时开始
					timer = setInterval(function() {
						times--;
						if (times < 0) {
							$("#hqyzm").show();
							$("#minut").hide();
							clearInterval(timer);
							times = 60;
						} else {
							$("#minut").text(times + "秒后重试");
						}
					}, 1000);
				var result=JSON.parse(data);
				if(result.msg==1){
					$("#pyzm").val(result.pyzm);
					}else if(result.msg==400){
						layer.tips('手机号未注册！', '.OKbtn1', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
							});
						}
					},
				});
			}
		});
		$("#gobacksf").on("click",function(){
			window.history.back(); 
//			window.location.href=path+"/app/shouye";
		});
		
		$(".OKbtnsf").click(function(){
			var mobile=$("input:text[name='mobile']").val();
			var pyzm=$("#pyzm").val();
			var password1=$("#password1").val();
			var password2=$("#password2").val();
			var yzm=$("input:text[name='yzm']").val();
			if(mobile==""||yzm==""||password1==""||password2==""){
				layer.tips('请完善填写信息!', '.logotable', {
					tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
				});
				return;
			}else{
			$.ajax({
				type:"GET",
				url:path+"/app/updatepwd",
				data:{
					mobile:mobile,
					pyzm:pyzm,
					password1:password1,
					password2:password2,
					yzm:yzm
				},
				datatype:"json",
				success: function(data){
				var result=JSON.parse(data);
				if(result.msg==505){
					layer.tips('您输入的验证码不正确!', '.yzm', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
					}else if(result.msg==401){
					layer.tips('密码长度不得小于6位!', '#password1', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
					}else if(result.msg==404){
					layer.tips('输入的两次密码不一致!', '#password2', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
					}else if(result.msg==-1){
					layer.tips('服务器异常 密码修改失败!', '.OKbtnsf', {
							tips : [ 1, 'red' ], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
						});
					}else{
						window.history.back();location.reload();
						}
					},
				});	
			}
	});	
	});
});