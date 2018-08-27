var senhasConferem = false;
var emailsConferem = false;

$(document).ready(function() 
{
	$('#cep').keyup(function() {
		buscarcep($(this).val());
	});

	$(".pop").popover({ trigger: "manual" , html: true, animation:false})
    .on("mouseenter", function () {
        var _this = this;
        $(this).popover("show");
        $(".popover").on("mouseleave", function () {
            $(_this).popover('hide');
        });
    }).on("mouseleave", function () {
        var _this = this;
        setTimeout(function () {
            if (!$(".popover:hover").length) {
                $(_this).popover("hide");
            }
        }, 300);
    });
	
});

function submitForm(number)
{
	if ($("#formNovoCadastro").valid())
	{
		if (number == '1')
		{
			$('#modalLoad').modal(); 
			$('#formNovoCadastro').submit();
		}
		else
			cadastraECompra();
	}
}

function buscarcep(cep) {
	cep = cep.replace(/\_/g, '').replace(/\-/g, '').replace(/\./g, '')
	if (cep.length != 8) {
		return;
	}
	$.ajax({
	  "async": true,
	  "crossDomain": true,
	  "url": "buscarcep?cep="+cep,
	  "method": "GET",
	}).done(function (response) {
		if (response == undefined || response == null) {
			return;
		}
		$("#bairro").val(response.bairro);
		$("#rua").val((response.tipo_logradouro != "" ? response.tipo_logradouro + " " : "") + response.logradouro);
		$("#estado").val($($('#estado option[data-uf='+response.uf+']')[0]).val());
		estadoChange();

		setTimeout(function(){ timerCidade(response.cidade); }, 1000);
	});
}

function timerCidade(cidade) {
	var optCidade;
    $('#cidade option').each(function() {
    	if ($(this).text().trim() === cidade) {
    		optCidade = $(this);
    	}
    });
    if (optCidade != undefined && optCidade != null) {
    	$("#cidade").val(optCidade.val());
    }
}



$(function() {
	 $.validator.setDefaults({
		    errorClass: 'invalid-feedback'
	 });
	 
	  $("#formNovoCadastro").validate({
		  onsubmit: false,
		    rules: {
		    	nome: {
		        required: true
		      },
		      cpfCnpj: {
				  required: true,
			      remote: {
			        url: "cpfcnpjvalido",
			        type: "POST",
			        data: {"checkCpfCnpf": function () { return $("#labelCPFCNPJ").text() } }
			      }
		      },
		      dataNascimento: {
		        required: true
		      },
		      genero: {
			        required: true
			  },
			  tipoPessoa: {
			        required: true
			  },
			  email: {
				  	email: true,
			        required: true
			  },
			  email2: {
			      equalTo: "#email"
			  },
			  //ajax validation
			  usuario: {
				  required: true,
			      remote: {
			        url: "usuariovalido",
			        type: "POST"			      
			      }
		      },
		      senha: {
		    	  required: true
		      },
		      senha1: {
		    	  equalTo: "#senha"
		      },
			  ////////////////
		      cep: {
		    	  required: true
		      },
		      rua: {
		    	  required: true
		      },
		      numero: {
		    	  required: true
		      },
		      bairro: {
		    	  required: true
		      },
		      cidade: {
		    	  required: true
		      },
		      tipoTelefone1: {
		    	  required: true,
		      },
		      operadoraTelefone1: {
		    	  required: true
		      },
		      dddTelefone1: {
		    	  required: true,
		    	  digits: true
		      },
		      numeroTelefone1: {
		    	  required: true,
		    	  digits: true
		      }
		    },
		    
		    messages: {
		    	usuario: {
		    	  required: 'Campo obrigatório!',
		    	  remote: 'Usuario inválido!'
			  },
			  cpfCnpj: {
		    	  required: 'Campo obrigatório!',
		    	  remote: 'CPF/CNPJ inválido!'
			  }
		    }
	 });
	  
	 jQuery.extend(jQuery.validator.messages, {
	     required: "Campo obrigatório!",
	     remote: "Por favor, corrija este campo.",
	     email: "Por favor, forneça um endereço eletrônico válido.",
	     url: "Por favor, forneça uma URL válida.",
	     date: "Por favor, forneça uma data válida.",
	     dateISO: "Por favor, forneça uma data válida (ISO).",
	     number: "Por favor, forneça um número válido.",
	     digits: "Por favor, forneça somente dígitos.",
	     creditcard: "Por favor, forneça um cartão de crédito válido.",
	     equalTo: "Por favor, forneça o mesmo valor novamente.",
	     accept: "Por favor, forneça um valor com uma extensão válida.",
	     maxlength: jQuery.validator.format("Por favor, forneça não mais que {0} caracteres."),
	     minlength: jQuery.validator.format("Por favor, forneça ao menos {0} caracteres."),
	     rangelength: jQuery.validator.format("Por favor, forneça um valor entre {0} e {1} caracteres de comprimento."),
	     range: jQuery.validator.format("Por favor, forneça um valor entre {0} e {1}."),
	     max: jQuery.validator.format("Por favor, forneça um valor menor ou igual a {0}."),
	     min: jQuery.validator.format("Por favor, forneça um valor maior ou igual a {0}.")
	 });
});
