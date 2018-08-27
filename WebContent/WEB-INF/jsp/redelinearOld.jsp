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

		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/animate.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquery-ui.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquerysctipttop.css" />">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">


		<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
    	<script>
//       		$.widget.bridge('uibutton', $.ui.button);
    	</script>
    	<script src="<c:url value="/resources/js/demo.js" />" ></script>
    	<script src="<c:url value="/resources/js/dataTables.bootstrap.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />" ></script>
    	

       	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/script.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery-ui.js" />" ></script>
       	<script src="<c:url value="/resources/js/angular-1.0.1.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/custom.js" />" ></script>
       	<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/app.js" />" ></script>
		
		<script>
			$(function() {
				$(".datepicker").datepicker();
				$(".data").mask("99/99/9999");
				$(".cep").mask("99999-999");
            });
		</script>
	</head>
	<style>	
		.ui-tooltip {
    		
		}
	</style>
	
	<body class="${skinUser} sidebar-mini">
		<div class="wrapper">
			<jsp:include page="message.jsp" />
      		<jsp:include page="header.jsp" />
      		<jsp:include page="menu.jsp" />
      	
			<div class="content-wrapper">
				<section id="divContent" class="content">
					<div class="box box-primary">
						<div class="box-header">
		                	<h3 class="box-title">Rede Linear</h3>
		                </div>
	  							  					
<!-- 	  					<div class="form-group"> -->
<!-- 							<a id="recomecar" href="redelinear" type="button" class="btn btn-default"> -->
<!-- 								<span class="glyphicon glyphicon-user" aria-hidden="true"></span> -->
<!-- 								Recomeçar -->
<!-- 							</a> -->
<!-- 							<button type="button" name="action" class="btn btn-default" value="entrar" onclick="carregaAnterior();"> -->
<!-- 								<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> -->
<!-- 								Voltar -->
<!-- 							</button> -->
<!-- 						</div> -->
						
<!-- 						<div class="input-group width300"> -->
<!-- 					      	<input id="pesquisa" type="text" class="form-control width300" placeholder="Buscar por login ou ID..."> -->
<!-- 					      	<span class="input-group-btn"> -->
<!-- 					        	<button class="btn btn-default" type="button" id="buttonPesquisa"> -->
<!-- 					        		<span class="glyphicon glyphicon-search" aria-hidden="true"></span> -->
<!-- 					        	</button> -->
<!-- 					    	</span> -->
<!-- 					    </div> -->
						
						<div class="form">
							<div id="divDataTable" class="dataTable box-body">
								<table id="dataTable" class="table table-striped"
									style="width: 100%">
									<thead>
										<tr>
											<th>ID</th>
											<th>Nome</th>
											<th>Perfil</th>
											<th>Cidade</th>
											<th>Gênero</th>
											<th>Posicionado Em</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
						
						<c:url var="carregaRedeLinearURL" value="desenharedelinear" />
						<script>
							$(document).ready
							(
								function()
								{
									$.getJSON('${carregaRedeLinearURL}', 
									{
										ajax : 'true',
										id : '${usuario}',
										selecionado : '${usuario_filho}'
									}, 
						
									function(data) 
									{
										
										var html = '';
										var len = data.length;
										for (var i = 0; i < len; i++) {
											html += data[i];
										}

										$('#divDataTable #dataTable tbody').html(html);
										$('#divDataTable #dataTable').DataTable({
												"language": {
												    "sEmptyTable": "Nenhum registro encontrado",
												    "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
												    "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
												    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
												    "sInfoPostFix": "",
												    "sInfoThousands": ".",
												    "sLengthMenu": "_MENU_ resultados por página",
												    "sLoadingRecords": "Carregando...",
												    "sProcessing": "Processando...",
												    "sZeroRecords": "Nenhum registro encontrado",
												    "sSearch": "Pesquisar",
												    "oPaginate": {
												        "sNext": "Próximo",
												        "sPrevious": "Anterior",
												        "sFirst": "Primeiro",
												        "sLast": "Último"
												    },
												    "oAria": {
												        "sSortAscending": ": Ordenar colunas de forma ascendente",
												        "sSortDescending": ": Ordenar colunas de forma descendente"
												    }
												}
										});

										//$( ".dataTables_wrapper .dataTables_filter input" ).addClass( "form-control" )

									});
							});
						
							function recarrega(id) {
										
								$.getJSON('${carregaRedeLinearURL}', {
									ajax : 'true',
									id : id,
									selecionado : '${usuario_filho}'
								}, 
						
								function(data) {
									var html = '';
									var len = data.length;
									for (var i = 0; i < len; i++) {
										html += data[i];
									}
									$('#divTree').html(html);				
						
								    // $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', '');
								    $('.tree li.parent_li > span').on('click', function (e) {
								        var children = $(this).parent('li.parent_li').find(' > ul > li');
								        if (children.is(":visible")) {
								            children.hide('fast');
								            $(this).attr('title', '').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
								        } else {
								            children.show('fast');
								            $(this).attr('title', '').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
								        }
								        e.stopPropagation();
								    });
								});
							}
						
							function carregaAnterior() {
								recarrega('ANTERIOR');
							}

							$(document).ready(function() {
								$('#pesquisa').change(
									function() {
										carregaPorLogin();
									}
								);
							});

							$(document).ready(function() {
								$('#buttonPesquisa').change(
									function() {
										carregaPorLogin();
									}
								);
							});

							function carregaPorLogin() {
								$.getJSON('buscaredelinear', {
									ajax : 'true',
									login : $("#pesquisa").val(),
									selecionado : '${usuario_filho}'
								}, 
						
								function(data) {
									var html = '';
									var len = data.length;
									for (var i = 0; i < len; i++) {
										html += data[i];
									}
									$('#divTree').html(html);				
						
								    // $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'That&apos;s what this widget is');
								    $('.tree li.parent_li > span').on('click', function (e) {
								        var children = $(this).parent('li.parent_li').find(' > ul > li');
								        if (children.is(":visible")) {
								            children.hide('fast');
								            $(this).attr('title', '').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
								        } else {
								            children.show('fast');
								            $(this).attr('title', '').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
								        }
								        e.stopPropagation();
								    });
								    $("#pesquisa").focus();
								});						
							}
						</script>

						<c:if test="${readonly == 'Y'}">
							<c:url var="listaUsuariosURL" value="/listausuariospelobase" />
							<script>
								
								$(document).ready(
									function() {
								
										$.getJSON('${listaUsuariosURL}', {
											ajax : 'true',
											id : '${usuario}'
										}, 
							
										function(data) {
											var html = '';
											var len = data.length;
											var id = '${usuario_filho}';
											for ( var i = 0; i < len; i++) {
												if (id == null || id == '') 
													id = data[i].id;
												html += '<option value="' + data[i].id + '">' + data[i].nome + '</option>';
											}
											html += '</option>';
											$('#filho').html(html);
											$('#filho').val(id);
											$('#filho').removeClass("hidden");
										});

										$('#filho').change(
											function() {
												$.post("usuariofilho", 
													{
														id : $('#filho').val()
													},{});
												window.location.href = 'redelinear';
											}
									);
								});
							</script>
						</c:if>

						<script>
							$("#pesquisa").focus();
						</script>
					</section>
				</div>
			<jsp:include page="footer.jsp" />      
		<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
    	</div>
	</body>
	
</html>