package modelo.modeloDAO;

import java.util.ArrayList;



import modelo.pojo.CRUDAble;
import modelo.pojo.Genero;

public interface GeneroDAO extends CRUDAble<Genero> {

	public ArrayList<Genero> getAll();
	

	/*
	 * Metodo que obtiene todos los generos literarios con los libros que tiene asociado.
	 * 
	 * @return ArrayList<Genero> ordenados alfabeticamente.
	 * */
	
	public ArrayList<Genero>getAllLibros();
	
}
