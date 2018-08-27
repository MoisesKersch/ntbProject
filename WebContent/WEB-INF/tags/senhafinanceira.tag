<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ attribute name="path" required="true" %>

<div class="input-group">
	<form:password class="form-control" path="${path}" id="${path}" maxlength="60"/>
	<span class="input-group-btn">
		<button id="buttonPassword${path}" class="btn btn-default" type="button">
			<i id="iconButtonPassword${path}" class="fa fa-eye-slash"></i>
		</button>
	</span>
</div>

<script>
	$(document).ready(function() {
		
		$("#${path}").keypress(
			function(event) {
				if (String.fromCharCode(event.which) == '@'
					|| String.fromCharCode(event.which) == '='
					|| String.fromCharCode(event.which) == '?') {
					event.preventDefault();
				}  
			}
		);

		$('#buttonPassword${path}').click(
			function() {
				if($('#${path}').attr('type') == 'text') {
					$('#${path}').attr('type', 'password');
					$('#iconButtonPassword${path}').removeClass('fa-eye');
					$('#iconButtonPassword${path}').addClass('fa-eye-slash');
				}
				else {
					$('#${path}').attr('type', 'text');
					$('#iconButtonPassword${path}').removeClass('fa-eye-slash');
					$('#iconButtonPassword${path}').addClass('fa-eye');
				}
				$('#${path}').focusTextToEnd();
			}
		);				
	});
</script>