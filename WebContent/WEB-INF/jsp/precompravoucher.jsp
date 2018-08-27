<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="financeiromenu.jsp" />
<br>
<jsp:include page="message.jsp" />
		
<img src="${produto.imageUrl}" alt="" class="" height="200px">
<h3><b>${produto.nome}</b></h3>
<span class="label-price">
	<fmt:formatNumber value="${produto.valor}" type="currency"/>
</span>
<br>

<div class="line-separator"></div>
<c:if test="${autenticacompra == 'Y'}">
	<p><input type="password" class="form-control" id="senha${produto.id}" maxlength="60" autocomplete="off" placeholder="Senha financeira"/></p>
	<script>
		$("#senha${produto.id}").focus();
	</script>
</c:if>							
<a href="#" onclick="compra('${produto.id}');" class="btn btn-primary">
	<i class="fa fa-usd"></i>  <span><b>Confirmar compra</b></span>
</a>
<a href="#" onclick="get('voucherprodutos');" class="btn btn-danger">
	<i class="fa fa-remove"></i>  <span><b>Cancelar</b></span>
</a>				
						
<script>
	function compra(id) {
		var autentica = '${autenticacompra}';
		if(autentica == 'Y') {
			var senha = $('#senha' + id).val();
			get('compravoucher?id=' + id + '&senhaFinanceira=' + senha);
		}
		else
			get('compravoucher?id=' + id);
	}
</script>
