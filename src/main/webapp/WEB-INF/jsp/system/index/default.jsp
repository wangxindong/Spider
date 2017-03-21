<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="../index/top.jsp"%>
<!-- 百度echarts -->
<script type="text/javascript" src="static/ace/js/jquery.js"></script>
<script src="plugins/echarts/echarts.min.js"></script>
<script src="plugins/echarts/china.js"></script>
</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="hr hr-18 dotted hr-double"></div>
					<div class="row">
						<div class="col-xs-12">
							<div id="main" style="width: 100%; height: 700px;"></div>
							
							<script type="text/javascript">
							var province = '',
			                	areat = '全国',
			                	mapType = 'china',
			                	myChart = echarts.init(document.getElementById('main'));
							
							// 指定图表的配置项和数据
				         	var option = {
				                title: {
				                    text: '全国地区当日农业数据统计',
				                    left: 'center',
				                    subtext: 'data from 嘉农在线',
				                    sublink: 'http://www.jianong.com/',
				                    top: '20px',
				                },
				                legend: {
							    	orient:'vertical',
					                left: 'right',
					                data: ['当日农业数据数量'],
					            },
					            tooltip : {
					            	showDelay: 0,
					            	show: true,
					            	borderRadius: 10,
					                trigger: 'item'
					            },
					            dataRange: {
					                min: 0,
					                max: 100,
					                x: 'left',
					                y: 'bottom',
					                text:['高','低'],// 文本，默认为数值文本
					                calculable : true
					              },
				                
				                series: [
				                    {
					                    type: 'map',
					                    roam: true,
					                    zoom: '1',
					                    name: '当日农业数据数量',
					                    selectedMode: 'single',  
					                    scaleLimit: {
					                        max: '0',
					                        min: '0'
					                    },
					                    label: {
					                        normal: {
					                            show: true,
					                            textStyle: {
					                                color: '#fff'
					                            }
					                        },
					                        emphasis: {
					                            show: true,
					                            textStyle: {
					                                color: "#fff"
					                            }
					                        }
					                    },
					                    itemStyle: {
					                        normal: {
					                            areaColor: "#009688",
					                            borderColor: "#ccc"
					                        },
					                        emphasis: {
					                            areaColor: "#02796e"
					                        }
					                    },
					                    data: []
				                	}
				                ]
				            };
				         // 使用刚指定的配置项和数据显示图表。
				            setCharts();
				            function setCharts() {
				            	var title = province,
			                    is_provice = false;

			                if (mapType == 'china') {
			                	province = "";
			                    title = '全国';     
			                }else{
			                	province = mapType;
			                    title = mapType;
			                    is_provice = true;  
			                }
			               	myChart.showLoading();
			            	$.post('<%=basePath%>spider/map/list.do',function(e){
			            	 	myChart.hideLoading();
			                	option.series[0].data = e.dataList;
		                        option.series[0].map = mapType;
		                        option.series[0].zoom = 1.5;
			                	myChart.setOption(option);
			                });
				            }
				            
				            var proArr = ['北京','黑龙江','吉林','内蒙古','辽宁','天津','河北','山东','山西','陕西','甘肃','宁夏','新疆','青海','西藏','四川','重庆','河南','安徽','江苏','上海','湖北','湖南','贵州','云南','广西','广东','江西','浙江','福建','台湾','海南','澳门','香港','南海诸岛'];
				            var teArr  = ['北京','上海','天津','重庆'];
				            
				          //点击事件
				            myChart.on('click', function(param) {
				                mapType = param.name;    
				                if(proArr.indexOf(mapType) > -1){
				                	 window.location.href='<%=basePath%>spider/province/list.do?province=' + param.name;
				            	}
				            });
							</script>							
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->


		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$(top.hangge());
	</script>
<script type="text/javascript" src="static/ace/js/jquery.js"></script>
</body>
</html>