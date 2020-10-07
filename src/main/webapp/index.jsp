<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<jsp:include page="includes/cabecera.jsp" >
  <jsp:param name="pagina" value="inicio" />
  <jsp:param name="title" value="Inicio" /> 
</jsp:include>
<h3>${encabezado}</h3>
	
			
			<c:forEach items="${generoConLibros}" var="g">
					
					<h4>${g.genero}</h4>
					<hr>
					
					<div class="row-card">
							
						<c:forEach items="${g.libros}" var="p">	
							
							<div class="card">
							  <img src="${l.imagen}" class="card-img-top" alt="${l.titulo}">
							  <div class="card-body">
							    <h5 class="card-title">${l.titulo}</h5>
							    <p><span class="badge badge-secondary">${l.genero.genero}</span></p>
							    <p class="precio">${l.precio} €</p>					    					    
							    <p class="precio">${l.usuario.nombre}</p>
							  </div>
							</div>					
							
						</c:forEach>
						
					</div>
				
			</c:forEach>
			





<%@include file="includes/pie.jsp" %>