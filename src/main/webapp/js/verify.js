$(document).ready(function () {
	var isIntroducedValid = true;
	var isDiscontinuedValid = true;
	var dateformat = "^(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])[\\s||T][0-9][0-9]:[0-9][0-9]$";

	$('#introduced').on('input', function() {     
        if (!$(this).val().match(dateformat) && $(this).val() != "") {
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
        if (!$(this).val().match(dateformat) && $(this).val() != "") {
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