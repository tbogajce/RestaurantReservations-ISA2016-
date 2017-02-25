var restaurantTableReser = "tableReservation/getReservedRestaurant";
var reservedRestaurantURL = "tableReservation/getReservedRestaurantData";
var getAllFriends = "tableReservation/getAllFriends"
var sendInvite = "tableReservation/sendInvite"

$(document).on('change', '.selectReservedRestaurant', function(e) {
	getData1();

});

function tableReservationInvite() {
			console.log('ubacivanje restorana invite');
			$(".selectReservedRestaurant").empty();
			$.ajax({
				type : 'GET',
				url : restaurantTableReser,
				contentType : 'application/json',
				dataType : "json",
				success : function(data) {
					$(".selectReservedRestaurant").empty();
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					var selectRestaurant = $(".selectReservedRestaurant");
					$.each(list, function(index, tableReservation) {
						var li = $('<option value="'+tableReservation.tableReservationId+'">' + tableReservation.tableReservationId + ' </option>');
						$(selectRestaurant).append(li);
					});
					getData1();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("AJAX ERROR: " + errorThrown);
				}
			});
}

function getData1(){
	console.log("vrati datum i vrijeme");
	var tableReservationId=$('.selectReservedRestaurant').find(":selected").val();
	console.log(tableReservationId);
	$.ajax({
		type : 'POST',
		url : reservedRestaurantURL,
		contentType : 'application/json',
		dataType : "json",
		data : formToJSONResRestaurant(tableReservationId),
		success : function(data) {
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			var selectRestaurant = $(".selectDiningTable");
			$.each(list, function(index, tableReservation) {
				$("input[name=dateInvite]").val(tableReservation.date);
				$("input[name=timeInvite]").val(tableReservation.time);
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
}

function formToJSONResRestaurant(tableReservationId) {
	return JSON.stringify({
		"tableReservationId" : tableReservationId,
	});
}


function getAllFriends1() {
	console.log('get friend reservation');
	$(".selectFriend").empty();
	$.ajax({
		type : 'GET',
		url : getAllFriends,
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			$(".selectFriend").empty();
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			var selectRestaurant = $(".selectFriend");
			$.each(list, function(index, user) {
				var li = $('<option value="'+user.email+'">' + user.userName + ' </option>');
				$(selectRestaurant).append(li);
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
}


$(document).on('submit', '.formCallFriend', function(e) {
	e.preventDefault();
	var tableReservationId=$('.selectReservedRestaurant').find(":selected").val();
	var email =$('.selectFriend').find(":selected").val();
	
	console.log(tableReservationId + " " + email )
	$.ajax({
		type : 'POST',
		url : sendInvite,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONInvite(tableReservationId, email),
		success : function(data) {
				
			location.reload(true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
});

function formToJSONInvite(tableReservationId, email) {
	return JSON.stringify({
		"tableReservationId" : tableReservationId,
		"email" : email,
	});
}