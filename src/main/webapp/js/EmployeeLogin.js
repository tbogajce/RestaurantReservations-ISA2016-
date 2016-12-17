/**
 * 
 */

var loginURL = "employeeController/loginEmployee";

function formToJSONLogin(email, password) {
	return JSON.stringify({
		"email" : email,
		"password" : password,
	});
}



$(document).on('submit', '.loginForm', function(e) {
	e.preventDefault();
	console.log("registration begin");
	var email = $(this).find("input[name=nickLogin]").val();
	var password = $(this).find("input[name=passwordLogin]").val();
	$.ajax({
		type : 'POST',
		url : loginURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONLogin(email, password),
		success: function(data) {
			if(data == "Guest") {
				window.location.href = "http://localhost:8080/GuestHome.html";
			}
			else if(data == "Employee")
				{
				window.location.href = "http://localhost:8080/EmployeeHome.html";
				}
			else {
				window.location.href = "http://localhost:8080/";
			}
		}
	});
});