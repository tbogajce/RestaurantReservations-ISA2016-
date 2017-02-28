var guestNamePrintURL = "users/guestName";
var guestData = "users/guestData";
var editGuest = "users/editGuest";

var getWS = "workingShiftController/getWorkingShifts";
var getOrders = "guestsOrderController/getOrders";
var acceptedOrder = "guestsOrderController/acceptedOrder";
var canOrder = "guestsOrderController/canOrder";
var doneOrder = "guestsOrderController/doneOrder";

var getAreas = "tablesAndBillController/getAreas";
var getAreaTables = "tablesAndBillController/getAreaTables";

var hasChangedPass = "employeeController/hasChangedPass";

var isKonobar = "employeeController/isKonobar";

var changePass = "employeeController/changePass";

var occupyTableThingUrl = "tablesAndBillController/occupyTable";

var seeAllTableOrders = "tablesAndBillController/seeAllTableOrders";
var removeSomeOrder = "tablesAndBillController/removeSomeOrder";
var getAllRestaurantBeverages = "tablesAndBillController/getAllRestaurantBeverages";
var getAllRestaurantMeals = "tablesAndBillController/getAllRestaurantMeals";
var addNewSomeOrder = "tablesAndBillController/addNewSomeOrder";

var prepareBill = "tablesAndBillController/prepareBill";
var finishBill = "tablesAndBillController/finishBill";


var goidforever;


$('#openBtn').click(function(){
    $('#myModal').modal({show:true})
});


function provjeraskaFunkcija()
{
	console.log("USLO U OVO NEKO YES");
	$.ajax(
			{
				type:'POST',
				url:isKonobar,
				dataType : "text",
				success : function(data)
				{
					console.log("DATA1");
					console.log(data);
					if(data=="jeste")
						{
							console.log("USLO U JESTE");
							$('#working-shift-calendar-button').show();
							$('#orders-button').show();
							$('#tables-button').show();
						}
					else
						{
							console.log("USLO U NIJE");
							$('#working-shift-calendar-button').show();
							$('#orders-button').show();
							$('#tables-button').hide();
						}
				}
				
			});
}

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
						
							provjeraskaFunkcija();
							
						
							
						}
					else if(data=="nijeCovjek")
						{
						$('#working-shift-calendar-button').hide();
						$('#orders-button').hide();
						$('#tables-button').hide();
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
	$('#seeTableOrdersDiv').hide();
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
			
			$('#seeTableOrdersDiv').hide();
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
						var waiter = wsofl.waiterID;
						var guestOID = wsofl.guestOrderID;
						//var guestsOrder = wsofl.guestsOrder;
						
						var singleTable = {};
							singleTable['isOccupied']=isOccupied;
							singleTable['positionX']=positionX;
							singleTable['positionY']=positionY;
							singleTable['tableID']=tableID;
							singleTable['resTableID'] = resTableID;
							singleTable['areaID'] = aID;
							singleTable['waiter']= waiter;
							singleTable['guestOID']= guestOID;
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
						
						console.log("-----------");
						console.log(isOccupied);
						console.log(positionX);
						console.log(positionY);
						console.log(tableID);
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
					var w;
					var g;
					//var guestsOrderx;
					
					
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
								w= thingy.waiter;
								g= thingy.guestOID;
								
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
						
						if(oc==true)
							{
								if(w==-1)
								{
										var tekstic = '<td height="60",width="60", bgcolor="#00BFFF"><form id="tableForm" action="" method="post" role="form" class="tableForm" >'
												+'<input type="hidden" id="tableID" name="tableID" value="'+id+'" >'
												+'<input type="hidden" id="oc" name="oc" value="'+oc+'" >'
												+'<input type="hidden" id="areaID" name="areaID" value="'+areaID+'" >'
												+'<input type="hidden" id="g" name="g" value="'+g+'" >'
												+'<input type="hidden" id="w" name="w" value="'+w+'" >'
												
												//+'<input type="hidden" id="guestsOrderx" name="guestsOrderx" value="'+guestsOrderx+'" >'
												+'<input type="submit" name="tab-submit" id="tab-submit" tabindex="7" role="button" class="form-control btn btn-success" value="'+resTID+'">'
												+'</form></td>';
								}
								else
								{
									var tekstic = '<td height="60",width="60", bgcolor="#FF0000"><form id="tableForm" action="" method="post" role="form" class="tableForm" >'
										+'<input type="hidden" id="tableID" name="tableID" value="'+id+'" >'
										+'<input type="hidden" id="oc" name="oc" value="'+oc+'" >'
										+'<input type="hidden" id="areaID" name="areaID" value="'+areaID+'" >'
										+'<input type="hidden" id="g" name="g" value="'+g+'" >'
										+'<input type="hidden" id="w" name="w" value="'+w+'" >'
										//+'<input type="hidden" id="guestsOrderx" name="guestsOrderx" value="'+guestsOrderx+'" >'
										+'<input type="submit" name="tab-submit" id="tab-submit" tabindex="7" role="button" class="form-control btn btn-success" value="'+resTID+'">'
										+'</form></td>';
								}
							}
						else
							{
							var tekstic = '<td height="60",width="60", bgcolor="#7FFF00"><form id="tableForm" action="" method="post" role="form" class="tableForm" >'
								+'<input type="hidden" id="tableID" name="tableID" value="'+id+'" >'
								+'<input type="hidden" id="oc" name="oc" value="'+oc+'" >'
								+'<input type="hidden" id="areaID" name="areaID" value="'+areaID+'" >'
								+'<input type="hidden" id="g" name="g" value="'+g+'" >'
								+'<input type="hidden" id="w" name="w" value="'+w+'" >'
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
						var canceled = wsofl.canceled;
						
						
						if(canceled!="canceled")
						{
						
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
							
							canceled=' <form id="canForm" action="" method="post" role="form" class="canForm" >'
								+'<input type="hidden" id="orderIDh" name="orderIDh" value="'+orderId+'" >'
								+'<input type="hidden" id="whatIsIt" name="whatIsIt" value="'+whatIsIt+'" >'
								+'<input type="submit" name="canForm-submit" id="accepted-submit" tabindex="7" role="button" class="form-control btn btn-success" value="Cancel">'
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
										+ canceled
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

$(document).on('click', '.canForm',function(e)
		{
			e.preventDefault();
			console.log('canceled this thingy thing');
			var orderxxID = $(this).find("input[name=orderIDh]").val();
			var whatIsItxx = $(this).find("input[name=whatIsIt]").val();
			console.log(orderxxID);
			console.log(whatIsItxx);
			$.ajax({
				type:'POST',
				url: canOrder,
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
	var g = $(this).find("input[name=g]").val();
	console.log("zauzmi Sto");
	console.log(areaID);
	$.ajax({
		type:'POST',
		url : occupyTableThingUrl,
		contentType:'application/json',
		dataType : "text",
		data: formatToTablePrintID(tableID,g),
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

$(document).on('click', '.ponistiOrder',function(e)
		{
			e.preventDefault();
			//var tableID = $(this).find("input[name=tableIDw]").val();
			//var areaID = $(this).find("input[name=areaID]").val();
			var orderIDZaPonistenje = $(this).find("input[name=orderIDZaPonistenje]").val();
			var foodOrDrink = $(this).find("input[name=foodOrDrink]").val();
			var table = $(this).find("input[name=table]").val();
			var g = $(this).find("input[name=g]").val();
			//console.log("zauzmi Sto");
			//console.log(areaID);
			$.ajax({
				type:'POST',
				url : removeSomeOrder,
				contentType:'application/json',
				dataType : "text",
				data: formatToOrderImmitationxy(orderIDZaPonistenje,foodOrDrink,table,g),
				success : function(data)
				{
					printTableOrders(data);
					
				}	
			});
				
		});

$(document).on('click', '.dnnbForm',function(e)
		{
			e.preventDefault();
			$.ajax({
				type:'POST',
				url : getAllRestaurantBeverages,
				dataType : "text",
				success : function(data)
				{
					//printTableOrders(data);
					console.log(data);
					data = $.parseJSON(data);
					var list = data == null ? [] : (data instanceof Array ? data : [data]);
					var tr = $('<tr></tr>');
					var t1 = '<form id="saveNewOrderB" action="" method="post" role="form" class="saveNewOrderB" >'
						+ '<td><select  id="beverageSelect" name="beverageSelect"></select></td>'
						+ '<td>Quantity: <input type="number" name="Quantity"></td>'
						+ '<td>Note: <input type="text" name = "note"></td>'			
						+'<td><input type="submit" name="saveNewOrderB-submit" role="button" id="saveNewOrderB-submit" tabindex="7" class="form-control btn btn-success" value="SAVE">'
						+ '<input type="hidden" id="goidforever" name="goidforever" value="'+goidforever+'" ></td>'
						+'</form>';
					tr.append(t1);
					$('#tablesData2').append(tr);
					$.each(list,function(index,wsofl)
							{
								text= ' <option value="'+wsofl.beveragesId+'">'+wsofl.beveragesName+'</option>';
								$('#beverageSelect').append(text);
						
							});
				}	
			});
				
		});


$(document).on('submit', '.saveNewOrderB',function(e)
		{
			e.preventDefault();
			//console.log("OJHAAAAAAAAAAAAAAAAAAAAAAXXX")
			console.log("OJHAAAAAAAAAAAAAAAAAAAAAA XXX")
			//e.preventDefault();
			//var tableID = $(this).find("input[name=tableIDw]").val();
			//var areaID = $(this).find("input[name=areaID]").val();
			
			//var gico = $(this).find("input[name=goidforever]").val();
			var gico = goidforever;
			console.log("GICO ::::::::::::");
			console.log(gico);
			
			var exw = document.getElementById("beverageSelect");
			console.log(exw);
			var bevId = exw.options[exw.selectedIndex].value;
			var quantity = $(this).find("input[name=Quantity]").val();
			var note = $(this).find("input[name=note]").val();
			
			console.log("OJHAAAAAAAAAAAAAAAAAAAAAA")
			
			//console.log("zauzmi Sto");
			//console.log(areaID);
			$.ajax({
				type:'POST',
				url : addNewSomeOrder,
				contentType:'application/json',
				dataType : "text",
				data: formatToOrderImmitationasd(gico,bevId,quantity,note,"b"),
				success : function(data)
				{
					printTableOrders(data);
					
				}	
			});
				
		});

$(document).on('submit', '.saveNewOrderM',function(e)
		{
			e.preventDefault();
			//var tableID = $(this).find("input[name=tableIDw]").val();
			//var areaID = $(this).find("input[name=areaID]").val();
			
			var gico = $(this).find("input[name=goidforever]").val();
			var exw = document.getElementById("mealsSelect");
			console.log(exw);
			var bevId = exw.options[exw.selectedIndex].value;
			var quantity = $(this).find("input[name=Quantity]").val();
			var note = $(this).find("input[name=note]").val();
			
			console.log("OJHAAAAAAAAAAAAAAAAAAAAAA")
			
			//console.log("zauzmi Sto");
			//console.log(areaID);
			$.ajax({
				type:'POST',
				url : addNewSomeOrder,
				contentType:'application/json',
				dataType : "text",
				data: formatToOrderImmitationasd(gico,bevId,quantity,note,"m"),
				success : function(data)
				{
					printTableOrders(data);
					
				}	
			});
				
		});



function formatToOrderImmitationasd(gico, bevId,quantity,note, wii)
{
	return JSON.stringify(
			{
				"guestOrderID" : gico,
				"whatWasOrderedId" : bevId,
				"quantity" : quantity,
				"note" : note,
				"whatIsIt" : wii
			}
	);
}


$(document).on('click', '.dnnmForm',function(e)
		{
			e.preventDefault();
			$.ajax({
				type:'POST',
				url : getAllRestaurantMeals,
				dataType : "text",
				success : function(data)
				{
					//printTableOrders(data);
					console.log(data);
					data = $.parseJSON(data);
					var list = data == null ? [] : (data instanceof Array ? data : [data]);
					var tr = $('<tr></tr>');
					var t1 = '<form id="saveNewOrderM" action="" method="post" role="form" class="saveNewOrderM" >'
						+ '<td><select  id="mealsSelect" name="mealsSelect"></select></td>'
						+ '<td>Quantity: <input type="number" name="Quantity"></td>'
						+ '<td>Note: <input type="text" name = "note"></td>'			
						+ '<td><input type="submit" name="saveNewOrderM-submit" role="button" id="saveNewOrderM-submit" tabindex="7" class="form-control btn btn-success" value="SAVE">'
						+ '<input type="hidden" id="goidforever" name="goidforever" value="'+goidforever+'" ></td>'
						+'</form>';
					tr.append(t1);
					$('#tablesData2').append(tr);
					$.each(list,function(index,wsofl)
							{
								text= ' <option value="'+wsofl.menuMealId+'">'+wsofl.menuMealDescription+'</option>';
								$('#mealsSelect').append(text);
						
							});
				}	
			});
				
		});






function printTableOrders(data)
{
	//e.preventDefault();
	console.log(data);
	data = $.parseJSON(data);
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	
	
	$('#tablesData2').empty();
	$('#seeTableOrdersDiv').show();
	var goid;
	var tid;
	$.each(list,function(index,wsofl)
			{
				console.log("RADI nesto..");
				var tr = $('<tr></tr>');
				goid = wsofl.guestOrderID;
				tid = wsofl.table;
				
				if(wsofl.whatWasOrdered != null)
					{
					
					
				tekstic = "<td>"+wsofl.whatWasOrdered+"</td>"
						+"<td> Quantity: "+wsofl.quantity +"</td>"
						+"<td> Note: "+ wsofl.note + "</td>"
						+"<td> Status: "+ wsofl.status + "</td>"
						+'<td><form id="ponistiOrder" action="" method="post" role="form" class="ponistiOrder" >'
						+'<input type="hidden" id="orderIDZaPonistenje" name="orderIDZaPonistenje" value="'+wsofl.orderId+'" >'
						+'<input type="hidden" id="foodOrDrink" name="foodOrDrink" value="'+wsofl.whatIsIt+'" >'
						+'<input type="hidden" id="table" name="table" value="'+wsofl.table+'" >'
						+'<input type="hidden" id="g" name="g" value="'+wsofl.guestOrderID+'" >'
						+'<input type="submit" name="ponisti-submit" role="button" id="ponisti-submit" tabindex="7" class="form-control btn btn-success" value="Remove">'
						+'</form></td>';
				tr.append(tekstic);
				$('#tablesData2').append(tr);
					}
				
			});
	
	goidforever = goid;
	
	$('#dvijeOpcije').empty();
	var dugmeZaDodavanjeNovogJela = '<form id="dnnmForm" action="" method="post" role="form" class="dnnmForm" >'
	+'<input type="hidden" id="guestOrderID" name="guestOrderID" value="'+goid+'" >'
	//+'<input type="hidden" id="table" name="table" value="'+tid+'" >'
	+'<input type="submit" name="dnnForm-submit" role="button" id="dnnForm-submit" tabindex="7" class="form-control btn btn-success" value="Add new Meal">'
	+'</form>';
	
	var dugmeZaDodavanjeNovogPica = '<form id="dnnbForm" action="" method="post" role="form" class="dnnbForm" >'
		+'<input type="hidden" id="guestOrderID" name="guestOrderID" value="'+goid+'" >'
		//+'<input type="hidden" id="table" name="table" value="'+tid+'" >'
		+'<input type="submit" name="dnnForm-submit" role="button" id="dnnForm-submit" tabindex="7" class="form-control btn btn-success" value="Add new Beverage">'
		+'</form>';
	
	var dugmeZaKreirajRacun ='<div><form id="kreirajRacun" action="" method="post" role="form" class="kreirajRacun" >'
	+'<input type="hidden" id="guestOrderID" name="guestOrderID" value="'+goid+'" >'
	//+'<input type="hidden" id="table" name="table" value="'+tid+'" >'
	+'<input type="submit" name="kreirajRacun-submit" role="button" id="kreirajRacun-submit" tabindex="7" class="form-control btn btn-success" value="Create BILL and unoccupy table">'
	+'</form></div>';
	
	//console.log(dugmeZaKreirajRacun);
	
	$('#dvijeOpcije').append(dugmeZaDodavanjeNovogJela);
	$('#dvijeOpcije').append(dugmeZaDodavanjeNovogPica);
	$('#dvijeOpcije').append(dugmeZaKreirajRacun);
	
	$('#dvijeOpcije').show();
	$('#tablesData2').show();
	$('#seeTableOrdersDiv').show();
	
}

$(document).on('click', '.kreirajRacun',function(e)
		{
			e.preventDefault();
			var gico = $(this).find("input[name=guestOrderID]").val();
			
			console.log("OJHAAAAAAAAAAAAAAAAAAAAAA uslo u kreiranje racuna")
			$.ajax({
				type:'POST',
				url : prepareBill,
				contentType:'application/json',
				dataType : "text",
				data: formatToOrderImmitationasd(gico,1,1,"wut",""),
				success : function(data)
				{
					data = $.parseJSON(data);
					var goxid= data.guestsOrderId;
					var prxice= data.price;
					
					console.log(data);
					console.log(goxid);
					console.log(prxice);
					
					var dugmeZaZavrsiRacun ='<div><form id="zavrsiRacun" action="" method="post" role="form" class="zavrsiRacun" >'
						+'<input type="hidden" id="guestOrderID" name="guestOrderID" value="'+goxid+'" >'
						+'<input type="hidden" id="price" name="price" value="'+prxice+'" >'
						//+'<input type="hidden" id="table" name="table" value="'+tid+'" >'
						+'<input type="submit" name="zavrsiRacun-submit" role="button" id="zavrsiRacun-submit" tabindex="7" class="form-control btn btn-success" value="Close '+prxice+' &euro; worth Bill and free the table">'
						+'</form></div>';
					
					$('#dvijeOpcije').empty();
					$('#dvijeOpcije').append(dugmeZaZavrsiRacun);
					
				}	
			});
				
		});


$(document).on('click', '.zavrsiRacun',function(e)
		{
			e.preventDefault();
			var gico = $(this).find("input[name=guestOrderID]").val();
			
			
			console.log("OJHAAAAAAAAAAAAAAAAAAAAAA uslo u zavrsi racuna")
			$.ajax({
				type:'POST',
				url : finishBill,
				contentType:'application/json',
				dataType : "text",
				data: formatToOrderImmitationasd(gico,1,1,"wut",""),
				success : function(data)
				{
					printTables(data);
					$('#dvijeOpcije').empty();
					$('#seeTableOrdersDiv').hide();
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
			var g = $(this).find("input[name=g]").val();
			var w = $(this).find("input[name=w]").val();
			
			console.log("GGGGGGGGGGGG: [table form]"+ g);

			console.log(typeof oc);
			$('#seeTableOrdersDiv').hide();
			
			if(oc=="true" )
			{
				if(g!=-1 && w!=-1)
				{
					$('#choseWhat').empty();

					$.ajax({
						type:'POST',
						url : seeAllTableOrders,
						contentType:'application/json',
						dataType : "text",
						data: formatToTablePrintID(tableID,g),
						success : function(data)
						{
							console.log("OVO SE POSLALO DA VIDI ALL TABLE ORDERS");
							//$('#tablesData').empty();
							printTableOrders(data);

							$('#seeTableOrdersDiv').show();

						}	
					});
				}
				if(g!=-1 && w==-1)
				{
					
					$('#seeTableOrdersDiv').hide();

					$('#choseWhat').empty();
					
					tekst_za_append='<form id="zauzmiSto" action="" method="post" role="form" class="zauzmiSto" >'
						+'<input type="hidden" id="tableIDw" name="tableIDw" value="'+tableID+'" >'
						+'<input type="hidden" id="areaID" name="areaID" value="'+areaID+'" >'
						+'<input type="hidden" id="g" name="g" value="'+g+'" >'
						+'<input type="submit" name="zauzmiSto-submit" role="button" id="zauzmiSto-submit" tabindex="7" class="form-control btn btn-success" value="Tend to this Table">'
						+'</form>';

					$('#choseWhat').append(tekst_za_append);

					console.log("Wut mate? u wut?");

				}
			}
			else if(oc == "false")
			{
				
			}
			console.log(areaID);
			console.log(oc);
			
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

function formatToTablePrintID(idXXX,g)
{
	return JSON.stringify(
			{
				"generalTableID":idXXX,
				"guestOrderID":g
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

function formatToOrderImmitationxy(orderIDZaPonistenje,foodOrDrink,table,g)
{
	/*
	var orderIDZaPonistenje = $(this).find("input[name=orderIDZaPonistenje]").val();
	var foodOrDrink = $(this).find("input[name=foodOrDrink]").val();
	var table = $(this).find("input[name=table]").val();
	*/
	return JSON.stringify(
			{
				"orderId":orderIDZaPonistenje,
				"whatIsIt":foodOrDrink,
				"table":table,
				"guestOrderID":g
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


