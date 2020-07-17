package controller.FrontOficce;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;





/**
 * Servlet implementation class inicioFrontOfficeController
 */
@WebServlet("/inicioFrontOffice")
public class InicioFrontOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(InicioFrontOfficeController.class);   
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO recueprar datos de inicio para el usuario
		
				request.setAttribute("productos_aprobados", 3);
				request.setAttribute("productos_pendientes", 2);
				
				// CUIDADO: mirar la URL del servlet "/frontoffice/inicio"
				// cuando hacemos forward se pierde lo ultimo de la url y se le suma la variabel pagina
				// ----------------------------------------------------------------------------------------
				// el forward resuelve la url de la siguiente manera:
				//
				//          "/frontoffice/inicio" + "index.jsp"  =  "/frontoffice/index.jsp"
				//  ------------------------------------------------------------------------------------------
				String pagina = "index.jsp";
				LOG.debug("forward: " + pagina);
				
				request.getRequestDispatcher(pagina).forward(request, response);		
				
			}

			/**
			 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			 */
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// TODO Auto-generated method stub
				doGet(request, response);
			}

}
