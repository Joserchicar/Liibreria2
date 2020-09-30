package controller.FrontOficce;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import controller.Alerta;
import modelo.modeloDAOImpl.LibroDAOImpl;
import modelo.pojo.Libro;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class EliminarFrontOfficeController
 */
@WebServlet("/views/frontoffice/eliminar")
public class EliminarFrontOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG=Logger.getLogger(EliminarFrontOfficeController.class);
	private final static LibroDAOImpl daoLibro=LibroDAOImpl.getInstance();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recoge parametro
		
				String parametroId = request.getParameter("id");
				LOG.trace("entramos en ELiminar Libro" + parametroId);
				HttpSession session= request.getSession();
				Alerta alerta=new Alerta();
				Usuario usuario= new Usuario();
				
			try {	
				
				usuario=(Usuario)session.getAttribute("usuario_login");
				int idLibro = Integer.parseInt(parametroId);
				int idUsuario= usuario.getId();
				
				Libro l= daoLibro.delete(idLibro, idUsuario);
				alerta = new Alerta ("success", "Libro" + l.getTitulo() + "ha sido eliminado");
				
				
				} catch (Exception e) {
					
					LOG.error(e);
					alerta =new Alerta("danger","Error inexperado");
				}finally {
					
					
					
					session.setAttribute("alerta",alerta);
								
					request.getRequestDispatcher("/views/frontoffice/inicioFrontOffice").forward(request, response);
					
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
