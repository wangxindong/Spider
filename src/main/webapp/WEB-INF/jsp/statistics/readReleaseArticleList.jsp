<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>已阅读AND已发布</title>
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../system/index/top.jsp"%>
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />
<script src="plugins/echarts/echarts.min.js"></script>
<style type="text/css">
tr{
	background-color: #FFFFFF;
}
.article-name{
	font-size: 15px;	
}
.article-num{
	color:red;
	font:lighter;
}
#oneDayHourseNum{
	height: 500px;
}
h2{
	font-size: 20px;
	font-weight: normal;
}
.mod{
	width: 98%;
	margin: 10px 10px 10px;
}

.mod .mod-header{
	text-align: left;
	padding:0 23px;
	background-color: #DBDBDB;
	border-radius: 8px 8px 0 0;
}
.mod .mod-header h2{
	margin-top: 10px;
	margin-bottom: 10px;
}
.mod .mod-body{
	border: 1px solid #b4b4b4;
	border-top: 0px none;
	position: relative;
	padding-bottom: 2px;
}
.digest-block {
	width: 25%;
	height: 115px;
	float: left;
	border-right: 1px solid #B4B4B4;
	box-sizing: border-box;
	position: relative;	
}
.digest-block h4 {
    margin-top: 15px;
    font-size: 14px;
    text-align: center;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
.digest-block h1 {
    text-align: center;
    font-size: 32px;
    margin-top: 20px;
}
.digest-block:nth-child(-n+4) {
    border-bottom: 1px solid #B4B4B4;
}
</style>
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<!-- 检索  -->
				<form action="analysis/selectReadAndReleaseArticle.do" method="POST" name="Form" id="Form">
				<input type="hidden" value="0" name="type"/> 
					<div id="app-digest" class="mod mod1">
						<div class="mod-header radius clearfix">
							<h2 >应用概要</h2>
						</div>
					
						<div class="mod-body clearfix">
							<div class="digest-block"><h4>已阅读文章数</h4><h1 style="font-size: 32px;">${readArticleNum }</h1></div>
							<div class="digest-block"><h4>已发布文章数</h4><h1 style="font-size: 32px;">${releaseArticleNum }</h1></div>
							<div class="digest-block"><h4>今日阅读文章数</h4><h1 style="font-size: 32px;">${readArticleTodayNum }</h1></div>
							<div class="digest-block"><h4>今日发布文章数</h4><h1 style="font-size: 32px;">${releaseArticleTodayNum }</h1></div>
							<div class="digest-block"><h4>今天阅读文章数最多用户</h4><h1 style="font-size: 32px;">${readArticleTodayUserAndNum.username }</h1></div>
							<div class="digest-block"><h4>今天阅读文章数最多用户阅读量</h4><h1 style="font-size: 32px;">${readArticleTodayUserAndNum.readnum }</h1></div>
							<div class="digest-block"><h4>平均看文章数,可发布一篇文章</h4><h1 style="font-size: 32px;">${readReleaseArticleConversionRatio }</h1></div>
						</div>
					</div>
					<!-- 图表 -->
					<div class="mod mod1">
						<div class="mod-header radius clearfix">
							<h2>一天内小时记发布趋势-->最优发布时间说明</h2>
						</div>
						<div class="mod-body">
							<div class="content">
								<div class="chartpanel">
									<div id="oneDayHourseNum"></div>
								</div>
							</div>
						</div>
					</div>
					
					
					<!-- 检索  -->
					<table id="simple-table"
						class="table table-striped table-bordered table-hover"
						style="margin-top: 0px;">
						<thead>
							<tr>
								<th class="center" style="width: 50px;">序号</th>
								<th class="center" style="width: 500px;">标题</th>
								<th class="center">最近阅读人</th>
								<th class="center">最近阅读时间</th>
								<th class="center">发布状态</th>
								<th class="center">发布时间</th>
								<th class="center">阅读总数</th>
							</tr>
						</thead>
						<tbody>
							<!-- 开始循环 -->
							<c:choose>
								<c:when test="${not empty readReleaseArticleList }">
									<c:if test="${QX.cha == 1 }">
										<c:forEach items="${readReleaseArticleList }" var="data" varStatus="vs">
											<tr id="data_main">
												<td class='center' >${vs.index+1}</td>
												<td class="center" >${data.title}</td>
												<td class="center" >${data.readUserName }</td>
												<td class="center" >${data.readTime }</td>
												<td class="center" >
												<c:if test="${data.releasestatus == 0 }">未发布</c:if>
												<c:if test="${data.releasestatus == 1 }"><span style="color:red;">已发布</span></c:if></td>
												<td class="center">${data.releaseTime }</td>
												<td class="center" >${data.readNum }</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${QX.cha == 0 }">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center">没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>

					<div class="page-header position-relative">
						<table style="width: 100%;">
							<tr>
								<td style="vertical-align: top;">
								</td>
								<td style="vertical-align: top;">
									<div class="pagination"
										style="float: right; padding-top: 0px; margin-top: 0px;">${page.pageStr}</div>
								</td>
							</tr>
						</table>
					</div>
				</form>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->
		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>	
			
	<!-- /.main-container -->
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../system/index/foot.jsp"%>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
</body>
<script type="text/javascript">
var one_day_hourse_num;

one_day_hourse_num = create('oneDayHourseNum');

function create(id) {
	return echarts.init(document.getElementById(id));
}

$(function(){ 
	$.post('<%=basePath%>statistics/getOneDayHoursNum.do',function(data){
		load_one_day_hourse_num(data);
	});
});


function load_one_day_hourse_num(datas){
	var names = [];
	var values = [];
	$.each(datas, function(i, j) {
		names.unshift(j.name);
		values.unshift(j.value);
	});
	var option={
		loadingOption:{
			text:'数据读取中...',
			x:'center',
			y:'center'
		},
		xAxis: {
			name:'小时',
			data:names
		},
		yAxis: {
			name:'文章数量'
		},
		series:[{
			type:'line',
			data:values,
			symbol:'star',
			markPoint:{
				effect:{
					show:true,
					type:'bounce',
					loop:true,
					period:15
				},
				data:[
					{type: 'max', name: '最大值'},
        			{type: 'min', name: '最小值'}
				]
			},
			markLine:{
				effect:{
					show:true,
					loop:true,
					period:20
				},
				data:[
					{type:'average',name:'平均值'}
				]
			}
		}]
	};
	one_day_hourse_num.setOption(option);
}

$(window).resize(function() {
	one_day_hourse_num.resize();
});
</script>
<script type="text/javascript">
$(top.hangge());
</script>
</html>

