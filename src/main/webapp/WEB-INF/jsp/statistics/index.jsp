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
<!-- jsp文件头和头部 -->
<%-- <%@ include file="../system/index/top.jsp"%> --%>

<style type="text/css">
body {
	min-width: 1150px;
}

.main {
	width: 100%;
}

.content {
	margin: 0 3%;
}

#article {
	height: 400px;
	width: 100%;
	float: left;
	border: 1px solid #e4e4e4;
	border-top: 0 none;
}

.box {
	margin: 20px 0 0 0;
	padding: 0;
}

.box_ban {
	width: 48%;
	float: left;
}

.box_ban:NTH-CHILD(2) {
    margin-left: 4%;
}

.box:NTH-LAST-CHILD(1) {
	
}

#userusecount {
	height: 400px;
	width: 100%;
	float: left;
	border: 1px solid #e4e4e4;
	border-top: 0 none;
}

#tagsList {
	position: relative;
	width: 50%;
	height: 400px;
	float: left;
}

#tagsList a {
	position: absolute;
	top: 0px;
	left: 0px;
	font-family: Microsoft YaHei;
	font-weight: bold;
	text-decoration: none;
	padding: 3px 6px;
}

#tagsList a:hover {
	color: #FF0000;
	letter-spacing: 2px;
}

.clearfloat {
	clear: both;
}

#usecountbyhour {
	width: 100%;
	height: 400px;
	border: 1px solid #e4e4e4;
	border-top: 0 none;
}

#releasedatestaticstics {
	height: 400px;
	border: 1px solid #e4e4e4;
	border-top: 0 none;
}

.title {
	height: 50px;
	line-height: 50px;
}

.title_ban {
	width: 100%; 
	height: 50px;
	line-height: 50px;
}

.title>div {
	background-color: #e4e4e4;
	border-radius: 8px 8px 0 0;
}

.title_ban>div {
	background-color: #e4e4e4;
	border-radius: 8px 8px 0 0;
}

.title>div span:NTH-CHILD(1) {
	margin: 0 0 0 20px;
}

.title_ban>div span:NTH-CHILD(1) {
	margin: 0 0 0 20px;
}

.time {
	float: right;
	margin: 0 10px;
}
</style>
</head>
<body>
	<div class="container main">
		<div class="content">
			<div class="box_ban">
				<div class="title_ban">
					<div>
						<span>分类分布top10</span>
					</div>
				</div>
				<div id="article"></div>
			</div>
			<div class="box_ban">
				<div class="title_ban">
					<div>
						<span>抓政府抓取平台人员使用次数</span>
					</div>
				</div>
				<div id="userusecount"></div>
			</div>
			<div class="box">
				<div class="clearfloat"></div>
			</div>
			<div class="box">
				<div class="title">
					<div>
						<span>单位时间使用次数</span>
					</div>
				</div>
				<div id="usecountbyhour"></div>
			</div>
			<div class="box">
				<div class="title">
					<div>
						<span>抓取数据发布时间分布图</span>
						<!-- <span class="time">2222222222</span> 
						<span class="time">2222222222</span> -->
					</div>
				</div>
				<div id="releasedatestaticstics"></div>
			</div>
		</div>
	</div>


</body>
<%@ include file="../system/index/foot.jsp"%>
<script src="plugins/echarts/echarts.min.js"></script>
<script src="plugins/echarts/china.js"></script>
<script type="text/javascript"
	src="static/ace/js/staticstics/mystaticstics.js"></script>
<script type="text/javascript">
	$(top.hangge());
</script>
<!-- 
<script type="text/javascript">
var baseurl = "http://101.201.72.53:8080/yqglht/";
var radius = 120;
var dtr = Math.PI / 180;
var d = 300;
var mcList = [];
var active = true;
var lasta = 1;
var lastb = 1;
var distr = true;
var tspeed = 5;
var size = 250;
var mouseX = 4;
var mouseY = 0;

var howElliptical = 1;

var aA = null;
var oDiv = null;

var ajax = null;

window.onload = function() {
	// 调用ajax获取数据
	getdata();
};

// 填充数据
function putdata() {
	var i = 0;
	var oTag = null;
	oDiv = document.getElementById('tagsList');
	aA = oDiv.getElementsByTagName('a');
	for(i = 0; i < aA.length; i++) {
		oTag = {};

		oTag.offsetWidth = aA[i].offsetWidth;
		oTag.offsetHeight = aA[i].offsetHeight;
		// 放入数据
		mcList.push(oTag);
	}

	sineCosine(0, 0, 0);

	positionAll();

	oDiv.onmouseover = function() {
		active = true;
	};

	oDiv.onmouseout = function() {
		active = false;
	};

	oDiv.onmousemove = function(ev) {
		var oEvent = window.event || ev;

		mouseX = oEvent.clientX - (oDiv.offsetLeft + oDiv.offsetWidth / 2);
		mouseY = oEvent.clientY - (oDiv.offsetTop + oDiv.offsetHeight / 2);
		// 控制旋转速度
		mouseX /= 23;
		mouseY /= 25;
	};
	setInterval(update, 30);
};

function update() {
	var a;
	var b;

	if(active) {
		a = (-Math.min(Math.max(-mouseY, -size), size) / radius) * tspeed;
		b = (Math.min(Math.max(-mouseX, -size), size) / radius) * tspeed;
	} else {
		a = lasta * 1;
		b = lastb * 1;
	}

	lasta = a;
	lastb = b;

	if(Math.abs(a) <= 0.01 && Math.abs(b) <= 0.01) {
		return;
	}

	var c = 0;
	sineCosine(a, b, c);
	for(var j = 0; j < mcList.length; j++) {
		var rx1 = mcList[j].cx;
		var ry1 = mcList[j].cy * ca + mcList[j].cz * (-sa);
		var rz1 = mcList[j].cy * sa + mcList[j].cz * ca;

		var rx2 = rx1 * cb + rz1 * sb;
		var ry2 = ry1;
		var rz2 = rx1 * (-sb) + rz1 * cb;

		var rx3 = rx2 * cc + ry2 * (-sc);
		var ry3 = rx2 * sc + ry2 * cc;
		var rz3 = rz2;

		mcList[j].cx = rx3;
		mcList[j].cy = ry3;
		mcList[j].cz = rz3;

		per = d / (d + rz3);

		mcList[j].x = (howElliptical * rx3 * per) - (howElliptical * 2);
		mcList[j].y = ry3 * per;
		mcList[j].scale = per;
		mcList[j].alpha = per;

		mcList[j].alpha = (mcList[j].alpha - 0.6) * (10 / 6);
	}
	doPosition();
	depthSort();
}

function depthSort() {
	var i = 0;
	var aTmp = [];

	for(i = 0; i < aA.length; i++) {
		aTmp.push(aA[i]);
	}

	aTmp.sort(
		function(vItem1, vItem2) {
			if(vItem1.cz > vItem2.cz) {
				return -1;
			} else if(vItem1.cz < vItem2.cz) {
				return 1;
			} else {
				return 0;
			}
		}
	);

	for(i = 0; i < aTmp.length; i++) {
		aTmp[i].style.zIndex = i;
	}
}

function positionAll() {
	var phi = 0;
	var theta = 0;
	var max = mcList.length;
	var i = 0;

	var aTmp = [];
	var oFragment = document.createDocumentFragment();

	//随机排序
	for(i = 0; i < aA.length; i++) {
		aTmp.push(aA[i]);
	}

	aTmp.sort(
		function() {
			return Math.random() < 0.5 ? 1 : -1;
		}
	);

	for(i = 0; i < aTmp.length; i++) {
		oFragment.appendChild(aTmp[i]);
	}

	oDiv.appendChild(oFragment);

	for(var i = 1; i < max + 1; i++) {
		if(distr) {
			phi = Math.acos(-1 + (2 * i - 1) / max);
			theta = Math.sqrt(max * Math.PI) * phi;
		} else {
			phi = Math.random() * (Math.PI);
			theta = Math.random() * (2 * Math.PI);
		}
		//坐标变换
		mcList[i - 1].cx = radius * Math.cos(theta) * Math.sin(phi);
		mcList[i - 1].cy = radius * Math.sin(theta) * Math.sin(phi);
		mcList[i - 1].cz = radius * Math.cos(phi);

		aA[i - 1].style.left = mcList[i - 1].cx + oDiv.offsetWidth / 2 - mcList[i - 1].offsetWidth / 2 + 'px';
		aA[i - 1].style.top = mcList[i - 1].cy + oDiv.offsetHeight / 2 - mcList[i - 1].offsetHeight / 2 + 'px';
	}
}

function doPosition() {
	var l = oDiv.offsetWidth / 2;
	var t = oDiv.offsetHeight / 2;
	for(var i = 0; i < mcList.length; i++) {
		aA[i].style.left = mcList[i].cx + l - mcList[i].offsetWidth / 2 + 'px';
		aA[i].style.top = mcList[i].cy + t - mcList[i].offsetHeight / 2 + 'px';

		aA[i].style.fontSize = Math.ceil(12 * mcList[i].scale / 2) + 8 + 'px';

		aA[i].style.filter = "alpha(opacity=" + 100 * mcList[i].alpha + ")";
		aA[i].style.opacity = mcList[i].alpha;
	}
}

function sineCosine(a, b, c) {
	sa = Math.sin(a * dtr);
	ca = Math.cos(a * dtr);
	sb = Math.sin(b * dtr);
	cb = Math.cos(b * dtr);
	sc = Math.sin(c * dtr);
	cc = Math.cos(c * dtr);
}

function getdata() {
	var url = baseurl + "keyWordController/getList.do";
	try {
		// Firefox, Opera 8.0+, Safari
		ajax = new XMLHttpRequest();
	} catch(e) {
		// Internet Explorer
		try {
			ajax = new ActiveXObject("Msxml2.XMLHTTP");
		} catch(e) {

			try {
				ajax = new ActiveXObject("Microsoft.XMLHTTP");
			} catch(e) {
				alert("您的浏览器不支持AJAX！");
				return false;
			}
		}
	}
	ajax.open("post", url, true);
	ajax.send();
	ajax.onreadystatechange = function() {
		// 请求成功 
		if(ajax.readyState == 4 && ajax.status == 200) {
			var data = ajax.responseText;
			var jsondata = JSON.parse(data);
			for(var key in jsondata) {
				if(key == "dataSet") {
					for(var value in jsondata[key]) {
						var element = document.createElement('a');
						var node = document.createTextNode(jsondata[key][value].key_name);
						element.appendChild(node);
						var temp = document.getElementById("tagsList");
						temp.appendChild(element);
					}
				}
			}
		}
		putdata();
	}
}
</script>
</html>
-->