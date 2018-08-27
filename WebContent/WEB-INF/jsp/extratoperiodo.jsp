<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


       	<style type="text/css">
		.jsgrid-cell { 
		    overflow: hidden; 
		}
		
		.jsgrid-pager { text-align: center; }
		
		.total-row {
		  font-weight: bold;
		  background: #f5f5f5;
		}
		
		.total-row td {
		  border-top: 2px solid #efefef;
		}

       	</style>
     
<jsp:include page="financeiromenu.jsp" />
<br>
	<jsp:include page="message.jsp" />
	
	<script>
	
		function includPtBrDataPicker()
		{
			jQuery(function($){
		        $.datepicker.regional['pt-BR'] = {
		               
		                monthNames: ['Janeiro','Fevereiro','Mar&ccedil;o','Abril','Maio','Junho',
		                'Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		                monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun',
		                'Jul','Ago','Set','Out','Nov','Dez'],
		                
		                dayNames: ['Domingo','Segunda-feira','Ter&ccedil;a-feira','Quarta-feira','Quinta-feira','Sexta-feira','Sabado'],
		                dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sab'],
		                dayNamesMin: ['Dom','Seg','Ter','Qua','Qui','Sex','Sab'],
		                
		                weekHeader: 'Sm',
		                dateFormat: 'dd/mm/yy',
		                
		                };
		        $.datepicker.setDefaults($.datepicker.regional['pt-BR']);
			});
		}
		
		$(function() 
		{
			$(".datepicker").datepicker();
			$(".data").mask("99/99/9999");
	    });
	
		var date = new Date();
		var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
		var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
		
		$("#dataInicial").val(firstDay.toLocaleDateString("pt-BR"));
		$("#dataFinal").val(lastDay.toLocaleDateString("pt-BR"));
	
	</script>	                

	<div id="extratoperiodo"> 
		<div class="row">
			<div class="col-lg-12" id="data-init-end">
				  <div class="col-xm-6 col-lg-4 col-md-4">
				    <div class="input-group">
				      <span class="input-group-addon" id="basic-addon3" style="background-color: #3c8dbc; color: white;"><strong> Data Inicial </strong></span>
				      <input type="text" class="form-control data datepicker" id="dataInicial" placeholder="Data Inicial">
				    </div>
				    <br>
				    <div id="dataValidInit" class="tooltip-erro hidden" >Data inicial ou data final não informada informada!</div>
				    
				  </div>
				  <div class="col-xm-6 col-lg-4 col-md-4">
				    <div class="input-group">
				      <span class="input-group-addon" id="basic-addon3" style="background-color: #3c8dbc; color: white;"><strong> Data Final</strong></span>
				      <input type="text" class="form-control data datepicker" placeholder="Data Final" id="dataFinal">
				    </div>
				    
				  </div>
				  <div class="col-lg-4">
				   <button class="btn btn-default header-color" id="basic-addon3" onclick="start()"><strong> Enviar </strong></button>
				  </div>
			  </div>
		</div>
		<br>
		<div class="col-sm-12">
		<div class="row">
		  <div id="loadingExtrato" class="col-md-12 text-center hidden"><i class="fa fa-spinner fa-spin fa-3x fa-fw"></i><div>Gerando extrato</div></div>
		</div> 
		
			<div class="table-responsive hidden" id="idtable">
				<table
					class="table table-bordered table-hover text-center">
					<thead class="bg-primary header-color">
						<tr>
							<th colspan="3">
								<div class="th-inner text-right">Saldo Inicial</div>
								<div class="fht-cell"></div>
							</th>
							<th>
								<div class="th-inner text-right" id="saldoInicial"></div>
								<div class="fht-cell"></div>
							</th>
						</tr>
						<tr>
							<th>Data</th>
							<th>Cartão</th>
							<th>Descrição</th>
							<th>
								<div class="th-inner text-right">Valor</div>
								<div class="fht-cell"></div>
							</th>
						</tr>
					</thead>
					<tbody id=printValues>
					</tbody>
				</table>
			</div>
			
		</div>
	</div> 
