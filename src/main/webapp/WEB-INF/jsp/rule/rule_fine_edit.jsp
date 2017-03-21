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
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../system/index/top.jsp"%>
</head>
<body class="no-skin">
	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-1"></div>
						<div class="col-xs-10">
								<form action="rulefine/${msg }.do" name="userForm" id="userForm" method="post">
									<input name="rulemainid" type="hidden" value="${pd.rulemainid }" />
									<input name="id" type="hidden" value="${pd.id }" />
									<input name="tmpstatus" type="hidden" value="${parameter.status }" />
									<div id="zhongxin" style="padding-top: 13px;">
									<table id="table_report" class="table table-striped table-bordered table-hover">
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">模块特点:</td>
											<td><input type="text" name="module" id="module" value="${pd.module }"  placeholder="这里输入模块特点" title="模块特点" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">模块名称:</td>
											<td><input type="text" name="modulename" id="modulename" value="${pd.modulename }"  placeholder="这里输入模块名称" title="模块名称" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">规则特点:</td>
											<td><input type="text" name="rulestyle" id="rulestyle" value="${pd.rulestyle }"  placeholder="规则特点" title="规则特点" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">规则名称:</td>
											<td><input type="text" name="rulename" id="rulename" value="${pd.rulename }"  placeholder="规则特点" title="规则特点" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">归&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;则:</td>
											<td><input type="text" name="rule" id="rule" value="${pd.rule }"   placeholder="规则" title="规则" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</td>
											<td>
												<select class="chosen-select form-control" name="status" id="status" data-placeholder="请选择状态" style="vertical-align:top;width: 120px;">
													<option value="1" <c:if test="${pd.status == 1}">selected</c:if>>启用</option>
													<option value="0" <c:if test="${pd.status == 0}">selected</c:if>>禁用</option>
										  	</select>
										</tr>
										<tr>
											<td style="text-align: center;" colspan="10">
												<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
												<a class="btn btn-mini btn-danger" onclick="goconcle('${pd.rulemainid }','${parameter.status }');">返回</a>
											</td>
										</tr>
									</table>
									</div>
									<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
								</form>
						</div>
						<div class="col-xs-1"></div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../system/index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- inline scripts related to this page -->
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
</body>
<script type="text/javascript">
	$(top.hangge());
	
	//返回
	function goconcle(id, status){
		window.location.href = '<%=basePath%>rulefine/index.do?rulemainid=' + id + '&status=' + status;
	}
	
	//保存
	function save(){
		if($("#module").val()==""){
			$("#juese").tips({
				side:3,
	            msg:'输入模块特点',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#module").focus();
			return false;
		}
		if($("#modulename").val()==""){
			$("#modulename").tips({
				side:3,
	            msg:'输入模块名称',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#modulename").focus();
			return false;
		}
		if($("#rulestyle").val()==""){
			$("#rulestyle").tips({
				side:3,
	            msg:'输入规则特点',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#rulestyle").focus();
			return false;
		}
		if($("#rulename").val()==""){
			$("#rulename").tips({
				side:3,
	            msg:'输入规则名称',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#rulename").focus();
			return false;
		}
		if($("#rule").val()==""){
			$("#rule").tips({
				side:3,
	            msg:'输入规则',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#rule").focus();
			return false;
		}
		else{
			$("#userForm").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
	}
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
	});
</script>
</html>