//加油
function addoil(obj){
	var _gid=$("#gid").val();//站点id
	var _preAddOil=$("#preAddOil").val();//多少升油
	$.ajax({
		url : 'addoil',
		type : 'get',
		data : {gid : _gid,preAddOil:_preAddOil},
		dataType : 'json',
		success : function(data) {
			if(data.msg==1){//加油成功
				window.location.href="";
			}else if(data.msg==-1){
				//加油失败
			}else if(data.msg==400){
				//系统超时,请稍后重试
			}
		}
	});
}