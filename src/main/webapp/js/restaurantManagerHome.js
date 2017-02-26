var newProviderURL = "providerController/createNewProvider";
var getUpdRestDataURL = "restaurantManagerController/updateRestaurant";
var getRestDataURL = "restaurantManagerController/restaurantData";
var newBeverageURL = "beveragesController/createNewBeverage";

$(function() {

	$('#create-new-employee').click(function(e) {
		// e.preventDefault();
		$("#add-new-employee-form").delay(300).fadeIn(100);

		$("#greetings").fadeOut(100);
		$("#add-new-provider-form").fadeOut(100);
		$("#add-new-beverage-form").fadeOut(100);
		$("#edit-info-form").fadeOut(100);
		$('#create-new-provider').removeClass('active');
		$('#create-new-beverage').removeClass('active');
		$('#edit-info').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#create-new-provider').click(function(e) {
		// e.preventDefault();
		$("#add-new-provider-form").delay(300).fadeIn(100);

		$("#greetings").fadeOut(100);
		$("#add-new-employee-form").fadeOut(100);
		$("#add-new-beverage-form").fadeOut(100);
		$("#edit-info-form").fadeOut(100);
		$('#create-new-employee').removeClass('active');
		$('#create-new-beverage').removeClass('active');
		$('#edit-info').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#edit-info').click(function(e) {
		// e.preventDefault();
		$("#edit-info-form").delay(300).fadeIn(100);

		$("#greetings").fadeOut(100);
		$("#add-new-employee-form").fadeOut(100);
		$("#add-new-beverage-form").fadeOut(100);
		$("#add-new-provider-form").fadeOut(100);
		$('#create-new-employee').removeClass('active');
		$('#create-new-beverage').removeClass('active');
		$('#create-new-provider').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#create-new-beverage').click(function(e) {
		// e.preventDefault();
		$("#add-new-beverage-form").delay(300).fadeIn(100);

		$("#greetings").fadeOut(100);
		$("#add-new-employee-form").fadeOut(100);
		$("#add-new-provider-form").fadeOut(100);
		$("#edit-info-form").fadeOut(100);
		$('#create-new-employee').removeClass('active');
		$('#create-new-provider').removeClass('active');
		$('#edit-info').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

});




$(document).ready
{
	console.log("Ovo se izvrsilox");
	// e.preventDefault();
	// $('#create-new-system-manager').addClass('active');
	// $("#add-new-system-manager-form").delay(300).fadeIn(100);
	// $("#restoran").fadeOut(100);
	// $("#add-new-rest-man-form").fadeOut(100);
	// $("#edit-info-form").fadeOut(100);
	// $('#edit-info').removeClass('active');
	// $('#create-new-restaurant').removeClass('active');
	// $('#create-new-restaurant-manager').removeClass('active');
	// e.preventDefault();

	$('#create-new-employee').removeClass('active');
	$('#create-new-provider').removeClass('active');
	$('#create-new-beverage').removeClass('active');
	$('#edit-info').removeClass('active'); 
	var apc = $.ajax({
		type : 'GET',
		url : getRestDataURL,
		// contentType : 'application/json',
		dataType : "text",
		// citavo nista ne saljem... jasno
		// data : formToJSONNewSysMan(nick, email, name, surname,
		// password),
		success : function(data) {
			console.log(data);
			if (data.length != 0) {
				console.log("Ovo se IF izvrsilo");
				data = $.parseJSON(data)
				// console.log("Ovo se izvrsilo");
				// console.log(data)
				// console.log(data.manager_email)
				// $('edit-info-form').find("input[name=nick]").val(data.manager_email);
				// $('edit-info-form').find("input[name=nick]").val(data.manager_email);
				// $('.editInfo').find("input[name=nick]").prop('value',data.manager_email);
				// $('.editInfo').find("input[name=nick]").prop('placeholder',data.manager_email);
				  $('#rname').val(data.restaurantName);
				  $('#rtype').val(data.restaurantType);
				  $('#rcoordinates').val(data.restaurantCoordinates);
				  $('#radress').val(data.restaurantAdress);
				  $('#rrate').val(data.restaurantRate);
				  $('#rvisitsnumber').val(data.restaurantVisitsNumber);
				  $('#rincome').val(data.restaurantIncome); 
				// console.log(data.manager_email)

			} else {
				console.log("Ovo se ELSE izvrsilo");;
				window.location.href = "RegistrationPage.html";
			}
			// window.location.href =
			// "http://localhost:8080/SystemManagerHome.html";
		}
	});
}

/*
$(document).ready { console.log("Ovo se izvrsilox, RESTORAN MENADZER");
  
  $('#create-new-employee').removeClass('active');
  $('#create-new-provider').removeClass('active');
  $('#create-new-beverage').removeClass('active');
  $('#edit-info').removeClass('active'); 
  var apc = $.ajax(
		  { type : 'GET', url :
  getRestDataURL, // contentType : 'application/json', dataType : "text", //
  // citavo nista ne saljem... jasno
  // data : formToJSONNewRest(restaurantName,
  // restaurantType, restaurantCoordinates, restaurantAdress, restaurantRate,
  // restaurantVisitsNumber, restaurantIncome), 
  success : function(data) {
  console.log(data); if (data.length != 0) { console.log("Ovo se IF izvrsilo");
  data = $.parseJSON(data) $('#rname').val(data.restaurantName);
  $('#rtype').val(data.restaurantType);
  $('#rcoordinates').val(data.restaurantCoordinates);
  $('#radress').val(data.restaurantAdress);
  $('#rrate').val(data.restaurantRate);
  $('#rvisitsnumber').val(data.restaurantVisitsNumber);
  $('#rincome').val(data.restaurantIncome); 
  // console.log(data.manager_email) }
  else { console.log("Ovo se ELSE izvrsilo"); window.location.href =RestaurantManagerHome.html"; }); // window.location.href =
  
  
   // "http://localhost:8080/SystemManagerHome.html"; 
   }); 
*/

// *********************************************************************************
// PONUDJAC
$(document).on('submit', '.newProviderForm', function(e) {
	e.preventDefault();
	console.log("Add new Provider begin");
	var email = $(this).find("input[name=pemail]").val();
	var password = $(this).find("input[name=ppassword]").val();
	var name = $(this).find("input[name=pname]").val();
	var surname = $(this).find("input[name=psurname]").val();
	var nick = $(this).find("input[name=pnick]").val();
	$.ajax({
		type : 'POST',
		url : newProviderURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONNewProvider(nick, email, name, surname, password),
		success : function(data) {

			window.location.href = "RestaurantManagerHome.html";
		}
	});
});
// *********************************************************************************
// IZMENA RESTORANA
$(document).on(
		'submit',
		'.editInfoForm',
		function(e) {
			e.preventDefault();
			console.log("Edit Restaurant begin");
			var name = $(this).find("input[name=rname]").val();
			var type = $(this).find("input[name=rtype]").val();
			var coordinates = $(this).find("input[name=rcoordinates]").val();
			var adress = $(this).find("input[name=radress]").val();
			var rate = $(this).find("input[name=rrate]").val();
			var visitsnumber = $(this).find("input[name=rvisitsnumber]").val();
			var income = $(this).find("input[name=rincome]").val();
			console.log("Edit Restaurant mid");
			$.ajax({
				type : 'POST',
				url : getUpdRestDataURL,
				contentType : 'application/json',
				dataType : "text",
				data : formToJSONNewRest(name, type, coordinates, adress, rate,
						visitsnumber, income),
				success : function(data) {

					window.location.href = "RestaurantManagerHome.html";
				}
			});
		});
// *********************************************************************************
// PICE
$(document).on('submit', '.newBeverageForm', function(e) {
	e.preventDefault();
	console.log("Add new Beverage begin");
	var brid = $(this).find("input[name=brid]").val();
	var bname = $(this).find("input[name=bname]").val();
	var bdescription = $(this).find("input[name=bdescription]").val();
	var bprice = $(this).find("input[name=bprice]").val();
	$.ajax({
		type : 'POST',
		url : newBeverageURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONNewBeverage(brid, bname, bdescription, bprice),
		success : function(data) {

			window.location.href = "RestaurantManagerHome.html";
		}
	});
});
// *********************************************************************************
function formToJSONNewRest(restaurantName, restaurantType,
		restaurantCoordinates, restaurantAdress, restaurantRate,
		restaurantVisitsNumber, restaurantIncome) {
	return JSON.stringify({
		"restaurantName" : restaurantName,
		"restaurantType" : restaurantType,
		"restaurantCoordinates" : restaurantCoordinates,
		"restaurantAdress" : restaurantAdress,
		"restaurantRate" : restaurantRate,
		"restaurantVisitsNumber" : restaurantVisitsNumber,
		"restaurantIncome" : restaurantIncome,
	});
}

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

function formToJSONNewBeverage(restaurantId, beveragesDescription, beveragesName,
		beveragesPrice) {
	return JSON.stringify({
		"restaurantId" : restaurantId,
		"beveragesDescription" : beveragesDescription,
		"beveragesName" : beveragesName,
		"beveragesPrice" : beveragesPrice,
	});
}