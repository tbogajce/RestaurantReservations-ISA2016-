var friendsURL = "users/getFriends";
var friendComboURL = "users/getFriendsCombo";
var addFriendURL = "users/addFriend";
var removeFriendURL = "users/removeFriend";

function printFriends() {
	$.ajax({
		type : 'GET',
		url : friendsURL,
		dataType : "json", // data type of response
		success : function(data) {
			friendsPrint(data);
		}
	});
}

function friendsPrint(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	$('#friendsData').empty();
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list,function(index, friend) {
						var tr = $('<tr></tr>');
						tr.append('<td>' + friend.email + '</td>'+ 
										'<td>' + friend.user_name + '</td>' +
										'<td>' + friend.user_surname + '</td>' + 
										'<td>' + friend.user_birth_date + '</td>'+
										'<td>' + '<form class="removeFriend" > ' + 
										'<input type="hidden" name="friendMail" value=' + friend.email +'> '+
										'<input type="submit" class="btn btn-danger btn-sm" role="button" value="Remove from friends"> '
										+ '</form></td>');
						$('#friendsData').append(tr);
					});
}

// -------------DODAVANJE U COMBOBOX

$(document).on(
		'change',
		'#searchFriends',
		function(e) {
			console.log('promjena na change');
			$(".friendsCombobox").empty();
			var email = $("#searchFriends").val();
			$.ajax({
				type : 'POST',
				url : friendComboURL,
				contentType : 'application/json',
				dataType : "json",
				data : formToJSONCombo(email),
				success : function(data) {
					$(".friendsCombobox").empty();
					var list = data == null ? []
							: (data instanceof Array ? data : [ data ]);
					var friendsCombobox = $(".friendsCombobox");
					$.each(list, function(index, friendCombo) {
						var li = $('<option>' + friendCombo.email
								+ ' </option>');
						$(friendsCombobox).append(li);
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("AJAX ERROR: " + errorThrown);
				}
			});
		});

function formToJSONCombo(email) {
	return JSON.stringify({
		"email" : email,
	});
}
// ---------------END DODAVANJE U COMBOBOX

// ------------DODAVANJE PRIJATELJA
$(document).on('click', '.addFriendButton', function(e) {
	console.log('klik na add friend');
	var email = $('select').children(':selected').text();
	console.log(email);
	$.ajax({
		type : 'POST',
		url : addFriendURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONCombo(email),
		success : function(data) {
			if (data == "OK") {

				toastr.options = {
					"closeButton" : true,
					"debug" : false,
					"newestOnTop" : false,
					"progressBar" : false,
					"positionClass" : "toast-top-right",
					"preventDuplicates" : false,
					"onclick" : null,
					"showDuration" : "300",
					"hideDuration" : "1000",
					"timeOut" : "5000",
					"extendedTimeOut" : "1000",
					"showEasing" : "swing",
					"hideEasing" : "linear",
					"showMethod" : "fadeIn",
					"hideMethod" : "fadeOut"
				}
				toastr.info('Friend request sent.');
				 $('#myModal').find("input,textarea,select,option").val('');
				 $('#myModal').find("option").empty();
				 $("#myModal").modal("hide");
				 
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});

// -----------KRAJ DODAVANJE PRIJATELJA

//----------IZBRISI PRIJATELJA
$(document).on('click', '.removeFriend', function(e) {
	e.preventDefault();
	console.log('izbrisi prijatelja');
	var email = $(this).find("input[type=hidden]").val();
	$.ajax({
		type : 'POST',
		url : removeFriendURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONCombo(email),
		success : function(data) {
			printFriends();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});
//---------END OBRISI PRIJATELJA
