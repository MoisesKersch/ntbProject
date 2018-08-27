<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="nav nav-tabs">
	<c:forEach var="item" items="${abas}">
		<li class="<c:if test="${aba == item.titulo}">active</c:if>">
			<a href="#" onclick="filtra('${item.titulo}');">${item.titulo}</a>
		</li>		
	</c:forEach>
	
</ul>
<br>
<div class="table-responsive">
	<table id="tabela" class="table table-bordered table-hover">
		<tr class="row-odd">
			<th>Histórico</th>
			<th>DC</th>
			<th>Pago</th>
			<th>Valor</th>
			<th>Saldo</th>
		</tr>
		
		<c:forEach var="item" items="${linhas}">
			  				
  			<c:if test="${item.linha == 'D'}">
  				<tr>
					<td>${item.historico}</td>
					<td>${item.debitoCredito}</td>
					<td>${item.pago}</td>
					<td><fmt:formatNumber value="${item.valor}" type="currency"/></td>
					<td></td>
				</tr>
			</c:if>
				
			<c:if test="${item.linha == 'T'}">
				<tr class="row-line-even">
					<td colspan="4"><fmt:formatDate value="${item.vencimento}" type="both" pattern="dd-MM-yyyy" /> - ${item.historico}</td>
					<td><fmt:formatNumber value="${item.valor}" type="currency"/></td>
				</tr>
			</c:if>

		</c:forEach>
		
		<tr class="row-line-even">
			<td colspan="3"><h4>Total no mês</h4></td>
			<td class="text-right"><h4><fmt:formatNumber value="${total}" type="currency"/></h4></td>
			<td class="text-right"><h4><fmt:formatNumber value="${totalfinal}" type="currency"/></h4></td>
		</tr>
		
	</table>
</div>

<script>
	$("#divGifProcessing").hide();
</script>