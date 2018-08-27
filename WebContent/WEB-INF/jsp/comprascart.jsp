<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="cartItens" class="shopping-cart-bottom-small hidden"></div>
<div id="divReload"></div>

<c:url var="listaItensURL" value="/cartlista" />
<script>
	function adicionaProdutoAoCarinho(id, venda) {
		$.post('cartadd', {id: id, venda:venda}, function(resposta) {
			$("#divReload").html(resposta);
			carregaItensDoCarrinho();
		});
	}

	function carregaItensDoCarrinho() {
		$.get('cartlista', {venda: '${venda}'}, function(resposta) {
			$("#cartItens").html(resposta);
		});	
	}
	
	carregaItensDoCarrinho();
</script>