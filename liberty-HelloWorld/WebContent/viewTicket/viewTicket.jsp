<%@ include file="../commons/header.jspf"%>



<link rel="stylesheet" type="text/css" href="viewTicket.css">


        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Ticket Summary</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                
                	
                	<!-- view ticket info -->
				     <div class="detailBox">
					    <div class="titleBox">
					      <label>Subject</label>
					       
					    </div>
					    <div class="commentBox">
					        
					        <p class="taskDescription">Problem Starting freerdp server</p>
					    </div>
					    <div class="actionBox">
					        <ul id="commentList">
					            <li>
					                <div class="commentedDetails">
					                  <span class="commentedBy">kamal</span>  <span class="commentedTime">commented on </span>
					                </div>
					                <div class="commentedText">
					                    <p class="commented">Hello this is a test comment.</p> 
					                </div>
					            </li>
					           
					        </ul>
					            <div class="form-group" style="width:100%">		
					                  <label for="comment">Comment:</label>
					            			
										  <textarea class="form-control" rows="5" id="commentTextArea" placeholder="Leave a comment"></textarea>
								</div>
								
					            <div class="form-group">
					                <button class="btn btn-primary pull-right" id="addCommentClick">Comment</button>
					            </div>
					    </div>
					</div>
					
					
                	
                	<!-- view ticket info -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../plugins/bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../plugins/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../plugins/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../plugins/dist/js/sb-admin-2.js"></script>
    
     <script src="viewTicket.js"></script>
    

</body>

</html>
