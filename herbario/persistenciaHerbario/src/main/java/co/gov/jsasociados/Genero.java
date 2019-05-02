package co.gov.jsasociados;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;

import javax.persistence.*;

/**
 * genero
 * 
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 * @author Sergio Osorio
 * @version 1.0 16/04/2019
 */
@Entity

public class Genero implements Serializable {
	/**
	 * identificador unico de un genero
	 */
	@Column(unique=true, nullable=false)
	@Id
	private String idGenero;
	/**
	 * genero
	 */
	@Column(length=30, unique=true, nullable=false)
	private String genero;
	/**
	 * familia de una planta
	 */
	@ManyToOne
	private Familia familia;
	
	@OneToMany(mappedBy="genero")
	private ArrayList<Planta> plantas;
	private static final long serialVersionUID = 1L;

	public Genero() {
		super();
	}

	/**
	 * @return the idGenero
	 */
	public String getIdGenero() {
		return idGenero;
	}

	/**
	 * @param idGenero the idGenero to set
	 */
	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((idGenero == null) ? 0 : idGenero.hashCode());
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
		Genero other = (Genero) obj;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (idGenero == null) {
			if (other.idGenero != null)
				return false;
		} else if (!idGenero.equals(other.idGenero))
			return false;
		return true;
	}

	/**
	 * @return the familia
	 */
	public Familia getFamilia() {
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	/**
	 * @return the plantas
	 */
	public ArrayList<Planta> getPlantas() {
		return plantas;
	}

	/**
	 * @param plantas the plantas to set
	 */
	public void setPlantas(ArrayList<Planta> plantas) {
		this.plantas = plantas;
	}   
	
	
   
}
