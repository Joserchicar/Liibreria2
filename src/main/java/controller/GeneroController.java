package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import modelo.modeloDAOImpl.GeneroDAOImpl;
import modelo.pojo.Genero;

/**
 * Servlet implementation class GeneroController
 */
@WebServlet("/Genero")
public class GeneroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(GeneroController.class);

	private static final GeneroDAOImpl generoDao = GeneroDAOImpl.getInstance();

	private static final String VIEW_TABLA = " index.jsp";
	private static final String VIEW_FORMULARIO = "registro.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) * @paramter id id Genero
	 * @paremter operacion para saber si queremos eliminar 
	 * IR FORMULARIO => Si recibimos como parametro el id del Genero, iremos al formulario
	 *           para poder guardarla 
	 * ELIMNAR CATEGORIA => Si recibimos como parametros el id del genero y operacion == 2, Eliminamos el Genero
	 *           y vamos a la tabla 
	 * MOSTRAR TABLA => Si id categoria es null mostramos la tabla con todas
	 * 
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = VIEW_TABLA;
		Alerta alerta = new Alerta();
		String paramId = request.getParameter("id");
		String operacionEliminar = request.getParameter("operacion");

		Genero genero = new Genero();

		try {

			if (paramId != null) {

				int id = Integer.parseInt(paramId);

				if (operacionEliminar != null) { /// ELIMINAR

					generoDao.delete(id);
					alerta = new Alerta("success", "Registro eliminado");

				} else { // IR AL FORMULARIO

					if (id > 0) {
						genero = generoDao.getById(id);
					}
					view = VIEW_FORMULARIO;
				}

			}

		} catch (Exception e) {
			LOG.error(e);
			alerta = new Alerta("danger", "No se puede eliminar el genero si tiene titulos asociados");

		} finally {

			request.setAttribute("alerta", alerta);
			request.setAttribute("genero", genero);
			request.setAttribute("generos", generoDao.getAll());
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Alerta alerta = new Alerta();

		String paramId = request.getParameter("id");
		String nombre = request.getParameter("nombre");

		Genero genero = new Genero();

		try {

			int id = Integer.parseInt(paramId);
			genero.setId(id);
			genero.setGenero(nombre);

			if (id > 0) {
				generoDao.update(genero);
				alerta = new Alerta("success", "Registro modificado");
			} else {
				generoDao.insert(genero);
				alerta = new Alerta("success", "Registro creado ");
			}

		} catch (Exception e) {
			LOG.error(e);
			alerta = new Alerta("warning",
					"Lo sentimos pero el genero <b>" + genero.getGenero() + "</b> ya esta registrada");

		} finally {

			request.setAttribute("genero", genero);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher(VIEW_FORMULARIO).forward(request, response);
		}
	}
}
