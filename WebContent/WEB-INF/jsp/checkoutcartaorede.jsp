<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt">
	<body>		
	
		<iframe id="vpos" name="vpos" width="800" height="550"></iframe>
		
		<form id="form_card" name="form_card" method="POST" target="vpos" action="https://ecommerce.userede.com.br/pos_virtual/form_card.asp">
			<input type="hidden" name="TOTAL" value="${form.total}">
			<!-- <input type="hidden" name="TOTAL" value="10.00"> -->
			<input type="hidden" name="TRANSACAO" value="${form.transacao}">
			<input type="hidden" name="PARCELAS" value="${form.parcelas}">
			<input type="hidden" name="FILIACAO" value="${form.filiacao}">
			<input type="hidden" name="DISTRIBUIDOR" value="${form.distribuidor}"> 
			<input type="hidden" name="BANDEIRA" value="${form.bandeira}">
			<input type="hidden" name="NUMPEDIDO" value="${form.numPedido}"> 
			<input type="hidden" name="PAX1" value="${form.pax1}">
			<input type="hidden" name="CODVER" value="${form.codVer}"> 
			<input type="hidden" name="URLBACK" value="${form.urlBack}"> 
			<!-- <input type="hidden" name="URLCIMA" value="${form.urlCima}">-->
			<input type="hidden" name="TARGET" value="${form.target}">
		</form>
	</body>

	<script>
		$("#form_card").submit();
	</script>

</html>