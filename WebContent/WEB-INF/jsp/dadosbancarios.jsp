<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="meusdadosmenu.jsp" />
	
<jsp:include page="message.jsp" />

<br>

<form:form id="formNovoCadastro" name="formNovoCadastro" class="form-horizontal" action="dadosbancarios" method="post" commandName="dados" role="form">
	
	<form:hidden path="cBPartnerId"/>
	<form:hidden path="adUserId"/>
			
	<div class="form-group">
   		<label for="banco" class="col-sm-2 control-label">Banco:</label>
   		<div class="col-sm-10">
   			<form:select class="form-control width400" path="banco" id="banco">
   			</form:select>
   			<form:errors path="banco" cssClass="tooltip-erro"/>
   		</div>
 		</div>
 		
 		<div id="divOperacao" class="form-group hidden">
   			<label for="conta" class="col-sm-2 control-label">Operação:</label>
   			<div class="col-sm-10">
				<form:input class="form-control width200" path="operacao" id="operacao" maxlength="10"/>
   				<form:errors path="operacao" cssClass="tooltip-erro"/>
   			</div>
   		</div>
 		
 		<div class="form-group">
   		<label for="agencia" class="col-sm-2 control-label">Agência:</label>
   		<div class="col-sm-10">
			<form:input class="form-control width400" path="agencia" id="agencia" maxlength="10"/>
   			<form:errors path="agencia" cssClass="tooltip-erro"/>
   		</div>
 		</div>
 		
 		<div class="form-group">
   		<label for="conta" class="col-sm-2 control-label">Conta:</label>
   		<div class="col-sm-10">
			<form:input class="form-control width200" path="conta" id="conta" maxlength="10"/>
   			<form:errors path="conta" cssClass="tooltip-erro"/>
   		</div>
   	</div>
   	
   	<div class="form-group">
   		<label for="contaDV" class="col-sm-2 control-label">DV:</label>
   		<div class="col-sm-10">
   			<form:input class="form-control width50" path="contaDV" id="contaDV" maxlength="2"/>
   			<form:errors path="contaDV" cssClass="tooltip-erro"/>
   		</div>
 	</div>
 	
 	<div class="form-group">
   		<label for="senhaFinanceira" class="col-sm-2 control-label">Senha financeira</label>
   		<div class="col-sm-10">
   			<form:password class="form-control width400" path="senhaFinanceira" id="senhaFinanceira" maxlength="60"/>
   			<form:errors path="senhaFinanceira" cssClass="tooltip-erro"/>
   		</div>
 	</div>
 		
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<a href="#" class="btn btn-primary" onclick="salva();">
				<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
				Salvar
			</a>
		</div>
	</div>

</form:form>

<div id="divInputs"></div>

<span id="helpBlock" class="help-block col-sm-offset-2">
	<b>ATENÇÃO!</b> Somente serão pagos saques em contas de sua titularidade.
</span>

<c:url var="listaBancosURL" value="/lista_bancos" />
<script>
	$(document).ready(
		function() {
			$.getJSON("${listaBancosURL}", {  
				ajax : 'true'
			},

			function(data) {
				var itens = '<option value="" selected="selected"></option>';
				var inputs = '';
			    $.each(data, function(key) {
			    	itens += '<option value="' + data[key].id + '">' + data[key].nome + '</option>';
			    	inputs += '<input type="hidden" id="bancoSolicitaOperacao' + data[key].id + '" value="' + data[key].solicitaOperacao + '"/>';
			    });
			    $("#divInputs").html(inputs);
			    $("#banco").html(itens);  
			    $('#banco').val('${dados.banco}');
			    solicitaOperacao('${dados.banco}');
				$('#banco').focus();
	    	});
			
			$('#banco').change(
				function() {
					solicitaOperacao(this.value);
				});
	});
	
	function solicitaOperacao(id) {
		var solicita = $('#bancoSolicitaOperacao' + id).val();
		if(solicita != 'Y') {
			if(!$('#divOperacao').hasClass('hidden'))
				$('#divOperacao').addClass('hidden');
		}
		else {
			$('#divOperacao').removeClass('hidden');
		}
	}
	
	function salva() {
		if($('#divOperacao').hasClass('hidden'))
			$('#operacao').val('');
		
		post('dadosbancarios', $('#formNovoCadastro').serialize());
	}
</script>