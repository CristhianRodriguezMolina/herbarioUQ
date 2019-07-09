package co.gov.jsasociados.bean;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.gov.jsasociados.Cuenta;
import co.gov.jsasociados.Recolector;
import co.gov.jsasociados.ejb.AdminEJB;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;
import co.gov.jsasociados.util.Util;

/**
 * 
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("empleadoBean")
@ApplicationScoped
public class EmpleadoBean {
	/**
	 * cedula de la persona
	 */
	private String cedula;
	/**
	 * nombre de la persona
	 */
	private String nombre;
	/**
	 * apellidos de la persona
	 */
	private String apellidos;
	/**
	 * telefono de la persona
	 */
	private String telefono;
	/**
	 * correo de la persona
	 */
	private String correo;
	/**
	 * direccion de la persona
	 */
	private String direccion;
	/**
	 * cuenta de una persona
	 */
	/**
	 * usuario unico de una cuenta
	 */
	private String usuario;
	/**
	 * contraseña de una cuenta
	 */
	private String contrasenia;

	/**
	 * 
	 */
	@EJB
	private AdminEJB adminEJB;
	
	/**
	 * permite realizar el registro de un recolector en base de datos
	 * 
	 * @return
	 */
	public String registrarRecolector() {
		Recolector recolector = new Recolector();
		recolector.setCedula(cedula);
		recolector.setNombre(nombre);
		recolector.setApellidos(apellidos);
		recolector.setCorreo(correo);
		recolector.setDireccion(direccion);
		recolector.setTelefono(telefono);

		Cuenta cuenta = new Cuenta();
		cuenta.setUsuario(usuario);
		cuenta.setContrasenia(contrasenia);
		cuenta.setPersona(recolector);
		recolector.setCuenta(cuenta);

//		recolector.setComentarios(comentarios);
//		recolector.setRegistro(registro);

		try {
			adminEJB.insertarRecolector(recolector);
			Util.mostarMensaje("Registrado con exito", "Se ha registrado la persona con exito.");
			limpiarCampos();
			return "/registra_recolector";
		} catch (ElementoRepetidoException | PersonaNoRegistradaException e) {
			// TODO Auto-generated catch block
			Util.mostarMensaje(e.getMessage(), e.getMessage());
			return null;
		}

	}

	private void limpiarCampos() {
		// TODO Auto-generated method stub
		cedula="";
		nombre="";
		apellidos="";
		correo="";
		direccion="";
		telefono="";
		usuario="";
		contrasenia="";
	}
	
	/**
	 * metodo que pemite llenar los campos de un recolector 
	 * @return
	 */
	public String llenarCamposRecolector() {
		if (cedula!=null) {
			try {
				Recolector recolector= adminEJB.buscarRecolector(cedula);
				if (recolector!=null) {
					nombre=recolector.getNombre();
					apellidos=recolector.getApellidos();
					telefono=recolector.getTelefono();
					correo=recolector.getCorreo();
					direccion= recolector.getDireccion();
					cedula=recolector.getCedula();
					return "";
				}
			} catch (PersonaNoRegistradaException | TipoClaseException e) {
				// TODO Auto-generated catch block
				Util.mostarMensaje(String.format("Error inesperado %s", e.getMessage()), e.getMessage());
				return "";
			}
			
		}
		return "";
	}
	/**
	 * metodo que permite modificar los datos de un recolector
	 * 
	 * @return
	 */
	public String modificarRecolector() {
		try {
			adminEJB.modificarRecolector(nombre, apellidos, telefono, correo, direccion, cedula, usuario, contrasenia);
			limpiarCampos();
			cedula=adminEJB.buscarRecolector(cedula).getCedula();
			llenarCamposRecolector();
			return "";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Util.mostarMensaje(String.format("Error inesperado %s", e.getMessage()), e.getMessage());
			return null;
		}
	}
	
	/**
	 * metodo que permite eliminar un recolector
	 * @return
	 */
	public String eliminarRecolector() {
		try {
			if (adminEJB.eliminarRecolector(cedula)) {
				limpiarCampos();
				return "/eliminar_recolector";
			}else {
				return "";
			}
		} catch (PersonaNoRegistradaException | TipoClaseException e) {
			// TODO Auto-generated catch block
			Util.mostarMensaje(String.format("Error inesperado %s", e.getMessage()), e.getMessage());
			return "";
		}
	}
	
	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}
