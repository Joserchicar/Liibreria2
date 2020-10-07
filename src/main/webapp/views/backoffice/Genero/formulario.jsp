<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<jsp:include page="../../../includes/office-head.jsp" />
<jsp:include page="../../../includes/office-navbar-admin.jsp" />


	<form action="views/backoffice/genero" method="post">
	
		<div class="form-group">
			<label for="id">id:</label>
			<input type="text" name="id" id="id" value="${genero.id}" readonly class="form-control">
		</div>	
		
		<div class="form-group">
			<label for="nombre">nombre:</label>
			<input type="text" name="nombre" id="nombre" value="${genero.genero}" class="form-control" placeholder="Escribe el nombre del genero" >
		</div>
	

		<input type="submit" value="Guardar" class="btn btn-primary btn-block">
		
	</form>

  
 <jsp:include page="../../../includes/office-footer.jsp" />