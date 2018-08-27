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
       	<script src="<c:url value="/resources/plugins/chartjs/Chart.js" />" ></script>
       	<script src="<c:url value="/resources/plugins/chartjs/Chart.HorizontalBar.js" />" ></script>
			
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
				            <h3 class="box-title inboxTitle">Meus Pontos</h3>
				        </div>	

						<div class="box-body">
						
							<table class="table table-bordered table-striped">
								<tr>
									<th>Descrição</th>
									<th align="center" class="text-center" width="150px">Saldo Anterior</th>
									<th align="center" class="text-center" width="150px">Saldo Atual</th>
									<th align="center" class="text-center" width="150px">Acumulado</th>
								</tr>
	
								<c:forEach var="item" items="${lista}">
									<tr>
										<td>Volume Esquerda</td>
										<td align="center">${item.saldoAnteriorEsquerda}</td>
										<td align="center">${item.saldoEsquerda}</td>
										<td align="center">${item.equipeEsquerda}</td>
									</tr>
									<tr>
										<td>Volume Direita</td>
										<td align="center">${item.saldoAnteriorDireita}</td>
										<td align="center">${item.saldoDireita}</td>
										<td align="center">${item.equipeDireita}</td>
									</tr>
								</c:forEach>
	
							</table>
								
							<span id="helpBlock" class="help-block">
								<h3>Como fazer a leitura desta tela?</h3>								
								<p>A coluna "Saldo Anterior" mostra o fechamento dos pontos do dia anterior e que serão calculados para o seu ganho no dia.
								<br>A coluna "Saldo Atual" mostra os pontos que estão sendo gerados em tempo real para o fechamento do dia seguinte.
								<br>A coluna "Acumulados" mostra os pontos que já foram fechados já com o"Saldo Anterior".
								<br>Se os pontos "Saldo Anterior" estão zerados e você tinha binário a receber consulte seu extrato financeiro.</p>
							</span>													
			            </div>
		            </div>
		            
		            
		            <div class="box box-primary">
		                <div class="box-header">
		                	<h3 class="box-title">Carreira</h3>
		                </div>
					
						<div class="box-body">
							<script>
								var labels = [];
								var dataset = [];
								var dataSaldo = [];
							</script>
							<c:forEach var="item" items="${carreira}">
								<script>						
									labels.push('${item.nome}');
									dataset.push(${item.realizado});
									dataSaldo.push('${item.saldoEmFormatado}');
								</script>
							</c:forEach>
							
							<div style="width: 100%;">
								<canvas id="my-chart"></canvas>
							</div>
														
		            		<script src="<c:url value="/resources/js/meuspontos.js" />" ></script>
		            		
			            </div>
		            </div>
					
				</section>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>
    							
	</body>

</html>