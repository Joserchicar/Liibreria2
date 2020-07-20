package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import org.apache.log4j.Logger;

import modelo.modeloDAOImpl.GeneroDAOImpl;
import modelo.modeloDAOImpl.LibroDAOImpl;
import modelo.pojo.Genero;
import modelo.pojo.Libro;

/**
 * Servlet implementation class RegistroController
 */
@WebServlet("/registro")
public class RegistroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(RegistroController.class);

	private static LibroDAOImpl daoLibro = LibroDAOImpl.getInstance();
	private static GeneroDAOImpl daoGenero = GeneroDAOImpl.getInstance();

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String parametroId = request.getParameter("id");
			Libro libro = new Libro();

			if (parametroId != null) {

				int id = Integer.parseInt(parametroId);
				LibroDAOImpl dao = LibroDAOImpl.getInstance();
				libro = dao.getById(id);
			}

			request.setAttribute("libro", libro);

		} catch (Exception e) {
			LOG.error(e);
			request.setAttribute("danger", "Lo sentimos fallo en GET " + e.getMessage());

		} finally {

			request.setAttribute("genero", daoGenero.getAll());
			// ir a la nueva vista o jsp
			request.getRequestDispatcher("registro.jsp").forward(request, response);
		}

	}

	/**
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Crear objeto

		Alerta alerta = new Alerta();
		Libro libro = new Libro();

		try {

			// recoger los valores del formulario
			String idParametro = request.getParameter("id");
			String titulo = request.getParameter("titulo");
			String generoId = request.getParameter("genero_id");

			int id = Integer.parseInt(idParametro);
			int idGenero = Integer.parseInt(generoId);

			// DAO
			LibroDAOImpl dao = LibroDAOImpl.getInstance();

			// parametros

			libro.setId(id);
			libro.setTitulo(titulo);

			Genero g = new Genero();
			g.setId(idGenero);
			libro.setGenero(g);

			Set<ConstraintViolation<Libro>> violations = validator.validate(libro);

			if (violations.isEmpty()) { // sin errores de validacion
				// vuelve al inicio y vuelve para listar los libros (redirecciona)

				if (id == 0) {

					daoLibro.insert(libro);

					request.getSession().setAttribute("mensaje", "Libro registrado con exito");
					request.setAttribute("libro", libro);
				} else {
					daoLibro.update(libro);

					// ir a la vista del registro de libros
					request.setAttribute("libro", libro);

					// request.setAttribute("mensaje", "El libro ya existe");

				} // if

				alerta = new Alerta("success", "Libro registrado. Pendiente de su validacion");
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

			request.setAttribute("libro", libro);
			request.setAttribute("generos", daoGenero.getAll());

			// ir a la nueva vista o jsp

			request.getRequestDispatcher("registro.jsp").forward(request, response);
		} // trycatch

	}

}// dopost
