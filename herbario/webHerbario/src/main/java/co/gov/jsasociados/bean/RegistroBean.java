package co.gov.jsasociados.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.Registro;
import co.gov.jsasociados.ejb.AdminEJB;
import co.gov.jsasociados.util.Util;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;


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
	 * lista de plantas
	 */
	private	List<Planta> listaPlantas;
	/**
	 * registro actual
	 */
	private Registro registro;
	/**
	 * Imagen de la planta
	 */
	private UploadedFile imagen;
	/**
	 * para la carga de la imagen
	 */
	private StreamedContent chart;
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
		try {
			listaPlantas = adminEJB.listarPlanta();
			listaGeneros = adminEJB.listarGenero();		
			listaRegistrosTotales = adminEJB.listarRegistrosTotales();
			listaRegistros = adminEJB.listarRegistros(usuario.getCedula());
			listaRegistrosAceptados = adminEJB.listarRegistrosAceptados(usuario.getCedula());
			listaRegistrosRechazados = adminEJB.listarRegistrosRechazados(usuario.getCedula());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			
//			byte[] imagenBytes = new byte[(int) imagen.getSize()];
//			imagenBytes = imagen.getContents();
//			planta.setImagen(imagenBytes);
			
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
     * Metodo para conseguir la imagen que se seleccione
     * @param event
     * @throws IOException
     */
    public void handleFileUpload(FileUploadEvent event) throws IOException {
    	        
        imagen = event.getFile();
        
        byte[] imagenBytes = new byte[(int) imagen.getSize()];
		imagenBytes = imagen.getContents();
		
		if(planta == null) {
			planta = new Planta();
		}
		
		planta.setImagen(imagenBytes);
        
//      byte[] foto = IOUtils.toByteArray(file.getInputstream());
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

	/**
	 * @return the imagen
	 */
	public UploadedFile getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(UploadedFile imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the chart
	 */
	public StreamedContent getChart() {
		if(planta != null) {
			chart = new DefaultStreamedContent(new ByteArrayInputStream(planta.getImagen()), "image/png");
		}		
		return chart;
	}

	/**
	 * @param chart the chart to set
	 */
	public void setChart(StreamedContent chart) {
		this.chart = chart;
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
