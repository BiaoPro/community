define(function(require,exports,module){
	var Manager = require("./newsManagerModel");
	var manager = new Manager();
	manager._hover(".oneRow",".row-actions");
	manager._page(".pagination");
	manager._select("#date","#class");
	manager._deleteClick(".submitdelete");
	manager._patchDo();
	function newWay(){
		
		//新添加的行为
		var types = document.getElementById("class");
		if(types==null)return false;
		var dates = document.getElementById("date");
		if(dates==null)return false;
		var form = document.getElementById("form");
		if(form==null)return false;
		types.onchange=function(){
			form.submit();
		}
		dates.onchange=function(){
			form.submit();
		}
	}
	newWay();
	
});