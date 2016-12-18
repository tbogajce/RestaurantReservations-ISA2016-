/**
 * 
 */

var loginURL = "restaurantManagerController/loginRestaurantManager";

function formToJSONLogin(restaurantManagerNickId, restaurantManagerPassword) {
	return JSON.stringify({
		"restaurantManagerNickId" : restaurantManagerNickId,
		"restaurantManagerPassword" : restaurantManagerPassword,
	});
}


$(document).on('submit', '.loginForm', function(e) {
	e.preventDefault();
	console.log("RestaurantManager LOGIN begin");
	var restaurantManagerNickId = $(this).find("input[name=nickLogin]").val();
	var restaurantManagerPassword = $(this).find("input[name=passwordLogin]").val();
	$.ajax({
		type : 'POST',
		url : loginURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONLogin(restaurantManagerNickId, restaurantManagerPassword),
		success: function(data) {
			if(data == "restaurantManager") {
				window.location.href = "http://localhost:8080/RestaurantManagerHome.html";
			} else {
				window.location.href = "http://localhost:8080/";
			}
		}
	});
});