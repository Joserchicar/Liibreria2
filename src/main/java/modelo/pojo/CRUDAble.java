
package modelo.pojo;

import java.util.ArrayList;

/**
 * Vamos a definir la operaciones Básicas de CRUD P es una clase GENERICA se
 * diferencia porque siempre un una LETRA MAYUSCULA Esa P luego podemos
 * sustituirla por cualquier Clase
 * 
 * @author joserra
 * @version 1.0
 */

public interface CRUDAble<P> {
	/**
	 * Crea una lista de objetos
	 * 
	 * @return {@code ArrayList<P> getAll()}
	 * @throws Exception excepción
	 */
	ArrayList<P> getAll() throws Exception;

	/**
	 * 
	 * @param id int id del objeto
	 * @return el objeto
	 * @throws Exception no encontrado el objeto
	 */

	P getById(int id) throws Exception;

	/**
	 * 
	 * @param id int id del objeto a borrar return P objeto P borrado
	 * @return P devuelve un objeto borrado
	 * @throws Exception controla los posibles errores al introducir el tipo de
	 *                   parametro.
	 * 
	 */
	P delete(int id) throws Exception;

	/**
	 * @param p objeto de la clase P
	 * @return Pp . objeto de clase P
	 * @throws Exception no se inserta el objeto
	 */
	P insert(P p) throws Exception;

	/**
	 * @param p objeto P
	 * @return objeto P
	 * @throws Exception Excepcion general. No se puede modificar el objeto
	 */
	P update(P p) throws Exception;

}
