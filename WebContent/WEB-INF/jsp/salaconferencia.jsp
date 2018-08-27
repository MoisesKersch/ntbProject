<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
				
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
		                	<h3 class="box-title">Sala de conferência</h3>
		                </div>

	                  	<div class="box-body">
		  					<span id="helpBlock" class="help-block">
								<p>Treinamentos e Conferências oficiais serão previamente marcados através desta ferramenta!
								<br>
								<p>Fique atento!
								<br>
								<p>Para acessar a sala de conferência clique no link a baixo.
								<br>
								<p>Não é necessário SENHA!
								<br>
								<p>Coloque no campo "seu nome" o seu nome e o nome de quem lhe convidou.
								<br>
								<p>Ex. Seu nome é João Antunes e quem lhe convidou é Marcio da Silva
								<p>Escreva assim... (João Antunes - C - Marcio da Silva).
								<br>
								<p>Click em OK e aproveite a conferência ou treinamento!								
							</span>
	  						<div class="col-sm-offset-2">		
			  					<a class="btn btn-default" target="_blank" href="http://login.hotconference.net.br/conference,newtimebrazil">
			  						<i class="glyphicon glyphicon-comment" aria-hidden="true"></i> <span>Entrar na sala de conferências</span>
			  					</a>
		  					</div>
		  					  					
		  				</div>
					
		            </div>
					
				</section>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>

	</body>
	
</html>