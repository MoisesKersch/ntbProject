var validPatrocinadorId = false;
var table = {};

$(document).ready(function(){
	
	responsavelOuAnfitriao();
	
	
	serializeObject();
	validator();
	
	 $("#reuniaoController").click(function ()
	 {
		 reuniaoSaveEdit();
	 })
	 
	 $("#deleteControllerReuniao").click(function ()
	 {
		 reuniaoRemove();
	 })
	 
	$.ajax({
		url: "minhasreunioeslista",
		type: "POST",
		success: function (minhasreunioeslista)
		{
			console.log(minhasreunioeslista)
			table = $('#minhasreunioes').DataTable({
		    "sPaginationType": "full_numbers",
		    data: minhasreunioeslista,
			"language": {
			    "url": "resources/json/Portuguese-Brasil.json",
    	        "select": {
    	            rows: " %d colunas selecionadas"
    	        }
			},
		    columns: [ 
				    	{ data: "responsavel",
							render: function ( data, type, row ) 
							{
								if (typeof(data) == "object")
									return data.nome;
			            		else
			            			row.responsavel.nome;
							}
				    	},
		    			{ data: "anfitriao",
							render: function ( data, type, row ) 
							{
								if (typeof(data) == "object")
									return data.nome;
			            		else
			            			row.anfitriao.nome;
							}
				    	},
		    			{ data: "data" },
		    			{ data: "participantes" },
		    			{ data: "observacoes" },
		    			{ data: "id" }
		    		],
		    	  dom: 'Bfrtip',        // Needs button container
		          select: 'single',
		          responsive: true,
		          altEditor: true,
		          buttons: [{
		            text: 'Adicionar',
		            name: 'add'        // do not change name
		          },
		          {
		            extend: 'selected', // Bind to Selected row
		            text: 'Editar',
		            name: 'edit'        // do not change name
		          },
		          {
		            extend: 'selected', // Bind to Selected row
		            text: 'Remover',
		            name: 'delete'      // do not change name
		         }],
		         "columnDefs": [
		        	    {"targets": [5], "visible": false}
		        	  ]
				})
			}
		})
})

function reuniaoRemove()
{
	 $.ajax({
		 type: "POST",
		 data:  {"id": $('#remove').val()},
		 url: "removerreuniao",
		 success: function(reuniao)
		 {
			 $('#deleteReuniao').modal('toggle'); 
			 table.row({
                 selected : true
               }).remove();
			 
			 table.draw();
		
		 }})
}

function responsavelOuAnfitriao()
{
	$('input[name="papel"]').change(function(){
		if ($('input[name="papel"]:checked').val() == "anfitriao")
		{
			$("#responsavelInput").removeClass("hidden");
			$("#anfitriaoInput").addClass("hidden");
			$("#resoran").text(" Responsável");
		}
		else
		{
			$("#responsavelInput").addClass("hidden");
			$("#anfitriaoInput").removeClass("hidden");
			$("#resoran").text(" Anfitrião");
		}
		
		$("#responsavelId").val("")
		$("#anfitriaoId").val("")
		$("#nome").val("")
	});
}

function reuniaoSaveEdit()
{
	if ($("#reuniaoForm").valid())
	{
		 $.ajax({
			 type: "POST",
			 data:  $("#reuniaoForm").serializeObject(),
			 url: $('#saveOrEdit').val(),
			 success: function(minhasreunioeslista)
			 {
				 console.log(minhasreunioeslista)
				 
				 $('#novoReuniaoModal').modal('toggle');
				 
				 if ($('#saveOrEdit').val() == "registrarnovareuniao")
				 {
					 table.row.add( {
						 "responsavel": minhasreunioeslista.responsavel,
						 "anfitriao": minhasreunioeslista.anfitriao,
						 "data": minhasreunioeslista.data,
						 "participantes": minhasreunioeslista.participantes,
						 "observacoes": minhasreunioeslista.observacoes,
						 "id": minhasreunioeslista.id
				     } ).draw();
				 }
				else
				{
					 table.row({ selected:true }).data( {
						 "responsavel": minhasreunioeslista.responsavel,
						 "anfitriao": minhasreunioeslista.anfitriao,
						 "data": minhasreunioeslista.data,
						 "participantes": minhasreunioeslista.participantes,
						 "observacoes": minhasreunioeslista.observacoes,
						 "id": minhasreunioeslista.id
				      });
				}
				
			 }
		 })
	}
}

function validator()
{
	$.validator.setDefaults({
		    errorClass: 'invalid-feedback'
	 });
	
	$.validator.addMethod( "time", function( value, element ) {
		return this.optional( element ) || /^([01]\d|2[0-3]|[0-9])(:[0-5]\d){1,2}$/.test( value );
	}, "Por favor entre um valor válido, entre 00:00 e 23:59" );

	$("#reuniaoForm").validate({
		onsubmit: false,
		rules: {
			data: {
				required: true
			},
			hora: {
				required: true
			},
			anfitriaoId: {
				  required: true,
			      remote: {
			        url: "validapatrocinadorcustom",
			        type: "POST",
			        data: {"login": function () { return $("#anfitriaoId").val() } },
			        dataFilter: function (response) {
		                var response = jQuery.parseJSON(response);
		                currentMessage = response.Message;
		                if (response) 
		                {
			        		$.ajax({
								 type: "POST",
								 data:  {"login" : $("#anfitriaoId").val()},
								 url: "validapatrocinador",
								 success: function(response)
								 {
									 $("#nome").val(response) 
								 }})
								 return true;
		                }
		                $("#nome").val("");
		                return false;
		            }
			      }
		      },
		      responsavelId: {
				  required: true,
			      remote: {
			        url: "validapatrocinadorcustom",
			        type: "POST",
			        data: {"login": function () { return $("#responsavelId").val() } },
			        dataFilter: function (response) {
		                var response = jQuery.parseJSON(response);
		                currentMessage = response.Message;
		                if (response) 
		                {
			        		$.ajax({
								 type: "POST",
								 data:  {"login" : $("#responsavelId").val()},
								 url: "validapatrocinador",
								 success: function(response)
								 {
									 $("#nome").val(response) 
								 }})
								 return true;
		                }
		                $("#nome").val("");
		                return false;
		            }
			      }
		      }
		 },
		 messages: {
			 anfitriaoId: {
		    	  required: 'Campo obrigatório!',
		    	  remote: 'Anfitrião inexistente no sistema!'
			  },
			  responsavelId: {
		    	  required: 'Campo obrigatório!',
		    	  remote: 'Responsável inexistente no sistema!'
			  }
		  }
	});
	
	
	 jQuery.extend(jQuery.validator.messages, {
	     required: "Campo obrigatório!",
	     remote: "Por favor, corrija este campo.",
	     email: "Por favor, forneça um endereço eletrônico válido.",
	     url: "Por favor, forneça uma URL válida.",
	     hora: "Entre um valor entre 00:00 e 23:59.",
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
}

function serializeObject()
{
	(function($)
			{
			    $.fn.serializeObject = function(){

			        var self = this,
			            json = {},
			            push_counters = {},
			            patterns = {
			                "validate": /^[a-zA-Z][a-zA-Z0-9_]*(?:\[(?:\d*|[a-zA-Z0-9_]+)\])*$/,
			                "key":      /[a-zA-Z0-9_]+|(?=\[\])/g,
			                "push":     /^$/,
			                "fixed":    /^\d+$/,
			                "named":    /^[a-zA-Z0-9_]+$/
			            };


			        this.build = function(base, key, value){
			            base[key] = value;
			            return base;
			        };

			        this.push_counter = function(key){
			            if(push_counters[key] === undefined){
			                push_counters[key] = 0;
			            }
			            return push_counters[key]++;
			        };

			        $.each($(this).serializeArray(), function(){

			            // skip invalid keys
			            if(!patterns.validate.test(this.name)){
			                return;
			            }

			            var k,
			                keys = this.name.match(patterns.key),
			                merge = this.value,
			                reverse_key = this.name;

			            while((k = keys.pop()) !== undefined){

			                // adjust reverse_key
			                reverse_key = reverse_key.replace(new RegExp("\\[" + k + "\\]$"), '');

			                // push
			                if(k.match(patterns.push)){
			                    merge = self.build([], self.push_counter(reverse_key), merge);
			                }

			                // fixed
			                else if(k.match(patterns.fixed)){
			                    merge = self.build([], k, merge);
			                }

			                // named
			                else if(k.match(patterns.named)){
			                    merge = self.build({}, k, merge);
			                }
			            }

			            json = $.extend(true, json, merge);
			        });

			        return json;
			    };
			})(jQuery);
}






