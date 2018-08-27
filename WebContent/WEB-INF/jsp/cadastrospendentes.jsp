<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
				
		<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
    	<script>
      		$.widget.bridge('uibutton', $.ui.button);

      		function removerCadastroPendente(direto) {
      			$.ajax({
      			  "async": true,
      			  "crossDomain": true,
      			  "url": "removercadastropendente?direto="+direto,
      			  "method": "GET",
      			  
      			}).done(function(response) {
      				window.location.href= 'cadastrospendentes';
      			});
      		}

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
		                	<h3 class="box-title inboxTitle">Cadastros pendentes</h3>
		                </div>

	                  	<div id="divContent" class="box-body">
		                  	<div class="table-responsive">
		                  		<table class="table table-bordered table-striped">
									<tr>
										<th>Login</th>
										<th>Nome</th>
										<th>CPF</th>
										<th>Perfil</th>
										<th>Cliente desde</th>
										<th>Cidade</th>
										<th></th>
										<th></th>
									</tr>
							
									<c:forEach var="item" items="${lista}">
										<tr>
											<td>${item.login}</td>
											<td>${item.diretoNome}</td>
											<td>${item.diretoCpf}</td>
											<td>${item.perfil}</td>
											<td>${item.clienteDesde}</td>
											<td>${item.cidade}</td>
											<td>
												<a class="btn btn-default" href="#" onclick="get('cadastrospendentesedita/${item.direto}');">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
													Editar
												</a>
											</td>
											<td>
												<a class="btn btn-default" href="#" onclick="removerCadastroPendente('${item.direto}');">
													<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
													Remover
												</a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
		            	</div>
		            </div>
				</section>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>
    							
	</body>

</html>