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

	<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />"></script>
	<script src="<c:url value="/resources/js/checkoutpagseguro.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
	<script src="<c:url value="/resources/js/demo.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>
	<script src="<c:url value="/resources/js/script.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
	<script src="<c:url value="/resources/js/custom.js" />"></script>
	<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" />"></script>
	<script src="<c:url value="/resources/js/app.min.js" />"></script>
	<script type="text/javascript">
		// Utilizada pelo PagSeguro
		var compraID    = "${compraID}";
		var sessaoID    = "${sessaoPS}";
		var urlImage    = "${urlImagePS}";
		var valorCompra = "${valorCompra}";
		var parcJuros   = "${parcJurosPS}";
		var soBoleto    = "${soBoleto}";
	</script>
</head>

<body class="${skinUser} sidebar-mini">

	<input type="hidden" name="id" value="">

	<div class="wrapper">
		<jsp:include page="message.jsp" />
		<jsp:include page="header.jsp" />
		<jsp:include page="menu.jsp" />

		<div class="content-wrapper">
			<section class="content">
				<div class="box box-primary">
					<div class="box-body">
						<div class="col-sm-12">
							<div id="payment-methods">
								<img id="imageLoading" src="<c:url value="/resources/img/carregando.gif" />">
							</div>
						</div>
<!-- 						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-2"> -->
<!-- 							<a href="#" class="btn btn-success pagar" onclick="efetuarPagamento();"> -->
<!-- 								Efetuar Pagamento -->
<!-- 							</a> -->
<!-- 						</div> -->
					</div>
					<!-- BEGIN - Modal CREDIT_CARD -->
					<div id="modalCreditCard" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalCreditCard" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="modalCreditCard">Cartão de Crédito</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<div class="col-sm-12">
										<div class="form-group">
											<label for="creditCardNumber">Número do Cartão (Somente números)</label>
											<input id="creditCardNumber" type="number" class="form-control" onkeyup="buscarDadosCartao(this.value);">
										</div>
										<div class="form-group">
											<label for="creditCardHolderName">Titular do Cartão (Nome Completo)</label>
											<input id="creditCardHolderName" type="text" class="form-control" >
										</div>
									</div>
									<div id="info-credit-card"></div>
									<div id="inst-credit-card"></div>
								</div>
								<div class="modal-footer">
									<div class="col-sm-12">
										<button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
										<button type="button" class="btn btn-success" onclick="criarTokenCartao();">Efetuar Pagamento</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- END - Modal CREDIT_CARD -->
				</div>
			</section>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
	<script type="text/javascript" src="${libjsPS}"></script>
</body>
</html>