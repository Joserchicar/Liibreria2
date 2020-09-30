package modelo.modeloDAO;

import java.util.ArrayList;

import javax.security.sasl.SaslException;

import modelo.pojo.CRUDAble;
import modelo.pojo.Libro;
import modelo.pojo.ResumenUsuario;

/**
 * Hereda los metodos basicos de la interfaz CrudAble Ademas definie un nuevo:
 * ArrayList<Libro> getAllByTitulo( String titulo )
 * 
 * @author javaee
 *
 */

public interface LibroDAO extends CRUDAble<Libro> {
	
	/**
	 * Validamos el libro para que sea visible en la parte publica
	 * @param id identificador del libro
	 * 
	 */
	void validar( int id );	
	
	/**
	 * Eliminar un registro comprobando que pertenezca a un usuario concreto.
	 * @param idLibro
	 * @param idUsuario
	 * @return Libro eliminado
	 * @throws Exception 
	 * 
	 */
	
	Libro delete(int idLibro, int idUsuario) throws Exception;
	
	/**
	 * Comprueba que el Producto pertenezca al Usuario
	 * @param idProducto
	 * @param idUsuario
	 * @return Producto pertenecinte al idUsuario
	 * @throws Exception
	 * @throws SeguridadException si no pertenece el producto al Usuario
	 */
	Libro checkSeguridad(int idLibro, int idUsuario) throws Exception, SeguridadException;
	
	/**
	 * 
	 * @param nombre
	 * @return
	 */
	ArrayList<Libro> getAllByNombre( String nombre );
	/**
	 * 
	 * @param idLibro
	 * @param idUsuario
	 * @return
	 * @throws Exception 
	 */
	
	Libro getById(int idLibro,int idUsuario) throws Exception;
	
	/**
	 * 
	 * @param titulo
	 * @return
	 */
	ArrayList<Libro> getAllByTitulo(String titulo);

	/**
	 * 
	 * Obtiene lo libros de un usuario.Pueden estar validados o no.
	 * @param idUsuario int identificador del usuario
	 * @param isValidado boolean true para mostrar los  libros con fecha_validacion, false para mostrar los pendientes de validar
	 * @return
	 */
	
	ArrayList<Libro>getAllByUser(int idUsuario,boolean isValidado);
	
	
	/**
	 * Obtiene los ultimos registros ordenador por id descentente
	 * 
	 * @param numReg int numero de registros a recuperar
	 * @return ArrayList<Libro>
	 */
	ArrayList<Libro> getLast(int numReg);

	/**
	 * Obtienes los libros de un Genero
	 * 
	 * @param idGenero int identificador del Genero
	 * @param numReg   int numero de registgros a mostrar
	 * @return ArrayList<Libro>
	 */
	ArrayList<Libro> getAllByGenero( int idGenero, int numReg );
	

	/**
	 * 
	 * @param precioMinimo
	 * @param precioMaximo
	 * @return
	 * @throws Exception
	 */
	ArrayList<Libro>getAllRangoPrecio(int precioMinimo,int precioMaximo) throws Exception;;
	
	/**
	 * Obtiene los datos estaisticos del usuario y sus libros
	 * @see ResumenUsuario
	 * @param idUsuario
	 * @return ResumenUsuario
	 */
	ResumenUsuario getResumenByUsuario(int idUsuario);
	
	/**
	 * Modifica un producto un Usuario normal, el cual no es Administrador.
	 * El producto vuelve a estar pendiente de Validación
	 * @param p Producto
	 * @return Producto validao
	 * @throws Exception
	 * @throws SeguridadException
	 */
	Libro updateByUser( Libro l) throws Exception, SeguridadException;
	
	
	
	
	}

	


