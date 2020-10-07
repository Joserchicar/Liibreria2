<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<jsp:include page="../../includes/office-head.jsp" />
<jsp:include page="../../includes/office-navbar.jsp" />

              
        <h1 class="mt-2">Dar de Alta Nuevo libo</h1>
                     
        <form action="views/frontoffice/guardar-producto" method="post" enctype="multipart/form-data">
        
        		<div class="form-group">
					<label for="id">id:</label>
					<input type="text" name="id" id="id" value="${libro.id}" readonly class="form-control">
				</div>	
				
				<div class="form-group">
					<label for="nombre">nombre:</label>
					<input type="text" name="nombre" id="nombre" value="${libro.titulo}" class="form-control" placeholder="Escribe el nombre del producto" >
				</div>
				
				<div class="form-group">
					<label for="precio">precio:</label>
					<input type="text" name="precio" id="precio" value="${libro.precio}" class="form-control" placeholder="0.0 €" >
				</div>
				
				<div class="form-group">
					<label for="imagen">Imagen:</label>
					<!--  <input type="text" name="imagen" id="imagen" value="${producto.imagen}" class="form-control" placeholder="URL de la imagen (.jpg o .png)" > -->
					<input type="file" name="fichero">
				</div>
				
				<div class="form-group">
					<label for="genero_id">CGenro:</label>
					<select class="custom-select" name="categoria_id" id="categoria_id">
					  <c:forEach items="${generos}" var="categoria">
					  	<option value="${genero.id}"  ${ ( genero.id eq libro.genero.id ) ? "selected" : "" }  >${genero.genero}</option>
					  </c:forEach>					  					  
					</select>
				</div>	
				
				
				<input type="submit" value="Guardar" class="btn btn-primary btn-block">
        
        </form>
                        
  
 <jsp:include page="../../includes/office-footer.jsp" />       