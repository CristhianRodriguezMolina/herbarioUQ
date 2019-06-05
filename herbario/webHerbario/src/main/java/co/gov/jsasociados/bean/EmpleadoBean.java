package co.gov.jsasociados.bean;


import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.gov.jsasociados.Cuenta;
import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.ejb.AdminEJB;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.util.Util;

/**
 * 
 *
 */
@FacesConfig(version=Version.JSF_2_3)
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
	 * permite realizar el registro de un empleado en base de datos
	 * @return
	 */
	public String registrar() {
		Empleado empleado= new Empleado();
		empleado.setCedula(cedula);
		empleado.setNombre(nombre);
		empleado.setApellidos(apellidos);
		empleado.setCorreo(correo);
		empleado.setDireccion(direccion);
		empleado.setTelefono(telefono);
	
		Cuenta cuenta= new Cuenta();
		cuenta.setUsuario(usuario);
		cuenta.setContrasenia(contrasenia);
		cuenta.setPersona(empleado);
		empleado.setCuenta(cuenta);
		
//		empleado.setComentarios(comentarios);
//		empleado.setRegistro(registro);
		
		try {
			adminEJB.insertarEmpleado(empleado);
			Util.mostarMensaje("Registrado con exito", "Se ha registrado la persona con exito.");
			return "lista";
		} catch (ElementoRepetidoException | PersonaNoRegistradaException e) {
			// TODO Auto-generated catch block
			Util.mostarMensaje(String.format("Error inesperado %s", e.getMessage()), e.getMessage());
			return null;
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
