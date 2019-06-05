package co.gov.jsasociados.entidades;

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
		@NamedQuery(name = Administrador.LISTAR_ADMINISTRADORES, query = "select administrador from Administrador administrador")})
public class Administrador extends Persona implements Serializable {
	
	public static final String LISTAR_ADMINISTRADORES = "listar Administradores";
	public static final String BUSCAR_POR_ID = "buscar por id";

	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}

}
