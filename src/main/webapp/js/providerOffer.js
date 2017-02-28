var getAllOffer="offers/getAllOfferProvider"
var makeOfferProvider = "offers/makeOfferProvider"

$( document ).ready(function() {
	$('#allOffersPanel').show();
	$('#makeOfferPanel').hide();
	printAllYourOffer();
});

$(document).on('click', '#allOffersButton', function(e) {
	$('#allOffersPanel').show();
	$('#makeOfferPanel').hide();
	printAllYourOffer();
});

$(document).on('click', '#makeOfferButton', function(e) {
	$('#allOffersPanel').hide();
	$('#makeOfferPanel').show();
	
	selectAllOffer();
	
});

function printAllYourOffer() {
	$.ajax({
		type : 'GET',
		url : getAllOffer,
		dataType : "json", // data type of response
		success : function(data) {
			printAllOffer(data);
		}
	});
}

function printAllOffer(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	$('#allOfferData').empty();
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list,function(index, offerManager) {
						var tr = $('<tr></tr>');
						tr.append('<td>' + offerManager.offerManagerId + '</td>'+ 
										'<td>' + offerManager.deadline + '</td>' +
										'<td>' + offerManager.note + '</td>');
						$('#allOfferData').append(tr);
					});
}

function selectAllOffer() {
	
	$(".selectOfferManager").empty();
	$.ajax({
		type : 'GET',
		url : getAllOffer,
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			$(".selectOfferManager").empty();
			var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
			var selectRestaurant = $(".selectOfferManager");
			$.each(list, function(index, offerManager) {
				var li = $('<option value="'+offerManager.offerManagerId+'">' + offerManager.offerManagerId + ' </option>');
				$(selectRestaurant).append(li);
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
}

//--------------MAKE OFFER
$(document).on('submit', '.makeOfferForm', function(e) {
	e.preventDefault();
	var offerProviderId=$('.selectOfferManager').find(":selected").val();
	var note = $(this).find("textarea[name=textAreaNote]").val();
	var price=$(this).find("input[name=price]").val();

	
	console.log(offerProviderId + " " + note + " " +price)
	$.ajax({
		type : 'POST',
		url : makeOfferProvider,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONMakeOffer(offerProviderId, note, price),
		success : function(data) {
			location.reload(true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
});

function formToJSONMakeOffer(offerProviderId, note, price) {
	return JSON.stringify({
		"offerProviderId" : offerProviderId,
		"note" : note,
		"price" : price,
	});
}
