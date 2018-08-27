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

		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquerysctipttop.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/iCheck/square/blue.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/main-layout.css" />">
				
    	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/demo.js" />" ></script>
    	
      
       	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/script.js" />" ></script>
       	
		<script src="<c:url value="/resources/js/novocadastro.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery-ui.js" />" ></script>
       	<script src="<c:url value="/resources/js/custom.js" />" ></script>
       	<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" />" ></script>
    
       	
		<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
    	
    	<script src="<c:url value="/resources/js/initial.js" />" ></script>
    
    	<script src="<c:url value="/resources/js/jquery-ui.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/jquery.mask.js" />" ></script>
    	<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
    	
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
	
	<body class="${skinUser} sidebar-mini" onresize="resizeFrame();">
	
		<div class="wrapper">
			<jsp:include page="message.jsp" />
      		<jsp:include page="header.jsp" />
      		<jsp:include page="menu.jsp" />
      	
			<div class="content-wrapper">
				<section class="content">

					<div class="box box-primary">
		                <div class="box-header">
		                	<h3 class="box-title inboxTitle">Suporte</h3>
		                </div>

	                  	<div class="box-body">
	                  		<div class="col-sm-10">
								<form:form id="formNovoCadastro" name="formNovoCadastro" class="form-horizontal" action="contatoform" method="post" commandName="suporteForm" role="form">
									<jsp:include page="message.jsp" />
	
							  		<div class="form-group">
							    		<label for="nome" class="col-sm-2 control-label">Assunto *</label>
							    		<div class="col-sm-10">
											<form:input class="form-control  required" path="assunto" id="nome" maxlength="60"/>
							    		</div>
							  		</div>

							  		<div class="form-group">
							    		<label for="nome" class="col-sm-2 control-label">Mensagem *</label>
							    		<div class="col-sm-10">
											<form:textarea class="form-control  required" path="descricao" rows="20" cols="120" style="resize:none" />
							    		</div>
							  		</div>
							  		
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-primary">Enviar</button>
										</div>
									</div>
									
									
								</form:form>
							</div>
						</div>
		            </div>
					
				</section>
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
						$('#labelCPFCNPJ').html("CNPJ:");	
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
						$('#labelCPFCNPJ').html("CPF:");
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
						        items += '<option value="' + data[key].id + '">' + data[key].nome + '</option>';  
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
			
			function cadastraECompra() {
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