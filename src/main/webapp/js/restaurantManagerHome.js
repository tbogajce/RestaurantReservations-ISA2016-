var newProviderURL = "providerController/createNewProvider";

$(function() {

	$('#create-new-employee').click(function(e) {
		// e.preventDefault();
		$("#add-new-employee-form").delay(300).fadeIn(100);

		$("#greetings").fadeOut(100);
		$("#add-new-provider-form").fadeOut(100);
		$('#create-new-provider').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#create-new-provider').click(function(e) {
		// e.preventDefault();
		$("#add-new-provider-form").delay(300).fadeIn(100);

		$("#greetings").fadeOut(100);
		$("#add-new-employee-form").fadeOut(100);
		$('#create-new-employee').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

});

//*********************************************************************************
//PONUDJAC
$(document)
		.on(
				'submit',
				'.newProviderForm',
				function(e) {
					e.preventDefault();
					console.log("Add new Provider begin");
					var email = $(this).find("input[name=pemail]").val();
					var password = $(this).find("input[name=ppassword]").val();
					var name = $(this).find("input[name=pname]").val();
					var surname = $(this).find("input[name=psurname]").val();
					var nick = $(this).find("input[name=pnick]").val();
					$
							.ajax({
								type : 'POST',
								url : newProviderURL,
								contentType : 'application/json',
								dataType : "text",
								data : formToJSONNewProvider(nick, email, name,
										surname, password),
								success : function(data) {

									window.location.href = "RestaurantManagerHome.html";
								}
							});
				});
//*********************************************************************************

function formToJSONNewProvider(providerNickId, providerMail,
		providerName, providerSurname,
		providerPassword) {
	return JSON.stringify({
		"providerNickId" : providerNickId,
		"providerMail" : providerMail,
		"providerName" : providerName,
		"providerSurname" : providerSurname,
		"providerPassword" : providerPassword,
	});
}