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
	//专门为修改文章设置的栏目设置
	Editor.prototype._editNews=function(selectAll,classIdSelect,label){
		var value = $(classIdSelect).val();
		$(selectAll).each(function(){
			var data = $(this).attr("data");
			if(data.indexOf(value)!=-1){
				$(label+" strong").text($(this).text());
			}
		});
	}
	
	Editor.prototype._editStatus=function(node){
		var value = $(node).val();
		if(value!=null){
			if(value.indexOf("success")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("恭喜！修改成功!");
				
			}
			if(value.indexOf("failed")!=-1){
				$($("#myAlertHtml").html()).appendTo($("#myAlertContainer"));
				$("#myAlert").text("failed!");
				
			}
		}
	}
	
	
	module.exports=Editor;

})