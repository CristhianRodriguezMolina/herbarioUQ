package co.gov.jsasociados.controlador;

import java.io.IOException;

import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Main;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.modelo.AdministradorDelegado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
	
//	/**
//	 * para almacenar empleados observables
//	 */
//	private ObservableList<EmpleadoObservable> empleadosObservables;
//	/**
//	 * conexion con capa de negocio
//	 */
//	private AdministradorDelegado administradorDelegado;

	/**
	 * recibe el escenario principla de la aplicacion
	 * 
	 * @param escenario inicial	
	 * @throws Exception 
	 */
	public ManejadorEscenarios(Stage escenario){

//		administradorDelegado = AdministradorDelegado.administradorDelegado;
		this.escenario = escenario;

		try {
			// se inicializa el escenario
			escenario.setTitle("Herbario");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/principal.fxml"));

			scrollPanel = ((ScrollPane) loader.load());

			PrincipalControlador pc = loader.getController();
			pc.setEscenarioInicial(this);
			
//			cargarEscenaEmpleado();
//			cargarEscenaRegistroGenerosFamilias();			
//			cargarEscenaRegistrarPlantas();
			cargarEscenaNavegacion();
			
			// se carga la escena
			Scene scene = new Scene(scrollPanel);
			escenario.setScene(scene);
			escenario.show();

			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * carga una escena en el centro del escenario
	 * @throws Exception 
	 */
	public void cargarEscenaRegistrarPlantas(){

		try {

			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(Main.class.getResource("./vista/registro_planta.fxml"));
			ScrollPane panel = (ScrollPane) loader2.load();
			((BorderPane)scrollPanel.getContent()).setCenter(panel);
						
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
			((BorderPane)scrollPanel.getContent()).setCenter(panel);
			
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
			((BorderPane)scrollPanel.getContent()).setCenter(panel);
			
//			RegistroEmpleadoControlador controlador = loader2.getController();
//			controlador.setEscenarioInicial(this);

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
			((BorderPane)scrollPanel.getContent()).setCenter(panel);
			
			GestionGFControlador gestionGPControlador= loader2.getController();
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
	
	
	
	
	
	
	
	
	//METODOS DE ENTIDADES OBSERVABLES Y DELEGADOS

//	/**
//	 * 
//	 * @return empleados observables
//	 */
//	public ObservableList<EmpleadoObservable> getEmpleadosObservables() {
//		return empleadosObservables;
//	}
//
//	/**
//	 * permite agrega una liente a la lista obsevable
//	 * 
//	 * @param empleado
//	 */
//	public void agregarALista(Persona empleado) {
//		empleadosObservables.add(new EmpleadoObservable(empleado));
//	}

	/**
	 * deveulve una instancia del escenario
	 * 
	 * @return escenario
	 */
	public Stage getEscenario() {
		return escenario;
	}

//	/**
//	 * permite registrar una persona en la base de datos
//	 * 
//	 * @param empleado a registrar
//	 * @return true si quedo registrado
//	 */
//	public boolean registrarEmpleado(Empleado empleado) throws Exception{
//		//try {
//			return administradorDelegado.registrarEmpleado(empleado)!=null;
//		//} catch (Exception e) {
//			//e.printStackTrace();
//		//}
//	}
//
//	/**
//	 * permite eliminar un empleado
//	 * 
//	 * @param empleado a ser eliminado
//	 * @return true si fue eliminado false si no
//	 */
//	public boolean eliminarEmpleado(Empleado empleado) {
//		return administradorDelegado.eliminarEmpleado(empleado);
//	}

//	/**
//	 * permite registrar una planta en la base de datos
//	 * 
//	 * @param planta a registrar
//	 * @return true si quedo registrado
//	 */
//	public boolean registrarPlanta(Planta planta) throws Exception{
//			return administradorDelegado.registrarEspecie(planta) != null;
//	}
}
