$(document).ready(function() {
 

	
$.ajax({
	url :  siteContextPath+"/Ticketing?action=getStatusAndCount",
	type : 'GET',
	        				
	success : function(data) {

		  var data=JSON.parse(data);
		
	var statusmap=JSON.parse(data.message);
	
		$("#open").text(statusmap.OPEN);
		$("#pending").text(statusmap.PENDING);
		$("#closed").text(statusmap.CLOSED);
		$("#resolved").text(statusmap.RESOLVED);
	},
	error : function(data) {
		alert("error");
	},

});


} );