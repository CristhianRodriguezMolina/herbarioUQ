package co.gov.jsasociados.util;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Genero;
import co.gov.jsasociados.ejb.AdminEJB;

/**
* convertidor de un genero
* 
* @author Cristhian Rodriguez
*
*/
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "generoConverter")
@ApplicationScoped
public class GeneroConverter implements Converter<Genero> {

	/**
	 * referencia a la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	@Override
	public Genero getAsObject(FacesContext context, UIComponent component, String value) {
		Genero genero = null;
		if (value != null && !"".equals(value)) {
			try {
				genero = adminEJB.buscarGeneroId(Long.parseLong(value));
			} catch (Exception e) {
				throw new ConverterException(new FacesMessage(component.getClientId() +

						":ID no valido"));
			}
		}
		return genero;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Genero value) {
		if (value != null) {
			return String.format("%s", value.getIdGenero());
		}
		return "";
	}
	
}
