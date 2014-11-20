$(document).ready(function(){

		// 	var checkItems = document.getElementById('checkItem').getElementsByTagName('input');
		// var choseAll =document.getElementById('checkItem').getElementsByTagName('a')[0];
		// var trs = document.getElementsByTagName('tr');
		// var identity = trs[1].querySelectorAll('a');
		// var i;
		// // 判断是否选择身份
		// 	for(i=0;i<identity.length;i++){
		// 		identity[i] .onclick = function(){
		// 			for(var n=0;n<identity.length;n++) removeClass(identity[n],'chosen');
		// 			addClass(this,'chosen');
		// 			this.parentNode.querySelector('span').className ="ok";
		// 		}
		// 	} 
		// 	// 全选和全不选实现
		// 	choseAll.onclick = function(){
		// 		if(isCheckAll()){
		// 			for(i=0;i<checkItems.length;i++){
		// 				checkItems[i].checked = false ;
		// 			}
		// 			choseAll.innerHTML = "全选";
		// 		}else{
		// 			for(i=0;i<checkItems.length;i++){
		// 				checkItems[i].checked = true;
		// 			}
		// 			choseAll.innerHTML = "全不选";
		// 		}
		// 	}
		// 	// 判断是否选取全部
		// 	function isCheckAll(){
		// 		for(var  n=0,i=0;i<checkItems.length;i++){
		// 			checkItems[i].checked&&n++;
		// 		}
		// 		if(n == checkItems.length){
		// 			return true;
		// 		}else{
		// 			return false;
		// 		}
		// 	}

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

	//checkbox实现全选与全不选
	// $("#checkItem button").click(function(){
		
	// });

	// 在填写类表单中对输入框进行必须填写设定
	$(".form-fill .required").filter("input").blur(function(){
		var $parentTd = $(this).parents("td");
		if(this.value === ""){
			$parentTd.find("span").length != 0 ? $parentTd.find("span").html("必须填写完整")  : $parentTd.append('<span  class="alert alert-danger">必须填写</span>');
		}else{
			if($parentTd.find("input.required").val() !== ""){
				$parentTd.find("span").remove();
			}			
		}
	});

	// 防止用户在输入数字的input里输入非数字的东东
	$("input.num-only").keyup(function(){
		this.value = this.value.replace(/[^\d]/g,"");
	});

	// 确认无误后发送表单
	$("#submit").click(function(event) {
		
	});


	// // 图片查看
	// $()
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

});