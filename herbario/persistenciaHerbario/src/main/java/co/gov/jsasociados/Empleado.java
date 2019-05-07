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
@NamedQueries({
	@NamedQuery(name = Empleado.BUSCAR_POR_ID, query = "select empledo from Empleado emplado where empleado.cedula=:cedula"),
	@NamedQuery(name = Empleado.LISTAR_EMPLEADOS, query = "select empleado from Empleado empleado"),
	@NamedQuery(name = Empleado.LISTAR_RECOLECTORES, query = "select recolector from Recolector recolector"),
	@NamedQuery(name = Empleado.LISTAR_PLANTAS, query = "select planta from Planta planta"),
	@NamedQuery(name = Empleado.LISTAR_PLANTAS_POR_APROVACION, query = "select registro.planta from Registro registro where registro.aprovacion=:aprovacion"),
	@NamedQuery(name = Empleado.LISTAR_PLANTAS_POR_FAMILIA, query = "select planta from Planta planta where planta.genero.familia=:familia"),
	@NamedQuery(name = Empleado.LISTAR_PLANTAS_POR_GENERO, query = "select planta from Planta planta where planta.genero=:genero"), })
public class Empleado extends Persona implements Serializable {
	
	// querys de emleado
	public static final String BUSCAR_POR_ID= "buscar Empleado por id"; 
	static final String LISTAR_EMPLEADOS = "listar Empleados";
	static final String LISTAR_RECOLECTORES = "listar Recolectores";

	// QUERYS DE PLANTAS
	static final String LISTAR_PLANTAS = "listar Plantas";
	static final String LISTAR_PLANTAS_POR_APROVACION = "listar Plantas por aprovacion";
	static final String LISTAR_PLANTAS_POR_FAMILIA = "listar Plantas por familia";
	static final String LISTAR_PLANTAS_POR_GENERO = "listar Plantas por genero";
	
	private static final long serialVersionUID = 1L;

	public Empleado() {
		super();
	}
   
}
