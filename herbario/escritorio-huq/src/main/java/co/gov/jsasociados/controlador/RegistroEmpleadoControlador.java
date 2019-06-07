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

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtNombre"
	private TextField txtNombre; // Value injected by FXMLLoader

	@FXML // fx:id="txtApellidos"
	private TextField txtApellidos; // Value injected by FXMLLoader

	@FXML // fx:id="txtCorreo"
	private TextField txtCorreo; // Value injected by FXMLLoader

	@FXML // fx:id="txtTelefono"
	private TextField txtTelefono; // Value injected by FXMLLoader

	@FXML // fx:id="txtDireccion"
	private TextField txtDireccion; // Value injected by FXMLLoader

	@FXML // fx:id="txtUsuario"
	private TextField txtUsuario; // Value injected by FXMLLoader

	@FXML // fx:id="btnAceptar"
	private Button btnAceptar; // Value injected by FXMLLoader

	@FXML // fx:id="txtCedula"
	private TextField txtCedula; // Value injected by FXMLLoader

	@FXML // fx:id="txtContrasenia"
	private PasswordField txtContrasenia; // Value injected by FXMLLoader
	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;

	@FXML // This method is called by the FXMLLoader when initialization is complete
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

			if (escenarioInicial.registrarEmpleado(empleado)) {
				Utilidades.mostrarMensaje("Registro exitoso", "Se ha registrado de forma exitosa.");
			}else {
				Utilidades.mostrarMensaje("Registro", "Error en registro!!");
			}
			
		}
	}
}
