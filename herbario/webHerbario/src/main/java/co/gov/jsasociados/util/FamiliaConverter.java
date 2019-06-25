package co.gov.jsasociados.util;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import co.gov.jsasociados.Familia;
import co.gov.jsasociados.ejb.AdminEJB;

/**
 * convertidor de una familia
 * @author Jhonatan Hidalgo
 *
 */
@FacesConfig(version= Version.JSF_2_3)
@Named(value= "familiaConveter")
@ApplicationScoped
public class FamiliaConverter implements Converter<Familia>{
	/**
	 * referencia a la capa de negocio
	 */
	@EJB
	private AdminEJB AdminEJB;
	
	@Override
	public Familia getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Familia value) {
		// TODO Auto-generated method stub1
		if (value!=null) {
			return String.format("%s", value.getIdFamilia());
		}
		return "";
	}
	
}
