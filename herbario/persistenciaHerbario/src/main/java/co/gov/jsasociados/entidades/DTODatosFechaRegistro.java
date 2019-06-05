package co.gov.jsasociados.entidades;

import java.util.ArrayList;

public class DTODatosFechaRegistro  {
	
	private Integer idRegistro;
	private Genero genero;
	private Planta planta;
	private String cedulaPersona, correoPersona;
	
	public DTODatosFechaRegistro(Integer idRegistro, Genero genero, Planta planta, String cedulaPersona, String correoPersona)
	{
		this.idRegistro = idRegistro;
		this.genero = genero;
		this.planta = planta;
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

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
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
