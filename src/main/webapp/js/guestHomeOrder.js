var tableReservationURL = "tableReservation/getTablesReservation33";
var tableResInfo = "tableReservation/getReservedRestaurantData";


function restaurantCombo33() {
			console.log('ubacivanje restorana');
			//$(".findRestaurant").empty();
			$.ajax({
				type : 'GET',
				url : tableReservationURL,
				contentType : 'application/json',
				dataType : "json",
				success : function(data) {
					$(".findRestaurant").empty();
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					var selectRestaurant = $(".findRestaurant");
					$.each(list, function(index, tableReservation) {
						var li = $('<option value="'+tableReservation.tableReservationId+'">' + tableReservation.tableReservationId + ' </option>');
						$(selectRestaurant).append(li);
						getReservationData();
					});
					

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("AJAX ERROR: " + errorThrown);
				}
			});
}

function getReservationData(){
	console.log("vrati datum i vrijeme");
	var tableReservationId=$('.findRestaurant').find(":selected").val();
	console.log(tableReservationId);
	$.ajax({
		type : 'POST',
		url : tableResInfo,
		contentType : 'application/json',
		dataType : "json",
		data : formToJSONResRestaurant(tableReservationId),
		success : function(data) {
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			$.each(list, function(index, tableReservation) {
				$("input[name=dateOrder]").val(tableReservation.date);
				$("input[name=timeOrder]").val(tableReservation.time);
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
}