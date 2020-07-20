package modelo.modeloDAO;

import java.util.ArrayList;

import modelo.pojo.CRUDAble;
import modelo.pojo.Libro;

/**
 * Hereda los metodos basicos de la interfaz CrudAble Ademas definie un nuevo:
 * ArrayList<Libro> getAllByTitulo( String titulo )
 * 
 * @author javaee
 *
 */

public interface LibroDAO extends CRUDAble<Libro> {

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
	ArrayList<Libro> getAllByGenero(int idGenero, int numReg);

	
	ArrayList<Libro>getAllRangoPrecio(int precioMinimo,int precioMaximo) throws Exception;;
	
	}

	


