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
@NamedQueries({
	@NamedQuery(name = Planta.LISTAR_PLANTAS_POR_GENERO_Y_FAMILIA, query="select planta from Planta planta where planta.genero.genero=:genero and planta.genero.familia.familia=:familia"),
	@NamedQuery(name = Planta.OBTENER_PLANTA_POR_NOMBRE, query="select planta from Planta planta where planta.nombre=:nombre"),
	@NamedQuery(name = Planta.OBTENER_FAMILIA_PLANTA, query="select planta.genero.familia from Planta planta where planta.idPlanta=:idPlanta"),
	@NamedQuery(name = Planta.LISTAR_NOMBRES_PLANTAS, query = "select planta.nombre from Planta planta"),
	@NamedQuery(name = Planta.LISTAR_PLANTAS, query = "select planta from Planta planta"),
	@NamedQuery(name = Planta.LISTAR_PLANTAS_POR_APROVACION, query = "select DISTINCT registro.planta from Registro registro where registro.aprovacion=:aprovacion"),
	@NamedQuery(name = Planta.LISTAR_PLANTAS_POR_FAMILIA, query = "select planta from Planta planta where planta.genero.familia.familia=:familia"),
	@NamedQuery(name = Planta.LISTAR_PLANTAS_POR_GENERO, query = "select planta from Planta planta where planta.genero.genero=:genero"),
	@NamedQuery(name = Planta.LISTAR_PLANTAS_ACEPTADAS, query = "select registro.planta from Registro registro where registro.aprovacion= 1"),
	@NamedQuery(name = Planta.LISTAR_PLANTAS_ENVIADAS, query = "select registro.planta from Registro registro where registro.persona.cedula=:cedula and registro.aprovacion=:aprovacion"),
	@NamedQuery(name = Planta.LISTAR_PLANTAS_ACEPTADAS_POR_FAMILIA, query = "select planta from Planta planta where planta.genero.familia.familia=:familia and planta.registro.aprovacion= 1"),
	@NamedQuery(name = Planta.LISTAR_PLANTAS_ACEPTADAS_POR_GENERO, query = "select planta from Planta planta where planta.genero.genero=:genero and planta.registro.aprovacion= 1")
})
public class Planta implements Serializable {
	
	public static final String LISTAR_PLANTAS_POR_GENERO_Y_FAMILIA = "Listar plantas por genero y familia";
	public static final String LISTAR_NOMBRES_PLANTAS = "Listar nombres de plantas";
	public static final String OBTENER_PLANTA_POR_NOMBRE = "Obtener planta por nombre";
	public static final String OBTENER_FAMILIA_PLANTA="obnter familia planta";
	public static final String LISTAR_PLANTAS = "listar Plantas";
	public static final String LISTAR_PLANTAS_POR_APROVACION = "listar Plantas por aprovacion";
	public static final String LISTAR_PLANTAS_POR_FAMILIA = "listar Plantas por familia desde administrador";
	public static final String LISTAR_PLANTAS_POR_GENERO = "listar Plantas por genero desde administrador";
	public static final String LISTAR_PLANTAS_ACEPTADAS = "listar plantas aceptadas";
	public static final String LISTAR_PLANTAS_ENVIADAS = "listar plantas enviadas";
	public static final String LISTAR_PLANTAS_ACEPTADAS_POR_FAMILIA = "listar plantas aceptadas por familia";
	public static final String LISTAR_PLANTAS_ACEPTADAS_POR_GENERO="listar plantas aceptadas por genero";
	
	/**
	 * id unico de la Planta
	 */	
	@Id
	@Column(unique=true, nullable=false)	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlanta;
	/**
	 * nombre de la Planta
	 */	
	@Column(length=30, unique=true, nullable=false)
	private String nombre;
	/**
	 * genero de la Planta
	 */	
	@ManyToOne
	private Genero genero;
	/**
	 * Descripcion de una planta
	 */
	@Column(nullable=false)
	private String descripcion;	
	/**
	 * Imagen relacionada a la planta
	 */
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name = "imagen")
	private byte[] imagen;
	/**
	 * Registro de la Planta
	 */	
	@OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE}, mappedBy="planta")
	private Registro registro;
	
	private static final long serialVersionUID = 1L;

	public Planta() {
		super();
	}

	/**
	 * @return the idPlanta
	 */
	public Long getIdPlanta() {
		return idPlanta;
	}

	/**
	 * @return the nombre
	 */
	@Column()
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
	 * @return the imagen
	 */
	public byte[] getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
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
	
	public String toString()
	{
		String s = "Id: "+idPlanta+"\n";
		s+= "Nombre: "+nombre+"\n";
		s+= "Genero: "+genero.getGenero()+"\n";
		s+= "Familia: "+genero.getFamilia().getFamilia()+"\n";
		
		return s;
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
		genero.addPlanta(this);
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

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}   
	
   
}
