function bindSelectAll(idButton, className){
	$('#'+idButton).bind('click',function(){
		$('.'+className).click();
	});
}