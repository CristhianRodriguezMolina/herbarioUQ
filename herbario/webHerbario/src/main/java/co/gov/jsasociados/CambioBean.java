/**
 * 
 */
package co.gov.jsasociados;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

/**
 * Permite realiza cambios de informacion entre dos atributos
 * 
 * @author Cristian Rodriguez
 * @author Sergio Osorio
 * @author Jhonatan Hidalgo
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "cambioBean")
@ApplicationScoped
public class CambioBean {
	private String atributo1;
	private String atributo2;
	
	/**
	 * 
	 */
	public CambioBean() {
		
	}
	/**
	 * permite cambiar la informacion de ...
	 */
	public void cambiar () {
		String temporal= atributo1;
		atributo1=atributo2;
		atributo2=temporal;
	}
	/**
	 * @return the atributo1
	 */
	public String getatributo1() {
		return atributo1;
	}

	/**
	 * @param atributo1 the atributo1 to set
	 */
	public void setatributo1(String atributo1) {
		this.atributo1 = atributo1;
	}

	/**
	 * @return the atributo2
	 */
	public String getatributo2() {
		return atributo2;
	}

	/**
	 * @param atributo2 the atributo2 to set
	 */
	public void setatributo2(String atributo2) {
		this.atributo2 = atributo2;
	}
}
