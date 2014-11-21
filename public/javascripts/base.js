$(document).ready(function(){
	//以下为form事件

	// 按钮选择，选中事件表示
	$(".filter a").not(".moreitem a").click(function(){
		var chosenItem = $(this).parent('dd').prev("dt").data('name');
		var num = $(this).prevAll('a').length;	

		// 修改为active
		$(this).addClass('active')
		.siblings().removeClass('active');

		// 显示到筛选条件那里去
		//判断是否为第一个li元素
		if(num > 0){	
			showFilter(chosenItem, this.innerHTML);
		}else{
			showFilter(chosenItem);			
		}	

		return false;
	});


	// form input点击链接，结果在input中显示，价格过滤
	$("#price a").click(function(){
		// 取出链接中的价格，比如说80-100 
		var link = this.innerHTML.split("-");
		var $chooseFilter = $(this).parents(".chose-type").find("input");
		var chosenItem = "a[name='" + this.innerHTML +  "']";

		if(link.length <= 1){
			var tmp;
			if(parseInt(link[0]) <= 800){
				tmp = link[0];
				link[0] = "0";
				link[1] = tmp;
			}
		}

		$chooseFilter.val("");
		$chooseFilter.each(function(index) {
			var str = parseInt(link[index]) || "";
			$(this).val(str);
		});
		return false;
	});
	$("#room a").click(function(){
		var str = parseInt(this.innerHTML) || "";
		$(this)
		.parents(".chose-type").find("input").eq(0).val(str);
		return false;
	});
	$('.choose-input').click(function(event) {
		var chosenItem = $(this).parent('dd').prev("dt").data('name');		
		var $inputItem = $(this).siblings('span').find('input');
		var str = $(this).parent('dd').prev("dt").data('title') + " ";

		$inputItem.each(function(index) {
			str += $(this).parent("label").nextAll("label").length == 0 ? this.value : this.value + "-"; 
		});

		// 显示到筛选条件那里去
		showFilter(chosenItem, str);
		return false;
	});
	// 选项hover效果及选中
	$(".moreitem-box").hover(function() {
		var $this = $(this);
		var $moreitem_ul = $this.children('ul.moreitem');
		var $moreitem_link = $this.find('.moreitem li');
		var chosenItem = $this.data("name");

		$moreitem_ul.css("display","block");

		$moreitem_link.click(function() {
			var str = $(this).find("a").html();

			$this.find('.moreitem-btn a').html(str);
			$moreitem_ul.css("display","none");

			// 如果在租房页面 显示到筛选条件中去
			if($(".form-filter")){
				showFilter(chosenItem, str);
			}
			
			return false;
		});
	}, function() {
		var $moreitem_ul = $(this).children('ul.moreitem');

		$moreitem_ul.css("display", "none");
	}); 

	// 取消条件
	$(".form-filter").on('click','.close-icon',function(){
		var chosenItem = $(this).prev("a").attr("name");
		console.log(chosenItem);

		// 移除选项
		showFilter(chosenItem);

		return false;
	});

// 身份填写
$("#identity a").click(function(){
	$(this).css({
		"background" : "#ff9700",
		"color" : "#fff"
	})
	.siblings('a').css({
		"background" : "",
		"color" : ""
	});

	if($(this).parent('td').find('span').length === 0){
		$(this).parent('td').append("<span class='ok'></span>")
	}

	return false;
})
	// checkbox实现全选与全不选
	$("#checkItem a").click(function(){
		var $checkbox = $(this).parent().find('li input[type="checkbox"]');
		var str = checkAll($checkbox) ? false : true;

		$checkbox.each(function(index) {
			this.checked = str;
		});

		this.innerHTML = checkAll($checkbox) ? "全不选" : "全选";

		return false;
	});


	// 在填写类表单中对输入框进行必须填写设定
	$(".form-fill").on('blur ', ':not(a)' ,function(){
		var $parentTd = $(this).parents("td");
		var str = this.value;
		if(str === ""){
			insertErrMessage($parentTd , "必须填写完整");
		}else if( this.id == "contract" && !((/^([\u4e00-\u9fa5]){2,4}$/).test(str))){
			insertErrMessage($parentTd, "必须输入中文姓名");
		}else if( this.id == "phone" && !( /^0?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(str))){
			insertErrMessage($parentTd, "必须输入有效的手机号码 !")
		}else{
			if($parentTd.find("input.required").val() !== ""){
				$parentTd.find("span").remove();
			}			
		}
	});

//校验填入数据是否科学
	$('.form-fill .beLegal input').blur(function(){
		var $parent = $(this).parents("td");
		var $smaller = $parent.find("input").eq(0);
		var $upper = $parent.find("input").eq(1);
		if($smaller.length > 0 && parseInt($upper.val()) < parseInt($smaller.val())){
			insertErrMessage($parent, "不科学了");
		}
	});

// 确保上传文件是图片，且不超过8张，不超过10M
$(".form-fill input[type='file']").change(function(){
	var $parent = $(this).parents("td");
	var files = this.files;
	var flag = false;

	if(files.length > 0){
		$parent.find("span").not("alert").html("已上传" + this.files.length + "个文件 ");
		// 错误识别
		if(files.length < 8){
			for(var i=0 ; i< files.length; i++){
				if(files[i].size === 0){
					insertErrMessage($parent, "上传图片不可为空");
					flag = true;
				}else if(!/\.(jpg)|(jpeg)|(png)|(gif)$/.test(files[i].name)){
					insertErrMessage($parent, "上传的必须是图片");
					flag = true;;
				}else if(Math.floor((files[i].size / 1024)/1024 ) > 10){
					insertErrMessage($parent, "不可以超过10M");
					flag = true;
				}else{
					$parent.find("span.alert").remove();
				}
			}	
		}else{
			insertErrMessage($parent,"不可以超过8张");
		}

//清除文件缓存，防止用户不小心提交
		if(flag){
			files = [];
			$parent.find("span").not("span.alert").empty();
		}
		console.log(files.length);
	}
});

//点击标签按钮添加标签
$(".form-fill .tag").click(function(event) {
	var $parent = $(this).parents('td');
	if($(this).siblings('input').length <= 5){
		$('<input class="btn btn-default required" name="tag"  value="" />').insertBefore($(this));
	}else{
		insertErrMessage($parent, "不可以超过5个呦");
	}
});
// 点击标签附近的链接，显示到input里面去
$(".form-fill .tag+span a").click(function(){
	var $parent = $(this).parents('td');
	if($parent.find('input').length <= 6){
		$('<input class="btn btn-default required" name="tag"  value="' + this.innerHTML + '" />').insertBefore($parent.find(".tag"));
	}else{
		insertErrMessage($parent,"不可以超过5个呦");
	}
})
	// 防止用户在输入数字的input里输入非数字的东东
	$("input.num-only").keyup(function(){
		this.value = this.value.replace(/[^\d]/g,"");
	});
	
	

	//确认无误后发送表单
	$("#submit").click(function(event) {
		var $required = $(".form-fill .required");
		$required.each(function(index, el) {
			var value = this.value || this.innerHTML || "";
			if($("#identity").length >0 && $("#identity").find("span").length === 0 ){
				alert("请选择身份");
				return false
			}else if(value === "" || value === "请选择" || value === "街道" || value === "装修情况") {
				var errMes = $(this).parents('tr').find("th span").html();
				alert("请完善 " + errMes + " 信息");
				return false;
			}else if($(".form-fill").find("span.alert-danger").length > 0){
				alert("请检查信息合法性");
				return false;
			}
		});
		return false;
	});

// 图片展示
if($(".img-small").length > 0){
// 自执行函数，防止污染变量域
	(function(){
		var page = 1;
		var $smUl = $('.img-small ul');
		var width = $(".img-small").width();
		var $smLi = $(".img-small li");
		var $smImg = $(".img-small img");
		var $bgImg = $(".show-img img");
		var len = Math.ceil($smUl.width() / width);
		
		$smLi.width(Math.floor($(".img-small").width()/4));
		$smImg.width(Math.floor($(".img-small").width()/4) - 4);

		$smImg.click(function(event) {
			$bgImg.get(0).src = this.src;
		});

		$("a#next").click(function(){	
			if(!$(this).is(":animated")){
				if(page === len){
					$smUl.animate({
						"left" : 0
					}, "slow");
		
					page = 1;
				}else{
					$smUl.animate({
						"left" : "-=" + width
					}, "slow");
		
					page++;
				}
			}	
		});
		
			$("a#prev").click(function(){
				if(!$(this).is(":animated")){
					if(page == 1){
						$smUl.animate({
							"left" : '-=' + width * ( len - 1 )
						}, "slow");
						page = len;
					}else{
						$smUl.animate({
							"left" : "+=" + width
						}, "slow");
						page -- ;
					}
				}
		});
	})();
}



	// 将已选选项显示到筛选列表中函数,可重载函数 
	function showFilter(chosenItem, content){
		var $showChoice = $(".choose-fliter");
		var chosenItemName ="a[name='" + chosenItem +  "']";
		$showChoice.find(chosenItemName).parent("li").remove();

		if( arguments.length >= 2){
			var str = '<li class="alert alert-warning"><a name="'+ chosenItem +'"">'+ content +'</a><a class="close-icon">&times;</a></li>';

			$showChoice.find("ul button").parent("li").before(str);
		}
	}

	// checkbox检查是否全选
	function checkAll(elem) {
		var n = 0;
		elem.each(function(index) {
			this.checked && n++;
		});
		if(n == elem.length){
			return true;
		}else{
			console.log("");
			return false;
		}
	}	
	// 插入错误信息函数
	function insertErrMessage(elem, content) {
		elem.find('span.alert').length != 0 ? elem.find("span.alert").html(content)  : elem.append('<span  class="alert alert-danger">'+content+'</span>');
	}

});