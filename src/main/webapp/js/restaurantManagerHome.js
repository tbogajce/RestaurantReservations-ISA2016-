var newProviderURL = "providerController/createNewProvider";
var getUpdRestDataURL = "restaurantManagerController/updateRestaurant";
var getRestDataURL = "restaurantManagerController/restaurantData";
var newBeverageURL = "beveragesController/createNewBeverage";
var newMenuURL = "menuController/createNewMenu";
var newEmployeeURL = "restaurantManagerController/createNewEmployee";
var newShiftURL = "workingShiftController/createNewShift";
var logoutURL = "restaurantManagerController/logoutRestaurantManager";

$(function() {

	$('#create-new-employee').click(function(e) {
		// e.preventDefault();
		$("#add-new-employee-form").delay(300).fadeIn(100);

		$("#greetings").fadeOut(100);
		$("#add-new-provider-form").fadeOut(100);
		$("#add-new-beverage-form").fadeOut(100);
		$("#add-new-menu-form").fadeOut(100);
		$("#add-new-shift-form").fadeOut(100);
		$("#edit-info-form").fadeOut(100);
		$('#create-new-provider').removeClass('active');
		$('#create-new-beverage').removeClass('active');
		$('#create-new-menu').removeClass('active');
		$('#create-new-shift').removeClass('active');
		$('#create-new-report').removeClass('active');
		$('#create-new-sc').removeClass('active');
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
		$("#add-new-menu-form").fadeOut(100);
		$("#add-new-shift-form").fadeOut(100);
		$("#edit-info-form").fadeOut(100);
		$('#create-new-employee').removeClass('active');
		$('#create-new-beverage').removeClass('active');
		$('#create-new-menu').removeClass('active');
		$('#create-new-shift').removeClass('active');
		$('#create-new-report').removeClass('active');
		$('#create-new-sc').removeClass('active');
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
		$("#add-new-shift-form").fadeOut(100);
		$("#add-new-provider-form").fadeOut(100);
		$("#add-new-menu-form").fadeOut(100);
		$('#create-new-employee').removeClass('active');
		$('#create-new-beverage').removeClass('active');
		$('#create-new-provider').removeClass('active');
		$('#create-new-menu').removeClass('active');
		$('#create-new-report').removeClass('active');
		$('#create-new-shift').removeClass('active');
		$('#create-new-sc').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#create-new-beverage').click(function(e) {
		// e.preventDefault();
		$("#add-new-beverage-form").delay(300).fadeIn(100);

		$("#greetings").fadeOut(100);
		$("#add-new-employee-form").fadeOut(100);
		$("#add-new-provider-form").fadeOut(100);
		$("#add-new-menu-form").fadeOut(100);
		$("#edit-info-form").fadeOut(100);
		$("#add-new-shift-form").fadeOut(100);
		$('#create-new-employee').removeClass('active');
		$('#create-new-provider').removeClass('active');
		$('#create-new-menu').removeClass('active');
		$('#create-new-shift').removeClass('active');
		$('#create-new-report').removeClass('active');
		$('#create-new-sc').removeClass('active');
		$('#edit-info').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#create-new-menu').click(function(e) {
		// e.preventDefault();
		$("#add-new-menu-form").delay(300).fadeIn(100);

		$("#greetings").fadeOut(100);
		$("#add-new-employee-form").fadeOut(100);
		$("#add-new-provider-form").fadeOut(100);
		$("#add-new-shift-form").fadeOut(100);
		$("#add-new-beverage-form").fadeOut(100);
		$("#edit-info-form").fadeOut(100);
		$('#create-new-employee').removeClass('active');
		$('#create-new-provider').removeClass('active');
		$('#create-new-beverage').removeClass('active');
		$('#create-new-shift').removeClass('active');
		$('#create-new-report').removeClass('active');
		$('#create-new-sc').removeClass('active');
		$('#edit-info').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#create-new-shift').click(function(e) {
		// e.preventDefault();
		$("#add-new-shift-form").delay(300).fadeIn(100);

		$("#greetings").fadeOut(100);
		$("#add-new-employee-form").fadeOut(100);
		$("#add-new-provider-form").fadeOut(100);
		$("#add-new-beverage-form").fadeOut(100);
		$("#add-new-menu-form").fadeOut(100);
		$("#edit-info-form").fadeOut(100);
		$('#create-new-employee').removeClass('active');
		$('#create-new-provider').removeClass('active');
		$('#create-new-beverage').removeClass('active');
		$('#create-new-sc').removeClass('active');
		$('#create-new-menu').removeClass('active');
		$('#create-new-report').removeClass('active');
		$('#edit-info').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#create-new-sc').click(function(e) {
		// e.preventDefault();

		$("#greetings").fadeOut(100);
		$("#add-new-employee-form").fadeOut(100);
		$("#add-new-provider-form").fadeOut(100);
		$("#add-new-shift-form").fadeOut(100);
		$("#add-new-beverage-form").fadeOut(100);
		$("#add-new-menu-form").fadeOut(100);
		$("#edit-info-form").fadeOut(100);
		$('#create-new-employee').removeClass('active');
		$('#create-new-provider').removeClass('active');
		$('#create-new-beverage').removeClass('active');
		$('#create-new-shift').removeClass('active');
		$('#create-new-menu').removeClass('active');
		$('#create-new-report').removeClass('active');
		$('#edit-info').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	$('#create-new-report').click(function(e) {
		// e.preventDefault();

		$("#greetings").fadeOut(100);
		$("#add-new-employee-form").fadeOut(100);
		$("#add-new-provider-form").fadeOut(100);
		$("#add-new-beverage-form").fadeOut(100);
		$("#add-new-shift-form").fadeOut(100);
		$("#add-new-menu-form").fadeOut(100);
		$("#edit-info-form").fadeOut(100);
		$('#create-new-employee').removeClass('active');
		$('#create-new-provider').removeClass('active');
		$('#create-new-beverage').removeClass('active');
		$('#create-new-shift').removeClass('active');
		$('#create-new-menu').removeClass('active');
		$('#create-new-sc').removeClass('active');
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
				console.log("Ovo se ELSE izvrsilo");
				;
				window.location.href = "RestaurantManagerHome.html";
			}
			// window.location.href =
			// "http://localhost:8080/SystemManagerHome.html";
		}
	});
}

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
// RADNIK

$(document).on(
		'submit',
		'.newEmployeeForm',
		function(e) {
			e.preventDefault();
			console.log("Add new Provider USER begin");
			var email = $(this).find("input[name=eemail]").val();
			var password = $(this).find("input[name=epassword]").val();
			var name = $(this).find("input[name=ename]").val();
			var surname = $(this).find("input[name=esurname]").val();
			var birthDate = $(this).find("input[name=ebirthDate]").val();

			var erole = $(this).find("input[name=erole]").val();
			var ecnumber = $(this).find("input[name=ecnumber]").val();
			var erate = $(this).find("input[name=erate]").val();
			var essize = $(this).find("input[name=essize]").val();

			$.ajax({
				type : 'POST',
				url : newEmployeeURL,
				contentType : 'application/json',
				dataType : "text",
				data : formToJSONUserEmployee(email, password, name, surname,
						birthDate, erole, ecnumber, erate, essize),
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
	var bname = $(this).find("input[name=bname]").val();
	var bdescription = $(this).find("input[name=bdescription]").val();
	var bprice = $(this).find("input[name=bprice]").val();
	$.ajax({
		type : 'POST',
		url : newBeverageURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONNewBeverage(bdescription, bname, bprice),
		success : function(data) {

			window.location.href = "RestaurantManagerHome.html";
		}
	});
});
// *********************************************************************************
// JELO
$(document).on('submit', '.newMenuForm', function(e) {
	e.preventDefault();
	console.log("Add new Menu begin");
	var mdescription = $(this).find("input[name=mdescription]").val();
	var mprice = $(this).find("input[name=mprice]").val();
	var mrate = $(this).find("input[name=mrate]").val();
	$.ajax({
		type : 'POST',
		url : newMenuURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONNewMenu(mdescription, mprice, mrate),
		success : function(data) {

			window.location.href = "RestaurantManagerHome.html";
		}
	});
});

// *********************************************************************************
// RASPORED RADA
$(document).on('submit', '.newShiftForm', function(e) {
	e.preventDefault();
	console.log("Add new Provider begin");
	var snote = $(this).find("input[name=snote]").val();
	var sbeginDate = $(this).find("input[name=sbeginDate]").val();
	var sendDate = $(this).find("input[name=sendDate]").val();
	var semployeeId = $(this).find("input[name=semployeeId]").val();
	$.ajax({
		type : 'POST',
		url : newShiftURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONNewShift(semployeeId, sbeginDate, sendDate, snote),
		success : function(data) {

			window.location.href = "RestaurantManagerHome.html";
		}
	});
});

// *********************************************************************************
// LOGOUT
$(document).on('click', '#logoutButton', function(e) {
	e.preventDefault();
	console.log("logout");
	$.ajax({
		type : 'GET',
		url : logoutURL,
		contentType : 'application/json',
		dataType : "text",
		success : function(data) {
			if (data == "logout") {
				window.location.href = "RestaurantManagerLogin.html";
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
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

function formToJSONNewBeverage(beveragesDescription, beveragesName,
		beveragesPrice) {
	return JSON.stringify({
		"beveragesDescription" : beveragesDescription,
		"beveragesName" : beveragesName,
		"beveragesPrice" : beveragesPrice,
	});
}

function formToJSONNewMenu(menuMealDescription, menuMealPrice, menuMealRate) {
	return JSON.stringify({
		"menuMealDescription" : menuMealDescription,
		"menuMealPrice" : menuMealPrice,
		"menuMealRate" : menuMealRate,
	});
}

function formToJSONUserEmployee(email, password, name, surname, birthDate,
		employeeRole, employeeConfectionNumber, employeeRate, employeeShoeSize) {
	return JSON.stringify({
		"email" : email,
		"password" : password,
		"name" : name,
		"surname" : surname,
		"birthDate" : birthDate,
		"employeeRole" : employeeRole,
		"employeeConfectionNumber" : employeeConfectionNumber,
		"employeeRate" : employeeRate,
		"employeeShoeSize" : employeeShoeSize,
	});
}

function formToJSONNewShift(worker, shiftBeginningTime, shiftEndTime, note) {
	return JSON.stringify({
		"worker" : worker,
		"shiftBeginningTime" : shiftBeginningTime,
		"shiftEndTime" : shiftEndTime,
		"note" : note,
	});
}