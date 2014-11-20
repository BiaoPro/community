define(function(require,exports,module){
	
	function Util(){
		
	}
	
	Util.prototype.getQueryString=function(name){
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return (r[2]); return null;
	}
	module.exports=Util;
})