package controller.FrontOficce;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import modelo.modeloDAOImpl.LibroDAOImpl;
import modelo.pojo.Libro;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class LibrosFrontOfficeController
 */
@WebServlet("/views/frontoffice/Libros")
public class LibrosFrontOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LibrosFrontOfficeController.class);
	private final static LibroDAOImpl daoLibro=LibroDAOImpl.getInstance();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String validados = request.getParameter("validados");
		String titulo = "";
		ArrayList<Libro> libros=new ArrayList<Libro>();
		
		try {
			
		Usuario usuarioSession=(Usuario)request.getSession().getAttribute("usuario_login");	
		
		 // comprobar esto
		int idUsuario = 0;
		
		if (validados == null) {
			titulo = "Libros Validados";
			libros=daoLibro.getAllByUser(idUsuario, true);
		} else {
			titulo = "Libros Pendientes de Validar";
			libros= daoLibro.getAllByUser(idUsuario, false);
		}

		}catch (Exception e) {
			LOG.error(e);
		}finally {
			
			request.setAttribute("libros", libros);
			request.setAttribute("titulo", titulo);
			request.getRequestDispatcher("Libro.jsp").forward(request, response);


		}
		
		
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
