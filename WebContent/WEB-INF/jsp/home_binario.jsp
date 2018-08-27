<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${perfil != null && perfil != '' && perfil != 'PENDENTE'}">
	<div class="col-sm-6 col-md-6">
	    <div id="bg-home-ativo" class="small-box ">
			<a href="#" class="small-box-footer headerColor"><strong>Qualificação</strong></a>
	    		<img src="${iconedesktop}"  width="100%">
		</div>
	</div>
</c:if>

