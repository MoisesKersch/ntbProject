<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>BackOffice</title>
    	
    	<!--  frameworks -->
    		<!--  jquery -->
   			<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />"></script>
			<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
			<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
			<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" ></script>
			
			
			<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquery-ui.css" />">
			<!--  jquery end -->
			
			<!--  bootstrap -->
			<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
			<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
    	<!--  frameworks end -->

	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/redelinear.css" />">
	
   	<script src="<c:url value="/resources/js/demo.js" />" ></script>
     <script src="<c:url value="/resources/js/script.js" />" ></script>
      
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	
	<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
	
	<script src="<c:url value="/resources/js/redelinear.js" />" ></script>
	
	<style>
		.panel {
		    margin-bottom: 20px;
		    background-color: #fff0;
		    border: 1px solid transparent;
		    border-radius: 4px;
		    -webkit-box-shadow: 0 1px 1px rgba(0,0,0,.05);
		    box-shadow: 0 1px 1px rgba(0,0,0,.05);
		}
	</style>
	
	</head>
	<body class="${skinUser} sidebar-mini">
		<div class="wrapper">
			<jsp:include page="message.jsp" />
      		<jsp:include page="header.jsp" />
      		<jsp:include page="menu.jsp" />
			<div class="content-wrapper">
				<section id="divContent" class="content">
					<div class="box box-primary">
						<div class="box-header">
		                	<h3 class="box-title inboxTitle">Rede Linear</h3>
		                </div>
		                
		         		<div class="row">
						  	<div id="loading" class="col-md-12 text-center hidden"><i class="fa fa-spinner fa-spin fa-3x fa-fw"></i><div>Carregando Rede Linear...</div></div>
						</div>
						
		                <div class="box-body">
		               		 <div class="table-responsive" id="redeLinearTableId">
								<table id=redeLinear2 class="table table-striped table-bordered"  style="width:100%">
							        <thead>
							            <tr>
							            	<th style="min-width: 200px;">Login</th>
							                <th style="min-width: 250px;">Nome</th>
							                <th style="min-width: 30px;">Informações</th>
							                <th style="min-width: 80px;">Celular</th>
							                <th style="min-width: 200px;">Email</th>
							                <th style="min-width: 200px;">Indicador</th>
							                <th style="min-width: 102px;">Data Entrada</th>
							                <th style="min-width: 200px;">Cidade/Estado</th>
							                <th style="min-width: 100px;">Graduacao</th>
							                <th style="min-width: 130px; text-align: center;">Nível na Equipe</th>
							                <th style="min-width: 135px;">Atividade Mensal</th>
							            </tr>
							        </thead>
							    </table>
						    </div>
						</div>
					</div>
				</section>
				</div>
			<jsp:include page="footer.jsp" />      
    	</div>
	</body>
	
	<!-- Modal -->
	<div class="modal fade modal-word-wrap" id="popupInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title inboxTitle" id="myModalLabel">Informações</h4>
	      </div>
	      <div class="modal-body">
		        <div class="text-center"> <strong> Celular:</strong> <span id="telefoneModal"></span></div> 
		        <div class="text-center"> <strong> Email: </strong> <span id="emailModal"></span></div>
		        <div class="text-center"> <strong> Cidade: </strong> <span id="cidadeModal"></span></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div>
	
</html>