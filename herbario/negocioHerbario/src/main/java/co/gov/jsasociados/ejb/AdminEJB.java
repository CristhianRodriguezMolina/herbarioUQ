package co.gov.jsasociados.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Persona;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;

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
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#insertarEmpleado(co.gov.jsasociados.Empleado)
	 */
	public Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoException {
		if (entityManager.find(Persona.class, empleado.getCedula()) != null) {
			throw new ElementoRepetidoException("La persona con esta cedula se encuentra agregada");
		}
		else if (buscarPorUsuario(empleado.getCuenta().getUsuario()) != null) {
			throw new ElementoRepetidoException("La persona con el usuario ya esta registrada");
		}
		try {
			entityManager.persist(empleado);
			return empleado;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
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
