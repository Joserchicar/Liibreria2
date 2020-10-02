package modelo.modeloDAO;

import modelo.pojo.CRUDAble;
import modelo.pojo.Usuario;
import java.util.ArrayList;


public interface UsuarioDAO extends CRUDAble<Usuario> {

	/**
	 * Busca usuarios que contengan la palabraBuscada
	 * @param palabraBuscada palabra a buscar
	 * @return {@code ArrayList<usuario>}
	 * 
	 * Ej: si palabraBuscada = "a"   <br>
	 *     lista [ "ander", "pepe", "manolo"] <br>
	 *     retorna ["ander","manolo"] <br>
	 *     
	 *  SQL = SELECT id, nombre FROM alumno WHERE nombre LIKE '%a%' ORDER BY nopmbre ASC;   
	 * 
	 */
	ArrayList<Usuario> getAllByNombre( String palabraBuscada );
	
	
	
	
	/**
	 * Busca si existe el usuario en la base datos
	 * @param nombre
	 * @param contrasenia
	 * @return Usuario con datos si lo encuentra, si no existe retorna null
	 */
	Usuario existe ( String nombre, String contrasenia ); 	
	
	
	
	
	
	
	
}
