/**
 * 
 */

$(function() {
	
	$('#create-new-employee').click(function(e) {
		// e.preventDefault();
		$("#add-new-employee-form").delay(300).fadeIn(100);
		
		$("#greetings").fadeOut(100);
		$("#add-new-provider-form").fadeOut(100);
		$('#create-new-provider').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	
	$('#create-new-provider').click(function(e) {
		// e.preventDefault();
		$("#add-new-provider-form").delay(300).fadeIn(100);
		
		$("#greetings").fadeOut(100);
		$("#add-new-employee-form").fadeOut(100);
		$('#create-new-employee').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	
	
});