function inteiro(e) {
	var tecla = (window.event) ? event.keyCode : e.which;
	if ((tecla > 47 && tecla < 58))
		return true;
	else {
		if (tecla == 8 || tecla == 0)
			return true;
		else
			return false;
	}
}

function postAjaxRegion(action, jsp, divName) {
	try {
		$.post(action + "?exibe=true&jsp=" + jsp, {}, function(resposta) {
			$("#" + divName).html(resposta);
			$("#" + divName).style.display = 'block';
		});
	} catch (e) {
		alert(e);
	}
}

function postUpdateAjaxRegion(action, divName) {
	try {
		$.post(action, {}, function(resposta) {
			$("#" + divName).html(resposta);
			$("#" + divName).style.display = 'block';
		});
	} catch (e) {
		alert(e);
	}
}

function postFormAjaxRegion(action, object, divName) {
	try {
		$.post(action, object, function(resposta) {
			$("#" + divName).html(resposta);
//			$("#" + divName).style.display = 'block';
			$("#" + divName).css('display', 'block');
		});
	} catch (e) {
		alert(e);
	}
}

function escondeAjaxRegion(action, divName) {
	try {
		$.post(action + "?exibe=false", {}, function(resposta) {
			$("#" + divName).html('');
			$("#" + divName).style.display = 'none';
		});
	} catch (e) {
		alert(e);
	}
}

function getAjaxRegion(action, divName) {
	try {
		$.get(action, {}, function(resposta) {
			$("#" + divName).html(resposta);
		});
	} catch (e) {
		alert(e);
	}
}

function decimal(value, event, numero, decimais) {

	var tecla = (window.event) ? event.keyCode : event.which;

	if (tecla == 8 || tecla == 0)
		return true;

	var separador = ",";
	var teclaSeparador = 44;
	//var separador = ".";
	//var teclaSeparador = 46;

	if ((tecla > 47 && tecla < 58)) {
		var index = value.indexOf(separador);
		if (value.length >= numero && index <= 0)
			return false;

		var casas = 0;
		if (index >= 0)
			casas = value.length - index;

		if (casas > decimais)
			return false;

		return true;
	}

	else {
		if (tecla == teclaSeparador) {
			if (value.indexOf(separador) > 0)
				return false;
			if (value.length <= 0)
				return false;
			return true;
		} else
			return false;
	}
}

function get(action) {
	getAjaxRegion(action, 'divContent');
}

function post(action, object) {
	postFormAjaxRegion(action, object, 'divContent');
}

function postInDiv(action, object, div) {
	postFormAjaxRegion(action, object, div);
}


$(document).ready(function() 
{
	$("#nitMessage").html("<span>O NIT é o Número de Identificação do Trabalhador para quem deseja fazer os recolhimentos das contribuições ao INSS, quem deve fazer o cadastro do NIT são os Segurados Contribuinte Individual, Empregado Doméstico, Segurado Especial e Facultativo, O NIT é equivalente ao PIS/PASEP, a diferença é que ele é para o Contribuinte Individual. Caso o contribuinte já tenha o PIS/PASEP poderá usar este numero para fazer o recolhimento ao INSS </span>");
});


$(function()
{
	$(document.body).on('click', '.changeType' ,function(){
		$(this).closest('.phone-input').find('.type-text').text($(this).text());
		$(this).closest('.phone-input').find('.type-input').val($(this).data('type-value'));
	});
	
	$(document.body).on('click', '.btn-remove-phone' ,function(){
		$(this).closest('.phone-input').remove();
	});
	
	
	$('.btn-add-phone').click(function(){

		var index = $('.phone-input').length + 1;
		
		$('.phone-list').append(''+
				'<div class="input-group phone-input">'+
					'<span class="input-group-btn">'+
						'<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="type-text">Type</span> <span class="caret"></span></button>'+
						'<ul class="dropdown-menu" role="menu">'+
							'<li><a class="changeType" href="javascript:;" data-type-value="phone">Phone</a></li>'+
							'<li><a class="changeType" href="javascript:;" data-type-value="fax">Fax</a></li>'+
							'<li><a class="changeType" href="javascript:;" data-type-value="mobile">Mobile</a></li>'+
						'</ul>'+
					'</span>'+
					'<input type="text" name="phone['+index+'][number]" class="form-control" placeholder="(49) 9 99999999" />'+
					'<input type="hidden" name="phone['+index+'][type]" class="type-input" value="" />'+
					'<span class="input-group-btn">'+
						'<button class="btn btn-danger btn-remove-phone" type="button"><span class="glyphicon glyphicon-remove"></span></button>'+
					'</span>'+
				'</div>'
		);

	});
});






