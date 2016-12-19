var friendRequestURL = "users/friendRequest";
var declineFriendURL = "users/declineFriend";
var acceptFriendURL = "users/acceptFriend";

function printFriendRequests() {
	$.ajax({
		type : 'GET',
		url : friendRequestURL,
		dataType : "json", // data type of response
		success : function(data) {
			requestsPrint(data);
		}
	});
}

function requestsPrint(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	$('#friendRequestsData').empty();
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list,function(index, friend) {
						var tr = $('<tr></tr>');
						tr.append('<td>' + friend.email + '</td>'+ 
										'<td>' + friend.user_name + '</td>' +
										'<td>' + friend.user_surname + '</td>' + 
										'<td>' + friend.user_birth_date + '</td>'+
										'<td>' + '<form class="acceptButton" > '
										+ '<input type="hidden" name="emailAccept" value=' + friend.email + '> '+
										' <input type="submit" class="btn btn-success btn-sm" role="button" value="Accept"> '
										+ '</form></td>' +
										'<td><form class="declineButton" > '
										+ '<input type="hidden" name="emailDecline" value=' + friend.email + '>'+
										' <input type="submit" class="btn btn-danger btn-sm" role="button" value="Decline"> '
										+ '</form> </td>')
						$('#friendRequestsData').append(tr);
					});
}

//-----------DECLINE FRIENDSHIP
$(document).on('click', '.declineButton', function(e) {
	e.preventDefault();
	console.log('odbij prijateljstvo');
	var email = $(this).find("input[type=hidden]").val();
	$.ajax({
		type : 'POST',
		url : declineFriendURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONCombo(email),
		success : function(data) {
			printFriendRequests();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});
//---------END DECLINE FRIENDSHIP

//---------ACCEPT FRIENDSHIP
$(document).on('click', '.acceptButton', function(e) {
	e.preventDefault();
	console.log('prihvati prijateljstvo');
	var email = $(this).find("input[type=hidden]").val();
	$.ajax({
		type : 'POST',
		url : acceptFriendURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONCombo(email),
		success : function(data) {
			printFriendRequests();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});
//---------END ACCEPT FRIENDSHIP


