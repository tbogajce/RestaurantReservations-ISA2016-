/**
 * 
 */

var loginURL = "providerController/loginProvider";

function formToJSONLogin(providerNickId, providerPassword) {
	return JSON.stringify({
		"providerNickId" : providerNickId,
		"providerPassword" : providerPassword,
	});
}


$(document).on('submit', '.loginForm', function(e) {
	e.preventDefault();
	console.log("Provider LOGIN begin");
	var providerNickId = $(this).find("input[name=nickLogin]").val();
	var providerPassword = $(this).find("input[name=passwordLogin]").val();
	$.ajax({
		type : 'POST',
		url : loginURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONLogin(providerNickId, providerPassword),
		success: function(data) {
			if(data == "provider") {
				window.location.href = "ProviderHome.html";
			} else {
				window.location.href = "http://localhost:8080/";
			}
		}
	});
});