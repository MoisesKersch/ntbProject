<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BackOffice</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/agendacontato.css" />">
	
	<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />"></script>
	
	<script src="<c:url value="/resources/js/script.js" />"></script>
	

	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/dataTables.bootstrap.min.css" />">
	<link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.1.2/css/buttons.dataTables.min.css" />
	
	<script src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" ></script>
	<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>	
	<script src="https://cdn.datatables.net/buttons/1.1.2/js/dataTables.buttons.min.js" ></script>
	<script src="https://cdn.datatables.net/select/1.1.2/js/dataTables.select.min.js" ></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
	<script type="text/javascript" src="resources/js/agenda-contato-editor.js"></script>
	<script type="text/javascript" src="resources/js/agenda-editor.js"></script>
	<script src="<c:url value="/resources/js/agendacontato.js" />"></script>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="<c:url value="/resources/plugins/datepicker/locales/datepicker-pt-br.js"/>"></script>
	
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
  	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
	
	<script>
	  $( function() {
		    $.datepicker.setDefaults($.datepicker.regional['pt-BR']);
		    $( ".datepicker" ).datepicker({format: 'dd/mm/yyyy'});
		  } );
	  
	  
	  $(".phone").mask("99 99999999");
	  $(".cellphone").mask("99 999999999");
	  $('.dateFormat').mask('00/00/0000');
	  
	  
	</script>
	
</head>
<body class="${skinUser} sidebar-mini">

	<div class="wrapper">
		<jsp:include page="message.jsp" />
		<jsp:include page="header.jsp" />
		<jsp:include page="menu.jsp" />
		
		<div class="content-wrapper">
		
			<section class="content">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title inboxTitle ">Agenda de Contatos
						 </h3>
					</div>
					<div class="box-body">
						<div class="row">
						  	<div id="loading" class="col-md-12 text-center hidden"><i class="fa fa-spinner fa-spin fa-3x fa-fw"></i><h3 class="inboxTitle">Carregando Agenda de Contatos...</h3></div>
						</div>
						<div class="table-responsive">
							<table class="dataTable table table-striped hidden" id="agendacontato" style="width: 100%"> 
								  <thead>
						            <tr>
						                <th style="min-width: 200px;">Nome</th>
						                <th>Telefone</th>
						                <th>Celular</th>
						                <th>Email</th>
						                <th class="text-center">Contato</th>
						                <th>Cidade </th>
						                <th>Estado</th>
						                <th class="text-center">Último Contato</th>
						                <th class="text-center">Próximo Contato</th>
						                <th>Descrição</th>
						                <th>Agendas</th>
						                <th>Agendas</th>
						                <th>Agendas</th>
						            </tr>
						        </thead>
					       	 <tbody>
							</table>
						</div>
					</div>
				</div>
				
				<div class="box box-primary hidden" id="agendaCard">
					<div class="box-header">
						<h3 class="box-title inboxTitle ">Agenda</h3>
					</div>
					<div class="box-body">
						<div class="table-responsive">
							 <table class="dataTable table table-striped" id="agenda" style="width: 100%"> 
							  <thead>
					            <tr>
					                <th style="min-width: 200px;">Assunto</th>
					                <th>Anotação</th>
					                <th class="text-center">Quando</th>
					                <th>Onde</th>
					                <th>Situação</th>
					                <th>Acão</th>
					                <th class="hidden">id</th>
					            </tr>
					        </thead>
					        <tbody>
							 </table>
						 </div>
					</div>
				</div>
				
				<div class="modal fade" id="novoCadastroModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title inboxTitle">Cadastrar Novo Contato</h3>
							</div>
							<div class="box-body">
								<form id="contatoForm" class="form-horizontal" name="contatoForm">
									<input type="hidden" id="id" name="id">
									<input type="hidden" id="saveOrEdit" value="save">
									<div class="form-group">
										<label for="nome" class="col-sm-2 control-label ">Nome *</label>
										<div class="col-sm-10 ">
											<input class="form-control" name="nome" id="nome" />
										</div>
									</div>
									<div class="form-group">
										<label for="telefone" class="col-sm-2 control-label">Telefone</label>
										<div class="col-sm-10 ">
											<input class="form-control phone" id="telefone" name="telefone"/>
										 </div>
									</div>
									<div class="form-group">
										<label for="celular" class="col-sm-2 control-label">Celular</label>
										<div class="col-sm-10 ">
											<input class="form-control cellphone" name="celular" id="celular"/>
										</div>
									</div>
			
									<div class="form-group">
										<label for="email" class="col-sm-2 control-label">Email</label>
										<div class="col-sm-10">
											<input class="form-control" name="email" id="email" />
										</div>
									</div>
									
									<div class="form-group">
										<label for="cidade" class="col-sm-2 control-label">Cidade</label>
										<div class="col-sm-10">
											<input class="form-control" name="cidade" id="cidade" />
										</div>
									</div>
									
									<div class="form-group">
										<label for="estado" class="col-sm-2 control-label">Estado</label>
										<div class="col-sm-10">
											<input class="form-control" name="estado" id="estado" />
										</div>
									</div>
									
									<div class="form-group">
										<label for="estado" class="col-sm-2 control-label">Último Contato</label>
										<div class="col-sm-10">
											<input class="form-control datepicker dateFormat" name="ultimoContato" id="ultimoContato" />
										</div>
									</div>
									
									<div class="form-group">
										<label for="estado" class="col-sm-2 control-label">Próximo Contato</label>
										<div class="col-sm-10">
											<input class="form-control datepicker dateFormat" name="proximoContato" id="proximoContato"/>
										</div>
									</div>
									
									<div class="form-group">
										<label for="descricao" class="col-sm-2 control-label">Decrição</label>
										<div class="col-sm-10 marginModal">
											<textarea class="form-control" name="descricao" id="descricao" rows="10" cols="60" style="resize:none" > </textarea>
										 </div>
									</div>
									
									<div class="modal-footer">
										 <button type="button" class="btn btn-default" id="contactController">Enviar</button>
										<button type="button" id="closeModal" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade" id="novoAgendaModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title inboxTitle">Agenda</h3>
							</div>
							<div class="box-body">
								<form id="agendaForm" name="Agenda" class="form-horizontal" action="save" method="post" role="form">
								
									<input type="hidden" id="agendaSaveOrEdit" value="agendasave">
									
									<input type="text" name="id" id="idAgenda1" class="hidden">
									<input type="text" name="type" id="typeAgenda" class="hidden">
									
									<input type="text" name="idContato" id="idContato" class="hidden">
									
									<div class="form-group">
										<label for="assunto" class="col-sm-2 control-label ">Assunto</label>
										<div class="col-sm-10 marginModal">
											<input class="form-control" id="assunto" name="assunto" />
										</div>
									</div>
									
									<div class="form-group">
										<label for="anotacao" class="col-sm-2 control-label">Anotação</label>
										<div class="col-sm-10 marginModal">
											<textarea class="form-control" id="anotacao" name="anotacao" rows="10" cols="60" style="resize:none" > </textarea>
										 </div>
									</div>
			
									<div class="form-group">
										<label for="quando" class="col-sm-2 control-label">Quando</label>
										<div class="col-sm-10 marginModal">
											<input class="form-control datepicker dateFormat" id="quando" autocomplete="off" name="quando"/>
										</div>
									</div>
			
									<div class="form-group">
										<label for="onde" class="col-sm-2 control-label">Onde</label>
										<div class="col-sm-10 marginModal">
											<input class="form-control" id="onde" name="onde"/>
										</div>
									</div>
									
									<div class="form-group"> 
										<label for="situacao" class="col-sm-2 control-label">Situação</label> 
										<div class="col-sm-10 marginModal">
											 <label class="radio-inline">
									    	<input type="radio" name="situacao" value="A" checked>Agendado
										    </label>
										    <label class="radio-inline">
										      	<input type="radio" name="situacao" value="R" >Resolvido
										    </label>
										</div>
									</div>
									
									<div class="form-group">
										<label for="amrAgendaAcaoId" class="col-sm-2 control-label">Ação</label>
										<div class="col-sm-10 marginModal">
											<select name="amrAgendaAcaoId" class="form-control form-control-sm">
											    <c:forEach var="item" items="${agendaAcao}">
											        <option value="${item.id}">${item.nome}</option>
											    </c:forEach>
											</select>
										</div>
									</div>
									
									<div class="modal-footer">
										 <button type="button" class="btn btn-default" id="agendaController">Enviar</button>
										<button type="button" id="closeModal1" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade" id="deleteContact" tabindex="-1" role="dialog" aria-labelledby="deleteContact" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title inboxTitle">Voce tem certeza que deseja excluir este contato?</h3>
							</div>
							<div class="box-body">
								<div class="modal-body">
									<input type="hidden" id="remove" name="remove">
							        <p></p>
							     </div>
							</div>
							<div class="modal-footer">
								 <button type="button" class="btn btn-default" id="deleteControllerContato">Excluir</button>
								<button type="button" id="closeModal" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade" id="deleteAgenda" tabindex="-1" role="dialog" aria-labelledby="deleteContact" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title inboxTitle">Voce tem certeza que deseja excluir este contato?</h3>
							</div>
							<div class="box-body">
								<div class="modal-body">
									<input type="hidden" id="removeAgenda" name="remove">
							        <p></p>
							     </div>
							</div>
							<div class="modal-footer">
								 <button type="button" class="btn btn-default" id="deleteControllerAgenda">Excluir</button>
								<button type="button" id="closeModal" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade modal-word-wrap" id="popupContatoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title inboxTitle" id="myModalLabel">Contato</h4>
				      </div>
				      <div class="modal-body">
					        <div class="text-center"> <strong> Telefone: </strong> <span id="mtelefone"></span></div> 
					        <div class="text-center"> <strong> Celular: </strong> <span id="mcelular"></span> </div>
					        <div class="text-center"> <strong> Email: </strong> <span id="memail"></span></div>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				      </div>
				    </div>
				  </div>
				</div>
				
				<div class="modal fade modal-word-wrap" id="popupDescricaoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title inboxTitle" id="myModalLabel">Descrição</h4>
				      </div>
				      <div class="modal-body">
					        <div class="text-center"> <span id="mdescricao" style="word-wrap: break-word;"></span></div> 
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				      </div>
				    </div>
				  </div>
				</div>
				
				<div class="modal fade modal-word-wrap" id="popupAgendaAnotacaoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title inboxTitle" id="myModalLabel">Anotação</h4>
				      </div>
				      <div class="modal-body">
					        <div class="text-center"><span id="manotacao"></span></div> 
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				      </div>
				    </div>
				  </div>
				</div>
			
			</section>
		</div>
		</div>
		<jsp:include page="footer.jsp" />
</body>
</html>
