$(document).ready(function()
{
	$("#texto").change(function() 
	{
		 buscaPorTexto( $("#texto").val() );
	});

	$('#ordenacao').val('${ordem}');
	
	$('#texto').focus();
	$('#texto').select();
});


function buscaPorTexto(valor) 
{
	var valor = $('#texto').val();	
}

function ordena(categoria) 
{
	var valor = $('#ordenacao').val();
	window.location.href = 'compras?ordem=' + valor + '&categoria=${categ}&venda=${venda}';  
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







