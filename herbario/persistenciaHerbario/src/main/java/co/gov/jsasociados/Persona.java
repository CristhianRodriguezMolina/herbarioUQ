package co.gov.jsasociados;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;

import javax.persistence.*;

/**
 * Informacion basica de cada una de las personas
 * 
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 * @author Sergio Osorio
 * @version 1.0 16/04/2019
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
//	@NamedQuery(name = Persona.NUMERO_REGISTROS_PERSONA, query="select new co.gov.jsasociados.DTORegistroPersona(p.cedula, COUNT(r)) from Persona p INNER JOIN p.registro r"),
	@NamedQuery(name = Persona.OBTENER_POR_USUARIO, query = "select persona from Persona persona where persona.cuenta.usuario=:usuario"),
	@NamedQuery(name = Persona.LISTAR_TODOS, query = "select p from Persona p"),

		@NamedQuery(name = Persona.OBTENER_CEDULA_Y_REGISTROS, query = "select persona.cedula, registro from Persona persona LEFT JOIN persona.registro registro where persona.cedula =:cedula"),
		@NamedQuery(name = Persona.PERSONA_SIN_REGISTRO, query = "select persona from Persona persona where persona.registro IS EMPTY"),
		//@NamedQuery(name = Persona.DTOREGISTROS, query = "select new co.gov.jsasociados.DtoRegistroPersona(persona.cedula, COUNT(registros) ) from JOIN persona.registro resgistros Persona persona where persona.resgistro NOT NULL"),	
		//@NamedQuery(name = Persona.DTOREGISTROS, query = "select new co.gov.jsasociados.DtoRegistroPersona(persona.cedula, COUNT(persona.registro)) from Persona persona ")
})
public class Persona implements Serializable {
	//este es el del ejb
	public static final String OBTENER_POR_USUARIO="Obtener una persona por su usuario registrado";
	
//	public static final String NUMERO_REGISTROS_PERSONA = "Numero de registros por persona";
	public static final String LISTAR_TODOS = "listar personas";
	public static final String OBTENER_CEDULA_Y_REGISTROS = "obtener cedula y registro";
	public static final String PERSONA_SIN_REGISTRO= "personas que no han realizado ningun registro";
	public static final String DTOREGISTROS= "DTO de cuantos registros a realizado una persona";
	/**
	 * cedula de la persona
	 */
	@Id
	@Column(length = 11, unique = true, nullable = false)
	private String cedula;
	/**
	 * nombre de la persona
	 */
	@Column(length = 50, nullable = false)
	private String nombre;
	/**
	 * apellidos de la persona
	 */
	@Column(length = 50, nullable = false)
	private String apellidos;
	/**
	 * telefono de la persona
	 */
	@Column(length = 10)
	private String telefono;
	/**
	 * correo de la persona
	 */
	@Column(length = 50, nullable = false)
	private String correo;
	/**
	 * direccion de la persona
	 */
	@Column(length = 50)
	private String direccion;
	/**
	 * cuenta de una persona
	 */
	@OneToOne(mappedBy = "persona")
	private Cuenta cuenta;
	/**
	 * registro de una persona
	 */
	@OneToMany(mappedBy = "persona")
	private ArrayList<Registro> registro;
	/**
	 * comentarios relizados por una persona
	 */
	@OneToMany(mappedBy = "persona")
	private ArrayList<Comentario> comentarios;

	private static final long serialVersionUID = 1L;

	/**
	 * constructor de la clase persona
	 */
	public Persona() {
		super();
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		return true;
	}

	/**
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the registro
	 */
	public ArrayList<Registro> getRegistro() {
		return registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(ArrayList<Registro> registro) {
		this.registro = registro;
	}

	/**
	 * @return the comentarios
	 */
	public ArrayList<Comentario> getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(ArrayList<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Persona [cedula=" + cedula + ", nombre=" + nombre + "]";
	}

}
