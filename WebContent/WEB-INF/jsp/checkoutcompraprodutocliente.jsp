<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>BackOffice</title>

		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
				
		<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
    	<script>
      		$.widget.bridge('uibutton', $.ui.button);
    	</script>
    	<script src="<c:url value="/resources/js/demo.js" />" ></script>
    	
       	<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/script.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery-ui.js" />" ></script>
       	<script src="<c:url value="/resources/js/custom.js" />" ></script>
       	<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/app.min.js" />" ></script>
       	
       	<script>
			$(function() {
				$(".datepicker").datepicker();
				$(".data").mask("99/99/9999");
				$(".cep").mask("99999-999");
				$(".cpf").mask("999.999.999-99");
				$(".cnpj").mask("99.999.999/9999-99");
			    $(document).tooltip();
		    });
		</script>
				
		<style>
			.sub-title-label {
				border-bottom: 1px solid #B4B4B4;
			}
		</style>
	</head>
	
	<body class="${skinUser} sidebar-mini">
	
		<div class="wrapper">
      		<jsp:include page="header.jsp" />
      		<jsp:include page="menu.jsp" />
      	
			<div class="content-wrapper">
				<section class="content">

					<div class="box box-primary">
						<div class="box-body">
											
							<form:form class="form-horizontal" action="checkoutcompraprodutocliente" method="POST" commandName="cadastro" role="form" autocomplete="off" accept-charset="UTF-8">
								<jsp:include page="message.jsp" />
								
								<form:hidden path="readOnly"/>
								<form:hidden path="preCompraId"/>
								<form:hidden path="cBPartnerId"/>
								<form:hidden path="cBPartnerLocationId"/>
								<form:hidden path="cLocationId"/>
								<form:hidden path="vendaDireta"/>
								
								<div class="form-group">
									<label class="col-sm-8 col-sm-offset-2 sub-title-label">Dados do cliente</label>
								</div>

								<c:if test="${cadastro.readOnly}">
									<form:hidden path="tipoPessoa"/>
									<form:hidden path="cpfCnpj"/>
								</c:if>

								<c:if test="${!cadastro.readOnly}">
									<div class="form-group">
										<label for="tipoPessoa" class="col-sm-2 control-label"></label>
										<div class="col-sm-10">
											<form:radiobutton path="tipoPessoa" value="F" onchange="mudaDocumentoFiscal('F');"/><span class="radio-label">Pessoa física</span>
											<form:radiobutton path="tipoPessoa" value="J" onchange="mudaDocumentoFiscal('J');"/><span class="radio-label">Pessoa jurídica</span>
										</div>
									</div>
									
									<div class="form-group">
										<label id="labelCPFCNPJ" for="cpfCnpj" class="col-sm-2 control-label">CPF:</label>
										
										<div class="col-sm-10">
											<div class="input-group">
												<form:input class="form-control width cpf required" path="cpfCnpj" id="cpfCnpj" maxlength="14"/>
					          				</div>
					          				<form:errors path="cpfCnpj" cssClass="tooltip-erro"/>											
										</div>
									</div>
								</c:if>
								
								<div id="regionCadastros" class="hidden">
								
									<c:if test="${cadastro.cBPartnerId != null && cadastro.cBPartnerId != ''}">
										<form:hidden path="nome"/>
										<form:hidden path="dataNascimento"/>
										<form:hidden path="genero"/>
										<form:hidden path="email"/>
										<form:hidden path="dddTelefone" value="99"/>
										<form:hidden path="numeroTelefone" value="9999-9999"/>
										<form:hidden path="operadoraTelefone"/>
										
										<div class="form-group">
											<label for="nome" class="col-sm-2 control-label">Nome:</label>
											<div class="col-sm-10">
												<input type="text" class="form-control width400" maxlength="60" value="${cadastro.nome}" readonly="readonly"/>
											</div>
										</div>
									</c:if>
									
									<c:if test="${cadastro.cBPartnerId == null || cadastro.cBPartnerId == ''}"> 		
										<div class="form-group">
											<label for="nome" class="col-sm-2 control-label">Nome:</label>
											<div class="col-sm-10">
												<form:input class="form-control width400 required" path="nome" id="nome" maxlength="60"/>
												<form:errors path="nome" cssClass="tooltip-erro"/>
											</div>
										</div>
										<div class="form-group" id="groupDataNascimento">
											<label for="dataNascimento" class="col-sm-2 control-label">Data de nascimento:</label>
											<div class="col-sm-10">
												<form:input class="form-control width100 data required" path="dataNascimento" id="dataNascimento"/>
												<form:errors path="dataNascimento" cssClass="tooltip-erro"/>
											</div>
										</div>
									
										<div class="form-group" id="groupGenero">
											<label for="genero" class="col-sm-2 control-label">Gênero</label>
											<div class="col-sm-10">
												<form:radiobutton path="genero" value="M"/><span class="radio-label">Masculino</span>
												<form:radiobutton path="genero" value="F"/><span class="radio-label">Feminino</span>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-sm-8 col-sm-offset-2 sub-title-label">Contato</label>
										</div>
										
										<div class="form-group">
											<label for="email" class="col-sm-2 control-label">E-Mail</label>
											<div class="col-sm-10">
												<form:input class="form-control width400" path="email" id="email" maxlength="80"/>
												<form:errors path="email" cssClass="tooltip-erro"/>
											</div>
										</div>
										
										<div class="form-group">
											<label for="email" class="col-sm-2 control-label">Telefone</label>
											<div class="col-sm-1">
												<form:input class="form-control width50 required" path="dddTelefone" id="dddTelefone" maxlength="2" onkeypress="return inteiro(event);"/>
												<form:errors path="dddTelefone" cssClass="tooltip-erro"/>
											</div>
											<div class="col-sm-9">
												<form:input class="form-control width100 required" path="numeroTelefone" id="numeroTelefone" maxlength="9" onkeypress="return inteiro(event);"/>
												<form:errors path="numeroTelefone" cssClass="tooltip-erro"/>
											</div>
										</div>
										
										<div class="form-group">
											<label for="operadoraTelefone1" class="col-sm-2 control-label">Operadora:</label>
											<div class="col-sm-10">
												<form:select class="form-control width100" path="operadoraTelefone" id="operadoraTelefone">
													<form:option value="" label=""/>
												   <form:option value="CLARO" label="CLARO"/>
												   <form:option value="OI" label="OI"/>
												   <form:option value="TIM" label="TIM"/>
												   <form:option value="VIVO" label="VIVO"/>				   
												</form:select>
												<form:errors path="operadoraTelefone" cssClass="tooltip-erro"/>
											</div>
										</div>
									</c:if>
									
									<div class="form-group">
										<label class="col-sm-8 col-sm-offset-2 sub-title-label">Endereço</label>
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
											<form:input class="form-control width400 required" path="rua" id="rua" maxlength="80"/>
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
											<form:input class="form-control width400" path="complemento" id="complemento" maxlength="80"/>
											<form:errors path="complemento" cssClass="tooltip-erro"/>
										</div>
									</div>
									
									<div class="form-group">
										<label for="bairro" class="col-sm-2 control-label">Bairro:</label>
										<div class="col-sm-10">
											<form:input class="form-control width400 required" path="bairro" id="bairro" maxlength="80"/>
											<form:errors path="bairro" cssClass="tooltip-erro"/>
										</div>
									</div>
									
									<div class="form-group">
										<label for="pais" class="col-sm-2 control-label">País:</label>
										<div class="col-sm-10">
											<form:select class="form-control width400 required" path="pais" id="pais">
											</form:select>
											<form:errors path="pais" cssClass="tooltip-erro"/>
										</div>
									</div>
									
									<div class="form-group">
										<label for="estado" class="col-sm-2 control-label">Estado:</label>
										<div class="col-sm-10">
											<form:select class="form-control width400 required" path="estado" id="estado">
											</form:select>
											<form:errors path="estado" cssClass="tooltip-erro"/>
										</div>
									</div>
									
									<div class="form-group">
										<label for="cidade" class="col-sm-2 control-label">Cidade:</label>
										<div class="col-sm-10">
											<form:select class="form-control width400 required" path="cidade" id="cidade">
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
								</div>
							
								<div class="col-sm-offset-2">			
									<a href="compras?venda=${cadastro.vendaDireta}" class="btn btn-default">
										<i class="glyphicon glyphicon-arrow-left"></i> <span>Voltar</span>
									</a>
									
									<c:if test="${cadastro.readOnly}">
										<a href="checkoutcompra?precompraid=${cadastro.preCompraId}" class="btn btn-default">
											<i class="glyphicon glyphicon-arrow-right"></i> <span>Continuar</span>
										</a>
									</c:if>
									
									<button type="submit" name="action" class="btn btn-primary hidden" value="completar" id="buttonContinua">
										<i class="glyphicon glyphicon-arrow-right"></i> <span><b>Salvar</b></span>
									</button>
								</div>
								
							</form:form>							
						</div>
					</div>								
				</section>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>
	</body>
		
	<c:url var="listaPaisesURL" value="/lista_paises" />
	<c:url var="listaEstadosURL" value="/lista_estados" />
	<c:url var="listaCidadesURL" value="/lista_cidades" />
	<c:url var="listaProdutosURL" value="/lista_produtos" />
	<script>
	
		function exibeButtonCompleta(checked) {
			if(checked) {
				$('#buttonCompleta').removeClass("hidden");
			}
			else {
				$('#buttonCompleta').addClass("hidden");
			}
		}
	
		function mudaDocumentoFiscal(tipo) {
			$('#cpfCnpj').removeClass("cpf");
			$('#cpfCnpj').removeClass("cnpj");
			$('#cpfCnpj').val("");
			if(tipo == 'F') {
				$('#cpfCnpj').addClass("cpf");
				$(".cpf").mask("999.999.999-99");
				$('#labelCPFCNPJ').html("CPF:");
				$('#groupGenero').removeClass("hidden");
				$('#groupDataNascimento').removeClass("hidden");
				$('#dataNascimento').val("");
			}
			else if(tipo == 'J') {
				$('#cpfCnpj').addClass("cnpj");
				$(".cnpj").mask("99.999.999/9999-99");
				$('#labelCPFCNPJ').html("CNPJ:");
				$('#groupGenero').addClass("hidden");
				$('#groupDataNascimento').addClass("hidden");
				$('#dataNascimento').val("01/01/1900");
			}		
			$('#cpfCnpj').focus();
		}
	
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
					
					// Estados
					
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
					    
					 	// Cidades
						
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
				});
		});
		
		$('#cep').change(
			function() {
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
						$('#complemento').val(data[0].complemento);
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
			$('#estado').change(
				function() {				
					carregaCidades();		
				});
		});
				
		function carregaCidades() {
			
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
		}
	
		$(document).ready(function() {
			$('#cpfCnpj').change(
				function() {
					window.location.href = 'checkoutcompraprodutocliente?id=${cadastro.preCompraId}&taxid=' + $('#cpfCnpj').val();
				}
			);
							
				$('#cpfCnpj').removeClass("cpf");
				$('#cpfCnpj').removeClass("cnpj");
				if('${cadastro.tipoPessoa}' == 'F') {
					$('#cpfCnpj').val('${cadastro.cpfCnpj}');
					$('#cpfCnpj').addClass("cpf");
					$(".cpf").mask("999.999.999-99");
					$('#labelCPFCNPJ').html("CPF:");
					$('#groupGenero').removeClass("hidden");
					$('#groupDataNascimento').removeClass("hidden");
				}
				else if('${cadastro.tipoPessoa}' == 'J') {
					$('#cpfCnpj').val('${cadastro.cpfCnpj}');
					$('#cpfCnpj').addClass("cnpj");
					$(".cnpj").mask("99.999.999/9999-99");
					$('#labelCPFCNPJ').html("CNPJ:");
					$('#groupGenero').addClass("hidden");
					$('#groupDataNascimento').addClass("hidden");
					$('#dataNascimento').val("01/01/1900");
					$('#genero').val('M');
				}		
			
			if($('#cpfCnpj').val() != null && $('#cpfCnpj').val() != '') {
				$('#regionCadastros').removeClass('hidden');
				$('#buttonContinua').removeClass('hidden');
				
				if($('#cBPartnerId').val() == null || $('#cBPartnerId').val() == '') {
					$('#nome').focus();	
				}				
				else {
					$('#cep').focus();
				}
				
			} else {
				$('#cpfCnpj').focus();
			}
		});
	</script>

</html>