package co.gov.jsasociados.modelo;

import co.gov.jsasociados.Familia;

/**
 * permite tranformar una platan en observable
 *
 */
public class FamiliaObservable {
	/**
	 * id de la familia
	 */
	private String idFamilia;
	/**
	 * nombre de una familia 
	 */
	private String familiaNombre;
	
	/**
	 * familia
	 */
	private Familia familia;
	
	
	
	/**
	 * permite generar una instacia con todos sus atributos
	 * @param idFamilia
	 * @param familiaNombre
	 * @param familia
	 */
	public FamiliaObservable(String idFamilia, String familiaNombre) {
		this.idFamilia = idFamilia;
		this.familiaNombre = familiaNombre;
	}
	/**
	 * permite generar una instancia con una familia
	 * @param familia
	 */
	public FamiliaObservable(Familia familia) {
		this.familia=familia;
		this.idFamilia = String.valueOf(familia.getIdFamilia());
		this.familiaNombre = familia.getFamilia();
	}
	/**
	 * @return the idFamilia
	 */
	public String getIdFamilia() {
		return idFamilia;
	}

	/**
	 * @param idFamilia the idFamilia to set
	 */
	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}

	/**
	 * @return the familiaNombre
	 */
	public String getFamiliaNombre() {
		return familiaNombre;
	}

	/**
	 * @param familiaNombre the familiaNombre to set
	 */
	public void setFamiliaNombre(String familiaNombre) {
		this.familiaNombre = familiaNombre;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return familiaNombre;
	}
	
	
}
