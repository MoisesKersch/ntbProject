<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/animate.css" />">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/skins/_all-skins.min.css" />">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/style.css" />">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquery-ui.css" />">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/redebinaria.css" />">

<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.1.4.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="resources/js/bootstrap.min.js"></script>

<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>

<script src="<c:url value="/resources/js/demo.js" />"></script>
<script src="<c:url value="/resources/js/jquery.bootstrap-growl.js" />"></script>
<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>
<script src="<c:url value="/resources/js/script.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
<script src="<c:url value="/resources/js/angular-1.0.1.min.js" />"></script>
<script src="<c:url value="/resources/js/custom.js" />"></script>
<script src="<c:url value="/resources/js/wz_jsgraphics.js" />"></script>
<script src="<c:url value="/resources/js/jquery.btree.js" />"></script>
<script src="<c:url value="/resources/js/app.min.js" />"></script>

<script>
    $(function() {
        $(".datepicker").datepicker();
        $(".data").mask("99/99/9999");
        $(".cep").mask("99999-999");
    });
</script>
</head>

<body class="${skinUser} sidebar-mini">
	<div class="wrapper">
		<jsp:include page="message.jsp" />
		<jsp:include page="header.jsp" />
		<jsp:include page="menu.jsp" />

		<div class="content-wrapper" style="min-width: 100px;">
			<section id="divContent" class="content">
				<div class="box box-primary" style="height: 960px;">
					<div class="box-header">
						<h3 class="box-title inboxTitle">Rede Binária</h3>
					</div>
					<div style="height:912px; overflow:auto;">
						<div class="box-body"> 
							<jsp:include page="arvorebinaria.jsp" />
						</div>
					</div>
				</div>

			</section>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
</body>

<c:if test="${readonly == 'Y'}">
    <c:url var="listaUsuariosURL" value="/listausuariospelobase" />
    <script>
        $(document).ready(
                function() {

                    $('#filho').change(function() 
                    {
                        $.post("usuariofilho", {
                            id : $('#filho').val()
                        }, {});
                        get('arvorebinaria');
                    });

                    $.getJSON('${listaUsuariosURL}', {
                        ajax : 'true',
                        id : '${usuario}'
                    },

                    function(data) {
                        var html = '';
                        var len = data.length;
                        var id = '${usuario_filho}';
                        var existe = 'Y';
                        for (var i = 0; i < len; i++) {
                            if (id == null || id == '') {
                                id = data[i].id;
                                existe = 'N';
                            }
                            html += '<option value="' + data[i].id + '">'
                                    + data[i].nome + '</option>';
                        }
                        html += '</option>';
                        $('#filho').html(html);
                        $('#filho').val(id);
                        $('#filho').removeClass("hidden");
                        if (existe == 'N') {
                            $.post("usuariofilho", {
                                id : $('#filho').val()
                            }, {});
                            get('arvorebinaria');
                        }
                    });

                });
    </script>
</c:if>

</html>
