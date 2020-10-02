package controller.FrontOficce;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import modelo.modeloDAOImpl.LibroDAOImpl;
import modelo.pojo.ResumenUsuario;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class inicioFrontOfficeController
 */
@WebServlet("/views/frontoffice/inicio")
public class InicioFrontOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(InicioFrontOfficeController.class);
	private final static LibroDAOImpl daoLibro = LibroDAOImpl.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("Panel de inicio");

		// recueprar datos de inicio para el usuario

		Usuario usuarioSession = (Usuario) request.getSession().getAttribute("usuario_login");
		int idUsuario = usuarioSession.getId();

		//  recuperar datos de una VIEW

		// ArrayList<Producto> aprobados = daoProducto.getAllByUser( idUsuario, true);
		// ArrayList<Producto> pendientes = daoProducto.getAllByUser( idUsuario, false);
		// request.setAttribute("productos_aprobados", aprobados.size() );
		// request.setAttribute("productos_pendientes", pendientes.size() );

		ResumenUsuario resumen = daoLibro.getResumenByUsuario(idUsuario);
		request.setAttribute("resumen", resumen);

		// request.setAttribute("libros_aprobados", 3);
		// request.setAttribute("libros_pendientes", 2);

		// CUIDADO: mirar la URL del servlet "/frontoffice/inicio"
		// cuando hacemos forward se pierde lo ultimo de la url y se le suma la variabel
		// pagina
		// ----------------------------------------------------------------------------------------
		// el forward resuelve la url de la siguiente manera:
		//
		// "/frontoffice/inicio" + "index.jsp" = "/frontoffice/index.jsp"
		// ------------------------------------------------------------------------------------------
		String pagina = "index.jsp";
		LOG.debug("forward: " + pagina);

		request.getRequestDispatcher(pagina).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
