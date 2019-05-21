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
		@NamedQuery(name = Recolector.LISTAR_RECOLECTORES_CON_REGISTROS, query = "select DISTINCT registro.persona from Registro registro")})
public class Recolector extends Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String BUSCAR_POR_ID = "buscar recolector por id";
	public static final String LISTAR_RECOLECTORES = "listar recolectores";
	public static final String LISTAR_RECOLECTORES_CON_REGISTROS = "LISTAR RECOLECTORES CON REGISTROS";

	public Recolector() {
		super();
	}

}
