package modelo.pojo;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Libro.java pojo del objeto libro
 * 
 * @author joserra
 * @version 1.0
 * @see Genero sobre generos de los libros
 * @see Usuario usuario a quien pertenece el libro
 * 
 */
public class Libro {

	private int id;

	@NotBlank
	@Size(min = 3, max = 100, message = "La longtitud de ser entre 3 y 100 caracteres")
	private String titulo;

	@NotBlank(message = "Escribe la url de la imagen")
	private String imagen;

	@Min(value = 0, message = "Debe ser positivo")
	private float precio;

	private Date fechaCreacion;
	private Date fechaValidacion;

	private Usuario usuario;

	private Genero genero;

	public Libro() {
		super();
		this.id = 0;
		this.titulo = "";
		this.imagen = "https://picsum.photos/100/100";
		this.precio = 0;
		this.usuario = new Usuario();
		this.genero = new Genero();

	}

	public Libro(String titulo) {
		this();
		this.titulo = titulo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;

	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaValidacion() {
		return fechaValidacion;
	}

	public void setFechaValidacion(Date fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", imagen=" + imagen + ", precio=" + precio
				+ ", fechaCreacion=" + fechaCreacion + ", fechaValidacion=" + fechaValidacion + ", usuario=" + usuario
				+ ", genero=" + genero + "]";
	}

}
