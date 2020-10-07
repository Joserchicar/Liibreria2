package controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
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

import com.google.gson.Gson;

import modelo.modeloDAOImpl.LibroDAOImpl;
import modelo.pojo.Libro;

/**
 * Servlet implementation class ProductoRestController
 * 
 * 
 */
@WebServlet("/api/producto/*")
public class LibroRestController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LibroRestController.class);
	private static LibroDAOImpl dao = LibroDAOImpl.getInstance();
	private PrintWriter out = null;
	private int id;
	private String responseBody;
	private int statusCode;
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		LOG.debug("Se ejecuta SOLO la 1º vez que recibe una petición");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		LOG.debug("Se ejecuta cuando se para la App");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("Se ejecuta ANTES de GET, POST, PUT o DELETE");

		try {

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			// conseguir id de la URL si es que nos viene
			id = 0;
			String pathInfo = request.getPathInfo();
			LOG.debug("url pathInfo:" + pathInfo);
			if (pathInfo != null) {
				String[] pathsParametros = pathInfo.split("/");
				if (pathsParametros.length > 0) {
					id = Integer.parseInt(pathsParametros[1]);
				}
			}

			out = response.getWriter();

			super.service(request, response); // GET, POST, PUT o DELETE
			LOG.debug("Se ejecuta DESPUES de GET, POST, PUT o DELETE");

			out.flush();

		} catch (Exception e) {

			e.printStackTrace();
			LOG.error(e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// LISTADO
		if (id == 0) {
			try {
				ArrayList<Libro> libros = dao.getAll();

				Gson gson = new Gson();
				;
				String stringBody = gson.toJson(libros);
				out.write(stringBody);
				LOG.debug("GET: libros recuperados " + libros.size());

				response.setStatus(HttpServletResponse.SC_OK);
			} catch (Exception e) {
				LOG.error(e);
				e.printStackTrace();
			}
			// DETALLE
		} else {

			try {
				Libro libro = dao.getById(id);
				Gson gson = new Gson();
				String stringBody = gson.toJson(libro);
				out.write(stringBody);
				response.setStatus(HttpServletResponse.SC_OK);

			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Cuidado.Los datos ya no vienen en parametros
		// Se envian dentro del body de la request en formato json
		// Hay que usar un BufferedReader par leer esa informacion

		BufferedReader bodyData = request.getReader();
		Libro libro = new Gson().fromJson(bodyData, Libro.class);

		LOG.debug("POST: productos Crear" + libro);

		try {

			// TODO javax validation. si pasa la validacion INSERT, si no, 409
			Set<ConstraintViolation<Libro>> violations = validator.validate(libro);

			if (violations.isEmpty()) {

				dao.insert(libro);
				responseBody = new Gson().toJson(libro);
				LOG.debug("insertando libro con exito");
				statusCode = HttpServletResponse.SC_CREATED;
			} else {

				String errores = "";
				for (ConstraintViolation<Libro> v : violations) {
					errores += "<p><b>" + v.getPropertyPath() + "</b>: " + v.getMessage() + "</p>";
				}
				responseBody = "error de validacion. Los datos no se han introducido correctamente";
				statusCode = HttpServletResponse.SC_CONFLICT;
			}
		} catch (Exception e) {
			LOG.error(e);

			responseBody = "{ \"error\": \"" + e.getMessage() + "\" }";
			statusCode = HttpServletResponse.SC_CONFLICT;

		}

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader bodyData = request.getReader();

		Libro libro = new Gson().fromJson(bodyData, Libro.class);

		LOG.debug("POST: productos Crear" + libro);

		try {

			// TODO javax validation. si pasa la validacion INSERT, si no, 409
			Set<ConstraintViolation<Libro>> violations = validator.validate(libro);

			if (violations.isEmpty()) {

				dao.update(libro);
				responseBody = new Gson().toJson(libro);
				LOG.debug("insertando productocon exito");
				statusCode = HttpServletResponse.SC_CREATED;
			} else {

				String errores = "";
				for (ConstraintViolation<Libro> v : violations) {
					errores += "<p><b>" + v.getPropertyPath() + "</b>: " + v.getMessage() + "</p>";
				}
				responseBody = "error de validacion. Los datos no se han introducido correctamente";
				statusCode = HttpServletResponse.SC_CONFLICT;
			}
		} catch (Exception e) {
			LOG.error(e);

			responseBody = "{ \"error\": \"" + e.getMessage() + "\" }";
			statusCode = HttpServletResponse.SC_CONFLICT;

		}

		response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Libro libroDel = dao.delete(id);
			Gson gson = new Gson();
			String stringBody = gson.toJson(libroDel);
			out.write(stringBody);
			response.setStatus(HttpServletResponse.SC_OK);
			LOG.debug(" libro eliminado adecuadamente");

		} catch (Exception e) {
			e.getStackTrace();
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			out.write("{\"error\":\"" + e.getMessage() + "\"}");
			LOG.error(e);
		}

	}

}