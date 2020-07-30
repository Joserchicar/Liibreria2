<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/cabecera.jsp" >
  <jsp:param name="pagina" value="genero" />
  <jsp:param name="title" value="genero" /> 
</jsp:include>

<h1>Listado Categorias</h1>
	
	<a href="genero?id=0">Crear Nuevo genero</a>
		
	<table class="tabla table table-striped">
		<thead>
			<tr>
				<td>Id</td>
				<td>genero</td>
				<td>Acciones</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${genero}" var="cat">
				<tr>
					<td>${g.id}</td>
					<td>${g.genero}</td>
					<td>
						<a href="genero?id=${g.id}"><i class="far fa-edit fa-2x" title="Editar Registro"></i></a>
						<a href="genero?id=${g.id}&accion=2" onclick="confirmar('${g.genero}')"><i class="fas fa-trash fa-2x" title="Eliminar Registro"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>			







    
    
<jsp:include page="../../includes/pie.jsp" />