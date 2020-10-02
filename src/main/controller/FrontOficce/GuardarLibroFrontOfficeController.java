package controller.FrontOficce;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.pojo.Categoria;
import com.ipartek.formacion.modelo.pojo.Producto;

import controller.Alerta;
import modelo.dao.SeguridadException;
import modelo.modeloDAOImpl.LibroDAOImpl;
import modelo.pojo.Genero;
import modelo.pojo.Libro;
import modelo.pojo.Usuario;

/**
 * Servlet implementation class CrearLibroFrontOfficeController
 */
@WebServlet("/views/frontoffice/guardarLibro")
public class GuardarLibroFrontOfficeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(GuardarLibroFrontOfficeController.class);
	private final static LibroDAOImpl daoLibro = LibroDAOImpl.getInstance();
	private static final String PATH_FICHERO = null;

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String paramId = request.getParameter("id");
		Libro l = new Libro();
		HttpSession session = request.getSession();			
		Usuario usuario = new Usuario();
		String view = "formulario.jsp";
		
try {
			
			usuario = (Usuario)session.getAttribute("usuario_login");
			int idUsuario = usuario.getId();
			int idLibro = Integer.parseInt(paramId);
			
			// recuperar solo si es diferente de Cero, si id == 0 es un NUEVO producto 
			if ( idLibro != 0 ) {
				l = daoLibro.checkSeguridad(idLibro, idUsuario);
			}				
			
			
		}catch (SeguridadException e) {
			view = "/views/frontoffice/inicio";
			LOG.error("SE estan saltando la seguridad " + usuario);
			
		}catch (Exception e) {
			LOG.error(e);
			
		}finally {
			request.setAttribute("libro", l);
			request.getRequestDispatcher(view).forward(request, response);
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Alerta alerta = new Alerta();
		Libro l = new Libro();
		Usuario usuario = new Usuario();
		HttpSession session = request.getSession();	
		String view = "formulario.jsp";

		// recoger parametros del formulario

		String idParametro = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String precio = request.getParameter("precio");
		String imagen = request.getParameter("imagen");
		String generoId = request.getParameter("genero_id");
		Part filePart =request.getPart("fichero");// Retrieves <input type="file" name="file">
		//String imagen = request.getParameter("imagen");	// anteriormente el parmetro imagen  
		
		try {

			int idLibro = Integer.parseInt(idParametro);
			usuario = (Usuario)session.getAttribute("usuario_login");
			int idUsuario = usuario.getId();
			
			/* **************************************************************** 
			 * Comprobar Seguridad, siempre que no sea un nuevo Producto 
			 * ***************************************************************/
			if ( idLibro != 0 ) {
				l = daoLibro.checkSeguridad(idLibro, idUsuario); // lanza SeguridadException si no le pertenece el producto
			}
			
			
			int idGenero = Integer.parseInt(generoId);
			float precioFloat = Float.parseFloat(precio);
			
			// crear objeto con esos parametros
			l.setId(idLibro);
			l.setTitulo(titulo);
			String fichNombre =  filePart.getSubmittedFileName();
			l.setImagen( "imagenes/" + fichNombre);
			//l.setImagen(imagen);
			l.setPrecio(precioFloat);
			
			Genero g = new Genero();
			g.setId(idGenero);
			l.setGenero(g);		
			
			// recuperar usuario de session y setearlo en el producto				
			l.setUsuario(usuario);
			
			
			// validar pojo				
			Set<ConstraintViolation<Libro>> violations = validator.validate(l);
			
			if ( violations.isEmpty() ) {	
				
				/* GUARDAR PRODUCTO EN BBDD */
				if ( idLibro == 0 ) {
					daoLibro.insert(l);
					uploadImagen(filePart, fichNombre, PATH_FICHERO);
				}else {
					daoLibro.updateByUser(l);
				}
				alerta = new Alerta( "success", "Una vez creado el producto, deberas esperar unas horas hasta que se validen sus datos.");
				
			}else {
				String errores = "";
				for (ConstraintViolation<Libro> v : violations) {					
					errores += "<p><b>" + v.getPropertyPath() + "</b>: "  + v.getMessage() + "</p>";					
				}				
				alerta = new Alerta( "warning", errores );
			}
			
		}catch (SeguridadException e) {
			view = "/views/frontoffice/inicio";
			LOG.error(" Intentan saltarse la seguridad " + usuario );	
	
		}catch (Exception e) {				
			LOG.error(e);
			alerta = new Alerta( "warning", "Lo sentimos pero ese nombre ya esta registrado" );
			
		}finally {
		
			request.setAttribute("alerta", alerta);
			request.setAttribute("libro", l);
			request.getRequestDispatcher(view).forward(request, response);
		}	
	}
	/**
	 * Guardamos un fichero 
	 * 
	 * @param filePart file input recogido del formulario
	 * @param fichNombre nombre de la imagen
	 * @param path ruta donde guardamos la imagen
	 * 
	 * @throws IOException si no existe la imagen
	 * @throws Exception si no es del tipo png o jpg, o tamaño mayor que 1Gb
	 */
	private void uploadImagen(Part filePart, String fichNombre, String path) throws IOException, Exception{
		
		long fichTamanio = filePart.getSize();			
		LOG.debug( "Fichero nombre: " + fichNombre + " tamaño: " + fichTamanio + " bytes");
		
		//TODO Exception si no es del tipo png o jpg, o tamaño mayor que 1Gb
		
		InputStream fichContent = filePart.getInputStream();				
		File file = new File( path + fichNombre );
		Files.copy(fichContent, file.toPath());
		
		
		
		LOG.info("Imagen subida " + PATH_FICHERO + fichNombre );
		
		
	}
}	

		