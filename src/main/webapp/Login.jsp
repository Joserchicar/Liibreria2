<jsp:include page="includes/cabecera.jsp" >
  <jsp:param name="pagina" value="Login" />
  <jsp:param name="title" value="Login" /> 
</jsp:include>

<form action="Login" method="post" class="form-login" >
	
	 	<div class="form-group">
			<input type="text" name="nombre" placeholder="Tu Nombre"  class="form-control">
		</div>
		
		<div class="form-group">
			<input type="password" id="pass" name="contrasenia" placeholder="Tu Contraseña"  class="form-control">
		</div>
		<br>
		
		<input type="submit" value="Iniciar Sesión" class="btn btn-primary btn-block">
	
	</form>

<%@include file="includes/pie.jsp" %>