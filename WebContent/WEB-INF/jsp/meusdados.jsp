<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>BackOffice</title>

		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		
		
		<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/initial.js" />" ></script>
    	
    	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.12/css/all.css" integrity="sha384-G0fIWCsCzJIMAVNQPfjH08cyYaUtMwjJwqiRKxxE/rx96Uroj1BtIQ6MLJuheaO9" crossorigin="anonymous">
		

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
       	
       	<script src="resources/js/adesao.js" ></script>  	
       	
       	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquerysctipttop.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/iCheck/square/blue.css" />">
		
		  	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
  	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>

		<script>
			$(function() {
				$(".datepicker").datepicker();
				$(".data").mask("99/99/9999");
				$(".cep").mask("99999-999");
				$(".cpf").mask("999.999.999-99");
				$(".cnpj").mask("99.999.999/9999-99");
			    $(document).tooltip();
            });
		</script>
		
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
				            <h3 class="box-title inboxTitle">Meus Dados</h3>
				         </div>					
						<div id="divContent" class="box-body">
							<c:if test="${go == null}">
		  						<jsp:include page="meucadastro.jsp" />
		  					</c:if>
						</div>
					</div>
				</section>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>
    							
	</body>
	
	<c:if test="${go != null && go != ''}">
		<script>
			get('${go}');
		</script>
	</c:if>
</html>