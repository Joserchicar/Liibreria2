package modelo.modeloDAO;

import java.util.ArrayList;

import modelo.pojo.CRUDAble;
import modelo.pojo.Libro;
import modelo.pojo.ResumenUsuario;

/**
 * Hereda los metodos basicos de la interfaz CrudAble Ademas define algunos
 * nuevos: {@code ArrayList<libro> getAllByTitulo(String titulo)}. void validar(
 * int id ). Libro checkSeguridad(int idLibro, int idUsuario), Libro getById(int
 * idLibro,int idUsuario), {@code ArrayList<Libro> getLast(int numReg)},
 * {@code ArrayList<Libro>getAllRangoPrecio(int precioMinimo,int precioMaximo)}
 * 
 * @author Joserra
 * @version V1.0
 */

public interface LibroDAO extends CRUDAble<Libro> {

	/**
	 * Validamos el libro para que sea visible en la parte publica
	 * 
	 * @param id int id identificador del libro
	 * 
	 * 
	 */
	void validar(int id);

	/**
	 * Eliminar un registro comprobando que pertenezca a un usuario concreto.
	 * 
	 * @param idLibro   int id del libro a borrar
	 * @param idUsuario int id del usuario
	 * @return Libro eliminado
	 * @throws Exception excepcion general
	 * 
	 */

	Libro delete(int idLibro, int idUsuario) throws Exception;

	/**
	 * Comprueba que el Libro pertenezca al Usuario
	 * 
	 * @param idLibro   int id del libro
	 * @param idUsuario int id del usuario
	 * @return Libro perteneciente al idUsuario
	 * @throws Exception          si los parametros no coinciden con ningún libro
	 *                            existente
	 * @throws SeguridadException si no pertenece el libro al Usuario
	 */
	Libro checkSeguridad(int idLibro, int idUsuario) throws Exception, SeguridadException;

	/**
	 * 
	 * @param nombre String titulos de los libros
	 * @return {@code ArrayList<libros> } ordenados por titulo
	 */
	// ArrayList<Libro> getAllByNombre(String nombre);

	/**
	 * 
	 * @param idLibro   int id del libro
	 * @param idUsuario int id del usuario
	 * @return registro. denominaion de un nuevo objeto Libro
	 * @throws Exception general
	 */

	Libro getById(int idLibro, int idUsuario) throws Exception;

	/**
	 * 
	 * @param titulo String titulo de los libros
	 * @return {@code ArrayList<libros> } ordenados por titulo
	 * 
	 *
	 */
	ArrayList<Libro> getAllByTitulo(String titulo);

	/**
	 * 
	 * Obtiene lo libros de un usuario.Pueden estar validados o no.
	 * 
	 * @param idUsuario  int identificador del usuario
	 * @param isValidado boolean true para mostrar los libros con fecha_validacion,
	 *                   false para mostrar los pendientes de validar
	 * @return {@code ArrayList<libros> } validados o no validados
	 */

	ArrayList<Libro> getAllByUser(int idUsuario, boolean isValidado);

	/**
	 * Obtiene los ultimos registros ordenador por id descentente
	 * 
	 * @param numReg int numero de registros a recuperar
	 * @return {@code ArrayList<libros> }
	 */
	ArrayList<Libro> getLast(int numReg);

	/**
	 * Obtienes los libros pertenecientes a un genero
	 * 
	 * @param idGenero int identificador del Genero
	 * @param numReg   int numero de registros a mostrar
	 * @return {@code ArrayList<libros> }
	 */
	ArrayList<Libro> getAllByGenero(int idGenero, int numReg);

	/**
	 * 
	 * @param precioMinimo int el precio minimo del rango
	 * @param precioMaximo int el precio maximo del rango
	 * @return {@code ArrayList<libros> } dentro del rango del precio de los
	 *         parametros
	 * @throws Exception general
	 */
	ArrayList<Libro> getAllRangoPrecio(int precioMinimo, int precioMaximo) throws Exception;;

	/**
	 * Obtiene los datos estadisticos del usuario y sus libros
	 * 
	 * @see ResumenUsuario
	 * @param idUsuario int id del usuario
	 * @return ResumenUsuario. Resut con los datos del resumen obtenido.
	 */
	ResumenUsuario getResumenByUsuario(int idUsuario);

	/**
	 * Modifica un libro un Usuario normal, el cual no es Administrador. El producto
	 * vuelve a estar pendiente de Validación
	 * 
	 * @param l objeto Libro
	 * @return Libro validado
	 * @throws Exception          el libro no existe o alguno de los parametros no
	 *                            es correcto
	 * @throws SeguridadException el libro no pertenece al usuario que intenta
	 *                            modificarlo.
	 */
	Libro updateByUser(Libro l) throws Exception, SeguridadException;

}
