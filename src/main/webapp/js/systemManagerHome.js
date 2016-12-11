var newSysManURL = "systemManagerController/createNewSystemManager";
var checkIsSMLoggedURL = "systemManagerController/checkForSystemManager";
var getSysManDataURL = "systemManagerController/systemManagerData";
var getUpdSysManDataURL = "systemManagerController/updateSystemManager";

$(function() {

    
	$('#create-new-system-manager').click(function(e) {
		//e.preventDefault();
		$("#add-new-system-manager-form").delay(300).fadeIn(100);
 		$("#add-new-rest-form").fadeOut(100);
 		$("#add-new-rest-man-form").fadeOut(100);
 		$("#edit-info-form").fadeOut(100);
 		$('#edit-info').removeClass('active');
		$('#create-new-restaurant').removeClass('active');
		$('#create-new-restaurant-manager').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#create-new-restaurant').click(function(e) {
		//e.preventDefault();
		$("#add-new-rest-form").delay(300).fadeIn(100);
 		$("#add-new-system-manager-form").fadeOut(100);
 		$("#add-new-rest-man-form").fadeOut(100);
 		$("#edit-info-form").fadeOut(100);
 		$('#edit-info').removeClass('active');
 		$('#create-new-system-manager').removeClass('active');
		$('#create-new-restaurant-manager').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#create-new-restaurant-manager').click(function(e) {
		//e.preventDefault();
		$("#add-new-rest-man-form").delay(300).fadeIn(100);
 		$("#add-new-system-manager-form").fadeOut(100);
 		$("#add-new-rest-form").fadeOut(100);
 		$("#edit-info-form").fadeOut(100);
 		$('#edit-info').removeClass('active');
 		$('#create-new-system-manager').removeClass('active');
		$('#create-new-restaurant').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#edit-info').click(function(e) {
		//e.preventDefault();
		$("#edit-info-form").delay(300).fadeIn(100);
 		$("#add-new-system-manager-form").fadeOut(100);
 		$("#add-new-rest-form").fadeOut(100);
 		$("#add-new-rest-man-form").fadeOut(100);
 		$('#create-new-restaurant-manager').removeClass('active');
 		$('#create-new-system-manager').removeClass('active');
		$('#create-new-restaurant').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

});

$(document).ready
{
	console.log("Ovo se izvrsilox");
	//e.preventDefault();
	//$('#create-new-system-manager').addClass('active');
	//$("#add-new-system-manager-form").delay(300).fadeIn(100);
	//$("#add-new-rest-form").fadeOut(100);
	//$("#add-new-rest-man-form").fadeOut(100);
	//$("#edit-info-form").fadeOut(100);
	//$('#edit-info').removeClass('active');
	//$('#create-new-restaurant').removeClass('active');
	//$('#create-new-restaurant-manager').removeClass('active');
	//e.preventDefault();
	
	$('#create-new-restaurant-manager').removeClass('active');
	$('#create-new-system-manager').removeClass('active');
	$('#create-new-restaurant').removeClass('active');
	$('#edit-info').addClass('active');
	var apc= $.ajax({
		type : 'GET',
		url : getSysManDataURL,
		//contentType : 'application/json',
		dataType : "text",
		//citavo nista ne saljem... jasno
		//data : formToJSONNewSysMan(nick, email, name, surname, password),
		success: function(data) {
			console.log(data);
			if(data.length!=0)
				{
					data = $.parseJSON(data)
					//console.log("Ovo se izvrsilo");
					//console.log(data)
					//console.log(data.manager_email)
					//$('edit-info-form').find("input[name=nick]").val(data.manager_email);
					//$('edit-info-form').find("input[name=nick]").val(data.manager_email);
					//$('.editInfo').find("input[name=nick]").prop('value',data.manager_email);
					//$('.editInfo').find("input[name=nick]").prop('placeholder',data.manager_email);
					$('#eemail').val(data.manager_email);
					$('#enick').val(data.system_manager_nick_id);
					$('#epassword').val(data.manager_password);
					$('#ename').val(data.manager_name);
					$('#esurname').val(data.manager_last_name);
					//console.log(data.manager_email)
					
				}
			else
				{
					//console.log("Ovo se OVAKO izvrsilo");
					window.location.href = "http://localhost:8080/";
				}
			//window.location.href = "http://localhost:8080/SystemManagerHome.html";
		}
	});
}

$(document).on('submit', '.newSysManForm', function(e) {
	e.preventDefault();
	console.log("Add new System Manager begin");
	var email = $(this).find("input[name=email]").val();
	var password = $(this).find("input[name=password]").val();
	var name = $(this).find("input[name=name]").val();
	var surname = $(this).find("input[name=surname]").val();
	var nick = $(this).find("input[name=nick]").val();
	$.ajax({
		type : 'POST',
		url : newSysManURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONNewSysMan(nick, email, name, surname, password),
		success: function(data) {
			
			window.location.href = "http://localhost:8080/SystemManagerHome.html";
		}
	});
});
$(document).on('submit', '.editInfoForm', function(e) {
	e.preventDefault();
	console.log("Add new System Manager begin");
	var email = $(this).find("input[name=eemail]").val();
	var password = $(this).find("input[name=epassword]").val();
	var name = $(this).find("input[name=ename]").val();
	var surname = $(this).find("input[name=esurname]").val();
	var nick = $(this).find("input[name=enick]").val();
	$.ajax({
		type : 'POST',
		url : getUpdSysManDataURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONNewSysMan(nick, email, name, surname, password),
		success: function(data) {
			
			window.location.href = "http://localhost:8080/SystemManagerHome.html";
		}
	});
});

function formToJSONNewSysMan(system_manager_nick_id, manager_email, manager_name, manager_last_name, manager_password) {
	return JSON.stringify({
		"system_manager_nick_id" : system_manager_nick_id,
		"manager_email" : manager_email,
		"manager_name" : manager_name,
		"manager_last_name" : manager_last_name,
		"manager_password" : manager_password,
	});
}
/*
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
			if(data == "Guest") {
				window.location.href = "http://localhost:8080/GuestHome.html";
			} else {
				window.location.href = "http://localhost:8080/";
			}
		}
	});
});

function formToJSONLogin(email, password) {
	return JSON.stringify({
		"email" : email,
		"password" : password,
	});
}
*/