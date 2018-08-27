var metodoSelecionadoCode;
var metodoSelecionadoName;
var metodoSelecionadoOpt;
var paymentMethodList = [];
var sixNumber;
var cardConfig;
var cardToken;
var parcelamentos;
var btnPagar = "<div class=\"col-xs-12 col-sm-6 col-md-4 col-lg-2\"><a href=\"#\" class=\"btn btn-success pagar\" onclick=\"efetuarPagamento();\">Efetuar Pagamento</a></div>";

$(document).ready(function() {
	iniciarSessao();
	buscarMetodoPagamento();
});

function iniciarSessao() {
	// PagSeguroDirectPayment é a interface de acesso aos métodos
	PagSeguroDirectPayment.setSessionId(sessaoID);
}

function infoComprador() {
	/* 
	 * Obter informações do comprador
	 * OBS.: MÉTODO NÃO PODE SER CHAMADO AO CARREGAR A
	 * PÁGINA POR DEPENDER DE OUTROS MÉTODOS
	 */
	return PagSeguroDirectPayment.getSenderHash();
}

function buscarMetodoPagamento() {
	var funcao = "buscarMetodoPagamento()";
	PagSeguroDirectPayment.getPaymentMethods({
		amount : valorCompra,
		success : function(response) {
			console.log("Success: " + funcao);
			var name;
			paymentMethodList = [];
			for (name in response.paymentMethods) {
				paymentMethodList.push(response.paymentMethods[name]);
			}

			var htmlMethods = ""/*, htmlTabMethods = "<div class=\"container-fluid\"><ul class=\"nav nav-tabs\">"*/;
			$.each(paymentMethodList, function(i,method) {
				if (((soBoleto.toUpperCase() != "TRUE") && (method.name.toUpperCase() == "ONLINE_DEBIT" || method.name.toUpperCase() == "CREDIT_CARD"))) {
//					htmlTabMethods += "<li" + (i == 0 ? " class=\"active\"" : "") + "><a href=\"#\" data-name=\"" + method.name + "\" onclick=\"selecionaTabMetodoPagamento(this);\">" + method.name + "</a></li>"

					var nomeMetodo = "";
					if (method.name.toUpperCase() == "BOLETO") {
						nomeMetodo = "Boleto";
					} else if (method.name.toUpperCase() == "ONLINE_DEBIT") {
						nomeMetodo = "Débito Online";
					} else if (method.name.toUpperCase() == "CREDIT_CARD") {
						nomeMetodo = "Cartão de Crédito";
					} else {
						nomeMetodo = method.name;
					}

					htmlMethods += "<div class=\"col-xs-12\" ><hr><h4>" + nomeMetodo + "</h4><hr></div>";
					htmlMethods += "<div id=\"" + method.name + "\" class=\"col-xs-12\" >";
					for (name in method.options) {
						var opt = method.options[name];
						if (opt.status == "AVAILABLE") {
							var img = "";
							if ((opt.images == null || opt.images == undefined)) {
								img = "<span class=\"text-method\">" + opt.displayName + "</span>";
							} else {
								img = "<span class=\"image-method\"><img src=\"" + urlImage + opt.images.MEDIUM.path + "\" title=\"" + opt.displayName + "\" ></span>";
							}
							htmlMethods += "<div class=\"payment-method\" data-opt=\"" + opt.name + "\" data-name=\"" + method.name + "\" data-method=\"" + method.code + "\" onclick=\"selecionaMetodoPagamento(this);\">" + img + "</div>";
						}
					}
					htmlMethods += "</div>";
				}
			});
//			htmlTabMethods += "</ul></div>"

			$('#payment-methods').html(/*htmlTabMethods + */htmlMethods);
		},
		error : function(response) {
			exibeErros(response.errors, funcao);
		},
		complete : function(response) {
			console.log("Complete: " + funcao);
		}
	});
}

function exibeErros(objJSON, funcao) {
	console.log("Error: " + funcao);
	console.log(objJSON);
	alert( "Error!\n" + (JSON.stringify(objJSON)).replace("{", "").replace("}", "") );
}

function selecionaMetodoPagamento(element) {
	desmarcarSelecaoMetodoPagamento();
	metodoSelecionadoCode = element.dataset.method;
	metodoSelecionadoName = element.dataset.name;
	metodoSelecionadoOpt  = element.dataset.opt;
	element.classList.add('active');
	acaoPorMetodoPagamento();
}

function desmarcarSelecaoMetodoPagamento() {
	metodoSelecionadoCode = null;
	metodoSelecionadoName = null;
	metodoSelecionadoOpt  = null;
	$('.payment-method').removeClass('active');
	$('.pagar').remove();
}

//function selecionaTabMetodoPagamento(element) {
//	desmarcarSelecaoMetodoPagamento();
//	// Altera seleção das TABs
//	$(element.parentElement).parent().children().removeClass('active')
//	$(element.parentElement).addClass('active')
//	// Exibe métodos de pagamento de acordo com TAB selecionada
//	$('.payment-method').parent().css("display", "none");
//	$('#'+element.dataset.name).css("display","block");
//}

function acaoPorMetodoPagamento() {
	switch (metodoSelecionadoCode) {
	case "1": // CREDIT_CARD
		abrirModalCartaoCredito();
		break;
	default:
		$('#'+metodoSelecionadoName).after(btnPagar);
		break;
	}
}

function abrirModalCartaoCredito() {
	$('#modalCreditCard').modal();
}

function buscarDadosCartao(value) {
	if (value == undefined || value == null || value == "") {
		return;
	}
	if ((value.length >= 6) && (sixNumber != value.substring(0,6))) {
		sixNumber = value.substring(0,6);
		buscarBandeiraCartao(value);
	}
}

function buscarBandeiraCartao(bin) {
	var funcao = "buscarBandeiraCartao()";
	PagSeguroDirectPayment.getBrand({
	    cardBin: bin,
	    success: function(response) {
			console.log("Success: " + funcao);
	    	cardConfig = response.brand;
	    	opcoesParcelamento();
			montarFormCartaoConfig();
		},
		error : function(response) {
			exibeErros(response, funcao);
			cardConfig = null;
			montarFormCartaoConfig();
		},
		complete : function(response) {
			console.log("Complete: " + funcao);
		}
	});
}

function montarFormCartaoConfig() {
	var html = "";
	if (!(cardConfig == null || cardConfig == undefined)) {
		if (cardConfig.config.hasDueDate) {
			html += "<div class=\"col-sm-3\"><div class=\"form-group\"><label for=\"expirationMonth\">Mês</label><select id=\"expirationMonth\" class=\"form-control\"><option>01</option><option>02</option><option>03</option><option>04</option><option>05</option><option>06</option><option>07</option><option>08</option><option>09</option><option>10</option><option>11</option><option>12</option></select></div></div>";
			html += "<div class=\"col-sm-6\"><div class=\"form-group\"><label for=\"expirationYear\" >Ano</label><input id=\"expirationYear\"  type=\"number\" class=\"form-control\" size=\"4\" onkeyup=\"validarTamanhoCampo(this);\" ></div></div>";
		}
		if (cardConfig.config.hasCvv) {
			html += "<div class=\"col-sm-3\"><div class=\"form-group\"><label for=\"cvv\">CVV</label><input id=\"cvv\" type=\"number\" class=\"form-control\" size=\"" + cardConfig.cvvSize + "\" onkeyup=\"validarTamanhoCampo(this);\" ></div></div>";
		}
		if (cardConfig.config.hasPassword) {
			html += "<div class=\"col-sm-12\"><div class=\"form-group\"><label for=\"password\">Senha</label><input id=\"password\" type=\"password\" class=\"form-control\" ></div></div>";
		}
	}
	$('#info-credit-card').html(html);
}

function validarTamanhoCampo(element) {
	var size = parseInt($(element).attr("size"));
	if (!(element.value.length <= size)) {
		$(element).val($(element).val().substring(0, size));
	}
}

function criarTokenCartao() {
	var funcao = "criarTokenCartao()";
	if (validarParametrosToken()) {
		PagSeguroDirectPayment.createCardToken({
			brand           : cardConfig.name,
			cardNumber      : $("input#creditCardNumber").val(),
			cvv             : $("input#cvv").val(),
			expirationMonth : $("select#expirationMonth").val(),
			expirationYear  : $("input#expirationYear").val(),
			success : function(response) {
				console.log("Success: " + funcao);
				cardToken = response.card.token;
				$('#modalCreditCard').modal("toggle");
				efetuarPagamento();
			},
			error : function(response) {
				exibeErros(response.errors, funcao);
			},
			complete : function(response) {
				console.log("Complete: " + funcao);
			}
		});
	} else {
		// TODO validar
	}
}

function validarParametrosToken() {
	return true;
}

function opcoesParcelamento() {
	var funcao = "opcoesParcelamento()";
	PagSeguroDirectPayment.getInstallments({
	    amount: valorCompra,
	    maxInstallmentNoInterest: parcJuros,
	    brand: cardConfig.name,
		success : function(response) {
			parcelamentos = [];
			for (name in response.installments) {
				if (parcelamentos.length == 0) {
					parcelamentos = response.installments[name];
				} else {
					parcelamentos = parcelamentos.concat(response.installments[name]);
				}
			}
			console.log("Success: " + funcao);
			montarFormParcelamento();
		},
		error : function(response) {
			parcelamentos = null;
			exibeErros(response.errors, funcao);
		},
		complete : function(response) {
			console.log("Complete: " + funcao);
		}
	});
}

function montarFormParcelamento() {
	var html = "";
	if (!(parcelamentos == null || parcelamentos == undefined)) {
		html += "<div class=\"col-sm-12\"><div class=\"form-group\"><label for=\"installment\">Parcelas</label><select id=\"installment\" class=\"form-control\">";
		for (var i = 0; i < parcelamentos.length; i++) {
			var parcelamento = parcelamentos[i];
			var valOpt = parcelamento.quantity.toString() + "x - R$ ";
			valOpt += parcelamento.installmentAmount.toFixed(2).replace(".", "*").replace(",", ".").replace("*", ",");
			valOpt += (parcelamento.interestFree ? " (SEM JUROS)" : (" - R$ " + parcelamento.totalAmount.toFixed(2).replace(".", "*").replace(",", ".").replace("*", ",")));
			html += "<option value=\"" + parcelamento.quantity + "\">" + valOpt + "</option>";
		}
		html += "</select></div></div>";
	}
	$('#inst-credit-card').html(html);
}

function efetuarPagamento() {
	var valQuantity = $("select#installment").val();
	var installmentValue = null;
	if (!(valQuantity == null || valQuantity == undefined)) {
		for (var i = 0; i < parcelamentos.length; i++) {
			if (parcelamentos[i].quantity == valQuantity) {
				installmentValue = parcelamentos[i].installmentAmount;
				break;
			} 
		}
	}
	var sendHash = infoComprador();
	$.ajax({
		"async" : true,
		"crossDomain" : true,
		"url" : "efetuarpagamento",
		"method" : "POST",
		"headers" : {
			"Content-Type" : "application/x-www-form-urlencoded",
			"Cache-Control" : "no-cache"
		},
		"data" : {
			"metodoCode" : metodoSelecionadoCode,
			"metodoName" : metodoSelecionadoName,
			"metodoOpt"  : metodoSelecionadoOpt,
			"hashSender" : sendHash,
			"compraID"   : compraID,
			"creditCardToken": cardToken,
			"installmentQuantity": valQuantity,
			"installmentValue": installmentValue,
			"noInterestInstallmentQuantity": parcJuros,
			"creditCardHolderName": $("input#creditCardHolderName").val()
		}
	}).done(function(response) {
		if (!(response == null || response == undefined || response == "")) {
			var json = $.parseJSON(response);
			if (!(json.paymentLink == null || json.paymentLink == undefined || json.paymentLink == "")) {
				window.location.href = json.paymentLink;
			} else {
				window.location.href = "minhascompras";
			}
		}
	});
}



