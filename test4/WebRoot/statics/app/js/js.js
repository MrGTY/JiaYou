$(function () {
    adddel()
});

//购买
function choose(obj,id,gid) {
    //让所有弟弟的已选隐藏
    $(obj).parents(".bgcolor-f").nextAll("li").find(".choosed").hide();
    //让所有哥哥的已选隐藏
    $(obj).parents(".bgcolor-f").prevAll("li").find(".choosed").hide();
    //让所有弟弟的购买隐藏
    $(obj).parents(".bgcolor-f").nextAll("li").find(".choose").show();
    //让所有哥哥的购买隐藏
    $(obj).parents(".bgcolor-f").prevAll("li").find(".choose").show();
    $(obj).parent().hide();
    $(obj).parent().prev().show();
    //获取到当前选中的商品价格  ￥100/只
    var shop=$(obj).parents(".clearfix").find(".fontcolor2").text();
    var price=shop.substring(1,shop.length-2);
    $("#trueprice").val(price);
    //金额：￥3500.00
    $("#money").text("金额：￥"+price+".00");
    $("#ok").removeAttr("disabled");
    $("#num_input").val("1");
    $("#shopid").val(id);
    $("#gid").val(gid);
}

//已选
function choosed(obj) {
    $(obj).parent().hide();
    $(obj).parent().next().show();
    $("#money").text("金额：￥0.00");
    $("#ok").attr("disabled","true");
    $("#trueprice").val("");
    $("#shopid").val("");
    $("#gid").val("");
}

function adddel() {
    //小计和加减
    //加
    $(".num-jia").each(function () {
        $(this).click(function () {
            var $multi = 0;
            var vall = $(this).prev().val();
            vall++;
            $(this).prev().val(vall);
            var trueprice=$("#trueprice").val();
            trueprice=Number(trueprice)*Number(vall);
            $("#money").text("金额：￥"+trueprice+".00");
        })
    });
    //减
    $(".num-jian").each(function () {
        $(this).click(function () {
            var $multi1 = 0;
            var vall1 = $(this).next().val();
            vall1--;
            if (vall1 <= 0) {
                vall1 = 1;
            }
            $(this).next().val(vall1);
            var trueprice=$("#trueprice").val();
            trueprice=Number(trueprice)*Number(vall1);
            $("#money").text("金额：￥"+trueprice+".00");
        })
    });
}
