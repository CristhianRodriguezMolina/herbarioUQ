package co.gov.jsasociados.bean;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.gov.jsasociados.Familia;
import co.gov.jsasociados.ejb.AdminEJB;
import co.gov.jsasocioados.exeption.FamiliaYaRegistradaExeption;
import co.gov.jsasociados.util.Util;

@FacesConfig(version=Version.JSF_2_3)
@Named("familiaBean")
@ApplicationScoped
public class FamiliaBean {
	/**
	 * nombre de la familia
	 */
	private String familia;
	private Familia fa; 
	/**
	 * 
	 */
	@EJB
	private AdminEJB adminEJB;
	/**
	 * metodo que permite insertar una familia desde la parte web
	 * @param familia
	 * @return
	 */
	public String registrarFamilia() {
		
		Familia familia = new Familia();
		familia.setFamilia(this.familia);
		try {
			fa=adminEJB.insertarFamilia(familia);
			return "/detalle_familia";
		} catch (FamiliaYaRegistradaExeption e) {
			Util.mostarMensaje(e.getMessage(), e.getMessage());
			return null;
		}
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
	 * @return the fa
	 */
	public Familia getFa() {
		return fa;
	}

	/**
	 * @param fa the fa to set
	 */
	public void setFa(Familia fa) {
		this.fa = fa;
	}
	
}
