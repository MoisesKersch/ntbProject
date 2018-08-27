<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<meta http-equiv="X-UA-Compatible" content="IE=edge">
   	<meta name="viewport" content="width=device-width, initial-scale=1">
   	<title>BackOffice</title>

	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/fontawesome.css"/>">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquerysctipttop.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/iCheck/square/blue.css" />">

			
	<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
   	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
   
   	<script src="<c:url value="/resources/js/demo.js" />" ></script>
   	
   	<script src="<c:url value="/resources/js/editarminhalista.js" />" ></script>
   	<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
   	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
   	<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" ></script>
   	<script src="<c:url value="/resources/js/script.js" />" ></script>
   	<script src="<c:url value="/resources/js/jquery-ui.js" />" ></script>
   	<script src="<c:url value="/resources/js/custom.js" />" ></script>
   	<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" />" ></script>
   	<script src="<c:url value="/resources/js/app.min.js" />" ></script>
      	
      	

	<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
   	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
   	<script src="<c:url value="/resources/js/initial.js" />" ></script>


</head>
	
	<body class="${skinUser} sidebar-mini">
	
		<div class="wrapper">
			<jsp:include page="message.jsp" /> 
      		<jsp:include page="header.jsp" />  </div>
      		<jsp:include page="menu.jsp" /> 
      		
      
			<div class="content-wrapper">
				<section class="content">
					<div class="box box-primary">
					
						<form:form id="saveTheList" name="Contato" class="form-horizontal" action="salvarnovo" method="post" commandName="contato" role="form">
							<button type="submit" name="action" class="btn btn-primary btn-block btn-flat" value="completar" id="buttonCompleta" style="text-align: left; font-size: 20px;">
								<i class="fa fa-floppy-o"></i>
									Salvar
							</button>
							<div class="box-header inboxTitle">
		                		<h2> Novo </h2>
		               	 	</div>
							
							
		                  	<div class="box-body">
						  		<div class="form-group">
						    		<div class="col-sm-12">
										<form:hidden class="form-control" path="id" id="id"/>
										<form:hidden class="form-control" path="adClientId" id="adClientId"/>
										<form:hidden class="form-control" path="adOrgId" id="adOrgId"/>
										<form:hidden class="form-control" path="isactive" id="isactive"/>
										<form:hidden class="form-control" path="cBpartneriId" id="cBpartneriId"/>
										<form:hidden class="form-control" path="classificacao" id="classificacao"/>
						    		</div>
						  		</div>
							  		
						  		<div class="form-group">
						    		<label for="nome" class="col-sm-2 control-label">Nome</label>
						    		<div class="col-sm-5">
										<form:input class="form-control required"  path="nome" id="nome" maxlength="60"/>
						    			<form:errors path="nome" cssClass="tooltip-erro"/>
						    		</div>
						  		</div>
 
						  		<div class="form-group">
						    		<label for="email" class="col-sm-2 control-label">Telefone</label>
						    		<div class="col-sm-5">
										<form:input class="form-control" path="telefone" id="telefone" maxlength="80"/>
						    			<form:errors path="telefone" cssClass="tooltip-erro"/>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group">
						    		<label for="email" class="col-sm-2 control-label">Celular</label>
						    		<div class="col-sm-5">
										<form:input class="form-control" path="celular" id="celular" maxlength="80"/>
						    			<form:errors path="celular" cssClass="tooltip-erro"/>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group">
						    		<label for="email" class="col-sm-2 control-label">Whatsapp</label>
						    		<div class="col-sm-5">
										<form:input class="form-control" path="whatsapp" id="whatsapp" maxlength="80"/>
						    			<form:errors path="whatsapp" cssClass="tooltip-erro"/>
						    		</div>
						  		</div>
						  		
						  		 <div class="form-group">
						    		<label for="email" class="col-sm-2 control-label">Skype</label>
						    		<div class="col-sm-5">
										<form:input class="form-control" path="skype" id="skype" maxlength="80"/>
						    			<form:errors path="skype" cssClass="tooltip-erro"/>
						    		</div>
						  		</div>
							  		
						  		 <div class="form-group">
						    		<label for="email" class="col-sm-2 control-label">Email</label>
						    		<div class="col-sm-5">
										<form:input class="form-control" path="email" id="email" maxlength="80"/>
						    			<form:errors path="email" cssClass="tooltip-erro"/>
						    		</div>
						  		</div>
							 </div>
						</form:form>
					</div>
			
		            <div class="box box-primary">
							<div class="box-body">
								<div id="rankingTable" class="container">
								  <h2><strong>Lista </strong></h2>
									  <table id="lista-agenda" class="container mylist table-bordered">
										    <thead>
												<tr>
													<th>Assunto</th>
													<th>Anotação</th>
													<th>Quando</th>
													<th>Onde</th>
													<th>Situação 
													<button type="button" class="customButton btn btn-default btn-sm" data-toggle="modal" data-target="#exampleModal" style="float: right; right: -56px ; margin-right: 6px ; width: 55% ;"><span class="glyphicon glyphicon-plus"></span> Adicionar </button></th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="listItems" items="${contato.agendas}">
												<tr>
												    <td> ${listItems.assunto} </td>
												    <td> ${listItems.anotacao} </td>
												    <td> ${listItems.quando} </td>
												    <td> ${listItems.onde} </td>
												    <td> ${listItems.situacao} </td>
												</tr>
										    </c:forEach>
										</tbody>
									</table>
								</div>
							</div>	
						</div>
					
				</section>
			</div>
			<jsp:include page="footer.jsp" />
			
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">			    	
			  	<div class="modal-dialog" role="document">
			  		<div class="box box-primary">
						<div class="box-body">
				       	 	<h2 class="modal-title" id="exampleModalLabel">Agenda</h2>
							<form:form id="agendaForm" name="Agenda" class="form-horizontal" action="novopopularcontatos" method="post" commandName="agenda" role="form">
					    		
			       			 	<div class="form-group">
					    			<label for="assunto" class="col-sm-2 control-label ">Assunto</label>
					    			<div class="col-sm-10 marginModal">
											<form:input class="form-control" path="assunto" id="assunto"/>
					    			</div>
					  			</div>
					  			
						  		 <div class="form-group">
						    		<label for="anotacao" class="col-sm-2 control-label">Anotação</label>
						    		<div class="col-sm-10 marginModal">
										<form:input class="form-control" path="anotacao" id="anotacao"/>
						    		</div> 
						  		</div>
						  		
						  		 <div class="form-group">
						    		<label for="quando" class="col-sm-2 control-label">Quando</label>
						    		<div class="col-sm-10 marginModal">
										<form:input class="form-control" path="quando" id="quando"/>
						    		</div>
						  		</div>
						  		
						  		 <div class="form-group">
						    		<label for="onde" class="col-sm-2 control-label">Onde</label>
						    		<div class="col-sm-10 marginModal">
										<form:input class="form-control" path="onde" id="onde"/>
						    		</div>
						  		</div>
						  		
						  		 <div class="form-group">
						    		<label for="situacao" class="col-sm-2 control-label">Situação</label>
						    		<div class="col-sm-10 marginModal">
										<form:input class="form-control" path="situacao" id="situacao"/>
						    		</div>
						  		</div>

								<div class="form-group">
									<label for="amrAgendaAcaoId" class="col-sm-2 control-label">Ação</label>
									<form:select id="amrAgendaAcaoId" path="amrAgendaAcaoId" >
<%--    										<form:option value="NONE" label="--- Selecionar ---"/> --%>
										<form:options items="${agendaAcao}" itemLabel="nome" itemValue="id" />
									</form:select>
								</div>
								
						    </form:form>
							<div class="modal-footer">
								<a href="#" class="btn btn-primary typeRequest" id="popularcontatos" onclick="popularContato();">
									Adicionar a Lista
								</a>
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					        </div>
		      			</div>
		      		</div>
			 	</div>
			 </div>
			 
	</body>
</html>