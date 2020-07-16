package controller;

public class Alerta {

	private String tipo;
	private String texto;
	
	
	public Alerta() {
		super();
		this.texto="";
		this.tipo="";
		
	}


	public Alerta(String tipo, String texto) {
		super();
		this.tipo = tipo;
		this.texto = texto;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	@Override
	public String toString() {
		return "alerta [tipo=" + tipo + ", texto=" + texto + "]";
	}

}
