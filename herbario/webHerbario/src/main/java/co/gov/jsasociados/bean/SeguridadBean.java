package co.gov.jsasociados.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;
import javax.swing.text.Utilities;

import co.gov.jsasociados.Persona;
import co.gov.jsasociados.ejb.AdminEJB;
import co.gov.jsasociados.util.Util;
import co.gov.jsasocioados.exeption.SesionException;

/**
 * permite manejar la sesion en la aplicacion web
 *
 */
@Named("seguridadBean")
@FacesConfig(version = Version.JSF_2_3)
@SessionScoped
public class SeguridadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * el ejb
	 */
	@EJB
	private AdminEJB adminEJB;
	/**
	 * persona que inicio sesion
	 */
	private Persona usuario;
	/**
	 * dice si la usuario inicio sesion o no
	 */
	private boolean autenticado;

	/**
	 * inicializar la informacion base de la sesion
	 */
	@PostConstruct
	private void init() {
		usuario = new Persona();
		autenticado = false;
	}

	/**
	 * permite iniciar la sesion de un usuario
	 */
	public void iniciarSesion() {
		Persona persona;
		try {
			persona = adminEJB.iniciarSesion(usuario.getCuenta().getUsuario(), usuario.getCuenta().getContrasenia());
			if (persona != null) {
				usuario = persona;
				autenticado = true;
			}
			else {
				Util.mostarMensaje("Verifique sus datos", "verifique sus datos ingersados");
			}
		} catch (SesionException e) {
			// TODO Auto-generated catch block
			Util.mostarMensaje(e.getMessage(), e.getMessage());
		}
		
	}

	/**
	 * @return the usuario
	 */
	public Persona getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the autenticado
	 */
	public boolean isAutenticado() {
		return autenticado;
	}

	/**
	 * @param autenticado the autenticado to set
	 */
	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

}
