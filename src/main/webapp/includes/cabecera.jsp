<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!doctype html>
<html lang="es">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- fontawesome 5 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<!-- datatables -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="css/styles.css">



<title>${param.title}|Libreria</title>
</head>

<body onload="init()">

	<!-- Barra de Navegacion -->
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

		<!-- icono para desplegar menu en moviles -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- lista enlaces -->
		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item "><a
					class="nav-link ${ ( 'inicio' eq param.pagina ) ? 'active' : '' }"
					href="inicio">Inicio</a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Generos</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="inicio">TODOS</a>
						<c:forEach items="${applicationScope.generos}" var="genero">
							<a class="dropdown-item"
								href="inicio?idGenero=${genero.id}& genero=${genero.genero}">${genero.genero }</a>
						</c:forEach>
					</div></li>

				<li class="nav-item"><a
					class="nav-link ${ ( 'Libros' eq param.pagina ) ? 'active' : '' }"
					href="inicio">Libros</a></li>

				<!-- opciones cuando el usuario esta Logeado -->

				<c:if test="${ not empty sessionScope.usuario_login }">

					<li class="nav-item"><a
						class="nav-link ${ ( 'Libros' eq param.pagina ) ? 'active' : '' } "
						href="Libro.jsp">Libros</a></li>
					<li class="nav-item"><a
						class="nav-link ${ ( 'registro' eq param.pagina ) ? 'active' : '' } "
						href="registro">gestion</a></li>

				</c:if>

			</ul>

			<span class="form-inline"> <c:if
					test="${ empty sessionScope.usuario_login }">
					<a class="nav-link  btn btn-outline-warning" href="Login.jsp">Iniciar
						Sesión</a>
				</c:if> <c:if test="${ not empty sessionScope.usuario_login }">
					<span class="badge badge-pill badge-light mr-3">${usuario_login.nombre}</span>
					<a class="nav-link  btn btn-outline-light" href="Logout">Cerrar
						Sesión</a>
				</c:if>

			</span>


		</div>
	</nav>
	<br>

	<main role="main" class="container">

		<jsp:include page="alerta.jsp"></jsp:include>