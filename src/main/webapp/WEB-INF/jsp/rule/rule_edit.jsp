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
						<div class="col-xs-12">
								<form action="rulemain/${msg }.do" name="userForm" id="userForm" method="post">
									<input type="hidden" name="id" value="${pd.id }" />
									<div id="zhongxin" style="padding-top: 13px;">
									<table id="table_report" class="table table-striped table-bordered table-hover">
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">网站名称:</td>
											<td><input type="text" name="webname" id="webname" value="${pd.webname }"  placeholder="这里输入网站名称" title="网站名称" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">种子url:</td>
											<td><input type="text" name="oriurl" id="oriurl" value="${pd.oriurl }"   placeholder="输入种子url" title="种子url" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">网站类别:</td>
											<td><input type="text" name="webtype" id="webtype" value="${pd.webtype }"   placeholder="网站类别" title="网站类别" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">表达式:</td>
											<td><input type="text" name="weburl" id="weburl" value="${pd.weburl }"  placeholder="这里输入匹配表达式" title="网站正则" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">状态:</td>
											<td>
												<select class="chosen-select form-control" name="status" id="status" data-placeholder="请选择状态" style="vertical-align:top;width: 120px;">
													<option value="1" <c:if test='${pd.status==1 }'>selected</c:if>>启用</option>
													<option value="0" <c:if test='${pd.status==0 }'>selected</c:if>>禁用</option>
										  	</select>
										</tr>
										<tr>
											<td style="text-align: center;" colspan="10">
												<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
												<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
											</td>
										</tr>
									</table>
									</div>
									<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
								</form>
						</div>
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
	//保存
	function save(){
		if($("#weburl").val()==""){
			$("#juese").tips({
				side:3,
	            msg:'输入网站地址',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#weburl").focus();
			return false;
		}
		if($("#webname").val()==""){
			$("#webname").tips({
				side:3,
	            msg:'输入网站名称',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#webname").focus();
			return false;
		}
		if($("#oriurl").val()==""){
			$("#oriurl").tips({
				side:3,
	            msg:'输入种子',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#oriurl").focus();
			return false;
		}
		if($("#webtype").val()==""){
			$("#webtype").tips({
				side:3,
	            msg:'输入网站类型',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#webtype").focus();
			return false;
		}
		if($("#weburl").val()==""){
			$("#weburl").tips({
				side:3,
	            msg:'输入网站匹配表达式',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#weburl").focus();
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