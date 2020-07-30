<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/cabecera.jsp" >
  <jsp:param name="pagina" value="genero" />
  <jsp:param name="title" value="Genero" /> 
</jsp:include>

<form action="genero" method="post">
	
		<div class="form-group">
			<label for="id">id:</label>
			<input type="text" name="id" id="id" value="${genero.id}" readonly class="form-control">
		</div>	
		
		<div class="form-group">
			<label for="nombre">Genero:</label>
			<input type="text" name="nombre" id="nombre" value="${genero.genero}" autofocus class="form-control">
		</div>	
		
		<input type="submit" value="Guardar" class="btn btn-primary btn-block">
	
	</form>






    
    
<jsp:include page="../../includes/pie.jsp" />   
    
