<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="form-group">
	<button type="button" name="action" class="btn btn-default" value="entrar" onclick="recarrega('${usuario}');">
		<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
		Recomeçar
	</button>
</div>

<div class="input-group width300">
	<input id="pesquisa" type="text" class="form-control width300" placeholder="Buscar por login ou ID...">
    <span class="input-group-btn">
    	<a href="#" onclick="carregaPorLogin();" class="btn btn-default" id="buttonPesquisa">
    		<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
    	</a>
  	</span>
</div>

<div id="treeDiv" class="col-sm-6">
	<img id="imageLoading" src="<c:url value="/resources/img/carregando.gif" />">
</div>

<c:url var="carregaArvoreBinariaURL" value="desenhaarvorebinaria" />
<script type="text/javascript">
	var myTree;
	$(function(){
		myTree = $("#treeDiv").btree()[0];
	});
	
	function redraw() {		
		myTree.clear();
		
		myTree = $("#treeDiv").btree({
			branchColor: '#033857',
			branchStroke: 3,
			hSpace: 0,
			vSpace: 150,
			borderWidth: 0,
			horizontal: false,
			flip: true
		})[0];
	}

	$(document).ready(
		function() {
			recarrega('${usuario}');
	});
	
	function recarrega(id) {

		if(id != null && id != '') {

			$.getJSON('${carregaArvoreBinariaURL}', {
				ajax : 'true',
				id: id
			},
			
			function(data) {
				var html = '';
				var len = data.length;
				for (var i = 0; i < len; i++) {
					html += '<div class="box-rb tooltip-class" style="border-style: inset; cursor: pointer;" title="' + data[i].tooltip + '" onclick="recarrega(' + data[i].adUserId + ');">';
	
					if(data[i].icone == null)
						html += '<img src="<c:url value="/resources/img/icon-pessoa-null.png" />" alt="' + data[i].username + '" class="img-thumbnail-icon-binario">';
					else
						html += '<img src="' + data[i].icone + '" alt="' + data[i].username + '" class="img-thumbnail-icon-binario">';
	
					if(data[i].ultimoNivel == true)
						html+='<br><span class="icon-info diagonal tip">' + data[i].username + '</span></div>';
					else
						html+='<br><span class="icon-info">' + data[i].username + '</span></div>';
				}
				
				$('#treeDiv').html(html);	
				$('.tooltip-class').tooltip({
					tooltipClass: "passwordTooltip"
				});
				setTimeout("window.location='login'",600000);
				redraw();
			});
		}
		
	}
	

	$(document).ready(function() {
		$('#pesquisa').change(
			function() {
				carregaPorLogin();
			}
		);
		
	});
	
	function carregaPorLogin() {		

		$.getJSON('buscaarvorebinaria', {
			ajax : 'true',
			login: $("#pesquisa").val()
		},		
		function(data) {
			var html = '';
			var len = data.length;
			for (var i = 0; i < len; i++) {
				html += '<div class="box-rb" title="' + data[i].tooltip + '" onclick="recarrega(' + data[i].adUserId + ');">';

				if(data[i].icone == null)
					html += '<img src="<c:url value="/resources/img/icon-pessoa-null.png" />" alt="' + data[i].username + '" class="img-thumbnail-icon-binario">';
				else
					html += '<img src="' + data[i].icone + '" alt="' + data[i].username + '" class="img-thumbnail-icon-binario">';

				if(data[i].ultimoNivel == true)
					html+='<br><span class="icon-info diagonal">' + data[i].username + '</span></div>';
				else
					html+='<br><span class="icon-info">' + data[i].username + '</span></div>';
			}
			
			$('#treeDiv').html(html);	
			redraw();
			$("#pesquisa").focus();
		});
	}
</script>

<script>
	$("#pesquisa").focus();
</script>