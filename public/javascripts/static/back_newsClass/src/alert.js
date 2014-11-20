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
			else if(value.indexOf("delete")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("恭喜！删除成功！");
				
			}
			else if(value.indexOf("warnAdd")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("对不起！请先添加栏目！");
				
			}
			else if(value.indexOf("addRepeat")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("对不起，添加的栏目名已经存在！");
				
			}
			else if(value.indexOf("addSuccess")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("恭喜！添加成功!");
			}
			else if(value.indexOf("FailedDelete")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("对不起！该栏目已经关联到相关文章，如需删除，请删掉所有此栏目的文章");
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
		$("#addButton").click(function(){
			var value = $("#classTitle").val();
			if(value.length==0){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				return;
			}
			else{
				$("#addClassForm").submit();
			}
		})
		
		
	}
	module.exports=Alert;
});