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
@NamedQueries({
		@NamedQuery(name = Recolector.BUSCAR_POR_ID, query = "select recolector from Recolector recolector where recolector.cedula=:cedula"),
		@NamedQuery(name = Recolector.LISTAR_RECOLECTORES, query = "select recolector from Recolector recolector"),
		@NamedQuery(name = Recolector.LISTAR_PLANTAS_ACEPTADAS, query = "select registro.planta from Registro registro where registro.aprovacion= 1"),
		@NamedQuery(name = Recolector.LISTAR_PLANTAS_ENVIADAS, query = "select registro.planta from Registro registro where registro.persona.cedula=:cedula and registro.aprovacion=:aprovacion"),
		@NamedQuery(name = Recolector.LISTAR_PLANTAS_POR_FAMILIA, query = "select planta from Planta planta where planta.genero.familia=:familia and planta.registro.aprovacion= 1"), // probar
																																														// bien
																																														// esta
																																														// consulta
		@NamedQuery(name = Recolector.LISTAR_PLANTAS_POR_GENERO, query = "select planta from Planta planta where planta.genero=:genero and planta.registro.aprovacion= 1"), })
public class Recolector extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String BUSCAR_POR_ID = "buscar recolector por id";
	public static final String LISTAR_RECOLECTORES = "listar recolectores";
	public static final String LISTAR_PLANTAS_ACEPTADAS = "listar plantas aceptadas";
	public static final String LISTAR_PLANTAS_ENVIADAS = "listar plantas enviadas";
	public static final String LISTAR_PLANTAS_POR_FAMILIA = "listar plantas aceptadas por familia";
	public static final String LISTAR_PLANTAS_POR_GENERO = "listar plantas aceptadas por genero";

	public Recolector() {
		super();
	}

}
