package modelo.modeloDAO;

import java.util.ArrayList;



import modelo.pojo.CRUDAble;
import modelo.pojo.Genero;

public interface GeneroDAO extends CRUDAble<Genero> {

	/*
	 * Metodo que obtiene todos los generos literarios 
	 * 
	 * @return {@code ArrayList<Genero>} ordenados alfabeticamente.
	 * 
	 * SQL_GET_ALL = " SELECT id, genero FROM genero ORDER BY genero ASC; ";
	 * 
	 * */
	public ArrayList<Genero> getAll();
	

	/*
	 * Metodo que obtiene todos los generos literarios con los libros que tiene asociado.
	 * 
	 * @return ArrayList<Genero> ordenados alfabeticamente.
	 * 
	 * SQL_GET_ALL_WITH_LIBROS = "SELECT g.id'genero_id', " + "g.genero'genero'," + "l.id 'idLibro',"
			+ "l.titulo'tituloLibro', " + "l.imagen," + "l.precio " + "FROM  genero g," + "libro l "
			+ "WHERE l.genero =g.id " + "ORDER BY g.genero ASC;";
	 * */
	
	public ArrayList<Genero>getAllLibros();
	
	
}
