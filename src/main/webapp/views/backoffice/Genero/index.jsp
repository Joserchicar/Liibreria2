<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   

<jsp:include page="../../../includes/office-head.jsp" />
<jsp:include page="../../../includes/office-navbar-admin.jsp" />
    
	<h1>${fn:length(generos)} generos </h1>
	<a href="views/backoffice/genero?id=0"> Nuevo genero</a> 
	  
	<table class="tabla table table-striped">
		<thead>
			<tr>
				<td>Id</td>
				<td>genero</td>				
				<td>operaciones</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${generos}" var="g">
				<tr>
					<td>${g.id}</td>
					<td>${g.genero}</td>					
					<td>
						<a href="views/backoffice/genero?id=${g.id}" class="mr-4"> <i class="far fa-edit fa-2x" title="Editar genero"></i></a>
						<a href="views/backoffice/genero?id=${g.id}&accion=delete"
						   onclick="confirmar('${g.genero}')" 
						   ><i class="fas fa-trash fa-2x" title="Eliminar genero"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
                 
  
  
 <jsp:include page="../../../includes/office-footer.jsp" />  