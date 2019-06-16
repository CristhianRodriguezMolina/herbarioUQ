package co.gov.jsasociados.modelo;

import java.io.IOException;

import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.util.Utilidades;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class PlantaObservable {

	private StringProperty idPlanta;
	
	private StringProperty nombrePlanta;
	
	private StringProperty generoPlanta;
	
	private StringProperty familiaPlanta;
	
	private StringProperty descripcionPlanta;
	
	private Image imagenPlanta;
	
	private Planta planta; 
	
	public PlantaObservable(Planta planta) {
		
		this.planta = planta;
		this.idPlanta = new SimpleStringProperty(planta.getIdPlanta().toString());
		this.nombrePlanta = new SimpleStringProperty(planta.getNombre());
		this.generoPlanta = new SimpleStringProperty(planta.getGenero().getGenero());
		this.familiaPlanta = new SimpleStringProperty(planta.getGenero().getFamilia().getFamilia());
		this.descripcionPlanta = new SimpleStringProperty(planta.getDescripcion());
		try {
			this.imagenPlanta = Utilidades.convertirBytesAImagen(planta.getImagen());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PlantaObservable(Long idPlanta, String nombrePlanta, Genero generoPlanta, String descripcionPlanta, byte[] imagen) {
		
		this.idPlanta = new SimpleStringProperty(idPlanta.toString());
		this.nombrePlanta = new SimpleStringProperty(nombrePlanta);
		this.generoPlanta = new SimpleStringProperty(generoPlanta.getGenero());
		this.familiaPlanta = new SimpleStringProperty(generoPlanta.getFamilia().getFamilia());
		this.descripcionPlanta = new SimpleStringProperty(descripcionPlanta);
		try {
			this.imagenPlanta = Utilidades.convertirBytesAImagen(planta.getImagen());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the idPlanta
	 */
	public StringProperty getIdPlanta() {
		return idPlanta;
	}

	/**
	 * @param idPlanta the idPlanta to set
	 */
	public void setIdPlanta(StringProperty idPlanta) {
		this.idPlanta = idPlanta;
	}

	/**
	 * @return the nombrePlanta
	 */
	public StringProperty getNombrePlanta() {
		return nombrePlanta;
	}

	/**
	 * @param nombrePlanta the nombrePlanta to set
	 */
	public void setNombrePlanta(StringProperty nombrePlanta) {
		this.nombrePlanta = nombrePlanta;
	}

	/**
	 * @return the generoPlanta
	 */
	public StringProperty getGeneroPlanta() {
		return generoPlanta;
	}

	/**
	 * @param generoPlanta the generoPlanta to set
	 */
	public void setGeneroPlanta(StringProperty generoPlanta) {
		this.generoPlanta = generoPlanta;
	}

	/**
	 * @return the planta
	 */
	public Planta getPlanta() {
		return planta;
	}

	/**
	 * @param planta the planta to set
	 */
	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	/**
	 * @return the familiaPlanta
	 */
	public StringProperty getFamiliaPlanta() {
		return familiaPlanta;
	}

	/**
	 * @param familiaPlanta the familiaPlanta to set
	 */
	public void setFamiliaPlanta(StringProperty familiaPlanta) {
		this.familiaPlanta = familiaPlanta;
	}

	/**
	 * @return the descripcionPlanta
	 */
	public StringProperty getDescripcionPlanta() {
		return descripcionPlanta;
	}

	/**
	 * @param descripcionPlanta the descripcionPlanta to set
	 */
	public void setDescripcionPlanta(StringProperty descripcionPlanta) {
		this.descripcionPlanta = descripcionPlanta;
	}

	/**
	 * @return the imagenPlanta
	 */
	public Image getImagenPlanta() {
		return imagenPlanta;
	}

	/**
	 * @param imagenPlanta the imagenPlanta to set
	 */
	public void setImagenPlanta(Image imagenPlanta) {
		this.imagenPlanta = imagenPlanta;
	}
	
	
}
