package co.gov.jsasociados.bean;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Genero;
import co.gov.jsasociados.ejb.AdminEJB;
import co.gov.jsasociados.util.Util;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.GeneroYaRegistradoExcepcion;

@FacesConfig(version=Version.JSF_2_3)
@Named("generoBean")
@ApplicationScoped
public class GeneroBean {

	/**
	 * nombre del genero
	 */
	private String genero;
	/**
	 * familia del genero
	 */
	private String familia;
	/**
	 * familia asociada
	 */
	private Genero ge; 
	/**
	 * Instancia del AdminEJB
	 */
	@EJB
	private AdminEJB adminEJB;
	/**
	 * metodo que permite insertar un genero desde la parte web
	 * @param familia
	 * @return
	 */
	public String registrarGenero() {
		
		Genero genero = new Genero();	
		
		try {			
			Familia familiaTemp = adminEJB.buscarFamilia(familia);		
			genero.setFamilia(familiaTemp);
			genero.setGenero(this.genero);
			familiaTemp.addGenero(genero);
			
			ge=adminEJB.insertarGenero(genero);
			return "/detalle_genero";
		} catch (GeneroYaRegistradoExcepcion e) { 
			Util.mostarMensaje(e.getMessage(), e.getMessage());
			return null;
		} catch (NullPointerException e1) {
			Util.mostarMensaje("Elemento no encontrado", String.format("La familia con el nombre $ no se encuentra registrada", familia));
			return null;
		}
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
	/**
	 * @return the ge
	 */
	public Genero getGe() {
		return ge;
	}
	/**
	 * @param ge the ge to set
	 */
	public void setGe(Genero ge) {
		this.ge = ge;
	}
	
	
}
