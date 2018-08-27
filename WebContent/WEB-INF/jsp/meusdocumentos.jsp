<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="meusdadosmenu.jsp" />

<br>

	<jsp:include page="message.jsp" />
	
	<table class="table table-bordered table-striped">
		
		<tr>
			<th>Documentos</th>
			<th width="50px"></th>
		</tr>

		<c:forEach var="item" items="${arquivos}">
			<tr>
				<td>${item.name}</td>
				<td>
					<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalRemove${item.id}">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
					</button>

					<div id="modalRemove${item.id}" class="modal fade" role="dialog">
					  	<div class="modal-dialog">
					    	<div class="modal-content">
					      		<div class="modal-header">
					        		<button type="button" class="close" data-dismiss="modal">&times;</button>
					        		<h4 class="modal-title">Excluindo documento</h4>
					      		</div>
					      		
					      		<div class="modal-body">
					        		<p>Deseja realmente excluir o documento: <b>${item.name}</b>?</p>
					      		</div>
					      
					      		<div class="modal-footer">
					      			<a href="#" onclick="removeDocumento('${item.id}');" class="btn btn-danger">
										<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
										Sim
									</a>
					        		<button type="button" class="btn btn-default" data-dismiss="modal">Não</button>
					      		</div>
					    	</div>
					  	</div>
					</div>
				
				</td>
			</tr>
		</c:forEach>
			
	</table>
	<br>
	<form class="form-horizontal" method="POST" action="uploaddocumento" enctype="multipart/form-data" onsubmit="return false;" id="formUpload">
        <div class="form-group">
			<div class="col-sm-offset-2 col-sm-9">
				<input type="file" name="file">
			</div>
		</div>
        
        <div class="form-group">
			<div class="col-sm-offset-2 col-sm-9">
				<a href="#" class="btn btn-primary" onclick="postFormUpload();">
					Enviar documento
				</a>
			</div>
		</div>
    </form>
	

<script>
	function postFormUpload() {
		
		var form = $('form')[0];
		var formData = new FormData(form);
		
		$.ajax({dataType : 'json',
      			url : "uploaddocumento",
      			data : formData,
      			type : "POST",
      			enctype: 'multipart/form-data',
      			processData: false,
      			contentType:false,
      			error : function(result) {
					if (result.responseText == '') {
						get('meusdocumentos');
					}
					else {
						alert(result.responseText);
					}
      			}
		});
	}
	
	function removeDocumento(id) {
		$('#modalRemove' + id).modal('hide');
		$('.modal-backdrop').addClass('hidden');
		get('removemeudocumento/' + id);
	}
</script>