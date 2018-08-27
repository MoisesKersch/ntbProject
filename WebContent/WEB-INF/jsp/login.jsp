<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>Back Office</title>

    	<link rel="stylesheet" type="text/css" media="screen" href="resources/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="resources/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="resources/css/AdminLTE.css">
		<link rel="stylesheet" type="text/css" media="screen" href="resources/css/style.css">
		<link rel="stylesheet" type="text/css" media="screen" href="resources/css/skins/_all-skins.min.css">

		<script src="resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    	<script src="resources/plugins/bootstrap/js/bootstrap.min.js"></script>
    	<script src="resources/js/jquery.bootstrap-growl.js"></script>
    	<script src="resources/js/initial.js"></script>
	</head>
	
	<body class="login-page">	
    	<div class="login-box">
      		<div class="login-logo">
        		<a href="#">
        			<img width="300px" src="resources/img/logo-home.png"/>
        		</a>
      		</div>
      		
      		<div class="login-box-body">
      			
      			<script>
		        	if (!navigator.cookieEnabled) {
		        		window.location.href = 'cookiesdesabilitados'; 		        		
		        	}
		        </script>
      			
				<form:form action="login" method="post" commandName="login" role="form">
					<jsp:include page="message.jsp" />
					
					<div class="form-group has-feedback">
            			<form:input class="form-control" path="login" id="email" name="email" maxlength="120" placeholder="Login"/>
			    		<form:errors path="login" cssClass="tooltip-erro"/>
            			<!-- <span class="glyphicon glyphicon-envelope form-control-feedback"></span> -->
          			</div>
			  		
			  		<div class="form-group has-feedback">
			            <form:password class="form-control" path="senha" id="senha" maxlength="120" placeholder="Senha"/>
			    		<form:errors path="senha" cssClass="tooltip-erro"/>
			            <!-- <span class="glyphicon glyphicon-lock form-control-feedback"></span> -->
			        </div>
			        			        				        
			        <div class="row">
            			<div class="col-xs-12">    
              				<button type="submit" name="action" class="btn btn-primary btn-block btn-flat" value="entrar">
								Entrar
							</button>                     
            			</div>
          			</div>
          							    
				</form:form>
				
				<br>				 
				<a href="esqueciminhasenha" class="btn btn-default btn-block btn-flat" style="text-align: left;">
					<i class="fa fa-envelope-o" aria-hidden="true"></i> Esqueci minha senha
				</a>
        		<a href="adesao" class="btn btn-primary btn-block btn-flat" style="text-align: left;">
        			<i class="fa fa-plus" aria-hidden="true"></i> Nova adesão
				</a>
			</div>			
		</div>       

		<script>
			$("#email").focus();	
		</script>
	</body>

</html>