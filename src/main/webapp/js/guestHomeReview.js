

var getHistoryRecords = "ratingsController/getHistoryRecords";
var rateRestaurant = "ratingsController/rateRestaurant";

var rateMeal = "ratingsController/rateMeal";
var rateService = "ratingsController/rateService";

$(document).on('click', '#historyVisitsButton', function(e) {
	$('#tableReservationPanel').hide();
	$('#callFriendPanel').hide();
	$('#makeOrderPanel').hide();
	$('#historyVisitsPanel').show();
	$('#allRestaurantsPanel').hide();
	
	//tu dodas poziv nekih funkcija koje se okidaju cim se otvori tab Review Restaurants
	
	
	$.ajax(
			{
				type:'POST',
				url:getHistoryRecords,
				dataType : "text",
				success : function(data)
				{
					printHistoryRecords(data);
				}
				
			});
	
	
	
});


function printHistoryRecords(data)
{
	data = $.parseJSON(data);
	var brojacxx=0;
	$('#reviewData').empty();
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list,function(index, wsofl) {
		/*
		<fieldset class="rating">
	    <legend>Please rate:</legend>
	    <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="Rocks!">5 stars</label>
	    <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="Pretty good">4 stars</label>
	    <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="Meh">3 stars</label>
	    <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="Kinda bad">2 stars</label>
	    <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="Sucks big time">1 star</label>
	    </fieldset>
		 */
		var tr = $('<tr></tr>');
		
		var ocjenaRestorana = wsofl.ocjenaRestorana;
		var ocjenaHrane = wsofl.ocjenaHrane;
		var ocjenaUsluge = wsofl.ocjenaUsluge;
		var historyRecordId = wsofl.historyRecordId;
		
		var stringOcjRest = '<form id="restoranRejtingForm" action="" method="post" role="form" class="restoranRejtingForm" >'
			+ '<select id="restoranRejtingSelect'+brojacxx+'" name="restoranRejtingSelect'+brojacxx+'"></select>'
			+ '<input type="hidden" id="historyRecordId" name="historyRecordId" value="'+historyRecordId+'" >'
			+ '<input type="hidden" id="brojacxx" name="brojacxx" value="'+brojacxx+'" >'
			+'<input type="submit" name="zauzmiSto-submit" role="button" id="zauzmiSto-submit" tabindex="7" class="form-control btn btn-success" value="Submit">'

			+'</form>';
		
		
		
		var stringOcjHrane = '<form id="hranaRejtingForm" action="" method="post" role="form" class="hranaRejtingForm" >'
			+ '<select  id="hranaRejtingSelect'+brojacxx+'" name="hranaRejtingSelect'+brojacxx+'"></select>'
			+ '<input type="hidden" id="historyRecordId" name="historyRecordId" value="'+historyRecordId+'" >'
			+ '<input type="hidden" id="brojacxx" name="brojacxx" value="'+brojacxx+'" >'
			+'<input type="submit" name="zauzmiSto-submit" role="button" id="zauzmiSto-submit" tabindex="7" class="form-control btn btn-success" value="Submit">'

			+'</form>';
		
		
		
		var stringOcjUsluge = '<form id="uslugaRejtingForm" action="" method="post" role="form" class="uslugaRejtingForm" >'
			+ '<select  id="uslugaRejtingSelect'+brojacxx+'" name="uslugaRejtingSelect'+brojacxx+'"></select>'
			+ '<input type="hidden" id="historyRecordId" name="historyRecordId" value="'+historyRecordId+'" >'
			+ '<input type="hidden" id="brojacxx" name="brojacxx" value="'+brojacxx+'" >'
			+'<input type="submit" name="zauzmiSto-submit" role="button" id="zauzmiSto-submit" tabindex="7" class="form-control btn btn-success" value="Submit">'
			+'</form>';
		
		
		
		var appendacija= '<td>'+wsofl.imeRestorana+'</td>'
						+'<td>'+wsofl.datumPosjete+'</td>'
						+'<td>'+wsofl.hrana+'</td>'
						+'<td>'+wsofl.pice+'</td>'
						+'<td>'+stringOcjRest+'</td>'
						+'<td>'+stringOcjHrane+'</td>'
						+'<td>'+stringOcjUsluge+'</td>'
						
		tr.append(appendacija);
		$('#reviewData').append(tr);
		
		
		for (i = 0; i < 6; i++) { 
		    if(ocjenaRestorana == i)
		    	{
		    		text= ' <option selected="selected" value="'+i+'">'+i+'</option>';
		    		$('#restoranRejtingSelect'+brojacxx).append(text);
		    	}
		    else
		    	{
		    		text= ' <option value="'+i+'">'+i+'</option>';
		    		console.log(i);
		    		var mm = "#restoranRejtingSelect"+brojacxx
		    		console.log(mm);
		    		$(mm).append(text);
		    	}
		}
		for (i = 0; i < 6; i++) { 
		    if(ocjenaHrane == i)
		    	{
		    		text= ' <option selected="selected" value="'+i+'">'+i+'</option>';
		    		$('#hranaRejtingSelect'+brojacxx).append(text);
		    	}
		    else
		    	{
		    		text= ' <option value="'+i+'">'+i+'</option>';
		    		$('#hranaRejtingSelect'+brojacxx).append(text);
		    	}
		}
		for (i = 0; i < 6; i++) { 
		    if(ocjenaUsluge == i)
		    	{
		    		text= ' <option selected="selected" value="'+i+'">'+i+'</option>';
		    		$('#uslugaRejtingSelect'+brojacxx).append(text);
		    	}
		    else
		    	{
		    		text= ' <option value="'+i+'">'+i+'</option>';
		    		$('#uslugaRejtingSelect'+brojacxx).append(text);
		    	}
		}
		
		
		
		brojacxx= brojacxx+1;
	});
}

$(document).on('submit', '.restoranRejtingForm',function(e)
		{
			e.preventDefault();
			console.log('accepted this thingy thing');
			var brojacxx = $(this).find("input[name=brojacxx]").val();
			var hrid =  $(this).find("input[name=historyRecordId]").val();
			console.log(brojacxx);
			var texty = "restoranRejtingSelect"+brojacxx;
			var exw = document.getElementById(texty);
			//console.log(texty);
			//var exw = $(texty);
			//var exw = $(this).find("select[name=restoranRejtingSelect"+brojacxx+"]").val();
			console.log("EEW "+exw);
			var vrijed = exw.options[exw.selectedIndex].value;
			//console.log(orderxxID);
			//console.log(whatIsItxx);
			console.log(vrijed);
			$.ajax({
				type:'POST',
				url: rateRestaurant,
				contentType:'application/json',
				data: formatToHRI(hrid,vrijed),
				success : function(data)
				{
					$.ajax(
							{
								type:'POST',
								url:getHistoryRecords,
								dataType : "text",
								success : function(data)
								{
									printHistoryRecords(data);
								}
								
							});
				}
				
			});
			
	});

$(document).on('submit', '.hranaRejtingForm',function(e)
		{
			e.preventDefault();
			console.log('accepted this thingy thing');
			var brojacxx = $(this).find("input[name=brojacxx]").val();
			var hrid =  $(this).find("input[name=historyRecordId]").val();
			console.log(brojacxx);
			var texty = "hranaRejtingSelect"+brojacxx;
			var exw = document.getElementById(texty);
			//console.log(texty);
			//var exw = $(texty);
			//var exw = $(this).find("select[name=restoranRejtingSelect"+brojacxx+"]").val();
			console.log("EEW "+exw);
			var vrijed = exw.options[exw.selectedIndex].value;
			//console.log(orderxxID);
			//console.log(whatIsItxx);
			console.log(vrijed);
			$.ajax({
				type:'POST',
				url: rateMeal,
				contentType:'application/json',
				data: formatToHRI(hrid,vrijed),
				success : function(data)
				{
					$.ajax(
							{
								type:'POST',
								url:getHistoryRecords,
								dataType : "text",
								success : function(data)
								{
									printHistoryRecords(data);
								}
								
							});
				}
				
			});
			
	});

$(document).on('submit', '.uslugaRejtingForm',function(e)
		{
			e.preventDefault();
			console.log('accepted this thingy thing');
			var brojacxx = $(this).find("input[name=brojacxx]").val();
			var hrid =  $(this).find("input[name=historyRecordId]").val();
			console.log(brojacxx);
			var texty = "uslugaRejtingSelect"+brojacxx;
			var exw = document.getElementById(texty);
			//console.log(texty);
			//var exw = $(texty);
			//var exw = $(this).find("select[name=restoranRejtingSelect"+brojacxx+"]").val();
			console.log("EEW "+exw);
			var vrijed = exw.options[exw.selectedIndex].value;
			//console.log(orderxxID);
			//console.log(whatIsItxx);
			console.log(vrijed);
			$.ajax({
				type:'POST',
				url: rateService,
				contentType:'application/json',
				data: formatToHRI(hrid,vrijed),
				success : function(data)
				{
					$.ajax(
							{
								type:'POST',
								url:getHistoryRecords,
								dataType : "text",
								success : function(data)
								{
									printHistoryRecords(data);
								}
								
							});
				}
				
			});
			
	});

function formatToHRI(hrid,vrijed)
{
	return JSON.stringify(
			{
				"historyRecordId" : hrid,
				"ocjenaRestorana" : vrijed
			}
	);
}
