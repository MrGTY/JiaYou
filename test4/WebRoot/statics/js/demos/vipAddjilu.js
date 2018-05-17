/*tab选项卡切换*/
$(function () {
    $("#jilu div:lt(3)").on("click", function () {
        var num = $(this).index();
        $("#showORhidden").children("div:not(:first-child)").slideUp("slow");
        $("#showORhidden").children("div:not(:first-child)").eq(num).slideDown("slow");
    });
    /*点击搜索显示div列表*/
    /*分拨会员*/
    $("#searchshow").click(function() {
		$.ajax({
			type : "GET",
			url : "getvip.json",
			data : {
				name : $("#vipvalue1").val()
			},
			dataType : "json",
			success : function(data) {
				$("#vipname1").children().detach();
				var str=null;
				$(data).each(function(){
					str="<div onclick='getword(this)'>"+this.phoneNum+"</div>";
					$("#vipname1").append(str);
					$("#papa").slideDown("faster");
				})
			},
			error : function(data) {
				alter("查找有误");
			}
		});
	});
    /*收油会员*/
    $("#searchshow2").click(function() {
		$.ajax({
			type : "GET",
			url : "getvip.json",
			data : {
				name : $("#vipvalue2").val()
			},
			dataType : "json",
			success : function(data) {
				$("#vipname2").children().detach();
				var str=null;
				$(data).each(function(){
					str="<div onclick='getword2(this)'>"+this.phoneNum+"</div>";
					$("#vipname2").append(str);
					$("#papa2").slideDown("faster");
				})
			},
			error : function(data) {
				alter("查找有误");
			}
		});
	});
    document.onclick = function(){
        $("#papa").slideUp("faster");
        $("#papa2").slideUp("faster");
    };
    $("#fenpeisave").click(function(){
    	var allocation=document.getElementById("allocation");
		if (allocation.validity.valueMissing == true) {
			allocation.setCustomValidity("请输入油量");
		} else if (isNaN(allocation.value)) {
			allocation.setCustomValidity("请输入数字");
		}else if(allocation.value<=0){
			allocation.setCustomValidity("请输入大于0的数字");
		} else {
			allocation.setCustomValidity("");
		}
    })
});

/*点击div列表获取值，放在表单里，并关闭div列表*/

function getword(obj) {
    var word=$(obj).text();
    $(".fa_jilu12").children("input").val(word);
    /*关闭列表*/
    $(obj).parent().parent().slideUp("faster");
}
/*点击div列表获取值，放在表单里，并关闭div列表*/
function getword2(obj) {
    var word=$(obj).text();
    $(".fa_jilu122").children("input").val(word);
    /*关闭列表*/
    $(obj).parent().parent().slideUp("faster");
}





