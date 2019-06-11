package co.gov.jsasociados.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Recolector;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.FamiliaYaRegistradaExeption;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;

/**
 * Session Bean implementation class EmpleadoEJB
 */
@Stateless
@LocalBean
public class EmpleadoEJB implements EmpleadoEJBRemote {

	/**
	 * permite manejar todas las transacciones manejadas por el empleado
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public EmpleadoEJB() {
    	
    }

    /*
     * (non-Javadoc)
     * @see co.gov.jsasociados.ejb.EmpleadoEJBRemote#insertarFamilia(co.gov.jsasociados.Familia)
     */
    public Familia insertarFamilia(Familia familia)
			throws ElementoRepetidoException, FamiliaYaRegistradaExeption {
		if (entityManager.find(Familia.class, familia.getIdFamilia()) != null) {
			throw new FamiliaYaRegistradaExeption("La familia con ese id ya se encuentra agregada");
		} else if (buscarFamiliaPorNombre(familia.getFamilia()) != null) {
			throw new ElementoRepetidoException("La familia con ese nombre ya esta registrada");
		}
		try {
			entityManager.persist(familia);
			return familia;
		} catch (Exception e) {
			return null;
		}
	}
    
    /*
     * (non-Javadoc)
     * @see co.gov.jsasociados.ejb.EmpleadoEJBRemote#eliminarFamilia(java.lang.String)
     */
    public boolean eliminarFamilia(String idFamilia) throws FamiliaYaRegistradaExeption {
		if ((entityManager.find(Familia.class, idFamilia)) == null){
			throw new FamiliaYaRegistradaExeption("La familia a eliminiar no se encuentra registrada");
		}
		try {
			entityManager.remove(entityManager.find(Familia.class, idFamilia));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
    
    /*
     * (non-Javadoc)
     * @see co.gov.jsasociados.ejb.EmpleadoEJBRemote#modificarFamilia(java.lang.String, java.lang.String)
     */
    public Familia modificarFamilia(String nombre, String idFamilia) throws FamiliaYaRegistradaExeption {
		Familia familia = entityManager.find(Familia.class, idFamilia);
		if (familia == null) {
			throw new FamiliaYaRegistradaExeption("La familia a la que quiere modificar los datos no esta registrada");
		}

		// Esto previamente por interfaz debe de estar validado para que no este vacio
		familia.setFamilia(nombre == ""? familia.getFamilia():nombre);
		familia.setIdFamilia(idFamilia == ""? familia.getIdFamilia():idFamilia);

		try {
			entityManager.merge(familia);
			return (Familia) familia;
		} catch (Exception e) {
			return null;
		}

	}
    
    /*
     * (non-Javadoc)
     * @see co.gov.jsasociados.ejb.EmpleadoEJBRemote#listarFamilias()
     */
    public List<Familia> listarFamilias() throws Exception {
		try {
			TypedQuery<Familia> query = entityManager.createNamedQuery(Familia.LISTAR_FAMILIAS,
					Familia.class);
			List<Familia> familias = query.getResultList();
			return familias;
		} catch (Exception e) {
			return null;
		}

	}
    
    /**
     * Metodo para buscar una familia por su nombre 
     * @param nombreFamilia nombre de la familia a buscar
     * @return la familia si se encontro o null de lo contrario
     */
    private Familia buscarFamiliaPorNombre(String nombreFamilia) {
		try {
			TypedQuery<Familia> familia = entityManager.createNamedQuery(Familia.OBTENER_POR_NOMBRE, Familia.class);
			familia.setParameter("nombre", nombreFamilia);
			return (Familia) familia.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
   
    /*
     * (non-Javadoc)
     * @see co.gov.jsasociados.ejb.EmpleadoEJBRemote#insertarRecolector(co.gov.jsasociados.Recolector)
     */
    public Recolector insertarRecolector(Recolector recolector)
			throws ElementoRepetidoException, PersonaNoRegistradaException {
		if (entityManager.find(Persona.class, recolector.getCedula()) != null) {
			throw new PersonaNoRegistradaException("El recolector con esta cedula se encuentra agregado");
		} else if (buscarPorUsuario(recolector.getCuenta().getUsuario()) != null) {
			throw new ElementoRepetidoException("El recolector con ese usuario ya esta registrado");
		}
		try {
			entityManager.persist(recolector);
			return recolector;
		} catch (Exception e) {
			return null;
		}
	}
    
    /*
     * (non-Javadoc)
     * @see co.gov.jsasociados.ejb.EmpleadoEJBRemote#eliminarRecolector(java.lang.String)
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
			return false;
		}
	}
    
    /*
     * (non-Javadoc)
     * @see co.gov.jsasociados.ejb.EmpleadoEJBRemote#modificarRecolector(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
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
		recolector.setNombre(nombre == ""? recolector.getNombre():nombre);
		recolector.setApellidos(apellido == ""? recolector.getApellidos():apellido);
		recolector.setTelefono(telefono == ""? recolector.getTelefono():telefono);
		recolector.setCorreo(correo == ""? recolector.getCorreo():correo);
		recolector.setDireccion(direccion == ""? recolector.getDireccion():direccion);

		try {
			entityManager.merge(recolector);
			return (Recolector) recolector;
		} catch (Exception e) {
			return null;
		}

	}
    
    /*
     * (non-Javadoc)
     * @see co.gov.jsasociados.ejb.EmpleadoEJBRemote#listarRecolectores()
     */
    public List<Recolector> listarRecolectores() throws Exception {
		try {
			TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.LISTAR_RECOLECTORES,
					Recolector.class);
			List<Recolector> recolectores = query.getResultList();
			return recolectores;
		} catch (Exception e) {
			return null;
		}

	}
    
    /*
     * (non-Javadoc)
     * @see co.gov.jsasociados.ejb.EmpleadoEJBRemote#buscarRecolector(java.lang.String)
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
     * Metodo para buscar una persona por su usuario
     * @param usuario
     * @return La persona si fue encontrada o null de lo contrario
     */
    private Persona buscarPorUsuario(String usuario) {
		try {
			TypedQuery<Persona> persona = entityManager.createNamedQuery(Persona.OBTENER_POR_USUARIO, Persona.class);
			persona.setParameter("usuario", usuario);
			return (Empleado) persona.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
}
