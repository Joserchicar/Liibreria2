package controller.FrontOficce;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import controller.Alerta;
import controller.EliminarLibroController;
import modelo.modeloDAOImpl.LibroDAOImpl;
import modelo.pojo.Libro;

/**
 * Servlet implementation class EliminarFrontOfficeController
 */
@WebServlet("/EliminarFrontOffice")
public class EliminarFrontOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG=Logger.getLogger(EliminarFrontOfficeController.class);
	


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recoge parametro
		
				String parametroId = request.getParameter("id");
				String parametroUsuario=request.getParameter("usuario");
				
				int idLibro = Integer.parseInt(parametroId);
				int idUsuario= Integer.parseInt(parametroUsuario);
				
				// llamar modelo
				LibroDAOImpl dao = LibroDAOImpl.getInstance(); // esto se tiene hecho con el  private final static librodao 
				String mensaje  = "";
				Libro libro = new Libro();
				
				try {
					libro= dao.delete(idLibro, idUsuario);
					mensaje = "Eliminado " + libro.getTitulo();
					
				} catch (Exception e) {
					mensaje = "Error " + e.getMessage();
					LOG.error(e);
					
				}finally {
					
					
					// guardar datos en session para el mensaje de la vista
					request.getSession().setAttribute("alerta",new Alerta("success",mensaje) );
								
					// pedimos al cliente que realize una segunda REQUEST
					response.sendRedirect("inicioFrontOffice");
					
					
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
