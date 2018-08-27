<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="box box-danger">
	<div class="box-header">
    	<h3 class="box-title inboxTitle">Notícias</h3>
    </div>
	
	<div class="box-body">

		<table class="table table-bordered table-striped">
			<c:forEach var="item" items="${noticias}" varStatus="row">
				<tr>
					<td width="20px"><small>${row.count}</small></td>
					<td>${item.note}</td>
				</tr>
			</c:forEach>									
		</table>

	</div>
</div>