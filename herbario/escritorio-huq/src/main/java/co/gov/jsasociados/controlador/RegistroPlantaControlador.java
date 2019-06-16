package co.gov.jsasociados.controlador;

import java.io.File;
import java.io.IOException;

import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.modelo.AdministradorDelegado;
import co.gov.jsasociados.modelo.PlantaObservable;
import co.gov.jsasociados.util.Utilidades;
import co.gov.jsasociados.vista.AutoCompleteTextField;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.GeneroYaRegistradoExcepcion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class RegistroPlantaControlador {

	/**
	 * Contenedor de la imagen de la planta
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
	private Button btnBuscar;
	/**
	 * 
	 */
	@FXML
	private Button btnRegistrarPlanta;
	/**
	 * 
	 */
	@FXML
	private Button btnModificarPlanta;
    /**
	 * 
	 */
    @FXML
    private TextField txtNombreEspecie;
    /**
	 * 
	 */
    @FXML
//    private TextField txtGeneroEspecie;
    private AutoCompleteTextField txtGeneroEspecie;
    /**
	 * 
	 */
    @FXML
    private TextArea txtaDescripcionEspecie;
    /**
	 * 
	 */
    @FXML
//    private TextField txtBuscar;
    private AutoCompleteTextField txtBuscar;
   
    @FXML
    private TableView<PlantaObservable> tblTablaEspecies;
    
    @FXML
    private TableColumn<PlantaObservable, String> idColumna;

    @FXML
    private TableColumn<PlantaObservable, String> nombreColumna;

    @FXML
    private TableColumn<PlantaObservable, String> generoColumna;
    
    @FXML
    private TableColumn<PlantaObservable, String> familiaColumna;
    
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
		idColumna.setCellValueFactory(idCelda -> idCelda.getValue().getIdPlanta());
		nombreColumna.setCellValueFactory(nombreCelda -> nombreCelda.getValue().getNombrePlanta());
		generoColumna.setCellValueFactory(generoCelda -> generoCelda.getValue().getGeneroPlanta());
		familiaColumna.setCellValueFactory(familiaCelda -> familiaCelda.getValue().getFamiliaPlanta());
		
		tblTablaEspecies.getSelectionModel().selectedItemProperty().
		addListener((observable, oldValue, newValue) -> mostrarDetallePlanta(newValue));
	}
	
	private void mostrarDetallePlanta(PlantaObservable planta) {
		
		if(planta != null) {
			txtGeneroEspecie.setText(planta.getGeneroPlanta().getValue());
			txtNombreEspecie.setText(planta.getNombrePlanta().getValue());
			txtaDescripcionEspecie.setText(planta.getDescripcionPlanta().getValue());
			imgImagen.setImage(planta.getImagenPlanta());
		}else {
			txtGeneroEspecie.setText("");
			txtNombreEspecie.setText("");
			txtaDescripcionEspecie.setText("");
			imgImagen.setImage(null);
		}
		
	}
	
	@FXML
	void modificarEspecie() throws ElementoNoEncontradoException, ElementoRepetidoException, IOException {
		if(validarCampoBusqueda()) {
			administradorDelegado.modificarEspecie(administradorDelegado.buscarPlanta(txtNombreEspecie.getText().trim()).getIdPlanta(),
					txtNombreEspecie.getText().trim(), administradorDelegado.buscarGenero(txtGeneroEspecie.getText().trim()), 
					txtaDescripcionEspecie.getText().trim(), Utilidades.convertirImagenABytes(rutaImagen));
		}else {
			Utilidades.mostrarMensaje("Error", "Error al modificar la especie");
		}
	}
	
	@FXML
	void registrarEspecie() throws Exception {
		
		if(validarCamposRegistro()) {
			
			
								
			Planta p = new Planta();
			p.setNombre(txtNombreEspecie.getText().trim());
			p.setGenero(administradorDelegado.buscarGenero(txtGeneroEspecie.getText().trim()));
			p.setDescripcion(txtaDescripcionEspecie.getText().trim());
			p.setImagen(Utilidades.convertirImagenABytes(rutaImagen));
			
			administradorDelegado.registrarEspecie(p);
		}else {
			Utilidades.mostrarMensaje("Error", "Error al registrar la especie");
		}
		
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
			txtBuscar.getEntries().addAll(administradorDelegado.listarNombresPlanta());
			txtGeneroEspecie.getEntries().addAll(administradorDelegado.listarNombresGenero());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean validarCampoBusqueda()
	{
		if(txtBuscar.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo de busqueda", "Por favor ingrese el nombre de la especie a buscar");
			return false;
		}
		return true;
	}
	
	public boolean validarCamposRegistro()
	{		
		Familia f = administradorDelegado.buscarFamilia("Axiomadas");
		Genero g = new Genero();
		g.setGenero("Generito");
		g.setFamilia(f);		
		
		//			administradorDelegado.modificarFamilia(f.getFamilia(), f.getIdFamilia());
		try {
			Familia fa = administradorDelegado.modificarFamilia(f.getFamilia(), f.getIdFamilia());
			System.out.println(fa.getFamilia());
		} catch (ElementoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (txtGeneroEspecie.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese el genero de la especie");
			return false;
		}
		if(administradorDelegado.buscarGenero(txtGeneroEspecie.getText().trim()) == null) {
			Utilidades.mostrarMensaje("Campo invalido", "Por favor ingrese un genero valido");
			return false;
		}
		if (txtNombreEspecie.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese un nombre para la especie");
			return false;
		}
		if (txtaDescripcionEspecie.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese la descripcion de la especie");
			return false;
		}
		if (imgImagen.getImage() != null) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor elija una imagen para la especie");
			return false;
		}
		return true;
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
        	rutaImagen = imgFile.getAbsolutePath();
            Image image = new Image("file:" + imgFile.getAbsolutePath());            
            imgImagen.setImage(image);
        }
    }
}