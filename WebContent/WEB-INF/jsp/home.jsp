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
    	
    	<!-- Latest compiled and minified CSS -->
<!-- 		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->

		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquery-ui.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/home.css" />">
		
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
		
		
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
		<script src="<c:url value="/resources/js/jquery-ui.js" />" ></script>
		
		<!-- Home js -->
		<script src="<c:url value="/resources/js/home.js" />" ></script>
		
	</head>
	<body class="${skinUser} sidebar-mini">
	
		<div class="wrapper">
			<jsp:include page="message.jsp" />
      		<jsp:include page="header.jsp" />
      		<jsp:include page="menu.jsp" />
			
			<div class="content-wrapper">
				<section class="content-header">
				
				</section>
				<section class="content" style="height:100%;">
					<c:if test="${readonly != 'Y'}">
						<c:if test="${mostrarNoticias == 'Y'}">
							<jsp:include page="home_mural.jsp" />
						</c:if>
						<div class="row">
	          				<jsp:include page="bonus-incentivo-rede.jsp" />
	          				<jsp:include page="qualificacao-rede.jsp" /> 
	          				<jsp:include page="indice-pagamento-diretos.jsp" />  
	          				<jsp:include page="contas-ativa-rede.jsp" />  
	          				<jsp:include page="home_statusativo.jsp" /> 
	          			</div>
	          			<div class="row">
	           				<jsp:include page="home_graduacao.jsp" />
	           				<jsp:include page="home_binario.jsp" />
	           				<jsp:include page="home_crescimento.jsp" />
	           				<jsp:include page="home_video.jsp" />
						</div>
						<jsp:include page="home_banners.jsp" />
					</c:if>
				</section>
		  	</div>
			<jsp:include page="footer.jsp" />
    	</div>

    	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />" ></script>
    	<script src="<c:url value="/resources/js/demo.js" />" ></script>
    	
       	<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/script.js" />" ></script>
       	
       	<script src="<c:url value="/resources/js/angular-1.0.1.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/custom.js" />" ></script>
       	<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/app.min.js" />" ></script>
       	<script>
		  	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		
		  	ga('create', 'UA-67737982-2', 'auto');
			ga('send', 'pageview');
		</script>
  	</body>
</html>