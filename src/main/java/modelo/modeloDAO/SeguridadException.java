package modelo.modeloDAO;

public class SeguridadException extends Exception {
	private static final long serialVersionUID = 1L;
	public static final String MENSAJE = "No tiene privilegios para manipular este registro";

	public SeguridadException() {
		super(MENSAJE);
	}

}
