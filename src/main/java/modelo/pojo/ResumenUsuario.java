package modelo.pojo;

public class ResumenUsuario {

	int idUsuario;
	int librosTotal;
	int librosAprobados;
	int librosPendientes;
	
	
	public ResumenUsuario() {
		super();
	
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public int getLibrosTotal() {
		return librosTotal;
	}


	public void setLibrosTotal(int librosTotal) {
		this.librosTotal = librosTotal;
	}


	public int getLibrosAprobados() {
		return librosAprobados;
	}


	public void setLibrosAprobados(int librosAprobados) {
		this.librosAprobados = librosAprobados;
	}


	public int getLibrosPendientes() {
		return librosPendientes;
	}


	public void setLibrosPendientes(int librosPendientes) {
		this.librosPendientes = librosPendientes;
	}


	@Override
	public String toString() {
		return "ResumenUsuario [idUsuario=" + idUsuario + ", librosTotal=" + librosTotal + ", librosAprobados="
				+ librosAprobados + ", librosPendientes=" + librosPendientes + "]";
	}

	
	
}
