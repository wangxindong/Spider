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
<style type="text/css">
#title_td a {
	width: 450px;
    height: 35px;
    overflow: hidden;
    display: block;
    line-height: 35px;
}

#data_main td {
	line-height: 35px;
	height: 35px;
}
.highlight{color:red}
</style>
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<!-- 检索  -->
				<form action="spider/information/list.do" method="POST" name="Form" id="Form">
					<table style="margin-top: 5px;">
						<tr>
							<td>
								<div class="nav-search">
									<span class="input-icon"> 
									<input autocomplete="off"
										class="nav-search-input" id="nav-search-input" type="text"
										name="keyword" value="${pd.keyword}" placeholder="这里输入关键词" />
									</span>
								</div>
							</td>
							<td style="padding-left:2px;">
								<input class="span10 date-picker" name="begintime" id="lastLoginStart"  value="${pd.begintime}" type="text" data-date-format="yyyy-mm-dd" style="width:88px;" placeholder="开始日期" title="最近登录开始"/>
							</td>
							<td style="padding-left:2px;">
								<input class="span10 date-picker" name="endtime" id="lastLoginEnd"  value="${pd.endtime}" type="text" data-date-format="yyyy-mm-dd" style="width:88px;" placeholder="结束日期" title="最近登录结束"/>
							</td>
							<!--来源 -->
							<td style="vertical-align:top;padding-left:2px;width: 120px;">
								 	<select class="chosen-select form-control" name="ny_type_name" id="ny_type_name" data-placeholder="请选择地区" style="vertical-align:top;width: 100%;">
									<option value="">--请选择--</option>
									<c:forEach items="${sourceList}" var="sourceList">
										<option value="${sourceList.source}"<c:if test="${pd.ny_type_name==sourceList.source}">selected</c:if>>${sourceList.source}</option>
									</c:forEach>
								  	</select>
							</td>
							<!-- <td style="vertical-align:top;padding-left:2px;width: 200px; display: none;" >
								 	<select class="chosen-select form-control" name="source" id="source" data-placeholder="请选择来源" style="vertical-align:top;width: 100%;">
								  	<option value="--请选择--" selected="selected">--请选择--</option>
								  	</select>
								</td> -->
							
							<c:if test="${QX.cha == 1 }">
								<td style="vertical-align: top; padding-left: 2px;"><a id="seaId"
									class="btn btn-light btn-xs" onclick="searchs();" title="检索"><i
										id="nav-search-icon"
										class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
							</c:if>
							<c:if test="${QX.toExcel == 1 }">
								<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a>
								</td>
							</c:if>
						</tr>
					</table>
					<!-- 检索  -->
					<table id="simple-table"
						class="table table-striped table-bordered table-hover"
						style="margin-top: 0px;">
						<thead>
							<tr>
								<!-- <th class="center" style="width: 35px;">
									<label><input type="checkbox" id="zcheckbox"
										class="ace" /><span class="lbl"></span></label>
								</th> -->
								<th class="center" style="width: 50px;">序号</th>
								<th class="center" style="width: 500px;">标题</th>
								<th class="center">来源</th>
								<th class="center">发布时间</th>
								<th class="center">类型</th>
								<th class="center">最近查看</th>
								<th class="center">蛋鸡管家是否发布</th>
								<th class="center">操作</th>
							</tr>
						</thead>
						<tbody>
							<!-- 开始循环 -->
							<c:choose>
								<c:when test="${not empty dataList}">
									<c:if test="${QX.cha == 1 }">
										<c:forEach items="${dataList}" var="data" varStatus="vs">
											<tr id="data_main">
												<td class='center' >${vs.index+1}</td>
												<c:choose>
                                                    <c:when test="${data.readstatus == '1' }">
                                                         <td id="title_td" class="left"><a style="color:#FF7F00;cursor:pointer;" onclick="godetail('${data.id}','${pd. begintime}', '${pd.endtime }','${pd.keyword }', '${pd.source }', '${pd.type }','${data.releasestatus}','${data.readstatus}')" >${data.title}</a></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                          <td id="title_td" class="left"><a style="cursor:pointer;" onclick="godetail('${data.id}','${pd. begintime}', '${pd.endtime }','${pd.keyword }', '${pd.source }', '${pd.type }','${data.releasestatus}','${data.readstatus}')">${data.title}</a></td>
                                                    </c:otherwise>
                                                </c:choose>
												<td class="center" >${data.source}</td>
												<td class="center" >${data.releasedate}</td>
												<td class="center" >${data.type}</td>
												<td class="center" >${data.username}</td>
												 <c:choose>
                                                    <c:when test="${data.releasestatus == '1'}">
                                                         <td class="center" >是</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                          <td class="center" >否</td>
                                                    </c:otherwise>
                                                </c:choose>
												<td class="center" ><c:if
														test="${QX.edit != 1 && QX.del != 1 }">
														<span
															class="label label-large label-grey arrowed-in-right arrowed-in"><i
															class="ace-icon fa fa-lock" title="无权限"></i></span>
													</c:if> <c:if test="${QX.edit == 1 }">
														<a style="cursor: pointer;" class="green"
															onclick="toOneExcel('${data.id}');" title="导出到excel">
															<i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i>
														</a>
													</c:if> &nbsp;<%--  <c:if test="${QX.del == 1 }">
														<a style="cursor: pointer;" class="red"
															onclick="del('${var.goods_id}');" title="删除"> <i
															class="ace-icon fa fa-trash-o bigger-130"></i>
														</a> 
													</c:if>--%></td>
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

	$(top.hangge());

    $(function() {
    	//日期框
    	$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
    	
    	<%-- $.getJSON('<%=basePath%>spider/getprovince.do', { province:"${pd.province}" } , function(data){
    		$("#province").empty();
    		$.each(data.pList, function(i,item){
				if(item.source==data.pd.province){
					tmp = "<option value='"+item.source+"' + selected='selected'>"+item.source+"</option>";
				}else{
					tmp = "<option value='"+item.source+"'>"+item.source+"</option>";
				}
				$('#province').append(tmp);
        	});
		}); --%>
   });
    	

    //检索
    function searchs() {
        top.jzts();
        $("#Form").submit();
    }

    //新增
    function add() {
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag = true;
        diag.Title = "新增";
        diag.URL = '<%=basePath%>goods/goadd.do';
        diag.Width = 1200;
        diag.Height = 800;
        diag.CancelEvent = function () { //关闭事件
            if ('${page.currentPage}' == '0') {
                top.jzts();
                setTimeout("self.location=self.location", 100);
            } else {
                nextPage(${page.currentPage});
            }
            diag.close();
        };
        diag.show();
    }
    // 回车事件
    $(document).keydown(function(event){
        if(event.keyCode == 13){ //绑定回车
        $('#seaId').click();
        }
    }); 
    //修改
    function edit(Id) {
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag = true;
        diag.Title = "编辑";
        diag.URL = '<%=basePath%>goods/goedit.do?goods_id=' + Id;
        diag.Width = 1200;
        diag.Height = 800;
        diag.CancelEvent = function () { //关闭事件
            if (diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none') {
                nextPage(${page.currentPage});
            }
            diag.close();
        };
        diag.show();
    }
    
  //导出excel
    function toExcel(){
    	var keyword = $("#nav-search-input").val();
    	var lastLoginStart = $("#lastLoginStart").val();
    	var lastLoginEnd = $("#lastLoginEnd").val();
    	var type = $('#ny_type_name').val();
    	var ny_type_name = "${pd.ny_type_name}";
    	var currentPage = "${page.currentPage}";
		var showCount = "${page.showCount}";
    	// type是标识查询哪个数据表的参数
    	window.location.href='<%=basePath%>spider/excel.do?keyword='+keyword+'&begin='+lastLoginStart+'&end='+lastLoginEnd + '&type=-1' + '&ny_type_name=' + ny_type_name + '&ny_type=' + 2  + '&currentPage=' + currentPage + '&showCount=' + showCount;
    }
  
	  // 导出一条数据
	  function toOneExcel(id){
		  var type = "${pd.ny_type_name}";
		  if(type == ''){
			  type = "-1";
		  }
		  window.location.href='<%=basePath%>spider/excel.do?id=' + id + '&type=' + -1;
	  }
	  
	  // 查看详情
	  function godetail(id,begintime,endtime,keyword,source,type,releasestatus,readstatus){
		  var type_name = "${pd.ny_type_name }";
		  var currentPage = "${page.currentPage}";
		  var showCount = "${page.showCount}";
		  
		  window.location.href='<%=basePath%>spider/showbyid.do?id=' + id + '&begintime=' + begintime + '&endtime=' + endtime + '&keyword=' + keyword + '&source=' + source + '&type=' + type + '&type_name=' + type_name + "&currentPage=" + currentPage + '&showCount=' + showCount + '&releasestatus=' + releasestatus+ '&readstatus=' + readstatus;
	  }
	  
	 	<%--  $('#province').change(function(){
	    	$("#source").empty();
	    	var value = $('#province').val();
	    	$.getJSON('<%=basePath%>spider/getsource.do', {province: value}, function(sourceList){
	    		$.each(sourceList, function(i,item){
					var tmp = "<option value='"+item.source+"'>"+item.source+"</option>";
	    			$('#source').append(tmp);
	        	});
			});
	    });
	 	  --%>
	 	 

</script>
</html>

