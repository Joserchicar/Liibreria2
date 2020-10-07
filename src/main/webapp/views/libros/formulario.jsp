<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/cabecera.jsp">
	<jsp:param name="pagina" value="registro" />
	<jsp:param name="title" value="registro" />
</jsp:include>


<p style="color: red">${mensaje}</p>

<h1>Formulario Para Crear/Modificar Libros</h1>

<div class="row pb-4">

	<div class="col">

		<form action="libro" method="post">

			<div class="form-group">
				<label for="id">id </label> <input type="text" name="id" id="id"
					Value="${libro.id}" readonly class="form-control">
			</div>
			<br>
			<div class="form-group">
				<label for="titulo">titulo del libro</label> <input type="text"
					name="titulo" id="titulo" Value="${libro.titulo}"
					class="form-control" placeholder="Introduce el Titulo">
			</div>

			<div class="form-group">
				<label for="precio">precio:</label> <input type="text" name="precio"
					id="precio" value="${libro.precio}" class="form-control"
					placeholder="0.0 €">
			</div>

			<div class="form-group">
				<label for="imagen">Imagen:</label> <input type="text" name="imagen"
					id="imagen" value="${libro.imagen}" class="form-control"
					placeholder="URL de la imagen (.jpg o .png)">
			</div>

			<div class="form-group">
				<select class="custom-select" name="categoria_id">
					<c:forEach items="${generos}" var="categoria">
						<option value="${genero.id}"
							${ ( genero.id eq libro.genero.id ) ? "selected" : "" }>${genero.genero}</option>
					</c:forEach>
				</select>
			</div>

			<br>
			<button type="submit" value="Guardar" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<div class="col">
		<img src="${libro.imagen}" class="img-thumbnail"
			alt="imagen del producto a modificar">
	</div>


</div>



<%@include file="../../includes/pie.jsp"%>
