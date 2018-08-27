<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${agenda.periodo != null}">
	<form:form id="formNovo" name="formNovo" class="form-horizontal" action="ativonovo" method="post" commandName="agenda" role="form">
		<form:hidden path="mProductId"/>
		
		<div class="box ativo-box">
			<div class="box-header">
				<h3 class="box-title">
					${agenda.periodo.nome} <small><fmt:formatDate value="${agenda.periodo.dataProgramada}" pattern="dd/MM/yyyy" /></small>
				</h3>
		
				<div class="pull-right box-tools circle">
					<button data-original-title="Collapse" class="btn btn-sm" data-widget="collapse" data-toggle="tooltip" title="">
						<i class="fa fa-minus"></i>
					</button>
				</div>
			</div>

			<div style="display: block;" class="box-body pad">			
				<div class="pull-left ativo-box-imagem text-center">
					<img id="imgNovo" src="" alt="" class="" height="70px">
				</div>
				<small><b>Período:</b> de </small><fmt:formatDate value="${agenda.periodo.dataInicio}" pattern="dd/MM/yyyy" /> <small>até </small> <fmt:formatDate value="${agenda.periodo.dataFinal}" pattern="dd/MM/yyyy" />
				<br>
				<small><b>Produto:</b></small>
				<select id="produtoNovo"></select>
				<br><br>
				<button type="submit" class="btn btn-sm btn-primary">
					Salvar
				</button>
			</div>
		</div>
	</form:form>
</c:if>

<c:forEach var="item" items="${lista}">

	<div class="box ativo-box collapsed-box">
		<div class="box-header">
			<div class="pull-left box-tools circle">
				<button data-original-title="Collapse" class="btn btn-sm" data-widget="collapse" data-toggle="tooltip" title="">
					<i class="fa fa-plus"></i>
				</button>
			</div>
	
			<h3 class="box-title">
				${item.periodo.nome} <small><fmt:formatDate value="${item.periodo.dataProgramada}" pattern="dd/MM/yyyy" /></small>
			</h3>
			
		</div>
	
		<div style="display: none;" class="box-body pad">		
			<div class="pull-left ativo-box-imagem text-center">
				<img id="imgProduto${item.id}" src="" alt="" class="" height="70px">
			</div>
			
			<small><b>Período:</b> de </small><fmt:formatDate value="${item.periodo.dataInicio}" pattern="dd/MM/yyyy" /> <small>até </small> <fmt:formatDate value="${item.periodo.dataFinal}" pattern="dd/MM/yyyy" />
			<br>
			
			<c:if test="${item.pedido == null}">
				<small><b>Produto:</b></small>
				<select id="produto${item.id}" class="select-ativos">${item.mProductId}</select>				
				<br><br>
				<a href="#" onclick="atualizaAtivo('${item.id}');" class="btn btn-sm btn-primary">
					Salvar
				</a>
			</c:if>
			
			<c:if test="${item.pedido != null}">
				<small><b>Pedido:</b></small>  <b class="text-blue">${item.pedido.numeroDocumento}</b>
				<br>
				<small><b>Produto:</b></small>  <b class="nomes-ativos text-blue" id="nomeProduto${item.id}">${item.mProductId}</b>
			</c:if>
						
		</div>
	</div>

</c:forEach>

<div id="imagens"></div>
<div id="divReload"></div>

<c:url var="listaAtivosURL" value="/listaativos" />
<script>
	
	$(document).ready(
		function() {

			$('#produtoNovo').change(
				function() {
					$('#mProductId').val($('#produtoNovo').val());
					$('#imgNovo').attr('src', $('#img' + $('#produtoNovo').val()).val());
				}
			);
			
			$('.select-ativos').change(
				function() {
					var id = this.id.replace('produto', '');
					var value = $('#' + this.id).val();
					$('#imgProduto' + id).attr('src', $('#img' + value).val());
				}
			);
			
			$.getJSON('${listaAtivosURL}', {
				ajax : 'true'
			}, 

			function(data) {
				var html = '';
				var img = '<input type="hidden" id="img" value=""/>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					img += '<input type="hidden" id="img' + data[i].id + '" value="' + data[i].imageURL + '"/>';
					img += '<input type="hidden" id="idProduto' + data[i].id + '" value="' + data[i].nome + '"/>';
					html += '<option value="' + data[i].id + '">' + data[i].nome + '</option>';
				}
				$('#produtoNovo').html(html);
				$('#imagens').html(img);
				
				$.each($('.select-ativos'), function() {
					var id = $(this).html();
					$(this).html(html);
					$(this).val(id);
					
					var id2 = $(this).attr('id').replace('produto', '');
					$('#imgProduto' + id2).attr('src', $('#img' + id).val());
				});
				
				$.each($('.nomes-ativos'), function() {
					var id = $(this).html();
					$(this).html($('#idProduto' + id).val());
					
					var id2 = $(this).attr('id').replace('nomeProduto', '');
					$('#imgProduto' + id2).attr('src', $('#img' + id).val());
				});
				
				$('#mProductId').val($('#produtoNovo').val());
				$('#imgNovo').attr('src', $('#img' + $('#produtoNovo').val()).val());
			});
	});
	
	function atualizaAtivo(id) {
		
		var mProductId = $('#produto' + id).val();
		
		$.post('ativoatualiza', {id: id, mProductId: mProductId}, function(resposta) {
			$("#divReload").html(resposta);
		});
	}
</script>