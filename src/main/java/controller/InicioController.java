package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.modeloDAOImpl.GeneroDAOImpl;
import modelo.modeloDAOImpl.LibroDAOImpl;
import modelo.pojo.Genero;
import modelo.pojo.Libro;

/**
 * Obtiene todos los libros por genero para listar
 * 
 * @parametro idGenero id genero, si es null muestra todas, else muestra
 *            productos de esa categoria
 * @parametro genero genero
 * 
 * 
 *            encabezado titulo para h3 en index.jsp
 * @param categoriasConProductos ArrayList<Categoria>, que contiene tambien
 *                               todos los productos de cada categoria
 * 
 * @ see view/index.jsp
 * 
 */
@WebServlet("/inicio")
public class InicioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TODOS_LOS_GENEROS = "1";
	private static final LibroDAOImpl libroDAO = LibroDAOImpl.getInstance();
	private static final GeneroDAOImpl generoDAO = GeneroDAOImpl.getInstance();

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
		// parametros
		String paramIdGenero = (request.getParameter("idGenero") == null) ? TODOS_LOS_GENEROS
				: request.getParameter("idGenero");
		String paramGenGenero = (request.getParameter("genero") == null) ? " todos los generos"
				: request.getParameter("genero");

		// Inicializar atributos a retornar a la vista

		ArrayList<Libro> libros = new ArrayList<Libro>();
		ArrayList<Genero> generosConLibros = new ArrayList<Genero>();
		String encabezado = "";

		if (TODOS_LOS_GENEROS.equals(paramIdGenero)) {

			generosConLibros = generoDAO.getAllLibros();

			encabezado = "Todos los Libros  por Genero";

		} else {

			// obtengo los libros
			int idGenero = Integer.parseInt(paramIdGenero);
			libros = libroDAO.getAllByGenero(idGenero, 10);

			// crear Genero para añadir los productos
			Genero g = new Genero();
			g.setId(idGenero);
			g.setGenero(paramGenGenero);
			g.setLibros(libros);

			// guardar en el array el genero
			generosConLibros.add(g);

			encabezado = "<b>" + libros.size() + "</b> Útimos libros de <b>" + paramGenGenero + "</b>";

		}
		request.setAttribute("generosConLibros", generosConLibros);
		request.setAttribute("encabezado", encabezado);

		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
