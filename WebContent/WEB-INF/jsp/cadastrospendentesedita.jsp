<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	$(function() {
		$(".data").mask("99/99/9999");
		$(".cep").mask("99999-999");
		$(".cpf").mask("999.999.999-99");
    });
</script>

<br>
	
	<jsp:include page="message.jsp" />
	
		<form:form id="formNovoCadastro" name="formNovoCadastro" class="form-horizontal" action="cadastrospendentesedita" method="post" commandName="cadastro" role="form">
			
			<form:hidden path="cBPartnerId"/>
			<form:hidden path="adUserId"/>
			<form:hidden path="cBPartnerLocationId"/>
			<form:hidden path="cLocationId"/>
			<form:hidden path="liberaCrescimento"/>
			<form:hidden path="senha"/>
		
	  		<div class="form-group">
	    		<label for="nome" class="col-sm-2 control-label">Nome:</label>
	    		<div class="col-sm-10">
					<form:input class="form-control width400" path="nome" id="nome" maxlength="60"/>
	    			<form:errors path="nome" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="cpfCnpj" class="col-sm-2 control-label">CPF:</label>
	    		<div class="col-sm-10">
					<form:input class="form-control width200 cpf" path="cpfCnpj" id="cpfCnpj" maxlength="14"/>
	    			<form:errors path="cpfCnpj" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="dataNascimento" class="col-sm-2 control-label">Data de nascimento:</label>
	    		<div class="col-sm-10">
					<form:input class="form-control width100 data" path="dataNascimento" id="dataNascimento"/>
	    			<form:errors path="dataNascimento" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	
	  		<div class="form-group">
	    		<label for="genero" class="col-sm-2 control-label">Gênero</label>
	    		<div class="col-sm-10">
					<form:radiobutton path="genero" value="M"/><span class="radio-label">Masculino</span>
					<form:radiobutton path="genero" value="F"/><span class="radio-label">Feminino</span>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="email" class="col-sm-2 control-label">E-Mail</label>
	    		<div class="col-sm-10">
					<form:input class="form-control width400" path="email" id="email" maxlength="80"/>
	    			<form:errors path="email" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<c:if test="${cadastro.liberaCrescimento}">
		  		<div class="form-group">
		   			<label for="crescimento" class="col-sm-2 control-label">Crescimento:</label>
			   		<div class="col-sm-10">
		   				<form:radiobutton path="crescimento" value="E"/><span class="radio-label">Esquerda</span>
						<form:radiobutton path="crescimento" value="M"/><span class="radio-label">Menor</span>
						<form:radiobutton path="crescimento" value="D"/><span class="radio-label">Direita</span>
			   			<form:errors path="crescimento" cssClass="tooltip-erro"/>
			   		</div>
		 		</div>
		 	</c:if>
	 		
	 		<c:if test="${!cadastro.liberaCrescimento}">
	 			<form:hidden path="crescimento"/>
	 		</c:if>
			
			<div class="form-group">
	    		<label for="usuario" class="col-sm-2 control-label">Usuário</label>
	    		<div class="col-sm-10">
					<form:input class="form-control width400" path="usuario" name="usuarioPendente" id="usuarioPendente" maxlength="80" autocomplete="off"/>
	    			<form:errors path="usuario" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  							
			<br>
	  		<div class="form-group">
	  			<p class="login-box-msg"><b>Telefones</b></p>
	  		</div>
			
			<div class="form-group">
	    		<label for="tipoTelefone1" class="col-sm-1 control-label">Tipo:</label>
	    		<div class="col-sm-2">
					<form:select class="form-control width150" path="tipoTelefone1" id="tipoTelefone1">
					   <form:option value="Celular" label="Celular"/>   
					</form:select>
	    			<form:errors path="tipoTelefone1" cssClass="tooltip-erro"/>
	    		</div>
	    		<label for="dddTelefone1" class="col-sm-1 control-label">Número:</label>
	    		<div class="col-sm-1">
					<form:input class="form-control width30" path="dddTelefone1" id="dddTelefone1" maxlength="2" onkeypress="return inteiro(event);"/>
					<form:errors path="dddTelefone1" cssClass="tooltip-erro"/>
	    		</div>
	    		<div class="col-sm-2">
					<form:input class="form-control width100" path="numeroTelefone1" id="numeroTelefone1" maxlength="9" onkeypress="return inteiro(event);"/>
	    			<form:errors path="numeroTelefone1" cssClass="tooltip-erro"/>
	    		</div>
	    		<label for="operadoraTelefone1" class="col-sm-2 control-label">Operadora:</label>
	    		<div class="col-sm-1">
					<form:select class="form-control width100" path="operadoraTelefone1" id="operadoraTelefone1">
					   <form:option value="CLARO" label="CLARO"/>
					   <form:option value="OI" label="OI"/>
					   <form:option value="TIM" label="TIM"/>
					   <form:option value="VIVO" label="VIVO"/>				   
					</form:select>
	    			<form:errors path="operadoraTelefone1" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<br>
	  		<div class="form-group">
	  			<p class="login-box-msg"><b>Endereço</b></p>
	  		</div>
			
			<div class="form-group">
	    		<label for="cep" class="col-sm-2 control-label">CEP:</label>
	    		<div class="col-sm-10">
					<form:input class="form-control width100 cep" path="cep" id="cep"/>
	    			<form:errors path="cep" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="rua" class="col-sm-2 control-label">Rua:</label>
	    		<div class="col-sm-10">
					<form:input class="form-control width400" path="rua" id="rua" maxlength="80"/>
	    			<form:errors path="rua" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="numero" class="col-sm-2 control-label">Número:</label>
	    		<div class="col-sm-10">
					<form:input class="form-control width100" path="numero" id="numero" maxlength="8" onkeypress="return inteiro(event);"/>
	    			<form:errors path="numero" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="complemento" class="col-sm-2 control-label">Complemento:</label>
	    		<div class="col-sm-10">
					<form:input class="form-control width400" path="complemento" id="complemento" maxlength="80"/>
	    			<form:errors path="complemento" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="bairro" class="col-sm-2 control-label">Bairro:</label>
	    		<div class="col-sm-10">
					<form:input class="form-control width400" path="bairro" id="bairro" maxlength="80"/>
	    			<form:errors path="bairro" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="pais" class="col-sm-2 control-label">País:</label>
	    		<div class="col-sm-10">
					<form:select class="form-control width400" path="pais" id="pais">
					</form:select>
	    			<form:errors path="pais" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="estado" class="col-sm-2 control-label">Estado:</label>
	    		<div class="col-sm-10">
	    			<form:select class="form-control width400" path="estado" id="estado">
	    			</form:select>
	    			<form:errors path="estado" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="cidade" class="col-sm-2 control-label">Cidade:</label>
	    		<div class="col-sm-10">
					<form:select class="form-control width400" path="cidade" id="cidade">
	    			</form:select>
	    			<form:errors path="cidade" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		
	  		<div class="form-group">
	    		<label for="referencia" class="col-sm-2 control-label">Referência:</label>
	    		<div class="col-sm-10">
					<form:input class="form-control width400" path="referencia" id="referencia" maxlength="80"/>
	    			<form:errors path="referencia" cssClass="tooltip-erro"/>
	    		</div>
	  		</div>
	  		  		
<!-- 	  		<div class="form-group"> -->
<!-- 	    		<div class="col-sm-2"></div> -->
<!-- 	    		<div class="col-sm-10"> -->
<%-- 	 				<form:checkbox path="residencial" label=" Endereço residêncial"/> --%>
<!-- 	    		</div> -->
<!-- 	  		</div> -->
	  		
<!-- 	  		<div class="form-group"> -->
<!-- 	  			<div class="col-sm-2"></div> -->
<!-- 	    		<div class="col-sm-10"> -->
<%-- 					<form:checkbox path="cobranca" label=" Endereço de cobrança" /> --%>
<!-- 	    		</div> -->
<!-- 	  		</div> -->
	  		
<!-- 	  		<div class="form-group"> -->
<!-- 	    		<div class="col-sm-2"></div> -->
<!-- 	    		<div class="col-sm-10"> -->
<%-- 					<form:checkbox path="correspondencia" label=" Endereço para correspondência"/> --%>
<!-- 	    		</div> -->
<!-- 	  		</div> -->
	  		
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-5  text-center">
					<a href="#" class="btn btn-primary" onclick="post('cadastrospendentesedita', $('#formNovoCadastro').serialize());">
						Salvar
					</a>
				</div>
				
				<div class="col-sm-5 text-center">
					<a href="cadastrospendentes" class="btn btn-danger">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						Cancelar
					</a>
				</div>
			</div>
			
			<!-- 
			<div class="form-group">
				<label for="senhaFinanceira" class="col-sm-2 control-label">Senha financeira:</label>
	    		<div class="col-sm-5">
	    			<form:password class="form-control width400" path="senhaFinanceira" name="senhaFinanceira" id="senhaFinanceira"  maxlength="60" autocomplete="off"/>
	    			<form:errors path="senhaFinanceira" cssClass="tooltip-erro"/>
	    		</div>
	    		
				<div class="col-sm-3">
					<a href="#" class="btn btn-success" onclick="post('cadastrospendentespaga', $('#formNovoCadastro').serialize());">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
						<span class="glyphicon glyphicon glyphicon-usd" aria-hidden="true"></span>
						Salvar e Pagar
					</a>
				</div>
			</div>
			 -->

		</form:form>

<c:url var="listaPaisesURL" value="/lista_paises" />
<c:url var="listaEstadosURL" value="/lista_estados" />
<c:url var="listaCidadesURL" value="/lista_cidades" />
<script>

	$(document).ready(
		function() {
			$.getJSON('${listaPaisesURL}', {
				ajax : 'true'
			}, 

			function(data) {
				var html = '';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].id + '">'
							+ data[i].nome + '</option>';
				}
				html += '</option>';
				$('#pais').html(html);
				$('#pais').val('${cadastro.pais}');				
			});
	});

	$(document).ready( 
		function() {
			$.getJSON("${listaEstadosURL}", { 
				pais : ${cadastro.pais}, 
				ajax : 'true'
			},

			function(data) {
				var items = "";
			    $.each(data, function(key) {
			        items += '<option value="' + data[key].id + '">' + data[key].nome + '</option>';  
			    });  
			    $("#estado").html(items);  
			    $('#estado').val('${cadastro.estado}');
	    	});
	});

	$(document).ready(
		function() {
			$.getJSON("${listaCidadesURL}", { 
				estado : ${cadastro.estado}, 
				ajax : 'true',
				completo : 'N'
			},

			function(data) {
				var items = "";
			    $.each(data, function(key) {
			        items += '<option value="' + data[key].id + '">' + data[key].nome + '</option>';  
			    });  
			    $("#cidade").html(items);  
			    $('#cidade').val('${cadastro.cidade}');
	    	});
	});

	$(document).ready(function() { 
		$('#pais').change(
			function() {
				$.getJSON("${listaEstadosURL}", { 
					pais : $('#pais').val(), 
					ajax : 'true'
				},

				function(data) {
					var items = "";
				    $.each(data, function(key) {
				        items += '<option value="' + data[key].id + '">' + data[key].nome + '</option>';  
				    });  
				    $("#estado").html(items);  
				    $('#estado').val('${cadastro.estado}');
		    	});
			});
	});

	$(document).ready(function() { 
		$('#cep').change(
			function() {
				$.getJSON("${listaCidadesURL}", {
					ajax : 'true',
					estado : $('#cep').val(),
					completo : 'Y'
				},

				function(data) {
					$('#estado').val(data[0].uf);
					$('#rua').val(data[0].logradouro);
					$('#bairro').val(data[0].bairro);
					var cidade = data[0].cidade;
					
					$.getJSON("${listaCidadesURL}", { 
							estado : $('#estado').val(), 
							ajax : 'true',
							completo : 'N'
						},
	
						function(data) {
							var items = "";
						    $.each(data, function(key) {
						        items += '<option value="' + data[key].id + '">' + data[key].nome + '</option>';  
						    });  
						    $("#cidade").html(items);
						    $('#cidade').val(cidade);  
				    	}
				    );	
							
		    	});
			});
	});

	$(document).ready(function() { 
		$('#estado').change(
			function() {
				$.getJSON("${listaCidadesURL}", { 
					estado : $('#estado').val(), 
					ajax : 'true',
					completo : 'N'
				},

				function(data) {
					var items = "";
				    $.each(data, function(key) {
				        items += '<option value="' + data[key].id + '">' + data[key].nome + '</option>';  
				    });  
				    $("#cidade").html(items);  
				    $('#cidade').val('${cadastro.cidade}');
		    	});
			});
	});

	nome.focus();	
</script>