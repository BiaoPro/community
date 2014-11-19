define(function(require,exports,module){
	require("jquery");
	var Util = require("Util");
	var util = new Util();
	var curpage = util.getQueryString('curpage');
	var newsCreateDate=util.getQueryString('newsCreateDate');
	var newClassType=decodeURI(util.getQueryString('newClassType'));
	 function NewsManagerModel(){
		 
	 }
	 //用于表格的所有效果
	 NewsManagerModel.prototype._hover=function(hoverNode,showNode){
		//用于鼠标飘在行上面的效果
		 $(hoverNode).each(function(){
			 this.onmouseover=function(){
				$(this).children().find(showNode).css({visibility:"visible"});
			 }
			 this.onmouseout=function(){
				 $(this).children().find(showNode).css({visibility:"hidden"});
				
			 }
		 });
		 //用于全选行为
		 $("#selectAll").click(function(){
			if(this.checked==true){
				 $(".selectAll_res").attr("checked","true");
				 
			 }
			 else{
				 $(".selectAll_res").each(function(){
					this.checked=false; 
				 });
			 }
		 });
		 
		 //用于审核
		 $(".audit").each(function(){
			 if($(this).attr("data")==0){
				 $(this).text("未审核");
				 $(this).addClass("btn-danger");
			 }
			 else{
				 $(this).text("已审核");
				 $(this).addClass("btn-primary");
				 $(this).mouseover(function(){
					 $(this).text("取消通过");
				 });
				 $(this).mouseout(function(){
					 $(this).text("已审核");
				 })
			 }
		 })
		 
	 }
	 //用于分页效果
	 NewsManagerModel.prototype._page=function(pageContainer){
		 if(curpage==null){
			 curpage=1;
		 }
		 $(pageContainer+" li").eq(curpage).addClass("active");
		 
	 }
	 //用于select定位到刚才选的日期和栏目
	 NewsManagerModel.prototype._select=function(select1,select2){
		 if(newsCreateDate!=null&&newsCreateDate.length!=0)
		 $(select1+" option").each(function(){
			 if(this.value.indexOf(newsCreateDate)!=-1){
				 $(this).attr("selected","selected");
			 }
		 });
		 
		 if(newClassType!=null&&newClassType.length!=0)
		 $(select2+" option").each(function(){
			 if(this.value.indexOf(newClassType)!=-1){
				 $(this).attr("selected","selected");
			 }
		 });
	 }
	 
	 //用于删除按钮的点击行为
	 NewsManagerModel.prototype._deleteClick=function(deleteClickClass){
		 $(deleteClickClass).each(function(){
			 this.onclick=function(){
				 return confirm("删除之后不能恢复，确定要删除？");
			 }
		 })
	 }
	 
	 //用于批量操作的行为
	 NewsManagerModel.prototype._patchDo=function(){
		 $("#patchSelect").change(function(){
			var value = $(this).children("option[selected='selected']").val();
			$("#patchFormInput").val(value);
		 });
		 $("#patchSubmit").click(function(){
			 var typeStr = $("#patchFormInput").val();
			 if(typeStr.length!=0){
				 if(confirm("可能此操作会带来一定的风险，确定要进行批量处理？"))
					 $("#patchForm").submit();
			 }
		 });
	 }
	 
	 
	 module.exports=NewsManagerModel;
});