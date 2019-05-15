package co.gov.jsasociados;

import java.util.ArrayList;

public class DTO  {
	
	private Integer idRegistro;
	private Genero genero;
	private ArrayList<Planta> plantas;
	private String cedulaPersona, correoPersona;
	
	public DTO(Integer idRegistro, Genero genero, ArrayList<Planta> plantas, String cedulaPersona, String correoPersona)
	{
		this.idRegistro = idRegistro;
		this.genero = genero;
		this.plantas = plantas;
		this.cedulaPersona = cedulaPersona;
		this.correoPersona = correoPersona;
	}

	public Integer getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public ArrayList<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(ArrayList<Planta> plantas) {
		this.plantas = plantas;
	}

	public String getCedulaPersona() {
		return cedulaPersona;
	}

	public void setCedulaPersona(String cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
	}

	public String getCorreoPersona() {
		return correoPersona;
	}

	public void setCorreoPersona(String correoPersona) {
		this.correoPersona = correoPersona;
	}
	
	
}
