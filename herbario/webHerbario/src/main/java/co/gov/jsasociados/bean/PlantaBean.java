package co.gov.jsasociados.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.ejb.AdminEJB;
import co.gov.jsasociados.util.Util;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;

@FacesConfig(version = Version.JSF_2_3)
@Named("plantaBean")
@ApplicationScoped
public class PlantaBean {
	
	/**
	 * planta relacionada
	 */
	private Planta planta;
	/**
	 * genero relacionado a la planta
	 */
	private Genero genero;
	/**
	 * familia relacionada a la planta
	 */
	private Familia familia;
	/**
	 * lista de plantas
	 */
	private	List<Planta> listaPlantas;
	/**
	 * Instancia del AdminEJB
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * carga la lista de familias
	 */
	@PostConstruct
	private void init() {
		try {
			listaPlantas = adminEJB.listarPlanta();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * permite obtener la planta que se desea eliminar
	 */
	public void eliminarPlanta() {
		try {
			adminEJB.elimiarEspecie(planta.getIdPlanta());
			listaPlantas = adminEJB.listarPlanta();
			Util.mostarMensaje("Eliminación exitosa!!!", "Eliminación exitosa!!!");
		} catch (ElementoNoEncontradoException e) {
			Util.mostarMensaje(e.getMessage(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * metodo para modificar una planta
	 * 
	 * @return
	 */
	public String modificarPlanta() {

		try {
			adminEJB.modificarEspecie(planta.getIdPlanta(), planta.getNombre(), planta.getGenero(), planta.getDescripcion(), planta.getImagen());
			return "/admin/planta/plantas";
		} catch (ElementoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ElementoRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
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
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	/**
	 * @return the familia
	 */
	public Familia getFamilia() {
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	/**
	 * @return the listaPlantas
	 */
	public List<Planta> getListaPlantas() {
		return listaPlantas;
	}

	/**
	 * @param listaPlantas the listaPlantas to set
	 */
	public void setListaPlantas(List<Planta> listaPlantas) {
		this.listaPlantas = listaPlantas;
	}
	
	
	

}
