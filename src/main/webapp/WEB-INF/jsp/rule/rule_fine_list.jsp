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
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<!-- 检索  -->
				<form action="rulefine/index.do" method="post" name="Form" id="Form">
					<input type="hidden" name="rulemainid" value="${pd.rulemainid }" />
					<table style="margin-top: 5px;">
						<tr>
							<%-- <td>
								<div class="nav-search">
									<span class="input-icon"> 
									<input autocomplete="off"
										class="nav-search-input" id="nav-search-input" type="text"
										name="keyword" value="${pd.KEY}" placeholder="这里输入关键词" />
									</span>
								</div>
							</td> --%>
							<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="status" id="status" data-placeholder="请选择状态" style="vertical-align:top;width: 120px;">
										<option value="" selected="selected"></option>
										<option value="1" <c:if test="${parameter.status == 1}">selected</c:if>>启用</option>
										<option value="0" <c:if test="${parameter.status == -1}">selected</c:if>>禁用</option>
								  	</select>
								</td>
							<c:if test="${QX.cha == 1 }">
								<td style="vertical-align: top; padding-left: 2px;"><a
									class="btn btn-light btn-xs" onclick="searchs();" title="检索"><i
										id="nav-search-icon"
										class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
							</c:if>
							<c:if test="${QX.toExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td></c:if>
						</tr>
					</table>
					<!-- 检索  -->
					<table id="simple-table"
						class="table table-striped table-bordered table-hover"
						style="margin-top: 0px;">
						<thead>
							<tr>
								<th class="center" style="width: 50px;">序号</th>
								<th class="center" >模块名称</th>
								<th class="center" >模块地址</th>
								<th class="center" >规则名称</th>
								<th class="center" >规则</th>
								<th class="center" >状态</th>
								<th class="center" >操作</th>
							</tr>
						</thead>
						<tbody>
							<!-- 开始循环 -->
							<c:choose>
								<c:when test="${not empty dataList}">
									<c:if test="${QX.cha == 1 }">
										<c:forEach items="${dataList}" var="data" varStatus="vs">
											<tr>
												<td class='center'>${vs.index+1}</td>
												<td class="center">${data.modulename}</td>
												<td class="center">${data.module}</td>
												<td class="center">${data.rulename}</td>
												<td class="center">${data.rule}</td>
												<td class="center">
												<c:if test="${data.status == 1}">启用</c:if>
												<c:if test="${data.status == 0}">禁用</c:if>
												</td>
												<td class="center"><c:if
														test="${QX.edit != 1 && QX.del != 1 }">
														<span
															class="label label-large label-grey arrowed-in-right arrowed-in"><i
															class="ace-icon fa fa-lock" title="无权限"></i></span>
													</c:if> 
													<c:if test="${QX.edit == 1 }">
													<a class="btn btn-xs btn-success" title="编辑" onclick="edit('${data.id}', '${pd.rulemainid }');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
													<c:if test="${QX.del == 1 }">
													<a class="btn btn-xs btn-danger" onclick="del('${data.id }', '${pd.rulemainid}',);">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
													</a>
													</c:if>
													</td>
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
									<c:if test="${QX.add == 1 }">
										<a class="btn btn-mini btn-success" onclick="add('${pd.rulemainid}');">新增</a>
										</c:if>
										<a class="btn btn-mini btn-success" onclick="goback('${pd.rulemainid }','${parameter.keyword}', '${parameter.webtype }' ,'${parameter.status }');">返回</a>
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
    	//下拉框
    	if(!ace.vars['touch']) {
    		$('.chosen-select').chosen({allow_single_deselect:true}); 
    		$(window)
    		.off('resize.chosen')
    		.on('resize.chosen', function() {
    			$('.chosen-select').each(function() {
    				 var $this = $(this);
    				 $this.next().css({'width': $this.parent().width()});
    			});
    		}).trigger('resize.chosen');
    		$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
    			if(event_name != 'sidebar_collapsed') return;
    			$('.chosen-select').each(function() {
    				 var $this = $(this);
    				 $this.next().css({'width': $this.parent().width()});
    			});
    		});
    		$('#chosen-multiple-style .btn').on('click', function(e){
    			var target = $(this).find('input[type=radio]');
    			var which = parseInt(target.val());
    			if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
    			 else $('#form-field-select-4').removeClass('tag-input-style');
    		});
    	}

    	
    	//复选框全选控制
	    var active_class = 'active';
	    $('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
	    		var th_checked = this.checked;//checkbox inside "TH" table header
	    		$(this).closest('table').find('tbody > tr').each(function(){
	    			var row = this;
	    			if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
	    			else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
	    		});
	    	});
    });

    //检索
    function searchs() {
        top.jzts();
        $("#Form").submit();
    }
	
    function add(id){
    	window.location.href = '<%=basePath%>rulefine/gosave.do?rulemainid=' + id;
    }
    //新增
    function add1(id) {
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag = true;
        diag.Title = "新增";
        diag.URL = '<%=basePath%>rulefine/gosave.do?rulemainid=' + id;
        diag.Width = 800;
        diag.Height = 400;
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
    
    function edit(id, rulemainid){
    	var status = $('#status').val();
    	window.location.href = '<%=basePath%>rulefine/goedit.do?id=' + id + '&rulemainid=' + rulemainid + '&status=' + status;
    }
    
    //修改
    function edit1(Id) {
        top.jzts();
        var diag = new top.Dialog();
        diag.Drag = true;
        diag.Title = "编辑";
        diag.URL = '<%=basePath%>rulefine/goedit.do?id=' + Id;
        diag.Width = 800;
        diag.Height = 400;
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
    	var source = $("#source").val();
    	window.location.href='<%=basePath%>rulefine/excel.do?keyword='+keyword+'&begin='+lastLoginStart+'&end='+lastLoginEnd+'&source='+source;
    }
  
	  // 导出一条数据
	  function toOneExcel(id){
		  window.location.href='<%=basePath%>rulefine/excel.do?id=' + id;
	  }
	  // 返回
	  function goback(id,keyword,webtype,status){
		  window.location.href='<%=basePath%>rulemain/index.do?rulemainid=' + id +'&keyword=' + keyword+ '&webtype=' + webtype + '&status=' + status;
	  }
	  
	  function del(id, rulemainid) {
		  bootbox.confirm("确定要删除吗?", function(result) {
			if(result) {
					top.jzts();
		 			 window.location.href='<%=basePath%>rulefine/delete.do?id=' + id + '&rulemainid=' + rulemainid;
		 			 }
	  		});
	  }
	  
	  function del1(id, rulemainid){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = '<%=basePath%>rulefine/delete.do?id=' + id + '&rulemainid=' + rulemainid;
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
</script>
</html>

