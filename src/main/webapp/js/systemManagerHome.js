var newSysManURL = "systemManagerController/createNewSystemManager";
var checkIsSMLoggedURL = "systemManagerController/checkForSystemManager";
var getSysManDataURL = "systemManagerController/systemManagerData";
var getUpdSysManDataURL = "systemManagerController/updateSystemManager";
var getIsBigDealURL = "systemManagerController/isBigDeal";
var getAllSystemManagersURL = "systemManagerController/getSystemManagers";
var removeSystemManager = "systemManagerController/deleteSystemManager";

var newRestManURL = "restaurantManagerController/createNewRestaurantManager";

$(function() {

    
	$('#create-new-system-manager').click(function(e) {
		$(this).addClass('active');
		
		var apc= $.ajax({
			type : 'GET',
			url : getIsBigDealURL,
			//contentType : 'application/json',
			dataType : "text",
			//citavo nista ne saljem... jasno
			//data : formToJSONNewSysMan(nick, email, name, surname, password),
			success: function(data) {
				console.log(data);
				if(data=="da")
					{
					$("#add-new-system-manager-form").delay(300).fadeIn(100);
					
			 		$("#add-new-rest-form").fadeOut(100);
			 		$("#errorMessage").fadeOut(100);
			 		$("#greetings").fadeOut(100);
			 		$("#add-new-rest-man-form").fadeOut(100);
			 		$("#edit-info-form").fadeOut(100);
			 		$("#remove-sys-man-form").fadeOut(100);
			 		
			 		$('#edit-info').removeClass('active');
					$('#create-new-restaurant').removeClass('active');
					$('#create-new-restaurant-manager').removeClass('active');
					$('#remove-sys-man').removeClass('active');
					$(this).addClass('active');
					e.preventDefault();
					}
				else
					{
						$("#errorMessage").delay(300).fadeIn(100);
					
						$("#greetings").fadeOut(100);
						$("#add-new-rest-form").fadeOut(100);
				 		$("#greetings").fadeOut(100);
				 		$("#add-new-rest-man-form").fadeOut(100);
				 		$("#edit-info-form").fadeOut(100);
				 		$("#remove-sys-man-form").fadeOut(100);
				 		
				 		$('#edit-info').removeClass('active');
						$('#create-new-restaurant').removeClass('active');
						$('#create-new-restaurant-manager').removeClass('active');
						$('#remove-sys-man').removeClass('active');
						//$('#create-new-system-manager').removeClass('active');
				 		
						e.preventDefault();
						//console.log("Ovo se OVAKO izvrsilo");
						//window.location.href = "http://localhost:8080/";
						//$('#errorMessage').val("You are not a MAIN System manager");
						//$('#errorMessage').val("You are not a MAIN System manager");
					}
				//window.location.href = "http://localhost:8080/SystemManagerHome.html";
			}
		});
		
		//e.preventDefault();
		
	});
	$('#create-new-restaurant').click(function(e) {
		//e.preventDefault();
		$("#add-new-rest-form").delay(300).fadeIn(100);
		
 		$("#add-new-system-manager-form").fadeOut(100);
 		$("#errorMessage").fadeOut(100);
 		$("#greetings").fadeOut(100);
 		$("#add-new-rest-man-form").fadeOut(100);
 		$("#edit-info-form").fadeOut(100);
 		$("#remove-sys-man-form").fadeOut(100);
 		
 		$('#edit-info').removeClass('active');
 		$('#create-new-system-manager').removeClass('active');
		$('#create-new-restaurant-manager').removeClass('active');
		$('#remove-sys-man').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#create-new-restaurant-manager').click(function(e) {
		//e.preventDefault();
		$("#add-new-rest-man-form").delay(300).fadeIn(100);
		
 		$("#add-new-system-manager-form").fadeOut(100);
 		$("#greetings").fadeOut(100);
 		$("#errorMessage").fadeOut(100);
 		$("#add-new-rest-form").fadeOut(100);
 		$("#edit-info-form").fadeOut(100);
 		$("#remove-sys-man-form").fadeOut(100);
 		
 		$('#edit-info').removeClass('active');
 		$('#create-new-system-manager').removeClass('active');
		$('#create-new-restaurant').removeClass('active');
		$('#remove-sys-man').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#edit-info').click(function(e) {
		//e.preventDefault();
		$("#edit-info-form").delay(300).fadeIn(100);
		
 		$("#add-new-system-manager-form").fadeOut(100);
 		$("#greetings").fadeOut(100);
 		$("#errorMessage").fadeOut(100);
 		$("#add-new-rest-form").fadeOut(100);
 		$("#add-new-rest-man-form").fadeOut(100);
 		$("#remove-sys-man-form").fadeOut(100);
 		
 		$('#create-new-restaurant-manager').removeClass('active');
 		$('#create-new-system-manager').removeClass('active');
		$('#create-new-restaurant').removeClass('active');
		$('#remove-sys-man').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	
	$('#remove-sys-man').click(function(e) {
		$(this).addClass('active');
		
		var apc= $.ajax({
			type : 'GET',
			url : getIsBigDealURL,
			//contentType : 'application/json',
			dataType : "text",
			//citavo nista ne saljem... jasno
			//data : formToJSONNewSysMan(nick, email, name, surname, password),
			success: function(data) {
				console.log(data);
				if(data=="da")
					{
					$("#remove-sys-man-form").delay(300).fadeIn(100);
					
					$("#add-new-system-manager-form").fadeOut(100);
			 		$("#add-new-rest-form").fadeOut(100);
			 		$("#errorMessage").fadeOut(100);
			 		$("#greetings").fadeOut(100);
			 		$("#add-new-rest-man-form").fadeOut(100);
			 		$("#edit-info-form").fadeOut(100);
			 		
			 		$('#edit-info').removeClass('active');
					$('#create-new-restaurant').removeClass('active');
					$('#create-new-restaurant-manager').removeClass('active');
					$('#create-new-system-manager').removeClass('active');
					//$('#remove-sys-man').removeClass('active');
					$(this).addClass('active');
					printSystemManagers();
					
					
					
					e.preventDefault();
					}
				else
					{
					$("#errorMessage").delay(300).fadeIn(100);
					$("#greetings").fadeOut(100);
					$("#add-new-rest-form").fadeOut(100);
			 		$("#greetings").fadeOut(100);
			 		$("#add-new-rest-man-form").fadeOut(100);
			 		$("#edit-info-form").fadeOut(100);
			 		$("#remove-sys-man-form").fadeOut(100);
			 		
			 		$('#edit-info').removeClass('active');
					$('#create-new-restaurant').removeClass('active');
					$('#create-new-restaurant-manager').removeClass('active');
					//$('#create-new-system-manager').removeClass('active');
					//$('#remove-sys-man').removeClass('active');
					$('#create-new-system-manager').removeClass('active');
					e.preventDefault();
					}
				//window.location.href = "http://localhost:8080/SystemManagerHome.html";
			}
		});
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
					window.location.href = "http://localhost:8080/RegistrationPage.html";
				}
			//window.location.href = "http://localhost:8080/SystemManagerHome.html";
		}
	});
}


function printSystemManagers() {
	$.ajax({
		type : 'GET',
		url : getAllSystemManagersURL,
		dataType : "json", // data type of response
		success : function(data) {
			systemManagersPrint(data);
		}
	});
}

function systemManagersPrint(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	$('#sysManData').empty();
	var brojac=0;
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list,function(index, systemManager) {
				var tr = $('<tr></tr>');
				tr.append('<td>' + systemManager.system_manager_nick_id + '</td>' + '<td>'
						+ systemManager.manager_email + '</td>' + '<td>'
						+ systemManager.manager_name + '</td>' + '<td>' + systemManager.manager_last_name
						+ '</td><td>' + 
						'<form class="delSM'+brojac+'" > '+
		                 '<input type="hidden" name="idx" value='+ systemManager.system_manager_nick_id +' id="identifikatorID">' +
		                 ' <input type="submit" class="btn btn-primary btn-sm" role="button" value="Remove"> '
		                 +'</form></td>' );
				$('#sysManData').append(tr);
				brojac=brojac+1;
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
//*********************************************************************************
//MENADZER RESTORANA
$(document)
.on(
		'submit',
		'.newRestManForm',
		function(e) {
			e.preventDefault();
			console.log("Add new Restaurant Manager begin");
			var email = $(this).find("input[name=rmemail]").val();
			var password = $(this).find("input[name=rmpassword]").val();
			var name = $(this).find("input[name=rmname]").val();
			var surname = $(this).find("input[name=rmsurname]").val();
			var nick = $(this).find("input[name=rmnick]").val();
			$
					.ajax({
						type : 'POST',
						url : newRestManURL,
						contentType : 'application/json',
						dataType : "text",
						data : formToJSONNewRestMan(nick, email, name,
								surname, password),
						success : function(data) {

							window.location.href = "http://localhost:8080/SystemManagerHome.html";
						}
					});
		});
//*********************************************************************************
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

function formToJSONNewRestMan(restaurantManagerNickId, restaurantManagerMail,
		restaurantManagerName, restaurantManagerSurname, restaurantManagerPassword) {
	return JSON.stringify({
		"restaurantManagerNickId" : restaurantManagerNickId,
		"restaurantManagerMail" : restaurantManagerMail,
		"restaurantManagerName" : restaurantManagerName,
		"restaurantManagerSurname" : restaurantManagerSurname,
		"restaurantManagerPassword" : restaurantManagerPassword,
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