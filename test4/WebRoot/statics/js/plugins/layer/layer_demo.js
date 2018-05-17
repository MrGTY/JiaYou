//分配财务信息弹出框
function businessbtn() {
    layer.open({
        type: 1,
        title: '分配财务信息',
        area: '600px',
       // closeBtn: [1, false], //去掉默认关闭按钮
        shadeClose: true, //点击遮罩关闭
        shift: 1,
        //maxmin: false,
        content: '<div style="padding:20px 0;"><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">开户银行：</label><div class="col-sm-9"><input type="text" name="" class="form-control" placeholder="请输入文本"> <span class="help-block m-b-none">说明文字</span></div></div><div class="form-group"><label class="col-sm-3 control-label">税务登记：</label><div class="col-sm-9"><input type="text" name="" class="form-control" placeholder="请输入文本"> <span class="help-block m-b-none">说明文字</span></div></div><div class="form-group"><label class="col-sm-3 control-label">账户信息：</label><div class="col-sm-9"><input type="text" name="" class="form-control" placeholder="请输入文本"> <span class="help-block m-b-none">说明文字</span></div></div><div class="form-group"><label class="col-sm-3 control-label">发票抬头：</label><div class="col-sm-9"><input type="text" name="" class="form-control" placeholder="请输入文本"> <span class="help-block m-b-none">说明文字</span></div></div><div class="form-group"><label class="col-sm-3 control-label">纳税人识别号：</label><div class="col-sm-9"><input type="text" name="" class="form-control" placeholder="请输入文本"><span class="help-block m-b-none">说明文字</span></div></div><div class="form-group"><label class="col-sm-3 control-label">账户状态：</label><div class="col-sm-9"><label class="radio-inline"><input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios">正常</label><label class="radio-inline"><input type="radio" value="option2" id="optionsRadios2" name="optionsRadios">关闭</label><label class="radio-inline"><input type="radio" value="option3" id="optionsRadios2" name="optionsRadios">冻结</label></div></div><div class="form-group"><div class="col-sm-9 col-sm-offset-3"><button class="btn btn-primary" type="submit">保存内容</button><button class="btn btn-white" type="submit">取消</button></div></div></div></div>'
		//,success: function(layero, index){
    // console.log(layero, index);
  //}
    });
};



//分配账号弹出层
function Butaccount() {
    var index = layer.open();
	layer.open({
        type: 1,
        title: '分配账号信息',
        area: '600px',
        //shade: [0.5, '#000'],
        shadeClose: false, //点击遮罩关闭
        shift: 1,
        content: '<div style="padding:20px 0;"><div class="col-md-12"><div class="form-group"><label class="col-sm-3 control-label">账号：</label><div class="col-sm-9"><input type="text" name="" class="form-control" placeholder="请输入文本"> <span class="help-block m-b-none">说明文字</span></div></div><div class="form-group"><label class="col-sm-3 control-label">密码：</label><div class="col-sm-9 help-block"><input type="password" class="form-control" name="password" placeholder="请输入密码"></div></div><div class="form-group"><label class="col-sm-3 control-label">确认密码：</label><div class="col-sm-9 help-block"><input type="password" class="form-control" name="password" placeholder="请输入密码"></div></div></div><button class="pagebtn btn btn-w-m btn-warning btn-sm" >保存</button></div>'
		,btn: ['保存', '取消']//保存关闭按钮
		,btn1: function() {
			layer.close(index);
			//layer.closeAll('dialog'); //关闭所有页面层
		}
		,btn2: function(){
            //按钮【按钮二】的回调
        }
		
    });
	

}



!function(){


//hello	
$('#btn_hi').on('click', function(){
    layer.msg('已删除');
});


//弹出alert
$('#about').on('click', function(){
    layer.alert('对该产品取消关注？');
});

//confirm
$('#confirm').on('click', function(){
    layer.confirm('您是如何看待木一商城的？', {
  btn: ['完美','奇葩'] //按钮
}, function(){
  layer.msg('的确很完美', {icon: 1});
}, function(){
  layer.msg('你居然是这么想的！', {
    time: 20000, //20s后自动关闭
    btn: ['明白了', '知道了']
  });
});
});


//自定义内容弹窗
$('#btn_bjtanchu').on('click', function(){
    layer.open({
        type: 1,
		title: '提示文字的标题',
        area: ['500px', '300px'],
		//closeBtn: [0, true], //去掉默认关闭按钮
        shadeClose: false, //点击遮罩关闭
        content: '<div class="tc_ts"><div class="tc_ts_zong"><div class="ttz_cont"><div class="ttz_c_top"><span class="icon_tip tip_sb"></span><div class="ttz_c_wz"><span class="ttz_c_qr">删除地址？</span><span class="ttz_c_xz">您可以选择新增地址，或删除默认地址</span></div></div><div class="ttz_c_bot"><input type="button" class="btn_sc" value="确定" onclick="javascript:;"/><input type="button" class="tz_btn" value="取消" onclick="javascript:;"></div></div></div></div>'
		});
});


//警告
$('#btn_info').on('click', function(){
    layer.open({
        type: 1,
		title: '抱歉！',
        area: ['300px', '150px'],
		shift: 2,
        shadeClose: true, //点击遮罩关闭
        content: '报告，出错啦！不能重复提交~~'
		});
});


//恭喜
$('#btn_success').on('click', function(){
    layer.open({
        type: 1,
		title: '恭喜你！',
        area: ['300px', '150px'],
        shadeClose: true, //点击遮罩关闭
		shift: 1,
        content: '恭喜您！提交成功'
		});
});

//弹出一个iframe层
$('#btn_iframe').on('click', function(){
    layer.open({
        type: 2,
        title: 'iframe父子操作',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['800px' , '520px'],
        content: 'http://www.taobao.com'
		//content: ['http://www.taobao.com', 'no']
    });
});


//弹出一个loading层
$('#btn_loading').on('click', function(){
    var ii = layer.load();
    //此处用setTimeout演示ajax的回调
    setTimeout(function(){
        layer.close(ii);
    }, 1000);
});

//loading
$('#btn_loading2').on('click', function(){
    var index = layer.load(1, {
  shade: [0.1,'#fff'] //0.1透明度的白色背景
});
});


//弹出一个tips层
$('#btn_tip').on('click', function(){
    layer.tips('运费的标准不一样发饭哦按点念佛巴萨地理分布看垃圾时间部分vjlkdsab', '#btn_tip');
});


//小tips
$('#btn_tip2').on('click', function(){
  layer.tips('我是另外一个tips，只不过我长得跟之前那位稍有些不一样。', '#btn_tip2', {
       tips: [1, '#3595CC'],
       time: 4000
  });
});


//prompt层
$('#prompt').on('click', function(){
layer.prompt({
  title: '输入任何口令，并确认',
  formType: 1 //prompt风格，支持0-2
}, function(pass){
  layer.prompt({title: '随便写点啥，并确认', formType: 2}, function(text){
    layer.msg('演示完毕！您的口令：'+ pass +' 您最后写下了：'+ text);
  });
});
});


//tab层
$('#tab').on('click', function(){
layer.tab({
  area: ['600px', '300px'],
  tab: [{
    title: 'TAB1', 
    content: '内容1'
  }, {
    title: 'TAB2', 
    content: '内容2'
  }, {
    title: 'TAB3', 
    content: '内容3'
  }]
});
});




}();