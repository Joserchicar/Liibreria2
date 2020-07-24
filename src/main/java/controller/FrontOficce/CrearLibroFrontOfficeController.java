package controller.FrontOficce;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import controller.Alerta;
import modelo.modeloDAO.modeloDAOImpl.LibroDAOImpl;
import modelo.pojo.Genero;
import modelo.pojo.Libro;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class CrearLibroFrontOfficeController
 */
@WebServlet("/CrearLibroFrontOffice")
public class CrearLibroFrontOfficeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(CrearLibroFrontOfficeController.class);
	private final static LibroDAOImpl daoLibro = LibroDAOImpl.getInstance();

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		// Vamos al formulario y enviamos un producto inicializado

		Libro l = new Libro();
		request.setAttribute("libro", l);

		// Los generos estan disponibles a traves del Listenner
		request.getRequestDispatcher("registro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Alerta alerta = new Alerta();

		Libro l = new Libro();

		// recoger parametros del formulario

		String idParametro = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String precio = request.getParameter("precio");
		String imagen = request.getParameter("imagen");
		String generoId = request.getParameter("genero_id");

		try {

			int id = Integer.parseInt(idParametro);
			int genero_id = Integer.parseInt(generoId);
			float precioFloat = Float.parseFloat(precio);

			// Crear objeto con esos parametros

			l.setId(id);
			l.setTitulo(titulo);
			l.setPrecio(precioFloat);
			l.setImagen(imagen);

			Genero g = new Genero();
			g.setId(genero_id);
			l.setGenero(g);

			// RECUPERAR USUARIO DE SESSION Y SETTEARLO EN EL LIBRO

			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuario_login");
			l.setUsuario(usuario);

			// Validar Pojo

			Set<ConstraintViolation<Libro>> violations = validator.validate(l);

			if (violations.isEmpty()) { // sin errores de validacion
				// vuelve al inicio y vuelve para listar los libros (redirecciona)

				if (id == 0) {

					daoLibro.insert(l);

					request.getSession().setAttribute("mensaje", "Libro registrado con exito");
					request.setAttribute("libro", l);
				} else {
					daoLibro.update(l);

					// ir a la vista del registro de libros
					request.setAttribute("libro", l);

					// request.setAttribute("mensaje", "El libro ya existe");

				} // if

				alerta = new Alerta("success", "Libro registrado");
			} else { // Si hay errores de validacion

				String errores = "";
				for (ConstraintViolation<Libro> v : violations) {

					errores += "<p><b>" + v.getPropertyPath() + "</b>:" + v.getMessage() + "</p>";

				}
				alerta = new Alerta("danger", errores);

			}

		} catch (SQLException e) {
			LOG.error(e);
			alerta = new Alerta("danger", "Lo sentimos pero ya existe ese NOMBRE,escribe otro por favor ");

		} catch (Exception e) {
			LOG.error(e);
			alerta = new Alerta("danger", "Lo sentimos pero hemos tenido un ERROR inxesperado ");

		} finally {

			// Enviar datos a la vista

			request.setAttribute("alerta", alerta);
			request.setAttribute("libro", l);

			// ir a la nueva vista o jsp

			request.getRequestDispatcher("registro.jsp").forward(request, response);
		} // trycatch

	}

}// dopost
