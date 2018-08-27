<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>	
     
<jsp:include page="financeiromenu.jsp" />
					
<br>
<div id="saldoresumido" class="table-responsive">

	<jsp:include page="message.jsp" />

	<table class="table table-bordered table-striped">
	
		<c:forEach var="item" items="${lista}">
			<tr>
				<td>${item.descricao}</td>
				<td width="150px">${item.valor}</td>
			</tr>
		</c:forEach>
			
	</table>
</div>