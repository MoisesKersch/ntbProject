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
							
							<form:form id="formCard" name="formCard" action="checkoutrede" target="vpos" class="form-horizontal" method="post" commandName="form" role="form">							
								
								<form:hidden path="id"/>
															
								<div class="form-group">
									<div id="divCartaoRede" class="col-sm-12"></div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-10">
										<form:radiobutton path="bandeira" value="MASTERCARD" onclick="carregaBandeira('MASTERCARD', '${form.total}');"/>
										<img class="bandeira" onclick="carregaBandeira('MASTERCARD', '${form.total}');" src="<c:url value="/resources/img/bandeiras/mastercard.jpg" />">										
										
										<form:radiobutton path="bandeira" value="DINERS" onclick="carregaBandeira('DINERS', '${form.total}');"/>
										<img class="bandeira" onclick="carregaBandeira('DINERS', '${form.total}');" src="<c:url value="/resources/img/bandeiras/diners.jpg" />">
										
										<form:radiobutton path="bandeira" value="VISA" onclick="carregaBandeira('VISA', '${form.total}');"/>
										<img class="bandeira" onclick="carregaBandeira('VISA', '${form.total}');" src="<c:url value="/resources/img/bandeiras/visa.jpg" />">
										
										<form:radiobutton path="bandeira" value="HIPER" onclick="carregaBandeira('HIPER', '${form.total}');"/>
										<img class="bandeira" onclick="carregaBandeira('HIPER', '${form.total}');" src="<c:url value="/resources/img/bandeiras/hiper.jpg" />">
										
										<form:radiobutton path="bandeira" value="HIPERCARD" onclick="carregaBandeira('HIPERCARD', '${form.total}');"/>
										<img class="bandeira" onclick="carregaBandeira('HIPERCARD', '${form.total}');" src="<c:url value="/resources/img/bandeiras/hipercard.jpg" />">
									
								</div>
						 		</div>
						 		
						 		<div class="form-group" id="groupParcelas">
						   			<label for="parcelas" class="col-sm-2 control-label">Condições de pagamento:</label>
						   			<div class="col-sm-10">
						   				<form:select class="form-control width400" path="parcelas" id="parcelas"></form:select>												
						   			</div>
						 		</div>
						 		
						 		<div class="form-group">
						   			<div class="col-sm-offset-2 col-sm-10">
										 <a href="#" id="autorizaCompra" onclick="concluiCompra();" class="btn btn-primary">
<!-- 										<a href="#" id="autorizaCompra" class="btn btn-primary"> -->
											<i class="fa fa-lock"></i>
											<span>  Abrir tela segura para digitar informações do cartão</span>
										</a>
						   			</div>
						 		</div>

							</form:form>
														
						</div>
					</div>								
				</section>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>
	</body>
	
	<input type="text" id="ultimaBandeira" />
	<c:url var="listaParcelasURL" value="/lista_redeparcelas" />
	<script>
	
		function carregaBandeira(b, valor) {
			
			var band = $('#ultimaBandeira').val();

			if(band != b) {

				$('#ultimaBandeira').val(b);
				$('input:radio[name=bandeira][value=' + b + ']').click();		
				$.getJSON("${listaParcelasURL}", { 
					bandeira : b, 
					total: valor,
					amrPreCompraId: '${form.id}',
					ajax : 'true'
				},
	
				function(data) {
					var items = "";
				    $.each(data, function(key) {
				        items += '<option value="' + data[key].id + '">' + data[key].nome + '</option>';
				    });  
				    $("#parcelas").html(items);			    
		    	});
			}
		}
	
		function concluiCompra() {
			postFormAjaxRegion("checkoutcielo", $('#formCard').serialize(), 'divCartaoRede');
		}
		
		$(document).ready(function() {			
			$('input:radio[name=bandeira]').each(function () { $(this).prop('checked', false); });
			// bandeira('MASTERCARD', '${form.total}');
		});
	</script>

</html>