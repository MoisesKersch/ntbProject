<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html lang="en_US">
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
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/font-awesome-4.5.0/css/font-awesome.min.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/compras.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/balloon.css" />">
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/main-layout.css" />">
		
		<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />" ></script>
    
    	<script src="<c:url value="/resources/js/demo.js" />" ></script>
    	
       	<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" ></script>
       	<script src="<c:url value="/resources/js/script.js" />" ></script>
       	<script src="<c:url value="/resources/js/jquery-ui.js" />" ></script>
       	<script src="<c:url value="/resources/js/custom.js" />" >
       	
       
       	</script>
       	<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js" />" ></script>
       	
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
		
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
		
		<script src="<c:url value="/resources/js/compras.js" />" ></script>
		
	</head>
	<body class="${skinUser} sidebar-mini">

		<div class="wrapper">
			<jsp:include page="message.jsp" />
      		<jsp:include page="header.jsp" />
      		<jsp:include page="menu.jsp" />
      		
			<div class="content-wrapper">
				<section class="content">
				
					<c:if test="${permitido}">
						<jsp:include page="comprascart.jsp" />
					</c:if>
					
					<c:if test="${!permitido}">
						<div class="box ativo-box informacao">
							<div class="box-header">
								<h3 class="box-title">Alerta!</h3>
							</div>
							<div class="box-body pad">
								<p>Não é permitido que você faça compras, porque não tem uma senha financeira cadastrada.</p>
								<p>Cadastre uma senha financeira clicando <a href="gosenhafinanceira">AQUI</a>.</p>
							</div>
						</div>						
					</c:if>		
					
					<div class="box box-primary">
						<div class="box-body">
							<div class="row">
								<c:if test="${permitido}">
									<div class="col-lg-12"> 
						          		<div class="col-lg-6" style="padding-left: 9px;">
						          			<div class="btn-group" style="margin-top: 3px;">
												<div class="btn-group">
												  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
												    Agrupar por <span class="caret"></span>
												  </button>
												  <ul class="dropdown-menu">
												    <li>
												   		<a href="compras?venda=${venda}" hreflang="en_US">TODOS</a>
													</li>
												    <c:forEach var="item" items="${categorias}">
									          			<li><a href="compras?categoria=${item.id}&venda=${venda}">
															${item.id}
														</a></li>
													</c:forEach>
												  </ul>
												</div>
												
												<select class="selectpicker" title="Ordenar por" id="ordenacao" name="ordenacao" onchange="ordena();">
												  <option value="novidades" selected>Novidades</option>
												  <option value="nome">Nome</option>
												   <option value="precoPor">Menor preço</option>
												  <option value="precoPor DESC">Maior preço</option>
												</select>
						
											</div>
										</div>
										
										<div class="col-lg-6" style="padding-right: 9px; padding-left: 9px;">
											<div class="input-group">
												<input type="text" class="form-control" id="texto" name="texto" maxlength="255" placeholder="Digite aqui o que deseja buscar.." onchange="buscaPorTexto();"
													   value="${texto}"/>
												<span class="input-group-addon">
													<a href="#" class="btn btn-sm btn-white" style="padding: 0px;" onclick="buscaPorTexto();">
														<i class="fa fa-search"></i>
													</a>
												</span>
						          			</div>
					          			</div>
										
										<script>
											function buscaPorTexto() {
												var valor = $('#texto').val();	
												window.location.href = 'compras?texto=' + valor + '&venda=${venda}';
											}
											
											function ordena(categoria) {
												var valor = $('#ordenacao').val();
													window.location.href = 'compras?ordem=' + valor + '&categoria=${categ}&venda=${venda}';  
													 							
											}
											
											$('#ordenacao').val('${ordem}');
											$('#texto').focus();
											$('#texto').select();
										</script>
									</div>
								</c:if>
								
								<hr>
								
								<c:forEach var="item" items="${lista}">
								  <div class="col-sm-12 col-md-6 col-lg-4">
								    <div class="thumbnail " style="text-align:center; box-shadow:0 1px 1px 0 rgba(0,0,0,.4); margin: 10px;">
								      <a href="comprasprodutodetalhes?id=${item.id}&venda=${venda}" data-toggle="tooltip" id="myTooltip">
								      
								         <c:choose>
										    <c:when test="${fn:startsWith(item.imagemURL, 'http://')}">
										         <img src="${item.imagemURL}" class="img-fluid imagemin-max" alt="Responsive image">
										        <br />
										    </c:when>  
										  
										    <c:otherwise>
										    <img src="resources/img/emptyimg.jpg" class="img-fluid imagemin-max" alt="Responsive image">
										        <br />
										    </c:otherwise>
										</c:choose>
										
								       <div class="caption">
									         <h4 class="text-center item-name">${item.nome}</h4>
									         </a>
									         <br>
									          <p class="text-center">
													${item.descricao}
									          </p>
								        </div>
								        
								      
								    	  <p style="color: #233e4e;">
								    	  	<c:if test="${not empty item.ptsBinario}">
								    	  		<strong> Pontos Binário: ${item.ptsBinario}</strong>
								    	  	</c:if>
								    	  <br>
								    	  	<c:if test="${not empty item.ptsCarreira}">
								    	 		 <strong> Pontos Carreira: ${item.ptsCarreira}</strong>
								    	 	 </c:if>
								    	  </p>
							    	  
							    	         <c:choose>
											    <c:when test="${item.precoDe != item.preco}">
													De
													<span id="precoDe${item.id}" class="label-price-de">
										      			<script>
										      				var preco = '${item.precoDe}'.replace('.', ',');
										      				$('#precoDe${item.id}').html('R$ ' + preco);
										      			</script>
										      		</span>
										      		<br>
										      		Por
													<span id="preco${item.id}" class="label-price">
										      			<script>
										      				var preco = '${item.preco}'.replace('.', ',');
										      				$('#preco${item.id}').html('R$ ' + preco);
										      			</script>
									      			</span>
											    </c:when>  
											    <c:otherwise>
											    	Por
													<script>
														var preco = '${item.precoDe}'.replace('.', ',');
														$('#precoDe${item.id}').html('R$ ' + preco);
													</script>
											    </c:otherwise>
											</c:choose>
											
					                      <span id="preco${item.id}" class="label-price text-center">
					                         <script>
					                             var preco = '${item.preco}'.replace('.', ',');
					                             $('#preco${item.id}').html('R$ ' + preco);
					                         </script>
					                     </span>
					                     
								        <br><br>
							           <div class="button-group">
				                           <span class="pull-center">
				                               <a href="#" onclick="adicionaProdutoAoCarinho('${item.id}','${venda}');" class="btn btn-success">
				                                   <i class="glyphicon glyphicon-shopping-cart"></i>
				                                   <span>ADICIONAR</span>
				                               </a>
				                               <a href="comprasprodutodetalhes?id=${item.id}&venda=${venda}" class="btn btn-info">
													<i class="glyphicon glyphicon-search"></i> <span><b>VER MAIS&nbsp;</b></span>
											  </a>
				                           </span>
				                       	</div>
								    </div>
								</div>
							</c:forEach>
						</div>
					</div>								
				</section>
			</div>
			<jsp:include page="footer.jsp" />
    	</div>
	</body>
</html>













