package co.gov.jsasociados.entidades;

/**
 * DTO que devuelve la cedula y los numero de registros
 *
 */
public class DTORegistroPersona {
	
	private String cedula;
	private String numeroRegistros;
	
	public DTORegistroPersona(String cedula,String numeroRegistros) {
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
	public String getNumeroRegistro() {
		return numeroRegistros;
	}
	/**
	 * @param numeroRegistro the numeroRegistro to set
	 */
	public void setNumeroRegistro(String numeroRegistros) {
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
