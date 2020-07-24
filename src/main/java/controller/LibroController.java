package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import modelo.modeloDAO.modeloDAOImpl.LibroDAOImpl;
import modelo.pojo.Libro;

/**
 * Servlet implementation class LibroController
 */
@WebServlet("/Libro")
public class LibroController extends HttpServlet {
	private static final Logger LOG=Logger.getLogger(LibroController.class);
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//conseguir libros de la BBDD
		LibroDAOImpl dao = LibroDAOImpl.getInstance();
		ArrayList<Libro> libros = new ArrayList<Libro>();
		
		
		
		try {
			libros = dao.getAll();
		} catch (Exception e) {
			
			LOG.error(e);
			
		}
		// obtengo los datos para enviar a la vista
		request.setAttribute("libros", libros);

		//ir a la nueva vista
		request.getRequestDispatcher("libro.jsp").forward(request, response);

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
