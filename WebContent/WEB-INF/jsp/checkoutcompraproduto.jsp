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
		
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/checkoutcompras.css" />">
				
		<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
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
		
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
		
       	<style>
       		.btn-gold {
	       		background: rgba(252,234,187,1);
				background: -moz-linear-gradient(top, rgba(252,234,187,1) 0%, rgba(252,205,77,1) 50%, rgba(248,181,0,1) 51%, rgba(251,223,147,1) 100%);
				background: -webkit-gradient(left top, left bottom, color-stop(0%, rgba(252,234,187,1)), color-stop(50%, rgba(252,205,77,1)), color-stop(51%, rgba(248,181,0,1)), color-stop(100%, rgba(251,223,147,1)));
				background: -webkit-linear-gradient(top, rgba(252,234,187,1) 0%, rgba(252,205,77,1) 50%, rgba(248,181,0,1) 51%, rgba(251,223,147,1) 100%);
				background: -o-linear-gradient(top, rgba(252,234,187,1) 0%, rgba(252,205,77,1) 50%, rgba(248,181,0,1) 51%, rgba(251,223,147,1) 100%);
				background: -ms-linear-gradient(top, rgba(252,234,187,1) 0%, rgba(252,205,77,1) 50%, rgba(248,181,0,1) 51%, rgba(251,223,147,1) 100%);
				background: linear-gradient(to bottom, rgba(252,234,187,1) 0%, rgba(252,205,77,1) 50%, rgba(248,181,0,1) 51%, rgba(251,223,147,1) 100%);
				filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fceabb', endColorstr='#fbdf93', GradientType=0 );
				border: 1px solid #AE7F00;
				color: #584000;
			}
       	</style>
      	<script>
      	
			$(function() {
				$(".datepicker").datepicker();
				$(".codigo").mask("999999999999999");
				
			    $(document).tooltip();
	           });
			
			$(function() {
			    $('.required-icon').tooltip({
			        placement: 'left',
			        title: 'Required field'
			        });
			});
			
			function realParaNumber(texto)
			{
				if (texto != null && texto !== "" && texto != undefined)
				{
					var compativelComParseFloat = texto.replace("R$ ","");
				    compativelComParseFloat = compativelComParseFloat.replace(",",".");
				    var valor = parseFloat(compativelComParseFloat);
				    return valor;
				}
				else return null;
			    
			}
			
			function numberToReal(numero) 
			{
				if (numero != null && numero !== "" && numero != undefined)
				{
					var numero = numero.toFixed(2).split('.');
					numero[0] = numero[0].split(/(?=(?:...)*$)/).join('.');
					numero = numero.join(',');
				    
				    numero = numero.replace("-.", "-");
				    
				    return numero;
				}
				else return null;
			}
			
			var totalDescontos;
			function discount(total, valorSemDesconto)
			{
				var resultado = valorSemDesconto - realParaNumber(total);
				
				console.log(numberToReal(resultado));
				
				totalDescontos = resultado;
				
				$('#totalDescontos').html("R$ "+ numberToReal(resultado));
			}
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

						<div class="box-body">
							
							<div class="box ativo-box">
								<div class="box-header">
									<h3 class="box-title">
										Pedido<span style="font-size: 30px; font-weight: BOLD;"> ${pedido.numero} </span>
									</h3>
									<br>
									${pedido.cliente.codigo} - ${pedido.cliente.nome}
								</div>

								<div style="display: block;" class="box-body pad">	
									<div class="table-responsive"> 
								      	<table class="table table-bordered table-striped" id="checkoutcompraprodutoTable" style="width:100%">
								      		<thead>
												<tr>
													<td width="20px"></td>
													<td width="80px"></td>
													<td>Nome</td>
													<td width="100px">Quantidade</td>
													<td width="100px">Preço</td>
													<td width="180px">Total</td>
												</tr>
											 </thead>
											 <tbody> 
									      		<c:forEach var="item" items="${itens}" varStatus="row">
													<tr>
														<td><small>${row.count}</small></td>
														<td><img src="${item.imagemURL}" alt="" class="" height="80px"></td>
														<td>${item.nome}</td>
														<td align="right">${item.quantidade}</td>
														<td align="right">	
															<span id="preco${item.id}"></span>
														</td>
														<td align="right">
															<span id="total${item.id}"></span>
															<script>
											      				var preco = '${item.precoDe}'.replace('.', ',');
											      				$('#preco${item.id}').html('R$ ' + preco);
											      				
											      				var precoDe = ${item.precoDe}
											      				var quantidade =  ${item.quantidade}
											      				var total = precoDe * quantidade;
											      				
											      				$('#total${item.id}').html('R$ ' + numberToReal(total) );
											      				var total2 = '${item.total}' + $('#valorTotal').html();
											      				$('#valorTotal').html(total2);
											      			</script>
											      		</td>
													</tr>
												</c:forEach>
												 <tfoot>
												 	<tr>
														<td colspan="4" style="border-left: 1px solid white; border-bottom: 1px solid white;"></td>
														<td align="right">
															Total Descontos	
														</td>
														<td align="right">
															<span id="totalDescontos" style="font-weight: BOLD;font-size:20px;"> </span>												
														</td>
													</tr>
													<tr>
														<td colspan="4" style="border-left: 1px solid white; border-bottom: 1px solid white;"></td>
														<td align="right">
															Total	
														</td>
														<td align="right">
															<span id="preco${pedido.id}" style="font-weight: BOLD;font-size:20px;"></span>
															<script>
											      				var preco = '${pedido.totalLinhas}'.replace('.', ',');
											      				$('#preco${pedido.id}').html('R$ ' + preco);
											      				discount(preco, "${valorTotalSemDesconto}", "${pedido.id}");
											      			</script>	
														</td>
													</tr>
												</tfoot>
											</tbody>										
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
					                        			<input style="margin-top: 3px;" type="radio" name="radioMetodoPagamento" id="radioMetodoPagamento${item.id}" value="${item.id}">
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
									<div class="col-sm-2">		
										<div class="form-group">
											<c:forEach var="item" items="${formaspagamento}">
					                      		<div class="radio">
					                        		<label>
					                        			<input onclick="getDiscount('${item.id}', '${pedido.totalItens}', '${pedido.id}')" style="margin-top: 3px;" type="radio" name="radioFormaPagamento" id="radioFormaPagamento${item.id}" value="${item.id}" onchange="descricaoFormaPagamento('${item.descricao}','${item.nome}');"
					                        				onselect="alert('dasf');">
					                        				${item.nome}
					                        		</label>
					                      		</div>
						                    </c:forEach>
						                </div>
						            </div>
						            <div class="col-sm-10">
						            	<div id="descricao" class="hidden" style="border: 1px solid rgb(220,220,220); border-radius: 10px 10px 10px 10px; padding: 10px;"></div>
						            </div>
								</div>
							</div>
							
				      		<div class="line-separator"></div>
				      		
				      		<div style="width: 400px;">
					      		<c:if test="${autenticacompra == 'Y'}">
				    				<p><input type="password" class="form-control" id="senhaFinanceira" maxlength="60" autocomplete="off" placeholder="Senha financeira"/></p>
				    			</c:if>
				    			
				    			<a href="checkoutcompraprodutocliente?id=${pedido.id}&taxid=X" class="btn btn-default">
									<i class="glyphicon glyphicon-chevron-left"></i>  <span><b>Endereço</b></span>
								</a>
				    			
				    			<c:if test="${pedido.vendaDireta == 'Y'}">
									<a href="compras?venda=true" class="btn btn-default">
										<i class="glyphicon glyphicon-remove"></i>  <span><b>Voltar</b></span>
									</a>
								</c:if>
								<c:if test="${pedido.vendaDireta != 'Y'}">
									<a href="compras?venda=false" class="btn btn-default">
										<i class="glyphicon glyphicon-remove"></i>  <span><b>Voltar</b></span>
									</a>
								</c:if>
								
								<a href="#" onclick="compra('${pedido.id}');" class="btn btn-primary pull-right" id="concluirButton">
									<span><b>Concluir</b></span> <i class="glyphicon glyphicon-chevron-right"></i>
								</a>
							</div>				

						</div>
					</div>								
				</section>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>
	</body>
	
	<script>
	
		$('#checkoutcompraprodutoTable').DataTable({
    		"language": {
   			 "url": "resources/json/Portuguese-Brasil.json"
   		}
		});
		
		function getDiscount(id, total, preCompraId)
		{
			if (id == "24B1C40DC7364229AF3C452F9B5057E9")
			{
				$.ajax({
					 type: "POST",
					 data:  {"paymentId" : id, "oldValue":  total, "preCompraId": preCompraId},
					 url: "getdiscount",
					 success: function(discount)
					 {
						 var descontoBoleto = totalDescontos + discount;
						 
						 $('#totalDescontos').html("R$ "+ numberToReal(descontoBoleto));
		      			 $('#preco${pedido.id}').html('R$ ' + numberToReal(discount));
					 }
				})
			}
			else
			{
				var preco = '${pedido.totalLinhas}'.replace('.', ',');
				$('#preco${pedido.id}').html('R$ ' + preco);
				$('#totalDescontos').html( 'R$ ' + numberToReal(totalDescontos) );
			}
		}
	
		function compra(id) {
			var amrMetodoEntregaId = $("input:radio[name=radioMetodoPagamento]:checked").val();
			var formaPagamentoId = $("input:radio[name=radioFormaPagamento]:checked").val();
			var senha = $("#senhaFinanceira").val();
			
			if(amrMetodoEntregaId == null || amrMetodoEntregaId == '') {
				$.bootstrapGrowl("<h4 class='message-title'>ERRO</h4><h4 class='message-detail'>Selecione um método de entrega</h4>", {
					ele: 'body',
				  	type: 'danger',
				  	offset: {from: 'top', amount: 40},
				  	align: 'right',
				  	delay: 0,
				  	allow_dismiss: true,
				  	width: 400,
				  	stackup_spacing: 10
				});
		   	}
		   	
			else if(formaPagamentoId == null || formaPagamentoId == '') {
				$.bootstrapGrowl("<h4 class='message-title'>ERRO</h4><h4 class='message-detail'>Selecione uma forma de pagamento</h4>", {
					ele: 'body',
				  	type: 'danger',
				  	offset: {from: 'top', amount: 40},
				  	align: 'right',
				  	delay: 0,
				  	allow_dismiss: true,
				  	width: 400,
				  	stackup_spacing: 10
				});
			}
			
		   	else {
				var voucherid = $('#getVoucherId').val();
				var svoucher = '';
				if (!(voucherid == null || voucherid == '' || voucherid == undefined)) {
					svoucher = "&voucherId=" + voucherid;
				}
				var autentica = '${autenticacompra}';
				if(autentica == 'Y') {
					window.location.href = "compraproduto?id=" + id + "&senhafinanceira=" + senha + "&amrMetodoEntregaId=" + amrMetodoEntregaId + "&formapagamentoid=" + formaPagamentoId + svoucher;
				}
				else
					window.location.href = "compraproduto?id=" + id + "&amrMetodoEntregaId=" + amrMetodoEntregaId + "&formapagamentoid=" + formaPagamentoId + svoucher;
			}
		}
		
		$("input:radio[name=radioFormaPagamento]").prop("checked", false);
		$("input:radio[name=radioMetodoPagamento]").prop("checked", false);
		
		$("#radioFormaPagamento${pedido.amrFormaPagamentoId}").prop("checked", true);
		$("#radioMetodoPagamento${pedido.amrMetodoEntregaId}").prop("checked", true);
		
		$("#radioFormaPagamento${pedido.amrFormaPagamentoId}").change();
		
		function descricaoFormaPagamento(descricao, nome)
		{
			if (nome === 'VOUCHER')
				$('#descricao').html(descricao + "<input type=\"text\" class=\"form-control\" id=\"getVoucherId\" onkeyup=\"isValidAnd8()\"> <span id=\"errorMesssage\" class=\"warning-red-message\"> </span>");
			else
			{
				$('#descricao').html(descricao);
			}
			$('#descricao').removeClass('hidden');
		}
		
		function isValidAnd8()
		{
			console.log($("#getVoucherId").val());
			if ($("#getVoucherId").val().length > 7)
				isValidVoucher($("#getVoucherId").val());
		}
		
		function isValidVoucher(code)
		{
			$("#concluirButton").removeClass("disable_a_href");
			$('#errorMesssage').html('');
			$.ajax({
				type : "POST",
				url : "validvoucher",
				data : {"code": code},
				cache : false,
				success : function(response) 
				{
					if (!response)
						$('#errorMesssage').html('Código de vaucher inexistente');
					$("#concluirButton").addClass("disable_a_href"); 
				}
			});
		}
	
	</script>
	
</html>