package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import modelo.modeloDAO.LibroDAO;
import modelo.modeloDAOImpl.GeneroDAOImpl;
import modelo.modeloDAOImpl.LibroDAOImpl;
import modelo.pojo.Genero;
import modelo.pojo.Libro;

/**
 * Servlet implementation class InicioController
 */
@WebServlet("/inicio")
public class InicioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final LibroDAOImpl libroDAO = LibroDAOImpl.getInstance();
	private static final GeneroDAOImpl generoDAO = GeneroDAOImpl.getInstance();
	private static final String TODOS_LOS_GENEROS = "-1";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Libro> libros = new ArrayList<Libro>();
		ArrayList<Genero> generosConLibros = new ArrayList<Genero>();

		String paramIdGenero = request.getParameter("idGenero");
		String paramGenGenero = (request.getParameter("genero") == null) ? " todos los generos"
				: request.getParameter("genero");

		if (TODOS_LOS_GENEROS.equals(paramIdGenero)) {

			generosConLibros = generoDAO.getAllLibros();
			libros = null;
			request.setAttribute("encabezado", "todos loslibros por genero");

		} else {

			generosConLibros = null;

			if (paramIdGenero != null) {

				int idGenero = Integer.parseInt(paramIdGenero);
				libros = libroDAO.getAllByGenero(idGenero, 10);

			} else {

				libros = libroDAO.getLast(10);

			}

			request.setAttribute("encabezado",
					"<b>" + libros.size() + "</b> Útimos libross de <b>" + paramGenGenero + "</b>");

		}
		request.setAttribute("libros", libros);
		request.setAttribute("generosConLibros", generosConLibros);

		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
