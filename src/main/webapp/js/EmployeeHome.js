var guestNamePrintURL = "users/guestName";
var guestData = "users/guestData";
var editGuest = "users/editGuest";

var getWS = "workingShiftController/getWorkingShifts";
var getOrders = "guestsOrderController/getOrders";
var acceptedOrder = "guestsOrderController/acceptedOrder";
var doneOrder = "guestsOrderController/doneOrder";

var getAreas = "tablesAndBillController/getAreas";
var getAreaTables = "tablesAndBillController/getAreaTables";

var hasChangedPass = "employeeController/hasChangedPass";

var changePass = "employeeController/changePass";

var occupyTableThingUrl = "tablesAndBillController/occupyTable";


$('#openBtn').click(function(){
    $('#myModal').modal({show:true})
});


$( document ).ready(function() {
	//$('#guestProfilePanel').show();
	$('#workingShiftCalendarPanel').hide();
	$('#OrdersPanel').hide();
	$('#changePasswordPanel').hide();
	$('#changePassForm').hide();
	$('#TablesPanel').hide();
	$('#area-pick-form').hide();
	console.log("Uslo do ovde..")
	$.ajax(
			{
				type:'POST',
				url:hasChangedPass,
				
				dataType : "text",
				
				success : function(data)
				{
					console.log(data)
					if(data=="no")
						{
							$('#working-shift-calendar-button').hide();
							$('#orders-button').hide();
							$('#tables-button').hide();
							$('#area-pick-form').hide();
							$('#changePasswordPanel').show();
							$('#changePassForm').show();
							
						}
					else if(data=="yes")
						{
							$('#working-shift-calendar-button').show();
							$('#orders-button').show();
							$('#tables-button').show();
						}
					
				}
				
		});
	
	
});

$(document).on('click', '#working-shift-calendar-button', function(e) {
	
	$('#workingShiftCalendarPanel').show();
	$('#ws-date-pick-form').show();
	$('#OrdersPanel').hide();
	$('#TablesPanel').hide();
	$('#area-pick-form').hide();
	
	
});



	


$(document).on('click', '#tables-button',function(e){
	
	//console.log("Uslo u tables thingy")
	//e.preventDefault();
	$('#workingShiftCalendarPanel').hide();
	$('#ws-date-pick-form').hide();
	$('#OrdersPanel').hide();
	
	
	
	console.log("Uslo u tables thingy")
	$.ajax(
	
			{
				type:'POST',
				url:getAreas,
				dataType:"text",
				success : function(data)
				{
					console.log(data);
					data = $.parseJSON(data);
					
					var text="";
					$('#areaSelect').empty();
					
					var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
					$.each(
									list,
									function(index, wsofl) {
										
										var areaName = wsofl.areaName;
										var restaurantName = wsofl.restaurantName;
										var areaID = wsofl.areaID;
										var restaurantID = wsofl.restaurantID;
										
										text= ' <option value="'+areaID+'">'+areaName+', '+restaurantName+'</option>';
										$('#areaSelect').append(text);
									}
						);
					
					
				}
			}
	);
	$('#TablesPanel').show();
	$('#area-pick-form').show();
	
});

$(document).on('click', '#orders-button', function(e) {
	//$('.guestName').empty();
	$('#workingShiftCalendarPanel').hide();
	$('#ws-date-pick-form').hide();
	$('#TablesPanel').hide();
	$('#OrdersPanel').show();
	$('#area-pick-form').hide();
	
	$.ajax(
			{
				type:'POST',
				url:getOrders,
				dataType : "text",
				success : function(data)
				{
					ordersPrint(data);

				}
				
		});

});

function showOrdersx()
{
	$('#workingShiftCalendarPanel').hide();
	$('#ws-date-pick-form').hide();
	$('#OrdersPanel').show();
	$('#TablesPanel').hide();
	$('#area-pick-form').hide();
	
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


function printTables2(areaID)
{
	//e.preventDefault();
	$.ajax(
			{
				type:'POST',
				url:getAreaTables,
				contentType : 'application/json',
				dataType : "text",
				data : formatToAreaImmitation(areaID),
				success : function(data)
				{
					//e.preventDefault();
					console.log("USLO U OVO ZA STOLOVE");
					//workingShiftPrint(data);
					
					console.log(" Print Tables 2")
					printTables(data);
				}
		});	
}


$(document).on('submit','.areaForm',function(e)
		{
			e.preventDefault();
			
			var e = document.getElementById("areaSelect");
			var areaID = e.options[e.selectedIndex].value;
			
			$.ajax(
					{
						type:'POST',
						url:getAreaTables,
						contentType : 'application/json',
						dataType : "text",
						data : formatToAreaImmitation(areaID),
						success : function(data)
						{
							console.log("USLO U OVO ZA STOLOVE");
							//workingShiftPrint(data);
							
							printTables(data);
						}
				});	
});

function printTables(data)
{
	console.log(data);
	data = $.parseJSON(data);
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	
	var areaSizeX;
	var areaSizeY;
	
	$.each(list,function(index,wsofl)
	{
		areaSizeX=wsofl.areaSizeX;
		areaSizeY=wsofl.areaSizeY;
	});
	
	
	console.log("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	console.log(areaSizeX);
	console.log(areaSizeY);
	
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
						
						var singleTable = {};
							singleTable['isOccupied']=isOccupied;
							singleTable['positionX']=positionX;
							singleTable['positionY']=positionY;
							singleTable['tableID']=tableID;
							singleTable['resTableID'] = resTableID;
							singleTable['areaID'] = aID;
							
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
						
						console.log("-----------");
						console.log(isOccupied);
						console.log(positionX);
						console.log(positionY);
						console.log(tableID);
						
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
					
					
					// OVO JE ID STOLA U RESTORANU ....
					//podsjecanje : svaki sto ima svoj generalni ID ali isto tako ima i ID koji sluzi samo estetski za prikaz u restoranu ... cisto da ne bi pisalo za neki restoran da ima sto broj 7765 ili tako nesto... znaci ovo je estetika samo i nema nikakvu vecu funkcionalnost
					
					var resTID;
					listx.some(function(thingy)
					{
						var x = thingy.positionX;
						console.log("x = "+x);
						var y = thingy.positionY;
						console.log("y = "+y);
						
						if(x==i && y==j)
							{
								naslo=true;
								id= thingy.tableID
								oc =  thingy.isOccupied;
								resTID = thingy.resTableID;
								areaID = thingy.areaID;
								return true;
								
								
							}
						else
							{
								naslo=false;
								
							}
					});
					
					if(naslo==true)
						{
						
						if(oc==true)
							{
							var tekstic = '<td height="60",width="60", bgcolor="#FF0000"><form id="tableForm" action="" method="post" role="form" class="tableForm" >'
								+'<input type="hidden" id="tableID" name="tableID" value="'+id+'" >'
								+'<input type="hidden" id="oc" name="oc" value="'+oc+'" >'
								+'<input type="hidden" id="areaID" name="areaID" value="'+areaID+'" >'
								+'<input type="submit" name="tab-submit" id="tab-submit" tabindex="7" role="button" class="form-control btn btn-success" value="'+resTID+'">'
								+'</form></td>';
							}
						else
							{
							var tekstic = '<td height="60",width="60", bgcolor="#7FFF00"><form id="tableForm" action="" method="post" role="form" class="tableForm" >'
								+'<input type="hidden" id="tableID" name="tableID" value="'+id+'" >'
								+'<input type="hidden" id="oc" name="oc" value="'+oc+'" >'
								+'<input type="hidden" id="areaID" name="areaID" value="'+areaID+'" >'
								+'<input type="submit" name="tab-submit" id="tab-submit" tabindex="7" role="button" class="form-control btn btn-success" value="'+resTID+'">'
								+'</form></td>';
							}
						
						
						
							//var text= ' <td>Thingy</td>'
							console.log("jednako");
							tr.append(tekstic)
						}
					else
						{
							tr.append('<td bgcolor="#696969", height="60",width="60"></td>')
						}
				
				
				}
			$('#tablesData').append(tr);
		
		}
	
	
	
	console.log("XXXX4");
	console.log(listx);
}


function formatToAreaImmitation(areaID)
{
	return JSON.stringify(
			{
				"areaID":areaID	
			}
	);
}



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

$(document).on('click', '.zauzmiSto',function(e)
{
	e.preventDefault();
	var tableID = $(this).find("input[name=tableIDw]").val();
	var areaID = $(this).find("input[name=areaID]").val();
	console.log("zauzmi Sto");
	console.log(areaID);
	$.ajax({
		type:'POST',
		url : occupyTableThingUrl,
		contentType:'application/json',
		dataType : "text",
		data: formatToTablePrintID(tableID),
		success : function(data)
		{
			console.log("ovoe");
			//$('#tablesData').empty();
			printTables2(areaID);
			$('#choseWhat').empty();
			console.log("ovoe 2");
			
		}	
	});
		
});

$(document).on('click', '.tableForm',function(e)
		{
			//console.log("did something")
			console.log("JEL SE POZOVE UOPSTE OVO?")
			e.preventDefault();
			//console.log('accepted this thingy thing');
			var tableID = $(this).find("input[name=tableID]").val();
			var oc = $(this).find("input[name=oc]").val();
			var areaID = $(this).find("input[name=areaID]").val();
			//console.log(orderxxID);
			//console.log(whatIsItxx);
			
			
			console.log(typeof oc);
			
			if(oc=="true")
			{
			//console.log("OC JE TRUE");
			//console.log("zasto se ne izvrsi");
				$('#choseWhat').empty();
			//alert("ovaj sto je zauzet ... eto...");
			
			//$('#choseWhat').empty();
			}
			else if(oc == "false")
			{
				//console.log("OC JE FALSE");
				//alert("ovaj sto nije zauzet x ... ");
				$('#choseWhat').empty();
				tekst_za_append='<form id="zauzmiSto" action="" method="post" role="form" class="zauzmiSto" >'
					+'<input type="hidden" id="tableIDw" name="tableIDw" value="'+tableID+'" >'
					+'<input type="hidden" id="areaID" name="areaID" value="'+areaID+'" >'
					+'<input type="submit" name="zauzmiSto-submit" role="button" id="zauzmiSto-submit" tabindex="7" class="form-control btn btn-success" value="Occupy Table">'
					+'</form>';
				
				$('#choseWhat').append(tekst_za_append);
				console.log("Wut mate? u wut?");
				//$('#choseWhat').show();	
			}
			
			
			console.log(areaID);
			console.log(oc);
			
			/*
			if(oc=="true")
				{
				
					console.log("STVARNI TRUE");
					
				}
			else if(oc== "false")
				{
					console.log("STVARNI FALSE");
				}
				*/
			/*
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
			*/
			
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

function formatToTablePrintID(idXXX)
{
	return JSON.stringify(
			{
				"generalTableID":idXXX
			}
			);
	
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


