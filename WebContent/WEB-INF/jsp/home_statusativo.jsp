<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-sm-12 col-md-12 ">

	<c:if test="${ativostatus.status == 'P'}">
	    <div class="small-box bg-green-active">
			<a href="#" class="small-box-footer "><strong>Bônus de incentivo</strong></a>
			<div class="inner">
    			<h1><a style="color:WHITE;" href="ativos">${ativostatus.titulo}</a></h1>
	    	</div>
	    	<div class="icon">
    			<i class="glyphicon glyphicon-ok"></i>
	    	</div>
	
		</div>
	</c:if>
	
	<c:if test="${ativostatus.status == 'V'}">
	    <div class="small-box bg-yellow">
			<a href="#" class="small-box-footer"><strong>Bônus de incentivo</strong></a>
			
			<div class="inner">
    			<h1><a style="color:WHITE;" href="ativos">${ativostatus.titulo}</a></h1>
    			<h4><a style="color:WHITE;" href="ativos">${ativostatus.descricao}</a></h4>
	    	</div>
	    	
	    	<div class="icon">
    			<i class="glyphicon glyphicon-exclamation-sign"></i>
	    	</div>
	
		</div>
	</c:if>
	
	<c:if test="${ativostatus.status == 'I'}">
	    <div class="small-box bg-gray">
			<a href="#" class="small-box-footer"><strong>Bônus de incentivo</strong></a>
			
			<div class="inner">
    			<h1><a style="color:WHITE;" href="ativos">${ativostatus.titulo}</a></h1>
    			<h4><a style="color:WHITE;" href="ativos">${ativostatus.descricao}</a></h4>
	    	</div>
	    	
	    	<div class="icon">
    			<i class="glyphicon glyphicon-remove"></i>
	    	</div>
	
		</div>
	</c:if>
	
<!--     <div id="bg-home-ativo" class="small-box"> -->
<!-- 		<a href="#" class="small-box-footer headerColor">Graduação</a> -->
<!-- 		<div id="graduacaoDiv" class="text-center"> -->
<%--     		<img src="${graduacaoIcone}" width="100%"> --%>
<!--     	</div> -->
<!-- 	</div> -->
	
</div>