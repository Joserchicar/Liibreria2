package modelo.pojo;

import java.util.ArrayList;

public class Genero {

	private int id;
	private String genero;
	private ArrayList<Libro>libros;
	
	
	public Genero() {
		super();
		this.id = 0;
		this.genero = "";
		this.libros=new ArrayList<Libro>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public ArrayList<Libro> getLibros() {
		return libros;
	}

	public void setLibros(ArrayList<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public String toString() {
		return "Genero [id=" + id + ", genero=" + genero + "]";
	}


		
	}
	
	
	
	
	
