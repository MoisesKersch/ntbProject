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
//       		$.widget.bridge('uibutton', $.ui.button);
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
							
							<div class="box ativo-box">
								<div class="box-header">
									<h3 class="box-title">
										<span style="font-size: 30px; font-weight: BOLD;">${pedido.descricao}</span>
									</h3>
									
									
									
									
									<div style="display: block;" class="box-body pad">			
										  		
							      		<table class="table table-bordered table-striped">
											<tr>
												<td width="20px"></td>
												<td>Nome</td>
												<td width="100px">Quantidade</td>
												<td width="100px">Preço</td>
												<td width="180px">Total</td>
											</tr>
								      		<c:forEach var="item" items="${itens}" varStatus="row">
												<tr>
													<td><small>${row.count}</small></td>
													<td>${item.nome}</td>
													<td align="right">${item.quantidade}</td>
													<td align="right">	
														<span id="preco${item.id}"></span>
													</td>
													<td align="right">
														<span id="total${item.id}"></span>
														<script>
										      				var preco = '${item.precoUnitario}'.replace('.', ',');
										      				$('#preco${item.id}').html('R$ ' + preco);
										      				
										      				var total = '${item.total}'.replace('.', ',');
										      				$('#total${item.id}').html('R$ ' + total);
										      				
										      				var total2 = '${item.total}' + $('#valorTotal').html();
										      				$('#valorTotal').html(total2);
										      			</script>
										      		</td>
												</tr>
											</c:forEach>

											<tr>
												<td colspan="4" style="border-left: 1px solid white; border-bottom: 1px solid white;"></td>
												<td align="right">
													<span id="preco${pedido.id}" style="font-weight: BOLD;font-size:20px;"></span>
													<script>
									      				var preco = '${pedido.totalLinhas}'.replace('.', ',');
									      				$('#preco${pedido.id}').html('R$ ' + preco);
									      			</script>	
												</td>
											</tr>
											
										</table>
									</div>
									
										
								</div>
							</div>

							<br><p>
				      		<div class="box ativo-box">
								<div class="box-header">
									<h3 class="box-title">
										Método de entrega
									</h3>
								</div>
					
								<div style="display: block;" class="box-body pad">			
									<div class="form-group">
										<c:forEach var="item" items="${metodosentrega}">
											<c:if test="${item.valor != null}">
					                      		<div class="radio">
					                        		<label>
					                        			<input style="margin-top: 3px;" type="radio" name="radioMetodoPagamento" id="radioMetodoPagamento${item.id}" value="${item.id}" checked>
					                        				${item.servico} (<fmt:formatNumber value="${item.valor}" type="currency"/>)
					                        		</label>
					                      		</div>
					                      	</c:if>
					                    </c:forEach>
			                    	</div>
								</div>
							</div>
								      		
							<br><p>
				      		<div class="box ativo-box">
								<div class="box-header">
									<h3 class="box-title">
										Forma de pagamento
									</h3>
								</div>
					
								<div style="display: block;" class="box-body pad">			
									<div class="form-group">
										<c:forEach var="item" items="${formaspagamento}">
				                      		<div class="radio">
				                        		<label>
				                        			<input style="margin-top: 3px;" type="radio" name="radioFormaPagamento" id="radioFormaPagamento${item.id}" value="${item.id}" checked>
				                        				${item.nome}
				                        		</label>
				                      		</div>
					                    </c:forEach>
			                    	</div>
								</div>
							</div>
				      		
				      		<c:if test="${autenticacompra == 'Y'}">
			    				<p><input type="password" class="form-control" id="senha${pedido.id}" maxlength="60" autocomplete="off" placeholder="Senha financeira"/></p>
			    				<script>
			    					$("#senha${pacote.id}").focus();
			    				</script>
			    			</c:if>
			    									
							<a href="#" onclick="compra('${pedido.id}');" class="btn btn-primary">
								<i class="fa fa-usd"></i>  <span><b>Confirmar compra</b></span>
							</a>
							<a href="compras" class="btn btn-danger pull-right">
								<i class="fa fa-remove"></i>  <span><b>Cancelar</b></span>
							</a>				
						</div>
					</div>								
				</section>
			</div>
			<div id="modalLoad" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalLoad" aria-hidden="true">
				<div class="modal-dialog" role="document"><img id="imageLoading" src="<c:url value="/resources/img/carregando.gif" />"></div>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>
	</body>
	
	<script>
		function compra(id) {
			$('#modalLoad').modal();
			var amrMetodoEntregaId = $("input:radio[name=radioMetodoPagamento]:checked").val();
			var formaPagamentoId = $("input:radio[name=radioFormaPagamento]:checked").val();

			var autentica = '${autenticacompra}';
			if(autentica == 'Y') {
				var senha = $('#senha' + id).val();
				window.location.href = "compraupgrade?id=" + id + "&senhaFinanceira=" + senha + "&amrMetodoEntregaId=" + amrMetodoEntregaId + "&formapagamentoid=" + formaPagamentoId;
			}
			else
				window.location.href = "compraupgrade?id=" + id + "&amrMetodoEntregaId=" + amrMetodoEntregaId + "&formapagamentoid=" + formaPagamentoId;
		}
		
		$("#radioFormaPagamento${pedido.amrFormaPagamentoId}").prop("checked", true);
		$("#radioMetodoPagamento${pedido.amrMetodoEntregaId}").prop("checked", true);
	</script>
	
</html>