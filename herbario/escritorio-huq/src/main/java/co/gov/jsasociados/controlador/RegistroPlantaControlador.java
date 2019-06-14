package co.gov.jsasociados.controlador;

import java.io.File;
import java.io.IOException;

import co.gov.jsasociados.Planta;
import co.gov.jsasociados.modelo.AdministradorDelegado;
import co.gov.jsasociados.util.Utilidades;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class RegistroPlantaControlador {

	/**
	 * 
	 */
    @FXML
    private ImageView imgImagen;
    /**
	 * 
	 */
    @FXML
    private Button btnImagen;
    /**
	 * 
	 */
    @FXML
    private TextField txtFamiliaEspecie;
    /**
	 * 
	 */
    @FXML
    private TextField txtNombreEspecie;
    /**
	 * 
	 */
    @FXML
    private TextField txtGeneroEspecie;
    /**
	 * 
	 */
    @FXML
    private TextArea txtaDescripcionEspecie;
    /**
	 * 
	 */
    @FXML
    private TextField txtBuscar;
    /**
	 * 
	 */
    @FXML
    private Button btnBuscar;
    /**
     * 
     */
    @FXML
    private Button btnRegistrarPlanta;
    
    @FXML
    private TableColumn<?, ?> idColumna;

    @FXML
    private TableColumn<?, ?> nombreColumna;

    @FXML
    private TableColumn<?, ?> imagenColumna;
    
    
    //VARIABLES NECESARIAS
    /**
     * Ruta de la imagen de la planta
     */
    private String rutaImagen;
    /**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;
	/**
	 * Instancia del administrador delegado
	 */
	private AdministradorDelegado administradorDelegado;
	
	@FXML
	void iniatilize()
	{
		
	}
	
	@FXML
	void registrarEspecie() throws Exception {
		
		Planta p = new Planta();
		p.setNombre(txtNombreEspecie.getText());
		p.setImagen(Utilidades.convertirImagenABytes(rutaImagen));
		administradorDelegado.registrarEspecie(p);
	}
	
	/**
	 * permite obtener una instancia del escenario general
	 * 
	 * @param escenarioInicial
	 */
	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		administradorDelegado = AdministradorDelegado.administradorDelegado;
		this.escenarioInicial = escenarioInicial;
	}
    
	/**
     * Metodo para buscar una imagen
     */
    @FXML
    void buscarImagen() {
    	FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);

        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            imgImagen.setImage(image);
        }
    }
}