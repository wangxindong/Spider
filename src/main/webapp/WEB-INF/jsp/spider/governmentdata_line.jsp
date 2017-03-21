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
<title>${pd.SYSNAME}</title>
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../system/index/top.jsp"%>
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />
<link rel="stylesheet" href="static/ace/css/webline.css" />
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-header position-relative">
						<table style="width: 10%;">
						<input id="articleid" type = "hidden" value="${pd.id}"/>
						<input id="tablename" name="tablename" type="hidden" value="${pd.tablename}"/>
							<tr>  
								<td style="vertical-align: top;">
										<a style="cursor:pointer;" class="btn btn-default btn-success" onclick="goback('${parameter.begintime}','${parameter.endtime }','${parameter.keyword }', '${parameter.source}', '${parameter.province}','${parameter.type}', '${parameter.is_province }');">返回</a>
								</td>
                                 <td style="vertical-align: top;">
                                 <input id ="releasestatus" type = "hidden" value="${pd.releasestatus}"/>
                                 <button id="butt1" class="btn btn-default btn-success">发布</button>
                                 <button id="butt2" class="btn btn-default btn-success">取消发布</button>
							     </td>
							</tr>
						</table>
					</div>
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-1 column"></div>
						<div class="col-md-10 column">
							<h3 style="text-align: center;">${pd.title}</h3>
							<hr />
							<div id="data">
								<ul>
									<li>日期：${pd.releasedate}</li>
									<li>发布单位：${pd.source}</li>
								</ul>
							</div>
						</div>
						<div class="col-md-1 column"></div>
					</div>
				</div>
				<!-- 正文 -->
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-1 column"></div>
						<div class="col-md-10 column">
							<div class="main-content-inner">${pd.labeltext }</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-1 column"></div>
						<div class="col-md-10 column">
							<div class="main-content-inner">
							 <c:if test="${pd.sourcename != ''} || ${pd.sourcename == null }">
								<p>附件：
									<a href="${pd.sourceUrl }">${pd.sourcename } </a>
								</p>
							</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
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
$(function(){
	var releasestatus = $("#releasestatus").val();
	   if(1 == releasestatus){
	        $('#butt1').hide();
	        $('#butt2').show();
	    }else{
	        $('#butt1').show();
	        $('#butt2').hide();
	    }
});
$('#butt1').click(function (){
    $('#butt1').hide();
    $('#butt2').show();
    updateRecord('1');
});
$('#butt2').click(function (){
    $('#butt2').hide();
    $('#butt1').show();
    updateRecord('0');
});
var quick_type = '${quickParameter.type}';
function updateRecord(releasestatus){
	$.ajax({
	    url :'spider/updateRecord.do',
	    type : 'POST',
	    data : {
	    	releasestatus :releasestatus,
	    	articleid : $('#articleid').val(),
	    	tablename : $('#tablename').val(),
	    	type:  quick_type
	    },
	    timeout : 500000,
	    dataType : 'html',
        success : function(msg) {
        }
	});
} 
</script>
<script type="text/javascript">
	$(top.hangge());
	
	// 返回
	function goback(begintime,endtime,keyword,source,province,type,is_province){
		 var currentPage = "${pd.currentPage}";
		 var showCount = "${pd.showCount}";
		if(province == '' && type == ''){
			 window.location.href='<%=basePath%>spider/index.do?keyword='+keyword+'&begintime='+begintime+'&endtime='+endtime+'&source='+source + '&currentPage=' + currentPage + '&showCount=' + showCount;
		}
		if(is_province == "1"){
			 window.location.href='<%=basePath%>spider/province/list.do?keyword='+keyword+'&begintime='+begintime+'&endtime='+endtime+'&source='+source+'&province='+province  + '&currentPage=' + currentPage + '&showCount=' + showCount;
		}
		// 科普
		if(type == '2'){
			var type_name = "${pd.ny_type_name }";
			var name = "${pd.type_name}";
			window.location.href='<%=basePath%>spider/information/list.do?keyword='+keyword+'&begintime='+begintime+'&endtime='+endtime+'&source='+source+'&type='+type_name + '&type_name=' + type_name + '&ny_type_name=' + type_name + '&currentPage=' + currentPage + '&showCount=' + showCount + '&d_type=2';
		}
		// 新闻
		if(type == '3'){
			var type_name = "${pd.ny_type_name }";
			var name = "${pd.type_name}";
			window.location.href='<%=basePath%>spider/news/list.do?keyword='+keyword+'&begintime='+begintime+'&endtime='+endtime+'&source='+source+'&type='+type_name + '&ny_type_name=' + name + '&type_name=' + type_name  + '&currentPage=' + currentPage + '&showCount=' + showCount + '&d_type=3';
		}
		// 猪
		if(type == '4'){
			window.location.href='<%=basePath%>subject/list.do?keyword='+keyword+'&begintime='+begintime+'&endtime='+endtime+'&source='+source+'&type='+type  + '&currentPage=' + currentPage + '&showCount=' + showCount;
		}
		// 禽业
		if(type == '5'){
			window.location.href='<%=basePath%>subject/list.do?keyword='+keyword+'&begintime='+begintime+'&endtime='+endtime+'&source='+source+'&type='+type  + '&currentPage=' + currentPage + '&showCount=' + showCount;
		}
		// 畜牧
		if(type == '6'){
			window.location.href='<%=basePath%>subject/list.do?keyword='+keyword+'&begintime='+begintime+'&endtime='+endtime+'&source='+source+'&type='+type  + '&currentPage=' + currentPage + '&showCount=' + showCount;
		}
		// 返回快速搜索列表页
		var quick_type = '${quickParameter.type}';
		if(quick_type == '7'){
			var title = '${quickParameter.title}';
	    	var key = '${quickParameter.keyword}';
	    	var web = '${quickParameter.web}';
	    	var begintime = '${quickParameter.begintime}';
	    	var endtime = '${quickParameter.endtime}';
	    	var source = '${source}';
	    	var module = '${quickParameter.module}';
			window.location.href='<%=basePath%>quick/list.do?keyword=' + key+ '&module=' + module +  '&source=' + source +  '&web=' + web + '&currentPage=' + currentPage + '&showCount=' + showCount + '&begintime=' + begintime + '&endtime=' + endtime + '&type=7' + '&title=' + title;
		}
		if(quick_type == '8'){
			var title = '${quickParameter.title}';
	    	var key = '${quickParameter.keyword}';
	    	var web = '${quickParameter.web}';
	    	var begintime = '${quickParameter.begintime}';
	    	var endtime = '${quickParameter.endtime}';
	    	var module = '${quickParameter.module}';
			window.location.href='<%=basePath%>quick/list.do?keyword=' + key+ '&web=' + web + '&module=' + module + '&currentPage=' + currentPage + '&showCount=' + showCount + '&begintime=' + begintime + '&endtime=' + endtime + '&type=8' + '&title=' + title;
		}
	}
</script>
</html>

