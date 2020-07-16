package modelo.modeloDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import modelo.conexion.ConnectionManager;
import modelo.modeloDAO.LibroDAO;
import modelo.pojo.Genero;
import modelo.pojo.Libro;

public class LibroDAOImpl implements LibroDAO {

	private static LibroDAOImpl INSTANCE = null;
private final static Logger LOG=Logger.getLogger(LibroDAOImpl.class );
	private LibroDAOImpl() {
		super();
	}

	public static synchronized LibroDAOImpl getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new LibroDAOImpl();
		}

		return INSTANCE;
	}

	// SQL   executeQuery => ResultSet
	private final String SQL_GET_ALL = "SELECT" + 
			"l.id  'libro_id'," + 
			"titulo , " + 
			"l.imagen 'imagen'," + 
			"l.precio 'precio'," + 
			"g.id  'genero_id'," + 
			"g.genero 'genero' " + 
			"FROM libro l,genero g " + 
			"WHERE l.genero = g.id "+
			"ORDER BY l.id ASC LIMIT 500;";

	private final String SQL_GET_LAST = "SELECT" + 
			" l.id  'libro_id', " + 
			" titulo ," +
			"l.imagen 'imagen'," + 
			"l.precio 'precio'," + 
			" g.id  'genero_id',"+ 
			"g.genero 'genero_genero' " +
			" FROM libro l,genero g " +
			" WHERE l.genero = g.id "+ 
			" ORDER BY l.id ASC LIMIT ? ; ";

	private final String SQL_GET_BY_GENERO = "SELECT" +
			" l.id  'libro_id', " + 
			" titulo ," + 
			"l.imagen 'imagen'," + 
			"l.precio 'precio'," + 
			" g.id  'genero_id',"+
			"g.genero 'genero_genero' " + 
			" FROM libro l,genero g " +
			" WHERE l.genero = g.id "+
			" ORDER BY g.id ASC LIMIT ? ; ";

	private final String SQL_GET_BY_ID = "SELECT " + 
			" l.id  'libro_id', " + 
			" titulo ," + 
			"l.imagen 'imagen'," + 
			"l.precio 'precio'," + 
			"g.id  'genero_id',"+ 
			"g.genero 'genero' " +
			" FROM libro l,genero g " +
			"WHERE l.genero = g.id AND l.id=? LIMIT 500;";

	private final String SQL_INSERT = " INSERT INTO libro (titulo, precio, imagen,id_usuario,genero_id) VALUES ( ?,?,?,1,? ) ; ";
	private final String SQL_UPDATE = "UPDATE libro SET nombre=?, precio=?,imagen=?,genero_id=? WHERE id=? ; ";

	private final String SQL_DELETE = " DELETE FROM libro WHERE id = ? ; ";

	@Override
	public ArrayList<Libro> getAll() throws Exception {
		
		ArrayList<Libro> registros = new ArrayList<Libro>();

		// Execute Query

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();

		) {
			LOG.debug(pst);
			while (rs.next()) {

				// guardar en lista
				registros.add(mapper(rs));

			} // while

		} catch (Exception e) {
			LOG.error(e);
			
		}

		return registros;
	}
	@Override
	public ArrayList<Libro> getLast(int numReg) {

		ArrayList<Libro> registros = new ArrayList<Libro>();

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_LAST);) {
			pst.setInt(1, numReg);
			System.out.println("SQL_GET_LAST:"+ pst);
			try (ResultSet rs = pst.executeQuery()) {
				
				LOG.debug(pst);
				while (rs.next()) {
					registros.add(mapper(rs));
				}
			}

		} catch (Exception e) {
			
			LOG.error(e);
			
		}
		return registros;
	}

	@Override
	public ArrayList<Libro> getAllByGenero(int idGenero, int numReg) {

		ArrayList<Libro> registros = new ArrayList<Libro>();

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_BY_GENERO)) {

			pst.setInt(1, idGenero);
			pst.setInt(2, numReg);
			try (ResultSet rs = pst.executeQuery()) {
				
				LOG.debug(pst);
				while (rs.next())
					registros.add(mapper(rs));
			}

		} catch (Exception e) {
			
			LOG.error(e);
			
		}

		return registros;
	}
	
	
	@Override
	public Libro getById(int id) throws Exception {

		Libro registro = new Libro();
		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_BY_ID);

		) {

			pst.setInt(1, id);
			LOG.debug(pst);
			
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {

				registro = mapper(rs);
			} else {
				throw new Exception("no se encuentra registro con id= " + id);

			}

		}
		return registro;
	}

	

	@Override
	public Libro delete(int id) throws Exception {

		// conseguir el libro antes de Eliminar
		Libro registro = getById(id);

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_DELETE);

		) {

			pst.setInt(1, id);
			LOG.debug(pst);
			
			int affectedRows = pst.executeUpdate();

			if (affectedRows != 1) {
				throw new Exception("No se puedo eliminar el registro id = " + id);
			}

		} // try
		return registro;

	}

	@Override
	public Libro insert(Libro libro) throws Exception {

		ArrayList<Libro> registros = new ArrayList<Libro>();

		// Execute Query

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_INSERT,
						PreparedStatement.RETURN_GENERATED_KEYS);) {

			
			pst.setString(1, libro.getTitulo());
			pst.setFloat(2, libro.getPrecio() );
			pst.setString(3, libro.getImagen() );
			pst.setInt(4, libro.getGenero().getId());
			LOG.debug(pst);
			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				
				// conseguir el id

				try (ResultSet rsKeys = pst.getGeneratedKeys()) {

					if (rsKeys.next()) {
						// recuperamos columnas del rs(resultSet)
						int id = rsKeys.getInt(1);
						libro.setId(id);

					}
				}

			} else {
				throw new Exception("No se ha podido guardar el registro " + libro);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return libro;
	}

	@Override
	public Libro update(Libro libro) throws Exception {

		
		// execute query

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);

		) {

			pst.setString(1, libro.getTitulo());
			pst.setString(2, libro.getImagen());
			pst.setFloat(3, libro.getPrecio());
			pst.setInt(3, libro.getGenero().getId());
			pst.setInt(5, libro.getId());
			LOG.debug(pst);
			int affectedRows = pst.executeUpdate();

			if (affectedRows != 1) {

				throw new Exception("No se puede podificar el registro con id=" + libro.getId());

			}
		}

		return libro;
	}
	@Override
	public ArrayList<Libro> getAllRangoPrecio(int precioMinimo, int precioMaximo) throws Exception {
		throw new Exception("Sin implementar");		
	}
	
	

	private Libro mapper(ResultSet rs) throws SQLException {

		Libro l = new Libro();
		Genero g = new Genero();

		l.setId(rs.getInt("libro_id"));
		l.setTitulo(rs.getString("titulo"));
		l.setImagen("imagen");
		l.setPrecio(rs.getFloat("precio"));

		g.setId(rs.getInt("genero_id"));
		g.setGenero(rs.getString("genero_genero"));

		l.setGenero(g);

		return l;

	}

	@Override
	public ArrayList<Libro> getAllByTitulo(String titulo) {
		
		return null;
	}

	



}
