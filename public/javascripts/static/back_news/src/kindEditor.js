define(function(require,exports,module){
	require("kindeditor");
	require("jquery");
	require("bootstrap");
	function Editor(container){
		this._editor={};
	}
	Editor.prototype._init=function(node){
		KindEditor.ready(function(K) {
                window.editor = K.create(node,{
                	height:'300px',
                	uploadJson : 'uploadFile',
                    allowFileManager : true
                });
        });
		
		$("input[name='title']").focus(function(){
			$("#myAlertContainer").children().remove();
		});
		editor.focus(function(){
			$("#myAlertContainer").children().remove();
		});

	}
	Editor.prototype._send=function(node){
		
		$(node).click(function(){
			if($("input[name='title']").val().length==0 || editor.html().length==0){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				return false;
			}else{
				$("textarea[name='content']").val(editor.html());
				console.log($("textarea[name='content']").val());
				$("#form").submit();
			}
		})
		
	}
	Editor.prototype._status=function(node){
		var value = $(node).val();
		if(value!=null){
			if(value.indexOf("success")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("恭喜！发布成功!");
				
			}
			if(value.indexOf("failed")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("failed!");
				
			}
		}
	}
	Editor.prototype._selectChange=function(select,label,selectData){
		$(select).each(function(){
			this.onclick=function(){
				$(selectData).attr("value",$(this).attr("data"));
				$(label+" strong").text($(this).text());
				
			}
		});
	}
	module.exports=Editor;

})