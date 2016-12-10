var guestNamePrintURL = "users/guestName";
var guestData = "users/guestData";

$( document ).ready(function() {
	$('#guestProfilePanel').show();
	$('#friendsPanel').hide();
	$('#friendRequestsPanel').hide();
	
	guestNamePrint();
	printGuestData();
});

$(document).on('click', '#guestProfileButton', function(e) {
	$('.guestName').empty();
	$('#guestProfilePanel').show();
	$('#friendsPanel').hide();
	$('#friendRequestsPanel').hide();
	
	guestNamePrint();
	printGuestData();
	
});

$(document).on('click', '#friendsButton', function(e) {
	$('.guestName').empty();
	$('#guestProfilePanel').hide();
	$('#friendsPanel').show();
	$('#friendRequestsPanel').hide();
	
});

$(document).on('click', '#friendRequestsButton', function(e) {
	$('.guestName').empty();
	$('#guestProfilePanel').hide();
	$('#friendsPanel').hide();
	$('#friendRequestsPanel').show();
});


function guestNamePrint() {
	$.ajax({
		type : 'GET',
		url : guestNamePrintURL,
		dataType : "text", // data type of response
		success : function(data) {
			$('.guestName').append('<p>' + data + '</p>');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("AJAX ERROR: " + errorThrown);
		}
	});
}

//---------------------------------ISPISIVANJE PODATAKA KUPCA
function printGuestData() {
	$.ajax({
		type : 'GET',
		url : guestData,
		dataType : "json", // data type of response
		success : render2
	});
}

function render2(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	$('.payment-form').empty();
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list,
			function(index, user) {
				var option = $('<form class="formGuest"> ' +
										
						'<div class="form-group"> ' +
						' <label for="concept" class="col-sm-3 control-label">Email</label>' +
						'<div class="col-sm-9"> ' +
						'	<input type="text" class="form-control" name="email" ' +
						'		value="'+ user.email+' " readonly> ' +
						'</div> ' +
					'</div>' +
					'<div class="form-group"> ' +
					' <label for="concept" class="col-sm-3 control-label">Password</label>' +
					'<div class="col-sm-9"> ' +
					'	<input type="text" class="form-control" name="pass" ' +
					'		value="'+ user.user_password+' "> ' +
					'</div> ' +
				'</div>' +
				'<div class="form-group"> ' +
				' <label for="concept" class="col-sm-3 control-label">Name</label>' +
				'<div class="col-sm-9"> ' +
				'	<input type="text" class="form-control" name="name" ' +
				'		value="'+ user.user_name+' "> ' +
				'</div> ' +
			'</div>' +
			'<div class="form-group"> ' +
			' <label for="concept" class="col-sm-3 control-label">Surname</label>' +
			'<div class="col-sm-9"> ' +
			'	<input type="text" class="form-control" name="surname" ' +
			'		value="'+ user.user_surname+' "> ' +
			'</div> ' +
		'</div>' +
		'<div class="form-group"> ' +
		' <label for="concept" class="col-sm-3 control-label">Birth date</label>' +
		'<div class="col-sm-9"> ' +
		'	<input type="date" class="form-control" name="birthDate" ' +
		'		value="'+user.user_birth_date+'"> ' +
		'</div> ' +
	'</div>'+
	
	'<button class="btn btn-success btn-block login" type="submit">Save changes</button> '+
	'</form>');
				$('.payment-form').append(option);
			});
}

//---------------------------------KRAJ ISPISIVANJE PODATAKA KUPCA


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