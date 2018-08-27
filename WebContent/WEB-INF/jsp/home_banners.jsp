<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	var imagem = [];
	var indice = -1;
</script>

<div>
	<c:choose>
	    <c:when test="${empty banner.url}">
	    </c:when>    
	    <c:otherwise>
	       <img id="banner" src="${banner.url}"></img>
	    </c:otherwise>
	</c:choose>

	<c:forEach var="banner" items="${banners}" varStatus="row">
		<script>
			imagem.push("${banner.url}");
		</script>
	</c:forEach>										
</div>

<script type="text/javascript">
	function trocaImagem() {
		try {
			indice++;
			if(indice >= imagem.length)
				indice = 0;
			document.getElementById('banner').src = imagem[indice];
			setTimeout("trocaImagem()", 15000);
		} catch (e) {
		}
	}

	trocaImagem();
</script>