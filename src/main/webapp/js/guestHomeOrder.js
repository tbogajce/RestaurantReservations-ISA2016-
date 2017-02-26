var tableReservationURL = "tableReservation/getTablesReservation33";
var tableResInfo = "tableReservation/getReservedRestaurantData";
var getMeal1 = "tableReservation/getRestaurantMeal";
var getSelectDrink = "tableReservation/getRestaurantBeverages";

$(document).on('change', '.findRestaurant', function(e) {
	getReservationData();
	returnMeals();
	returnBeverages();
});

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
						
					});
					getReservationData();
					returnMeals();
					returnBeverages();

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


function returnMeals() {
	console.log("vrati JELO");
	var tableReservationId=$('.findRestaurant').find(":selected").val();
	console.log(tableReservationId);
	$(".selectMeal").empty();
	$.ajax({
		type : 'POST',
		url : getMeal1,
		contentType : 'application/json',
		dataType : "json",
		data : formToJSONResRestaurant(tableReservationId),
		success : function(data) {
			$(".selectMeal").empty();
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			var selectRestaurant = $(".selectMeal");
			$.each(list, function(index, meal) {
				var li = $('<option value="'+meal.menuMealId+'">' + meal.menuMealDescription + ' </option>');
				$(selectRestaurant).append(li);
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
}


//----------
function returnBeverages() {
	console.log("vrati PICA");
	var tableReservationId=$('.findRestaurant').find(":selected").val();
	console.log(tableReservationId);
	$(".selectDrink").empty();
	$.ajax({
		type : 'POST',
		url : getSelectDrink,
		contentType : 'application/json',
		dataType : "json",
		data : formToJSONResRestaurant(tableReservationId),
		success : function(data) {
			$(".selectDrink").empty();
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			var selectRestaurant = $(".selectDrink");
			$.each(list, function(index, beverage) {
				var li = $('<option value="'+beverage.beveragesId+'">' + beverage.beveragesName + ' </option>');
				$(selectRestaurant).append(li);
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
}