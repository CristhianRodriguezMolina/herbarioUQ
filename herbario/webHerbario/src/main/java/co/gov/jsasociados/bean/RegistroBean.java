package co.gov.jsasociados.bean;

import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.swing.text.SimpleAttributeSet;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.Registro;
import co.gov.jsasociados.ejb.AdminEJB;
import co.gov.jsasociados.util.Util;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import javafx.stage.FileChooser;

@FacesConfig(version = Version.JSF_2_3)
@Named("registroBean")
@ApplicationScoped
public class RegistroBean {

	/**
	 * usuario actual
	 */
	private Persona usuario;
	/**
	 * lista de registros totales
	 */
	private List<Registro> listaRegistrosTotales;
	/**
	 * lista de registros
	 */
	private List<Registro> listaRegistros;
	/**
	 * lista de registros aceptados
	 */
	private List<Registro> listaRegistrosAceptados;
	/**
	 * lista de registros rechazados
	 */
	private List<Registro> listaRegistrosRechazados;
	/**
	 * registro actual
	 */
	private Registro registro;
	/**
	 * Imagen de la planta
	 */
	private String imagen;
	/**
	 * ruta de la imagen
	 */
	private String rutaImagen;
	/**
	 * nombre del genero
	 */
	private String nombrePlanta;
	/**
	 * Planta relacionada al bean
	 */
	private Planta planta;
	/**
	 * familia asociada
	 */
	private Genero genero;
	/**
	 * descrpcion de la planta
	 */
	private String descripcion;
	/**
	 * lista de generos
	 */
	private List<Genero> listaGeneros;
	/**
	 * fecha de recoleccion de una planta
	 */
	private Date fechaRecoleccion;
	/**
	 * fecha de registro de un aplanta
	 */
	private Date fechaRegistro;
	/**
	 * pais de recoleccion de una planta
	 */
	private String pais;
	/**
	 * departamento de recoleccion de una planta
	 */
	private String departamento;
	/**
	 * municiopio de recoleccion de una planta
	 */
	private String municipio;
	/**
	 * lugar de recoleccion de una planta
	 */
	private String lugar;
	/**
	 * Instancia del AdminEJB
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * inicializa la lista de generos
	 */
	@PostConstruct
	private void init() {
//		try {
//			listaGeneros = adminEJB.listarGenero();		
//			listaRegistrosTotales = adminEJB.listarRegistrosTotales();
//			listaRegistros = adminEJB.listarRegistros(usuario.getCedula());
//			listaRegistrosAceptados = adminEJB.listarRegistrosAceptados(usuario.getCedula());
//			listaRegistrosRechazados = adminEJB.listarRegistrosRechazados(usuario.getCedula());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * metodo que permite insertar una planta desde la parte web
	 * 
	 * @param familia
	 * @return
	 * @throws Exception 
	 */
	public String registrarPlanta() {
		
		Planta planta = new Planta();
		Registro registro = new Registro();

		try {
			Genero generoTemp = genero;
			planta.setGenero(generoTemp);
			planta.setNombre(nombrePlanta);
			planta.setDescripcion(descripcion);
			//planta.setImagen(Util.convertirImagenABytes(rutaImagen));
			generoTemp.addPlanta(planta);
			
			planta = adminEJB.registrarPlanta(planta);
			
			planta.setRegistro(registro);
			registro.setPlanta(planta);
			registro.setPersona(usuario);
			registro.setPais(pais);
			registro.setDepartamento(departamento);
			registro.setMunicipio(municipio);
			registro.setLugar(lugar);
			registro.setFechaRecoleccion(fechaRecoleccion);
			registro.setFechaRegistro(new Date());
			registro.setAprovacion(0);
			
			adminEJB.insertarRegistro(registro);
			
			init();
		} catch (ElementoRepetidoException e) {
			// TODO Auto-generated catch block
			Util.mostarMensaje("Elemento repetido", e.getMessage());
				//	String.format("La planta con el nombre %s ya se encuentra registrada", nombrePlanta));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/registrar_plantas";

	}
	
	/**
	 * metodo para reiniciar el bean de registro
	 */
	public void reiniciar() {
		init();
	}
	
	/**
	 * metodo para aceptar un registro
	 * @return
	 */
	public String aceptarRegistro() {
		try {
			adminEJB.modificarRegistro(1, registro.getNumeroRegistro());
			init();
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
		}
		
		return "/admin/registro/gestionar_registros";
	}
	
	/**
	 * metodo para rechazar un registro
	 * @return
	 */
	public String rechazarRegistro() {
		try {
			adminEJB.modificarRegistro(-1, registro.getNumeroRegistro());
			init();
		} catch (ElementoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/admin/registro/gestionar_registros";
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
 
    public void click() {
        PrimeFaces.current().ajax().update("form:display");
        PrimeFaces.current().executeScript("PF('dlg').show()");
    }
	
	/**
	 * @return the usuario
	 */
	public Persona getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 * @throws Exception 
	 */
	public void setUsuario(Persona usuario) throws Exception {
		this.usuario = usuario;		
	}

	/**
	 * @return the nombrePlanta
	 */
	public String getNombrePlanta() {
		return nombrePlanta;
	}

	/**
	 * @param nombrePlanta the nombrePlanta to set
	 */
	public void setNombrePlanta(String nombrePlanta) {
		this.nombrePlanta = nombrePlanta;
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
	 * @return the listaGeneros
	 */
	public List<Genero> getListaGeneros() {
		return listaGeneros;
	}

	/**
	 * @param listaGeneros the listaGeneros to set
	 */
	public void setListaGeneros(List<Genero> listaGeneros) {
		this.listaGeneros = listaGeneros;
	}

	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the fechaRecoleccion
	 */
	public Date getFechaRecoleccion() {
		return fechaRecoleccion;
	}

	/**
	 * @param fechaRecoleccion the fechaRecoleccion to set
	 */
	public void setFechaRecoleccion(Date fechaRecoleccion) {
		this.fechaRecoleccion = fechaRecoleccion;
	}

	/**
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the lugar
	 */
	public String getLugar() {
		return lugar;
	}

	/**
	 * @param lugar the lugar to set
	 */
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	/**
	 * @return the rutaImagen
	 */
	public String getRutaImagen() {
		return rutaImagen;
	}

	/**
	 * @param rutaImagen the rutaImagen to set
	 */
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the listaRegistros
	 */
	public List<Registro> getListaRegistros() {
		return listaRegistros;
	}

	/**
	 * @param listaRegistros the listaRegistros to set
	 */
	public void setListaRegistros(List<Registro> listaRegistros) {
		this.listaRegistros = listaRegistros;
	}

	/**
	 * @return the registro
	 */
	public Registro getRegistro() {
		return registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	/**
	 * @return the listaRegistrosAceptados
	 */
	public List<Registro> getListaRegistrosAceptados() {
		return listaRegistrosAceptados;
	}

	/**
	 * @param listaRegistrosAceptados the listaRegistrosAceptados to set
	 */
	public void setListaRegistrosAceptados(List<Registro> listaRegistrosAceptados) {
		this.listaRegistrosAceptados = listaRegistrosAceptados;
	}

	/**
	 * @return the listaRegistrosRechazados
	 */
	public List<Registro> getListaRegistrosRechazados() {
		return listaRegistrosRechazados;
	}

	/**
	 * @param listaRegistrosRechazados the listaRegistrosRechazados to set
	 */
	public void setListaRegistrosRechazados(List<Registro> listaRegistrosRechazados) {
		this.listaRegistrosRechazados = listaRegistrosRechazados;
	}

	/**
	 * @return the listaRegistrosTotales
	 */
	public List<Registro> getListaRegistrosTotales() {
		return listaRegistrosTotales;
	}

	/**
	 * @param listaRegistrosTotales the listaRegistrosTotales to set
	 */
	public void setListaRegistrosTotales(List<Registro> listaRegistrosTotales) {
		this.listaRegistrosTotales = listaRegistrosTotales;
	}
	
	

}
