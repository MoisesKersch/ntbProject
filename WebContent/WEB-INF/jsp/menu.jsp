<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<aside class="main-sidebar" id="mainLayoutAside">
	<section class="sidebar">
    	<div class="user-panel">

<!--     		<div class="pull-left image"> -->
<%--             	<c:if test="${avatar != null}"> --%>
<%-- 					<img src="${avatar}" alt="logo" width="40px" class="img-circle"> --%>
<%-- 				</c:if> --%>
<%-- 				<c:if test="${avatar == null}"> --%>
<%-- 					<img src="<c:url value="/resources/img/icon-pessoa-null.png" />" alt="logo" width="40px" class="img-circle"> --%>
<%-- 				</c:if> --%>
<!--             </div> -->

<!--             <div class="pull-left info"> -->
<!--             	<h4> -->
<!-- 	            	<a href="meusdados"> -->
<%-- 	            		${login} --%>
<%-- 	            		<br><small>${codigobp}</small> --%>
<!-- 	            	</a> -->
<!--             	</h4> -->
<!--             </div> -->

		</div>

        <ul class="sidebar-menu sideCustom">
			<li><a href="home"><i class="fa fa-home "></i><span class="titleFontWeight">Início</span></a></li>
        	        	
        	<c:if test="${readonly == 'N'}">
				<li><a class="<c:if test="${menu == 'meusdados'}">active</c:if>" href="meusdados"><i class="fa fa-folder-open-o"></i> <span class="titleFontWeight">Meus dados</span></a></li>
				<c:if test="${autenticacompra == 'Y'}">
					<li><a class="<c:if test="${menu == 'empreendedor'}">active</c:if>" href="empreendedor"><i class="fa fa-male"></i> <span class="titleFontWeight">Empreendedor</span></a></li>
				</c:if>
			</c:if>

			<c:if test="${readonly == 'N'}">
				<li><a href="agendacontato"><i class="fa fa-user"> </i> <span class="titleFontWeight">Minha Lista</span></a></li>
			</c:if>
			
			<c:if test="${autenticacompra == 'Y'}">
				<li><a class="<c:if test="${menu == 'novocadastro'}">active</c:if>" href="novocadastro"><i class="fa fa-file-o"></i> <span class="titleFontWeight">Novo cadastro</span></a></li>
				<li><a class="<c:if test="${menu == 'compras'}">active</c:if>" href="compras"><i class="fa fa-shopping-cart"></i> <span class="titleFontWeight">Loja virtual</span></a></li>
			</c:if>
			
			<c:if test="${autenticacompra != 'Y'}">
				<li><a class="<c:if test="${menu == 'compras'}">active</c:if>" href="compras"><i class="fa fa-shopping-cart"></i> <span class="titleFontWeight">Adesão</span></a></li>
			</c:if>

			<c:if test="${readonly == 'N'}">
				<c:if test="${autenticacompra == 'Y'}">
					<li><a class="<c:if test="${menu == 'minhascompras'}">active</c:if>" href="minhascompras"><i class="fa fa-money"></i> <span class="titleFontWeight">Minhas compras</span></a></li>
				</c:if>				
				<li><a class="<c:if test="${menu == 'financeiro'}">active</c:if>" href="financeiro"><i class="fa fa-usd"></i> <span class="titleFontWeight">Meu financeiro</span></a></li>
			</c:if>

			<c:if test="${readonly == 'N'}">
				<li><a class="<c:if test="${menu == 'financeiro'}">active</c:if>" href="ativacao"><i class="fa fa-usd"></i> <span class="titleFontWeight">Bônus de Incentivo</span></a></li>
			<li> <a href="minhasreunioes"><i class="fa fa-users"></i> <span class="titleFontWeight">Minhas Reuniões</span></a></li>
			</c:if>

			<c:if test="${autenticacompra == 'Y'}">
				<li><a class="<c:if test="${menu == 'redebinaria'}">active</c:if>" href="redebinaria"><i class="fa fa-sitemap"></i> <span class="titleFontWeight">Rede binária</span></a></li>
	        	<li><a id="redelinear" class="<c:if test="${menu == 'redelinear'}">active</c:if>" href="redelinear"><i class="fa fa-bars"></i> <span class="titleFontWeight">Rede linear</span></a></li>
	        </c:if>
			
			<c:if test="${readonly == 'N'}">
				<c:if test="${autenticacompra == 'Y'}">
					<li><a class="<c:if test="${menu == 'meuspontos'}">active</c:if>" href="meuspontos"><i class="fa fa-signal"></i> <span class="titleFontWeight">Meus pontos</span></a></li>
					<li><a class="<c:if test="${menu == 'cadastrospendentes'}">active</c:if>" href="cadastrospendentes"><i class="fa fa-file-text-o"></i> <span class="titleFontWeight">Cadastros pendentes</span></a></li>
				</c:if>
				<li><a class="<c:if test="${menu == 'documentos'}">active</c:if>" href="documentos"><i class="fa fa-book"></i> <span class="titleFontWeight">Documentos</span></a></li>
<%-- 				<li><a class="<c:if test="${menu == 'salaconferencia'}">active</c:if>" href="salaconferencia"><i class="fa fa-users"></i> <span>Sala de conferência</span></a></li> --%>
				<c:if test="${autenticacompra == 'Y'}">
					<li><a class="<c:if test="${menu == 'upgrade'}">active</c:if>" href="upgrade"><i class="fa fa-arrow-up"></i> <span class="titleFontWeight">Upgrade</span></a></li>
				</c:if>				
			</c:if>
        	
        	<li><a class="<c:if test="${menu == 'ajuda'}">active</c:if>" href="helpvideolista"><i class="fa fa-question"></i> <span class="titleFontWeight">Vídeos</span></a></li>
			<li><a class="<c:if test="${menu == 'mural'}">active</c:if>" href="mural"><i class="fa fa-rss"></i> <span class="titleFontWeight">Notícias</span></a></li>
			<li> <a href="contatoform"><i class="fa fa-comments-o"></i> <span class="titleFontWeight">Suporte</span></a></li>
        	
		</ul>

	</section>
</aside>