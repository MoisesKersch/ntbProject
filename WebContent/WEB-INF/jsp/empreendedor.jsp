<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>BackOffice</title>

		
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
				
		<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
    	<script>
      		$.widget.bridge('uibutton', $.ui.button);
    	</script>
    	<script src="<c:url value="/resources/js/demo.js" />" ></script>
    	
       	<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/script.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery-ui.js" />" ></script>
       	<script src="<c:url value="/resources/js/custom.js" />" ></script>
       	<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/app.min.js" />" ></script>
	</head>
	<body class="${skinUser} sidebar-mini">
		<div class="wrapper">
			<jsp:include page="message.jsp" />
      		<jsp:include page="header.jsp" />
      		<jsp:include page="menu.jsp" />
      	
			<div class="content-wrapper">
				<section class="content">

					<div class="box box-primary">
		                <div class="box-header">
		                	<h3 class="box-title inboxTitle">Empreendedor</h3>
		                </div>

	                  	<div class="box-body">
	                  	
	                  		<div class="form-group row">
						   		<label class="col-sm-2 control-label text-right">ID:</label>
					   			<label class="col-sm-10 control-label">${franqueado.codigo}</label>
						 	</div>
							
							<div class="form-group row">
						   		<label for="nome" class="col-sm-2 control-label text-right">Nome:</label>
						   		<div class="col-sm-10">
									<input type="text" class="form-control width400" value="${franqueado.nome}" readonly/>
						   		</div>
						 	</div>
						 	
						 	<div class="form-group row">
						   		<label for="login" class="col-sm-2 control-label text-right">Login:</label>
						   		<div class="col-sm-10">
									<input type="text" class="form-control width400" value="${franqueado.login}" readonly/>
						   		</div>
						 	</div>
						 
						 	<div class="form-group row">
						   		<label for="url" class="col-sm-2 control-label text-right">Link para adesão: </label>
						   		<div class="col-sm-10">
									<a href="${franqueado.url}" target="_blank">${franqueado.url}</a>
						   		</div>
						 	</div>

						</div>
					
		            </div>
					
				</section>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>
	</body>
	
	<script src="&lt;?php echo $this-&gt;webroot;?&gt;js/jquery-1.10.2.min.js"></script>
		<script src="&lt;?php echo $this-&gt;webroot;?&gt;js/bootstrap.min.js"></script>
		<script>
		    $('.sectionLeft').affix();
	</script>

</html>