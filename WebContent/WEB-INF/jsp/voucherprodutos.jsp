<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="financeiromenu.jsp" />
<br>
<jsp:include page="message.jsp" />
<div id="voucherprodutos">
	<c:forEach var="item" items="${lista}">
		<div class="price-box">
			<div class="text-center">
				<img src="${item.imageUrl}" alt="" class="" height="200px">
			
			<h3><b>${item.nome}</b></h3>
			<span class="label-price">
				<fmt:formatNumber value="${item.valor}" type="currency"/>
		    </span>
		    <p><br>
			<a href="#" onclick="get('precompravoucher?id=${item.id}');" class="btn btn-success">
				<i class="fa fa-usd"></i>  <span><b>COMPRAR</b></span>
			</a>
			</div>
		</div>
	</c:forEach>
</div>