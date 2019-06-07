/**
 * Sample Skeleton for 'registro_empleado.fxml' Controller Class
 */

package co.gov.jsasociados.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import co.gov.jsasociados.Cuenta;
import co.gov.jsasociados.Empleado;
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
	void insertarEmpleado(ActionEvent e1)
	{		
		System.out.println("1");
		if(e1.getSource().equals(btnAceptar))
		{
			System.out.println("2");
			Empleado e = new Empleado();
			e.setCedula(txtCedula.getText());
			e.setNombre(txtNombre.getText());
			e.setApellidos(txtApellidos.getText());
			e.setCorreo(txtCorreo.getText());
			e.setDireccion(txtDireccion.getText());
			e.setTelefono(txtTelefono.getText());
			
			Cuenta c = new Cuenta();
			c.setUsuario(txtUsuario.getText());
			c.setContrasenia(txtContrasenia.getText());
//			c.setPersona(e);
			e.setCuenta(c);
			
			System.out.println(e.toString());
			
			escenarioInicial.registrarEmpleado(e);
		}
	}
}
