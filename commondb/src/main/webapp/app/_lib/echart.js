function chartsTool(myChart,chartName,arryX,arryY){
		option = {
			    title : {
			        text: chartName,
			        subtext: ''
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:[]
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : arryX,
			            interval:1 
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            name : '时间/h',
			            axisLabel : {
	                         formatter: '{value}'
	                     },
			            splitArea : {show : true},
			            min  : 0,
			            max  : 2000
			        }
			    ],
			    series : [
			        {
			            name:'时间/h',
			            type:'bar',
			            data:arryY,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },
			            markLine : {
			                data : [
			                    {type : 'average', name: '平均值'}
			                ]
			            }
			        }
			    ]
			};
		myChart.setOption(option);
	}