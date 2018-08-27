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
	
	<script src="<c:url value="/resources/js/minhasreunioes-editor.js" />"></script>
	<script src="<c:url value="/resources/js/minhasreunioes.js" />"></script>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="<c:url value="/resources/plugins/datepicker/locales/datepicker-pt-br.js"/>"></script>
	
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
  	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
  	
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>

	<style>
		tbody tr.selected 
		{
		   color: #023146 !important;
		   background-color: #eeeeee !important;
		}
	</style>
   
	<script>
	  $( function() {
		    $.datepicker.setDefaults($.datepicker.regional['pt-BR']);
		    $( ".datepicker" ).datepicker({format: 'dd/mm/yyyy'});
		   
		  } );
	  
	  $(".time").mask("00:00");
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
						<h3 class="box-title inboxTitle "> Minhas Reuniões
						 </h3>
					</div>
					<div class="box-body">
						<div class="table-responsive">
							<table class="dataTable table table-striped" id="minhasreunioes" style="width: 100%"> 
								  <thead>
						            <tr>
						                <th>Responsável</th>
						                <th>Anfitrião</th>
						                <th>Data/Hora</th>
						                <th>Participantes</th>
						                <th>Observações</th>
						                <th>null</th>
						            </tr>
						        </thead>
					       	 <tbody>
							</table>
						</div>
					</div>
				</div>
			</section>
			
			<div class="modal fade" id="novoReuniaoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title inboxTitle">Reunião</h3>
							</div>
							<div class="box-body">
								<form id="reuniaoForm" name="Reuniao" class="form-horizontal" method="post" role="form">
									<input type="hidden" id="saveOrEdit" value="registrarnovareuniao">	
									<input type="hidden" id="reuniaoId" name="reuniaoId">								
									<div class="form-group"> 
										<label for="papel" class="col-sm-4 control-label"></label> 
										<div class="col-sm-8 marginModal">
											 <label class="radio-inline">
									    		<input type="radio" name="papel" class="papel" value="responsavel" id="responsavel" checked><strong>Responsável</strong>
										    </label>
										    <label class="radio-inline">
										      	<input type="radio" name="papel" class="papel" value="anfitriao"  id="anfitriao"><strong>Anfitrião</strong>
										    </label>
										</div>
									</div>
									
									<div class="form-group" id="anfitriaoInput">
										<label for="anfitriaoId" class="col-sm-4 control-label">ID Anfitrião</label>
										<div class="col-sm-8 marginModal">
											<input type="number" class="form-control" id="anfitriaoId"  name="anfitriaoId"  />
										</div>
									</div>
									
									<div class="form-group hidden" id="responsavelInput">
										<label for="responsavelId" class="col-sm-4 control-label">ID Responsável</label>
										<div class="col-sm-8 marginModal">
											<input type="number" class="form-control" id="responsavelId"  name="responsavelId" />
										</div>
									</div>
									
									<div class="form-group">
										<label for="nome" class="col-sm-4 control-label">Nome do<span id="resoran"> Anfitrião </span></label>
										<div class="col-sm-8 marginModal">
											<input class="form-control" id="nome" name="nome" readonly/> 
										 </div>
									</div>
									
									<div class="form-group">
										<label for="data" class="col-sm-4 control-label">Data</label>
										<div class="col-sm-8 marginModal">
											<input class="form-control datepicker dateFormat" id="data" autocomplete="off" name="data"/>
										</div>
									</div>

									<div class="form-group">
										<label for="quando" class="col-sm-4 control-label">Hora</label>
										<div class="col-sm-8 marginModal">
											<input class="form-control time" id="hora" autocomplete="off" name="hora"/>
										</div>
									</div>
									
									<div class="form-group">
										<label for="participantes" class="col-sm-4 control-label ">Participantes</label>
										<div class="col-sm-8 marginModal">
											<input type="number" class="form-control" id="participantes" name="participantes" />
										</div>
									</div>
									
									<div class="form-group">
										<label for="observacoes" class="col-sm-4 control-label">Observações</label>
										<div class="col-sm-8 marginModal">
											<textarea class="form-control" id="observacoes" name="observacoes" rows="10" cols="60" style="resize:none" > </textarea>
										 </div>
									</div>
									
									<div class="modal-footer">
										 <button type="button" class="btn btn-default" id="reuniaoController">Enviar</button>
										<button type="button" id="closeModal1" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade" id="deleteReuniao" tabindex="-1" role="dialog" aria-labelledby="deleteReuniao" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title inboxTitle">Voce tem certeza que deseja excluir esta reunião?</h3>
							</div>
							<div class="box-body">
								<div class="modal-body">
									<input type="hidden" id="remove" name="remove">
							        <p></p>
							     </div>
							</div>
							<div class="modal-footer">
								 <button type="button" class="btn btn-default" id="deleteControllerReuniao">Excluir</button>
								<button type="button" id="closeModal" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
							</div>
						</div>
					</div>
				</div>
		</div>
		</div>
		<jsp:include page="footer.jsp" />
</body>
</html>
