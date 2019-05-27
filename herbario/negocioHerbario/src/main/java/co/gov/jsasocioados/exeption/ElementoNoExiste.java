/**
 * 
 */
package co.gov.jsasocioados.exeption;

/**
 * Excepcion que es lanzada cuando un elemento no ha sido creado
 *
 */
public class ElementoNoExiste extends Exception {
	/**
	 * 
	 */
	public ElementoNoExiste(String mensaje) {
		super(mensaje);
	}
}
