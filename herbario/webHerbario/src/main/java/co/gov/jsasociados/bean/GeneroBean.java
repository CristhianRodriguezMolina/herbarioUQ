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
import co.gov.jsasociados.ejb.AdminEJB;
import co.gov.jsasociados.util.Util;
import co.gov.jsasocioados.exeption.GeneroYaRegistradoExcepcion;

@FacesConfig(version=Version.JSF_2_3)
@Named("generoBean")
@ApplicationScoped
public class GeneroBean {

	/**
	 * nombre del genero
	 */
	private String generoNombre;
	/**
	 * familia del genero
	 */
	private Familia familia;
	/**
	 * familia asociada
	 */
	private Genero genero; 
	/**
	 * Instancia del AdminEJB
	 */
	/**
	 * lista de familias
	 */
	private List<Familia> listaFamilias;
	
	@EJB
	private AdminEJB adminEJB;
	
	public GeneroBean() {
		
	}
	
	/**
	 * inicializa la lista de familia
	 */
	@PostConstruct
	private void init() {
		try {
			listaFamilias=adminEJB.listarFamilias();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * metodo que permite insertar un genero desde la parte web
	 * @param familia
	 * @return
	 */
	public String registrarGenero() {
		
		Genero genero = new Genero();	
		
			Familia familiaTemp = familia;	
			genero.setFamilia(familiaTemp);
			genero.setGenero(generoNombre);
			familiaTemp.addGenero(genero);
			
			try {
				genero=adminEJB.insertarGenero(genero);
			} catch (GeneroYaRegistradoExcepcion e) {
				// TODO Auto-generated catch block
				Util.mostarMensaje("Elemento no encontrado", String.format("La familia con el nombre $ no se encuentra registrada", familia));
			}
			return "/detalle_genero";
		
	}
	/**
	 * @return the generoNombre
	 */
	public String getGeneroNombre() {
		return generoNombre;
	}
	/**
	 * @param generoNombre the generoNombre to set
	 */
	public void setGeneroNombre(String generoNombre) {
		this.generoNombre = generoNombre;
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
	 * 
	 * @return
	 */
	public List<Familia> getListaFamilias() {
		return listaFamilias;
	}
	/**
	 * 
	 * @param listaFamilias
	 */
	public void setListaFamilias(List<Familia> listaFamilias) {
		this.listaFamilias = listaFamilias;
	}
	
	
}
