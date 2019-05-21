package co.gov.jsasociados;

/**
 * DTO que devuelve la cedula y los numero de registros
 *
 */
public class DtoRegistroPersona {
	
	private String cedula;
	private Long numeroRegistros;
	
	public DtoRegistroPersona(String cedula,Long numeroRegistros) {
		super();
		this.cedula = cedula;
		this.numeroRegistros = numeroRegistros;
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
	 * @return the numeroRegistro
	 */
	public Long getNumeroRegistro() {
		return numeroRegistros;
	}
	/**
	 * @param numeroRegistro the numeroRegistro to set
	 */
	public void setNumeroRegistro(Long numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DtoRegistroPersno [cedula=" + cedula + ", numeroRegistros=" + numeroRegistros + "]";
	}
	
	
}
