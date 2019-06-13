package co.gov.jsasociados.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Recolector;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.FamiliaYaRegistradaExeption;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;

/**
 * permite manejar el patron layer para exponer las funciones deseadas
 */
@Remote
public interface AdminEJBRemote {
					
	public static final String JNDI = "java:global/ear-herbario/negocioHerbario/AdminEJB!co.gov.jsasociados.ejb.AdminEJBRemote";
	
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
	 * metodo que permite modificar la informacion basica de un empleado
	 * 
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param correo
	 * @param direccion
	 * @param cedula    - cedula del empleado a modificar sus datos
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 */
	Empleado modificarEmpleado(String nombre, String apellido, String telefono, String correo, String direccion,
			String cedula) throws PersonaNoRegistradaException, TipoClaseException;

	/**
	 * metodo que permite modificar la informacion de un recolector
	 * 
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param correo
	 * @param direccion
	 * @param cedula
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 */
	Recolector modificarRecolector(String nombre, String apellido, String telefono, String correo, String direccion,
			String cedula) throws PersonaNoRegistradaException, TipoClaseException;

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
	 * @param familia
	 * @return
	 * @throws FamiliaYaRegistradaExeption
	 */
	Familia insertarFamilia(Familia familia) throws FamiliaYaRegistradaExeption;
}
