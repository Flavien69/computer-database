$(document).ready(function () {
	var isIntroducedValid = true;
	var isDiscontinuedValid = true;
	var dateformat = strings['date_regex'];

	$('#introduced').on('input', function() {     
		console.log(strings['date_regex']);
        if (!$(this).val().match(strings['date_regex']) && $(this).val() != "") {
            $(this).css( "border", "2px solid red");
            isIntroducedValid = false;
        }
        else{
        	isIntroducedValid = true;
            $(this).css( "border", "");  
        }
        checkValid()
	});
	
	$('#discontinued').on('input', function() {        
        if (!$(this).val().match(strings['date_regex']) && $(this).val() != "") {
            $(this).css( "border", "2px solid red");
            isIntroducedValid = false;
        }
        else{
        	isIntroducedValid = true;
            $(this).css( "border", "");  
        }
        checkValid();
	});
	
	function checkValid(){
		if (isIntroducedValid && isDiscontinuedValid)
			$(".validation").removeClass( "disabled" );
		else
            $(".validation").addClass( "disabled" );
	}
});