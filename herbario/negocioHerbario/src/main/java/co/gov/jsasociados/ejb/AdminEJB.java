package co.gov.jsasociados.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Recolector;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;

/**
 * aca se protgama todo operaciones que puenen realizar en el administrador
 */
@Stateless
@LocalBean
public class AdminEJB implements AdminEJBRemote {

	/**
	 * permite manejar todas las transacciones manejadas por el empleado
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AdminEJB() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.gov.jsasociados.ejb.AdminEJBRemote#insertarEmpleado(co.gov.jsasociados.
	 * Empleado)
	 */
	public Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoException, PersonaNoRegistradaException {
		if (entityManager.find(Persona.class, empleado.getCedula()) != null) {
			throw new PersonaNoRegistradaException("La persona con esta cedula se encuentra agregada");
		} else if (buscarPorUsuario(empleado.getCuenta().getUsuario()) != null) {
			throw new ElementoRepetidoException("La persona con el usuario ya esta registrada");
		}
		try {
			entityManager.persist(empleado.getCuenta());
			entityManager.persist(empleado);
			return empleado;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("TEfadasfafas");
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.gov.jsasociados.ejb.AdminEJBRemote#insertarRecolector(co.gov.jsasociados.
	 * Recolector)
	 */
	public Recolector insertarRecolector(Recolector recolector)
			throws ElementoRepetidoException, PersonaNoRegistradaException {
		if (entityManager.find(Persona.class, recolector.getCedula()) != null) {
			throw new PersonaNoRegistradaException("La persona con esta cedula se encuentra agregada");
		} else if (buscarPorUsuario(recolector.getCuenta().getUsuario()) != null) {
			throw new ElementoRepetidoException("La persona con el usuario ya esta registrada");
		}
		try {
			entityManager.persist(recolector);
			return recolector;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#eliminarEmpleado(java.lang.String)
	 */
	public boolean eliminarEmpleado(String cedula) throws PersonaNoRegistradaException, TipoClaseException {
		if ((entityManager.find(Persona.class, cedula)) != null) {
			if (!((entityManager.find(Persona.class, cedula)).getClass().equals(Empleado.class))) {
				throw new TipoClaseException("La persona que desea eliminar no es un empleado");
			}
		} else {
			throw new PersonaNoRegistradaException("La persona a eliminiar no se encuentra registrada");
		}
		try {
			entityManager.remove(entityManager.find(Persona.class, cedula));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.gov.jsasociados.ejb.AdminEJBRemote#eliminarRecolector(java.lang.String)
	 */
	public boolean eliminarRecolector(String cedula) throws PersonaNoRegistradaException, TipoClaseException {
		if ((entityManager.find(Persona.class, cedula)) != null) {
			if (!((entityManager.find(Persona.class, cedula)).getClass().equals(Recolector.class))) {
				throw new TipoClaseException("La persona que desea eliminar no es un recolector");
			}
		} else {
			throw new PersonaNoRegistradaException("La persona a eliminiar no se encuentra registrada");
		}
		try {
			entityManager.remove(entityManager.find(Persona.class, cedula));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.gov.jsasociados.ejb.AdminEJBRemote#modificarEmpleado(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public Empleado modificarEmpleado(String nombre, String apellido, String telefono, String correo, String direccion,
			String cedula) throws PersonaNoRegistradaException, TipoClaseException {
		Persona empleado = entityManager.find(Persona.class, cedula);
		if (empleado == null) {
			throw new PersonaNoRegistradaException("La persona a la que quiere modificar los datos no esta registrada");
		} else if (!(empleado.getClass().equals(Empleado.class))) {
			throw new TipoClaseException("La persona a la que quiere modificar sus datos no es un empleado");
		}

		// Esto previamente por interfaz debe de estar validado para que no este vacio
		empleado.setNombre(nombre);
		empleado.setApellidos(apellido);
		empleado.setTelefono(telefono);
		empleado.setCorreo(correo);
		empleado.setDireccion(direccion);

		try {
			entityManager.merge(empleado);
			return (Empleado) empleado;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.gov.jsasociados.ejb.AdminEJBRemote#modificarRecolector(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public Recolector modificarRecolector(String nombre, String apellido, String telefono, String correo,
			String direccion, String cedula) throws PersonaNoRegistradaException, TipoClaseException {
		Persona recolector = entityManager.find(Persona.class, cedula);
		if (recolector == null) {
			throw new PersonaNoRegistradaException("La persona a la que quiere modificar los datos no esta registrada");
		} else if (!(recolector.getClass().equals(Recolector.class))) {
			throw new TipoClaseException("La persona a la que quiere modificar sus datos no es un recolector");
		}

		// Esto previamente por interfaz debe de estar validado para que no este vacio
		recolector.setNombre(nombre);
		recolector.setApellidos(apellido);
		recolector.setTelefono(telefono);
		recolector.setCorreo(correo);
		recolector.setDireccion(direccion);

		try {
			entityManager.merge(recolector);
			return (Recolector) recolector;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#listarEmpleados()
	 */
	public List<Empleado> listarEmpleados() throws Exception {
		try {
			TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.LISTAR_EMPLEADOS, Empleado.class);
			List<Empleado> empleados = query.getResultList();
			return empleados;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#listarRecolectores()
	 */
	public List<Recolector> listarRecolectores() throws Exception {
		try {
			TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.LISTAR_RECOLECTORES,
					Recolector.class);
			List<Recolector> recolectores = query.getResultList();
			return recolectores;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#buscarEmpleado(java.lang.String)
	 */
	public Empleado buscarEmpleado(String cedula) throws PersonaNoRegistradaException, TipoClaseException {
		Persona empleado = entityManager.find(Persona.class, cedula);
		if (empleado == null) {
			throw new PersonaNoRegistradaException("La persona a la que quiere buscar esta registrada");
		} else if (!(empleado.getClass().equals(Empleado.class))) {
			throw new TipoClaseException("La persona a la que busca no es un empleado");
		}
		return (Empleado) empleado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#buscarRecolector(java.lang.String)
	 */
	public Recolector buscarRecolector(String cedula) throws PersonaNoRegistradaException, TipoClaseException {
		Persona recolector = entityManager.find(Persona.class, cedula);
		if (recolector == null) {
			throw new PersonaNoRegistradaException("La persona a la que quiere buscar esta registrada");
		} else if (!(recolector.getClass().equals(Recolector.class))) {
			throw new TipoClaseException("La persona a la que busca no es un recolector");
		}
		return (Recolector) recolector;
	}

	/**
	 * metodo que permite buscar un empleado por su usuario
	 * 
	 * @param usuario
	 * @return
	 */
	private Persona buscarPorUsuario(String usuario) {
		try {
			TypedQuery<Persona> persona = entityManager.createNamedQuery(Persona.OBTENER_POR_USUARIO, Persona.class);
			persona.setParameter("usuario", usuario);
			return (Empleado) persona.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
			return null;
		}

	}
}
