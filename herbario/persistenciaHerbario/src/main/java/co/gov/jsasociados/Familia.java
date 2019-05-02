package co.gov.jsasociados;

import java.awt.List;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;

import javax.persistence.*;

/**
 * familia
 * 
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 * @author Sergio Osorio
 * @version 1.0 16/04/2019
 */
@Entity

public class Familia implements Serializable {

	/**
	 * clave unica de una familia   
	 */
	@Id
	@Column(unique=true, nullable=false)
	private String IdFamilia;
	/**
	 * nombre de una familia 
	 */
	@Column(length=30, unique=true, nullable=false)
	private String familia;
	
	/**
	 * generos de una familia
	 */
	@OneToMany(mappedBy="familia")
	private ArrayList<Genero> generos;
	
	private static final long serialVersionUID = 1L;

	public Familia() {
		super();
	}

	/**
	 * @return the idFamilia
	 */
	public String getIdFamilia() {
		return IdFamilia;
	}

	/**
	 * @param idFamilia the idFamilia to set
	 */
	public void setIdFamilia(String idFamilia) {
		IdFamilia = idFamilia;
	}

	/**
	 * @return the familia
	 */
	public String getFamilia() {
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(String familia) {
		this.familia = familia;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IdFamilia == null) ? 0 : IdFamilia.hashCode());
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
		Familia other = (Familia) obj;
		if (IdFamilia == null) {
			if (other.IdFamilia != null)
				return false;
		} else if (!IdFamilia.equals(other.IdFamilia))
			return false;
		return true;
	}

	/**
	 * @return the generos
	 */
	public ArrayList<Genero> getGeneros() {
		return generos;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(ArrayList<Genero> generos) {
		this.generos = generos;
	}   
	
   
}
