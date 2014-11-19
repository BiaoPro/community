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