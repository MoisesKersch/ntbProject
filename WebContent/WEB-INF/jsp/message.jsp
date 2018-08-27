<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty messageType}">
	
	<!-- ERRO -->
	<c:if test="${messageType == 0}">	
		<script type="text/javascript">
			$.bootstrapGrowl("<h4 class='message-title'><c:out value="${messageTitle}"/></h4><h4 class='message-detail'>${messageDetail}</h4>", {
				ele: 'body',
			  	type: 'danger', // (null, 'info', 'error', 'success')
			  	offset: {from: 'top', amount: 40},
			  	align: 'right',
			  	delay: 6000,
			  	allow_dismiss: true,
			  	width: 262,
			  	stackup_spacing: 10
			});
	   	</script>
	</c:if>
	
	<!-- INFO -->
	
	<c:if test="${messageType == 2}">	    
	    <script type="text/javascript">
	    	$.bootstrapGrowl("<h4 class='message-title'><c:out value="${messageTitle}"/></h4><h4 class='message-detail'>${messageDetail}</h4>", {
				ele: 'body',
			  	type: 'info', // (null, 'info', 'error', 'success')
			  	offset: {from: 'top', amount: 40},
			  	align: 'right',
			  	delay: 6000,
			  	allow_dismiss: true,
			  	width: 262,
			  	stackup_spacing: 10
			});
	   	</script>
	</c:if>

	<!-- SUCESSO -->
	
	<c:if test="${messageType == 3}">	    
	    <script type="text/javascript">
	    	$.bootstrapGrowl("<h4 class='message-title'><c:out value="${messageTitle}"/></h4><h4 class='message-detail'>${messageDetail}</h4>", {
				ele: 'body',
			  	type: 'success', // (null, 'info', 'error', 'success')
			  	offset: {from: 'top', amount: 40},
			  	align: 'right',
			  	delay: 6000,
			  	allow_dismiss: true,
			  	width: 262,
			  	stackup_spacing: 10
			});
	   	</script>
	</c:if>
	
	
</c:if>

<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>