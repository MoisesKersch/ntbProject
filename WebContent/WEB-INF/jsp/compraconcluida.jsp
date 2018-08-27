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

						<div class="box-body">
	                  		<h1>Parabéns!!!</h1>
	                  		<p>Sua compra foi concluida com sucesso
	                  		<p>Pedido: ${pedido.numero}
							<c:if test="${pedido.upgrade == 'Y'}">
	                  			<h3>${pedido.descricao}</h3>
	                  		</c:if>
	                  		
	                  		<c:if test="${pedido.vendaDireta == 'Y'}">
	                  			<p><a class="btn btn-primary" href="compras?venda=true"><i class="fa fa-shopping-cart"></i> <span>Compras</span></a></p>
	                  		</c:if>
	                  		<c:if test="${pedido.vendaDireta == 'N'}">
	                  			<p><a class="btn btn-primary" href="compras?venda=false"><i class="fa fa-shopping-cart"></i> <span>Compras</span></a></p>
	                  		</c:if>
	                  		
	                  		<c:if test="${patrocinadorId != null && patrocinadorId != ''}">
		                  		<p>
		                  			<a class="btn btn-success text-center" href="patrocinadorlogin">
		                  				<span>Sair dessa sessão e logar como:</span>
		                  				<br></i><span> ${patrocinadorNome}</span>
		                  			</a>
		                  		</p>
	                  		</c:if>

	                  		<c:if test="${pedido.amrFormaPagamentoId == 'CARTAO'}">
	                  			<br><br>
	                  			<a id="linkComprovante" href="https://ecommerce.userede.com.br/pos_virtual/cupom.asp?DATA=${pedido.data}&TRANSACAO=201&NUMAUTOR=${pedido.numautor}&NUMCV=${pedido.numcv}&LANGUAGE=" class="btn btn-default" target="_blank">
	                  				<i class="fa fa-ticket"></i>
	                  				<span> Comprovante de venda do cartão</span>
	                  			</a>
	                  			
	                  			<script>
	                  				window.open("https://ecommerce.userede.com.br/pos_virtual/cupom.asp?DATA=${pedido.data}&TRANSACAO=201&NUMAUTOR=${pedido.numautor}&NUMCV=${pedido.numcv}&LANGUAGE=");
	                  			</script>
	                  		</c:if>
	                  					
			            </div>
		           </div>
				</section>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>
    							
	</body>
</html>