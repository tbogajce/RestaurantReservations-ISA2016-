var guestNamePrintURL = "users/guestName";
var guestData = "users/guestData";
var editGuest = "users/editGuest";

var getWS = "workingShiftController/getWorkingShifts";
var getOrders = "guestsOrderController/getOrders";
var acceptedOrder = "guestsOrderController/acceptedOrder";
var doneOrder = "guestsOrderController/doneOrder";

var hasChangedPass = "employeeController/hasChangedPass";

var changePass = "employeeController/changePass";


$('#openBtn').click(function(){
    $('#myModal').modal({show:true})
});


$( document ).ready(function() {
	//$('#guestProfilePanel').show();
	$('#workingShiftCalendarPanel').hide();
	$('#OrdersPanel').hide();
	$('#changePasswordPanel').hide();
	$('#changePassForm').hide();
	console.log("Uslo do ovde..")
	$.ajax(
			{
				type:'POST',
				url:hasChangedPass,
				//contentType : 'application/json',
				dataType : "text",
				//data : formToJSONWSRequest(startingDate,endingDate),
				success : function(data)
				{
					console.log(data)
					if(data=="no")
						{
							$('#working-shift-calendar-button').hide();
							$('#orders-button').hide();
							$('#changePasswordPanel').show();
							$('#changePassForm').show();
							//$('#working-shift-calendar-button').hide();
							//$('#orders-button').hide();
						}
					else if(data=="yes")
						{
							$('#working-shift-calendar-button').show();
							$('#orders-button').show();
						}
					//ordersPrint(data);
					//console.log("XXXX1");
					//workingShiftPrint(data);
				}
				
		});
	
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
	
	$.ajax(
			{
				type:'POST',
				url:getOrders,
				//contentType : 'application/json',
				dataType : "text",
				//data : formToJSONWSRequest(startingDate,endingDate),
				success : function(data)
				{
					
					ordersPrint(data);
					//console.log("XXXX1");
					//workingShiftPrint(data);
				}
				
		});
	//$('#friendRequestsPanel').hide();
	
	//printFriends();
});

function showOrdersx()
{
	$('#workingShiftCalendarPanel').hide();
	$('#ws-date-pick-form').hide();
	$('#OrdersPanel').show();
	
	$.ajax(
			{
				type:'POST',
				url:getOrders,
				//contentType : 'application/json',
				dataType : "text",
				//data : formToJSONWSRequest(startingDate,endingDate),
				success : function(data)
				{
					
					ordersPrint(data);
					//console.log("XXXX1");
					//workingShiftPrint(data);
				}
				
		});
}

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

$(document).on('submit','.changePassForm',function(e)
		{
			e.preventDefault();
			//var startingDate = $(this).find("input[name=startingDate]").val();
			//var endingDate = $(this).find("input[name=endingDate]").val();
			
			var newPass = $(this).find("input[name=newPass]").val();
			
			$.ajax(
					{
						type:'POST',
						url:changePass,
						contentType : 'application/json',
						dataType : "text",
						data : formatToPassword(newPass),
						success : function(data)
						{
							console.log("Izmjena passworda");
							$('#working-shift-calendar-button').show();
							$('#orders-button').show();
							$('#changePasswordPanel').hide();
							$('#changePassForm').hide();
							//workingShiftPrint(data);
						}
						
				});
				
});


function ordersPrint(data)
{
	$('#ordersForm').show();
	$('#orderedData').empty();
	$('#orderedData').show();
	//var brojac = 0;
	console.log(data);
	data = $.parseJSON(data);
	var brojacxx=0;
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(
					list,
					function(index, wsofl) {
						
						
						var whatWasOrdered =wsofl.whatWasOrdered;
						var quantity = wsofl.quantity;
						var note = wsofl.note;
						var accepted = wsofl.accepted;
						var done = wsofl.done;
						var employee = wsofl.employee;
						var table = wsofl.table;
						var orderId = wsofl.orderId;
						var whatIsIt = wsofl.whatIsIt;
						
						if(accepted =="")
							{
							/*
							accepted = ' <script language = "text/javascript">'
								+	'var acceptedOrder = "guestsOrderController/acceptedOrder";'
								+	'$(document).on("submit","acceptedForm'+orderId+'",function(e)'
								+	'{'
								+	'e.preventDefault();'
								+	'console.log("OVO SE KAO POZVALO NESTO xx");'
								+	'var orderxxID = $(this).find("input[name=orderIDh]").val();'
								+	'$.ajax({'
								+	'type: "POST" , url:acceptedOrder,'
								+	'contentType : "application/json",'
								
								+	'data : formatToOrderID(orderId),'
								+	'success : function()'
								+	'{'
								+	'console.log("OVO SE KAO POZVALO NESTO");'
								+	'showOrdersx()'
								+	'}'
								+	'});'
								+	'});'
								+ '</script> ';
								*/
								
							accepted=/*accepted+*/' <form id="acceptedForm" action="" method="post" role="form" class="acceptedForm" >'
								+'<input type="hidden" id="orderIDh" name="orderIDh" value="'+orderId+'" >'
								+'<input type="hidden" id="whatIsIt" name="whatIsIt" value="'+whatIsIt+'" >'
								+'<input type="submit" name="accepted-submit" id="accepted-submit" tabindex="7" role="button" class="form-control btn btn-success" value="Accept">'
								+'</form>';
								//Console.
								//accepted="Something";
							
							done="";
							
							
							}
						else
							{
								if(done =="")
								{
									//done="Something2"
									done='<form id="doneForm" action="" method="post" role="form" class="doneForm" >'
										+'<input type="hidden" id="orderID" name="orderIDh" value="'+orderId+'" >'
										+'<input type="submit" name="done-submit" role="button" id="done-submit" tabindex="7" class="form-control btn btn-success" value="Done">'
										+'</form>';
								}
							}
						
						
						
						
						console.log("XXXX2");
						console.log(wsofl.name);
						var tr = $('<tr></tr>');
						tr.append(		'<td>'
										+ whatWasOrdered
										+ '</td>'
										+ '<td>'
										+ quantity
										+ '</td>'
										+ '<td>'
										+ note
										+ '</td>'
										+ '<td>'
										+ accepted
										+ '</td>'
										+ '<td>'
										+ done
										+ '</td>'
										+ '<td>'
										+ employee
										+ '</td>'
										+ '<td>'
										+ table
										+ '</td>'
										
								);
						
						$('#orderedData').append(tr);
						console.log("XXXX3");
						//brojac = brojac + 1;
	});
	console.log("XXXX4");
}


$(document).on('click', '.acceptedForm',function(e)
{
	e.preventDefault();
	console.log('accepted this thingy thing');
	var orderxxID = $(this).find("input[name=orderIDh]").val();
	var whatIsItxx = $(this).find("input[name=whatIsIt]").val();
	console.log(orderxxID);
	console.log(whatIsItxx);
	$.ajax({
		type:'POST',
		url: acceptedOrder,
		contentType:'application/json',
		data: formatToOrderID(orderxxID),
		success : function()
		{
			showOrdersx();
		}
		
	});
	
});

$(document).on('click', '.doneForm',function(e)
		{
			e.preventDefault();
			console.log('accepted this thingy thing');
			var orderxxID = $(this).find("input[name=orderIDh]").val();
			var whatIsItxx = $(this).find("input[name=whatIsIt]").val();
			console.log(orderxxID);
			console.log(whatIsItxx);
			$.ajax({
				type:'POST',
				url: doneOrder,
				contentType:'application/json',
				data: formatToOrderID(orderxxID),
				success : function()
				{
					showOrdersx();
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


function formatToPassword(newPass)
{
	return JSON.stringify(
			{
				"newPassword":newPass
			}
			
	);
}

function formatToOrderID(orderxxxx)
{
	return JSON.stringify(
			{
				"orderId":orderxxxx	
			}
	);
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


