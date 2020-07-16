<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>apartadoA</title>
</head>
<body>
	<main>
// Formulario para recoger datos del usuario. Conectado con el controlador /Apartado.a 
		<form action="Apartado-a" method="post" class="form-usuario">


			<input type="text" name="nombre" placeholder="nombre de usuario"> <br>
			
			<br> <select name="color" class="custom-select">
				<option value="bl">Blanco</option>
				<option value="ne">negro</option>
				<option value="am">Amarillo</option>
				<option value="na">Naranja</option>
				<option value="ro">Rojo</option>
				<option value="ve">Verde</option>
				<option value="az">Azul</option>
				<option value="vi">Violeta</option>
				
				
			</select> <br> <input type="submit" value="guardar">

		</form>

	</main>

</body>
</html>