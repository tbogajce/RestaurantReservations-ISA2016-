var restaurantCombo = "tableReservation/getRestaurantCombo";
var restaurantTables = "tableReservation/restaurantTables";
var restaurantReservation = "tableReservation/restaurantReservation";
var getAllRestaurantsFun = "tableReservation/getAllRestaurants";
var restaurantAreas = "tableReservation/getAllAreas"
var areaTablesURL = "tableReservation/getTablePrint"
var checkURL="tableReservation/check"


$( document ).ready(function() {
	$('#tableReservationPanel').show();
	$('#callFriendPanel').hide();
	$('#makeOrderPanel').hide();
	$('#historyVisitsPanel').hide();
	$('#allRestaurantsPanel').hide();
	restaurantCombo22();
	document.getElementById("reserveButton").disabled = true;
	
	$('#hours').keyup(function () { 
		document.getElementById("reserveButton").disabled = true;
	});
	$('#time').keyup(function () { 
		document.getElementById("reserveButton").disabled = true;
	});
	$('#date').change(function () { 
		document.getElementById("reserveButton").disabled = true;
	});
	
});

$(document).on('click', '#reservationButton', function(e) {
	$('#tableReservationPanel').show();
	$('#callFriendPanel').hide();
	$('#makeOrderPanel').hide();
	$('#historyVisitsPanel').hide();
	$('#allRestaurantsPanel').hide();
	restaurantCombo22();
	document.getElementById("reserveButton").disabled = true;

});


$(document).on('click', '#callFriendButton', function(e) {
	$('#callFriendPanel').show();
	$('#tableReservationPanel').hide();
	$('#makeOrderPanel').hide();
	$('#historyVisitsPanel').hide();
	$('#allRestaurantsPanel').hide();
	
	tableReservationInvite();
	getAllFriends1();
});

$(document).on('click', '#makeOrderButton', function(e) {
	$('#tableReservationPanel').hide();
	$('#callFriendPanel').hide();
	$('#makeOrderPanel').show();
	$('#historyVisitsPanel').hide();
	$('#allRestaurantsPanel').hide();
	
	restaurantCombo33();
});

$(document).on('click', '#allRestaurantsButton', function(e) {
	$('#tableReservationPanel').hide();
	$('#callFriendPanel').hide();
	$('#makeOrderPanel').hide();
	$('#historyVisitsPanel').hide();
	$('#allRestaurantsPanel').show();
	
	printAllRestaurants();
});



$(document).on('change', '.selectRestaurant', function(e) {
	//getTables();
	getAreas();
	getAreaTables();
	document.getElementById("reserveButton").disabled = true;
});

$(document).on('change', '.selectArea', function(e) {
	getAreaTables();
	document.getElementById("reserveButton").disabled = true;

});
//-----------------CHECK AVAILABILITY

$(document).on('click', '#checkButton', function(e) {
	e.preventDefault();
	console.log('klik na check availability');
	var restaurantId=$('.selectRestaurant').find(":selected").val();
	var date = document.getElementById("date").value;
	var time = document.getElementById("time").value;
	var hours = document.getElementById("hours").value;
	var diningTableId = document.getElementById("tableId").value;
	console.log(restaurantId + " " + date + " " + time + " " + hours + " " + diningTableId)
	$.ajax({
		type : 'POST',
		url : checkURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONReservation(restaurantId, date, time, hours, diningTableId),
		success : function(data) {
			if(data=="OK") {
				
				document.getElementById("reserveButton").disabled = false;
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
					toastr.info('For requested date and time, table is free.');
			} else if(data=="NO") {
				
				document.getElementById("reserveButton").disabled = true;
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
					toastr.info('For requested date and time, table is occupied.');
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});




//-----------------END CHECK AVAILABILITY

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
					//getTables();
					getAreas();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("AJAX ERROR: " + errorThrown);
				}
			});
}

//function getTables(){
//	console.log("vrati broj stolova restorana");
//	var restaurantId=$('.selectRestaurant').find(":selected").val();
//	console.log(restaurantId);
//	$.ajax({
//		type : 'POST',
//		url : restaurantTables,
//		contentType : 'application/json',
//		dataType : "json",
//		data : formToJSONTable(restaurantId),
//		success : function(data) {
//			$(".selectDiningTable").empty();
//			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
//			var selectRestaurant = $(".selectDiningTable");
//			$.each(list, function(index, diningTable) {
//				var li = $('<option value="'+diningTable.generalTableID+'">' + diningTable.numberOfSeats + ' </option>');
//				$(selectRestaurant).append(li);
//			});
//		},
//		error : function(XMLHttpRequest, textStatus, errorThrown) {
//			alert("AJAX ERROR: " + errorThrown);
//		}
//	});
//}

function getAreas(){
	console.log("vrati AREA");
	var restaurantId=$('.selectRestaurant').find(":selected").val();
	console.log(restaurantId);
	$.ajax({
		type : 'POST',
		url : restaurantAreas,
		contentType : 'application/json',
		dataType : "json",
		data : formToJSONTable(restaurantId),
		success : function(data) {
			$(".selectArea").empty();
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			var selectRestaurant = $(".selectArea");
			$.each(list, function(index, area) {
				var li = $('<option value="'+area.areaID+'">' + area.areaName + ' </option>');
				$(selectRestaurant).append(li);
			});
			getAreaTables();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
}

function getAreaTables(){
	console.log("vrati stolove od AREA");
	var areaID=$('.selectArea').find(":selected").val();
	console.log(areaID);
	$.ajax({
		type : 'POST',
		url : areaTablesURL,
		contentType : 'application/json',
		dataType : "json",
		data : formToJSONArea(areaID),
		success : function(data) {
			printTables(data);
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

function formToJSONArea(areaID) {
	return JSON.stringify({
		"areaID" : areaID,
	});
}

$(document).on('submit', '.formReservation', function(e) {
	e.preventDefault();
	var restaurantId=$('.selectRestaurant').find(":selected").val();
	var date = $(this).find("input[name=date]").val();
	var time = $(this).find("input[name=time]").val();
	var hours = $(this).find("input[name=hours]").val();
	var diningTableId =$(this).find("input[name=tableId]").val();
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

//----------
///GET ALL RESTAURANTS

function printAllRestaurants() {
	$.ajax({
		type : 'GET',
		url : getAllRestaurantsFun,
		dataType : "json", // data type of response
		success : function(data) {
			allRestaurantsPrint(data);
		}
	});
}

function allRestaurantsPrint(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	$('#allRestaurantsData').empty();
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list,function(index, restaurant) {
						var tr = $('<tr></tr>');
						tr.append('<td>' + restaurant.restaurantName + '</td>'+ 
										'<td>' + restaurant.restaurantType + '</td>' +
										'<td>' + restaurant.restaurantAdress + '</td>'+
										'<td>' + restaurant.sumOfVotes/restaurant.totalNumberOfVoters + '</td>'
						);
						$('#allRestaurantsData').append(tr);
					});
}


///////////TABELA FILTER
//----------------------tabela-----------------------
$(document)
		.ready(
				function() {
					$(document)
							.on(
									'click',
									'.filterable .btn-filter',
									function() {
										var $panel = $(this).parents(
												'.filterable'), $filters = $panel
												.find('.filters input'), $tbody = $panel
												.find('.table tbody');
										if ($filters.prop('disabled') == true) {
											$filters.prop('disabled', false);
											$filters.first().focus();
										} else {
											$filters.val('').prop('disabled',
													true);
											$tbody.find('.no-result').remove();
											$tbody.find('tr').show();
										}
									});

					$('.filterable .filters input')
							.keyup(
									function(e) {
										/* Ignore tab key */
										var code = e.keyCode || e.which;
										if (code == '9')
											return;
										/* Useful DOM data and selectors */
										var $input = $(this), inputContent = $input
												.val().toLowerCase(), $panel = $input
												.parents('.filterable'), column = $panel
												.find('.filters th').index(
														$input.parents('th')), $table = $panel
												.find('.table'), $rows = $table
												.find('tbody tr');
										/* Dirtiest filter function ever ;) */
										var $filteredRows = $rows
												.filter(function() {
													var value = $(this).find(
															'td').eq(column)
															.text()
															.toLowerCase();
													return value
															.indexOf(inputContent) === -1;
												});
										/* Clean previous no-result if exist */
										$table.find('tbody .no-result')
												.remove();
										/*
										 * Show all rows, hide filtered ones
										 * (never do that outside of a demo !
										 * xD)
										 */
										$rows.show();
										$filteredRows.hide();
										/*
										 * Prepend no-result row if all rows are
										 * filtered
										 */
										if ($filteredRows.length === $rows.length) {
											$table
													.find('tbody')
													.prepend(
															$('<tr class="no-result text-center"><td colspan="'
																	+ $table
																			.find('.filters th').length
																	+ '">No result found</td></tr>'));
										}
									});
				});
// --------------------------kraj tabela------------------------------------



function printTables(data)
{

	//data = $.parseJSON(data);
	//console.log(data)
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	
	var areaSizeX;
	var areaSizeY;
	
	$.each(list,function(index,wsofl)
	{
		areaSizeX=wsofl.areaSizeX;
		areaSizeY=wsofl.areaSizeY;
	});
	
	

	
	var listx=[];
	
	
	$.each(
					list,
					function(index, wsofl) {
						
						var isOccupied = wsofl.occupied;
						var positionX = wsofl.positionX;
						var positionY = wsofl.positionY;
						var tableID = wsofl.generalTableID;
						var resTableID = wsofl.resTableID;
						var aID = wsofl.areaID;
	//					var waiter = wsofl.waiterID;
//						var guestOID = wsofl.guestOrderID;
						//var guestsOrder = wsofl.guestsOrder;
						
						var singleTable = {};
							singleTable['isOccupied']=isOccupied;
							singleTable['positionX']=positionX;
							singleTable['positionY']=positionY;
							singleTable['tableID']=tableID;
							singleTable['resTableID'] = resTableID;
							singleTable['areaID'] = aID;
			//				singleTable['waiter']= waiter;
		//					singleTable['guestOID']= guestOID;
							//singleTable['guestsOrder'] = guestsOrder;
							
							
							listx.push(singleTable);
						
						
/*
						var listOfObjects = [];
						var a = ["car", "bike", "scooter"];
						a.forEach(function(entry) {
						    var singleObj = {}
						    singleObj['type'] = 'vehicle';
						    singleObj['value'] = entry;
						    listOfObjects.push(singleObj);
						});

						console.log(listOfObjects);
*/
						
/*
						var list = [
						    { date: '12/1/2011', reading: 3, id: 20055 },
						    { date: '13/1/2011', reading: 5, id: 20053 },
						    { date: '14/1/2011', reading: 6, id: 45652 }
						];

						and then access it:

						alert(list[1].date);

*/
						

						//console.log(guestsOrder)
						
						//var tr = $('<tr></tr>');
						//tr.append()
						
						/*
						console.log("XXXX2");
						console.log(wsofl.name);
						var tr = $('<tr></tr>');
						tr.append(		'<td>'
										+ wsofl.name
										+ '</td>'
										+ '<td>'
										+ wsofl.lastName
										+ '</td>'
										+ '<td>'
										+ wsofl.start
										+ '</td>'
										+ '<td>'
										+ wsofl.finish
										+ '</td>'
								);
						
						$('#workShiftData').append(tr);
						console.log("XXXX3");
						//brojac = brojac + 1;
						*/
	});
	
	
	/*for (i = 0; i < cars.length; i++) {
    text += cars[i] + "<br>";
}*/
	
	$('#tablesData').empty();
	
	for( var i = 0; i< areaSizeX; i++)
		{
			var tr = $('<tr></tr>');
			for(var j = 0; j<areaSizeY; j++)
				{
				
					var naslo=false;
					var id;
					var oc;
					//var w;
				//	var g;
					//var guestsOrderx;
					
					
					// OVO JE ID STOLA U RESTORANU ....
					//podsjecanje : svaki sto ima svoj generalni ID ali isto tako ima i ID koji sluzi samo estetski za prikaz u restoranu ... cisto da ne bi pisalo za neki restoran da ima sto broj 7765 ili tako nesto... znaci ovo je estetika samo i nema nikakvu vecu funkcionalnost
					
					var resTID;
					listx.some(function(thingy)
					{
						var x = thingy.positionX;
						
						var y = thingy.positionY;
						
						
						if(x==i && y==j)
							{
								naslo=true;
								id= thingy.tableID
								oc =  thingy.isOccupied;
								resTID = thingy.resTableID;
								areaID = thingy.areaID;
							//	w= thingy.waiter;
						//		g= thingy.guestOID;
								
								//guestsOrderx = thingy.guestsOrder;
								return true;
								
								
							}
						else
							{
								naslo=false;
								
							}
					});
					
					if(naslo==true)
						{
						
					
							var tekstic = '<td height="60",width="60", bgcolor="#7FFF00"><form id="tableForm" action="" method="post" role="form" class="tableForm" >'
								+'<input type="hidden" id="tableID" name="tableID" value="'+id+'" >'
								+'<input type="hidden" id="oc" name="oc" value="'+oc+'" >'
								+'<input type="hidden" id="areaID" name="areaID" value="'+areaID+'" >'

								+'<input type="submit" name="tab-submit" id="tab-submit" tabindex="7" role="button" class="form-control btn btn-success" value="'+resTID+'">'
								+'</form></td>';
							
						
						
						
							//var text= ' <td>Thingy</td>'

							tr.append(tekstic)
						}
					else
						{
							tr.append('<td bgcolor="#696969", height="60",width="60"></td>')
						}
				
				
				}
			$('#tablesData').append(tr);
		
		}
	
	
	

}

$(document).on('click', '.tableForm',function(e) {
	e.preventDefault();
	console.log("KLIKNUO NA BUTTON")
	var idStola = $(this).find("input[name=tableID]").val();
	console.log(idStola);
	var idStola = $(this).find("input[name=tableID]").val();
	$('#tableId').val(idStola);
	$("input[name=tableId]").val(idStola);
	
});


