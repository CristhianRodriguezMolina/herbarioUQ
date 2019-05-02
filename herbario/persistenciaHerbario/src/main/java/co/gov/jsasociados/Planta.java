package co.gov.jsasociados;

import java.io.Serializable;
import java.lang.String;
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

public class Planta implements Serializable {

	@Column(unique=true, nullable=false)
	@Id
	private String idPlanta;
	@Column(length=30, unique=true, nullable=false)
	private String nombre;   
	@ManyToOne
	private Genero genero;
//	imagen imagen
	
	@OneToOne(mappedBy="planta")
	private Registro registro;
	private static final long serialVersionUID = 1L;

	public Planta() {
		super();
	}

	/**
	 * @return the idPlanta
	 */
	public String getIdPlanta() {
		return idPlanta;
	}

	/**
	 * @param idPlanta the idPlanta to set
	 */
	public void setIdPlanta(String idPlanta) {
		this.idPlanta = idPlanta;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPlanta == null) ? 0 : idPlanta.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	
	/* (non-Javadoc)
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
		Planta other = (Planta) obj;
		if (idPlanta == null) {
			if (other.idPlanta != null)
				return false;
		} else if (!idPlanta.equals(other.idPlanta))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	/**
	 * @return the registro
	 */
	public Registro getRegistro() {
		return registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}   
	
   
}
