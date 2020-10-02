package modelo.modeloDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import modelo.conexion.ConnectionManager;
import modelo.modeloDAO.GeneroDAO;
import modelo.modeloDAO.SeguridadException;
import modelo.pojo.Genero;
import modelo.pojo.Libro;

public class GeneroDAOImpl implements GeneroDAO {

	private static GeneroDAOImpl INSTANCE = null;
	private final static Logger LOG = Logger.getLogger(GeneroDAOImpl.class);

	private GeneroDAOImpl() {
		super();
	}

	public static synchronized GeneroDAOImpl getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new GeneroDAOImpl();
		}

		return INSTANCE;
	}

	// excuteQuery => ResultSet
	private final String SQL_GET_ALL = " SELECT id, genero FROM genero ORDER BY genero ASC; ";
	private final String SQL_GET_ALL_WITH_LIBROS = "SELECT g.id'genero_id', " + "g.genero'genero'," + "l.id 'idLibro',"
			+ "l.titulo'tituloLibro', " + "l.imagen," + "l.precio " + "FROM  genero g," + "libro l "
			+ "WHERE l.genero =g.id " + "ORDER BY g.genero ASC;";
	private final String SQL_GET_BY_ID = " SELECT id, genero FROM genero WHERE id = ?; ";

	// executeUpdate => int affectedRows

	private final String SQL_INSERT = " INSERT INTO genero ( genero ) VALUES ( ? ) ; ";
	private final String SQL_UPDATE = " UPDATE genero SET genero = ?  WHERE id = ? ; ";
	private final String SQL_DELETE = " DELETE FROM genero WHERE id = ? ; ";

	@Override
	public ArrayList<Genero> getAll() {

		ArrayList<Genero> registros = new ArrayList<Genero>();

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();) {

			LOG.debug(pst);
			while (rs.next()) {
				registros.add(mapper(rs));
			} // while

		} catch (Exception e) {
			LOG.error(e);

		}
		return registros;

	}

	@Override
	public ArrayList<Genero> getAllLibros() {

		// La clave va a ser Integer, con el id de Genero

		HashMap<Integer, Genero> registros = new HashMap<Integer, Genero>();

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_ALL_WITH_LIBROS);
				ResultSet rs = pst.executeQuery();) {

			LOG.debug(pst);
			while (rs.next()) {

				int idGenero = rs.getInt("genero_Id"); // Key del Hashmap
				Genero g = registros.get(idGenero);

				if (g == null) {
					g = new Genero();
					g.setId(idGenero);
					g.setGenero(rs.getString("genero"));

					Libro l = new Libro();
					l.setId(rs.getInt("idLibro"));
					l.setTitulo("tituloLibro");
					l.setImagen("imagen");
					l.setPrecio(rs.getFloat("precio"));

					// Se recuperan los libros y se añade uno mas dentro de la categoría.
					g.getLibros().add(l);

					// Guardar en el HashMap la categoría .

					registros.put(idGenero, g);
				}

			} // while

		} catch (Exception e) {

			LOG.error(e);

		}
		return new ArrayList<Genero>(registros.values());

	}

	@Override
	public Genero getById(int id) throws Exception {

		Genero registro = new Genero();

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_BY_ID);) {

			pst.setInt(1, id);
			LOG.debug(pst);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				registro = mapper(rs);

			} else {
				throw new Exception("No se puede encontrar registro con id=" + id);
			}

			return registro;

		}

	}

	@Override
	
	
	public Genero delete(int id) throws Exception {

		Genero genero = null;

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_DELETE);) {
			// recuperar antes de eliminar
			genero = getById(id);

			// eliminar
			pst.setInt(1, id);
			LOG.debug(pst);
			pst.executeUpdate();

		}

		return genero;

	}

	@Override
	
	
	public Genero insert(Genero genero) throws Exception {
		
		try(
				Connection conexion = ConnectionManager.getConnection();	
				PreparedStatement pst = conexion.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);				
			){
		
				pst.setString(1, genero.getGenero() );			
				LOG.debug(pst);
				
				int affectedRows = pst.executeUpdate();			
				if ( affectedRows == 1 ) {					
					try( ResultSet rsKeys = pst.getGeneratedKeys() ){						
						if ( rsKeys.next() ) {
							int id = rsKeys.getInt(1);
							genero.setId(id);
						}						
					}				
					
			}else {				
				throw new Exception("No se ha podido guardar el registro " + genero );
			}
		}
		
		return genero;
	}

	@Override
	
	public Genero update(Genero pojo) throws Exception {
		throw new Exception("Sin implementar de momento");
	}

	private Genero mapper(ResultSet rs) throws SQLException {
		Genero g = new Genero();
		g.setId(rs.getInt("id"));
		g.setGenero(rs.getString("genero"));
		return g;
	}

}
