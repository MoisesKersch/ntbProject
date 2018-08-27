<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>BackOffice</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="resources/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/AdminLTE.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/style.css">


<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/jquerysctipttop.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/skins/_all-skins.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="resources/plugins/iCheck/square/blue.css">

<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/adesao.css">
<script src="resources/js/jquery-1.11.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.bootstrap-growl.js"></script>
<script src="resources/js/jquery.maskedinput.min.js"></script>
<script src="resources/js/script.js"></script>
<script src="resources/js/jquery-ui.js"></script>
<script src="resources/js/angular-1.0.1.min.js"></script>
<script src="resources/js/custom.js"></script>
<script src="resources/js/adesao.js"></script>

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
	$(function() {
		$('.required-icon').tooltip({
			placement : 'left',
			title : 'Required field'
		});
	});
</script>

</head>

<body class="register-page">

	<div class="register-box">
		<jsp:include page="message.jsp" />

		<div class="register-logo">
			<img width="300px" src="resources/img/logo-home.png" />
		</div>

		<div class="register-box-body">
			<p class="login-box-msg titleFontWeight">Efetuando nova adesão</p>

			<form:form id="formadesao" class="form-horizontal" action="adesao_novo" method="post" commandName="cadastro" role="form" autocomplete="off" accept-charset="UTF-8">

				<div class="form-group">
					<label for="patrocinador" class="col-sm-3 col-lg-3 control-label">
					
					<span id="patrocinadorNome" class="glyphicon glyphicon-info-sign pop hidden" data-container="body" data-toggle="popover" data-placement="left"
						data-content="ID indicador inválido" data-original-title=""
						title=""> 
					</span> &nbsp; ID Indicador* <form:errors
							path="patrocinador" cssClass="tooltip-erro" />
					</label>

					<div class="col-sm-3 col-lg-3 ">
						<form:input class="form-control" path="patrocinador" id="patrocinador" maxlength="60" />
					</div>

					<div class="col-sm-2 col-lg-3">
						<a type="button" class="btn btn-primary pop" id="btnIndicador"
							data-container="body" data-toggle="popover"
							data-placement="right" onclick="getIndicador();"
							data-content="Clique aqui para gerar um ID indicador<br>"
							data-original-title="" title=""> Não Tenho um Indicador </a>
					</div>
				</div>

				<div class="form-group">
					<label for="" class="col-sm-3 control-label"> Patrocinador</label>
					<div class="col-sm-9" style="margin-top: 7px;">
						<span id="patrocinadorNomeDisplay" class="titleFontWeight">
						</span>
					</div>
				</div>


				<div class="form-group">
					<label for="nome" class="col-sm-3 control-label">Nome*</label>
					<div class="col-sm-9">
						<form:input class="form-control" path="nome" id="nome"
							maxlength="60" />
					</div>
				</div>

				<div class="form-group">
					<label for="tipoPessoa" class="col-sm-3 control-label"></label>
					<div class="col-sm-9">
						<form:radiobutton path="tipoPessoa" value="F"
							onchange="mudaDocumentoFiscal('F');" />
						<span class="radio-label">Pessoa física</span>
						<form:radiobutton path="tipoPessoa" value="J"
							onchange="mudaDocumentoFiscal('J');" />
						<span class="radio-label">Pessoa jurídica</span>
					</div>
				</div>

		  		<div class="form-group">
		    		<label id="labelCPFCNPJ" for="cpfCnpj" class="col-sm-3 control-label">CPF*<form:errors path="cpfCnpj" cssClass="tooltip-erro"/></label>
		    		<div class="col-sm-9">
		    			<c:if test="${cadastro.tipoPessoa == 'F'}">
							<form:input class="form-control cpf" path="cpfCnpj" id="cpfCnpj"/>
						</c:if>
						<c:if test="${cadastro.tipoPessoa == 'J'}">
							<form:input class="form-control cnpj" path="cpfCnpj" id="cpfCnpj"/>
						</c:if>
		    		</div>
		  		</div>

				<script>
					$(function() {
						$(".nit").mask("999.99999.99-9");
					});
				</script>

				<div class="form-group">
					<label for="nit" class="col-sm-3 control-label"> <span
						class="glyphicon glyphicon-info-sign pop" data-container="body"
						data-toggle="popover" data-placement="left"
						data-content="Clique aqui para gerar um ID indicador<br>
				   		O NIT é o Número de Identificação do Trabalhador para quem deseja fazer os recolhimentos das contribuições ao INSS, quem deve fazer o cadastro do NIT são os Segurados Contribuinte Individual, Empregado Doméstico, Segurado Especial e Facultativo, O NIT é equivalente ao PIS/PASEP, a diferença é que ele é para o Contribuinte Individual. Caso o contribuinte já tenha o PIS/PASEP poderá usar este numero para fazer o recolhimento ao INSS
				   		"
						data-original-title="" title=""> </span> &nbsp; NIT<form:errors
							path="nit" cssClass="tooltip-erro" />
					</label>
					<div class="col-sm-9">
						<form:input class="form-control nit" path="nit" id="nit" />
					</div>
				</div>

				<div class="form-group <c:if test="${cadastro.tipoPessoa == 'J'}">hidden</c:if>"
					id="groupDataNascimento">
					<label for="dataNascimento" class="col-sm-3 control-label">Data
						de nascimento*<form:errors path="dataNascimento"
							cssClass="tooltip-erro" />
					</label>
					<div class="col-sm-9">
						<form:input class="form-control width100 data required"
							path="dataNascimento" id="dataNascimento" />
					</div>
				</div>

				<div class="form-group <c:if test="${cadastro.tipoPessoa == 'J'}">hidden</c:if>"
					id="groupGenero">
					<label for="genero" class="col-sm-3 control-label">Gênero</label>
					<div class="col-sm-9">
						<form:radiobutton path="genero" value="M" />
						<span class="radio-label">Masculino</span>
						<form:radiobutton path="genero" value="F" />
						<span class="radio-label">Feminino</span>
					</div>
				</div>

				<div class="form-group">
					<label for="email" class="col-sm-3 control-label">E-Mail*<form:errors
							path="email" cssClass="tooltip-erro" /></label>
					<div class="col-sm-9">
						<form:input class="form-control" path="email"
							name="email" id="email" maxlength="80" />
					</div>
				</div>

				<div class="form-group">
					<label for="email" class="col-sm-3 control-label">Confirmar
						E-Mail</label>
					<div class="col-sm-9">
						<input class="form-control" name="email2" maxlength="80">
						<span id="emailInfo" class="warning-red-message"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="usuario" class="col-sm-3 control-label"> <span
						id="usuarioNome" class="glyphicon glyphicon-info-sign pop hidden"
						data-container="body" data-toggle="popover" data-placement="left"
						data-content="" data-original-title="" title=""> </span> &nbsp;
						Usuário*<form:errors path="usuario" cssClass="tooltip-erro" />
					</label>
					<div class="col-sm-9">
						<form:input class="form-control  required" path="usuario"
							id="usuario2" maxlength="80" autocomplete="off" />
						<span id="usuarioNomeInfo" class="warning-red-message"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="senha" class="col-sm-3 control-label">Senha*<form:errors
							path="senha" cssClass="tooltip-erro" /></label>
					<div class="col-sm-9">
						<form:password class="form-control  required" path="senha"
							id="senha1" maxlength="60" autocomplete="off" />

					</div>
				</div>

				<div class="form-group">
					<label for="senha" class="col-sm-3 control-label">Confirmar
						Senha</label>
					<div class="col-sm-9">
						<input type="password" class="form-control  required" id="senha2"
							maxlength="60"> <span id="senhaInfo"
							class="warning-red-message"></span>
					</div>
				</div>

				<br>

				<div class="form-group">
					<label for="" class="col-sm-3 control-label">Telefone*<form:errors
							path="dddTelefone1" cssClass="tooltip-erro" /></label>
					<form:hidden class="form-control" path="tipoTelefone1" id=""
						value="celular" />
					<label for="dddTelefone1" class="control-label"> </label>
					<div class="col-sm-2">
						<form:input class="form-control required" path="dddTelefone1"
							id="dddTelefone1" maxlength="2"
							onkeypress="return inteiro(event);"  placeholder="DDD" />
					</div>
					<div class="col-sm-4">
						<form:input class="form-control required" path="numeroTelefone1"
							id="numeroTelefone1" maxlength="9"
							onkeypress="return inteiro(event);" />
					</div>
				</div>
				<br>
				<div class="form-group">
					<label for="cep" class="col-sm-3 control-label">CEP*<form:errors
							path="cep" cssClass="tooltip-erro" /></label>
					<div class="col-sm-9">
						<form:input class="form-control width100 cep required" path="cep"
							id="cep" />
					</div>
				</div>

				<div class="form-group">
					<label for="rua" class="col-sm-3 control-label">Rua*<form:errors
							path="rua" cssClass="tooltip-erro" /></label>
					<div class="col-sm-9">
						<form:input class="form-control  required" path="rua" id="rua"
							maxlength="80" />
					</div>
				</div>

				<div class="form-group">
					<label for="numero" class="col-sm-3 control-label">Número*<form:errors
							path="numero" cssClass="tooltip-erro" /></label>
					<div class="col-sm-9">
						<form:input class="form-control width100 required" path="numero"
							id="numero" maxlength="8" onkeypress="return inteiro(event);" />
					</div>
				</div>

				<div class="form-group">
					<label for="complemento" class="col-sm-3 control-label">Complemento<form:errors
							path="complemento" cssClass="tooltip-erro" /></label>
					<div class="col-sm-9">
						<form:input class="form-control " path="complemento"
							id="complemento" maxlength="80" />
					</div>
				</div>

				<div class="form-group">
					<label for="bairro" class="col-sm-3 control-label">Bairro*<form:errors
							path="bairro" cssClass="tooltip-erro" /></label>
					<div class="col-sm-9">
						<form:input class="form-control  required" path="bairro"
							id="bairro" maxlength="80" />
					</div>
				</div>

				<div class="form-group">
					<label for="pais" class="col-sm-3 control-label">País*<form:errors
							path="pais" cssClass="tooltip-erro" /></label>
					<div class="col-sm-9">
						<form:select class="form-control  required" path="pais" id="pais">
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label for="estado" class="col-sm-3 control-label">Estado*<form:errors
							path="estado" cssClass="tooltip-erro" /></label>
					<div class="col-sm-9">
						<form:select class="form-control  required" path="estado"
							id="estado">
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label for="cidade" class="col-sm-3 control-label"> Cidade*
						<form:errors path="cidade" cssClass="tooltip-erro" />
					</label>
					<div class="col-sm-9">
						<form:select class="form-control  required" path="cidade"
							id="cidade">
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label for="referencia" class="col-sm-3 control-label">Referência<form:errors
							path="referencia" cssClass="tooltip-erro" /></label>
					<div class="col-sm-9">
						<form:input class="form-control " path="referencia"
							id="referencia" maxlength="80" />
					</div>
				</div>

				<div class="jumbotron" style="padding: 20px;">

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5 text-center">
							<a href="resources/termos_de_uso.pdf" class="btn btn-default"
								target="_blank"> <span class="glyphicon glyphicon-download"
								aria-hidden="true"></span> Download termos de uso
							</a>
						</div>
					</div>

				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-5 text-center">
						<a href="#" class="btn btn-primary btn-block btn-flat"
							id="buttonCompleta"
							onclick="submitForm()">
							Completar </a>
				
						<c:if test="${cadastro.aceito}">
							<script>
								$('#buttonCompleta').removeClass('hidden');
							</script>
						</c:if>
					</div>

					<div class="col-sm-5 text-center">
						<a href="home" class="btn btn-white btn-block btn-flat">
							Cancelar </a>
					</div>
				</div>
			</form:form>
		</div>
		<div id="modalLoad" class="modal fade" tabindex="-1" role="dialog"
			aria-labelledby="modalLoad" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<img id="imageLoading"
					src="<c:url value="/resources/img/carregando.gif" />">
			</div>
		</div>
	</div>

	<c:url var="listaPaisesURL" value="/lista_paises" />
	<c:url var="listaEstadosURL" value="/lista_estados" />
	<c:url var="listaCidadesURL" value="/lista_cidades" />
	<c:url var="listaProdutosURL" value="/lista_produtos" />
	<script>
		function exibeButtonCompleta(checked) {
			if (checked) {
				$('#buttonCompleta').removeClass("hidden");
			} else {
				$('#buttonCompleta').addClass("hidden");
			}
		}

		function mudaDocumentoFiscal(tipo) {
			if (tipo == 'J') {
				if (!$('#cpfCnpj').hasClass("cnpj")) {
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
				if (!$('#cpfCnpj').hasClass("cpf")) {
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

		$(document)
				.ready(
						function() {$
									.getJSON(
'${listaPaisesURL}',
{
	ajax : 'true'
},

function(data) {
	var html = '';
	var len = data.length;

	for (var i = 0; i < len; i++) {
		html += '<option value="' + data[i].id + '">'
				+ data[i].nome
				+ '</option>';
	}
	html += '</option>';
	$('#pais').html(html);
	$('#pais').val(
			'${cadastro.pais}');

	$
	.getJSON( "${listaEstadosURL}",
				{
						pais : '${cadastro.pais}',
						ajax : 'true'
					},

					function(data) {
						var items = "";
						$
								.each(
										data,
										function(
												key) {
											items += '<option data-uf="' + data[key].uf + '" value="' + data[key].id + '">'
													+ data[key].nome
													+ '</option>';
										});
						$("#estado")
								.html(
										items);
						$('#estado')
								.val(
										'${cadastro.estado}');

						$
								.getJSON(
										"${listaCidadesURL}",
										{
											estado : '${cadastro.estado}',
											ajax : 'true',
											completo : 'N'
										},

										function(
												data) {
											var items = "";
											$
													.each(
															data,
															function(
																	key) {
																items += '<option value="' + data[key].id + '">'
																		+ data[key].nome
																		+ '</option>';
															});
											$(
													"#cidade")
													.html(
															items);
											$(
													'#cidade')
													.val(
															'${cadastro.cidade}');
										});
					});
});
						});

		$(document)
				.ready(
						function() {
							$('#cep')
									.change(
											function() {
												$
														.getJSON(
																"${listaCidadesURL}",
																{
																	ajax : 'true',
																	estado : $(
																			'#cep')
																			.val(),
																	completo : 'Y'
																},

																function(data) {
																	$('#estado')
																			.val(
																					data[0].uf);
																	$('#rua')
																			.val(
																					data[0].logradouro);
																	$('#bairro')
																			.val(
																					data[0].bairro);
																	$(
																			'#complemento')
																			.val(
																					data[0].complemento);
																	var cidade = data[0].cidade;

																	$
																			.getJSON(
																					"${listaCidadesURL}",
																					{
																						estado : $(
																								"#estado")
																								.val(),
																						ajax : 'true',
																						completo : 'N'
																					},

																					function(
																							data) {
																						var items = "";
																						$
																								.each(
																										data,
																										function(
																												key) {
																											items += '<option value="' + data[key].id + '">'
																													+ data[key].nome
																													+ '</option>';
																										});
																						$(
																								"#cidade")
																								.html(
																										items);
																						$(
																								'#cidade')
																								.val(
																										cidade);
																					});

																});
											});
						});

		$(document)
				.ready(
						function() {
							$('#pais')
									.change(
											function() {
												$
														.getJSON(
																"${listaEstadosURL}",
																{
																	pais : $(
																			'#pais')
																			.val(),
																	ajax : 'true'
																},

																function(data) {
																	var items = "";
																	$
																			.each(
																					data,
																					function(
																							key) {
																						items += '<option data-uf="' + data[key].uf + '" value="' + data[key].id + '">'
																								+ data[key].nome
																								+ '</option>';
																					});
																	$("#estado")
																			.html(
																					items);
																	$('#estado')
																			.val(
																					'${cadastro.estado}');
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
					items += '<option value="' + data[key].id + '">'
							+ data[key].nome + '</option>';
				});
				$("#cidade").html(items);
				$('#cidade').val('${cadastro.cidade}');
			});
		}

		$(document).ready(function() {
			$('#patrocinador').keyup(function() {
				validarPatrocinador();
			});
		});

		function validaLogin() {
			$.post("validalogin", {
				'login' : $('#usuario2').val()
			},

			function(resposta) {
				if (resposta != null) {
					$("#usuarioNome").removeClass("hidden");
					if (resposta !== 'OK') {
						$('#usuarioNome').attr('data-content', resposta);
						$('#usuarioNomeInfo').html(resposta);
					} else {
						$("#usuarioNome").addClass("hidden");
						$('#usuarioNomeInfo').html("");
					}

				} else {
					$("#usuarioNome").removeClass("hidden");
					$('#usuarioNome').attr('data-content', 'Campo requerido!')
				}
			});
		}

		$(document).ready(function() {
			$('#usuario2').keyup(function() {
				validaLogin();
			});
		});

		$("#patrocinador").focus();
	</script>

</body>
</html>