<%@ include file="../commons/header.jspf"%>



<link rel="stylesheet" type="text/css" href="viewTicket.css">
<!-- 
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="css/watson-bootstrap-dark.css">
<link rel="stylesheet" href="css/style.css"> -->

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
					        
					        <p class="taskDescription" id="subject"></p>
					    </div>
					    <div class="actionBox">
					        <ul id="commentList">
					           
					           
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
                	
                	
                	                	<!-- view personaliy info -->
                	
                	
                	
                	
<div class="row">

<div class="col-lg-6 col-md-6 col-xs-12">
	<div class="row">
		<div style="display:none;" class="form-group row loading text-center loading">
			<h2>&nbsp;</h2>
			<img src="images/watson.gif">
		</div>
		<div style="display: none;" class="form-group row error">
			<h2>&nbsp;</h2>
			<div class="well">
				<p class="errorMsg"></p>
			</div>
		</div>
	</div>
	<div style="display: none;" class="results">
		<h3 style="height: 29px;">Customer Personality*</h3>
		<div class="well">
			<div class="summary-div"></div>
			<div style="color: gray" class="text-right">
				<em class="small"></em>
			</div>
		</div>
	</div>
</div>
<div style="display: none;" class="results">
	<div class="row">
		<div class="col-lg-5 col-md-5 col-xs-12">
			<h3>Data Behind the Customer Personality</h3>
			<div style="display: none;" class="col540px well traits"></div>
			</div>
			
		</div>
	</div>
	<div class="hidden">
		<div id="header-template">
			<div class="row theader">
				<div class="col-lg-5 col-xs-5">
					<span>Name</span>
				</div>
				<div class="col-lg-7 col-xs-7 text-right">
					<span>Value ± Sampling Error</span>
				</div>
			</div>
		</div>
		<div id="trait-template">
			<div class="row">
				<div class="tname col-lg-7 col-xs-7">
					<span></span>
				</div>
				<div class="tvalue col-lg-5 col-xs-5 text-right">
					<span></span>
				</div>
			</div>
		</div>
		<div id="model-template">
			<div class="row">
				<div class="col-lg-12 col-xs-12 text-center">
					<span></span>
				</div>
			</div>
		</div>
	</div>
</div>
</div>

                	
                	                	<!-- view personaliy info -->
                	                	
                	                	
                	                	
                	                	
                	                	<!--   view twitter -->
                	                	
                	                	       <!-- Page Content -->

                <!-- twitter  -->
                
<div class="chat-panel panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-comments fa-fw"></i>
                            Tweets

                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <ul class="chat" id="chat">

                            </ul>
                        </div>
                <!-- twitter -->
                
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#twitter -->

   <script>

//var data=JSON.parse(data);


</script>
</body>

</html>
                	                	
                	                	<!-- /twitter -->
                	
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

	<script type="text/javascript" src="js/d3.v2.min.js"></script>
	
	<script type="text/javascript" src="js/personality.js"></script>
	<script type="text/javascript" src="js/textsummary.js"></script>
	<script type="text/javascript" src="js/demo.js"></script>

</body>

</html>
