function GetURLParameter(sParam) {
	var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)  {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}

function validateLogIn(){
	var valid = true;
	if ($("#username").val() == ""){
			$("#username_req").addClass("displayerror");
			valid = false;
	} else {
		$("#username_req").removeClass("displayerror")
	}
	if ($("#password").val()== ""){
		$("#password_req").addClass("displayerror");
			valid = false;
	}else {
		$("#password_req").removeClass("displayerror")
	}
	return valid;
}
function addOpportunityTypeToURL(link,list) {
    $(link).attr('href', function() {
        return this.href + '?select=' + $("#" + list).val();
    });
}