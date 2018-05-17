/*通过*/
function ok(obj) {
    swal({
        title: "操作提示",      //弹出框的title
        text: "确定通过吗？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，确定通过！",//确定按钮上面的文档
        closeOnConfirm: true
    }, function () {
        $.ajax({
            type: "post",
            url: "",
            data: { "": JSON.stringify("") },
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
/*拒绝*/
function no(obj) {
    swal({
        title: "操作提示",      //弹出框的title
        text: "确定拒绝吗？",   //弹出框里面的提示文本
        type: "warning",        //弹出框类型
        showCancelButton: true, //是否显示取消按钮
        confirmButtonColor: "#DD6B55",//确定按钮颜色
        cancelButtonText: "取消",//取消按钮文本
        confirmButtonText: "是的，确定拒绝！",//确定按钮上面的文档
        closeOnConfirm: true
    }, function () {
        $.ajax({
            type: "post",
            url: "",
            data: { "": JSON.stringify("") },
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