package co.gov.jsasociados.modelo;


import co.gov.jsasociados.Persona;
import javafx.beans.property.SimpleStringProperty;
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
	private StringProperty cedula;
	/**
	 * nombre observable de una persona
	 */
	private StringProperty nombre;
	/**
	 * apellido observable de un empleado
	 */
	private StringProperty apellido;
	/**
	 * email observable de un empleado
	 */
	private StringProperty correo;
	/**
	 * clave observable de un empleado
	 */
	private StringProperty telefono;

	private StringProperty direccion;
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
		this.cedula = new SimpleStringProperty(persona.getCedula());
		this.nombre = new SimpleStringProperty(persona.getNombre());
		this.apellido = new SimpleStringProperty(persona.getApellidos());
		this.correo = new SimpleStringProperty(persona.getCorreo());
		this.direccion = new SimpleStringProperty(persona.getDireccion());
		this.telefono = new SimpleStringProperty(persona.getTelefono());
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
	public PersonaObservable(String cedula, String nombre, String apellido, String correo, String direccion, String telefono) {

		this.cedula = new SimpleStringProperty(cedula);
		this.nombre = new SimpleStringProperty(nombre);
		this.apellido = new SimpleStringProperty(apellido);
		this.correo = new SimpleStringProperty(correo);
		this.direccion= new SimpleStringProperty(direccion);
		this.telefono = new SimpleStringProperty(telefono);

	}

	/**
	 * @return the cedula
	 */
	public StringProperty getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(StringProperty cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the nombre
	 */
	public StringProperty getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public StringProperty getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(StringProperty apellido) {
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
	public StringProperty getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(StringProperty correo) {
		this.correo = correo;
	}

	/**
	 * @return the telefono
	 */
	public StringProperty getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(StringProperty telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the direccion
	 */
	public StringProperty getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(StringProperty direccion) {
		this.direccion = direccion;
	}
	

}
