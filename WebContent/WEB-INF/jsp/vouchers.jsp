<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="financeiromenu.jsp" />
<br>
<jsp:include page="message.jsp" />
<div id="vouchers">
	<table class="table table-bordered table-striped">
		<tr>
			<th width="100px">Código</th>
			<th width="120px">Valor</th>
			<th>Nome</th>
		</tr>
		<c:forEach var="item" items="${lista}">
			<tr>
				<td align="center"><b>${item.codigo}</b></td>
				<td align="right"><fmt:formatNumber value="${item.valor}" type="currency"/></td>
				<td>${item.nome}</td>
			</tr>
		</c:forEach>
	</table>
</div>