package co.gov.jsasociados;

import java.io.Serializable;
import javax.persistence.*;

/**
 * clase recolector
 * 
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 * @author Sergio Osorio
 * @version 1.0 16/04/2019
 */
@Entity

public class Recolector extends Persona implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Recolector() {
		super();
	}
   
}
