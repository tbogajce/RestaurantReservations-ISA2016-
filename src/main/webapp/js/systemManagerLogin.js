/**
 * 
 */

var loginURL = "systemManagerController/loginSystemManager";

function formToJSONLogin(system_manager_nick_id, manager_password) {
	return JSON.stringify({
		"system_manager_nick_id" : system_manager_nick_id,
		"manager_password" : manager_password,
	});
}


$(document).on('submit', '.loginForm', function(e) {
	e.preventDefault();
	console.log("SystemManager LOGIN begin");
	var system_manager_nick_id = $(this).find("input[name=nickLogin]").val();
	var manager_password = $(this).find("input[name=passwordLogin]").val();
	$.ajax({
		type : 'POST',
		url : loginURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONLogin(system_manager_nick_id, manager_password),
		success: function(data) {
			if(data == "systemManager") {
				window.location.href = "http://localhost:8080/SystemManagerHome.html";
			} else {
				window.location.href = "http://localhost:8080/";
			}
		}
	});
});