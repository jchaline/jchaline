$(document).ready(function(){
	$('#autoSetEmail').show();
	$('#autoSetEmail').bind('click',function(){
		var mail = $('#nom').val().replace(/\s/g, "") +'.'+$('#prenom').val().replace(/\s/g, "") +'@sopragroup.com';
		$('#email').attr('value',mail);
	});
});