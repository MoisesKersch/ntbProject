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
				<form:form action="esqueciminhasenha" method="post" commandName="login" role="form">
					
					<form:hidden path="senha"/>
					<jsp:include page="message.jsp" />
					  		
					<div class="form-group has-feedback">
            			<form:input class="form-control" path="login" id="email" name="email" maxlength="120" placeholder="Login"/>
			    		<form:errors path="login" cssClass="tooltip-erro"/>
            			<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          			</div>

			        <div class="row">
            			<div class="col-xs-6">
              				<button type="submit" name="action" class="btn btn-primary btn-block btn-flat" value="redefinir">
								<i class="fa fa-envelope-o" aria-hidden="true"></i> Redefinir
							</button>                     
            			</div>
            			
            			<div class="col-xs-6">
              				<a href="home" class="btn btn-default btn-block btn-flat">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								Cancelar
							</a>                     
            			</div>
          			</div>
				</form:form>
			</div>
    	</div>

		<script>
			$("#email").focus();	
		</script>
    
	</body>
</html>