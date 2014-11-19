define(function(require,exports,module){
	require("jquery");
	function Alert(){
		
	}
	Alert.prototype._init=function(node){
		
		var value = $(node).val();
		if(value!=null){
			if(value.indexOf("modify")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("恭喜！修改成功!");
				
			}
			if(value.indexOf("delete")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("恭喜！删除成功！");
				
			}
			if(value.indexOf("addRepeat")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("对不起，添加的栏目名已经存在！");
				
			}
			if(value.indexOf("addSuccess")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("恭喜！添加成功!");
				
			}
		}
		
		$("input").focus(function(){
			$("#myAlertContainer").children().remove();
		});
		$("#modifyButton").click(function(){
			var isOk=true;
			$("input[name='title']").each(function(){
				if($(this).val().length==0){
					$(this).focus();
					isOk=false;
				}
			
		});
			if(!isOk){
				alert("文本域不能为空！");
			}
			return isOk;
		});
		
		
		
	}
	module.exports=Alert;
});