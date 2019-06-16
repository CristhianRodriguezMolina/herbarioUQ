package co.gov.jsasociados.controlador;

import java.util.List;

import co.gov.jsasociados.modelo.AdministradorDelegado;
import co.gov.jsasociados.modelo.PlantaObservable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class NavegacionBusquedaControlador {

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
		
		tblTablaPlantas.getSelectionModel().selectedItemProperty().
		addListener((observable, oldValue, newValue) -> mostrarDetallePlanta(newValue));
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

		//SE ISTAN LOS NOMBRES DE LA FAMILIAS Y LAS PLANTAS EN LOS FILTROS(MENUS)
		List<String> familias = null, generos = null;
		try {
			familias = administradorDelegado.listarNombresFamilia();
			generos = administradorDelegado.listarNombresGenero();
		} catch (Exception e) {
			e.printStackTrace();
		}
						
		for(int i=0; i<Math.max(familias.size(), generos.size()); i++) {
			if(i<familias.size()) {
				MenuItem f = new MenuItem(familias.get(i));
				f.setOnAction(new EventHandler<ActionEvent>() {					
					@Override
					public void handle(ActionEvent event) {

						System.out.println("Si funciono xd");
						
					}
				});
				mbtnFamilia.getItems().addAll(f);
			}
			if(i<generos.size()) {
				MenuItem g = new MenuItem(generos.get(i));
				g.setOnAction(new EventHandler<ActionEvent>() {					
					@Override
					public void handle(ActionEvent event) {

						System.out.println("Si funciono xd");
						
					}
				});
				mbtnGenero.getItems().addAll(g);
			}
		}		
		
	}
    
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
    
    @FXML
    void buscar(MouseEvent event) {
    	
    }

}