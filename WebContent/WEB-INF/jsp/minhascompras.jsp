<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>BackOffice</title>

		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">

		<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
    	<script>
//       		$.widget.bridge('uibutton', $.ui.button);
			var data = ${listaData};
			var tr;
	        var row;
    	</script>
    	<script src="<c:url value="/resources/js/demo.js" />" ></script>
    	
       	<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/script.js" />" ></script>
       	<script src="<c:url value="/resources/js/custom.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery-ui.js" />" ></script>
       	<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/app.min.js" />" ></script>
		<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
		
		<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	
		<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
		
		
		
	</head>
	
	<body class="${skinUser} sidebar-mini">
	
		<div class="wrapper">
			<jsp:include page="message.jsp" />
      		<jsp:include page="header.jsp" />
      		<jsp:include page="menu.jsp" />
      	
			<div class="content-wrapper">
				<section class="content">

					<div class="box box-primary">
		                <div class="box-header">
		                	<h3 class="box-title inboxTitle">Minhas compras</h3>
		                </div>

	                  	<div class="box-body">
							<div class="table-responsive">
								<table id="table" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th style="width: 0px;"></th>
											<th>Pedido</th>
											<th>Dias</th>
											<th>Lançamento</th>
											<th>Total</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>

		            </div>

				</section>
			</div>

			<script>
				$(document).ready(function() {
					var table = $('#table').DataTable({
			            "data": data,
				        "columns": [
				            {
				                "className":      'details-control',
				                "orderable":      false,
				                "data":           null,
				                "defaultContent": '',
				            },
				            { "data": "pedido" },
				            { "data": "dias" },
				            { "data": "lancamento" },
				            { "data": "total" }
				        ],
				        "order": [[1, 'asc']],
				        "language": {
						    "sEmptyTable": "Nenhum registro encontrado",
						    "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
						    "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
						    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
						    "sInfoPostFix": "",
						    "sInfoThousands": ".",
						    "sLengthMenu": "_MENU_ Resultados por página",
						    "sLoadingRecords": "Carregando...",
						    "sProcessing": "Processando...",
						    "sZeroRecords": "Nenhum registro encontrado",
						    "sSearch": "Pesquisar",
						    "oPaginate": {
						        "sNext": "Próximo",
						        "sPrevious": "Anterior",
						        "sFirst": "Primeiro",
						        "sLast": "Último"
						    },
						    "oAria": {
						        "sSortAscending": ": Ordenar colunas de forma ascendente",
						        "sSortDescending": ": Ordenar colunas de forma descendente"
						    },
						    "columnDefs": [
						        { "width": "100%", "targets": 0 }
						      ]
						}
				    });

				    $('#table tbody').on('click', 'td.details-control', function () {
				        tr = $(this).closest('tr');
				        row = table.row( tr );
				 
				        if ( row.child.isShown() ) {
				            // This row is already open - close it
				            row.child.hide();
				            tr.removeClass('shown');
				        }
				        else {
				            // Open this row
			            	$.ajax({
		            		  "async": false,
		            		  "url": "buscaitenscompra",
		            		  "method": "POST",
		            		  "data": {
		            			  "id": row.data().id
		            		  }
		            		}).done(function (r) {
								var t = '<table cellpadding="6" cellspacing="0" border="0" style="padding-left:50px;">'+
				                '<tr>'+
				                    '<td><strong>Fatura:</strong></td>'+
				                    '<td>' + (row.data().fatura == undefined || row.data().fatura == null || row.data().fatura == '' ? '' : row.data().fatura) + '</td>'+
				                    '<td><strong>Quitação:</strong></td>'+
				                    '<td>' + (row.data().quitacao == undefined || row.data().quitacao == null || row.data().quitacao == '' ? '' : row.data().quitacao) + '</td>'+
				                '</tr>'+
				                '<tr>'+
				                    '<td><strong>Total Itens:</strong></td>'+
				                    '<td>R$ ' + row.data().itens + '</td>'+
				                    '<td><strong>Frete:</strong></td>'+
				                    '<td>R$ ' + row.data().frete + '</td>'+
				                '</tr>'+
				                '<tr>'+
				                    '<td><strong>Itens:</strong></td>'+
				                    '<td></td>'+
				                '</tr>';
					            for (i in r) {
					            	t += '<tr>'+
					                    '<td style="float: right;">' + (i+1) + '</td>'+
					                    '<td>' + r[i].item + '</td>'+
					                    '<td>Quantidade: ' + r[i].quantidade + '</td>'+
					                    '<td>Unitário: R$ ' + r[i].unitario.toFixed(2).replace(".", ",") + '</td>'+
					                '</tr>';
					            }
				                t += '</table>';
					            row.child( t ).show();
					            tr.addClass('shown');
		            		});
				        }
				    } );

				    $('thead th.details-control').css("width", "10px");
				    $('#table').css("width", "100%");
				});
			</script>
			<jsp:include page="footer.jsp" />
    	</div>

	</body>
</html>
