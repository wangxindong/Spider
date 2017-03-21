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
<link rel="stylesheet" href="static/ace/css/mycss/search.css" />
<script type="text/javascript" src="static/ace/js/jquery.js"></script>
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner" id="body">
				<!-- 检索  -->
				<div id="Form">
					<div>
						<input type="text" id="sear" placeholder="搜索框可为空" />
						<div>
							<input type="button" value="搜索" id="net" />
						</div>
						<div class="clear"></div>
						<div class="tabbable" id="tabs-451217">
							<ul class="nav nav-tabs">
								<li class="active" onclick="getWebName('7')" value="7" id="tabbable_li_id"><a href="#panel-942367"
									data-toggle="tab">政策</a></li>
								<li onclick="getWebName('8')" value="8"><a href="#panel-363906" data-toggle="tab">专题</a></li>
							</ul>

							<div class="tab-content my-tab-content">
								<div class="tab-pane active" id="panel-942367">
									<div>
										<p>时 间:</p>
										<input class="span10 date-picker" name="begintime"
											id="lastLoginStart" value="${quickParameter.begintime}" type="text"
											data-date-format="yyyy-mm-dd" style="width: 88px;"
											placeholder="开始日期" /> ~ 
											<input class="span10 date-picker"
											name="endtime" id="lastLoginStop" value="${quickParameter.endtime}"
											type="text" data-date-format="yyyy-mm-dd"
											style="width: 88px;" placeholder="结束日期" />
									</div>
									<div>
										<p>网 站:</p>
										<div class="web" id="web" value="g">
											<span value="www">农村商机网</span>
										</div>
									</div>
									<div class="clear"></div>
									<div>
										<p>关键词：</p>
										<div class="web" id="key">
											<span>蛋鸡</span> 
											<span class="nones">公告</span>
											<span class="nones">政策</span>
										</div>
									</div>
									<div class="clear"></div>
									<div>
										<p>模 块:</p>
										<div class="web" id="module">
											
										</div>
									</div>
								</div>
								<div class="tab-pane" id="panel-363906">
									<div>
										<p>时 间:</p>
										<input class="span10 date-picker" name="begintime"
											id="lastLoginStart" value="${quickParameter.begintime}" type="text"
											data-date-format="yyyy-mm-dd" style="width: 88px;"
											placeholder="开始日期" /> ~ 
											<input class="span10 date-picker"
											name="endtime" id="lastLoginStop" value="${quickParameter.endtime}"
											type="text" data-date-format="yyyy-mm-dd"
											style="width: 88px;" placeholder="结束日期" />
									</div>
									<div>
										<p>网 站:</p>
										<div class="web" id="web" value="c">
											<span value="www">农村商机网</span>
										</div>
									</div>
									<div class="clear"></div>
									<div>
										<p>关键词:</p>
										<div class="web" id="key">
											<span>蛋鸡</span> 
											<span class="nones">伟嘉集团</span>
										</div>
									</div>
									<div class="clear"></div>
									<div>
										<p>模 块:</p>
										<div class="web" id="module">
											
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
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
	
	function getParameter(){
		var myform = $('form');
		myform.action+="?1";
	}
	
	function loadingData(){
		var title = '${quickParameter.title}';
    	var key = '${quickParameter.keyword}';
		var web = '${quickParameter.web}';
    	var begintime = '${quickParameter.begintime}';
    	var endtime = '${quickParameter.endtime}';
    	var type = '${quickParameter.type}';
    	var module = '${quickParameter.module}';
    	if(type != ''){
    		getWebName(type);
    		default_time_show();
    	}
    	
    	// tab
    	/* if(key != ''){
    		if(key.indexOf(',') != -1){
    			key = key.split(',');
    		} 
    	} */
    	
    	if(title != ''){
    		$('#sear').val(title);
    	}
    	/* if(web != ''){
    		if(web.indexOf(',') != -1){
    			
    		} 
    	} */
    	// 政府
    	if(type == '7'){
    		// tab选中
    		$('.tabbable ul li a[href="#panel-942367"]').tab('show');
    		//web
    		if(web != '') {
    		$.each($('.my-tab-content #panel-942367 #web span'),function(i, myspan){
    			if(web.indexOf(',') == -1){
    				if($(myspan).attr('value') == web){
	    				$(myspan).removeClass('nones');
	    				$(myspan).addClass('actived');
	    			}
    			} else {
    			var webs = web.split(',');
				$.each(webs, function(index, v){
	    			if($(myspan).attr('value') == v){
	    				$(myspan).removeClass('nones');
	    				$(myspan).addClass('actived');
	    			}
				})
    		}
    		});
    		}
    		// 关键词
    		if(key != '') {
    		$.each($('.my-tab-content #panel-942367 #key span'),function(i, myspan){
    	    	if(key.indexOf(',') == -1){
    	    			if($(myspan).text() == key){
    	    				$(myspan).removeClass('nones');
    	    				$(myspan).addClass('actived');
    	    			}
    	    		} else {
    	    	var keys = key.split(',');
				$.each(keys, function(index, value){
	    			if($(myspan).text() == value){
	    				$(myspan).removeClass('nones');
	    				$(myspan).addClass('actived');
	    			}
				}) 
    	    }
    		});
    	}
    		
    		if(module != ''){
    			$.each($('.my-tab-content #panel-942367 #module span'),function(i, myspan){
        	    	if(module.indexOf(',') == -1){
        	    			if($(myspan).text() == module){
        	    				$(myspan).removeClass('nones');
        	    				$(myspan).addClass('actived');
        	    			}
        	    		} else {
        	    	var modules = module.split(',');
    				$.each(modules, function(index, value){
    	    			if($(myspan).text() == value){
    	    				$(myspan).removeClass('nones');
    	    				$(myspan).addClass('actived');
    	    			}
    				}) 
        	    }
        		});
    		}
    	}
    	
    	if(type == '8'){
    		$('.tabbable ul li a[href="#panel-363906"]').tab('show')
    		if(web != '') {
        		$.each($('.my-tab-content #panel-363906 #web span'),function(i, myspan){
        			if(web.indexOf(',') == -1){
        				if($(myspan).attr('value') == web){
    	    				//$(myspan).css('background-color', 'rgb(43, 155, 244)');
    	    				$(myspan).removeClass('nones');
    	    				$(myspan).addClass('actived');
    	    			}
        			} else {
        			var webs = web.split(',');
    				$.each(webs, function(index, v){
    	    			if($(myspan).attr('value') == v){
    	    				//$(myspan).css('background-color', 'rgb(43, 155, 244)');
    	    				$(myspan).removeClass('nones');
    	    				$(myspan).addClass('actived');
    	    			}
    				})
        		}
        		})
        		}
        		// 关键词
        		if(key != '') {
        		$.each($('.my-tab-content #panel-363906 #key span'),function(i, myspan){
        	    	if(key.indexOf(',') == -1){
        	    			if($(myspan).text() == key){
        	    				//$(myspan).css('background-color', 'rgb(43, 155, 244)');
        	    				$(myspan).removeClass('nones');
        	    				$(myspan).addClass('actived');
        	    			}
        	    		} else {
        	    	var keys = key.split(',');
    				$.each(keys, function(index, value){
    	    			if($(myspan).text() == value){
    	    				//$(myspan).css('background-color', 'rgb(43, 155, 244)');
    	    				$(myspan).removeClass('nones');
    	    				$(myspan).addClass('active');
    	    			}
    				}) 
        	    }
        		});
        	}
        		if(module != ''){
        			$.each($('.my-tab-content #panel-363906 #module span'),function(i, myspan){
            	    	if(module.indexOf(',') == -1){
            	    			if($(myspan).text() == key){
            	    				//$(myspan).css('background-color', 'rgb(43, 155, 244)');
            	    				$(myspan).removeClass('nones');
            	    				$(myspan).addClass('actived');
            	    			}
            	    		} else {
            	    	var modules = module.split(',');
        				$.each(modules, function(index, value){
        	    			if($(myspan).text() == value){
        	    				//$(myspan).css('background-color', 'rgb(43, 155, 244)');
        	    				$(myspan).removeClass('nones');
        	    				$(myspan).addClass('actived');
        	    			}
        				}) 
            	    }
            		});
        		}
	}
	}
	
	$(function() {
		// 默认显示数据
		var type = '${quickParameter.type}';
		
		var begin = "${quickParameter.begintime}";
        var end = "${quickParameter.endtime}";
        var oriurl = '${quickParameter.web}';
        if(begin == '' && end == ''){
            $('#lastLoginStart').val(get7FormatDate());
            $('#lastLoginStop').val(getNowFormatDate());
        }
        
		if(type == ''){
			getWebName('7');
    	} else {
    		loadingData();
    		getModule(type, oriurl);
    		chooseOne();
    	}
    	// 默认选中
    	chooseOne();
    	
		$('.date-picker').datepicker({
			autoclose : true,
			todayHighlight : true
		});
		
		default_time_show();
		//下拉框
		if (!ace.vars['touch']) {
			$('.chosen-select').chosen({
				allow_single_deselect : true
			});
			$(window).off('resize.chosen').on('resize.chosen', function() {
				$('.chosen-select').each(function() {
					var $this = $(this);
					$this.next().css({
						'width' : $this.parent().width()
					});
				});
			}).trigger('resize.chosen');
			$(document).on('settings.ace.chosen',
					function(e, event_name, event_val) {
						if (event_name != 'sidebar_collapsed')
							return;
						$('.chosen-select').each(function() {
							var $this = $(this);
							$this.next().css({
								'width' : $this.parent().width()
							});
						});
					});
			$('#chosen-multiple-style .btn').on('click', function(e) {
				var target = $(this).find('input[type=radio]');
				var which = parseInt(target.val());
				if (which == 2)
					$('#form-field-select-4').addClass('tag-input-style');
				else
					$('#form-field-select-4').removeClass('tag-input-style');
			});
		}

	});
	
	// 默认选中
	function chooseOne(){
		var count = 0 ; 				
		$(".my-tab-content #panel-942367 #web span").each(function(index, e){
			if($(e).attr('class') == 'nones'){
				count ++;
			}
			
		});
		if(count == 2){
			$('.my-tab-content #panel-942367 #web span:nth-child(1)').removeClass('nones');
			$('.my-tab-content #panel-942367 #web span:nth-child(1)').addClass('actived');
		}
	}
	
	// 政府模块上面只能选择一个
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
	    return currentdate;
	}
	
	function get7FormatDate(){
		var date = new Date();
		date.setDate(date.getDate() - 7); 
	    var seperator1 = "-";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
	    return currentdate;
	}

	//检索
	function searchs() {
		//top.jzts();
		//getParameter();
		//$("#Form").submit();
		// 获取被选择的tab
		var activeLi = $('#tabs-451217 ul li.active a').attr('href');
		var url = '<%=basePath%>quick/search.do';
		var title = $('#sear').val();
		var lastLoginStart = $(activeLi + ' #lastLoginStart').val();
		var lastLoginStop = $(activeLi + ' #lastLoginStop').val();
		var tmp_type = $('#tabs-451217 ul li.active a').text();
		var type = '';
		if(tmp_type == '政策'){
			type = '7';
		}
		if(tmp_type == '专题'){
			type = '8';
		}
		
		var obj = '';
		$.each($('.my-tab-content ' + activeLi + ' #web span.actived'),function(i, myspan){
			if (i == $('.my-tab-content ' + activeLi + ' #web span.actived').length-1) {
				obj += $(this).attr('value');
			} else {
				obj += $(this).attr('value') + ',';
			}
		});
		
		var keyword = "";
		$.each($(activeLi + ' #key span.actived'),function(i, myspan){
			if (i == $(activeLi + ' #key span.actived').length-1) {
				keyword += $(this).text();
			} else {
				keyword += $(this).text() + ',';
			}
		});
		
		var module = "";
		$.each($(activeLi + ' #module span.actived'),function(i, myspan){
			if (i == $(activeLi + ' #module span.actived').length-1) {
				module += $(this).text();
			} else {
				module += $(this).text() + ',';
			}
		});
		
		
		$.ajax({
			  type: "POST",
			  url: url,
			  data: {
				  	title: title, 
					begintime: lastLoginStart,
					endtime: lastLoginStop, 
					web: obj,
					keyword: keyword,
					type: type,
					module: module
			  },
			  success: function(){
				  window.location.href='<%=basePath%>quick/list.do?title=' + title + '&begintime=' + lastLoginStart + '&endtime=' + lastLoginStop + '&web=' + obj + '&keyword=' + keyword + '&type=' + type + '&module=' + module;
			  },
			  async: false
			});
	}
	 
	function getWebName(search_type) {
		$.ajax({  
	          type: "post",  
	          url: '<%=basePath%>quick/searchtab.do',  
	          data: {search_type: search_type},  
	          async: false,
	          success: function(data){  
	        	var news_html = "";
	  			$.each(data.dataList, function (i ,obj) {
	  				news_html += '<span value="'+obj.oriurl + '"class="nones' + '"> '+obj.oriurlname+'</span>';
	  			});
	  			if(search_type == '7'){
	  				$('#panel-942367 #web').empty().append(news_html);
	  			}
	  			if(search_type == '8'){
	  				$('#panel-363906 #web').empty().append(news_html);
	  			}
	  			var key_html = "";
	  			
	  			$.each(data.keywordList, function (j ,k) {
	  				key_html += '<span class="nones' + '"> '+k.keyword+'</span>';
                });
                if(search_type == '7'){
                    $('#panel-942367 #key').empty().append(key_html);
                }
                if(search_type == '8'){
                    $('#panel-363906 #key').empty().append(key_html);
                }
                
               	if(data.moduleList.length != 0){
                var module_html = "";
                $.each(data.moduleList, function (j ,k) {
                	module_html += '<span class="nones' + '"> '+k.module+'</span>';
                });
                if(search_type == '7'){
                    $('#panel-942367 #module').empty().append(module_html);
                }
                if(search_type == '8'){
                    $('#panel-363906 #module').empty().append(module_html);
                }
               	}
	  			
	        }  
	     }); 
		
		default_time_show(search_type);
	}
	
	function getModule(type, oriurl){
		$.ajax({  
	          type: "post",  
	          url: '<%=basePath%>quick/searchtab.do',  
	          data: {search_type: type, web: oriurl},  
	          async: false,
	          success: function(data){ 
	        	  var module_html = "";
	                $.each(data.moduleList, function (j ,k) {
	                	module_html += '<span class="nones' + '"> '+k.module+'</span>';
	                });
	                if(type == '7'){
	                    $('#panel-942367 #module').empty().append(module_html);
	                }
	                if(type == '8'){
	                    $('#panel-363906 #module').empty().append(module_html);
	                }
	     		}
	      })
	}
	
	$(".my-tab-content #web").on('click', 'span', function(){
		var oriurl = $(this).attr('value');
		var status = $(this).attr('class');
		var type = $('#tabs-451217 ul li.active').attr('value');
		if(type == '7'){
		// 开启
		if(status == 'nones'){
			var obj = '';
			$.each($('.my-tab-content #panel-942367 #web span.actived'),function(i, myspan){
				if (i == $('.my-tab-content #panel-942367 #web span.actived').length-1) {
					obj += $(this).attr('value');
				} else {
					obj += $(this).attr('value') + ',';
				}
			});
			getModule(type, oriurl);
		}
		}
		
		if(type == '8'){
			var obj = '';
				if ($('.my-tab-content #panel-363906 #web span.actived').length == 0) {
					obj = oriurl;
				} else {
					$.each($('.my-tab-content #panel-363906 #web span.actived'), function(b ,q){
						obj += $(this).attr('value') + ',';
					});
					obj += ',' + oriurl;
				}
			getModule(type, obj);
			}
	});
	
	$('#net').bind('click', function(){
		searchs();
	})
	
	/* $(".my-tab-content .web span").on('click', function(){
			if($(this).css("background-color")=="rgb(43, 155, 244)"){
				$(this).css('background-color', '#fff');
				$(this).removeClass('active');
			}else {
				$(this).css('background-color', 'rgb(43, 155, 244)');
				$(this).addClass('active');
			}
		});
	 */
	$(".my-tab-content .web").on('click', 'span', function(){
		// 政府
		var webs = $(this).parent().attr('value');
		var ss = $(this);
		if(webs == 'g'){
			$('.my-tab-content #panel-942367 #web span').each(function (index, e){
				if($(e).attr('class') == 'actived'){
					$(e).removeClass('actived');
					$(e).addClass('nones');
				}
			})
			$(ss).addClass('actived');
			$(this).removeClass('nones');
			
		} else if($(this).attr('class') == "actived"){
			$(this).removeClass('actived');
			$(this).addClass('nones');
		}else {
			$(this).addClass('actived');
			$(this).removeClass('nones');
		}
	});
	 
	// 回车事件
	$(document).keydown(function(event){
	   if(event.keyCode == 13){
		   searchs();
	    }; 
	});
	 
	// 时间空间上默认显示时间
	function default_time_show(type){
        var begin = "${quickParameter.begintime}";
        var end = "${quickParameter.endtime}";
        if(begin == '' && end == ''){
        	if(type == '7'){
            $('#panel-942367 #lastLoginStart').val(get7FormatDate());
            $('#panel-942367 #lastLoginStop').val(getNowFormatDate());
        	}
        	if(type == '8'){
                $('#panel-363906 #lastLoginStart').val(get7FormatDate());
                $('#panel-363906 #lastLoginStop').val(getNowFormatDate());
                }
        }
    }
	
	$('#body #Form>div>input').focus(function(){
		$(this).css('border-color','rgb(43, 155, 244)');
	});
	$('#body #Form>div>input').blur(function(){
		$(this).css('border-color','#c5d0dc');
	});
	
	$('#tabs-451217 ul li#tabbable_li_id a').bind('click', function(){
		
		chooseOne();
	})
	
	
</script>
</html>

