var guestNamePrintURL = "users/guestName";
var guestData = "users/guestData";
var editGuest = "users/editGuest";

var getWS = "workingShiftController/getWorkingShifts";


$('#openBtn').click(function(){
    $('#myModal').modal({show:true})
});


$( document ).ready(function() {
	//$('#guestProfilePanel').show();
	$('#workingShiftCalendarPanel').hide();
	$('#OrdersPanel').hide();
	
	//guestNamePrint();
	//printGuestData();
});

$(document).on('click', '#working-shift-calendar-button', function(e) {
	//$('.guestName').empty();
	$('#workingShiftCalendarPanel').show();
	$('#ws-date-pick-form').show();
	
	$('#OrdersPanel').hide();
	//$('#friendRequestsPanel').hide();
	
	//guestNamePrint();
	//printGuestData();
	
});

$(document).on('click', '#orders-button', function(e) {
	//$('.guestName').empty();
	$('#workingShiftCalendarPanel').hide();
	$('#ws-date-pick-form').hide();
	$('#OrdersPanel').show();
	//$('#friendRequestsPanel').hide();
	
	//printFriends();
});

/*
$(document).on('click', '#friendRequestsButton', function(e) {
	$('.guestName').empty();
	$('#guestProfilePanel').hide();
	$('#friendsPanel').hide();
	$('#friendRequestsPanel').show();
});
*/

$(document).on('submit','.wsDateForm',function(e)
		{
			e.preventDefault();
			var startingDate = $(this).find("input[name=startingDate]").val();
			var endingDate = $(this).find("input[name=endingDate]").val();
			
			$.ajax(
					{
						type:'POST',
						url:getWS,
						contentType : 'application/json',
						dataType : "text",
						data : formToJSONWSRequest(startingDate,endingDate),
						success : function(data)
						{
							console.log("XXXX1");
							workingShiftPrint(data);
						}
						
				});
				
});

function workingShiftPrint(data)
{
	$('#workShiftDataForm').show();
	$('#workShiftData').empty();
	$('#workShiftData').show();
	//var brojac = 0;
	console.log(data);
	data = $.parseJSON(data);
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(
					list,
					function(index, wsofl) {
						
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
	});
	console.log("XXXX4");
}




function formToJSONWSRequest(startingDate, endingDate)
{
	return JSON.stringify(
			{
				"startDate" : startingDate,
				"endDate" : endingDate
			}
	);
}


