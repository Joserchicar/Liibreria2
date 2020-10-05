package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.modeloDAOImpl.UsuarioDAOImpl;
import modelo.pojo.Rol;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		String contrasenia = request.getParameter("contrasenia");

		HttpSession session = request.getSession();

		UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();
		Usuario usuario = dao.existe(nombre, contrasenia);

		if (usuario != null) {

			session.setMaxInactiveInterval(60 * 5); // 5 minutos sin peticiones, se invalida la session del usuario
			session.setAttribute("usuario_login", usuario);

			// usuarios conectados recuperar y actualizar, inicializado en
			// InicioAppListenner
			ServletContext sc = request.getServletContext();
			int usuariosConectados = (int) sc.getAttribute("usuarios_conectados");
			sc.setAttribute("usuarios_conectados", ++usuariosConectados);

			request.setAttribute("alerta", new Alerta("success", "Ongi Etorri, ya estas Logeado"));

			if (usuario.getRol().getId() == Rol.ADMINISTRADOR) {
				// request.getRequestDispatcher("views/backoffice/inicioBacktOffice").forward(request,
				// response);
				response.sendRedirect("views/backoffice/inicio");
			} else {
				// request.getRequestDispatcher("views/frontoffice/inicioBacktOffice").forward(request,
				// response);
				response.sendRedirect("views/frontoffice/inicio");
			}

		} else {

			request.setAttribute("alerta", new Alerta("warning", "Credenciales Incorrectas"));
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		}

	}

}
