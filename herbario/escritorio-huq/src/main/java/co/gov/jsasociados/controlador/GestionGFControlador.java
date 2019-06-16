
/**
 * Sample Skeleton for 'registro_generos_familias.fxml' Controller Class
 */

package co.gov.jsasociados.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Genero;
import co.gov.jsasociados.modelo.AdministradorDelegado;
import co.gov.jsasociados.modelo.FamiliaObservable;
import co.gov.jsasociados.modelo.GeneroObservable;
import co.gov.jsasociados.util.Utilidades;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.FamiliaYaRegistradaExeption;
import co.gov.jsasocioados.exeption.GeneroYaRegistradoExcepcion;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestionGFControlador {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="scrollPane"
	private ScrollPane scrollPane; // Value injected by FXMLLoader

	@FXML // fx:id="tableFamilias"
	private TableView<FamiliaObservable> tableFamilias; // Value injected by FXMLLoader

	@FXML // fx:id="columnIdFamilia"
	private TableColumn<FamiliaObservable, String> columnIdFamilia; // Value injected by FXMLLoader

	@FXML // fx:id="columnNombreFamilia"
	private TableColumn<FamiliaObservable, String> columnNombreFamilia; // Value injected by FXMLLoader

	@FXML // fx:id="textFieldNombreFamilia"
	private TextField textFieldNombreFamilia; // Value injected by FXMLLoader

	@FXML // fx:id="btnAgregarFamilia"
	private Button btnAgregarFamilia; // Value injected by FXMLLoader

	@FXML // fx:id="btnEliminarFamilia"
	private Button btnEliminarFamilia; // Value injected by FXMLLoader

	@FXML // fx:id="btnModificarFamilia"
	private Button btnModificarFamilia; // Value injected by FXMLLoader

	@FXML
	private Button btnModificarGenero;

	@FXML // fx:id="tableGenereros"
	private TableView<GeneroObservable> tableGenereros; // Value injected by FXMLLoader

	@FXML // fx:id="columnIdGenero"
	private TableColumn<GeneroObservable, String> columnIdGenero; // Value injected by FXMLLoader

	@FXML // fx:id="columnNombreGenero"
	private TableColumn<GeneroObservable, String> columnNombreGenero; // Value injected by FXMLLoader

	@FXML // fx:id="columnFamiliaGenero"
	private TableColumn<GeneroObservable, String> columnFamiliaGenero; // Value injected by FXMLLoader

	@FXML // fx:id="textFieldNombreGenero"
	private TextField textFieldNombreGenero; // Value injected by FXMLLoader

	@FXML // fx:id="comboBox"
	private ComboBox<FamiliaObservable> comboBox; // Value injected by FXMLLoader

	@FXML // fx:id="btnEliminarGenero"
	private Button btnEliminarGenero; // Value injected by FXMLLoader

	@FXML // fx:id="btnAgregarGenero"
	private Button btnAgregarGenero; // Value injected by FXMLLoader

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert tableFamilias != null : "fx:id=\"tableFamilias\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert columnIdFamilia != null : "fx:id=\"columnIdFamilia\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert columnNombreFamilia != null : "fx:id=\"columnNombreFamilia\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert textFieldNombreFamilia != null : "fx:id=\"textFieldNombreFamilia\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert btnAgregarFamilia != null : "fx:id=\"btnAgregarFamilia\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert btnEliminarFamilia != null : "fx:id=\"btnEliminarFamilia\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert btnModificarFamilia != null : "fx:id=\"btnModificarFamilia\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert tableGenereros != null : "fx:id=\"tableGenereros\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert columnIdGenero != null : "fx:id=\"columnIdGenero\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert columnNombreGenero != null : "fx:id=\"columnNombreGenero\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert columnFamiliaGenero != null : "fx:id=\"columnFamiliaGenero\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert textFieldNombreGenero != null : "fx:id=\"textFieldNombreGenero\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert btnEliminarGenero != null : "fx:id=\"btnEliminarGenero\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";
		assert btnAgregarGenero != null : "fx:id=\"btnAgregarGenero\" was not injected: check your FXML file 'registro_generos_familias.fxml'.";

	}

	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;

	/**
	 * conexion con la capade negocio
	 */
	private AdministradorDelegado administradorDelegado;

	/**
	 * observable de Familia
	 */
	private ObservableList<FamiliaObservable> familias;

	/**
	 * id de la familia
	 */
	private Long idFamilia;

	/**
	 * id del genero
	 */
	private Long idGenero;

	/**
	 * permite obtener una instancia del escenario general
	 * 
	 * @param escenarioInicial
	 */
	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		administradorDelegado = AdministradorDelegado.administradorDelegado;
		this.escenarioInicial = escenarioInicial;
		llenarTablaFamilias();
		llenarTablaGeneros();
		llenarComboBox();
	}

	/**
	 * metodo para agregar una familia o una planta
	 * 
	 * @param event
	 */
	@FXML
	void agregar(ActionEvent event) {
		if (event.getSource() == btnAgregarFamilia) {
			if (verificarCamposFamilia()) {
				Familia familia = new Familia();
				familia.setFamilia(textFieldNombreFamilia.getText().trim());
				try {
					familia = administradorDelegado.insertarFamilia(familia);
					if (familia != null) {
						Utilidades.mostrarMensaje("Registro con exito",
								"Se ha registrado con exito la familia " + familia.getFamilia());
						llenarTablaFamilias();
						llenarComboBox();
						vaciarCampos();
					}
				} catch (FamiliaYaRegistradaExeption e) {
					// TODO Auto-generated catch block
					Utilidades.mostrarMensaje(e.getMessage(), e.getMessage());
				}
			}
		}
		if (event.getSource() == btnAgregarGenero) {
			if (verificarCamposGenero()) {
				Genero genero = new Genero();
				genero.setGenero(textFieldNombreGenero.getText().trim());
				genero.setFamilia(comboBox.getSelectionModel().getSelectedItem().getFamilia());
				try {
					genero = administradorDelegado.insertarGenero(genero);
					if (genero != null) {
						Utilidades.mostrarMensaje("Registro con exito",
								"Se ha registrado con exito el genero " + genero.getGenero());
						llenarTablaGeneros();
						vaciarCampos();
					} else {
						System.out.println("sout no");
					}
				} catch (GeneroYaRegistradoExcepcion e) {
					// TODO Auto-generated catch block
					Utilidades.mostrarMensaje(e.getMessage(), e.getMessage());
				}
			}
		}
	}

	/**
	 * metodo para eliminar un elemento
	 * 
	 * @param event
	 */
	@FXML
	void eliminar(ActionEvent event) {

		if (event.getSource() == btnEliminarFamilia) {
			if (verificarCamposFamilia()) {
				if (idFamilia != null) {
					if (Utilidades.mostrarMensajeConfirmacion("Alerta",
							"¿Esta usted seguro de eliminar la familia con el id " + idFamilia + "?")) {

						try {
							if (administradorDelegado.eliminarFamilia(idFamilia)) {
								Utilidades.mostrarMensaje("Exito", "se ha eliminado la familia exitosamente");
								idFamilia = null;
								vaciarCampos();
								llenarTablaFamilias();
								llenarComboBox();
							}
						} catch (ElementoNoEncontradoException e) {
							// TODO Auto-generated catch block
							Utilidades.mostrarMensaje("Error", e.getMessage());
						}
					}
				}
			}
		}
		if (event.getSource() == btnEliminarGenero) {
			if (verificarCamposGenero()) {
				if (idGenero != null) {
					if (Utilidades.mostrarMensajeConfirmacion("Alerta",
							"¿Esta usted seguro de eliminar el genero con el id " + idGenero + "?")) {
						try {
							if (administradorDelegado.elimiarGenero(idGenero)) {
								Utilidades.mostrarMensaje("Exito", "se ha eliminado el genero exitosamente");
								idFamilia = null;
								vaciarCampos();
								llenarTablaGeneros();
							}
						} catch (ElementoNoEncontradoException e) {
							// TODO Auto-generated catch block
							Utilidades.mostrarMensaje("Error", e.getMessage());
						}
					}
				}
			}
		}
	}

	/**
	 * metodo para modificar un elemento
	 * 
	 * @param event
	 */
	@FXML
	void modificar(ActionEvent event) {
		if (event.getSource() == btnModificarFamilia) {
			if (verificarCamposFamilia()) {
				if (Utilidades.mostrarMensajeConfirmacion("Confirmacion",
						"¿Esta usted seguro de cambiar la informacion de la familia con el id " + idFamilia + "?")) {
					try {
						Familia familia = administradorDelegado
								.modificarFamilia(textFieldNombreFamilia.getText().trim(), idFamilia);
						if (familia != null) {
							Utilidades.mostrarMensaje("Exito", "Se ha modificado la familia " + familia.getFamilia());
							vaciarCampos();
							llenarComboBox();
							llenarTablaFamilias();
							llenarTablaGeneros();
						} else {
							Utilidades.mostrarMensaje("Error", "no se pudo ralizar el cambio");
						}
					} catch (ElementoNoEncontradoException e) {
						// TODO Auto-generated catch block
						Utilidades.mostrarMensaje("Error", e.getMessage());
					}
				}

			}
		}
		if (event.getSource() == btnModificarGenero) {
			if (verificarCamposGenero()) {
				if (Utilidades.mostrarMensajeConfirmacion("Confirmacion",
						"¿Esta usted seguro de cambiar la informacion del genero con el id " + idGenero + "?")) {
					try {
						Genero genero = administradorDelegado.modificarGenero(textFieldNombreGenero.getText().trim(), idGenero);
						if (genero != null) {
							Utilidades.mostrarMensaje("Exito", "Se ha modificado le genero " + genero.getGenero());
							vaciarCampos();
							llenarComboBox();
							llenarTablaFamilias();
							llenarTablaGeneros();
						} else {
							Utilidades.mostrarMensaje("Error", "no se pudo ralizar el cambio");
						}
					} catch (ElementoNoEncontradoException e) {
						// TODO Auto-generated catch block
						Utilidades.mostrarMensaje("Error", e.getMessage());
					}
				}
				
			}
		}
	}

	/**
	 * metodo que permite verificar que los campos esten completos
	 * 
	 * @return
	 */
	private boolean verificarCamposFamilia() {
		if (textFieldNombreFamilia.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor el nombre de la familia.");
			return false;
		}
		return true;
	}

	/**
	 * metodo que permite validar los campos requeridos de un genero
	 * 
	 * @return
	 */
	private boolean verificarCamposGenero() {
		if (textFieldNombreGenero.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor el nombre del genero.");
			return false;
		}
		if (comboBox.getSelectionModel().getSelectedIndex() == -1) {
			Utilidades.mostrarMensaje("Seleccione una familia", "Por favor seleccione una familia para el genero.");
			return false;
		}
		return true;
	}

	/**
	 * metodo que permite limpiar los campos
	 */
	private void vaciarCampos() {
		textFieldNombreFamilia.clear();
		textFieldNombreGenero.clear();
		comboBox.getSelectionModel().select(-1);
		idFamilia = null;
		idGenero = null;
	}

	/**
	 * metodo que permite agregar elemetos al comboBox
	 */
	private void llenarComboBox() {
		try {
			familias = administradorDelegado.listarFamiliasObservables();
			comboBox.setItems(familias);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * metodo que permite llenar la tabla generos
	 */
	private void llenarTablaGeneros() {
		// TODO Auto-generated method stub
		try {
			ObservableList<GeneroObservable> generos = administradorDelegado.listarGenerosObservable();
			tableGenereros.setItems(generos);
			columnIdGenero.setCellValueFactory(new PropertyValueFactory<GeneroObservable, String>("idGenero"));
			columnNombreGenero.setCellValueFactory(new PropertyValueFactory<GeneroObservable, String>("generoNombre"));
			columnFamiliaGenero
					.setCellValueFactory(new PropertyValueFactory<GeneroObservable, String>("familiaNombre"));
			tableGenereros.getColumns().setAll(columnIdGenero, columnNombreGenero, columnFamiliaGenero);
			tableGenereros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

			tableGenereros.getSelectionModel().selectedIndexProperty().addListener(new RowSelectChangeListener());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * metodo que permite llenar la tabla familias
	 */
	private void llenarTablaFamilias() {
		// TODO Auto-generated method stub
		try {
			ObservableList<FamiliaObservable> familias = administradorDelegado.listarFamiliasObservables();
			tableFamilias.setItems(familias);
			columnIdFamilia.setCellValueFactory(new PropertyValueFactory<FamiliaObservable, String>("idFamilia"));
			columnNombreFamilia
					.setCellValueFactory(new PropertyValueFactory<FamiliaObservable, String>("familiaNombre"));
			tableFamilias.getColumns().setAll(columnIdFamilia, columnNombreFamilia);
			tableFamilias.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

			tableFamilias.getSelectionModel().selectedIndexProperty().addListener(new RowSelectChangeListener());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class RowSelectChangeListener implements ChangeListener<Object> {

		@Override
		public void changed(ObservableValue rip, Object arg1, Object index) {
			if ((int) index != -1) {
				
				if (((ReadOnlyIntegerProperty) rip).getBean() == tableFamilias.getSelectionModel()
						.selectedIndexProperty().getBean()) {
					textFieldNombreFamilia
							.setText(tableFamilias.getSelectionModel().getSelectedItem().getFamiliaNombre());
					idFamilia = Long.valueOf(tableFamilias.getSelectionModel().getSelectedItem().getIdFamilia());
				} else if (((ReadOnlyIntegerProperty) rip).getBean() == tableGenereros.getSelectionModel()
						.selectedIndexProperty().getBean()) {
					GeneroObservable ob = tableGenereros.getSelectionModel().getSelectedItem();
					textFieldNombreGenero.setText(ob.getGeneroNombre());
					comboBox.getSelectionModel().select(obtenerObservableFamilia(ob.getFamiliaNombre()));
					idGenero = Long.valueOf(tableGenereros.getSelectionModel().getSelectedItem().getIdGenero());
				}
			}

		}

		/**
		 * metodo que devuelve un obserrvable para completar el comboBox
		 * 
		 * @param familiaNombre
		 * @return
		 */
		private FamiliaObservable obtenerObservableFamilia(String familiaNombre) {
			// TODO Auto-generated method stub
			for (FamiliaObservable familiaObservable : familias) {
				if (familiaObservable.getFamiliaNombre().equals(familiaNombre)) {
					return familiaObservable;
				}
			}
			return null;
		}
	}

}
