define(function(require,exports,module){

	var Manager = require("./newsManagerModel");
	var manager = new Manager();
	manager._hover(".oneRow",".row-actions");
	manager._page(".pagination");
	manager._select("#date","#class");
	manager._deleteClick(".submitdelete");
	manager._patchDo();
});