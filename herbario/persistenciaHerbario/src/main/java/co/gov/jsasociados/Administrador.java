package co.gov.jsasociados;

import java.io.Serializable;
import javax.persistence.*;

/**
 * clase administrador
 * 
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 * @author Sergio Osorio
 * @version 1.0 16/04/2019
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Administrador.BUSCAR_POR_ID, query = "select administrador from Administrador administrador where administrador.cedula=:cedula"),
		@NamedQuery(name = Administrador.LISTAR_ADMINISTRADORES, query = "select administrador from Administrador administrador"),
		@NamedQuery(name = Administrador.LISTAR_EMPLEADOS, query = "select empleado from Empleado empleado"),
		@NamedQuery(name = Administrador.LISTAR_RECOLECTORES, query = "select recolector from Recolector recolector"),
		@NamedQuery(name = Administrador.LISTAR_PLANTAS, query = "select planta from Planta planta"),
		@NamedQuery(name = Administrador.LISTAR_PLANTAS_POR_APROVACION, query = "select registro.planta from Registro registro where registro.aprovacion=:aprovacion"),
		@NamedQuery(name = Administrador.LISTAR_PLANTAS_POR_FAMILIA, query = "select planta from Planta planta where planta.genero.familia=:familia"),
		@NamedQuery(name = Administrador.LISTAR_PLANTAS_POR_GENERO, query = "select planta from Planta planta where planta.genero=:genero"), })
public class Administrador extends Persona implements Serializable {

	// NOMBRES DE LOS QUERYS DE ESTA CLASE

	// QUERYS DE PERSONAS
	static final String LISTAR_ADMINISTRADORES = "listar Administradores";
	static final String LISTAR_EMPLEADOS = "listar Empleados";
	static final String LISTAR_RECOLECTORES = "listar Recolectores";

	// QUERYS DE PLANTAS
	static final String LISTAR_PLANTAS = "listar Plantas";
	static final String LISTAR_PLANTAS_POR_APROVACION = "listar Plantas por aprovacion";
	static final String LISTAR_PLANTAS_POR_FAMILIA = "listar Plantas por familia";
	static final String LISTAR_PLANTAS_POR_GENERO = "listar Plantas por genero";
	
	//buscar administrador por id
	static final String BUSCAR_POR_ID = "buscar por id";

	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}

}
