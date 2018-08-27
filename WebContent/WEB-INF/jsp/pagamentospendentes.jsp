<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="financeiromenu.jsp" />
<br>
	<jsp:include page="message.jsp" />
	<div id="pagamentospendentes"> 
		<div class="table-responsive">
			<table class="table table-bordered table-striped" id="pagamentosPendentes">
				<thead>
					<tr>
						<th><input type="checkbox" name="select-all" id="select-all"></th>
						<th>Login</th>
						<th>Nome</th>
						<th>CPF</th>
						<th>Patrocinador</th>
						<th>Descrição</th>
						<th width="40px"></th>
						<th width="40px"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${lista}">
						<tr>
							<td><input type="checkbox" class="checkbox-dp" name="inpSelecao${item.id}" id="inpSelecao${item.id}" value="${item.cDebtPaymentId}"><br></td>
							<td>${item.loginRede}</td>
							<td>${item.name}</td>
							<td>${item.taxid}</td>
							<td>${item.patrocinador}</td>
							<td>${item.descricao}</td>
							<td>
								<a href="downloadboleto/${item.cDebtPaymentId}" class="btn btn-default" title="Imprimir boleto">
									<span class="fa fa-barcode" aria-hidden="true"></span>
								</a>
							</td>
							<td>
								<a href="checkoutpagseguro?id=${item.cDebtPaymentId}" class="btn btn-default" title="Pagar com PagSeguro">
									<span class="fa fa-credit-card" aria-hidden="true"></span>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br>
		
		
		
			<div class="row">
				  <div class="col-xm-6 col-lg-4 col-md-4">
				    <div class="input-group">
				      <span class="input-group-addon" id="basic-addon3" style="background-color: #3c8dbc; color: white;"><strong> Senha financeira </strong></span>
				      <input type="password" maxlength="60" autocomplete="off" class="form-control" id="senhaFinanceira" name="senhaFinanceira" placeholder="Senha financeira">
				    </div>
				    <br>
				    <div id="dataValidInit" class="tooltip-erro hidden" >Data inicial ou data final não informada informada!</div>
				    
				  </div>
				  <div class="col-xm-6 col-lg-4 col-md-4">
				    <div class="input-group">
				      <span class="input-group-addon" id="basic-addon3" style="background-color: #3c8dbc; color: white;"><strong> Código Voucher </strong></span>
				      <input type="text" class="form-control data datepicker" id="voucher" name="voucher" placeholder="Código Voucher" maxlength="8">
				    </div>
				  </div>
				  <div class="col-lg-4">
				   <button class="btn btn-default header-color" id="pagar" onclick="paga();" style="background-color: #3c8dbc; color: white;"><strong> Pagar </strong></button>
				  </div>
			  </div>
	
		
<!-- 		<div class="center-block form-horizontal"> -->
		
<!-- 			<div class="form-group"> -->
<!-- 	    		<label for="senhaFinanceira" class="col-sm-2 control-label">Senha financeira:</label> -->
<!-- 	    		<div class="col-sm-6"> -->
<!-- 					<input id="senhaFinanceira" name="senhaFinanceira" class="form-control width400" value="" maxlength="60" autocomplete="off" type="password"> -->
<!-- 	    		</div> -->
<!-- 	  		</div> -->
	
<!-- 	  		<div class="form-group"> -->
<!-- 	    		<label for="voucher" class="col-sm-2 control-label">Código Voucher:</label> -->
<!-- 	    		<div class="col-sm-6"> -->
<!-- 					<input id="voucher" name="voucher" class="form-control width200" value="" maxlength="8" type="text"> -->
<!-- 	    		</div> -->
<!-- 	  		</div> -->
	
<!-- 	  		<div class="form-group"> -->
<!-- 	    		<div class="col-sm-offset-2 col-sm-4"> -->
<!-- 		    		<a id="pagar" href="#" class="btn btn-primary" onclick="paga();"> -->
<!-- 						<span class="glyphicon glyphicon-usd" aria-hidden="true"></span> -->
<!-- 						Pagar -->
<!-- 					</a> -->
<!-- 				</div> -->
<!-- 	  		</div> -->
	  		
<!-- 		</div> -->
	</div>
<script>

	$(document).ready(function() {
	    $('#pagamentosPendentes').dataTable( {
	        "language": {
	            "url": "resources/json/Portuguese-Brasil.json"
	        },
	        "columnDefs": [
		 		{
		 			"orderable": false,
			       	"targets": 0,
			       	"className": "text-center"
			    },
				{
		 			"orderable": false,
			       	"targets": 6,
			       	"className": "text-center"
			    },
				{
		 			"orderable": false,
			       	"targets": 7,
			       	"className": "text-center"
			    },
	          ]
	    } );
	});
	
	$('#select-all').click(function(event) {   
	    if(this.checked) {
	        $('.checkbox-dp').each(function() {
	            this.checked = true;                        
	        });
	    }
	    else {
	        $('.checkbox-dp').each(function() {
	            this.checked = false;                        
	        });
	    }
	});
	
	function paga() {
		var id = '';
        $('.checkbox-dp').each(function() {
        	if(this.checked) {
            	id += '#' + this.value;
        	}                        
        });
        
        $.post('pagamentospendentes', {
            	id : id,
            	senha : $("#senhaFinanceira").val(),
            	voucher : $("#voucher").val()
        	}, 

        	function(resposta) {
				$("#divContent").html(resposta);
			}
		);
	}
</script>