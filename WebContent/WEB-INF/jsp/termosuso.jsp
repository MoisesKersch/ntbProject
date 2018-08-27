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
      		<div class="login-logo">
        		<a href="#">
        			<img width="300px" src="<c:url value="/resources/img/logo-home.png" />"/>
        		</a>
      		</div>
      
      		<div class="login-box-body">
  		
				<div class="text-center">
					<p class="login-box-msg">Para continuar é necessario aceitar os termos de uso</p>
					<a href="<c:url value="/resources/termos_de_uso.pdf" />" class="btn btn-default" target="_blank">
						<span class="glyphicon glyphicon-download" aria-hidden="true"></span>
						Download termos de uso
					</a>
					<p><br>				
					<input type="checkbox" onchange="exibeButtonAceito(this.checked);"/>Aceito os termos de uso	
					<p><br>
					<a href="aceite" class="btn btn-primary hidden" id="linkAceite">
						Aceito
					</a>
					<p>
	        		<a href="logout" class="btn btn-danger">
						Sair
					</a>
				</div>
												
			</div>
    	</div>
    	
    	<script>
	    	function exibeButtonAceito(checked) {
				if(checked) {
					$('#linkAceite').removeClass("hidden");
				}
				else {
					$('#linkAceite').addClass("hidden");
				}
			}
    	</script>
	</body>
</html>