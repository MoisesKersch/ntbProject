<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>Back Office</title>

		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/bootstrap-theme.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/bootstrap.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquery-ui.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquerysctipttop.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
		
		<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/script.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery-ui.js" />" ></script>
       	<script src="<c:url value="/resources/js/angular-1.0.1.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/custom.js" />" ></script>

	</head>
	
	<body>
      				
		<div class="container-fluid" style="margin: 10px;">
			<div class="panel panel-default">
		  		<div class="panel-body">
		  		
		  			<div class="login-logo text-center">
		        		<a href="../" target="_blank">
		        			<img width="300px" src="resources/img/logo-home.png"/>
		        		</a>
		      		</div>
		  			<br>
		  			<div class="text-center">
<!-- 		  				<a href="http://www.newtimebrazil.com" target="_blank">www.newtimebrazil.com</a> -->
		  				<h1>${titulo}</h1>
		  				<h1>
		  					<i class="glyphicon glyphicon-cog"></i>
		  					<i class="glyphicon glyphicon-cog"></i>
		  					<i class="glyphicon glyphicon-cog"></i>
		  				</h1>
		  				${descricao}
		  			</div>
		  		</div>
			</div>				
		</div>			

	</body>

</html>