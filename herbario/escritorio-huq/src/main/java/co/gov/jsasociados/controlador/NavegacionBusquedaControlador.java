package co.gov.jsasociados.controlador;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXRadioButton;

import co.gov.jsasociados.modelo.AdministradorDelegado;
import co.gov.jsasociados.modelo.PlantaObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class NavegacionBusquedaControlador {

	@FXML
	private JFXRadioButton rbFamilia;

	@FXML
	private JFXRadioButton rbGenero;

	@FXML
	private JFXRadioButton rbPersonalizado;

	@FXML
	private ToggleGroup filtro;

	@FXML
	private MenuButton mbtnFamilia;

	@FXML
	private MenuButton mbtnGenero;

	@FXML
	private ImageView imgLupa;

	@FXML
	private TextField txtBuscar;

	@FXML
	private TableView<PlantaObservable> tblTablaPlantas;

	@FXML
	private TableColumn<PlantaObservable, String> idColumna;

	@FXML
	private TableColumn<PlantaObservable, String> nombreColumna;

	@FXML
	private TableColumn<PlantaObservable, String> generoColumna;

	@FXML
	private TableColumn<PlantaObservable, String> familiaColumna;
	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;
	/**
	 * Instancia del administrador delegado
	 */
	private AdministradorDelegado administradorDelegado;

	@FXML
	void initialize() {

		idColumna.setCellValueFactory(idCelda -> idCelda.getValue().getIdPlanta());
		nombreColumna.setCellValueFactory(nombreCelda -> nombreCelda.getValue().getNombrePlanta());
		generoColumna.setCellValueFactory(generoCelda -> generoCelda.getValue().getGeneroPlanta());
		familiaColumna.setCellValueFactory(familiaCelda -> familiaCelda.getValue().getFamiliaPlanta());

		tblTablaPlantas.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetallePlanta(newValue));
	}

	/**
	 * permite obtener una instancia del escenario general
	 * 
	 * @param escenarioInicial
	 * @throws Exception
	 */
	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		administradorDelegado = AdministradorDelegado.administradorDelegado;
		this.escenarioInicial = escenarioInicial;
		try {
			tblTablaPlantas.setItems(administradorDelegado.listarPlantasObservable());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// SE LISTAN LOS NOMBRES DE LA FAMILIAS Y LAS PLANTAS EN LOS FILTROS(MENUS)
		List<String> familias = null, generos = null;
		try {
			familias = administradorDelegado.listarNombresFamilia();
			generos = administradorDelegado.listarNombresGenero();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < Math.max(familias.size(), generos.size()); i++) {
			if (i < familias.size()) {
				MenuItem f = new MenuItem(familias.get(i));
				f.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						mbtnFamilia.setText(f.getText());
						tblTablaPlantas.setItems(administradorDelegado.listarPlantasObservablePorFiltros(
								administradorDelegado.buscarFamilia(f.getText()), null));

					}
				});
				mbtnFamilia.getItems().addAll(f);
			}
			if (i < generos.size()) {
				MenuItem g = new MenuItem(generos.get(i));
				g.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						mbtnGenero.setText(g.getText());
						tblTablaPlantas.setItems(administradorDelegado.listarPlantasObservablePorFiltros(null,
								administradorDelegado.buscarGenero(g.getText())));

					}
				});
				mbtnGenero.getItems().addAll(g);
			}
		}

	}

	/**
	 * 
	 * @param planta
	 */
	@FXML
	private void mostrarDetallePlanta(PlantaObservable planta) {

//		if(planta != null) {
//			txtGeneroEspecie.setText(planta.getGeneroPlanta().getValue());
//			txtNombreEspecie.setText(planta.getNombrePlanta().getValue());
//			txtaDescripcionEspecie.setText(planta.getDescripcionPlanta().getValue());
//			imgImagen.setImage(planta.getImagenPlanta());
//		}else {
//			txtGeneroEspecie.setText("");
//			txtNombreEspecie.setText("");
//			txtaDescripcionEspecie.setText("");
//			imgImagen.setImage(null);
//		}

	}

	/**
	 * Metodo para filtrar las busquedas de acuerdo a la familia, el genero y/o algo escrito en un textField
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void buscar(MouseEvent event) throws Exception {

		ObservableList<PlantaObservable> plantas = administradorDelegado.listarPlantasObservable();

		plantas.removeIf(new Predicate<PlantaObservable>() {

			@Override
			public boolean test(PlantaObservable t) {
				
				if(rbFamilia.isSelected()) {
					//SI ESTA SELECCIONADO EL rbFamilia FILTRA DE ACUERDO A ESTE ULTIMO Y LO QUE HAYA ESCRITO EN EL txtBuscar 
					return !t.getFamiliaPlanta().getValue().toLowerCase().equals(rbFamilia.getText().toLowerCase()) &&
							!t.getNombrePlanta().getValue().toLowerCase().contains(txtBuscar.getText().toLowerCase());
				}else if(rbGenero.isSelected()) {
					//SI ESTA SELECCIONADO EL rbGenero FILTRA DE ACUERDO A ESTE ULTIMO Y LO QUE HAYA ESCRITO EN EL txtBuscar 
					return !t.getGeneroPlanta().getValue().toLowerCase().equals(rbGenero.getText().toLowerCase()) &&
							!t.getNombrePlanta().getValue().toLowerCase().contains(txtBuscar.getText().toLowerCase());
				}else {					
					//SI ESTA SELECCIONADO EL rbPersonalizado FILTRA DE ACUERDO A ESTE ULTIMO Y LO QUE HAYA ESCRITO EN EL txtBuscar 
					return !t.getNombrePlanta().getValue().toLowerCase().contains(txtBuscar.getText().toLowerCase());
				}				
				
			}

		});

		tblTablaPlantas.setItems(plantas);
	}

}