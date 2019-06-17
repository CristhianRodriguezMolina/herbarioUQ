package co.gov.jsasociados.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrincipalControlador {


	/**
	 * boton de gestion de empleados
	 */
	@FXML
    private Button btnGestionarEmpleados;
	/**
	 * boton de gestion de plantas, generos y familias
	 */
    @FXML
    private Button btnGestionarGenerosPlantas;
    /**
     * boton de navegacion
     */
    @FXML
    private Button btnNavegacion;
    /**
     * boton de registro de especies
     */
    @FXML
    private Button btnRegistrarExpecies;
    /**
     * boton de salir
     */
    @FXML
    private Button btnSalir;
    /**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;
	/**
	 * escenario
	 */
	private Stage escenario;
	
	@FXML
	void iniatilize()
	{
		
	}
    
	@FXML
	void salir() {
		escenario.close();
	}
	
    /**
	 * permite obtener una instancia del escenario general
	 * 
	 * @param escenarioInicial
	 */
	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial, Stage escenario) {
		this.escenarioInicial = escenarioInicial;
		this.escenario = escenario;
	}
    
	/**
	 * Metodo para cargar la escena de gestion del empleado
	 * @param event
	 */
    @FXML
    void cargarEscenaEmpleado(ActionEvent event) {
    	escenarioInicial.cargarEscenaEmpleado();
    }

    /**
	 * Metodo para cargar la escena de navegacion y busqueda
	 * @param event
	 */
    @FXML
    void cargarEscenaNavegacion(ActionEvent event) {
    	escenarioInicial.cargarEscenaNavegacion();
    }

    /**
	 * Metodo para cargar la escena de gestion de plantas, etc
	 * @param event
	 */
    @FXML
    void cargarEscenaRegistroGenerosFamilias(ActionEvent event) {
    	escenarioInicial.cargarEscenaRegistroGenerosFamilias();
    }
    
    @FXML
    void cargarEscenaRegistroPlantas(ActionEvent event) {
    	escenarioInicial.cargarEscenaRegistrarPlantas();
    }
	
}
