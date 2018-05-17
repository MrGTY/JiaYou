$(function () {
	
	var myChart = echarts.init(document.getElementById('containerYear'));
	var myDate = new Date();
	var month=myDate.getYear(); //获取当前月份(0-11,0代表1月)
	var year = myDate.getFullYear();       //年  
	var da=[];//本月天数
	var oilder6=[];//燃油总收油量(L)
	var oilder7=[];//燃油总提取量(L)
	var oilder8=[];//燃油交易量(次)
	$.get('oilrecords2').done(function (data) {
		console.log(data);
		var a=JSON.parse(data);
		var unitConversion=a.sumVo.paramatersetting.unitConversion;
		$.each(a.sumVo.oilrecords2,function(i,item){
			da.push(i+1+"月");
			oilder6.push(item.income);
			oilder8.push(item.id);
		});
		$.each(a.sumVo.applies2,function(i,item){
			oilder7.push(item.amountDrawn*unitConversion);
		});
	    myChart.setOption({
	    	title: {
		        text: year+'年交易统计'
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {//燃油总收油量(L)  //燃油总提取量(L)  //燃油交易量(次)			
		        data:['燃油总收油量(L)','燃油总提取量(L)','燃油交易量(次)']
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
		            data:oilder6
		        },
		        {
		            name:'燃油总提取量(L)',
		            type:'line',
		            stack: '总量',  
		            data:oilder7
		        },
		        {
		            name:'燃油交易量(次)',
		            type:'line',
		            stack: '总量',  
		            data:oilder8
		        }
		    ]
	    });
	});
});