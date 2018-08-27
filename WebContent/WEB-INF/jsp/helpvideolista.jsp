<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		
		<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
    	<script>
      		$.widget.bridge('uibutton', $.ui.button);
    	</script>
    	<script src="<c:url value="/resources/js/demo.js" />" ></script>
    	
       	<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/script.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery-ui.js" />" ></script>
       	<script src="<c:url value="/resources/js/custom.js" />" ></script>
       	<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/app.min.js" />" ></script>
       	
       	
		<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
		<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
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
				            	<h3 class="box-title inboxTitle">Videos</h3>
				            </div>											
						<div class="box-body">
							<table id="table-helpvideos" class="table table-bordered table-striped">
							
								<thead>
									<tr>
										<th>Nome</th>
										<th>Descri��o</th>
										<th width="70px"></th>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach var="item" items="${lista}">
										<tr>
											<td>${item.nome}</td>
											<td>${item.descricao}</td>
											<td>
												<a href="helpvideover?id=${item.id}" class="btn btn-default">
													<i class="fa fa-youtube"></i>
													<i class="fa fa-play"></i>
												</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
			
						</div>
					</div>								
				</section>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>
	</body>
   	<script>
    		$(document).ready(function() {
    			$('#table-helpvideos').DataTable({
			"language": {
			    "sEmptyTable": "Nenhum registro encontrado",
			    "sInfo": "Mostrando de _START_ at� _END_ de _TOTAL_ registros",
			    "sInfoEmpty": "Mostrando 0 at� 0 de 0 registros",
			    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
			    "sInfoPostFix": "",
			    "sInfoThousands": ".",
			    "sLengthMenu": "_MENU_ resultados por p�gina",
			    "sLoadingRecords": "Carregando...",
			    "sProcessing": "Processando...",
			    "sZeroRecords": "Nenhum registro encontrado",
			    "sSearch": "Pesquisar",
			    "oPaginate": {
			        "sNext": "Pr�ximo",
			        "sPrevious": "Anterior",
			        "sFirst": "Primeiro",
			        "sLast": "�ltimo"
			    },
			    "oAria": {
			        "sSortAscending": ": Ordenar colunas de forma ascendente",
			        "sSortDescending": ": Ordenar colunas de forma descendente"
			    }
			}
  			});
	});
  	</script>
</html>