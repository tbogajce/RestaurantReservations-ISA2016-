var getProviderDataURL = "providerController/providerData";
var getUpdProviderDataURL = "providerController/updateProvider";
var hasChangedPass = "providerController/hasChangedPass";
var changePass = "providerController/changePass";
var logoutURL = "providerController/logoutProvider";

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
// NOVA SIFRA
$(document).ready(function() {
	// $('#guestProfilePanel').show();
	$('#edit-info').hide();
	console.log("Uslo do ovde..")
	$.ajax({
		type : 'POST',
		url : hasChangedPass,

		dataType : "text",

		success : function(data) {

			console.log(data)
			if (data == "no") {
				$('#edit-info').hide();

				$('#changePasswordPanel').show();
				$('#changePassForm').show();

			} else if (data == "yes") {

				$('#edit-info').show();

				$('#changePasswordPanel').hide();
				$('#changePassForm').hide();

			} else if (data == "nijeCovjek") {
				$('#edit-info').hide();
			}

		}

	});

});

$(document).on('submit', '.changePassForm', function(e) {
	e.preventDefault();
	// var startingDate = $(this).find("input[name=startingDate]").val();
	// var endingDate = $(this).find("input[name=endingDate]").val();

	var newPass = $(this).find("input[name=newPass]").val();

	$.ajax({
		type : 'POST',
		url : changePass,
		contentType : 'application/json',
		dataType : "text",
		data : formatToPassword(newPass),
		success : function(data) {
			console.log("Izmjena passworda");
			$('#edit-info').show();
			$('#changePasswordPanel').hide();
			$('#changePassForm').hide();
			// workingShiftPrint(data);
		}

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

//*********************************************************************************
//LOGOUT
$(document).on('click', '#logoutButton', function(e) {
	e.preventDefault();
	console.log("logout");
	$.ajax({
		type : 'GET',
		url : logoutURL,
		contentType : 'application/json',
		dataType : "text",
		success : function(data) {
			if(data=="logout") {
				window.location.href = "ProviderLogin.html";
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});
//*********************************************************************************

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

function formatToPassword(newPass) {
	return JSON.stringify({
		"newPassword" : newPass
	}

	);
}