package co.gov.jsasociados.controlador;

import java.io.IOException;

import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Main;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.modelo.AdministradorDelegado;
import co.gov.jsasociados.modelo.EmpleadoObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Permite manejar los escenarios que tiene la aplicacion
 * 
 * @author EinerZG
 * @version 1.0
 */
public class ManejadorEscenarios {

	/**
	 * contenedor prinpipal de la aplicacion
	 */
	private Stage escenario;
	/**
	 * tipo de panel inicial
	 */
	@FXML
	private BorderPane bordePanel;
	/**
	 * para almacenar empleados observables
	 */
	private ObservableList<EmpleadoObservable> empleadosObservables;
	/**
	 * conexion con capa de negocio
	 */
	private AdministradorDelegado administradorDelegado;

	/**
	 * recibe el escenario principla de la aplicacion
	 * 
	 * @param escenario inicial
	 */
	public ManejadorEscenarios(Stage escenario) {

		administradorDelegado = AdministradorDelegado.administradorDelegado;
		this.escenario = escenario;

		try {
			// se inicializa el escenario
			escenario.setTitle("Herbario");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/principal.fxml"));

			
			bordePanel = ((BorderPane) loader.load());
			bordePanel.setPrefSize(1100, 600);
			
			cargarEscena();
			
			// se carga la escena
			Scene scene = new Scene(bordePanel);
			escenario.setScene(scene);
			escenario.show();

			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	/**
	 * carga una escena en el centro del escenario
	 */
	public void cargarEscena() {

		try {

			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(Main.class.getResource("./vista/registro_empleado.fxml"));
			BorderPane panel = (BorderPane) loader2.load();
			bordePanel.setCenter(panel);
			
			RegistroEmpleadoControlador controlador = loader2.getController();
			controlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * muestra el escenario para crear un empleado nuevo
	 */
	public void cargarEscenarioCrearEmpleado() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/vista/editar_empleado.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Crear"); 
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EdicionEmpleadoControlador empleadoControlador = loader.getController();
			empleadoControlador.setEscenarioEditar(escenarioCrear); 
			empleadoControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return empleados observables
	 */
	public ObservableList<EmpleadoObservable> getEmpleadosObservables() {
		return empleadosObservables;
	}

	/**
	 * permite agrega una liente a la lista obsevable
	 * 
	 * @param empleado
	 */
	public void agregarALista(Persona empleado) {
		empleadosObservables.add(new EmpleadoObservable(empleado));
	}

	/**
	 * deveulve una instancia del escenario
	 * 
	 * @return escenario
	 */
	public Stage getEscenario() {
		return escenario;
	}

	/**
	 * permite registrar una persona en la base de datos
	 * 
	 * @param empleado a registrar
	 * @return true si quedo registrado
	 */
	public boolean registrarEmpleado(Empleado empleado) {
		try {
			return administradorDelegado.registrarEmpleado(empleado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * permite eliminar un empleado
	 * 
	 * @param empleado a ser eliminado
	 * @return true si fue eliminado false si no
	 */
	public boolean eliminarEmpleado(Empleado empleado) {
		return administradorDelegado.eliminarEmpleado(empleado);
	}

}
