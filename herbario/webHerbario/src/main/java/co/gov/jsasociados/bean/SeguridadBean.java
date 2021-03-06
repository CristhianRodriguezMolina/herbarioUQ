package co.gov.jsasociados.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JOptionPane;
import javax.swing.text.Utilities;

import co.gov.jsasociados.Administrador;
import co.gov.jsasociados.Cuenta;
import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Recolector;
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
	 * Cuenta del usuario
	 */
	private Cuenta cuenta;
	/**
	 * dice si la usuario inicio sesion o no
	 */
	private boolean autenticado;
	/**
	 * dice si la usuario es un administrador
	 */
	private boolean administrador;
	/**
	 * dice si la usuario es un empleado
	 */
	private boolean empleado;
	/**
	 * dice si la usuario es un recolector
	 */
	private boolean recolector;

	//BEANS EXTERNOS
	/**
	 * Bean de registros
	 */
	@Inject
	@ManagedProperty(value = "#{registroBean}")
	private RegistroBean registroBean;
	
	/**
	 * inicializar la informacion base de la sesion
	 */
	@PostConstruct
	private void init() {
		usuario = new Persona();
		cuenta = new Cuenta();
		autenticado = false;
		administrador = false;
		empleado = false;
		recolector = false;
	}

	/**
	 * permite iniciar la sesion de un usuario
	 */
	public void iniciarSesion() {
				
		Persona persona;
		
		try {
			persona = adminEJB.iniciarSesion(cuenta.getUsuario(), cuenta.getContrasenia());
			if (persona != null) {
				usuario = persona;
				autenticado = true;
				administrador = persona.getClass().getSimpleName().equals(Administrador.class.getSimpleName())? true:false;
				empleado = persona.getClass().getSimpleName().equals(Empleado.class.getSimpleName())? true:false;
				recolector = persona.getClass().getSimpleName().equals(Recolector.class.getSimpleName())? true:false;		
				registroBean.setUsuario(usuario);
				registroBean.reiniciar();				
			}
			else {
				Util.mostarMensaje("Verifique sus datos", "verifique sus datos ingersados");
			}
		} catch (SesionException e) {
			Util.mostarMensaje(e.getMessage(), e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	/**
	 * permite cerrar sesion
	 */
	public String cerrarSesion() {
		if (cuenta != null) {
			init();		
			return "success";
		}
		
		return null;
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

	/**
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the administrador
	 */
	public boolean isAdministrador() {
		return administrador;
	}

	/**
	 * @param administrador the administrador to set
	 */
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	/**
	 * @return the empleado
	 */
	public boolean isEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(boolean empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return the recolector
	 */
	public boolean isRecolector() {
		return recolector;
	}

	/**
	 * @param recolector the recolector to set
	 */
	public void setRecolector(boolean recolector) {
		this.recolector = recolector;
	}

	
}
