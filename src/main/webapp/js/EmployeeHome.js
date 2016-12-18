var guestNamePrintURL = "users/guestName";
var guestData = "users/guestData";
var editGuest = "users/editGuest";

var getWS = "WorkingShiftController/getWorkingShifts";


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
							
						}
						
					});
				
		});


function formToJSONWSRequest(startingDate, endingDate)
{
	return JSON.stringify(
			{
				"startDate" : startingDate,
				"endDate" : endingDate
			}
	);
}


