var page = "saldoresumido";

function getNLoad(action)
{
	$.ajax({
	 type: "GET",
	 url: action,
	 beforeSend: function()
	 {
		 if (action == "pagamentospendentes")
			 $('#pagamentosPendentes1').addClass("not-active");
		 
		 if ( $( "#loading" ).hasClass( "hidden" ) )
		 {
			 $("#loading").removeClass("hidden");
			 $("#"+page+"").addClass("hidden");
			 page = action;
		 }
		 else
		 {
			 $("#loading").addClass("hidden");
		 }
     },
     success: function (resposta)
     {
    	 if (action == "pagamentospendentes")
			 $('#pagamentosPendentes1').removeClass("not-active");
    	 
    	 $("#divContent").html(resposta);
     }});
}
