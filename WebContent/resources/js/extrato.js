var saldoCorrente;
var saldoInit;

function start()
{
	$('#saldoInicial').text("");
	$('#printValues').html("");
	
	$('#idtable').addClass('hidden')
	$('#idtable').removeClass('hidden')
	
	$('#data-init-end').removeClass('hidden')
	
	if (  ($("#dataInicial").val() == "")  || ($("#dataInicial").val() == undefined) || ($("#dataInicial").val() == null) ||  ($("#dataFinal").val() == "")  || ($("#dataFinal").val() == undefined) || ($("#dataFinal").val() == null))
	{
		$("#dataValidInit").removeClass("hidden");
		$("#dataValidEnd").removeClass("hidden");
		$('#idtable').addClass('hidden')
	}
	else
	{
		$("#dataValidInit").addClass("hidden");
		$("#dataValidEnd").addClass("hidden");
		
		getSaldoInicial();
	}
}

function getSaldoInicial()
{
	$.ajax({
        type: "GET",
        beforeSend: function()
		{
			 if ( $("#loadingExtrato").hasClass( "hidden" ) )
			 {				
				 $('#data-init-end').addClass('hidden');
				 $("#idtable").addClass("hidden");
				 $("#loadingExtrato").removeClass("hidden");
			 }
	    },
        url: "getsaldoinicial",
        data:{ "dataInicial": $("#dataInicial").val() },
		 success: function (value)
		 {
			 $("#loadingExtrato").addClass("hidden");
			 $('#data-init-end').removeClass('hidden')
			 $("#idtable").removeClass("hidden");
			 
			 
			 if (value != null && value != "")
			 {
				 $('#saldoInicial').text( numberToReal(value) );
				 saldoInit = value;
			 }
			 else
				 $('#saldoInicial').text("RS 0,00");
			 
			 $.ajax({
					type: "GET",
					url: "getextrato",
					beforeSend: function()
					{
						 if ( $("#loadingExtrato").hasClass( "hidden" ) )
						 {				
							 $('#data-init-end').addClass('hidden');
							 $("#idtable").addClass("hidden");
							 $("#loadingExtrato").removeClass("hidden");
						 }
				    },
					data:
					{
						"dataInicial": $("#dataInicial").val(),
						"dataFinal":  $("#dataFinal").val()
					},
					success: function (extrato)
					{
						
						 $("#loadingExtrato").addClass("hidden");
						 $('#data-init-end').removeClass('hidden')
						 $("#idtable").removeClass("hidden");
						 
						var total = 0;
						
						for (x in extrato)
							total += extrato[x].valor;

						var html = "";
						
						for (y in extrato)
						{
							html+= "<tr>"
							+"<td style=\"width: 150px\">" +extrato[y].dataLcto+ "</td>"
							+"<td style=\"width: 60px\">" +extrato[y].debitoCredito+ "</td>"
							+"<td>" +extrato[y].descricao+ "</td>"
							+"<td style=\"width: 150px\" >" 
							+"<div class=\"th-inner text-right\">" +numberToReal(extrato[y].valor)+ "</div>"
							+"<div class=\"fht-cell\"></div>"
							+"</td>"
							+"</tr>";
						}
						
						if (html == "" || html == null || html == undefined)
						{
							html= "	<tr> <th colspan=\"4\"> <div class=\"th-inner text-center\">Nada Encontrado</div> <div class=\"fht-cell\"></div> </th> </tr>";
						}
						else
						{
							html+= "<tr class=\"\"> <td colspan=\"3\"> <div class=\"th-inner text-right\">Saldo Do Período</div> <div class=\"fht-cell\"></div> </td> <th > <div class=\"th-inner text-right\">" +numberToReal(total)+ "</div> <div class=\"fht-cell\"></div> </th> </tr>";
							if ($('#saldoInicial').text() != "RS 0,00")
								html+= "<tr class=\"\"> <th colspan=\"3\"> <div class=\"th-inner text-right\">Saldo Final</div> <div class=\"fht-cell\"></div> </th> <th > <div class=\"th-inner text-right\">" + numberToReal( saldoInit + total ) + "</div> <div class=\"fht-cell\"></div> </th> </tr>";
							else 
								html+= "<tr class=\"\"> <th colspan=\"3\"> <div class=\"th-inner text-right\">Saldo Final</div> <div class=\"fht-cell\"></div> </th> <th > <div class=\"th-inner text-right\">" + numberToReal(total)+ "</div> <div class=\"fht-cell\"></div> </th> </tr>";
						}
						
						$('#printValues').html(html);
						
					}
								
				});
			 
		 }
    });
}

function realParaNumber(texto)
{
	if (texto != null && texto !== "" && texto != undefined)
	{
		var compativelComParseFloat = texto.replace("R$ ","");
	    compativelComParseFloat = compativelComParseFloat.replace(",",".");
	    var valor = parseFloat(compativelComParseFloat);
	    return valor;
	}
	else return null;
    
}

function numberToReal(numero) 
{
	if (numero != null && numero !== "" && numero != undefined)
	{
		var numero = numero.toFixed(2).split('.');
		numero[0] = "R$ " + numero[0].split(/(?=(?:...)*$)/).join('.');
		numero = numero.join(',');
	    
	    numero = numero.replace("-.", "-");
	    
	    return numero;
	}
	else return null;
   
}

function getSaldoFinal()
{
	   var saldoInicial = realParaNumber($('#saldoInicial').text());
	   
	   return saldoFinal = (saldoInicial - Math.abs(saldoCorrente));
}


function getActive()
{
	console.log("hi")
	
	 $(".tab").removeClass("active");
	 $(".tab").addClass("active");
}

















