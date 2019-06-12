package co.gov.jsasociados.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
    private Button btnGestionarEspecies;
    /**
     * boton de navegacion
     */
    @FXML
    private Button btnNavegacion;
    /**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;
	
	@FXML
	void iniatilize()
	{
		
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
    void cargarEscenaPlanta(ActionEvent event) {
    	escenarioInicial.cargarEscenaPlanta();
    }
	
}
