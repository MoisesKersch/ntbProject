<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="meusdadosmenu.jsp" />
<jsp:include page="message.jsp" />

<br>

<form:form id="formNovoCadastro" name="formNovoCadastro" class="form-horizontal" action="meucadastro" method="post" commandName="cadastro" role="form">

	<form:hidden path="cBPartnerId"/>
	<form:hidden path="adUserId"/>
	<form:hidden path="cBPartnerLocationId"/>
	<form:hidden path="cLocationId"/>
	<form:hidden path="usuario"/>
	<form:hidden path="senha"/>
	<form:hidden path="email"/>
	<form:hidden path="patrocinador"/>
	<form:hidden path="cpfCnpj"/>
	<form:hidden path="crescimento"/>
	<form:hidden path="nome"/>

	<div class="form-group">
   		<label for="nome" class="col-sm-2 control-label">ID:</label>
   		<div class="col-sm-10">
			<input type="text" class="form-control " value="${meuID}" readonly/>
   		</div>
 	</div>

	<div class="form-group">
   		<label for="nome" class="col-sm-2 control-label">Patrocinador:</label>
   		<div class="col-sm-10">
			<input type="text" class="form-control " value="${cadastro.patrocinador}" readonly/>
   		</div>
 	</div>

	<div class="form-group">
   		<label for="nome" class="col-sm-2 control-label">Nome:</label>
   		<div class="col-sm-10">
   			<input type="text" class="form-control " value="${cadastro.nome}" readonly/>
   		</div>
 	</div>
 		
 		<div class="form-group">
   		<label for="cpfCnpj" class="col-sm-2 control-label">CPF:</label>
	   		<div class="col-sm-10">
				<input type="text" class="form-control " value="${cadastro.cpfCnpj}" readonly/>
	   		</div>
 		</div>
 		
		<script>
			$(function() {
				$(".nit").mask("999.99999.99-9");
            });
		</script>

		<div class="form-group">
			<label for="nit" class="col-sm-2 control-label"> <span class="glyphicon glyphicon-info-sign pop" data-container="body" data-toggle="popover" data-placement="left" data-content="Clique aqui para gerar um ID indicador
	   		O NIT é o Número de Identificação do Trabalhador para quem deseja fazer os recolhimentos das contribuições ao INSS, quem deve fazer o cadastro do NIT são os Segurados Contribuinte Individual, Empregado Doméstico, Segurado Especial e Facultativo, O NIT é equivalente ao PIS/PASEP, a diferença é que ele é para o Contribuinte Individual. Caso o contribuinte já tenha o PIS/PASEP poderá usar este numero para fazer o recolhimento ao INSS" data-original-title="" title=""> </span> &nbsp; 
	   		NIT <form:errors path="nit" cssClass="tooltip-erro"/>
			</label>
			<div class="col-sm-10">
				<form:input class="form-control width300 nit" path="nit" id="nit" />
			</div>
		</div>
		
		<div class="form-group">
   			<label for="dataNascimento" class="col-sm-2 control-label">Data de nascimento:</label>
	   		<div class="col-sm-10">
				<form:input class="form-control width100 data required" path="dataNascimento" id="dataNascimento"/>
	   			<form:errors path="dataNascimento" cssClass="tooltip-erro"/>
	   		</div>
 		</div>

 		<div class="form-group">
   			<label for="genero" class="col-sm-2 control-label">Gênero:</label>
	   		<div class="col-sm-10">
				<form:radiobutton path="genero" value="M"/><span class="radio-label">Masculino</span>
				<form:radiobutton path="genero" value="F"/><span class="radio-label">Feminino</span>
	   		</div>
 		</div>
 		
 		<div class="form-group">
   		<label for="email" class="col-sm-2 control-label">E-Mail:</label>
   		<div class="col-sm-10">
   			<input type="text" class="form-control " value="${cadastro.email}" readonly/>
   		</div>
 	</div>
 					
	<br>
	<div class="form-group">
		<p class="login-box-msg"><b>Telefones</b></p>
	</div>
	
	<div class="form-group">
   		<label for="tipoTelefone1" class="col-sm-1 control-label">Tipo:</label>
   		<div class="col-sm-2">
			<form:select class="form-control width150 required" path="tipoTelefone1" id="tipoTelefone1">
			   <form:option value="Celular" label="Celular"/>   
			</form:select>
   			<form:errors path="tipoTelefone1" cssClass="tooltip-erro"/>
   		</div>
   		<label for="dddTelefone1" class="col-sm-1 control-label">Número:</label>
   		<div class="col-sm-1">
			<form:input class="form-control width30 required" path="dddTelefone1" id="dddTelefone1" maxlength="2" onkeypress="return inteiro(event);"/>
			<form:errors path="dddTelefone1" cssClass="tooltip-erro"/>
   		</div>
   		<div class="col-sm-2">
			<form:input class="form-control width100 required" path="numeroTelefone1" id="numeroTelefone1" maxlength="9" onkeypress="return inteiro(event);"/>
   			<form:errors path="numeroTelefone1" cssClass="tooltip-erro"/>
   		</div>
 	</div>
 		
	<br>
	<div class="form-group">
		<p class="login-box-msg"><b>Endereço</b></p>
	</div>
	
	<div class="form-group">
   		<label for="cep" class="col-sm-2 control-label">CEP:</label>
   		<div class="col-sm-10">
			<form:input class="form-control width100 cep required" path="cep" id="cep"/>
   			<form:errors path="cep" cssClass="tooltip-erro"/>
   		</div>
 		</div>
 		
 		<div class="form-group">
   		<label for="rua" class="col-sm-2 control-label">Rua:</label>
   		<div class="col-sm-10">
			<form:input class="form-control  required" path="rua" id="rua" maxlength="80"/>
   			<form:errors path="rua" cssClass="tooltip-erro"/>
   		</div>
 		</div>
 		
 		<div class="form-group">
   		<label for="numero" class="col-sm-2 control-label">Número:</label>
   		<div class="col-sm-10">
			<form:input class="form-control width100 required" path="numero" id="numero" maxlength="8" onkeypress="return inteiro(event);"/>
   			<form:errors path="numero" cssClass="tooltip-erro"/>
   		</div>
 		</div>
 		
 		<div class="form-group">
   		<label for="complemento" class="col-sm-2 control-label">Complemento:</label>
   		<div class="col-sm-10">
			<form:input class="form-control " path="complemento" id="complemento" maxlength="80"/>
   			<form:errors path="complemento" cssClass="tooltip-erro"/>
   		</div>
 		</div>
 		
 		<div class="form-group">
   		<label for="bairro" class="col-sm-2 control-label">Bairro:</label>
   		<div class="col-sm-10">
			<form:input class="form-control  required" path="bairro" id="bairro" maxlength="80"/>
   			<form:errors path="bairro" cssClass="tooltip-erro"/>
   		</div>
 		</div>
 		
 		<div class="form-group">
   		<label for="pais" class="col-sm-2 control-label">País:</label>
   		<div class="col-sm-10">
			<form:select class="form-control  required" path="pais" id="pais">
			</form:select>
   			<form:errors path="pais" cssClass="tooltip-erro"/>
   		</div>
 		</div>
 		
 		<div class="form-group">
   		<label for="estado" class="col-sm-2 control-label">Estado:</label>
   		<div class="col-sm-10">
   			<form:select class="form-control  required" path="estado" id="estado">
   			</form:select>
   			<form:errors path="estado" cssClass="tooltip-erro"/>
   		</div>
 		</div>
 		
 		<div class="form-group">
   		<label for="cidade" class="col-sm-2 control-label">Cidade:</label>
   		<div class="col-sm-10">
			<form:select class="form-control  required" path="cidade" id="cidade">
   			</form:select>
   			<form:errors path="cidade" cssClass="tooltip-erro"/>
   		</div>
 		</div>
 		
 		<div class="form-group">
   		<label for="referencia" class="col-sm-2 control-label">Referência:</label>
   		<div class="col-sm-10">
			<form:input class="form-control " path="referencia" id="referencia" maxlength="80"/>
   			<form:errors path="referencia" cssClass="tooltip-erro"/>
   		</div>
 		</div>
		<div class="col-sm-offset-2">
			<a href="#" class="btn btn-primary" onclick="post('meucadastro', $('#formNovoCadastro').serialize());">
				Salvar
			</a>
		</div>
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
			        items += '<option data-uf="' + data[key].uf + '" value="' + data[key].id + '">' + data[key].nome + '</option>';  
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
				        items += '<option data-uf="' + data[key].uf + '" value="' + data[key].id + '">' + data[key].nome + '</option>';  
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
		$('#estado').change(function() {
			estadoChange();
		});
	});

	function estadoChange() {
		$.getJSON("${listaCidadesURL}", { 
			estado : $("#estado").val(), 
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
	}

	$("#nit").focus();	
</script>
