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
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
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
	
	private List<Genero> generos;
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
			generos=adminEJB.listarGenero();
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
			System.out.println(generos.size()+"++++++++");
			try {
				genero=adminEJB.insertarGenero(genero);
				generos=adminEJB.listarGenero();
				System.out.println(adminEJB.buscarFamilia(genero.getFamilia().getFamilia())+"--------");
			} catch (GeneroYaRegistradoExcepcion e) {
				// TODO Auto-generated catch block
				Util.mostarMensaje("Elemento no encontrado", String.format("La familia con el nombre $ no se encuentra registrada", familia));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "/lista_generos";
		
	}
	
	/**
	 * permite eliminar un genero
	 */
	public void eliminarGenero() {
		try {
			adminEJB.eliminarGenero(genero.getIdGenero());
			generos = adminEJB.listarGenero();
			Util.mostarMensaje("Eliminación exitosa!!!", "Eliminación exitosa!!!");
		} catch (ElementoNoEncontradoException e) {
			Util.mostarMensaje(e.getMessage(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * metodo para modificar un genero
	 * 
	 * @return
	 */
	public String modificarGenero() {

		try {
			adminEJB.modificarGenero(genero.getGenero(), genero.getFamilia(), genero.getIdGenero()); 
			return "/admin/genero/generos";
		} catch (ElementoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
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

	/**
	 * @return the generos
	 */
	public List<Genero> getGeneros() {
		return generos;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
	
	
}
