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
@NamedQueries({
@NamedQuery(name = Genero.LISTAR_NOMBRES_GENEROS, query="select genero.genero from Genero genero"),
@NamedQuery(name = Genero.OBTENER_PLANTAS, query="select planta from Genero genero INNER JOIN genero.plantas planta where genero.idGenero=:idGenero"),
@NamedQuery(name = Genero.OBTENER_GENERO, query="select genero from Genero genero where genero.genero=:genero"),
@NamedQuery(name = Genero.OBTENER_GENEROS, query="select genero from Genero genero")
}
)
public class Genero implements Serializable {
	
	public static final String LISTAR_NOMBRES_GENEROS = "Listar nombres de generos";
	public static final String OBTENER_PLANTAS= "obtener plantas";
	public static final String OBTENER_GENERO= "obtener un genero por su nombre";
	public static final String OBTENER_GENEROS= "obtener el listado de generos";
	
	/**
	 * identificador unico de un genero
	 */
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long idGenero;
	/**
	 * nombre del genero
	 */
	@Column(length=30, unique=true, nullable=false)
	private String genero;
	/**
	 * familia de una planta
	 */
	@ManyToOne
	private Familia familia;
	
	@OneToMany(cascade = {CascadeType.REMOVE,CascadeType.MERGE}, mappedBy="genero")
	private ArrayList<Planta> plantas;
	
	private static final long serialVersionUID = 1L;

	public Genero() {
		super();
		plantas = new ArrayList<>();
	}
	
	public void addPlanta(Planta p) {
		if(plantas == null) {
			plantas = new ArrayList<>();
		}
		plantas.add(p);
	}

	/**
	 * @return the idGenero
	 */
	public Long getIdGenero() {
		return idGenero;
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
		familia.addGenero(this);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return genero;
	}   
	
	
   
}
