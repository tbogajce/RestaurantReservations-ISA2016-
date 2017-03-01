
var getAreasForRM = "restaurantManagerController/getAreasForRM";
var getAreaTablesRM = "restaurantManagerController/getAreaTablesRM";
var getAreaSpaceRM = "restaurantManagerController/getAreaSpaceRM";
var removeTableRM = "restaurantManagerController/removeTableRM";
var addTableRM = "restaurantManagerController/addTableRM";


var areaSpaceX=0;
var areaSpaceY=0;
var areaIDx =0;

function formatToAreaImmitation(areaID)
{
	return JSON.stringify(
			{
				"areaID":areaID	
			}
	);
}

$(document).on('click', '#create-new-sc', function(e) {
//	$('#create-new-sc').click(function(e) {
	// e.preventDefault();
	//console.log("USLO JE U OVO BAREM 1...")
	e.preventDefault();
	
	$("#seating-config-div").delay(300).fadeIn(100);
	$("#greetings").fadeOut(100);
	$("#beveragesListForm").fadeOut(100);
	$("#menuListForm").fadeOut(100);
	$("#ws-date-pick-form").fadeOut(100);
	$("#workShiftDataForm").fadeOut(100);
	$("#add-new-area-form").fadeOut(100);
	$("#weListForm").fadeOut(100);
	$("#add-new-employee-form").fadeOut(100);
	$("#area-pick-form").fadeOut(100);
	$("#add-new-provider-form").fadeOut(100);
	$("#restaurantRatingForm").fadeOut(100);
	$("#add-new-beverage-form").fadeOut(100);
	$("#add-new-menu-form").fadeOut(100);
	$("#add-new-shift-form").fadeOut(100);
	$("#add-new-segment-form").fadeOut(100);
	$("#edit-info-form").fadeOut(100);
	$('#create-new-employee').removeClass('active');
	$('#create-new-provider').removeClass('active');
	$('#create-new-beverage').removeClass('active');
	$('#create-new-shift').removeClass('active');
	$('#create-new-menu').removeClass('active');
	$('#create-new-report').removeClass('active');
	$('#edit-info').removeClass('active');
	$(this).addClass('active');
	//e.preventDefault();

	//console.log("USLO JE U OVO BAREM...")

	$.ajax(
			{
				type:'POST',
				url:getAreasForRM,
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

	$('#area-pick-form').show();

});



$(document).on('submit','.areaForm',function(e)
		{
	e.preventDefault();

	$('#seeTableOrdersDiv').hide();
	var e = document.getElementById("areaSelect");
	var  areaID = e.options[e.selectedIndex].value;
	areaIDx = areaID;

	$.ajax(
			{
				type:'POST',
				url:getAreaSpaceRM,
				contentType : 'application/json',
				dataType : "text",
				data : formatToAreaImmitation(areaID),
				success : function(data)
				{

					data = $.parseJSON(data);
					areaSpaceX = data.spaceX;
					areaSpaceY = data.spaceY;

					console.log(areaSpaceX);
					console.log(areaSpaceY);
					$.ajax(
							{
								type:'POST',
								url:getAreaTablesRM,
								contentType : 'application/json',
								dataType : "text",
								data : formatToAreaImmitation(areaID),
								success : function(data)
								{
									console.log("USLO U OVO ZA STOLOVE");
									//workingShiftPrint(data);
									console.log(data);
									printTables(data);
								}
							});	
				}
			});	


		});


function printTables(data)
{
	console.log(data);
	data = $.parseJSON(data);
	console.log(data);
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);


	console.log("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

	var listx=[];
	
	var areaID =0;


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

	for( var i = 0; i< areaSpaceX; i++)
	{
		var tr = $('<tr></tr>');
		for(var j = 0; j<areaSpaceY; j++)
		{

			var naslo=false;
			var id;
			var oc;
			var w;
			var g;
			
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
					
					
					console.log("STO: "+id);
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

					var tekstic = '<td height="60",width="60", bgcolor="#FF0000"><form id="tableForm" action="" method="post" role="form" class="tableForm" >'
						+'<input type="hidden" id="tableID" name="tableID" value="'+id+'" >'
						+'<input type="hidden" id="oc" name="oc" value="'+oc+'" >'
						+'<input type="hidden" id="areaID" name="areaID" value="'+areaID+'" >'
						+'<input type="hidden" id="g" name="g" value="'+g+'" >'
						+'<input type="hidden" id="w" name="w" value="'+w+'" >'
						+'<input type="submit" name="tab-submit" id="tab-submit" tabindex="7" role="button" class="form-control btn btn-success" value="'+resTID+'">'
						+'</form></td>';


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
				var tekstic = '<td height="60",width="60", bgcolor="#696969"><form id="addTable" action="" method="post" role="form" class="addTable" >'
					
					+'<input type="hidden" id="areaID" name="areaID" value="'+areaIDx+'" >'
					+'<input type="hidden" id="x" name="x" value="'+i+'" >'
					+'<input type="hidden" id="y" name="y" value="'+j+'" >'
					+'<input type="submit" name="tab-submit" id="tab-submit" tabindex="7" role="button" class="form-control btn btn-success" value="New">'
					+'</form></td>';
				
				tr.append(tekstic);
				//tr.append('<td bgcolor="#696969", height="60",width="60"></td>')
			}


		}
		$('#tablesData').append(tr);

	}



	//console.log("XXXX4");
	//console.log(listx);
}

$(document).on('click', '.tableForm',function(e)
		{
			//console.log("did something")
			console.log("JEL SE POZOVE UOPSTE OVO?")
			e.preventDefault();
			//console.log('accepted this thingy thing');
			var tableID = $(this).find("input[name=tableID]").val();
			var oc = $(this).find("input[name=oc]").val();
			var areaID = $(this).find("input[name=areaID]").val();
			//var g = $(this).find("input[name=g]").val();
			//var w = $(this).find("input[name=w]").val();
			
			//console.log("GGGGGGGGGGGG: [table form]"+ g);

			console.log(typeof oc);
			$('#seeTableOrdersDiv').hide();
			
			if(oc=="true" )
			{
				$('#choseWhat').empty();
				$('#choseWhat').append("<h2> This table is currently occupied and can not be removed! </h2> ");
				
				/*
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
				*/
			}
			else if(oc == "false")
			{
				$('#seeTableOrdersDiv').hide();

				$('#choseWhat').empty();
				
				tekst_za_append='<form id="removeTable" action="" method="post" role="form" class="removeTable" >'
					+'<input type="hidden" id="tableIDw" name="tableIDw" value="'+tableID+'" >'
					+'<input type="hidden" id="areaID" name="areaID" value="'+areaID+'" >'
					//+'<input type="hidden" id="g" name="g" value="'+g+'" >'
					+'<input type="submit" name="zauzmiSto-submit" role="button" id="zauzmiSto-submit" tabindex="7" class="form-control btn btn-success" value="Remove this table">'
					+'</form>';

				$('#choseWhat').append(tekst_za_append);
			}
			console.log(areaID);
			console.log(oc);
			
		});


$(document).on('click', '.removeTable',function(e)
		{
			e.preventDefault();
			var tableID = $(this).find("input[name=tableIDw]").val();
			var areaID = $(this).find("input[name=areaID]").val();
			//var g = $(this).find("input[name=g]").val();
			//console.log("zauzmi Sto");
			//console.log(areaID);
			$.ajax({
				type:'POST',
				url : removeTableRM,
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

$(document).on('click', '.addTable',function(e)
		{
	e.preventDefault();
	//var tableID = $(this).find("input[name=tableIDw]").val();
	var areaID = $(this).find("input[name=areaID]").val();
	var x = $(this).find("input[name=x]").val();
	var y = $(this).find("input[name=y]").val();
	//var g = $(this).find("input[name=g]").val();
	//console.log("zauzmi Sto");
	//console.log(areaID);

	$('#choseWhat').empty();
	tekst_za_append='<form id="addTable2" action="" method="post" role="form" class="addTable2" >'
		+'<input type="hidden" id="areaID" name="areaID" value="'+areaID+'" >'
		+'<input type="hidden" id="x" name="x" value="'+x+'" >'
		+'<input type="hidden" id="y" name="y" value="'+y+'" >'
		+'Number of seats: <input type="number" name="nos">'
		+'Number in restaurant: <input type="number" name="nir">'
		+'<input type="submit" name="zauzmiSto-submit" role="button" id="zauzmiSto-submit" tabindex="7" class="form-control btn btn-success" value="Add a new Table">'
		+'</form>';

	$('#choseWhat').append(tekst_za_append);


		});

$(document).on('submit', '.addTable2',function(e)
		{
			e.preventDefault();
			//var tableID = $(this).find("input[name=tableIDw]").val();
			var areaID = $(this).find("input[name=areaID]").val();
			var x = $(this).find("input[name=x]").val();
			var y = $(this).find("input[name=y]").val();
			var nos = $(this).find("input[name=nos]").val();
			var nor = $(this).find("input[name=nir]").val();
			
			
			//var y = $(this).find("input[name=y]").val();
			//var g = $(this).find("input[name=g]").val();
			//console.log("zauzmi Sto");
			//console.log(areaID);
			$.ajax({
				type:'POST',
				url : addTableRM,
				contentType:'application/json',
				dataType : "text",
				data: formatToTablePrintID2(areaID,x,y,nos,nor),
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



function printTables2(areaID)
{
	//e.preventDefault();
	$('#seeTableOrdersDiv').hide();
	$.ajax(
			{
				type:'POST',
				url:getAreaSpaceRM,
				contentType : 'application/json',
				dataType : "text",
				data : formatToAreaImmitation(areaID),
				success : function(data)
				{

					data = $.parseJSON(data);
					areaSpaceX = data.spaceX;
					areaSpaceY = data.spaceY;

					console.log(areaSpaceX);
					console.log(areaSpaceY);



					$.ajax(
							{
								type:'POST',
								url:getAreaTablesRM,
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
				}
			});	
}

function formatToTablePrintID(idXXX)
{
	return JSON.stringify(
			{
				"generalTableID":idXXX
			}
			);
	
}

function formatToTablePrintID2(areaID,positionX,positionY,numberOfSeats,numberInRestoraunt)
{
	return JSON.stringify(
			{
				"positionX":positionX,
				"positionY":positionY,
				"areaID":areaID,
				"guestOrderID":numberOfSeats,
				"resTableID":numberInRestoraunt
				
			}
			);
	
}


