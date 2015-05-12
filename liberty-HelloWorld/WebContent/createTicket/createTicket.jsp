<%@ include file="../commons/header.jspf"%>


        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Create Ticket</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                
                <!-- create ticket  -->
                
	        	  <div class="form-container">
	
					<form class="form-horizontal" role="form">
					  <div class="form-group">
					    <label class="control-label col-sm-2" for="subject">Subject:</label>
					    <div class="col-sm-7">
					      <input type="text" class="form-control" id="subject" placeholder="Enter subject">
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="control-label col-sm-2" for="description">Description:</label>
					    <div class="col-sm-7"> 
					      <textarea class="form-control" rows="5" id="description"  placeholder="Enter the descriptions"></textarea>
					    </div>
					  </div>
					
					
					  <div class="form-group">
					    <label class="control-label col-sm-2 radio-inline"><strong>Priority:</strong></label>
					       <label class="radio-inline">
					     <input id="priority" name="priority" value="low" type="radio">
					     Low</label>
					    <label class="radio-inline">
					     <input id="priority" name="priority" value="high" type="radio">
					     High</label>
					    
					   </div>
					
					    
					  <div class="form-group"> 
					    <div class="col-sm-offset-2 col-sm-7">
					      <button type="button" id="submitTicket" class="btn btn-primary center-block">Submit Issue</button>
					    </div>
					  </div>
					</form>
					
					</div>
                <!-- /create ticket -->
                
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

</body>
     <script src="createTicket.js"></script>
</html>
