$(document).ready(function(){
	getRedeLinear();
})

function getInfo(celular, email, cidade)
{
	$('#popupInfoModal').modal('toggle');
	
	$('#telefoneModal').text(celular);
	$('#emailModal').text(email);
	$('#cidadeModal').text(cidade);
}

function getRedeLinear()
{
	 $.ajax({
		    type: "GET",
		    url: "getredelinear",
		    beforeSend: function()
		   	{
		   		 if ( $( "#loading" ).hasClass( "hidden" ) )
		   		 {
		   			 $("#loading").removeClass("hidden");
		   			 $("#redeLinearTableId").addClass("hidden");
		   			
		   		 }
		   		 else
		   		 {
		   			 $("#loading").addClass("hidden");
		   		 }
		    },
		    }).done(function (redelinear) {
		    	
	   			$("#loading").addClass("hidden");
		    	$("#redeLinearTableId").removeClass("hidden");
		    	
		    	$('#redeLinear2').DataTable({
		    		"language": {
		    			 "url": "resources/json/Portuguese-Brasil.json"
		    		},
		    		LoadingRecords: false,
		    		processing: true,
		    		"autoWidth": false,
		            data: redelinear,
		            "sAjaxDataProp":"",
		            columns: [
		            	{ data: "login"},
		                { data: "nome" },
		                {
		                	render: function ( data, type, row ) 
		                	{
		                		return "<a class=\"glyphicon glyphicon-list-alt\" onclick=\"getInfo('"+row.celular+"','"+row.email+"','"+row.cidade+"')\"> </a>";
		                	}
		                },
		                { data: "celular" },
		                { data: "email" },
		                { data: "indicador"},
		                { data: "posicionadoEm" },
		                { data: "cidade" },
		                { data: "graduacao" },
		                { data: "nivel" },
		                { data: "ativoPago",
		                	render: function ( data, type, row ) 
		                	{
		                		if (data.ativoPago != "Y")
	                			{	
		                			return "ATIVO";
	                			}
		                		else
		                		{
		                			return "INATIVO";
		                		}
		                	}
		                },
		            ],
		         
		    		"createdRow": function( row, data, dataIndex ) {
		    		     if (data.ativoPago == "INATIVO" ) {        
		    		    	 $(row).addClass('background-inativo-red');
		    		      }
		    		  },
	    		  'columnDefs': [
	    		        	 {
	    		       	      "targets": 2,
	    		       	      "className": "text-center",
	    		        	 },
	    		        	 {
	    		        	      "targets": 6,
	    		        	      "className": "text-center",
	    		        	 },
	    		        	 {
	    		        	      "targets": 4,
	    		        	      "className": "text-center",
	    		        	 },
	    		        	 {
	    		        	      "targets": 8,
	    		        	      "className": "text-center",
	    		        	 },
	    		        	 {
	    		        	      "targets": 9,
	    		        	      "className": "text-center",
	    		        	 },
	    		        	 {
	    		        	      "targets": 10,
	    		        	      "className": "text-center",
	    		        	 },
	    		        	 { "visible": false, "targets": 3},
	    		        	 { "visible": false, "targets": 4},
	    		        	 { "visible": false, "targets": 7},
	    		        ]
		        });
		   });
}	






