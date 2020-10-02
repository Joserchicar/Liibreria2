package controller.Backoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



import modelo.modeloDAOImpl.GeneroDAOImpl;
import modelo.modeloDAOImpl.LibroDAOImpl;
import modelo.pojo.Genero;
import modelo.pojo.Libro;
import modelo.pojo.Usuario;
/**
 * Servlet implementation class CrawlerController
 */
@WebServlet("/views/backoffice/Crawler")
public class CrawlerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(CrawlerController.class);
	private final static GeneroDAOImpl daoGenero = GeneroDAOImpl.getInstance();
	private final static LibroDAOImpl daoLibro = LibroDAOImpl.getInstance();   
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("inicio");
		request.setAttribute("generos", daoGenero.getAll() );
		request.getRequestDispatcher("crawler.jsp").forward(request, response);
		
	}

	/**
	 * @param genero 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response, String genero) throws ServletException, IOException {
		int cont = 0;
		int contError = 0;
		Usuario usuario = null;
				
		String gen = request.getParameter("gen");
		String url = request.getParameter("url");
		LOG.trace( String.format("parametros url %s generoId %s", url, gen));
		
		try {
			
			int idGenero = Integer.parseInt(gen);
			usuario = (Usuario)request.getSession().getAttribute("usuario_login");
			
			final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:79.0) Gecko/20100101 Firefox/79.0"; 
			Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();			
			Elements eNombres = doc.select("a.product");
			Libro l = null;
			Genero g = null;
			Usuario u = null;
			for (Element element : eNombres) {
				
				try {
					String nombre = element.select("span.desc-height strong").first().text();				
					
					String precioInicio = element.select("span.price-height span b").last().text().replace("*", "");  // 9.88*
					float precio = Float.parseFloat(precioInicio);
					
					// Imagen
					String imagenUrl = element.select("img").attr("src");
					String[] imagenSplit = imagenUrl.split("/");
					String imagenNombre = imagenSplit[ (imagenSplit.length -1 )];
					
					String urlImagen = "https://www.lidl.es" + imagenUrl;
					String pathImagen = "/home/javaee/eclipse-workspace/supermercado-java/src/main/webapp/imagenes/" + imagenNombre;
					Utilidades.downloadImage( urlImagen, pathImagen);
					
					
					// guardar pojo
					l = new Libro();
					l.setTitulo(nombre);
					l.setImagen(imagenNombre);
					l.setPrecio(precio);
					
					g = new Genero();
					g.setId(idGenero);
					g.setGenero(genero);
					
					u = new Usuario();
					u.setId( usuario.getId() );
					l.setUsuario(u);
					
					daoLibro.insert(l);
					
					LOG.debug("Insertado Libro " + l);
					cont++;
				}catch (Exception e) {
					LOG.error(e.getMessage());
					contError++;
				}	
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			
		}finally {
			request.setAttribute("contError", contError);
			request.setAttribute("cont", cont );
			request.setAttribute("categorias", daoGenero.getAll() );
			request.getRequestDispatcher("crawler.jsp").forward(request, response);
		}
		
		
	}

}
