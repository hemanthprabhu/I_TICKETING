/**
 * 
 */

/**
 * 
 */


$(document).ready(function() {
 

	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
var commentList=$("#commentList");
var addCommentClick=$("#addCommentClick");
var commentTextArea=$("#commentTextArea");
var ticketId=getParameterByName("ticketId");
$.ajax({
	url :  siteContextPath+"/Ticketing?action=getTicketAndComments",
	type : 'GET',
	 data: {
		 ticketId:ticketId,
		
		 },         				
	success : function(data) {

		var data=JSON.parse(data);
		
	 var ticket=JSON.parse(data.message);
		var subject=ticket.subject;
		$("#subject").text(subject);
		var comments=ticket.comments;
		 text="";
		for(i=0;i<comments.length;i++)
			{
			if(comments[i].role=="customer")
				{
				text=text+comments[i].commentText+"  ";
			addComment("Customer "+comments[i].createdBy,comments[i].createdAt,comments[i].commentText);
				}
			else if(comments[i].role=="agent")
				{
				addComment("Agent "+comments[i].createdBy,comments[i].createdAt,comments[i].commentText);
				}
			}
		 var wordCount = text.replace( /[^\w ]/g, "" ).split( /\s+/ ).length;
		 while(wordCount<=100)
		 {
			 text=text+" "+text;  
			 wordCount = text.replace( /[^\w ]/g, "" ).split( /\s+/ ).length;
		 }
		
	},
	error : function(data) {
		alert("error");
	},

});


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

			 var hours = d.getHours();
			  var minutes = d.getMinutes();
			  var  sec=d.getSeconds();
			  var ampm = hours >= 12 ? 'PM' : 'AM';
			  hours = hours % 12;
			  hours = hours ? hours : 12; // the hour '0' should be '12'
			  minutes = minutes < 10 ? '0'+minutes : minutes;

			return m_names[curr_month]  + "-" + curr_date + "-" + curr_year +' '+ hours + ':' + minutes + ' ' + sec + ' ' + ampm;;

}
var addComment=function(commentedBy,commentedAt,commentedText)
{
	 var template='<li><div class="commentedDetails"><span class="commentedBy">'	
			+'<img src="'+siteContextPath+'/commons/images/icon.png"></img>'
			+commentedBy+'</span>  <span class="commentedTime">  commented on '
			+commentedAt+'</span></div>'
			+'<div class="commentedText"><p class="commented">'
			+commentedText+'</p> </div> </li>'
	commentList.append(template);
}
$(addCommentClick).click(function() {

	var ticketId=getParameterByName("ticketId");
	var comment=commentTextArea.val();
	
	  $.ajax({
			url :  siteContextPath+"/Ticketing?action=addComment",
			type : 'GET',
			 data: {
				 ticketId:ticketId,
				 comment:comment
				 },         				
			success : function(data) {


				addComment(currentUserName,getCurrentDate(),commentTextArea.val());
         
			},
			error : function(data) {
				alert("error");
			},

		});

	});
addTweeet=function(tweet,tweetedBy,tweetLink,imageLink)
{
var template='<li class="left clearfix">'
    +'<span class="chat-img pull-left">'
    +'<object data="'+imageLink+'" type="image/png">'
    +'<img src="'+siteContextPath+'/commons/images/icon.png"  alt="User" class="img-circle"  />'
   +'</object>'
    +'</span>'
    +'<div class="chat-body clearfix">'
                     +'<div class="header">'
                     +'<strong class="primary-font">' + tweetedBy +'</strong>'
                     +'</div>'
                     +'<p><a href='+tweetLink+'>'
            +tweet+'</a></p>'
    +'</div>'
+'</li>';
$("#chat").append(template); 

}

$.ajax({
	url :  siteContextPath+"/CloudantTwitterServlet",
	type : 'GET',
	 data: {
		 action:"getAllTweetsFromCloudant",
		 },  
	 success : function(data) {

		 
		var data=JSON.parse(data);

		for(var i=0;i<data.length;i++)
		{
		console.log(data[i].body); 
		addTweeet(data[i].body,data[i].preferredUsername,data[i].link,data[i].image);
	//	addTweeet(data[i].body,"aaa",data[i].link,"ddd");
		}

		
	},
	error : function(data) {
		alert("error");
	},

});




 


} );