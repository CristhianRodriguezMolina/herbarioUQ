package co.gov.jsasociados.controlador;


import co.gov.jsasociados.Persona;
import co.gov.jsasociados.modelo.AdministradorDelegado;
import co.gov.jsasociados.util.Utilidades;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginControlador {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private Button btnIngresar;

    @FXML
    private Label lblRecuperacionContrasenia;

  //VARIABLES NECESARIAS
    /**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;
	/**
	 * Instancia del administrador delegado
	 */
	private AdministradorDelegado administradorDelegado;
	/**
	 * escenario
	 */
	private Stage escenario;
    
	/**
	 * 
	 * @param escenarioInicial
	 */
    public void setEscenarioInicial(ManejadorEscenarios escenarioInicial, Stage escenario) {
		administradorDelegado = AdministradorDelegado.administradorDelegado;
		this.escenarioInicial = escenarioInicial;
		this.escenario = escenario;
    }
	
    @FXML
    void signIn(ActionEvent event) throws PersonaNoRegistradaException {
    	Utilidades.enviarConGMail("sosorioa_2@uqvirtual.edu.co", "puto cotizon", "NOT REPLY");
    	if (validarCampos()) {
    		Persona p = administradorDelegado.buscarPersona(txtUsuario.getText().trim());
        	if(p != null) {
        		if(p.getCuenta().getContrasenia().equals(txtContrasenia.getText().trim())) {
        			escenarioInicial.setUser(p);
        			escenarioInicial.iniciarAplicacion(new Stage());
        			escenario.close();
        		}else {
            		Utilidades.mostrarMensaje("Error", "Contraseña invalida");
            	}
        	}else {
        		Utilidades.mostrarMensaje("Error", "Usuario invalido");
        	}
		}
    	
    	
    }

	private boolean validarCampos() {
		// TODO Auto-generated method stub
		if (txtUsuario.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Completar", "Por favor ingrese un nobre de usuario.");
			return false;
		}
		if (txtContrasenia.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Completar", "Por favor ingrese una contraseña");
			return false;
		}
		return true;
	}
    
}