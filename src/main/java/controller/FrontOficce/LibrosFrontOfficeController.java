package controller.FrontOficce;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class LibrosFrontOfficeController
 */
@WebServlet("/LibrosFrontOffice")
public class LibrosFrontOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LibrosFrontOfficeController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibrosFrontOfficeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String validados = request.getParameter("validados");
		String titulo = "";

		if (validados == null) {
			titulo = "Libros Validados";
		} else {
			titulo = "Libros Pendientes de Validar";
		}

		request.setAttribute("titulo", titulo);
		request.getRequestDispatcher("Libro.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
