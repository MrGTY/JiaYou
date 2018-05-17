$(function () {
    //pc弹窗
    layui.use('layer', function () {
        $(".usern").blur(function () {
        	var name = $(".usern").val();
            if ($.trim(name) == '') {
                layer.tips('请输入用户名', '.usern', {
                    tips: [1, '#0FA6D8'], //上右下左四个方向，通过1-4进行方向设定,还可配置颜色
                });
                $(".usern").focus();
            }
        });
    });



    //移动弹窗
    //提示
    $("#ifno1").on("click",function () {
        //提示
        layer.open({
            type:'0',
            content: 'hello layer'
            ,skin: 'msg'
            ,time: 2 //2秒后自动关闭
        });
    });


});