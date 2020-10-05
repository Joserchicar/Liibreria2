package controller.Backoffice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletContext;
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

import controller.Alerta;
import modelo.modeloDAOImpl.GeneroDAOImpl;
import modelo.pojo.Genero;

/**
 * Servlet implementation class generoBackOfficeController
 */
@WebServlet("/views/backoffice/genero")
public class generoBackOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(generoBackOfficeController.class);
	private static final GeneroDAOImpl dao = GeneroDAOImpl.getInstance();
	private static final String VIEW_LISTA = "categoria/index.jsp";
	private static final String VIEW_FORM = "categoria/formulario.jsp";
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	/**
	 * Se encarga de listar, eliminar o mostrar una categoria en el fomrulario
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("Listado generos");
		String idParam = request.getParameter("id");
		String accionParam = request.getParameter("accion");

		String forward = VIEW_LISTA;
		try {

			// LISTAR
			if (idParam == null) {

				ArrayList<Genero> listado = dao.getAll();
				request.setAttribute("generos", listado);

				// ELIMINAR
			} else if (accionParam != null) {

				int id = Integer.parseInt(idParam);
				try {
					dao.delete(id);
					request.setAttribute("alerta", new Alerta("success", "Genero Eliminado con exito"));
				} catch (Exception e) {
					request.setAttribute("alerta",
							new Alerta("warning", "Genero tiene libros asociados y no se puede eliminar"));
				}
				// despues de eliminar conseguimos todas los generos y vamos al listado
				request.setAttribute("generos", dao.getAll());

				// MOSTRAR EN FORMULARIO
			} else {

				Genero genero = new Genero();
				int id = Integer.parseInt(idParam);

				if (id > 0) {
					genero = dao.getById(id);
				}

				request.setAttribute("genero", genero);
				forward = VIEW_FORM;
			}

		} catch (Exception e) {
			LOG.error(e);

		} finally {
			// como el controlador escucha en la url "/views/backoffice/genero"
			// para hacer el forward pierde la utima parte de la url "genero" y debemos
			// añadir la careta donde esta la index de los generos
			// /views/backoffice/ + genero/index.jsp
			request.getRequestDispatcher(forward).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 todo bien
		// 2 los datos introduciodos en el formulario no sean correctos
		// 3 nombre categoria duplicado

		LOG.trace("envian datos desde un formulario");

		String idParam = request.getParameter("id");
		String nombreParam = request.getParameter("genero");
		Alerta alerta = new Alerta();
		Genero g = new Genero();

		try {

			int id = Integer.parseInt(idParam);

			// Mapeado los datos del formulario al POJO
			g.setId(id);
			g.setGenero(nombreParam);

			// validar datos envaidos antes de insertar
			Set<ConstraintViolation<Genero>> violations = validator.validate(g);
			if (!violations.isEmpty()) {

				alerta = new Alerta("warning", "Los datos introducidos no son correctos");

				// no hay errores de validacion, guardar en bbdd
			} else {

				try {
					if (id > 0) {
						dao.update(g);
					} else {
						dao.insert(g);
					}
					alerta = new Alerta("success", "Categoria guardada con exito");

					// actualizar las categorias del contexto
					ServletContext contextoAplicacion = request.getServletContext();
					contextoAplicacion.setAttribute("categorias", dao.getAll());

				} catch (Exception e) {
					alerta = new Alerta("warning", "El nombre de la Categoria ya existe, por favor elige otro.");
				}
			}

		} catch (Exception e) {
			LOG.error(e);
			alerta = new Alerta("danger", "Lo sentimos pero tenemos un fallo");

		} finally {
			request.setAttribute("categoria", g);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher(VIEW_FORM).forward(request, response);
		}

	}

}
