<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="box box-primary">
	<div class="box-header">
		<h3 class="box-title"><i class="glyphicon glyphicon-shopping-cart"></i> 
			(${fn:length(lista)}) <small>Produtos</small>
			<span id="valorTotal" style="font-weight: BOLD;"></span> <small>Total</small>
		</h3>
		<div id="linkHover" class="text-center" style="margin-top: -20px; cursor: pointer;" onclick="hiddenAndHover();">
			<i class="glyphicon glyphicon-chevron-up"></i>
		</div>
		
		<div class="pull-right" style="margin-top: -26px !important;">
			<a onclick="sendCompra('${venda}')" class="btn btn-success">
				<i class="glyphicon glyphicon-shopping-cart"></i>  <span><b>CONCLUIR COMPRA</b></span>
			</a>
		</div>
	</div>
	
	<div class="box-body">
		
		<table class="table table-bordered table-striped">
			<tr>
				<td width="20px"></td>
				<td>Nome</td>
				<td width="32px"></td>
				<td width="32px"></td>
				<td width="80px">Quantidade</td>
				<td width="80px">Preço unitário</td>
				<td width="80px">Total</td>
				<td width="32px"></td>
			</tr>
			<c:forEach var="item" items="${lista}" varStatus="row">
				<tr>
					<td><small>${row.count}</small></td>
					<td>${item.nome}</td>
					<td>
						<a href="#" onclick="removeQuantidade('${item.id}');" class="btn btn-sm btn-default">
							<i class="glyphicon glyphicon-minus"></i>
						</a>
					</td>
					<td>
						<a href="#" onclick="adicionaQuantidade('${item.id}');" class="btn btn-sm btn-default">
							<i class="glyphicon glyphicon-plus"></i>
						</a>
					</td>
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
		      				
		      				var total2 = Number('${item.total}') + Number($('#valorTotal').html());
		      				$('#valorTotal').html(Number(total2).toFixed(2));
		      				
		      			</script>
		      		</td>
					
					<td>
						<a href="#" onclick="removeItem('${item.id}');" class="btn btn-sm btn-danger">
							<i class="glyphicon glyphicon-trash"></i>
						</a>
						
					</td>
				</tr>
			</c:forEach>	
		</table>
		
		<br>
<!-- 		<div class="text-center"> -->
<%-- 			<a href="checkoutcompracarrinho?venda=${venda}" class="btn btn-success"> --%>
<!-- 				<i class="glyphicon glyphicon-shopping-cart"></i>  <span><b>CONCLUIR COMPRA</b></span> -->
<!-- 			</a> -->
<!-- 		</div> -->
		
	</div>
</div>

<script>
	
	function sendCompra(venda)
	{
		window.location = "checkoutcompracarrinho?venda="+venda+"";
	}

	function hiddenAndHover() {
		
		if($("#cartItens").hasClass('shopping-cart-bottom')) {
			$("#cartItens").removeClass('shopping-cart-bottom');
			$("#cartItens").addClass('shopping-cart-bottom-small');
			$("#linkHover").html('<i class="glyphicon glyphicon-chevron-up"></i>');
		}
		
		else {
			$("#cartItens").removeClass('shopping-cart-bottom-small');
			$("#cartItens").addClass('shopping-cart-bottom');
			$("#linkHover").html('<i class="glyphicon glyphicon-chevron-down"></i>');
		}
	}
	
	function adicionaQuantidade(id) {
		$.post('cartaddquantidade', {id: id}, function(resposta) {
			carregaItensDoCarrinho();
			$("#divReload").html(resposta);
		});
	}
	
	function removeQuantidade(id) {
		$.post('cartremovequantidade', {id: id}, function(resposta) {
			$("#divReload").html(resposta);
			carregaItensDoCarrinho();
		});
	}
	
	function removeItem(id) {
		$.post('cartremoveitem', {id: id}, function(resposta) {
			$("#divReload").html(resposta);
			carregaItensDoCarrinho();
		});
	}

	$('#valorTotal').html('R$ ' + $('#valorTotal').html().replace('.', ','));
	
	$(document).ready(function() {
		$(document).bind("contextmenu", function(e) {
			return false;
		});
		
		var quantidade = Number('${fn:length(lista)}');
		if (quantidade > 0) {
			
			if($("#cartItens").hasClass('hidden')) {
				$("#cartItens").removeClass('hidden');
				$("#cartItens").removeClass('shopping-cart-bottom');
				$("#cartItens").addClass('shopping-cart-bottom-small');
				$("#linkHover").html('<i class="glyphicon glyphicon-chevron-up"></i>');
			}
		}

		else {
			if(!$("#cartItens").hasClass('hidden')) { 
				$("#cartItens").addClass('hidden');
			}
		}
	});
</script>

<style>
	#cartItens {
		max-height: 400px;
		overflow: auto;
	}
</style>