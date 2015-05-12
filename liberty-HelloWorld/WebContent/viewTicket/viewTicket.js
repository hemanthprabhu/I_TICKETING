/**
 * 
 */

/**
 * 
 */


$(document).ready(function() {
 


var commentList=$("#commentList");
var addCommentClick=$("#addCommentClick");
var commentTextArea=$("#commentTextArea");

var getCurrentDate=function()
{
	
	var date=new Date();

	var m_names = new Array("Jan", "Feb", "Mar", 
			"Apr", "May", "Jun", "Jul", "Aug", "Sep", 
			"Oct", "Nov", "Dec");

			var d = new Date();
			var curr_date = d.getDate();
			var curr_month = d.getMonth();
			var curr_year = d.getFullYear();
			
			return  curr_date + "-" + m_names[curr_month]+ "-" + curr_year;
	
}
var addComment=function(commentedBy,commentedAt,commentedText)
{
 var template='<li><div class="commentedDetails"><span class="commentedBy">'			 
				+commentedBy+'</span>  <span class="commentedTime">  commented on '
				+commentedAt+'</span></div>'
				+'<div class="commentedText"><p class="commented">'
				+commentedText+'</p> </div> </li>'
	commentList.append(template);
}
$(addCommentClick).click(function() {
	addComment("somu",getCurrentDate(),commentTextArea.val());
	});
} );