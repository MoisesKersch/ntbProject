var table;
var tableEdit;
$(document).ready(function() {

	 $("#contactController").click(function ()
	 {
		 saveEdit();
	 })
	 
	 $("#deleteControllerContato").click(function (){
		 remover("delete");
	 })
	 
	 $("#deleteControllerAgenda").click(function (){
		 remover("deleteagenda");
	 })
	 
	 $("#agendaController").click(function ()
	 {
		 agendaSaveEdit();
	 })
	 
	$.ajax({
	url: "getlist",
	beforeSend: function()
   	{
   		 if ( $( "#loading" ).hasClass( "hidden" ) )
   		 {
   			 $("#loading").removeClass("hidden");
   			 $("#agendacontato").addClass("hidden");
   		 }
   		 else
   		 {
   			 $("#loading").addClass("hidden");
   		 }
   	},
	success: function (agendacontato){
		
		$("#loading").addClass("hidden");
		
		table = $('#agendacontato').DataTable({
	    "sPaginationType": "full_numbers",
	    data: agendacontato,
		"language": {
		    "url": "resources/json/Portuguese-Brasil.json"
		},
	    columns: [ 	{ data: "nome" },
	    			{ data: "telefone" },
	    			{ data: "celular" },
	    			{ data: "email" },
	    			{ 
	    				data: null,
	    				"mRender": function(data, type, full)
	    				{
	    					return "<a class=\"glyphicon glyphicon-list\"  onclick=\"getContactInfo('"+data.telefone+"','"+data.celular+"','"+data.email+"')\"  >";
	    				}},
	    			{ data: "cidade" },
	    			{ data: "estado" },
	    			{ data: "ultimoContato" },
	    			{ data: "proximoContato" },
	    			{ 
	    				data: null,
	    				"mRender": function(data, type, full) 
	    				{
	    					return "<a class=\"glyphicon glyphicon-list\"  onclick=\"getContactInfoDesc('"+data.descricao+"')\"  >";
	    			}},
	    			{ data: "descricao" },
	    			{ data: "id" },
	    			{ 
	    				data: null,
	    				"mRender": function(data, type, full) 
	    				{
	    					return "<a class=\"glyphicon glyphicon-list\" onclick=\"agenda('" +data.id+  "')\">";
	    			}}],
			dom: 'Bfrtip',        // Needs button container
	          select: 'single',
	          responsive: true,
	          altEditor: true,     // Enable altEditor
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
	        	    {"targets": [ 1, 2, 3, 10, 11 ], "visible": false},
	        	    { "special": "datepicker", "targets":  [ 7, 8] },
	        	    {
		           	      "targets": [ 4, 9, 12, 7, 8 ],
		           	      "className": "text-center"
		            },
	        	  ]
			})
	    	$("#agendacontato").removeClass("hidden");
		}
	})
});

function saveEdit()
{
	 if ($("#contatoForm").valid())
	 {
		 $.ajax({
			 type: "POST",
			 data:  $("#contatoForm").serializeObject(),
			 url: $('#saveOrEdit').val(),
			 success: function(agendacontato)
			 {
				 console.log(agendacontato)
				 $('#novoCadastroModal').modal('toggle');
				 
				 if ($('#saveOrEdit').val() == "save")
			 	 {
					 table.row.add( {
	                      "nome": agendacontato.nome ,
	                      "telefone": agendacontato.telefone  ,
	                      "celular": agendacontato.celular,
	                      "email": agendacontato.email,
	                      "cidade": agendacontato.cidade,
	                      "estado": agendacontato.estado,
	                      "ultimoContato": agendacontato.ultimoContato,
	                      "proximoContato": agendacontato.proximoContato,
	                      "descricao": agendacontato.descricao,
	                      "id": agendacontato.id
	                  } ).draw();
				 }
				else
				{
					 table.row({ selected:true }).data( {
	                      "nome":  agendacontato.nome ,
	                      "telefone": agendacontato.telefone  ,
	                      "celular": agendacontato.celular,
	                      "email": agendacontato.email,
	                      "cidade":   agendacontato.cidade,
	                      "estado":     agendacontato.estado,
	                      "ultimoContato": agendacontato.ultimoContato,
	                      "proximoContato":  agendacontato.proximoContato,
	                      "descricao" : agendacontato.descricao,
	                      "id" : agendacontato.id
	                  });
				}
				
			 }
		 })
	 }
}

function agenda(id)
{
	
	$("#idContato").val(id);
	
	$.ajax({
	type: "POST",
	url: "getagendasbyid",
	data: {"id": id},
	success: function (agenda){
		console.log(agenda)
		tableEdit = $('#agenda').DataTable({
		"language": {
		    "url": "resources/json/Portuguese-Brasil.json"
		},
		 retrieve: true,
	    "sPaginationType": "full_numbers",
	    data: agenda,
	    columns: [ 	{ data: "assunto" },
	    			{ data: "anotacao", 
	    				"mRender": function(data, type, full)
	    				{
	    					return "<a class=\"glyphicon glyphicon-list\"  onclick=\"getAgendaInfo('"+data+"')\"  >";
	    				}
	    			},
	    			{ data: "quando" },
	    			{ data: "onde"},
	    			{
	    				data: "situacao",
	                	render: function ( data, type, row ) 
	                	{
	                		return data == "A" ? "Agendado" : "Resolvido";
	                	}
	                },
	    			{ data: "amrAgendaAcaoId",
	    				render: function ( data, type, row ) 
	                	{
	    					if (typeof(data) == "object")
	    						return data.nome;
	                		else
	                			row.amrAgendaAcaoId;
	                	}},
	    			{ data: "id" }
	    		],
			dom: 'Bfrtip',        // Needs button container
	          select: 'single',
	          responsive: true,
	          altEditor: true,     // Enable altEditor
	          buttons: [{
	            text: 'Adicionar',
	            name: 'agendaAdd'     // do not change name
	          },
	          {
	            extend: 'selected', // Bind to Selected row
	            text: 'Editar',
	            name: 'agendaEdit'        // do not change name
	          },
	          {
	            extend: 'selected', // Bind to Selected row
	            text: 'Remover',
	            name: 'agendaDelete'      // do not change name
	         }],
	         "columnDefs": [
	        	    {"targets": [ 6 ], "visible": false},
	        	    {
		           	      "targets": [ 1, 2],
		           	      "className": "text-center"
		            },
	        	  ]
			});
		}
	})
	
	if (tableEdit)
	{
		tableEdit.destroy();
	}
	
	
	$('#agendaCard').removeClass("hidden");
}

function agendaSaveEdit()
{
	 if ($("#agendaForm").valid())
	 {
		 $.ajax({
			 type: "POST",
			 data:  $("#agendaForm").serializeObject(),
			 url: $('#agendaSaveOrEdit').val(),
			 success: function(agenda)
			 {
				 console.log(agenda)
				 $('#novoAgendaModal').modal('toggle');
				 
				 if ($('#agendaSaveOrEdit').val() == "agendasave")
			 	 {
					 var amrAgendaAcaoId = agenda.amrAgendaAcaoId.nome; 
					 console.log(amrAgendaAcaoId)
					 
					 tableEdit.row.add( {
	                      "assunto": agenda.assunto,
	                      "anotacao": agenda.anotacao  ,
	                      "quando": agenda.quando,
	                      "onde": agenda.onde,
	                      "situacao": agenda.situacao,
	                      "amrAgendaAcaoId": agenda.amrAgendaAcaoId,
	                      "id": agenda.id
	                  } ).draw();
				 }
				else
				{
					tableEdit.row({ selected:true }).data( {
						  "assunto": agenda.assunto,
	                      "anotacao": agenda.anotacao  ,
	                      "quando": agenda.quando,
	                      "onde": agenda.onde,
	                      "situacao": agenda.situacao,
	                      "amrAgendaAcaoId": agenda.amrAgendaAcaoId,
	                      "id": agenda.id
	                  });
				}
				
			 }
		 })
	 }
}

function getContactInfo(telefone, celular, email)
{
	$('#popupContatoModal').modal('toggle');
	
	$('#mtelefone').text(telefone);
	$('#mcelular').text(celular);
	$('#memail').text(email);
}

function getContactInfoDesc(descricao)
{
	$('#popupDescricaoModal').modal('toggle');
	$('#mdescricao').text(descricao);
}

function getAgendaInfo(anotacao)
{
	$('#popupAgendaAnotacaoModal').modal('toggle');
	$('#manotacao').text(anotacao);
}

function remover(url)
{
	 $.ajax({
		 type: "POST",
		 data:  {"id": $('#remove').val()},
		 url: url,
		 success: function(agendacontato)
		 {
			 if (url == "delete")
			 {
				 $('#deleteContact').modal('toggle'); 
				 
				 table.row({
	                 selected : true
	               }).remove();
				 
				 table.draw();
			 }
			 else
			 {
				 $('#deleteAgenda').modal('toggle');
				 
				 tableEdit.row({
	                 selected : true
	               }).remove();
				 
				 tableEdit.draw();
			 }
		 }})
}

(function($){
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


$(function() {
	 $.validator.setDefaults({
		    errorClass: 'help-block',
		    highlight: function(element) {
		      $(element)
		        .closest('.form-group')
		        .addClass('has-error');
		    },
		    unhighlight: function(element) {
		      $(element)
		        .closest('.form-group')
		        .removeClass('has-error');
		    },
		    
		    errorPlacement: function (error, element) {
		      if (element.prop('type') === 'checkbox') {
		        error.insertAfter(element.parent());
		      } else {
		        error.insertAfter(element);
		      }
		    }
		  });
	 
	  $("#formNovoCadastro").validate({
		  onsubmit: false,
		    rules: {
		    	nome: {
		        required: true
		      },
		      email: {
		          email: true
		        }
		    },
		    
		    messages: {
		    	nome: {
		        required: 'Campo obrigatório!'
		      },
		      email: {
		    	  email: 'Email inválido!'
			  }
		    }
	 });
});
