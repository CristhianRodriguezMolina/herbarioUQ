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
import co.gov.jsasociados.ejb.AdminEJB;

/**
 * convertidor de una familia
 * 
 * @author Jhonatan Hidalgo
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "familiaConverter")
@ApplicationScoped
public class FamiliaConverter implements Converter<Familia> {
	/**
	 * referencia a la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	@Override
	public Familia getAsObject(FacesContext context, UIComponent component, String value) {
		Familia familia = null;
		if (value != null && !"".equals(value)) {
			try {
				familia = adminEJB.buscarFamiliaId(Long.parseLong(value));
			} catch (Exception e) {
				throw new ConverterException(new FacesMessage(component.getClientId() +

						":ID no valido"));
			}
		}
		return familia;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Familia value) {
		if (value != null) {
			return String.format("%s", value.getIdFamilia());
		}
		return "";
	}

}
