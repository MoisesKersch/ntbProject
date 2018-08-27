<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="financeiromenu.jsp" />
<br>
	<jsp:include page="message.jsp" />
		<div id="transferencia">
			<form:form id="formTransferencia" name="formTransferencia" class="form-horizontal" action="transferencia" method="post" commandName="transferencia" role="form">
				<div class="form-group">
		    		<label for="adUserIdCredito" class="col-sm-2 control-label">ID destinatário:</label>
		    		<div class="col-sm-10">
						<form:input class="form-control " path="adUserIdCredito" id="adUserIdCredito" maxlength="60"/>
		    			<form:errors path="adUserIdCredito" cssClass="tooltip-erro"/>
		    		</div>
		  		</div>
		  		<div class="form-group" style="margin-top: -5px;">
		  			<div class="col-sm-offset-2 col-sm-10">
			    		<div id="labelUsuario" class="hidden">
				  		</div>	
			  		</div>
		  		</div>
		  		<div class="form-group">
		    		<label for="nome" class="col-sm-2 control-label">Valor:</label>
		    		<div class="col-sm-10">
						<form:input class="form-control" path="valor" id="valor" maxlength="60" onkeypress="return decimal(this.value, event, 10, 2);"/>
		    			<form:errors path="valor" cssClass="tooltip-erro"/>
		    		</div>
		  		</div>
		  		<div class="form-group">
		    		<label for="senhaFinanceira" class="col-sm-2 control-label">Senha financeira:</label>
		    		<div class="col-sm-10">
		    			<form:password class="form-control " path="senhaFinanceira" id="senhaFinanceira" maxlength="60" autocomplete="off"/>
		    			<form:errors path="senhaFinanceira" cssClass="tooltip-erro"/>
		    		</div>
		  		</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a href="#" class="btn btn-primary disabled" id="confirmarButton" onclick="completar();">
							<span class="glyphicon glyphicon-usd" aria-hidden="true"></span>
							Completar
						</a>
					</div>
				</div>
				<div id="confirma-modal" class="modal" tabindex="-1" role="dialog">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Confirmação de Transferência</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<p id="taxavalor"></p>
							</div>
							<div class="modal-footer">
								<a href="#" class="btn btn-primary" onclick="$('#confirma-modal').modal('toggle');post('transferencia', $('#formTransferencia').serialize());">
									Confirmar
								</a>
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
<script>

	$(document).ready(function() {
		$('#adUserIdCredito').keyup(
			function() {
				validaUsuario();
			}
		);
	});

	function validaUsuario() {
		$.post("carregausuariocodigo", 
				{
					'codigo' : $('#adUserIdCredito').val()
				}, 
				function(resposta) {
					$("#labelUsuario").removeClass("hidden");
					if(resposta == null || resposta == "") {
		    			$("#labelUsuario").removeClass("sign-info");
						$("#labelUsuario").addClass("sign-error");
						resposta = '<span></i></span> Usuário não encontrado no sistema';
						$("#labelUsuario").html(resposta);
					}
					else {
						$("#labelUsuario").removeClass("sign-error");
						$("#labelUsuario").addClass("alert alert-info");
						var resp = resposta.split('-');
						resposta = '<span></span> Destinatário: ' + resp[0] + ' Login: ' + resp[1];
						$("#labelUsuario").html(resposta);
						$("#valor").focus();
						$('#confirmarButton').removeClass("disabled");
					}
		   		}
		   	);
	}
	
	$("#adUserIdCredito").focus();
	function completar() {
		$.ajax({
			type : "POST",
			url : "calculataxatransferencia",
			data: {
				"valor": $('#valor').val().replace(".", "*").replace(",", ".").replace("*", ",")
			},
			cache : false,
			success : function(response) {
				$("#taxavalor").html(response);
				$('#confirma-modal').modal();
			}
		});
	}
</script>