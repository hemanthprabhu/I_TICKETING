/**
 * 
 */

/**
 * 
 */



$(document).ready(function() {
	
	$("#submitTicket").click(function(){
			var subject=$("#subject").val();
			var description=$("#description").val();
			var priority=$("input[name='priority']:checked").val();

		
       	  $.ajax({
				url :  siteContextPath+"/Ticketing",
				type : 'GET',
				 data: {
					 action:"createTicket",
		
					 subject:subject,
					 description:description,
					 priority:priority
					 },         				
				success : function(data) {
                  var data = JSON.parse(data);
                 
                  var viewticketsURL=siteContextPath+"/viewTickets/viewTickets.jsp";

                  window.location = viewticketsURL;
				},
				error : function(data) {
					alert("error");
				},

			});
			 

		});
	
});