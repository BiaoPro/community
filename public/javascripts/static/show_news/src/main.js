define(function(require,exports,module){
	var Manager = require("./newsManagerModel");
	var manager = new Manager();
	manager._hover(".oneRow",".row-actions");
	manager._page(".pagination");
	manager._select("#date","#class");
	manager._deleteClick(".submitdelete");
	manager._patchDo();
	//新添加的行为
	var types = document.getElementById("class");
	var dates = document.getElementById("date");
	var form = document.getElementById("form");
	types.onchange=function(){
		form.submit();
	}
	dates.onchange=function(){
		form.submit();
	}
	
});