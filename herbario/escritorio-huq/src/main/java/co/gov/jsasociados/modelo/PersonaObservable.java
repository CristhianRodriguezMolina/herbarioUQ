package co.gov.jsasociados.modelo;

import co.gov.jsasociados.Persona;
import javafx.beans.property.StringProperty;

/**
 * Permite transformar una persona en formato observable
 * 
 * @version 1.0
 */
public class PersonaObservable {

	/**
	 * cedula observable de un empleado
	 */
	private String cedula;
	/**
	 * nombre observable de una persona
	 */
	private String nombre;
	/**
	 * apellido observable de un empleado
	 */
	private String apellido;
	/**
	 * email observable de un empleado
	 */
	private String correo;
	/**
	 * clave observable de un empleado
	 */
	private String telefono;

	private String direccion;
//	/**
//	 * fecha de nacimiento observable de un empleado
//	 */
//	private ObjectProperty<Date> fechaNacimiento;
	/**
	 * empleado asociado
	 */
	private Persona persona;

	/**
	 * constructor que genera con persona observable con base a un persona
	 * 
	 * @param persona que se quiere volver observable
	 */
	public PersonaObservable(Persona persona) {

		this.persona = persona;
		this.cedula = persona.getCedula();
		this.nombre = persona.getNombre();
		this.apellido = (persona.getApellidos());
		this.correo = persona.getCorreo();
		this.direccion = persona.getDireccion();
		this.telefono = persona.getTelefono();
	}

	/**
	 * permite generar una instacioa usando todos los elementos
	 * 
	 * @param cedula
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param clave
	 * @param fecha
	 */
	public PersonaObservable(String cedula, String nombre, String apellido, String correo, String direccion,
			String telefono) {

		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = (correo);
		this.direccion = direccion;
		this.telefono = telefono;
		
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the persona
	 */
	public Persona getEmpleado() {
		return persona;
	}

	/**
	 * @param persona the persona to set
	 */
	public void setEmpleado(Persona persona) {
		this.persona = persona;
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

}
