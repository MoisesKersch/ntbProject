<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="adapta"%>

<jsp:include page="meusdadosmenu.jsp" />

	<br>
	
	<jsp:include page="message.jsp" />
	
		<form:form id="formTrocaSenha" name="formTrocaSenha" class="form-horizontal" action="senhafinanceira" onsubmit="return false;" method="post" commandName="chaveiro" role="form">
	  		
	  		<form:hidden path="senhaAtual"/>
	  		<form:hidden path="temSenha"/>
	  		
	  		<c:if test="${chaveiro.temSenha}">
		  		<div class="form-group">
		    		<label for="senhaAtualFinanceira" class="col-sm-2 control-label">Senha Atual</label>
		    		<div class="col-sm-10">
		    			<adapta:senhafinanceira path="senhaAtualFinanceira"/>
		    			<!--<form:password class="form-control" path="senhaAtualFinanceira" id="senhaAtualFinanceira" maxlength="60"/>-->
		    			<form:errors path="senhaAtualFinanceira" cssClass="tooltip-erro"/>
		    		</div>
		  		</div>
	  		</c:if>
	  		
	  		<div class="form-group">
	    		<label for="senhaNova" class="col-sm-2 control-label">Nova Senha</label>
	    		<div class="col-sm-10">
	    			<adapta:senhafinanceira path="senhaNova"/>
	    			<!--<form:password class="form-control" path="senhaNova" id="senhaNova" maxlength="60"/>-->
	    			<form:errors path="senhaNova" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>

	  		<div class="form-group">
	    		<label for="senhaNova2" class="col-sm-2 control-label">Confirmação de Nova Senha</label>
	    		<div class="col-sm-10">
	    			<adapta:senhafinanceira path="senhaNova2"/>
	    			<!--<form:password class="form-control" path="senhaNova2" id="senhaNova2" maxlength="60"/>-->
	    			<form:errors path="senhaNova2" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a href="#" class="btn btn-primary" onclick="post('senhafinanceira', $('#formTrocaSenha').serialize());">
						Salvar
					</a>
				</div>
			</div>
		</form:form>
		
		<span id="helpBlock" class="help-block col-sm-offset-2">	
			Clique 
			<a href="envianovasenhafinanceira">
			 AQUI
			</a>
			para receber uma nova senha em seu e-mail.	
		</span>

<c:if test="${chaveiro.temSenha}">
	<script>
		senhaAtualFinanceira.focus();		
	</script>
</c:if>
<c:if test="${!chaveiro.temSenha}">
	<script>
		senhaNova.focus();		
	</script>
</c:if>