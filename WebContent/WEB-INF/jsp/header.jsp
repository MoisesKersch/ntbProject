<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/main-layout.css">

<script src="<c:url value="/resources/js/header.js" />"></script>

<link rel="stylesheet" type="text/css" media="screen"href="resources/css/header.css">

<header class="main-header">

	<a href="home" id="logo" class="logo"> <span class="logo-mini">
			<img src="<c:url value="/resources/img/logo-small.png" />" width="60" />
	</span> <span class="logo-lg"> <img
			src="<c:url value="/resources/img/logo-large.png" />" width="130" />
	</span>
	</a>

	<nav role="navigation">
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>

		<div class="navbar-custom-menu center navbar navbar-static-top  ">
			<ul class="nav navbar-nav">
				<li style="padding-top: 24px;">
					<a class="dropdown-toggle text-center" data-toggle="dropdown" href="#"> 
						<span id="loginName">${login}</span> <span style="font-size: 12px">${codigobp} </span> 
					</a>
			  </li>
			  
			  	<li style="padding-top: 15px;">
					<c:if test="${avatar != null}">
							<img src="${avatar}" alt="logo" class="img-circle">
							</c:if> <c:if test="${avatar == null}">
								<img src="<c:url value="/resources/img/icon-pessoa-null.png" />" alt="logo" class="img-circle">
					</c:if>
					<i class="fa fa-sign-out hidden" id="sair"></i>
				</li>
			  
			  
			</ul>
		</div>
	</nav>

</header>
<script>
	(function(i, s, o, g, r, a, m) {
		i['GoogleAnalyticsObject'] = r;
		i[r] = i[r] || function() {
			(i[r].q = i[r].q || []).push(arguments)
		}, i[r].l = 1 * new Date();
		a = s.createElement(o), m = s.getElementsByTagName(o)[0];
		a.async = 1;
		a.src = g;
		m.parentNode.insertBefore(a, m)
	})(window, document, 'script', '//www.google-analytics.com/analytics.js',
			'ga');

	ga('create', 'UA-67737982-2', 'auto');
	ga('send', 'pageview');
</script>