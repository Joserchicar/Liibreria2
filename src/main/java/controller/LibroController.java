package controller;

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

/**
 * Controlador para el listado de los libros existente
 * 
 * "escucha" en /Libro.
 * 
 * @author Joserra
 * @version 1.0
 */
@WebServlet("/Libro")
public class LibroController extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(LibroController.class);
	private static final long serialVersionUID = 1L;

	/**
	 * presenta una lista de todos los libros existentes.
	 * 
	 * Obtiene una {@code ArrayList<Libro> libros} y la envia para que se muestre a
	 * la vista views/libros/Libro.jsp, donde se muestra como tabla de libros.
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// conseguir libros de la BBDD
		LibroDAOImpl dao = LibroDAOImpl.getInstance();
		ArrayList<Libro> libros = new ArrayList<Libro>();

		try {
			libros = dao.getAll();
		} catch (Exception e) {

			LOG.error(e);

		}
		// obtengo los datos para enviar a la vista
		request.setAttribute("libros", libros);

		// ir a la nueva vista
		request.getRequestDispatcher("views/libros/Libro.jsp").forward(request, response);

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
