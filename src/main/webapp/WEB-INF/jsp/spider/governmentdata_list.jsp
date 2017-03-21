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
				<form action="spider/index.do" method="POST" name="Form" id="Form">
				<input type="hidden" value="0" name="type"/> 
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
							<!-- 局选择框 -->
							<td style="vertical-align:top;padding-left:2px;width: 300px;">
								 	<select class="chosen-select form-control" name="source" id="source" data-placeholder="请选择来源" style="vertical-align:top;width: 120px;">
									<option value=""></option>
									<c:forEach items="${sourceList}" var="sourceList">
										<option value="${sourceList.source}"<c:if test="${pd.source==sourceList.source}">selected</c:if>>${sourceList.source}</option>
									</c:forEach>
								  	</select>
								</td>
							<c:if test="${QX.cha == 1 }">
								<td style="vertical-align: top; padding-left: 2px;"><a id="seaId"
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
								<!-- <th class="center" style="width: 35px;">
									<label><input type="checkbox" id="zcheckbox"
										class="ace" /><span class="lbl"></span></label>
								</th> -->
								<th class="center" style="width: 50px;">序号</th>
								<th class="center" style="width: 500px;">标题</th>
								<th class="center">来源</th>
								<th class="center">发布时间</th>
								<th class="center">隶属部门</th>
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
                                                    <c:when test="${data.readstatus == '1'}">
											             <td id="title_td" class="left">
											             <a style="color:#FF7F00;cursor:pointer;" 
											             onclick="godetail('${data.id}','${pd. begintime}','${pd.endtime }','${pd.keyword }','${pd.source }','${data.releasestatus}','${data.readstatus}')">${data.title}</a>
											             </td>
											        </c:when>
					                                <c:otherwise>
					                                      <td id="title_td" class="left"><a style="cursor:pointer;" onclick="godetail('${data.id}','${pd. begintime}', '${pd.endtime }','${pd.keyword }','${pd.source }','${data.releasestatus}','${data.readstatus}')">${data.title}</a></td>
					                                </c:otherwise>
					                            </c:choose>
												<td class="center" >${data.source}</td>
												<td class="center" >${data.releasedate}</td>
												<td class="center" >${data.webname}</td>
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
    // 回车事件
    $(document).keydown(function(event){
    	if(event.keyCode == 13){ //绑定回车
    	$('#seaId').click();
    	}
    }); 
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
    	var source = $("#source").val();
    	var type = '0';
    	var currentPage = "${page.currentPage}";
		var showCount = "${page.showCount}";
    	window.location.href='<%=basePath%>spider/excel.do?keyword='+keyword+'&begin='+lastLoginStart+'&end='+lastLoginEnd+'&source='+source + '&type=' + type + '&currentPage=' + currentPage + '&showCount=' + showCount;
    }
  
	  // 导出一条数据
	  function toOneExcel(id){
		  window.location.href='<%=basePath%>spider/excel.do?id=' + id;
	  }
	  
	  // 查看详情
	  function godetail(id,begintime,endtime,keyword,source,releasestatus,readstatus){
		  var currentPage = "${page.currentPage}";
		  var showCount = "${page.showCount}";
		  window.location.href='<%=basePath%>spider/showbyid.do?id=' + id + '&begintime=' + begintime + '&endtime=' + endtime + '&keyword=' + keyword + '&source=' + source + '&currentPage=' + currentPage + '&showCount=' + showCount+ '&releasestatus=' + releasestatus+ '&readstatus=' + readstatus;
	  }
    
    // 导出选中的数据
    function makeAll(msg){
    	bootbox.confirm(msg, function(result) {
    		if(result){
    			var str = '';
    			for(var i = 0;i < document.getElementsByName('ids').length;i++) {
    				if(document.getElementsByName('ids')[i].checked){
    				  	if(str=='') {
    				  		str += document.getElementsByName('ids')[i].value;
    				  	}else {
    				  		str += ',' + document.getElementsByName('ids')[i].value;
    				  	}
    				  }
    			}
    			if(str==''){
    				bootbox.dialog({
    					message: "<span class='bigger-110'>您没有选择任何内容!</span>",
    					buttons: 			
    					{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
    				});
    				$("#zcheckbox").tips({
    					side:3,
    		            msg:'点这里全选',
    		            bg:'#AE81FF',
    		            time:8
    		        });
    				return;
    			}else{
    				if(msg == '确定要导出中的数据吗?'){
    					top.jzts();
    					$.ajax({
    						type: "POST",
    						url: '<%=basePath%>spider/excel.do?',
    				    	data: {ids:str},
    						dataType:'json',
    						cache: false,
    						success: function(data){
    							 $.each(data.list, function(i, list){
    									nextPage(${page.currentPage});
    							 });
    						}
    					});
    			}
    		}
    	}
    });
 }
</script>
</html>

