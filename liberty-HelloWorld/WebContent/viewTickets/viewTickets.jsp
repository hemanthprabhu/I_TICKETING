<%@ include file="../commons/header.jspf"%>


        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Tickets List</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                 <!-- view ticket info -->
                 
                 <c:if test="${sessionScope.role != null}">
							<c:if test="${sessionScope.role == 'agent'}">
							
							 <div class="btn-group pull-right">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                        Actions
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
                                        <li><a href="#" id="assignAgent">Assign Agent</a>
                                        </li> 
                                          <li><a href="#" id="updateStatus">Update Status </a>
                                        </li>                                      
                                    </ul>
                   			 </div>
                 
							</c:if>
						</c:if>
						

                 
                 
          <div class="table-responsive"  style="margin-top:30px">
                 
<table id="ticketsInfo" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th></th>
         		 <th>Ticket Id</th>               
                <th>Ticket Summary</th>
            	<th>Agents Assigned</th>             
                <th>Priority</th>
                <th>Status</th>
                
            </tr>
        </thead>
 
        <tbody>

     
        </tbody>
    </table>
              </div>   
                 
                  <!-- /view ticket info -->
               		
               		
               		
               		<!-- ticket info assigning agent pop up -->
               		
				               		<!-- Modal HTML -->
				               		
				               		
				               		 <!-- Modal HTML -->
				    <div id="assignAgentPopup" class="modal fade">
				        <div class="modal-dialog">
				            <div class="modal-content">
				                <div class="modal-header">
				                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				                    <h4 class="modal-title">Assign Agent</h4>
				                </div>
				                <div class="modal-body">
				                    <form role="form">        
				                            <div class="form-group" style="min-height:50px">
											 <label class="control-label col-sm-2" for="sel1">Agent</label>
												<div class="col-sm-7">
													 <select class="form-control" id="agentList">
																	
													</select>
											</div>
											  </div>
														    
														      
				                    </form>
				                </div>
				                <div class="modal-footer">
				                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				                    <button type="button" class="btn btn-primary" id="assignAgentSaveBtn">Assign</button>
				                </div>
				            </div>
				        </div>
				    </div>
				</div>    
				               		

               	
               		<!-- /ticket info   updating status  pop up -->
               		
               		
               		
               		
               		
               		              		<!-- ticket info assigning agent pop up -->
               		
				               		<!-- Modal HTML -->
				               		
				               		
				               		 <!-- Modal HTML -->
				    <div id="updateStatusPopup" class="modal fade">
				        <div class="modal-dialog">
				            <div class="modal-content">
				                <div class="modal-header">
				                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				                    <h4 class="modal-title">Update Status</h4>
				                </div>
				                <div class="modal-body">
				                    <form role="form">        
				                            <div class="form-group" style="min-height:50px">
											 <label class="control-label col-sm-2" for="sel1">Status</label>
												<div class="col-sm-7">
													 <select class="form-control" id="statusList">
																	<option value="OPEN">Open</option>
														       	    <option value="PENDING">Pending</option>
														       	       <option value="CLOSED">Closed</option>
														       	         <option value="RESOLVED">Resolved</option>
														       	          									       	    
													</select>
											</div>
											  </div>
														    
														      
				                    </form>
				                </div>
				                <div class="modal-footer">
				                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				                    <button type="button" class="btn btn-primary" id="updateStatusSaveBtn">Update</button>
				                </div>
				            </div>
				        </div>
				    </div>
				</div>    
				               		

               	
               		<!-- /ticket info  updating status  pop up -->
               		
               		
               		
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

    <!-- DataTables JavaScript -->
    <script src="../plugins/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="../plugins/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>


    <script src="viewTickets.js"></script>



</body>

</html>
