var friendsURL = "users/getFriends";

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
	$.each(list,
			function(index, friend) {
				var tr = $('<tr></tr>');
				tr.append('<td>' + friend.email + '</td>' + '<td>'
						+ friend.user_name + '</td>' + '<td>'
						+ friend.user_surname + '</td>' + '<td>' + friend.user_birth_date
						+ '</td><td>' + '<form class="detaljiKupovine" > '+
		                 '<input type="hidden" name="id" value='+ friend.id +' id="identifikatorID">' +
		                 ' <input type="submit" class="btn btn-primary btn-sm" role="button" value="Obrisi prijatelja"> '+
		                 '</form></td>' );
				$('#friendsData').append(tr);
			});
}