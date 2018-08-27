<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<ul class="nav nav-tabs">
		<li class="<c:if test="${submenu == 'meucadastro'}">active</c:if>"><a href="#" onclick="get('meucadastro');">Dados pessoais</a></li>
		<li class="<c:if test="${submenu == 'trocasenha'}">active</c:if>"><a href="#" onclick="get('trocasenha');">Senha do sistema</a></li>
		<li class="<c:if test="${submenu == 'senhafinanceira'}">active</c:if>"><a href="#" onclick="get('senhafinanceira');">Senha financeira</a></li>
		<li class="<c:if test="${submenu == 'dadosbancarios'}">active</c:if>"><a href="#" onclick="get('dadosbancarios');">Dados bancários</a></li>
		<li class="<c:if test="${submenu == 'meusdocumentos'}">active</c:if>"><a href="#" onclick="get('meusdocumentos');">Meus documentos</a></li>
	</ul>
</div>