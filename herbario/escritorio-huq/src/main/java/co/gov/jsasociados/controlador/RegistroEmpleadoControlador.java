/**
 * Sample Skeleton for 'registro_empleado.fxml' Controller Class
 */

package co.gov.jsasociados.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import co.gov.jsasociados.Cuenta;
import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.util.Utilidades;
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
	 * permite obtener una instancia del escenario general
	 * 
	 * @param escenarioInicial
	 */
	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		this.escenarioInicial = escenarioInicial;
	}

	/**
	 * Metodo para insertar un empleado a la base de datos por medio de un botón
	 * @param e1
	 */
	@FXML
	void insertarEmpleado(ActionEvent e1) {
		if (e1.getSource().equals(btnAceptar)) {
			Empleado empleado = new Empleado();
			empleado.setCedula(txtCedula.getText());
			empleado.setNombre(txtNombre.getText());
			empleado.setApellidos(txtApellidos.getText());
			empleado.setCorreo(txtCorreo.getText());
			empleado.setDireccion(txtDireccion.getText());
			empleado.setTelefono(txtTelefono.getText());

			Cuenta cuenta = new Cuenta();
			cuenta.setUsuario(txtUsuario.getText());
			cuenta.setContrasenia(txtContrasenia.getText());
			cuenta.setPersona(empleado);
			empleado.setCuenta(cuenta);

			try {
				if (escenarioInicial.registrarEmpleado(empleado)) {
					Utilidades.mostrarMensaje("Registro exitoso", "Se ha registrado de forma exitosa.");
				}else {
					Utilidades.mostrarMensaje("Registro", "Error en registro!!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
