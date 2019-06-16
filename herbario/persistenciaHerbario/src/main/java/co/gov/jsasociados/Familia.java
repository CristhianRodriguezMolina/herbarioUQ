package co.gov.jsasociados;

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
@NamedQueries(
		{@NamedQuery(name=Familia.LISTAR_NOMBRES_FAMILIAS, query="select f.familia from Familia f"),
		@NamedQuery(name=Familia.LISTAR_FAMILIAS, query="select f from Familia f"),
		@NamedQuery(name=Familia.OBTENER_POR_NOMBRE, query="select f from Familia f where f.familia=:familia"),
		@NamedQuery(name=Familia.OBTENER_POR_ID, query="select f from Familia f where f.idFamilia=:idFamilia"),
		@NamedQuery(name=Familia.FAMILIA_CON_MAS_ESPECIES, query="select familia, COUNT(p) from Familia Familia, Genero g INNER JOIN g.plantas p where g.familia.idFamilia = familia.idFamilia"),
		@NamedQuery(name=Familia.FAMILIA_CON_MAS_ESPECIES_2, query="select familia, MAX(select COUNT(genero.plantas) from Genero genero where genero.familia.idFamilia=familia.idFamilia) from Familia familia"),		 
		@NamedQuery(name = Familia.DECIR_CANTIDAD_FAMILIA, query = "select count(familia) from Familia familia")})
@Entity

public class Familia implements Serializable {

	public static final String LISTAR_NOMBRES_FAMILIAS="Listar nombres de familias";
	public static final String DECIR_CANTIDAD_FAMILIA="Dice la cantidad de familias registradas";
	public static final String FAMILIA_CON_MAS_ESPECIES_2 = "Familia con mas especies 2";
	public static final String FAMILIA_CON_MAS_ESPECIES = "Familia con mas especies";
	public static final String OBTENER_POR_ID = "Obtener familia por id";
	public static final String OBTENER_POR_NOMBRE = "Obtener familia por nombre";
	public static final String LISTAR_FAMILIAS = "Listar familias";
	
	/**
	 * clave unica de una familia   
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long idFamilia;
	/**
	 * nombre de una familia 
	 */
	@Column(length=30, unique=true, nullable=false)
	private String familia;
	
	/**
	 * generos de una familia
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy="familia")
	private ArrayList<Genero> generos;
	
	private static final long serialVersionUID = 1L;

	public Familia() {
		super();
		generos = new ArrayList<>();
	}

	/**
	 * @return the idFamilia
	 */
	public Long getIdFamilia() {
		return idFamilia;
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
		result = prime * result + ((idFamilia == null) ? 0 : idFamilia.hashCode());
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
		if (idFamilia == null) {
			if (other.idFamilia != null)
				return false;
		} else if (!idFamilia.equals(other.idFamilia))
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
	
	public void addGenero(Genero g) {
		if(generos == null) {
			generos = new ArrayList<>();
		}
		generos.add(g);
	}
}
