/**
 * 
 */
package co.edu.uniquindio.grid.excesiones;

/**
 * Se lanza cuando no se encuentra algun elemento en la basa de datos
 * 
 * @author EinerZG
 * @version 1.0
 */
public class ElementoNoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ElementoNoEncontradoException(String mensaje) {
		super(mensaje);
	}

}
