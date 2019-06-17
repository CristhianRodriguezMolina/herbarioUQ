package co.gov.jsasociados.controlador;

import co.gov.jsasociados.modelo.AdministradorDelegado;
import co.gov.jsasociados.modelo.PlantaObservable;
import co.gov.jsasociados.util.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DetallePlantaControlador {

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblGenero;

    @FXML
    private Label lblFamilia;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblRecolector;

    @FXML
    private Label lblPais;

    @FXML
    private Label lblDepartamento;

    @FXML
    private Label lblMunicipio;

    @FXML
    private Label lblLugar;

    @FXML
    private Label lblFechaRecoleccion;

    @FXML
    private Label lblFechaRegistro;

    @FXML
    private ImageView imgImagen;

    @FXML
    private Button btnRegresar;

    @FXML
    private TextField txtComentario;
    
    @FXML
    private VBox vboxComentarios;

    @FXML
    private Button btnComentar;
    /**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;
	/**
	 * Instancia del administrador delegado
	 */
	private AdministradorDelegado administradorDelegado;
	/**
	 * 
	 */
	private PlantaObservable planta;
    
	@FXML
	void initialize() {
		
	}
	
	public void inicializarDatos() {
		lblNombre.setText(planta.getNombrePlanta().getValue());
		lblFamilia.setText(planta.getFamiliaPlanta().getValue());
		lblGenero.setText(planta.getGeneroPlanta().getValue());
		lblDescripcion.setText(planta.getDescripcionPlanta().getValue());
		imgImagen.setImage(planta.getImagenPlanta());
	}
	
	/**
	 * 
	 * @param escenarioInicial
	 */
    public void setEscenarioInicial(ManejadorEscenarios escenarioInicial, PlantaObservable po) {
		administradorDelegado = AdministradorDelegado.administradorDelegado;
		this.escenarioInicial = escenarioInicial;
		this.planta = po;
			
		inicializarDatos();
    }
    
    /**
     * 
     * @param event
     */
    @FXML
    void regresar(ActionEvent event) {
    	escenarioInicial.cargarEscenaNavegacion();
    }

    @FXML
    void comentar(ActionEvent event) {
    	
    	if(!txtComentario.getText().trim().equals("")) {
    		Label comentario = new Label();
        	comentario.setFont(new Font("Arial", 18));
        	comentario.setText(String.format("%1$s: %2$s", escenarioInicial.getUser().getCuenta().getUsuario(), txtComentario.getText()));
        	vboxComentarios.getChildren().add(comentario);
        	Label aux = new Label();
        	aux .setPrefHeight(10);
    	}else {
    		Utilidades.mostrarMensaje("Error", "Escriba algo");
    	}    	
    }
    
    
}

