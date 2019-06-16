package co.gov.jsasociados.modelo;

import java.util.ArrayList;

import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Planta;

/**
 * permite transformar un genero en observalble
 *
 */
public class GeneroObservable {
	/**
	 * identificador unico de un genero
	 */
	private String idGenero;
	/**
	 * nombre del genero
	 */
	private String generoNombre;
	/**
	 * familia de una planta
	 */
	private String familiaNombre;
	
	/**
	 * plantas asociadas al genero
	 */
	private ArrayList<Planta> plantas;
	
	private Genero genero;

	/**
	 * permite generar una instancia con sus atributos
	 * @param idGenero
	 * @param generoNombre
	 * @param familia
	 * @param plantas
	 * @param genero
	 */
	public GeneroObservable(String idGenero, String generoNombre, String familia, ArrayList<Planta> plantas,
			Genero genero) {
		super();
		this.idGenero = idGenero;
		this.generoNombre = generoNombre;
		this.familiaNombre = familia;
		this.plantas = plantas;
		this.genero = genero;
	}
	
	/**
	 * permite generar una instancia con un genero
	 * @param genero
	 */
	public GeneroObservable(Genero genero) {
		this.genero= genero;
		this.idGenero = String.valueOf(genero.getIdGenero());
		this.generoNombre = genero.getGenero();
		this.familiaNombre = genero.getFamilia().getFamilia();
		this.plantas = genero.getPlantas();
		this.genero = genero;
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
	 * @return the generoNombre
	 */
	public String getGeneroNombre() {
		return generoNombre;
	}

	/**
	 * @param generoNombre the generoNombre to set
	 */
	public void setGeneroNombre(String generoNombre) {
		this.generoNombre = generoNombre;
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
	
	
}
