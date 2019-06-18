package co.gov.jsasociados.modelo;

import co.gov.jsasociados.Administrador;
import co.gov.jsasociados.Comentario;
import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Recolector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorInput;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Permite transformar un comentario en observable
 * @author Rodriguez
 *
 */
public class ComentarioObservable {

	/**
	 * El escrito de este comentario 
	 */
	private StringProperty comentario;
	/**
	 * comentario visual para la interaz
	 */
	private Label lblComentario;
	/**
	 * Comentario
	 */
	private Comentario coment;  
	
	public ComentarioObservable(Comentario coment) {
		this.comentario = new SimpleStringProperty(coment.getComentario());
//		this.lblComentario = crearComentarioVisual(coment);
	}
	
	/**
     * Metodo que crea un comentario visual para la base de datos a partir de un comentario
     * @param coment
     */
    public Label crearComentarioVisual(Comentario coment) {
    	//Se crea el label que contendra el comentario en la base de datos
		Label comentario = new Label();
		if(coment.getPersona().getClass() == Administrador.class) {
			ColorInput color = new ColorInput();
			color.setPaint(Color.PINK);
			comentario.setEffect(color);
		}else if(coment.getPersona().getClass() == Empleado.class) {
			ColorInput color = new ColorInput();
			color.setPaint(Color.DARKTURQUOISE);
			comentario.setEffect(color);
		}else if(coment.getPersona().getClass() == Recolector.class) {
			ColorInput color = new ColorInput();
			color.setPaint(Color.GREENYELLOW);
			comentario.setEffect(color);
		}
    	comentario.setFont(new Font("Arial", 18));
    	comentario.setText(coment.getComentario());
    	
    	return comentario;
    }

	/**
	 * @return the comentario
	 */
	public StringProperty getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(StringProperty comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the coment
	 */
	public Comentario getComent() {
		return coment;
	}

	/**
	 * @param coment the coment to set
	 */
	public void setComent(Comentario coment) {
		this.coment = coment;
	}

	/**
	 * @return the lblComentario
	 */
	public Label getLblComentario() {
		return lblComentario;
	}

	/**
	 * @param lblComentario the lblComentario to set
	 */
	public void setLblComentario(Label lblComentario) {
		this.lblComentario = lblComentario;
	}
	
	
}
