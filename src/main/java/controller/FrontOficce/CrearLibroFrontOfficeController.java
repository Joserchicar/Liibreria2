package controller.FrontOficce;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import modelo.pojo.Libro;

/**
 * Servlet implementation class CrearLibroFrontOfficeController
 */
@WebServlet("/CrearLibroFrontOffice")
public class CrearLibroFrontOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final static Logger LOG= Logger.getLogger(CrearLibroFrontOfficeController.class );
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Vamos al formulario y enviamos un producto inicializado 
		
		Libro l=new Libro();
		request.setAttribute("libro", l);
		
		request.getRequestDispatcher("registro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger parametros del formulario
		
		//Crear objeto con esos parametros
		
		Libro l=new Libro();
		l.setId(27);
		l.setTitulo("titulo");
		// Validar Pojo
		
		
		// LLamar al DAO
		
		
		// Volver al formulario de nuevo
		
		request.setAttribute("libro", l);
		request.getRequestDispatcher("registro.jsp").forward(request, response);
		
		
		
		
		
	}

}
