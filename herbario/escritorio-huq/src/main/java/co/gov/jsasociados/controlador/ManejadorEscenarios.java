package co.gov.jsasociados.controlador;

import java.io.IOException;

import co.gov.jsasociados.Main;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.modelo.PlantaObservable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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
	private ScrollPane scrollPanel;
	
	/**
	 * tipo de panel login
	 */
	@FXML
	private AnchorPane anchoPanel;

	/**
	 * Usuario actual
	 */
	private Persona user;

	/**
	 * Login
	 * @param escenario
	 */
	public ManejadorEscenarios(Stage escenario) {
		try {
			// se inicializa el escenario
			escenario.setTitle("Login");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/login.fxml"));

			anchoPanel = loader.load();
			
			LoginControlador controlador = loader.getController();
			controlador.setEscenarioInicial(this, escenario);			
			
			// se carga la escena
			Scene scene = new Scene(anchoPanel);
			escenario.setScene(scene);
			escenario.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * recibe el escenario principla de la aplicacion
	 * 
	 * @param escenario inicial
	 * @throws Exception
	 */
	public void iniciarAplicacion(Stage escenario) {

		this.escenario = escenario;

		try {
			// se inicializa el escenario
			escenario.setTitle("Herbario");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/principal.fxml"));

			scrollPanel = ((ScrollPane) loader.load());

			PrincipalControlador pc = loader.getController();
			pc.setEscenarioInicial(this, escenario);

			cargarEscenaEmpleado();
			cargarEscenaRegistroGenerosFamilias();
			cargarEscenaRegistrarPlantas();
			cargarEscenaNavegacion();

			// se carga la escena
			Scene scene = new Scene(scrollPanel);
			escenario.setScene(scene);
			escenario.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void cargarEscenaDetallePlantas(ManejadorEscenarios me, PlantaObservable po) {

		try {

			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(Main.class.getResource("./vista/detalle_planta.fxml"));
			ScrollPane panel = (ScrollPane) loader2.load();
			((BorderPane) scrollPanel.getContent()).setCenter(panel);

			DetallePlantaControlador controlador = loader2.getController();
			controlador.setEscenarioInicial(me, po);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * carga una escena en el centro del escenario
	 * 
	 * @throws Exception
	 */
	public void cargarEscenaRegistrarPlantas() {

		try {

			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(Main.class.getResource("./vista/registro_planta.fxml"));
			ScrollPane panel = (ScrollPane) loader2.load();
			((BorderPane) scrollPanel.getContent()).setCenter(panel);

			RegistroPlantaControlador controlador = loader2.getController();
			controlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * carga una escena en el centro del escenario
	 */
	public void cargarEscenaEmpleado() {

		try {
			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(Main.class.getResource("./vista/registro_empleado.fxml"));
			BorderPane panel = (BorderPane) loader2.load();
			((BorderPane) scrollPanel.getContent()).setCenter(panel);

			RegistroEmpleadoControlador controlador = loader2.getController();
			controlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * carga una escena en el centro del escenario
	 */
	public void cargarEscenaNavegacion() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/navegacion_y_busquedas.fxml"));
			BorderPane panel = (BorderPane) loader.load();
			((BorderPane) scrollPanel.getContent()).setCenter(panel);

			NavegacionBusquedaControlador controlador = loader.getController();
			controlador.setEscenarioInicial(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * carga una escena en el centro del escenario
	 */
	public void cargarEscenaRegistroGenerosFamilias() {

		try {

			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(Main.class.getResource("./vista/registro_generos_familias.fxml"));
			ScrollPane panel = (ScrollPane) loader2.load();
			((BorderPane) scrollPanel.getContent()).setCenter(panel);

			GestionGFControlador gestionGPControlador = loader2.getController();
			gestionGPControlador.setEscenarioInicial(this);

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
	 * deveulve una instancia del escenario
	 * 
	 * @return escenario
	 */
	public Stage getEscenario() {
		return escenario;
	}

	/**
	 * @return the user
	 */
	public Persona getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Persona user) {
		this.user = user;
	}

}
