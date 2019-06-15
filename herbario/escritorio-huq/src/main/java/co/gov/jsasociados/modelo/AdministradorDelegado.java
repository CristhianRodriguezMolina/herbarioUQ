/**
 * 
 */
package co.gov.jsasociados.modelo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.Recolector;
import co.gov.jsasociados.ejb.AdminEJBRemote;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.FamiliaYaRegistradaExeption;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Delegado que permite conectar la capa de negocio con la de presentación
 * 
 * @author EinerZG
 * @version 1.0
 */
public class AdministradorDelegado {

	/**
	 * instancia que representa el ejb remoto de administrador
	 */
	private AdminEJBRemote adminEJB;
	/**
	 * permite manejar una unica instancia para le manejo de delegados
	 */
	public static AdministradorDelegado administradorDelegado = instancia();

	/**
	 * constructor para conectar con la capa de negocio
	 */
	private AdministradorDelegado() {
		try {
			adminEJB = (AdminEJBRemote) new InitialContext().lookup(AdminEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static AdministradorDelegado instancia() {

		if (administradorDelegado == null) {
			administradorDelegado = new AdministradorDelegado();
			return administradorDelegado;
		}
		return administradorDelegado;
	}

	/**
	 * metodo que permite registrar un recolector
	 * 
	 * @param recolector
	 * @return
	 * @throws ElementoRepetidoException
	 * @throws PersonaNoRegistradaException
	 */
	public Recolector insertarRecolector(Recolector recolector)
			throws ElementoRepetidoException, PersonaNoRegistradaException {
		return adminEJB.insertarRecolector(recolector);
	}

	/**
	 * metodo que permite eliminar un recolector
	 * 
	 * @param cedula
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 */
	public boolean eliminarRecolector(String cedula) throws PersonaNoRegistradaException, TipoClaseException {
		return adminEJB.eliminarRecolector(cedula);
	}

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
	public Empleado modificarEmpleado(String nombre, String apellido, String telefono, String correo, String direccion,
			String cedula) throws PersonaNoRegistradaException, TipoClaseException {
		return adminEJB.modificarEmpleado(nombre, apellido, telefono, correo, direccion, cedula);
	}

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
	public Recolector modificarRecolector(String nombre, String apellido, String telefono, String correo,
			String direccion, String cedula) throws PersonaNoRegistradaException, TipoClaseException {
		return adminEJB.modificarRecolector(nombre, apellido, telefono, correo, direccion, cedula);
	}

	/**
	 * metodo que permite listar a todos los recolecotores
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Recolector> listarRecolectores() throws Exception {
		return adminEJB.listarRecolectores();
	}

	/**
	 * metodo que permite buscar un empleado por su cedula
	 * 
	 * @param cedula
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 */
	public Empleado buscarEmpleado(String cedula) throws PersonaNoRegistradaException, TipoClaseException {
		return adminEJB.buscarEmpleado(cedula);
	}

	/**
	 * metodo que permite buscar un recolector por su cedula
	 * 
	 * @param cedula
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 */
	public Recolector buscarRecolector(String cedula) throws PersonaNoRegistradaException, TipoClaseException {
		return adminEJB.buscarRecolector(cedula);
	}

	/**
	 * metodo que permite agregar una familia desde el perfil de administrador
	 * 
	 * @param familia
	 * @return
	 * @throws FamiliaYaRegistradaExeption
	 */
	public Familia insertarFamilia(Familia familia) throws FamiliaYaRegistradaExeption {
		return adminEJB.insertarFamilia(familia);
	}

	/**
	 * pemite registar un nuevo empleado
	 * 
	 * @param empleado empleado a agregar
	 * @return devuelve el empleado
	 * @throws PersonaNoRegistradaException
	 * @throws ElementoRepetidoException
	 */
	public Empleado registrarEmpleado(Empleado empleado)
			throws ElementoRepetidoException, PersonaNoRegistradaException {
		return adminEJB.insertarEmpleado(empleado);
	}

	/**
	 * devuvel la lista de empleado que estan en la base de datos
	 * 
	 * @return todos los empleados
	 * @throws Exception
	 */
	public List<Empleado> listarEmpleado() throws Exception {
		return adminEJB.listarEmpleados();
	}

	/**
	 * permite eliminar el empleado por cedula
	 * 
	 * @return empleado si fue eliminado
	 * @throws TipoClaseException
	 * @throws PersonaNoRegistradaException
	 */
	public boolean eliminarEmpleado(Empleado empleado) throws PersonaNoRegistradaException, TipoClaseException {
		return adminEJB.eliminarEmpleado(empleado.getCedula());
	}

	/**
	 * metodo que permite buscar una familia por su nombre
	 * 
	 * @param famila - nombre de la familia
	 * @return
	 */
	public Familia buscarFamilia(String famila) {
		return adminEJB.buscarFamilia(famila);
	}

	/**
	 * metodo que permite eliminar una familia
	 * 
	 * @param idFamilia
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public boolean eliminarFamilia(Long idFamilia) throws ElementoNoEncontradoException {
		return adminEJB.eliminarFamilia(idFamilia);
	}

	/**
	 * metodo que permite modificar el nombre de una familia
	 * 
	 * @param nombre    - nombre de la familia
	 * @param idFamilia
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public Familia modificarFamilia(String nombre, Long idFamilia) throws ElementoNoEncontradoException {
		return adminEJB.modificarFamilia(nombre, idFamilia);
	}

	/**
	 * metodo que obtiene la lista de las familias registradas
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Familia> listarFamilias() throws Exception {
		return adminEJB.listarFamilias();
	}

	/**
	 * metodo que permite buscar un genero por su nombre
	 * 
	 * @param genero
	 * @return
	 */
	public Genero buscarGenero(String genero) {
		return adminEJB.buscarGenero(genero);
	}

	/**
	 * metodo que permite agregar un genero
	 * 
	 * @param genero
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Genero registrarGenero(Genero genero) throws ElementoRepetidoException {
		return adminEJB.registrarGenero(genero);
	}

	/**
	 * metodo que permite eliminar un genero
	 * 
	 * @param idGenero
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public boolean elimiarGenero(Long idGenero) throws ElementoNoEncontradoException {
		return adminEJB.elimiarGenero(idGenero);
	}

	/**
	 * metodo que permite modificar el nombre de un genero
	 * 
	 * @param genero   - nombre del genero
	 * @param idGenero
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public Genero modificarGenero(String genero, Long idGenero) throws ElementoNoEncontradoException {
		return adminEJB.modificarGenero(genero, idGenero);
	}
	
	/**
	 * metodo que permite agregar una especie
	 * 
	 * @param planta
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Planta registrarEspecie(Planta planta) throws ElementoRepetidoException {
		return adminEJB.registrarEspecie(planta);
	}

	/**
	 * metodo que permite eliminar un genero
	 * 
	 * @param idPlanta
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public boolean elimiarEspecie(Long idPlanta) throws ElementoNoEncontradoException {
		return adminEJB.elimiarEspecie(idPlanta);
	}

	/**
	 * metodo que permite modificar el nombre de un genero
	 * 
	 * @param nombrePlanta   - nombre del genero
	 * @param idPlanta
	 * @return
	 * @throws ElementoNoEncontradoException
	 */
	public Planta modificarEspecie(String nombrePlanta, Long idPlanta) throws ElementoNoEncontradoException {
		return adminEJB.modificarEspecie(nombrePlanta, idPlanta);
	}

	/**
	 * metodo que lista todos los generos
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Genero> listarGenero() throws Exception {
		return adminEJB.listarGenero();
	}
	
	/**
	 * Metodo para listar los nombres de las plantas
	 * @return
	 * @throws Exception
	 */
	public List<String> listarNombresPlanta() throws Exception{
		return adminEJB.listarNombresPlanta();
	}

	/**
	 * Metodo para listar los nombres de los generos
	 * @return
	 * @throws Exception
	 */
	public List<String> listarNombresGenero() throws Exception{
		return adminEJB.listarNombresGenero();
	}
	
	/**
	 * genera una lista de empleados observables
	 * 
	 * @return todos los empleados obsevables
	 * @throws Exception
	 */
	public ObservableList<PersonaObservable> listarEmpleadosObservables() throws Exception {
		List<Empleado> empleados = listarEmpleado();
		ObservableList<PersonaObservable> empleadosObservables = FXCollections.observableArrayList();
		for (Persona persona : empleados) {
			empleadosObservables.add(new PersonaObservable(persona));
		}
		return empleadosObservables;
	}
	
	/**
	 * genera una lista de recolectores observables
	 * 
	 * @return todos los recolectores obsevables
	 * @throws Exception
	 */
	public ObservableList<PersonaObservable> listarRecolectoresObservables() throws Exception {
		List<Recolector> recolectores = listarRecolectores();
		ObservableList<PersonaObservable> recolectoresObservables = FXCollections.observableArrayList();
		for (Persona persona : recolectores) {
			recolectoresObservables.add(new PersonaObservable(persona));
		}
		return recolectoresObservables;
	}
}
