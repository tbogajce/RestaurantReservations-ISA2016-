var restaurantCombo = "tableReservation/getRestaurantCombo";
var restaurantTables = "tableReservation/restaurantTables";
var restaurantReservation = "tableReservation/restaurantReservation";



$( document ).ready(function() {
	$('#tableReservationPanel').show();
	$('#callFriendPanel').hide();
	$('#makeOrderPanel').hide();
	$('#historyVisitsPanel').hide();
	restaurantCombo22();
});

$(document).on('click', '#reservationButton', function(e) {
	$('#tableReservationPanel').show();
	$('#callFriendPanel').hide();
	$('#makeOrderPanel').hide();
	$('#historyVisitsPanel').hide();
	restaurantCombo22();

});


$(document).on('click', '#callFriendButton', function(e) {
	$('#callFriendPanel').show();
	$('#tableReservationPanel').hide();
	$('#makeOrderPanel').hide();
	$('#historyVisitsPanel').hide();
	
	tableReservationInvite();
	getAllFriends1();
});

$(document).on('click', '#makeOrderButton', function(e) {
	$('#tableReservationPanel').hide();
	$('#callFriendPanel').hide();
	$('#makeOrderPanel').show();
	$('#historyVisitsPanel').hide();

});



$(document).on('change', '.selectRestaurant', function(e) {
	getTables();

});

function restaurantCombo22() {
			console.log('ubacivanje restorana');
			$(".selectRestaurant").empty();
			$.ajax({
				type : 'GET',
				url : restaurantCombo,
				contentType : 'application/json',
				dataType : "json",
				success : function(data) {
					$(".selectRestaurant").empty();
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					var selectRestaurant = $(".selectRestaurant");
					$.each(list, function(index, restaurant) {
						var li = $('<option value="'+restaurant.restaurantId+'">' + restaurant.restaurantName + ' </option>');
						$(selectRestaurant).append(li);
					});
					getTables();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("AJAX ERROR: " + errorThrown);
				}
			});
}

function getTables(){
	console.log("vrati broj stolova restorana");
	var restaurantId=$('.selectRestaurant').find(":selected").val();
	console.log(restaurantId);
	$.ajax({
		type : 'POST',
		url : restaurantTables,
		contentType : 'application/json',
		dataType : "json",
		data : formToJSONTable(restaurantId),
		success : function(data) {
			$(".selectDiningTable").empty();
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			var selectRestaurant = $(".selectDiningTable");
			$.each(list, function(index, diningTable) {
				var li = $('<option value="'+diningTable.generalTableID+'">' + diningTable.numberOfSeats + ' </option>');
				$(selectRestaurant).append(li);
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
}

function formToJSONTable(restaurantId) {
	return JSON.stringify({
		"restaurantId" : restaurantId,
	});
}

$(document).on('submit', '.formReservation', function(e) {
	e.preventDefault();
	var restaurantId=$('.selectRestaurant').find(":selected").val();
	var date = $(this).find("input[name=date]").val();
	var time = $(this).find("input[name=time]").val();
	var hours = $(this).find("input[name=hours]").val();
	var diningTableId =$('.selectDiningTable').find(":selected").val();	
	console.log(restaurantId + " " + date + " " + time + " " + hours + " " + diningTableId)
	$.ajax({
		type : 'POST',
		url : restaurantReservation,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONReservation(restaurantId, date, time, hours, diningTableId),
		success : function(data) {
				
			location.reload(true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
});

function formToJSONReservation(restaurantId, date, time, hours, diningTableId) {
	return JSON.stringify({
		"restaurantId" : restaurantId,
		"date" : date,
		"time" : time,
		"hours" : hours,
		"diningTableId" : diningTableId,
	});
}

