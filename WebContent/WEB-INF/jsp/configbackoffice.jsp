<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="meusdadosmenu.jsp" />

<br>
	<jsp:include page="message.jsp" />
	
<form id="formNovoCadastro" name="formNovoCadastro" class="form-horizontal" action="salvaconfigbackoffice" method="post" role="form" onsubmit="return false;">
			
	<div class="form-group">
   		<label for="banco" class="col-sm-2 control-label">Tema:</label>
   		<div class="col-sm-10">
   			<select id="skin" class="form-control width400">
			  	<option value="skin-blue">Azul</option>
			  	<option value="skin-blue-light">Azul e branco</option>
			  	<option value="skin-black">Preto</option>
			  	<option value="skin-black-light">Preto e branco</option>
			  	<option value="skin-green">Verde</option>
			  	<option value="skin-green-light">Verde e branco</option>
			  	<option value="skin-purple">Roxo</option>
			  	<option value="skin-purple-light">Roxo e branco</option>
			  	<option value="skin-red">Vermelho</option>
			  	<option value="skin-red-light">Vermelho e branco</option>
			  	<option value="skin-yellow">Laranja</option>
			  	<option value="skin-yellow-light">Laranja e branco</option>
			</select>
   		</div>
 	</div>
 	
 	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<a href="#" class="btn btn-primary" onclick="salvaConfiguracao();">
				<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
				Salvar
			</a>
		</div>
	</div>	
</form>	 

<script>
	function salvaConfiguracao() {
		$('body').removeClass('${usuario.skin}');
		var skin = $('#skin').val();
		get('salvaconfigbackoffice?skin=' + skin);
	}
	
	$('body').addClass('${usuario.skin}');
	$('#skin').val('${usuario.skin}');
</script>