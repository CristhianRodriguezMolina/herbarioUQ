package co.gov.jsasociados;

import java.io.Serializable;
import javax.persistence.*;

/**
 * clase empleado
 * 
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 * @author Sergio Osorio
 * @version 1.0 16/04/2019
 */
@Entity

public class Empleado extends Persona implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Empleado() {
		super();
	}
   
}
