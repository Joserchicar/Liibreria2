package modelo.modeloDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import modelo.conexion.ConnectionManager;
import modelo.modeloDAO.UsuarioDAO;
import modelo.pojo.Rol;
import modelo.pojo.Usuario;

import java.sql.DriverManager;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {

	private static final Logger LOG = Logger.getLogger(UsuarioDAOImpl.class);
	private static UsuarioDAOImpl INSTANCE = null;

	// executequery=>ResultSet
	static final String SQL_GET_ALL = " SELECT u.id, u.nombre, contrasenia, id_rol, r.nombre AS 'nombre_rol' "
			+ "FROM usuario AS u " + "INNER JOIN rol AS r ON u.id_rol = r.id " + "ORDER BY u.id DESC LIMIT 500 ;";
	static final String SQL_GET_BY_ID = " SELECT u.id, u.nombre, contrasenia, id_rol, r.nombre AS 'nombre_rol'"
			+ " FROM usuario AS u INNER JOIN rol AS r ON u.id_rol = r.id WHERE u.id = ? ; ";
	static final String SQL_GET_ALL_BY_NOMBRE = " SELECT u.id, u.nombre, contrasenia, id_rol, r.nombre AS 'nombre_rol' "
			+ "FROM usuario AS u INNER JOIN rol AS r ON u.id_rol = r.id WHERE nombre LIKE ? LIMIT 500 ;   ";
	static final String SQL_EXISTE = " SELECT u.id, u.nombre, contrasenia, id_rol, r.nombre AS 'nombre_rol' "
			+ "FROM usuario AS u " + "INNER JOIN rol AS r ON u.id_rol = r.id "
			+ "WHERE u.nombre = ? AND contrasenia = ? ; ";

	// executeUpdate => int
	static final String SQL_INSERT = " INSERT INTO usuario(nombre, contrasenia, id_rol) VALUES( ? , ? , ? ); ";
	static final String SQL_DELETE = " DELETE FROM usuario WHERE id = ? ;";
	static final String SQL_UPDATE = " UPDATE usuario SET nombre = ?, contrasenia = ? , id_rol = ? WHERE id = ? ; ";

	private UsuarioDAOImpl() {
		super();
	}

	public static synchronized UsuarioDAOImpl getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAOImpl();
		}

		return INSTANCE;
	}

	@Override
	public ArrayList<Usuario> getAll() throws Exception {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();) {

			LOG.debug(pst);

			while (rs.next()) {
				usuarios.add(mapper(rs));

			}

		} catch (Exception e) {
			LOG.error(e);

		}

		return usuarios;
	}

	@Override
	public Usuario getById(int id) throws Exception {
		Usuario usuario = new Usuario();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_BY_ID);

		) {

			pst.setInt(1, id);
			LOG.debug(pst);
			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					usuario = mapper(rs);
				} else {
					throw new Exception("Usuario no encontrado id = " + id);
				}

			} // 2º try

		} catch (Exception e) {
			LOG.error(e);
		}

		return usuario;
	}
	
	@Override
	public ArrayList<Usuario> getAllByNombre(String palabraBuscada) {
		ArrayList<Usuario> registros = new ArrayList<Usuario>();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_GET_ALL_BY_NOMBRE);) {

			pst.setString(1, "%" + palabraBuscada + "%");
			LOG.debug(pst);
			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					registros.add( mapper(rs) );
				} // while

			} // 2º try

		} catch (Exception e) {
			LOG.error(e);
		}

		return registros;
	}

	@Override
	public Usuario delete(int id) throws Exception {
		Usuario usuario = getById(id);

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETE);) {

			pst.setInt(1, id);
			LOG.debug(pst);
			if (pst.executeUpdate() != 1) {
				throw new Exception("No se puede eliminar registro " + id);
			}

		}

		return usuario;
	}
	/**
	 * Inserta un nuevo usuario en la tabla <b>usuario</b>
	 * @param usuario necesitamos que esten rellenos los atributos de: <b>nombre</b>, <b>contrasenia</b> y <b>Rol (solo su id)</b>
	 * @see com.ipartek.formacion.modelo.pojo.Rol
	 * @return Usuario con el id actualizado
	 * @throws Exception Si el nombre del usuario ya existe ne la bbdd
	 */
	@Override
	public Usuario insert(Usuario p) throws Exception {
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {

			pst.setString(1, p.getNombre() );
			pst.setString(2, p.getContrasenia() );
			pst.setInt(3, p.getRol().getId() );
			
			LOG.debug(pst);
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {

				try (ResultSet rsKeys = pst.getGeneratedKeys()) {

					if (rsKeys.next()) {
						p.setId(rsKeys.getInt(1));
					}
				}

			} else {
				throw new Exception("No se puede insertar registro " + p);
			}

		}

		return p;
	}
	@Override
	public Usuario update(Usuario p) throws Exception {
		throw new Exception("Sin implementar de momento");
	}

	@Override
	public Usuario existe(String nombre, String contrasenia) {

		Usuario usuario = null;

		try (Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_EXISTE);

		) {

			pst.setString(1, nombre);
			pst.setString(2, contrasenia);
			LOG.debug(pst);

			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {

					usuario = mapper(rs);
				}

			} // try

		} catch (Exception e) {

			LOG.error(e);

		}

		return usuario;
	}

	private Usuario mapper(ResultSet rs) throws SQLException {

		Usuario usuario = new Usuario();

		usuario.setId(rs.getInt("id"));
		usuario.setNombre(rs.getString("nombre"));
		usuario.setContrasenia(rs.getString("contrasenia"));

		// rol
		Rol rol = new Rol();
		rol.setId(rs.getInt("id_rol"));
		rol.setNombre(rs.getString("nombre_rol"));

		// setear el rol al usuario
		usuario.setRol(rol);

		return usuario;

	}

	
}
