$(document).ready(function(){
	$(function(){
		if(window.PIE){
			$('section').each(function(){
				PIE.attach(this);
			});
		}
	});
});