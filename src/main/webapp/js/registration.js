var registrationURL = "users/createUser";
var loginURL = "users/loginUser";

$(function() {

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

});

$(document).on('submit', '.regForm', function(e) {
	e.preventDefault();
	console.log("registration begin");
	var email = $(this).find("input[name=email]").val();
	var password = $(this).find("input[name=password]").val();
	var name = $(this).find("input[name=name]").val();
	var surname = $(this).find("input[name=surname]").val();
	var birthDate = $(this).find("input[name=birthDate]").val();
	$.ajax({
		type : 'POST',
		url : registrationURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONRegistration(email, password, name, surname, birthDate),
		success: function(data) {
			window.location.href = "http://localhost:8080/stranica.html";
		}
	});
});

function formToJSONRegistration(email, password, name, surname, birthDate) {
	return JSON.stringify({
		"email" : email,
		"password" : password,
		"name" : name,
		"surname" : surname,
		"birthDate" : birthDate,
	});
}

//-----------LOGOVANJE
$(document).on('submit', '.loginForm', function(e) {
	e.preventDefault();
	console.log("registration begin");
	var email = $(this).find("input[name=emailLogin]").val();
	var password = $(this).find("input[name=passwordLogin]").val();
	$.ajax({
		type : 'POST',
		url : loginURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONLogin(email, password),
		success: function(data) {
			window.location.href = "http://localhost:8080/pocetna.html";
		}
	});
});

function formToJSONLogin(email, password) {
	return JSON.stringify({
		"email" : email,
		"password" : password,
	});
}