<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="financeiromenu.jsp" />

<br>

<jsp:include page="message.jsp" />
	<div id="saque"> 
		<c:if test="${nit == null || nit == ''}">
			<div class="box ativo-box informacao">
				<div class="box-header">
					<h3 class="box-title">Alerta!</h3>
				</div>
				<div class="box-body pad">
					<p>Campo número de identificação do trabalhador (NIT) precisa ser preenchido.</p>
					<p>
						Edite seus dados clicando aqui <a href="meusdados">AQUI</a>.
					</p>
				</div>
			</div>
		</c:if>
		
		<form:form id="formSaque" name="formSaque" class="form-horizontal"
			action="saque" method="post" commandName="saque" role="form">
		
			<div class="form-group">
				<label for="nome" class="col-sm-2 control-label">Valor:</label>
				<div class="col-sm-10">
					<form:input class="form-control width400" path="valor" id="valor"
						maxlength="60"
						onkeypress="return decimal(this.value, event, 10, 2);" />
					<form:errors path="valor" cssClass="tooltip-erro" />
				</div>
			</div>
		
			<div class="form-group">
				<label for="senhaFinanceira" class="col-sm-2 control-label">Senha
					financeira</label>
				<div class="col-sm-10">
					<form:password class="form-control width400" path="senhaFinanceira"
						id="senhaFinanceira" maxlength="60" autocomplete="off" />
					<form:errors path="senhaFinanceira" cssClass="tooltip-erro" />
				</div>
			</div>
		
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a href="#" class="btn btn-primary" onclick="completar();">
						<span class="glyphicon glyphicon-usd" aria-hidden="true"></span>
						Completar
					</a>
				</div>
			</div>
		
			<div id="confirma-modal" class="modal" tabindex="-1" role="dialog">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Confirmação de Saque</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p id="taxavalor"></p>
						</div>
						<div class="modal-footer">
							<a href="#" class="btn btn-primary" onclick="$('#confirma-modal').modal('toggle');post('saque', $('#formSaque').serialize());">
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
	valor.focus();
	function completar() {
		$.ajax({
			type : "POST",
			url : "calculataxasaque",
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
