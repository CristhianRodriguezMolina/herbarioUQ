package co.gov.jsasociados.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.Recolector;
import co.gov.jsasociados.Registro;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.ElementoNoExiste;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;

/**
 * permite manejar el patron layer para exponer las funciones deseadas
 */
@Remote
public interface RecolectorEJBRemote {
	/**
	 * metodo que permite insetar un recolector
	 * 
	 * @param recolector
	 * @return
	 * @throws ElementoRepetidoException
	 * @throws PersonaNoRegistradaException
	 */
	Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoException, PersonaNoRegistradaException;

	/**
	 * metodo que pemite modificar lo datos de un recolector
	 * 
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param correo
	 * @param direccion
	 * @param cedula
	 * @param usuario
	 * @param clave
	 * @return
	 * @throws Exception
	 */
	Recolector modificarRecolector(String nombre, String apellido, String telefono, String correo, String direccion,
			String cedula, String usuario, String clave) throws Exception;

	/**
	 * metodo que permite registrar una planta
	 * 
	 * @param planta
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Planta registrarPlanta(Planta planta) throws ElementoRepetidoException;

	/**
	 * metodo que permite buscar una planta pos su nombre
	 * 
	 * @param nombrePlanta
	 * @return
	 */
	Planta buscarPlanta(String nombrePlanta);

	/**
	 * metodo que permite listar las plantas aceptadas
	 * 
	 * @return
	 */
	List<Planta> listarEspeciesAceptadas();

	/**
	 * metodo que permite listar las plantas que un recolector ha enviado y que sean
	 * aceptadas
	 * 
	 * @param cedulaRecolector
	 * @return
	 */
	List<Planta> listarPlantasAceptasEnviadas(String cedulaRecolector);

	/**
	 * metodo que permite listar las plantas que un recolector ha enviado y que sean
	 * rechazada
	 * 
	 * @param cedulaRecolector
	 * @return
	 */
	List<Planta> listarPlantasRechazadasEnviadas(String cedulaRecolector);

	/**
	 * metodo que permite listar las plantas por un filtro
	 * 
	 * @param f
	 * @param g
	 * @return
	 */
	List<Planta> listarPlantasPorFiltros(Familia f, Genero g);

	/**
	 * metodo que permite restablecer la clave de una cuenta
	 * 
	 * @param persona
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoRepetidoException
	 */
	String restablecerClave(Persona persona) throws ElementoNoEncontradoException, ElementoRepetidoException;
}
