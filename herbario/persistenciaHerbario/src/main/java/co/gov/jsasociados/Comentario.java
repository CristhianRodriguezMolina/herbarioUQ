package co.gov.jsasociados;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * comentaria realizado a una planta
 * 
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 * @author Sergio Osorio
 * @version 1.0 16/04/2019
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Comentario.LISTAR_COMENTARIOS, query = "select comentario from Comentario comentario"),
		@NamedQuery(name = Comentario.LISTAR_COMENTARIOS_FECHAPUBLICACION, query = "select comentario.comentario from Comentario comentario where comentario.fechaPublicacion=:fechaPublicacion"),
		@NamedQuery(name = Comentario.LISTAR_COMENTARIOS_PERSONA, query = "select comentario.comentario from Comentario comentario where comentario.persona=:persona"),
})
public class Comentario implements Serializable {
	public static final String LISTAR_COMENTARIOS= "listar comentarios";
	public static final String LISTAR_COMENTARIOS_FECHAPUBLICACION= "listar comentarios";
	public static final String LISTAR_COMENTARIOS_PERSONA= "listar comentarios realizados por una persona";
	/**
	 * numero de registron de un comentario
	 */
	@Id
	@Column(unique = true, nullable = false)
	private Integer numeroRegistro;
	/**
	 * comentaria realizado
	 */
	@Column(length = 250)
	private String comentario;
	/**
	 * fecha de publicacion de un comentario
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPublicacion;

	/**
	 * persona asociada a un comentario
	 */
	@ManyToOne
	private Persona persona;
	
	//aca hace falta mirar que esta clase debe de tener un arrayList de comentarios
	private static final long serialVersionUID = 1L;

	public Comentario() {
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
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the fechaPublicacion
	 */
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	/**
	 * @param fechaPublicacion the fechaPublicacion to set
	 */
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
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
		result = prime * result + ((fechaPublicacion == null) ? 0 : fechaPublicacion.hashCode());
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
		Comentario other = (Comentario) obj;
		if (fechaPublicacion == null) {
			if (other.fechaPublicacion != null)
				return false;
		} else if (!fechaPublicacion.equals(other.fechaPublicacion))
			return false;
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

}
