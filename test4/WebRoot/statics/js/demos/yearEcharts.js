$(function () {
	
	var myChart = echarts.init(document.getElementById('conta'));
	var myDate = new Date();
	var month=myDate.getYear(); //获取当前月份(0-11,0代表1月)
	var year = myDate.getFullYear();       //年  
	var da=[];//本月天数=
	var list3=[];//燃油总收油量(L)=
	var list5=[];//燃油总提取量(L)=
	var list11=[];//燃油交易量(次)=
	var list2=[];//轮胎总交易额 (元)=
	var list7=[];//轮胎总提取量(元)=
	var list13=[];//轮胎交易量(次)=
	var list9=[];//新增会员(个)
	$.get('year').done(function (data) {
		var a=JSON.parse(data);
		$.each(a.summaryVo.yearOilClosedTotal,function(i,item){
			 da.push(year+"年"+(i+1)+"月");
			 list3.push(item.income);
			 list11.push(item.id);
		});
		$.each(a.summaryVo.yearOilextractTotal,function(i,item){
			list2.push(item.income);
			list13.push(item.id);
		});
		$.each(a.summaryVo.yearOilTradingCount,function(i,item){
			list5.push(item.amountDrawn);
		});
		$.each(a.summaryVo.yeartireCount,function(i,item){
			list7.push(item.amountDrawn);
		});
		$.each(a.summaryVo.yeartireextractCount,function(i,item){
			list9.push(item.id);
		});
	    myChart.setOption({
	    	title: {
		        text: year+'年交易统计'
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		   	    legend: {
		    	left:'20%',	 
		    						//list3   		list5  			list11		list2		list7		list13		list9
		    	data:['\t\t\t    ','燃油总收油量(L)','燃油总提取量(L)','燃油交易量(次)','轮胎总交易额 (元)','\n','轮胎总提取量(元)','轮胎交易量(次)','新增会员(个)']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: da
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [
		        {
		            name:'燃油总收油量(L)', 
		            type:'line',
		            stack: '总量',
		            data:list3
		        },
		        {
		            name:'燃油总提取量(L)',
		            type:'line',
		            stack: '总量',  
		            data:list5
		        },
		        {
		            name:'燃油交易量(次)',
		            type:'line',
		            stack: '总量',  
		            data:list11
		        },
		        {
		            name:'轮胎总交易额 (元)',
		            type:'line',   
		            stack: '总量',
		            data:list2
		        },
		        {
		            name:'轮胎总提取量(元)',
		            type:'line',
		            stack: '总量',
		            data:list7
		        }
		        ,
		        {
		            name:'轮胎交易量(次)',
		            type:'line',
		            stack: '总量',
		            data:list13
		        }
		        ,
		        {
		            name:'新增会员(个)',
		            type:'line',
		            stack: '总量',
		            data:list9
		        }
		    ]
	    });
	});
});