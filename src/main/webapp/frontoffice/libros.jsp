<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 <jsp:include page="../../includes/office-head.jsp" />
<jsp:include page="../../includes/office-navbar.jsp" />

     
 <h2>${titulo}</h2>
                        
  <!-- tabla listado libros -->



<table class="tabla table table-striped">
	<thead>
		<tr>
			<td>Id</td>
			<td>Titulo</td>
			<td>Precio</td>
			<td>Imagen</td>
			<td>Genero</td>
			<td>Acciones</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${libros}" var="l">
			<tr>
				<td>${l.id}</td>
				<td>${l.titulo}</td>
				<td>${l.precio}&euro;</td>
				<td><img src="${l.imagen}" class="img-thumbnail"
					alt="imagen..."></td>
				<td>${l.genero.genero}</td>

				<td><a href="registro?id=${l.id}" class="mr-4"> <i
						class="far fa-edit fa-2x" title="Editar libro"></i></a> <a
					href="EliminarLibro?id=${l.id}"
					onclick="confirmar('¿Estas Seguro ?')"> <i
						class="fas fa-trash fa-2x" title="Eliminar libro"></i></a></td>
			</tr>
	
		</c:forEach>
	</tbody>
</table>
 <jsp:include page="../../includes/office-footer.jsp" />     