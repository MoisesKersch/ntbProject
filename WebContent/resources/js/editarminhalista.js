$(document).ready(function() {
	initialFunctions();
});

function initialFunctions()
{
	init();
}


function init() {
	var hashes = window.location.href.slice(
			window.location.href.indexOf('?') + 1).split('&');
	for (var i = 0; i < hashes.length; i++) {
		var hash = hashes[i].split('=');
		if (hash[0] == 'id') {
			id = hash[1];
			break;
		}
	}
}

function deleteList(id)
{
	$.ajax({
		type : "POST",
		url : "popularcontatosdelete",
		data : {
			"id" : id,
		},
		cache : false,
		success : function(response) {
			if (response == 'erro') {
				return;
			}
			$('#'+response+'delete').parent().parent().remove();
		}
	});
}

function openModal(id)
{
	var newId = id;
	$('#exampleModal').modal('toggle');
	$('#popularcontatos').removeClass('typeRequest');
//	$('#popularcontatos').addClass('editButton');
	$('#popularcontatos').removeAttr('onclick');
	$('#modalConfirmButton').text('Confirmar');
	$("#popularcontatos").attr("onClick","editList(\'"+newId+"\');");
	
	$.ajax({
		type : "POST",
		url : "popularcontatoseditar",
		data : {
			"id" : newId,
		},
		cache : false,
		success : function(agenda) {
			if (agenda != null) 
			{
				$("#assunto").val(agenda.assunto);
				$("#anotacao").val(agenda.anotacao);
				$("#quando").val(agenda.quando);
				$("#onde").val(agenda.onde);
				$("#situacao").val(agenda.situacao);
				//editList(id);
			}
			else
			{
				closeModal();
				return;
			}
		}
	});
}

function editList(id)
{
	var newId = id;
	
	$.ajax({
		type : "POST",
		url : "popularcontatoseditarconfirmar",
		data : {
			"id" : newId,
			
			"assunto" : $('#assunto').val(),
			"anotacao" : $('#anotacao').val(),
			"quando" : $('#quando').val(),
			"onde" : $('#onde').val(),
			"situacao" : $('input[name=situacao]:checked').val(),
			"amrAgendaAcaoId" : $('#amrAgendaAcaoId').val(),
		},
		cache : false,
		success : function(agenda) {
			if (agenda == 'erro') {
				return;
			}
			$('#assunto').val("");
			$('#anotacao').val("");
			$('#quando').val("");
			$('#onde').val("");
			$('#situacao').val("");
			$('#amrAgendaAcaoId').val("");

			var html = 
			"<td>" +agenda.assunto+ "</td>" 
			+"<td>" +agenda.anotacao+ "</td>"
			+"<td>" +agenda.quando+ "</td>"   
            +"<td>" +agenda.onde + "</td>" 
            +"<td>" +agenda.situacao+ "</td>"
            +"<td>"
            +"	 <a href=\"#\" class=\"btn btn-info btn-sm\" id=\""+agenda.id+"edit\"" 
            +"   onclick=\"openModal('"+agenda.id+"');\"> "
            +"         <span class=\"glyphicon glyphicon-pencil\"></span>"
            +"   </a>"
            +"   <a href=\"#\" class=\"btn btn-info btn-sm deleteButton\" id=\""+agenda.id+"delete\" data-json=\""+agenda.id+"\" onclick=\"deleteList('2A1471EE9F0746C7B6C23F6DB7CBF653')\">"
            +"         <span class=\"glyphicon glyphicon-trash\"></span>"
            +"   </a>"
            +"</td>";
	
			$('#'+agenda.id).html(html);
			
			confirmModal();
			
			$('#'+agenda.id+'edit').attr("onClick", "openModal('"+agenda.id+"');");
			
			$('#closeModal').trigger('click');
		}
	});
}

function closeModal()
{
	$('#popularcontatos').addClass('typeRequest');
	$('#popularcontatos').removeAttr('onclick');
	$('#popularcontatos').removeClass('editButton');
	$("#popularcontatos").attr("onclick","popularContato();");
	$('#modalConfirmButton').text('Adicionar a Lista');
	$("#popularcontatos").removeAttr('data-json');
}

function confirmModal()
{
	$('#popularcontatos').addClass('typeRequest');
	$('#popularcontatos').removeClass('editButton');
	$("#popularcontatos").attr("onclick","popularContato();");
	$('#modalConfirmButton').text('Adicionar a Lista');
	$("#popularcontatos").removeAttr('data-json');
}



function popularContato() {
	if (validarAgenda()) {
		$.ajax({
			type : "POST",
			url : $('.typeRequest').attr('id'),
			data : {
				"assunto" : $('#assunto').val(),
				"anotacao" : $('#anotacao').val(),
				"quando" : $('#quando').val(),
				"onde" : $('#onde').val(),
				"situacao" : $('#situacao').val(),
				"amrAgendaAcaoId" : $('#amrAgendaAcaoId').val(),

				"nome" : $('#nome').val(),
				"telefone" : $('#telefone').val(),
				"celular" : $('#celular').val(),
				"whatsapp" : $('#whatsapp').val(),
				"skype" : $('#skype').val(),
				"email" : $('#email').val()

			},
			cache : false,
			success : function(agenda) {
				$('#assunto').val("");
				$('#anotacao').val("");
				$('#quando').val("");
				$('#onde').val("");
				$('#situacao').val("");
				$('#amrAgendaAcaoId').val("");

				var html = "<tr><td>" + agenda.assunto + "</td>" + "<td>"
						+ agenda.anotacao + "</td>" + "<td>" + agenda.quando
						+ "</td>" + "<td>" + agenda.onde + "</td>" + "<td>"
						+ agenda.situacao + "</td>" 
						+" <td> "
						+"   <a href='#' class='btn btn-info btn-sm openModal' href='#' id='"+agenda.id+"edit' >"                  
			            +"         <span class='glyphicon glyphicon-pencil'></span>"
			            +"   </a>"
						+"   <a href='#' class='btn btn-info btn-sm deleteButton' href='#' id='"+agenda.id+"delete' data-json='"+agenda.id+"'>"
						+"         <span class='glyphicon glyphicon-trash'></span>"
						+"   </a>"
						+"</td>"
						+"</tr>";

				$('#lista-agenda tbody').append(html);
				
				$('#' +agenda.id+ 'edit').attr("onClick", "openModal('"+agenda.id+"');");

				$('#closeModal').trigger('click');
			}
		});
	}
}

function validarAgenda() {
	if (valorNulo($('#assunto').val())) {
		return false;
	}
	if (valorNulo($('#situacao').val())) {
		return false;
	}
	if (valorNulo($('#amrAgendaAcaoId').val())) {
		return false;
	}
	return true;
}

function valorNulo(value) {
	if (value == null || value == undefined || value == "") {
		return true;
	}
	return false;
}






