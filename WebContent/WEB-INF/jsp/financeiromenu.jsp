<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<ul class="nav nav-tabs nav-bar-submenu">
		<li class="<c:if test="${submenu == 'saldoresumido'}">active</c:if>"><a href="#" onclick="getNLoad('saldoresumido');"> Saldos </a> 
		</li>
		<li class="<c:if test="${submenu == 'extratoperiodo'}">active</c:if>"><a href="#" onclick="getNLoad('extratoperiodo');">Extrato</a></li>
		<li class="<c:if test="${submenu == 'pagamentospendentes'}">active</c:if>"> <a id="pagamentosPendentes1" href="#" onclick="getNLoad('pagamentospendentes');"> Pagamentos pendentes </a>
		</li>
		
		<li id="menuSaque" class="hidden <c:if test="${submenu == 'saque'}">active</c:if>"><a href="#" onclick="getNLoad('saque');">Solicitar saque</a></li>
		
		<li class="<c:if test="${submenu == 'transferencia'}">active</c:if>"><a href="#" onclick="getNLoad('transferencia');">Tranferência de saldo</a></li>
		
		<li class="<c:if test="${submenu == 'voucherprodutos'}">active</c:if>"><a href="#" onclick="getNLoad('voucherprodutos');">Comprar voucher</a></li>
		<li class="<c:if test="${submenu == 'vouchers'}">active</c:if>"><a href="#" onclick="getNLoad('vouchers');">Vouchers</a></li>
<%-- 		<li class="<c:if test="${submenu == 'solicitacoessaque'}">active</c:if>"><a href="#" onclick="get('saque');">Solicitações de saque</a></li> --%>
	</ul>
	
	<div class="row">
	  <div id="loading" class="col-md-12 text-center hidden "><i class="fa fa-spinner fa-spin fa-3x fa-fw"></i><div>Carregando</div></div>
	</div>
	
	
</div>

<script>
	$(document).ready( function() {
		$.get("exibemenusaque", function(resposta) {
					if(resposta == null || resposta == "") {
						$("#menuSaque").addClass("hidden");
					} 
					else {
						$("#menuSaque").removeClass("hidden");
					}
		   		}
		   	);
	});
</script>