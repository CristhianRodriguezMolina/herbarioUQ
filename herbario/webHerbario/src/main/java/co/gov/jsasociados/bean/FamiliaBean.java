package co.gov.jsasociados.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.gov.jsasociados.Familia;
import co.gov.jsasociados.ejb.AdminEJB;
import co.gov.jsasocioados.exeption.FamiliaYaRegistradaExeption;
import co.gov.jsasociados.util.Util;

@FacesConfig(version=Version.JSF_2_3)
@Named("familiaBean")
@ApplicationScoped
public class FamiliaBean {
	/**
	 * nombre de la familia
	 */
	private String familia;
	/**
	 * familia asociada
	 */
	private Familia fa; 
	/**
	 * listado total de familias de plantas
	 */
	private List<Familia> familias;
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
			familias = adminEJB.listarFamilias();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * metodo que permite insertar una familia desde la parte web
	 * @param familia
	 * @return
	 */
	public String registrarFamilia() {
		System.out.println("asdad");
		Familia familia = new Familia();
		familia.setFamilia(this.familia);
		try {
			fa=adminEJB.insertarFamilia(familia);
			return "/index";
		} catch (FamiliaYaRegistradaExeption e) {
			Util.mostarMensaje(e.getMessage(), e.getMessage());
			return null;
		}
	}
	
	/**
	 * permite obtener la familia que se desea eliminar
	 */
	public void eliminarFamilia() {
		try {
			adminEJB.eliminarFamilia(fa.getIdFamilia());
			familias = adminEJB.listarFamilias();
			Util.mostarMensaje("Eliminación exitosa!!!", "Eliminación exitosa!!!");
		} catch (ElementoNoEncontradoException e) {
			Util.mostarMensaje(e.getMessage(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the familia
	 */
	public String getFamilia() {
		return familia;
	}
	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(String familia) {
		this.familia = familia;
	}

	/**
	 * @return the fa
	 */
	public Familia getFa() {
		return fa;
	}

	/**
	 * @param fa the fa to set
	 */
	public void setFa(Familia fa) {
		this.fa = fa;
	}
	
}
