
package modelo.pojo;

import java.util.ArrayList;
/**
 * Vamos a definir la operaciones Básicas de CRUD
 * P es una clase GENERICA se diferencia porque siempre un una LETRA MAYUSCULA
 * Esa P luego podemos sustituirla por cualquier Clase
 * @author joserra
 *
 */



public interface CRUDAble<P> {

	ArrayList<P> getAll() throws Exception;

	P getById(int id) throws Exception;

	/**
	 * 
	 * @param id int id del objeto a borrar
	 * return P  objeto P borrado
	 * @throws for java.lang.Exception 
	 * 
	 */
	P delete(int id) throws Exception;

	/**
	 * @param p   objeto de la clase P
	 * @return    objeto P
	 */
	P insert(P p) throws Exception;

	/**
	 * @param   p  objeto P 
	 * @return objeto P 
	 * @throws for java.lang.Exception
	 */
	P update(P p) throws Exception;

}
