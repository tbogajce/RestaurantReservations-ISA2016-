var getAllYourOffer = "offers/getAllYourOffer";
var createOfferManager = "offers/createOfferManager";
var pendingOffersURL = "offers/pendingOffers";
var acceptOfferURL = "offers/acceptOffer";

$( document ).ready(function() {
	$('#yourOffersPanel').show();
	$('#createOfferPanel').hide();
	$('#pendingOffersPanel').hide();
	printAllYourOffer();
});

$(document).on('click', '#yourOffersButton', function(e) {
	$('#yourOffersPanel').show();
	$('#createOfferPanel').hide();
	$('#pendingOffersPanel').hide();
	printAllYourOffer();

});

$(document).on('click', '#createOfferButton', function(e) {
	$('#yourOffersPanel').hide();
	$('#createOfferPanel').show();
	$('#pendingOffersPanel').hide();

});

$(document).on('click', '#pendingOffersButton', function(e) {
	$('#yourOffersPanel').hide();
	$('#createOfferPanel').hide();
	$('#pendingOffersPanel').show();
	
	getPendingOffers();
});

function printAllYourOffer() {
	$.ajax({
		type : 'GET',
		url : getAllYourOffer,
		dataType : "json", // data type of response
		success : function(data) {
			allYourOfferPrint(data);
		}
	});
}

function allYourOfferPrint(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	$('#yourOffersData').empty();
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list,function(index, offerManager) {
						var tr = $('<tr></tr>');
						tr.append('<td>' + offerManager.offerManagerId + '</td>'+ 
										'<td>' + offerManager.deadline + '</td>' +
										'<td>' + offerManager.note + '</td>' +
										'<td>' + offerManager.finished + '</td>'
						);
						$('#yourOffersData').append(tr);
					});
}

//-----------KREIRANJE NOVE POTRAZNJE

$(document).on('submit', '.createOfferForm', function(e) {
	e.preventDefault();
	var deadline=$(this).find("input[name=deadline]").val();
	var note = $(this).find("textarea[name=textAreaNote]").val();
	
	console.log(deadline + " " + note )
	$.ajax({
		type : 'POST',
		url : createOfferManager,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONCreateOffer(deadline, note),
		success : function(data) {
			location.reload(true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
	
});

function formToJSONCreateOffer(deadline, note) {
	return JSON.stringify({
		"deadline" : deadline,
		"note" : note,
	});
}

//------------GET PENDING OFFERS
function getPendingOffers() {
	$.ajax({
		type : 'GET',
		url : pendingOffersURL,
		dataType : "json", // data type of response
		success : function(data) {
			printPendingOffers(data);
		}
	});
}

function printPendingOffers(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	$('#pendingOffersData').empty();
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list,function(index, offerProvider) {
						var tr = $('<tr></tr>');
						tr.append('<td>' + offerProvider.offerProviderId + '</td>'+ 
										'<td>' + offerProvider.note + '</td>' +
										'<td>' + offerProvider.price + '</td>' +
										'<td>' + '<form class="acceptOffer" > ' + 
										'<input type="hidden" name="offerProviderId" value=' + offerProvider.offerProviderId +'> '+
										'<input type="submit" class="btn btn-primary btn-sm" role="button" value="Accept offer"> '
										+ '</form></td>'
						);
						$('#pendingOffersData').append(tr);
					});
}

$(document).on('click', '.acceptOffer', function(e) {
	e.preventDefault();
	console.log('klik na accept');
	var offerProviderId=$(this).find("input[type=hidden]").val();
	console.log(offerProviderId);
	$.ajax({
		type : 'POST',
		url : acceptOfferURL,
		contentType : 'application/json',
		dataType : "text",
		data : formToJSONAccept(offerProviderId),
		success : function(data) {
			location.reload(true);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
});

function formToJSONAccept(offerProviderId) {
	return JSON.stringify({
		"offerProviderId" : offerProviderId,
	});
}
