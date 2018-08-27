<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

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
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/compras.css" />">

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
	<script> 
		$(document).ready(function(){
			$( ".go-click" ).click(function() {
				
				window.location.href="upgradedetalhe?upgrade="+$(this).attr('id');
			});
		})
	
	</script>
	<body class="${skinUser} sidebar-mini">
	
		<div class="wrapper">
			<jsp:include page="message.jsp" />
      		<jsp:include page="header.jsp" />
      		<jsp:include page="menu.jsp" />
      	
			<div class="content-wrapper">
				<section class="content">
					
					<div class="box box-primary">
						
						<div class="box-header">
				            <h3 class="box-title inboxTitle">Upgrade</h3>
				         </div>
						<div class="box-body">
							<div class="row">
								<c:forEach var="item" items="${lista}">
									<div class="col-sm-12 col-md-6 col-lg-4">
										  <div class="thumbnail " style="text-align:center; box-shadow:0 1px 1px 0 rgba(0,0,0,.4); margin: 10px;">
										  	<div class="caption">
										  	
											  	<c:choose>
												    <c:when test="${empty item.imageurl}">
												    	<i class="icon-large fa fa-arrow-circle-o-up"></i>
												        <br />
												    </c:when>  
												    <c:otherwise>
												    	<img src="${item.imageurl}" class="img-fluid image-width-height go-click"  id="${item.upgrade}" alt="Responsive image" style="max-width: 50%; max-height: 150px;" >
												        <br />
												    </c:otherwise>
												</c:choose>
										  	
												 <a href="upgradedetalhe?upgrade=${item.upgrade}">
										         	 <h3 class="text-center">${item.nome}</h3> 
										         </a>
										         
										         <div class="divmin">
										        	 <p class="text-center">
											        	${item.shortdescription}
											        </p>
										         </div>
										         
										    </div>
										    
									           	<div class="button-group">
						                           <div class="pull-center">
						                               <a href="precompraupgrade?pacoteUpgradeId=${item.id}" class="btn btn-success">
															<i class="fa fa-usd"></i> <span><b>COMPRAR</b></span>
													   </a>
													   <a href="upgradedetalhe?upgrade=${item.upgrade}" class="btn btn-info">
															<i class="glyphicon glyphicon-search"></i> <span><b>VER MAIS&nbsp;</b></span>
													   </a>
						                           </div>
						                    	</div>
						                    	
						                    	<div class="button-group">
						                          
						                    	</div>
								    	</div>
									</div>
								</c:forEach>
							</div>
						</div>	
					</div>							
				</section>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>
	</body>
</html>