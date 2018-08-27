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
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquery-ui.css" />">
				
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
				            <h3 class="box-title inboxTitle">Ativação</h3>
				        </div>
						<div id="divContent" class="box-body">
							<div class="table-responsive">
								<table class="table table-bordered table-striped">
									<tr>
										<th>Período</th>
										<th>Descrição</th>
										<th>Vencimento</th>
										<th>Valor</th>
										<th>Pagamento</th>
										<th style="width: 40px;"></th>
									</tr>
									<c:forEach var="item" items="${lista}">
										<tr>
											<td>${item.periodo}</td>
											<td>${item.descricao}</td>
											<td><fmt:formatDate value="${item.datePlanned}" type="both" pattern="dd-MM-yyyy" /></td>
											<td><fmt:formatNumber value="${item.amount}" type="currency" currencySymbol="R$ " /></td>
											<td>
												<c:if test="${item.isPaid == 'N'}">
													<c:if test="${item.isExibeBoleto == 'Y'}">
														<a href="downloadboleto/${item.cDebtPaymentId}" class="btn btn-default" title="Imprimir boleto">
															<span class="fa fa-barcode" aria-hidden="true"></span>
														</a>
													</c:if>
												</c:if>
												<c:if test="${item.isPaid == 'Y'}">
													<fmt:formatDate value="${item.dataPagamento}" type="both" pattern="dd-MM-yyyy" />
												</c:if>
											</td>
											<td>
												<a href="#" class="btn btn-default" onclick="abrirModal('${item.amrAtivacaoId}');" title="Detalhes">
													<span class="fa fa-info" aria-hidden="true"></span>
												</a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>

						</div>
					</div>
				<div id="detalhes" class="modal" tabindex="-1" role="dialog">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title"><strong>Detalhes</strong></h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div id="detalhamento" class="table-responsive"></div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
							</div>
						</div>
					</div>
				</div>
			</section>
			</div>
			<script>
				function abrirModal(id) {
	            	$.ajax({
            		  "async": false,
            		  "url": "buscaitensativacao",
            		  "method": "POST",
            		  "data": {
            			  "id": id
            		  }
            		}).done(function (r) {
            			var htmlModal = "<table class=\"table table-bordered table-striped\">"+
							"<tr>"+
								"<th>Descrição</th>"+
								"<th>Valor</th>"+
							"</tr>";
						for (i in r) {
							var valor = "";
							if (!(r[i].valor == null || r[i].valor == '' || r[i].valor == undefined)) {
								valor = "R$ " + r[i].valor.toFixed(2).replace(".",",");
							}
							htmlModal += "<tr>"+
								"<td>" + r[i].descricao + "</td>"+
								"<td>" + valor + "</td>"+
							"</tr>";
						}
						htmlModal += "</table>";

            			$('#detalhamento').html(htmlModal);
            			$('#detalhes').modal();
            			console.log(r);
            		});
				}
			</script>
			<jsp:include page="footer.jsp" />
    	</div>
		<c:if test="${go != null && go != ''}">
			<script>
				get('${go}');
			</script>
		</c:if>
	</body>
</html>