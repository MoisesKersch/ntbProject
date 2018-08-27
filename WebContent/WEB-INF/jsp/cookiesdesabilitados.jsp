<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>Back Office</title>

		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		
		<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
    	<script src="<c:url value="/resources/plugins/bootstrap/js/bootstrap.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
	</head>
	
	<body class="login-page">	
    	<div class="login-box">
       		<h1>COOKIES DESABILITADOS</h1>
      		
      		<div class="login-box-body">
      		    Os cookies do navegador estão desabilitados, para navegar no backoffice você precisa habilitados.
      		    <p>Abaixo estão os links explicando como deve ser feito esse procedimento para alguns navegadores.</p>
				
				<a href="https://support.google.com/accounts/answer/61416?hl=pt-BR" target="_blank" class="btn btn-white btn-block btn-flat">
					<b>Google Chrome</b>
				</a>
				<br>
				<a href="https://support.mozilla.org/pt-BR/kb/sites-dizem-que-cookies-estao-bloqueados-como-desb" target="_blank" class="btn btn-white btn-block btn-flat">
					<b>Firefox</b>
				</a>
				<br>
				<br>
				Após desbloquear os cookies clique 		 
				<a href="login" class="btn btn-white btn-block btn-flat">
					AQUI
				</a>
				para entrar no sistema
 				
			</div>
    	</div>
       	
		<script>
			$("#email").focus();	
		</script>
	</body>

</html>