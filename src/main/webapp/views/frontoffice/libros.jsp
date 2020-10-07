<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<jsp:include page="../../includes/office-head.jsp" />
<jsp:include page="../../includes/office-navbar.jsp" />
    
           
<h2>${titulo}</h2>
	
	<table class="tabla table table-striped">
		<thead>
			<tr>
				<td>Id</td>
				<td>titulo</td>
				<td>Precio</td>
				<td>Imagen</td>
				<td>genero</td>
				<td>Operaciones</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${libros}" var="p">
				<tr>
					<td>${l.id}</td> <% // no hace falta usar el getter p.id == p.getId() %>
					<td>${l.nombre}</td>
					<td>${l.precio} &euro;</td>
					<td><img src="${l.imagen}"  class="img-thumbnail" alt="imagen..."></td>
					<td>${l.genero.genero}</td>
					<td>
						<a href="views/frontoffice/guardar-producto?id=${l.id}" class="mr-4"> <i class="far fa-edit fa-2x" title="Editar Producto"></i></a>
						<a href="views/frontoffice/eliminar?id=${l.id}"
						   onclick="confirmar('${l.titulo}')" 
						   ><i class="fas fa-trash fa-2x" title="Eliminar Producto"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
                        
  
 <jsp:include page="../../includes/office-footer.jsp" />     