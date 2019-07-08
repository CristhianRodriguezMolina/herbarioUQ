package co.gov.jsasociados;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * planta
 * 
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 * @author Sergio Osorio
 * @version 1.0 16/04/2019
 */
@Entity
@NamedQueries(
		{	@NamedQuery(name = Registro.DATOS_REGISTRO_FECHA, query = "select registro.numeroRegistro,registro.planta.nombre, registro.planta.genero.genero, registro.persona.correo, registro.persona.cedula from Registro registro where CAST(registro.fechaRegistro as DATETIME)=:fechaRegistro"),
			@NamedQuery(name = Registro.LISTAR_REGISTROS, query = "select registro from Registro registro"),
		@NamedQuery(name = Registro.REGISTROS_POR_PERSONA, query = "select registro from Registro registro where registro.persona.cedula=:cedula"),
		@NamedQuery(name = Registro.REGISTROS_POR_PERSONA_ACPETADOS, query = "select registro from Registro registro where registro.persona.cedula=:cedula and registro.aprovacion=1"),
		@NamedQuery(name = Registro.REGISTROS_POR_PERSONA_RECHAZADOS, query = "select registro from Registro registro where registro.persona.cedula=:cedula and registro.aprovacion=-1"),
	})

public class Registro implements Serializable {
	public static final String LISTAR_REGISTROS = "listar registros";
	public static final String REGISTROS_POR_PERSONA = "obtener registros por persona";
	public static final String REGISTROS_POR_PERSONA_ACPETADOS = "obtener registros por persona aceptados";
	public static final String REGISTROS_POR_PERSONA_RECHAZADOS = "obtener registros por persona rechazados";
	public static final String DATOS_REGISTRO_FECHA = "obtener datos fecha";

	/**
	 * numero de registro de una planta
	 */
	@Column(unique = true, nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numeroRegistro;
	/**
	 * fecha de recoleccion de una planta
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRecoleccion;
	/**
	 * fecha de registro de un aplanta
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	/**
	 * pais de recoleccion de una planta
	 */
	@Column(length = 30, nullable = false)
	private String pais;
	/**
	 * departamento de recoleccion de una planta
	 */
	@Column(length = 30, nullable = false)
	private String departamento;
	/**
	 * municiopio de recoleccion de una planta
	 */
	@Column(length = 30, nullable = false)
	private String municipio;
	/**
	 * lugar de recoleccion de una planta
	 */
	@Column(length = 30, nullable = false)
	private String lugar;
	/**
	 * estado de un envio, aprovado=1, en espera=0; no aprovado=-1
	 */
	private Integer aprovacion;
	/**
	 * determirminacion dada a un aplanta
	 *
	 */
	@Column(length = 250)
	private String determinacion;
	/**
	 * persona asociada a un registro
	 */
	@ManyToOne
	private Persona persona;
	/**
	 * registro de una planta
	 */
	@OneToOne
	private Planta planta;
	private static final long serialVersionUID = 1L;

	public Registro() {
		super();
	}

	/**
	 * @return the numeroRegistro
	 */
	public Integer getNumeroRegistro() {
		return numeroRegistro;
	}

	/**
	 * @param numeroRegistro the numeroRegistro to set
	 */
	public void setNumeroRegistro(Integer numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	/**
	 * @return the fechaRecoleccion
	 */
	public Date getFechaRecoleccion() {
		return fechaRecoleccion;
	}

	/**
	 * @param fechaRecoleccion the fechaRecoleccion to set
	 */
	public void setFechaRecoleccion(Date fechaRecoleccion) {
		this.fechaRecoleccion = fechaRecoleccion;
	}

	/**
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the lugar
	 */
	public String getLugar() {
		return lugar;
	}

	/**
	 * @param lugar the lugar to set
	 */
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	/**
	 * @return the aprovacion
	 */
	public String getAprovacion() {
		if(aprovacion.equals(1)) {
			return "Aceptado";
		}else if(aprovacion.equals(-1)) {
			return "Rechazado";
		}else {
			return "Pendiente";
		}
	}

	/**
	 * @param aprovacion the aprovacion to set
	 */
	public void setAprovacion(Integer aprovacion) {
		this.aprovacion = aprovacion;
	}

	/**
	 * @return the determinacion
	 */
	public String getDeterminacion() {
		return determinacion;
	}

	/**
	 * @param determinacion the determinacion to set
	 */
	public void setDeterminacion(String determinacion) {
		this.determinacion = determinacion;
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
		result = prime * result + ((numeroRegistro == null) ? 0 : numeroRegistro.hashCode());
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
		Registro other = (Registro) obj;
		if (numeroRegistro == null) {
			if (other.numeroRegistro != null)
				return false;
		} else if (!numeroRegistro.equals(other.numeroRegistro))
			return false;
		return true;
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * @return the planta
	 */
	public Planta getPlanta() {
		return planta;
	}

	/**
	 * @param planta the planta to set
	 */
	public void setPlanta(Planta planta) {
		this.planta = planta;
	}


}
