<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>	
<!DOCTYPE html>
<html lang="pt">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BackOffice</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/font-awesome.min.css" />">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/AdminLTE.css" />">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/style.css" />">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/skins/_all-skins.min.css" />">

<script
	src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script>
	$.widget.bridge('uibutton', $.ui.button);
</script>
<script src="<c:url value="/resources/js/demo.js" />"></script>

<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />"></script>
<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>
<script src="<c:url value="/resources/js/script.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
<script src="<c:url value="/resources/js/custom.js" />"></script>
<script
	src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" />"></script>
<script src="<c:url value="/resources/js/app.min.js" />"></script>
<script src="<c:url value="/resources/plugins/chartjs/Chart.js" />"></script>
<script
	src="<c:url value="/resources/plugins/chartjs/Chart.HorizontalBar.js" />"></script>

<script src="<c:url value="/resources/js/editarminhalista.js" />"></script>

	<script>
		$(function() {
			$(".datepicker").datepicker();
			$(".data").mask("99/99/9999");
			$(".cep").mask("99999-999");
			$(".cpf").mask("999.999.999-99");
			$(".cnpj").mask("99.999.999/9999-99");
			$(".phone").mask("99 999999999");
		    $(document).tooltip();
           });
		$(function() {
		    $('.required-icon').tooltip({
		        placement: 'left',
		        title: 'Required field'
		        });
		});
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
					<form:form id="saveTheList" name="Contato" class="form-horizontal" action="${acao}" method="post" commandName="contato" role="form">
						<div class="box-header">
							<h3 class="box-title inboxTitle">${tipo}</h3>
							<button type="submit" class="btn btn-light" style="float: right;"> ${button}</button>
						</div>
						<div class="box-body">
							<div class="form-group"> 
								<div class="col-sm-12"> 
									<form:hidden class="form-control" path="id" id="id" /> 
									<form:hidden class="form-control" path="adClientId" id="adClientId" /> 
									<form:hidden class="form-control" path="adOrgId" id="adOrgId" /> 
 									<form:hidden class="form-control" path="isactive" id="isactive" /> 
									<form:hidden class="form-control" path="cBpartneriId" id="cBpartneriId" /> 
									<form:hidden class="form-control" path="classificacao" id="classificacao" value="1" /> 
 									<form:hidden class="form-control" path="createdby" id="createdby" value="1" /> 
									<form:hidden class="form-control" path="updatedby" id="updatedby" value="1" /> 
 								</div>
							</div> 

							<div class="form-group">
								<label for="nome" class="col-sm-2 control-label"> Nome <form:errors
										path="nome" cssClass="tooltip-erro" />
								</label>
								<div class="col-sm-5">
									<form:input class="form-control required" path="nome" id="nome"
										maxlength="60" />
								</div>
							</div>

							<div class="form-group">
								<label for="email" class="col-sm-2 control-label">Telefone</label>
								<div class="col-sm-5">
									<form:input class="form-control phone" path="telefone"
										id="telefone" maxlength="80" />
									<form:errors type="number" path="telefone"
										cssClass="tooltip-erro" />
								</div>
							</div>

							<div class="form-group">
								<label for="email" class="col-sm-2 control-label">Celular</label>
								<div class="col-sm-5">
									<form:input class="form-control phone" path="celular"
										id="celular" maxlength="80" />
									<form:errors path="celular" cssClass="tooltip-erro" />
								</div>
							</div>

							<div class="form-group">
								<label for="email" class="col-sm-2 control-label">Whatsapp</label>
								<div class="col-sm-5">
									<form:input class="form-control phone" path="whatsapp"
										id="whatsapp" maxlength="80" />
									<form:errors path="whatsapp" cssClass="tooltip-erro" />
								</div>
							</div>

							<div class="form-group">
								<label for="email" class="col-sm-2 control-label">Skype</label>
								<div class="col-sm-5">
									<form:input class="form-control" path="skype" id="skype"
										maxlength="80" />
									<form:errors path="skype" cssClass="tooltip-erro" />
								</div>
							</div>

							<div class="form-group">
								<label for="email" class="col-sm-2 control-label">Email</label>
								<div class="col-sm-5">
									<form:input class="form-control" path="email" id="email"
										maxlength="80" type="email" />
									<form:errors path="email" cssClass="tooltip-erro" />
								</div>
							</div>
						</div>
					</form:form>

					<div class="box-body">
						<div class="box-header">
							<h3 class="box-title inboxTitle"></h3>
							<button type="submit"  data-toggle="modal"  data-target="#exampleModal" class="btn btn-light" style="float: right;">Adicionar Nova Lista</button>
						</div>
						
						<table class="table table-bordered table-striped" id="lista-agenda">
							<tr>
								<th align="center" class="text-center" width="150px">Assunto</th>
								<th align="center" class="text-center" width="150px">Anotação</th>
								<th align="center" class="text-center" width="150px">Quando</th>
								<th align="center" class="text-center" width="150px">Onde</th>
								<th align="center" class="text-center" width="150px">Situação</th>
								<th align="center" class="text-center" width="150px">Acão</th>
							</tr>
							<c:forEach var="listItems" items="${contato.agendas}">
								<tr id="${listItems.id}">
									<td > ${listItems.assunto} </td>
								    <td> ${listItems.anotacao} </td>
								    <td> <fmt:formatDate value="${listItems.quando}" pattern="dd/MM/yyyy" /></td>
								    <td> ${listItems.onde} </td>
								    <td> ${listItems.situacao} </td>
								    <td>
										<a class="btn btn-info btn-sm" href="#" id="${listItems.id}edit" onclick="openModal('${listItems.id}')">
											<span class="glyphicon glyphicon-pencil"></span>
										</a>
										
									    <a href="#" class="btn btn-info btn-sm deleteButton" href="#" id="${listItems.id}delete" data-json="${listItems.id}" onclick="deleteList('${listItems.id}')">
								          	<span class="glyphicon glyphicon-trash"></span>  
								        </a>
								    </td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</section>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
	
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title inboxTitle">Agenda</h3>
				</div>
				<div class="box-body">
					<form:form id="agendaForm" name="Agenda" class="form-horizontal"
						action="${modalType}" method="post" commandName="agenda" role="form">
						<div class="form-group">
							<label for="assunto" class="col-sm-2 control-label ">Assunto</label>
							<div class="col-sm-10 marginModal">
								<form:input class="form-control" path="assunto" id="assunto" />
							</div>
						</div>

						<div class="form-group">
							<label for="anotacao" class="col-sm-2 control-label">Anotação</label>
							<div class="col-sm-10 marginModal">
								<form:textarea class="form-control required" path="anotacao" id="anotacao" rows="10" cols="60" style="resize:none" />
							 </div>
						</div>

						<div class="form-group">
							<label for="quando" class="col-sm-2 control-label">Quando</label>
							<div class="col-sm-10 marginModal">
								<form:input class="form-control data" path="quando" id="quando" />
							</div>
						</div>

						<div class="form-group">
							<label for="onde" class="col-sm-2 control-label">Onde</label>
							<div class="col-sm-10 marginModal">
								<form:input class="form-control" path="onde" id="onde" />
							</div>
						</div>
						
						<div class="form-group"> 
							<label for="situacao" class="col-sm-2 control-label">Situação</label> 
							<div class="col-sm-10 marginModal">
								<form:radiobutton path="situacao" class="situacao" value="A"/>Agendado
								<form:radiobutton path="situacao" class="situacao" value="R"/>Resolvido
							</div>
						</div>
							
						<div class="form-group">
							<label for="amrAgendaAcaoId" class="col-sm-2 control-label">Ação</label>
							<div class="col-sm-10 marginModal">
								<form:select id="amrAgendaAcaoId" path="amrAgendaAcaoId"
									class="form-control form-control-sm">
									<form:option value="NONE" label="Selecionar" />
									<form:options items="${agendaAcao}" itemLabel="nome"
										itemValue="id" />
								</form:select>
							</div>
						</div>
					</form:form>
					
					<div class="modal-footer">
						<a href="#" class="btn btn-primary typeRequest" id="${modalType}" onclick="popularContato();"> <span id="modalConfirmButton"> Adicionar a Lista </span>
						</a>
						<button type="button" id="closeModal" class="btn btn-secondary" data-dismiss="modal" onclick="closeModal()">Fechar</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
