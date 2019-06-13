/**
 * Sample Skeleton for 'registro_empleado.fxml' Controller Class
 */

package co.gov.jsasociados.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import co.gov.jsasociados.Cuenta;
import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.modelo.AdministradorDelegado;
import co.gov.jsasociados.util.Utilidades;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroEmpleadoControlador {

	/**
	 * 
	 */
	@FXML 
	private ResourceBundle resources;
	/**
	 * 
	 */
	@FXML
	private URL location;
	/**
	 * 
	 */
	@FXML
	private TextField txtNombre; 
	/**
	 * 
	 */
	@FXML 
	private TextField txtApellidos;
	/**
	 * 
	 */
	@FXML 
	private TextField txtCorreo;
	/**
	 * 
	 */
	@FXML 
	private TextField txtTelefono;
	/**
	 * 
	 */
	@FXML
	private TextField txtDireccion; 
	/**
	 * 
	 */
	@FXML
	private TextField txtUsuario; 
	/**
	 * 
	 */
	@FXML 
	private Button btnAceptar;
	/**
	 * 
	 */
	@FXML 
	private TextField txtCedula; 
	/**
	 * 
	 */
	@FXML 
	private PasswordField txtContrasenia; 
	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;

	@FXML
	void initialize() {
		assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtApellidos != null : "fx:id=\"txtApellidos\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtCorreo != null : "fx:id=\"txtCorreo\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtTelefono != null : "fx:id=\"txtTelefono\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtDireccion != null : "fx:id=\"txtDireccion\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtUsuario != null : "fx:id=\"txtUsuario\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert btnAceptar != null : "fx:id=\"btnAceptar\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtCedula != null : "fx:id=\"txtCedula\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtContrasenia != null : "fx:id=\"txtContrasenia\" was not injected: check your FXML file 'registro_empleado.fxml'.";

	}
	
	/**
	 * conexion con la capade negocio
	 */
	private AdministradorDelegado administradorDelegado;

	/**
	 * permite obtener una instancia del escenario general
	 * 
	 * @param escenarioInicial
	 */
	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		administradorDelegado=AdministradorDelegado.administradorDelegado;
		this.escenarioInicial = escenarioInicial;
	}

	/**
	 * Metodo para insertar un empleado a la base de datos por medio de un botón
	 * @param e1
	 */
	@FXML
	void insertarEmpleado(ActionEvent e1) {
		if (e1.getSource().equals(btnAceptar)) {
			if (validarCampos()) {
				Empleado empleado = new Empleado();
				empleado.setCedula(txtCedula.getText().trim());
				empleado.setNombre(txtNombre.getText().trim());
				empleado.setApellidos(txtApellidos.getText().trim());
				empleado.setCorreo(txtCorreo.getText().trim());
				empleado.setDireccion(txtDireccion.getText().trim());
				empleado.setTelefono(txtTelefono.getText().trim());

				Cuenta cuenta = new Cuenta();
				cuenta.setUsuario(txtUsuario.getText().trim());
				cuenta.setContrasenia(txtContrasenia.getText().trim());
				cuenta.setPersona(empleado);
				empleado.setCuenta(cuenta);
				
				try {
					empleado = administradorDelegado.registrarEmpleado(empleado);
					if (empleado!=null) {
						Utilidades.mostrarMensaje("Registro con exito", "Se ha registrado al empleado "+empleado.getNombre()+" con exito.");
					}
					
				} catch (ElementoRepetidoException | PersonaNoRegistradaException e) {
					// TODO Auto-generated catch block
					Utilidades.mostrarMensaje("Error", e.getMessage());
				}
			}
			
			//ESTO HAY QUE OBTENER EL DELEGADO DESDE ACA
//			try {
//				if (escenarioInicial.registrarEmpleado(empleado)) {
//					Utilidades.mostrarMensaje("Registro exitoso", "Se ha registrado de forma exitosa.");
//				}else {
//					Utilidades.mostrarMensaje("Registro", "Error en registro!!");
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
	}
	
	/**
	 * metodo que permite verificar que todos los campos esten completos
	 * @return
	 */
	private boolean validarCampos() {
		// TODO Auto-generated method stub
		if (txtCedula.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese un numero de cedula");
			return false;
		}
		if (txtNombre.getText().trim().endsWith("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese un nombre");
			return false;
		}
		if (txtApellidos.getText().trim().endsWith("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese los apellidos");
			return false;
		}
		if (txtCorreo.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese el correo");
			return false;
		}
		if (txtDireccion.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese la direccion");
			return false;
		}
		if (txtTelefono.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese el numero de telefono");
			return false;
		}
		if (txtUsuario.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese el nombre de usuario");
			return false;
		}
		if (txtContrasenia.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese una contraseña");
			return false;
		}
		return true;
	}
}
