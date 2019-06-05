package co.gov.jsasociados.entidades;

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
@NamedQueries({
	@NamedQuery(name = Empleado.BUSCAR_POR_ID, query = "select empleado from Empleado empleado where empleado.cedula=:cedula"),
	@NamedQuery(name = Empleado.LISTAR_EMPLEADOS, query = "select empleado from Empleado empleado"),
	@NamedQuery(name = Empleado.LISTAR_RECOLECTORES, query = "select recolector from Recolector recolector")})
public class Empleado extends Persona implements Serializable {
	
	// querys de emleado
	public static final String BUSCAR_POR_ID= "buscar Empleado por id"; 
	public static final String LISTAR_EMPLEADOS = "listar Empleados";
	public static final String LISTAR_RECOLECTORES = "listar Recolectores";

	
	private static final long serialVersionUID = 1L;

	public Empleado() {
		super();
	}
   
}
