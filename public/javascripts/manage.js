/*
 * 判断图片类型
 * 
 * @param ths 
 * 			type="file"的javascript对象
 * @return true-符合要求,false-不符合
 */
function checkImgType(ths){

		if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(ths.value)) {
			alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
			ths.value = "";
			return false;
		}

	return true;
}
/*
 * 判断链接是否完整
 * 
 * @param ths 
 * 			type="file"的javascript对象
 * @return true-符合要求,false-不符合
 */
function checkUrl(obj){
   var pattern=/http(s)?:////([/w-]+/.)+[/w-]+(//[/w- .//?%&=]*)?/;
   var str = obj.value;
   if(str.match(pattern)==null) obj.value="http://"+obj.value;
}


#{if flash.backMessage }
<script type="text/javascript"">
alert('${flash.backMessage }');
</script>
#{/if}
	#{if flash.error }
	<script type="text/javascript"">
	alert('${flash.error }');
	</script>
	#{/if}