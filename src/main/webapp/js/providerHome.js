var getProviderDataURL = "providerController/providerData";
var getUpdProviderDataURL = "providerController/updateProvider";

$(function() {

	$('#edit-info').click(function(e) {
		// e.preventDefault();
		$("#edit-info-form").delay(300).fadeIn(100);

		$("#greetings").fadeOut(100);

		$(this).addClass('active');
		e.preventDefault();
	});

});

// *********************************************************************************
// IZMENA PODATAKA

$(document).ready
{
	console.log("Ovo se izvrsilo@");

	$('#edit-info').addClass('active');
	var apc = $.ajax({
		type : 'GET',
		url : getProviderDataURL,
		dataType : "text",
		success : function(data) {
			console.log(data);
			if (data.length != 0) {
				data = $.parseJSON(data)
				$('#eemail').val(data.providerMail);
				$('#enick').val(data.providerNickId);
				$('#epassword').val(data.providerPassword);
				$('#ename').val(data.providerName);
				$('#esurname').val(data.providerSurname);

			} else {
				window.location.href = "RegistrationPage.html";
			}
		}
	});
}

$(document).on('submit', '.editInfoForm', function(e) {
	e.preventDefault();
	console.log("Edit provider begin");
	var email = $(this).find("input[name=eemail]").val();
	var password = $(this).find("input[name=epassword]").val();
	var name = $(this).find("input[name=ename]").val();
	var surname = $(this).find("input[name=esurname]").val();
	var nick = $(this).find("input[name=enick]").val();
	$.ajax({
		type : 'POST',
		url : getUpdProviderDataURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONNewProvider(nick, email, name, surname, password),
		success : function(data) {

			window.location.href = "ProviderHome.html";
		}
	});
});
// *********************************************************************************

function formToJSONNewProvider(providerNickId, providerMail, providerName,
		providerSurname, providerPassword) {
	return JSON.stringify({
		"providerNickId" : providerNickId,
		"providerMail" : providerMail,
		"providerName" : providerName,
		"providerSurname" : providerSurname,
		"providerPassword" : providerPassword,
	});
}