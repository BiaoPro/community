define(function(require,exports,module){
	require("jquery");
	var Util = require("Util");
	var util = new Util();
	 var curpage = util.getQueryString('curpage');
	 function NewsManagerModel(){
		 
	 }
	 NewsManagerModel.prototype._hover=function(hoverNode,showNode){
		
		 $(hoverNode).each(function(){
			 this.onmouseover=function(){
				$(this).children().find(showNode).css({visibility:"visible"});
			 }
			 this.onmouseout=function(){
				 $(this).children().find(showNode).css({visibility:"hidden"});
				
			 }
		 });
		 
	 }
	 NewsManagerModel.prototype._page=function(pageContainer){
		 if(curpage==null){
			 curpage=1;
		 }
		 $(pageContainer+" li").eq(curpage).addClass("active");
		 
	 }
	 module.exports=NewsManagerModel;
});