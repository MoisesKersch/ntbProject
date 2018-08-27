<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>BackOffice</title>
    	
    	<!--  frameworks -->
    		<!--  jquery -->
   			<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />"></script>
			<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
			<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
			<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" ></script>
			<script src="<c:url value="/resources/js/jquery-ui.min.js" />" ></script>
    		<script src="<c:url value="/resources/js/jquery.mask.js" />" ></script>
   			<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" > </script>
			
			<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquerysctipttop.css" />">
			<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquery-ui.css" />">
			<!--  jquery end -->
			
			<!--  bootstrap -->
			<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
			<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
    	<!--  frameworks end -->
    	
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/iCheck/square/blue.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/balloon.css" />">
		
    	<script src="<c:url value="/resources/js/demo.js" />" ></script>
       	<script src="<c:url value="/resources/js/script.js" />" ></script>
       	
		<script src="<c:url value="/resources/js/novocadastro.js" />" ></script>
       	<script src="<c:url value="/resources/js/custom.js" />" ></script>
       	<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" />" ></script>
    
    
    	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
  	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
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
		<script>
      		$.widget.bridge('uibutton', $.ui.button);
    	</script>
		
	</head>
	
	<body class="${skinUser} sidebar-mini">
	
		<div class="wrapper">
			<jsp:include page="message.jsp" />
      		<jsp:include page="header.jsp" />
      		<jsp:include page="menu.jsp" />
      	
			<div class="content-wrapper">
				<section class="content">
					<div class="box box-primary">
		                <div class="box-header">
		                	<h3 class="box-title inboxTitle">Novo cadastro</h3>
		                </div>

	                  	<div class="box-body">
	                  		<div class="col-sm-10">
							<form:form id="formNovoCadastro" name="formNovoCadastro" class="form-horizontal" action="novocadastronovo" method="post" commandName="cadastro" role="form">
								<form:hidden path="automatico"/>
								<div class="form-group">
						    		<label for="patrocinador" class="col-sm-2 control-label">Indicador</label>
						    		<div class="col-sm-10">
										<form:input class="form-control required" path="patrocinador" id="patrocinador" maxlength="60" readonly="true"/>
						    			<form:errors path="patrocinador" cssClass="tooltip-erro"/>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group">
						    		<label for="nome" class="col-sm-2 control-label">Nome*<form:errors path="nome" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
										<form:input class="form-control   required" path="nome" id="nome" maxlength="60"/>
						    		</div>
						  		</div>
						  		
						  
						  		<div class="form-group">
						    		<label for="tipoPessoa" class="col-sm-2 control-label"></label>
						    		<div class="col-sm-10">
										<form:radiobutton path="tipoPessoa" value="F" onchange="mudaDocumentoFiscal('F');"/><span class="radio-label">Pessoa física</span>
										<form:radiobutton path="tipoPessoa" value="J" onchange="mudaDocumentoFiscal('J');"/><span class="radio-label">Pessoa jurídica</span>
						    		</div>
						  		</div>

						  		<div class="form-group">
						    		<label id="labelCPFCNPJ" for="cpfCnpj" class="col-sm-2 control-label">CPF*<form:errors path="cpfCnpj" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
						    			<c:if test="${cadastro.tipoPessoa == 'F'}">
											<form:input class="form-control cpf required" path="cpfCnpj" id="cpfCnpj" maxlength="11"/>
										</c:if>
										<c:if test="${cadastro.tipoPessoa == 'J'}">
											<form:input class="form-control cnpj required" path="cpfCnpj" id="cpfCnpj" maxlength="14"/>
										</c:if>
						    		</div>
						  		</div>
						  		
								<script>
									$(function() {
										$(".nit").mask("999.99999.99-9");
						            });
								</script>
								<div class="form-group">
									<label for="nit" class="col-sm-2 control-label"> 
										<span data-balloon-length="large" data-balloon="
									      O NIT é o Número de Identificação do Trabalhador para quem deseja fazer os 
									      recolhimentos das contribuições ao INSS, quem deve fazer o cadastro do NIT 
									      são os Segurados Contribuinte Individual, Empregado Doméstico, Segurado Especial e 
									      Facultativo, O NIT é equivalente ao PIS/PASEP, a diferença é que ele é para o 
									      Contribuinte Individual. Caso o contribuinte já tenha o PIS/PASEP poderá usar 
									      este numero para fazer o recolhimento ao INSS"
									      data-balloon-pos="right"> 
									      <span class="glyphicon glyphicon-info-sign pop"></span>
									    </span>
									 &nbsp; 
							   		NIT <form:errors path="nit" cssClass="tooltip-erro"/>
									</label>
									<div class="col-sm-10">
										<form:input class="form-control  nit" path="nit" id="nit" />
									</div>
								</div>
								
						  		<div class="form-group <c:if test="${cadastro.tipoPessoa == 'J'}">hidden</c:if>" id="groupDataNascimento">
						    		<label for="dataNascimento" class="col-sm-2 control-label">Data de nascimento*<form:errors path="dataNascimento" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
										<form:input class="form-control  data required" path="dataNascimento" id="dataNascimento"/>
						    		</div>
						  		</div>
						
						  		<div class="form-group <c:if test="${cadastro.tipoPessoa == 'J'}">hidden</c:if>" id="groupGenero">
						    		<label for="genero" class="col-sm-2 control-label">Gênero</label>
						    		<div class="col-sm-10">
										<form:radiobutton path="genero" value="M"/><span class="radio-label">Masculino</span>
										<form:radiobutton path="genero" value="F"/><span class="radio-label">Feminino</span>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group">
						    		<label for="email" class="col-sm-2 control-label">E-Mail*<form:errors path="email" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
										<form:input id="email" class="form-control required" path="email" name="email" maxlength="80"/>
						    		</div>
						  		</div>
						  		
								<div class="form-group">
						    		<label for="email" class="col-sm-2 control-label">Confirmar E-Mail<form:errors path="email" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
						    			<input class="form-control required " name="email2" maxlength="80">
						    		</div>
						  		</div>
											
								<div class="form-group">
						    		<label for="usuario" class="col-sm-2 control-label">Usuário*<form:errors path="usuario" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
										<form:input class="form-control required" path="usuario" id="usuario" maxlength="80" autocomplete="off"/>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group" style="margin-top: -5px;">
						  			<div class="col-sm-offset-2 col-sm-10">
							    		<div id="usuarioNome" class="hidden"></div>	
							  		</div>
						  		</div>
						  		  		
						  		<div class="form-group">
						    		<label for="senha" class="col-sm-2 control-label">Senha*<form:errors path="senha" cssClass="tooltip-erro"/> </label>
						    		<div class="col-sm-10">
						    			<form:password class="form-control required" path="senha" id="senha" maxlength="60" name="senha" autocomplete="off"/>
						    		</div>
						  		</div>
						  		
								<div class="form-group">
						    		<label for="senha" class="col-sm-2 control-label">Confirmar Senha</label>
						    		<div class="col-sm-10">
						    			<input type="password" class="form-control required" name="senha1" maxlength="60">
						    		</div>
						  		</div>
						  		
								<br>
						  	
								<div class="form-group">
						    		<label for="dddTelefone1" class="control-label"></label>
						    		<form:hidden class="form-control" path="tipoTelefone1" id="tipoTelefone1" value="celular"/>
									<label for="" class="col-sm-2 control-label">Telefone*<form:errors path="numeroTelefone1" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-2">
										<form:input class="form-control required" path="dddTelefone1" id="dddTelefone1" maxlength="2" onkeypress="return inteiro(event);"/>
						    		</div>
						    		<div class="col-sm-4">
										<form:input class="form-control required" path="numeroTelefone1" id="numeroTelefone1" maxlength="9" onkeypress="return inteiro(event);"/>
						    		</div>
						  		</div>
						  		
						  		<br>
								
								<div class="form-group">
						    		<label for="cep" class="col-sm-2 control-label">CEP*<form:errors path="cep" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
										<form:input class="form-control  width200 cep required" path="cep" id="cep"/>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group">
						    		<label for="rua" class="col-sm-2 control-label">Rua*<form:errors path="rua" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
										<form:input class="form-control  required" path="rua" id="rua" maxlength="80"/>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group">
						    		<label for="numero" class="col-sm-2 control-label">Número*<form:errors path="numero" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
										<form:input class="form-control  width100 required" path="numero" id="numero" maxlength="8" onkeypress="return inteiro(event);"/>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group">
						    		<label for="complemento" class="col-sm-2 control-label">Complemento</label>
						    		<div class="col-sm-10">
										<form:input class="form-control " path="complemento" id="complemento" maxlength="80"/>
						    			<form:errors path="complemento" cssClass="tooltip-erro"/>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group">
						    		<label for="bairro" class="col-sm-2 control-label">Bairro*<form:errors path="bairro" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
										<form:input class="form-control  required" path="bairro" id="bairro" maxlength="80"/>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group">
						    		<label for="pais" class="col-sm-2 control-label">País<form:errors path="pais" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
										<form:select class="form-control  required" path="pais" id="pais">
										</form:select>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group">
						    		<label for="estado" class="col-sm-2 control-label">Estado*<form:errors path="estado" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
						    			<form:select class="form-control  required" path="estado" id="estado">
						    			</form:select>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group">
						    		<label for="cidade" class="col-sm-2 control-label">Cidade*<form:errors path="cidade" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
										<form:select class="form-control  required" path="cidade" id="cidade">
						    			</form:select>
						    		</div>
						  		</div>
						  		
						  		<div class="form-group">
						    		<label for="referencia" class="col-sm-2 control-label">Referência<form:errors path="referencia" cssClass="tooltip-erro"/></label>
						    		<div class="col-sm-10">
										<form:input class="form-control " path="referencia" id="referencia" maxlength="80"/>
						    		</div>
						  		</div>
								<div class="form-group">
									<div class="col-sm-offset-2">
										<a href="#" class="btn btn-primary" onclick="submitForm('1')">
											<i class="fa fa-thumbs-o-up"></i>
											Cadastrar
										</a>
										<a href="#" class="btn btn-success"  onclick="submitForm('0')">
											<i class="fa fa-thumbs-o-up"></i>
											Cadastrar e comprar
										</a>
									</div>
								</div>
							</form:form>
							</div>
						</div>
		            </div>
				</section>
				
			</div>
			<div id="modalLoad" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalLoad" aria-hidden="true">
				<div class="modal-dialog" role="document"><img id="imageLoading" src="<c:url value="/resources/img/carregando.gif" />"></div>
			</div>
			<jsp:include page="footer.jsp" />
			
    	</div>
    	
		<c:url var="listaPaisesURL" value="/lista_paises" />
		<c:url var="listaEstadosURL" value="/lista_estados" />
		<c:url var="listaCidadesURL" value="/lista_cidades" />
		<c:url var="listaProdutosURL" value="/lista_produtos" />

		<script>
			function mudaDocumentoFiscal(tipo) {
				if(tipo == 'J') {
					if(!$('#cpfCnpj').hasClass("cnpj")){
						$('#cpfCnpj').removeClass("cpf");
						$('#cpfCnpj').addClass("cnpj");
						$('#cpfCnpj').val("");
						$(".cnpj").mask("99.999.999/9999-99");
						$('#labelCPFCNPJ').html("CNPJ*");	
					}
					$('#groupGenero').addClass("hidden");
					$('#groupDataNascimento').addClass("hidden");
					$('#dataNascimento').val("01/01/1900");
				}
				
				else {
					if(!$('#cpfCnpj').hasClass("cpf")){
						$('#cpfCnpj').removeClass("cnpj");
						$('#cpfCnpj').addClass("cpf");
						$('#cpfCnpj').val("");
						$(".cpf").mask("999.999.999-99");
						$('#labelCPFCNPJ').html("CPF*");
					}					
					$('#groupGenero').removeClass("hidden");
					$('#groupDataNascimento').removeClass("hidden");
					$('#dataNascimento').val("");
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
						
						$.getJSON("${listaEstadosURL}", { 
							pais : '${cadastro.pais}', 
							ajax : 'true'
						},
			
						function(data) {
							var items = "";
						    $.each(data, function(key) {
						        items += '<option data-uf="' + data[key].uf + '" value="' + data[key].id + '">' + data[key].nome + '</option>';  
						    });  
						    $("#estado").html(items);  
						    $('#estado').val('${cadastro.estado}');
						    
						    $.getJSON("${listaCidadesURL}", { 
								estado : '${cadastro.estado}', 
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
			
			function cadastraECompra() {
				$('#modalLoad').modal();
 				$('#automatico').val(true);
				$('#formNovoCadastro').submit();
			}
			
			function validaLogin() 
			{
				$.post("validalogin", 
				{
					'login' : $('#usuario2').val()
				}, 
						
						function(resposta) 
						{
							if(resposta != null)
							{
								$("#usuarioNome").removeClass("hidden");
								if (resposta !=='OK')
								{
				    				$('#usuarioNome').attr( 'data-content',resposta);
				    				$('#usuarioNomeInfo').html(resposta);
								}
								else
								{
									$("#usuarioNome").addClass("hidden");
									$('#usuarioNomeInfo').html("");
								}
									
							}
							else 
							{
								$("#usuarioNome").removeClass("hidden");
								$('#usuarioNome').attr( 'data-content','Campo requerido!')
							}
				   		});
			}
			
			
			$(document).ready(function() {
				$('#usuario2').keyup(
					function() {
						validaLogin();
					}
				);
			});
			
			$('#nome').focus();
			
		</script>
		
		
	</body>

</html>