<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="adapta"%>

<jsp:include page="meusdadosmenu.jsp" />

<br/>
	<jsp:include page="message.jsp" />
	
		<form:form id="formTrocaSenha" name="formTrocaSenha" class="form-horizontal" action="trocasenha" onsubmit="return false;" method="post" commandName="chaveiro" role="form">
	  		  		
	  		<div class="form-group">
	    		<label for="senhaAtual" class="col-sm-3 control-label">Senha Atual</label>
	    		<div class="col-sm-9">
	    			<adapta:senhafinanceira path="senhaAtual"/>
	    			<!--<form:password class="form-control width400" path="senhaAtual" id="senhaAtual" maxlength="60" autocomplete="off"/>-->
	    			<form:errors path="senhaAtual" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="senhaNova" class="col-sm-3 control-label">Nova Senha</label>
	    		<div class="col-sm-9">
	    			<adapta:senhafinanceira path="senhaNova"/>
	    			<!-- <form:password class="form-control width400" path="senhaNova" id="senhaNova" maxlength="60" autocomplete="off"/> -->
	    			<form:errors path="senhaNova" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>

	  		<div class="form-group">
	    		<label for="senhaNova2" class="col-sm-3 control-label">Confirmação de Nova Senha</label>
	    		<div class="col-sm-9">
	    			<adapta:senhafinanceira path="senhaNova2"/>
	    			<!-- <form:password class="form-control width400" path="senhaNova2" id="senhaNova2" maxlength="60" autocomplete="off"/> -->
	    			<form:errors path="senhaNova2" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>

			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-9">
					<a href="#" class="btn btn-primary" onclick="post('trocasenha', $('#formTrocaSenha').serialize());">
						Atualizar senha
					</a>
				</div>
			</div>
		</form:form>

<script>
	senhaAtual.focus();	
</script>