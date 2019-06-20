package co.gov.jsasociados.controlador;

import javafx.scene.paint.Color;

import java.text.SimpleDateFormat;
import java.util.Date;

import co.gov.jsasociados.Administrador;
import co.gov.jsasociados.Comentario;
import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Recolector;
import co.gov.jsasociados.modelo.AdministradorDelegado;
import co.gov.jsasociados.modelo.ComentarioObservable;
import co.gov.jsasociados.modelo.PlantaObservable;
import co.gov.jsasociados.util.Utilidades;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Callback;

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
    
    @FXML
    private TableView<ComentarioObservable> tblTablaComentarios;

    @FXML
    private TableColumn<ComentarioObservable, String> comentariosColumna;
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
		
		comentariosColumna.setCellValueFactory(comentarioCelda -> comentarioCelda.getValue().getComentario());
						
	}
	
	public void inicializarDatos() {
		lblNombre.setText(planta.getNombrePlanta().getValue());
		lblFamilia.setText(planta.getFamiliaPlanta().getValue());
		lblGenero.setText(planta.getGeneroPlanta().getValue());
		lblDescripcion.setText(planta.getDescripcionPlanta().getValue());
		imgImagen.setImage(planta.getImagenPlanta());
	}
	
	/**
	 * Obtiene todos los comentarios relacionados a planta de muestra y los agrega a la lista de comentarios en  la interfaz 
	 */
	public void inicializarComentarios() {
		try {
			ObservableList<ComentarioObservable> comentarios = administradorDelegado.listarComentariosObservables(planta.getPlanta());
			
			tblTablaComentarios.setItems(comentarios);
						
			
//			for (ComentarioObservable coment : comentarios) {
//				vboxComentarios.getChildren().add(coment.getLblComentario()); //SE AGREGA AL VBOX QUE CONTIENE LOS COMENTARIOS EN LA INTERFAZ GRAFICA
//	    		Label aux = new Label(); //SE CREA UN LABEL PARA SEPARAR LOS COMENTARIOS
//	        	aux .setPrefHeight(10);
//	        	vboxComentarios.getChildren().add(aux);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		inicializarComentarios();
    }
    
    /**
     * 
     * @param event
     */
    @FXML
    void regresar(ActionEvent event) {
    	escenarioInicial.cargarEscenaNavegacion();
    }

    /**
     * Metodo para hacer un comentario y guardarlo en la base de datos
     * @param event
     */
    @FXML
    void comentar(ActionEvent event) {
    	
    	if(!txtComentario.getText().trim().equals("")) {
    		
    		//ES ESTA PARTE SE CREA EL COMENTARIO Y se añade a una persona(User) y a una planta
    		Comentario coment = new Comentario();
    		coment.setFechaPublicacion(new Date());
    		coment.setPersona(escenarioInicial.getUser());
    		coment.setComentario(String.format("(%4$s) %1$s: %2$s -%3$s", coment.getPersona().getCuenta().getUsuario(), 
    				txtComentario.getText(), new SimpleDateFormat("dd/MM/yyyy").format(coment.getFechaPublicacion()), coment.getPersona().getClass().getSimpleName()));    		
    		coment.setPlanta(planta.getPlanta());
    		
    		administradorDelegado.insertarComentario(coment);
    		
    		tblTablaComentarios.getItems().add(new ComentarioObservable(coment));
    		tblTablaComentarios.refresh();
//    		ComentarioObservable comentarioObservable = new ComentarioObservable(coment);    		
//    		vboxComentarios.getChildren().add(comentarioObservable.getLblComentario()); //SE AGREGA AL VBOX QUE CONTIENE LOS COMENTARIOS EN LA INTERFAZ GRAFICA
//    		Label aux = new Label(); //SE CREA UN LABEL PARA SEPARAR LOS COMENTARIOS
//        	aux .setPrefHeight(10);
//        	vboxComentarios.getChildren().add(aux);
    	}else {
    		Utilidades.mostrarMensaje("Error", "Escriba algo");
    	}    	
    }    
    
}

