package co.gov.jsasociados.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.gov.jsasociados.Comentario;
import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.Recolector;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;

@Remote
public interface EmpleadoEJBRemote {

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
	 * metodo que permite buscar un recolector por su cedula
	 * 
	 * @param cedula
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 */
	Recolector buscarRecolector(String cedula) throws PersonaNoRegistradaException, TipoClaseException;

	/**
	 * metodo que permite eliminar un recolector por su cedula
	 * 
	 * @param cedula
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 */
	boolean eliminarRecolector(String cedula) throws PersonaNoRegistradaException, TipoClaseException;

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
	 * metodo que permite listar recolectores
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Recolector> listarRecolectores() throws Exception;

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
	 * metodo que permite listar los nombres de una planta
	 * 
	 * @return
	 * @throws Exception
	 */
	List<String> listarNombresPlanta() throws Exception;

	/**
	 * metodo que permite listar las plantas por un filtro
	 * 
	 * @param f
	 * @param g
	 * @return
	 */
	List<Planta> listarPlantasPorFiltros(Familia f, Genero g);

	/**
	 * metodo que permite listar todas las plantas
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Planta> listarPlanta() throws Exception;

	/**
	 * metodo que permite restablecer la clave de una cuenta
	 * 
	 * @param persona
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoRepetidoException
	 */
	String restablecerClave(Persona persona) throws ElementoNoEncontradoException, ElementoRepetidoException;

	/**
	 * metodo que permite insetar un comentario
	 * 
	 * @param comentario
	 */
	void insertarComentario(Comentario comentario);

	/**
	 * metodo que permite listar los comentarios
	 * 
	 * @param p
	 * @return
	 */
	List<Comentario> listarComentarios(Planta p);
}
