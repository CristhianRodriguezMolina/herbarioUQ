package co.gov.jsasociados.util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Util {

	public static void mostarMensaje(String titulo, String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static ResourceBundle getResourseBundle() {
		FacesContext facesContext= FacesContext.getCurrentInstance();
		ResourceBundle resourceBundle= facesContext.getApplication().getResourceBundle(facesContext, "msg");
		return resourceBundle;
	}
	
	public static String getPath() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}
}
