package co.gov.jsasociados;

import java.util.ArrayList;

public class DtoRegistroPersno {
	
	private String cedula;
	private ArrayList<Integer> numeroRegistro;
	
	public DtoRegistroPersno(String cedula, ArrayList<Integer> numeroRegistro) {
		super();
		this.cedula = cedula;
		this.numeroRegistro = numeroRegistro;
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
	public ArrayList<Integer> getNumeroRegistro() {
		return numeroRegistro;
	}
	/**
	 * @param numeroRegistro the numeroRegistro to set
	 */
	public void setNumeroRegistro(ArrayList<Integer> numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DtoRegistroPersno [cedula=" + cedula + ", numeroRegistro=" + numeroRegistro + "]";
	}
	
	
}
