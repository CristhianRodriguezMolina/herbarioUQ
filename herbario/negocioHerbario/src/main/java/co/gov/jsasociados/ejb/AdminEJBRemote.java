package co.gov.jsasociados.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.gov.jsasociados.Comentario;
import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.Recolector;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.FamiliaYaRegistradaExeption;
import co.gov.jsasocioados.exeption.GeneroYaRegistradoExcepcion;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;

/**
 * permite manejar el patron layer para exponer las funciones deseadas
 */
@Remote
public interface AdminEJBRemote {

	String JNDI = "java:global/ear-herbario/negocioHerbario/AdminEJB!co.gov.jsasociados.ejb.AdminEJBRemote";

	/**
	 * 
	 * @param user
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 */
	Persona buscarPersona(String user) throws PersonaNoRegistradaException;
	
	/**
	 * metodo que permiete agregar un empleado
	 * 
	 * @param empleado - empleado a registrar
	 * @return
	 * @throws ElementoRepetidoException
	 * @throws PersonaNoRegistradaException
	 */
	Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoException, PersonaNoRegistradaException;

	/**
	 * metodo que permite registrar un recolector
	 * 
	 * @param recolector
	 * @return
	 * @throws ElementoRepetidoException
	 * @throws PersonaNoRegistradaException
	 */
	Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoException, PersonaNoRegistradaException;

	/**
	 * metodo que permite elinar un empleado
	 * 
	 * @param cedula - cedula del empleado
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 */
	boolean eliminarEmpleado(String cedula) throws PersonaNoRegistradaException, TipoClaseException;

	/**
	 * metodo que permite eliminar un recolector
	 * 
	 * @param cedula
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 */
	boolean eliminarRecolector(String cedula) throws PersonaNoRegistradaException, TipoClaseException;

	/**
	 * metodo que permite modificar la informacion de un empleado
	 * 
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param correo
	 * @param direccion
	 * @param cedula    - cedula del empleado a modificar sus datos
	 * @param usuario
	 * @param clave
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 * @throws ElementoRepetidoException
	 * @throws ElementoNoEncontradoException
	 * @throws Exception
	 */
	Empleado modificarEmpleado(String nombre, String apellido, String telefono, String correo, String direccion,
			String cedula, String usuario, String clave) throws PersonaNoRegistradaException, TipoClaseException,
			ElementoNoEncontradoException, ElementoRepetidoException, Exception;

	/**
	 * 
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param correo
	 * @param direccion
	 * @param cedula    - cedula del recolector
	 * @param usuario
	 * @param clave
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 * @throws ElementoRepetidoException
	 * @throws ElementoNoEncontradoException
	 * @throws Exception
	 */
	Recolector modificarRecolector(String nombre, String apellido, String telefono, String correo, String direccion,
			String cedula, String usuario, String clave) throws PersonaNoRegistradaException, TipoClaseException,
			ElementoNoEncontradoException, ElementoRepetidoException, Exception;

	/**
	 * metodo que permite listar a todos los empleados
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Empleado> listarEmpleados() throws Exception;

	/**
	 * metodo que permite listar a todos los recolecotores
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Recolector> listarRecolectores() throws Exception;

	/**
	 * metodo que permite buscar un empleado por su cedula
	 * 
	 * @param cedula
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 */
	Empleado buscarEmpleado(String cedula) throws PersonaNoRegistradaException, TipoClaseException;

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
	 * metodo que permite agregar una familia desde el perfil de administrador
	 * 
	 * @param familia
	 * @return
	 * @throws FamiliaYaRegistradaExeption
	 * @throws ElementoNoEncontradoException 
	 */
	Familia insertarFamilia(Familia familia) throws FamiliaYaRegistradaExeption;

	/**
	 * metodo que permite buscar una familia por su nombre
	 * 
	 * @param famila - nombre de la familia
	 * @return
	 */
	Familia buscarFamilia(String famila);

	/**
	 * metodo que permite eliminar una familia
	 * 
	 * @param idFamilia
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	boolean eliminarFamilia(Long idFamilia) throws ElementoNoEncontradoException;

	/**
	 * metodo que permite modificar el nombre de una familia
	 * 
	 * @param nombre    - nombre de la familia
	 * @param idFamilia
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	Familia modificarFamilia(String nombre, Long idFamilia) throws ElementoNoEncontradoException;

	/**
	 * metodo que obtiene la lista de las familias registradas
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Familia> listarFamilias() throws Exception;

	/**
	 * metodo que permite buscar un genero por su nombre
	 * 
	 * @param genero
	 * @return
	 */
	Genero buscarGenero(String genero);

	/**
	 * metodo que pemite registrar un genero
	 * 
	 * @param genero
	 * @return
	 * @throws ElementoRepetidoException
	 * @throws GeneroYaRegistradoExcepcion 
	 */
	Genero insertarGenero(Genero genero) throws  GeneroYaRegistradoExcepcion;

	/**
	 * metodo que permite eliminar un genero
	 * 
	 * @param idGenero
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	boolean elimiarGenero(Long idGenero) throws ElementoNoEncontradoException;

	/**
	 * metodo que permite modificar el nombre de un genero
	 * 
	 * @param genero   - nombre del genero
	 * @param idGenero
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	Genero modificarGenero(String genero, Long idGenero) throws ElementoNoEncontradoException;

	/**
	 * metodo que lista todos los generos
	 * 
	 * @return
	 * @throws Exception
	 */

	 List<Genero> listarGenero() throws Exception;
	 
	 /**
	  * metodo que permite registrar una especie
	  * @param planta
	  * @return
	  * @throws ElementoRepetidoException
	  */
	 Planta registrarPlanta(Planta planta) throws ElementoRepetidoException;
	 
	 /**
	  * metodo para eliminar una especie
	  * @param idPlanta
	  * @return
	  * @throws ElementoNoEncontradoException
	  */
	 boolean elimiarEspecie(Long idPlanta) throws ElementoNoEncontradoException;
	 
	 /**
	  * Metodo para modificar una especie
	  * @param idPlanta
	  * @param nombrePlanta
	  * @param genero
	  * @param descripcion
	  * @param imagen
	  * @return
	  * @throws ElementoNoEncontradoException
	 * @throws ElementoRepetidoException 
	  */
	 Planta modificarEspecie(Long idPlanta, String nombrePlanta, Genero genero, String descripcion, byte[] imagen) throws ElementoNoEncontradoException, ElementoRepetidoException;
	 
	 /**
	  * metodo para listar las plantas registradas
	  * @return
	  * @throws Exception
	  */
	 List<Planta> listarPlanta() throws Exception;
	 
	 /**
	  * Metodo para buscar una planta
	  * @param nombrePlanta
	  * @return
	  */
	 Planta buscarPlanta(String nombrePlanta);
	 
	 /**
	  * Metodo para listar los nombres de las plantas
	  * @return
	  * @throws Exception
	  */
	 List<String> listarNombresPlanta() throws Exception;
	 
	 /**
	  * Metodo para listar los nombres de los generos
	  * @return
	  * @throws Exception
	  */
	 List<String> listarNombresGenero() throws Exception;
	 
	 /**
	  * Metodo para listar los nombres de las familias
	  * @return
	  * @throws Exception
	  */
	 List<String> listarNombresFamilia() throws Exception;
	 
	 /**
	  * Metodo para listar plantas por filtros
	  * @return
	  */
	 List<Planta> listarPlantasPorFiltros(Familia f, Genero g);
	 
	 /**
	  * Metodo para listar los comentarios dada un planta
	  * @param p
	  * @return
	  */
	 List<Comentario> listarComentarios(Planta p);
	 
	 /**
	  * metodo para insertar un comentario en la base de datos
	  * @param comentario
	  */
	 void insertarComentario(Comentario comentario);

	 /** metodo que permite reestablecer la contraseña de una cuenta
	  * @param persona
	  * @return
	  * @throws ElementoNoEncontradoException
	  * @throws ElementoRepetidoException
	  */
	 String reestablecerContraseña(Persona persona) throws ElementoNoEncontradoException, ElementoRepetidoException;
}
