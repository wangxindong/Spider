var locats = (window.location+'').split('/'); 
$(function(){
	locats =  locats[0]+'//'+locats[2]+'/'+locats[3];
	locats = locats + '/statistics/';
});

var article_charts;
var user_use_count_charts;
var use_count_byhour_charts;
var releasedates_charts;

article_charts = create('article');
user_use_count_charts = create('userusecount');
use_count_byhour_charts = create('usecountbyhour');
releasedates_charts = create('releasedatestaticstics');

function create(id) {
	return echarts.init(document.getElementById(id));
}

function load_use_count_byhour_charts(data) {
	var timedata = [];
	$.each(data, function(i, j) {
		timedata.push(j.name);
	});
	var option = {
		/*title : {
			text : '单位时间使用次数',
			left : 'center'
		},*/
		legend: {
			show: true,
			data:['访问次数'],
	        x: 'left'
	    },
	    grid:{
            x: 60,
            y: 40,
            x2: 60,
            y2: 40
        },
		tooltip : {
			show: true,
			trigger : 'axis',
			axisPointer : {
				animation : false
			},
			showDelay: 0,
            hideDelay: 0,
            transitionDuration: 0,
            backgroundColor: 'rgba(0,0,0,0.7)',
            borderRadius: 8,
            // 格式化显示
            formatter: function (params, ticket, callback) {
                var res = "时间" + ' : ' + new Date().getDate() + "日" +　params[0].name + "时<br/>";
                for (var i = 0, l = params.length; i < l; i++) {
                    res += '<br/>' + params[i].seriesName + ' : ' + params[i].value;
                };
                setTimeout(function () {
                    callback(ticket, res);
                }, 1000);
                return 'loading...';
            }
		},
		xAxis: [{
			type : 'category',
			boundaryGap : false,
			axisLine : {
				onZero : true
			},
			data : timedata
		}],
		yAxis : {
			name: "访问次数",
			type : 'value'
			/*boundaryGap : [ 0, '100%' ],
			splitLine : {
				show : true
			}*/
		},
		series : [ {
			name : '每小时使用量',
			type : 'line',
			showSymbol : false,
			hoverAnimation : false,
			width : "100%",
			data : data
		} ]
	};
	use_count_byhour_charts.setOption(option);
}

function getDataByHour() {
	$.post(locats + 'getByHourStatistics.do', function(data) {
		load_use_count_byhour_charts(data);
	})
}

function load_user_usecount_charts(datas) {
	var names = [];
	var values = [];
	$.each(datas, function(i, j) {
		names.unshift(j.name);
		values.unshift(j.value);
	})
	var option = {
		/*title : {
			text : '政府抓取平台人员使用次数',
			x : 'center',
			y : 10
		},*/
		tooltip : {
			trigger : 'item',
			axisPointer : {
				animation: true
			}
		},
		legend : {
			orient : 'vertical',
			left : 'left',
			data : names
		},
		series : [ {
			name : '用户',
			type : 'pie',
			roseType : 'angle',
			radius : '55%',
			center : [ '50%', '60%' ],
			data : datas,
			itemStyle : {
				normal : {
					// 阴影的大小
					shadowBlur : 200,
					// 阴影水平方向上的偏移
					shadowOffsetX : 0,
					// 阴影垂直方向上的偏移
					shadowOffsetY : 0,
					// 阴影颜色
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			}
		} ]
	};
	user_use_count_charts.setOption(option);
}

function load_article_charts(datas) {
	var names = [];
	var values = [];
	$.each(datas, function(i, j) {
		names.unshift(j.name);
		values.unshift(j.value);
	})
	var option = {
		/*title : {
			text : '分类分布top10',
			x : 'center',
			y : 10,
			textStyle : {
				fontSize : 18,
				fontWeight : 'bolder',
				color : '#333'
			},
		},*/
		legend : {
			orient : 'vertical',
			left : 'left',
			data : ['农业部网站','其他']
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'shadow',
				animation: true
			}
		},
		grid : {
			x : '2%',
			y : '7%',
			x1: '5%',
			y2 : '3%',
			containLabel : true
		},
		xAxis : {
			name: '数量',
			type : 'value',
			boundaryGap : [ 0, 0.01 ],
			"axisLabel" : {
				"interval" : 0
			}
		},
		yAxis : {
			name: '分类',
			type : 'category',
			data : names
		},
		series : [ {
			name : '分类分布数量',
			type : 'bar',
			data : values
		} ]
	};
	article_charts.setOption(option);
}


function load_releasedates_charts(data) {
	var timedata = [];
	var datas = [];
	$.each(data.crawl_data, function(i, j) {
		timedata.unshift(j.name);
		datas.unshift(j.value);
	});
	
	var timedata_g = [];
	var datas_g = [];
	$.each(data.government_data, function(i, j) {
		timedata_g.unshift(j.name);
		datas_g.unshift(j.value);
	});
	
	
	var option = {
		/*title: {
			text: '抓取数据发布时间分布图',
			left: 'center',
			x: '-100px'
		},*/
		/*legend: {
			show: true,
			data: ['数量'],
			x: 'left'
		},*/
		grid:{
            x: 60,
            y: 40,
            x2: 60,
            y2: 40
        },
		tooltip: {
			show: true,
			trigger: 'axis',
			axisPointer: {
				animation: false
			},
			hideDelay: 0,
			transitionDuration: 0,
			backgroundColor: 'rgba(0,0,0,0.7)',
			borderRadius: 8,
			// 格式化显示
			formatter: function(params, ticket, callback) {
				var res = "时间" + ' : ' + new Date().getFullYear() + '-' + params[0].name + "<br/>";
				for(var i = 0, l = params.length; i < l; i++) {
					res += '<br/>' + params[i].seriesName + ' : ' + params[i].value + '个';
				};
				setTimeout(function() {
					callback(ticket, res);
				}, 1000);
				return 'loading...';
			}
		},
		xAxis: [{
			name: '时间',
			type: 'category',
			boundaryGap: false,
			axisLine: {
				onZero: true
			},
			data: timedata
		}],
		yAxis: [{
			name: '数量',
			x: 'center',
			type: 'value',
			splitLine: {
				show: true
			}
		}],
		series: [
			{
				name: '畜牧业网站抓取数量',
				type: 'line',
				showSymbol: false,
				hoverAnimation: false,
				data: datas
			},{
					name: '农业部网站抓取数量',
					type: 'line',
					showSymbol: false,
					hoverAnimation: false,
					data: datas_g
		}]
	};
	releasedates_charts.setOption(option);
}

/**
 * 请求数据
 * @returns
 */
function getData(){
	$.post(locats + 'getArticleTypeStatistics.do', function(data) {
		load_article_charts(data.article);
		load_user_usecount_charts(data.userUseCount);
	});

	$.post(locats + 'getDataCountByReleasedateStaticstics.do', function(data) {
		load_releasedates_charts(data);
	});
}

/**
 * 页面在家完成统一回去数据
 */
$(function() { 
	getData();
	getDataByHour();
});

setInterval(getDataByHour, 1000 * 60 * 60);

$(window).resize(function() {
	article_charts.resize();
	user_use_count_charts.resize();
	use_count_byhour_charts.resize();
	releasedates_charts.resize();
});

