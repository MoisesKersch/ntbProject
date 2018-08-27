<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-sm-6 col-md-6">
    <div class="small-box bg-aqua">
		<a href="#" class="small-box-footer"><strong>Crescimento</strong></a>
		<div class="inner">    		
    		<form:form id="formCrescimento" name="formCrescimento" class="form-horizontal" action="crescimento" method="post" commandName="crescimento" role="form">
				
				<c:if test="${crescimento.liberaCrescimento}">	
			 		<div class="form-group">
				   		<div class="col-sm-10">
			   				<form:radiobutton path="crescimento" value="E"/><span class="radio-label">Esquerda</span><br>
							<form:radiobutton path="crescimento" value="M"/><span class="radio-label">Menor</span><br>
							<form:radiobutton path="crescimento" value="D"/><span class="radio-label">Direita</span><br>
				   			<form:errors path="crescimento" cssClass="tooltip-erro"/>
				   		</div>		   		
			 		</div>
			  		
					<div class="form-group">
						<div class="col-sm-10">
							<button type="submit" name="action" class="btn btn-primary" value="salva">
								Salvar
							</button>
						</div>
					</div>
				</c:if>
	
				<c:if test="${crescimento.liberaCrescimento == false}">
				
					<div class="form-group">
				   		<div class="col-sm-10">
			   				<form:radiobutton path="crescimento" value="E" onclick="return false;"/><span class="radio-label">Esquerda</span><br>
							<form:radiobutton path="crescimento" value="M" onclick="return false;"/><span class="radio-label">Menor</span><br>
							<form:radiobutton path="crescimento" value="D" onclick="return false;"/><span class="radio-label">Direita</span>
				   		</div>		   		
			 		</div>
				</c:if>
			</form:form>
    	</div>
    	<div class="icon">
    		<c:if test="${crescimento.crescimento == 'D'}">
    			<i class="fa fa-arrow-right"></i>
    		</c:if>
    		<c:if test="${crescimento.crescimento == 'E'}">
    			<i class="fa fa-arrow-left"></i>
    		</c:if>
    		<c:if test="${crescimento.crescimento == 'M'}">
    			<i class="fa fa-arrow-down"></i>
    		</c:if>
    	</div>
	</div>
</div>
		
<!-- <script> -->
<!-- // 	crescimento.focus();	 -->
<!-- </script> -->