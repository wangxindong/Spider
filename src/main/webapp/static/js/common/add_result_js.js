function add_result_js_success(){
	if(dg.curWin.document.forms[0]){
		dg.curWin.document.forms[0].action = dg.curWin.location+"";
		dg.curWin.document.forms[0].submit();
	}else{
		dg.curWin.location.reload();
	}
	dg.cancel();
}

function add_result_js_failed(){
	alert("新增失败！");
}

function post(url, params) {
    var temp = document.createElement("form");
    temp.action = url;
    temp.method = "post";
    temp.style.display = "none";
    for (var x in params) {
        var opt = document.createElement("input");
        opt.name = x;
        opt.value = params[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}