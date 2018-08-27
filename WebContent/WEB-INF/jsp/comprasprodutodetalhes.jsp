<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en_US">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>BackOffice</title>
	
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.min.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css" />">
	
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/plugins/font-awesome-4.5.0/css/font-awesome.min.css" />">
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/campeaovendas.css" />">
	
	<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
	<script src="<c:url value="/resources/js/demo.js" />"></script>
	
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>
	<script src="<c:url value="/resources/js/script.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
	<script src="<c:url value="/resources/js/custom.js" />"></script>

	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/AdminLTE.css" />">
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
	
	<!-- Latest compiled and minified Locales -->
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/locale/bootstrap-table-zh-CN.min.js"></script>
	
   	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/comprasprodutodetalhes.css" />">
   	<script src="<c:url value="/resources/js/comprasprodutodetalhes.js" />"></script>
   	
   	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/sliderstyle.css" />">
	<link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
	
	
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/lightbox.min.css" />">
	<script src="<c:url value="/resources/js/lightbox-plus-jquery.min.js" />" ></script>
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<script src="<c:url value="/resources/js/jquery.slidizle.js" />" ></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"></script>

	
	<script>
		jQuery(function($) 
		{
			$('[data-slidizle]').slidizle(
			{
				beforeChange : function(api) 
				{
		
				}
			});
		});
	</script>
	
	<style>
		#img-slide{
			background-image:url( ${produto.imagemURL} );
			max-width: 652px;
		}
	</style>
</head>

<body class="${skinUser} sidebar-mini">
		<div class="wrapper">
			<jsp:include page="message.jsp" />
      		<jsp:include page="header.jsp" />
      		<jsp:include page="menu.jsp" />
      		
      		<jsp:include page="comprascart.jsp" />
      		
			<div class="content-wrapper">
			
				<section class="content">
					<div class="row">
						<div class="col-lg-12">
							<div class="box box-primary">
								<div class="box-body">
									<div class="col-lg-6">
										<section class="sample" data-slidizle style="height: 640px">
											<ul class="slider-content" data-slidizle-content >
												 <li class="slider-item img-click-compras" id="img-slide">  
													<a class="example-image-link" href="${produto.imagemURL}" data-lightbox="example-set" data-title=""><img class="example-image" src="" alt=""/></a>
												</li> 
								
<!-- 												<li class="slider-item img-click-compras" style="background-image:url(resources/img/emptyimg.jpg)"> -->
<!-- 													 <a class="example-image-link" href="http://lokeshdhakar.com/projects/lightbox2/images/image-4.jpg" data-lightbox="example-set" data-title=""><img class="example-image" src="" alt="" /></a> -->
<!-- 												</li> -->
								
<!-- 												<li class="slider-item img-click-compras" style="background-image:url(resources/img/emptyimg.jpg)"> -->
												
<!-- 												</li> -->
											</ul>
										
											<div class="slider-next" data-slidizle-next>
												<i class="fa fa-angle-right font-size-left-right-compras-detalhes"></i>
											</div>
											
											<div class="slider-previous" data-slidizle-previous>
												<i class="fa fa-angle-left font-size-left-right-compras-detalhes"></i>
											</div>
								
											<ul class="slider-navigation" data-slidizle-navigation>
												<!-- automatically generated navigation -->
											</ul>
										</section>
									</div>
									
									<div class="col-lg-6" style="margin-bottom: 115px;">
										<h1 class="title-produto"> 
											${produto.nome}
										</h1>
											<hr>
										<p class="descr-product-info">
											${produto.descricaoCompleta}
										</p>
									</div>
									
									<div class="col-lg-6">
									<hr>
									
									<p>
							    	  	<c:if test="${not empty produto.ptsBinario}">
							    	  		<strong style="color: #233e4e;"> Pontos Binário: ${produto.ptsBinario}</strong>
							    	  	</c:if>
							    	  <br>
							    	  	<c:if test="${not empty produto.ptsCarreira}">
							    	 		 <strong style="color: #233e4e;"> Pontos Carreira: ${produto.ptsCarreira}</strong>
							    	 	 </c:if>
							    	 </p>
									
										<c:if test="${!venda}">
											<c:if test="${produto.precoDe != null && produto.precoDe != produto.preco}">
												De
												<span id="precoDe${produto.id}" class="label-price-de">
									      			<script>
									      				var preco = '${produto.precoDe}'.replace('.', ',');
									      				$('#precoDe${produto.id}').html('R$ ' + preco);
									      			</script>
									      		</span>
									      		<br>
								      		</c:if>
								      		Por
											<span id="preco${produto.id}" class="label-price">
								      			<script>
								      				var preco = '${produto.preco}'.replace('.', ',');
								      				$('#preco${produto.id}').html('R$ ' + preco);
								      			</script>
								      		</span>
										</c:if>
									
										<c:if test="${venda}">
											<span id="precoDe${produto.id}" class="label-price">
								      			<script>
								      				var preco = '${produto.precoDe}'.replace('.', ',');
								      				$('#precoDe${produto.id}').html('R$ ' + preco);
								      			</script>
								      		</span>
										</c:if>
									
										
										<span class="pull-right"> Categoria: <a title="Ver mais produtos dessa categoria"  href="compras?categoria=${produto.categoria}&venda=false"> ${produto.categoria} </a></span>
										<hr>
										
						    			<div class="button-group">
							    			<a href="compras?venda=${venda}" class="btn btn-default">
												<i class="fa fa-undo"></i> Voltar
											</a>
											<a href="#" title="Adicionar ao carrinho" onclick="adicionaProdutoAoCarinho('${produto.id}', '${venda}');" class="btn btn-success">
												<i class="glyphicon glyphicon-shopping-cart"></i>
											 <span>ADICIONAR</span>
											</a>
										
											<a title="Comprar somente esse produto" href="checkoutcompraproduto?id=${produto.id}&venda=${venda}" class="btn btn-info">
											<i class="fa fa-usd" aria-hidden="true"></i>

												 <span>COMPRAR</span>
											</a>
										</div>
									</div>
							</div>	
						</div>
					</div>
				</div>
			</section>
		</div>
			<jsp:include page="footer.jsp" />
    	</div>
</body>
</html>
