/**
 * 
 */
package co.gov.jsasociados.ejb;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.gov.jsasociados.Comentario;
import co.gov.jsasociados.Cuenta;
import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.Recolector;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;

/**
 * EJB donde estan los metodos cumenes entre los roles
 * 
 * @author Sergio Osorio
 * @author Cristian Rodriguez
 * @author Jhontan Hidalgo
 *
 */
public class ComunEJB {
	/**
	 * permite manejar todas las transacciones manejadas por el empleado
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 
	 */
	public ComunEJB() {

	}

	/**
	 * metodo que permite insetar un recolector
	 * 
	 * @param recolector
	 * @return
	 * @throws ElementoRepetidoException
	 * @throws PersonaNoRegistradaException
	 */
	public Recolector insertarRecolector(Recolector recolector)
			throws ElementoRepetidoException, PersonaNoRegistradaException {
		if (entityManager.find(Persona.class, recolector.getCedula()) != null) {
			throw new PersonaNoRegistradaException("La persona con esta cedula se encuentra agregada");
		} else if (buscarPorUsuario(recolector.getCuenta().getUsuario()) != null) {
			throw new ElementoRepetidoException("La persona con el usuario ya esta registrada");
		}
		try {
			entityManager.persist(recolector.getCuenta());
			entityManager.persist(recolector);
			return buscarRecolector(recolector.getCedula());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
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
		Persona recolector = entityManager.find(Persona.class, cedula);
		if (recolector == null) {
			throw new PersonaNoRegistradaException("La persona a la que quiere buscar no esta registrada");
		} else if (!(recolector.getClass().equals(Recolector.class))) {
			throw new TipoClaseException("La persona a la que busca no es un recolector");
		}
		return (Recolector) recolector;
	}

	/**
	 * metodo que permite eliminar un recolector por su cedula
	 * 
	 * @param cedula
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
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
	public Recolector modificarRecolector(String nombre, String apellido, String telefono, String correo,
			String direccion, String cedula, String usuario, String clave) throws Exception {
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

//		Cuenta cuenta = modificarCuenta(usuario, clave, recolector);
//		if (cuenta == null) {
//			throw new Exception("Error al asignar la nueva cuenta");
//		}
//		cuenta.setPersona(recolector);
//		recolector.setCuenta(cuenta);

		try {
			entityManager.merge(recolector);
			return buscarRecolector(cedula);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	/**
	 * metodo que permite listar recolectores
	 * 
	 * @return
	 * @throws Exception
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

	/**
	 * metodo que permite registrar una planta
	 * 
	 * @param planta
	 * @return
	 * @throws ElementoRepetidoException
	 */
	public Planta registrarPlanta(Planta planta) throws ElementoRepetidoException {
		if (buscarPlanta(planta.getNombre()) != null) {
			throw new ElementoRepetidoException("La planta ya se encuentra registrada.");
		}
		try {
			entityManager.persist(planta);
			return buscarPlanta(planta.getNombre());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/**
	 * metodo que permite buscar una planta pos su nombre
	 * 
	 * @param nombrePlanta
	 * @return
	 */
	public Planta buscarPlanta(String nombrePlanta) {
		try {
			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.OBTENER_PLANTA_POR_NOMBRE, Planta.class);
			query.setParameter("nombre", nombrePlanta);
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/**
	 * metodo que permite listar los nombres de una planta
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> listarNombresPlanta() throws Exception {
		try {
			TypedQuery<String> query = entityManager.createNamedQuery(Planta.LISTAR_NOMBRES_PLANTAS, String.class);
			List<String> plantas = query.getResultList();
			return plantas;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * metodo que permite listar las plantas por un filtro
	 * 
	 * @param f
	 * @param g
	 * @return
	 */
	public List<Planta> listarPlantasPorFiltros(Familia f, Genero g) {
		try {
			TypedQuery<Planta> query = null;
			if (f == null) {
				query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_GENERO, Planta.class);
				query.setParameter("genero", g.getGenero());
			} else if (g == null) {
				query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_FAMILIA, Planta.class);
				query.setParameter("familia", f.getFamilia());
			} else {
				query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_GENERO_Y_FAMILIA, Planta.class);
				query.setParameter("familia", f.getFamilia());
				query.setParameter("genero", g.getGenero());
			}
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * metodo que permite listar todas las plantas
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Planta> listarPlanta() throws Exception {
		try {
			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS, Planta.class);
			List<Planta> plantas = query.getResultList();
			return plantas;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * metodo que permite restablecer la clave de una cuenta
	 * 
	 * @param persona
	 * @return
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoRepetidoException
	 */
	public String restablecerClave(Persona persona) throws ElementoNoEncontradoException, ElementoRepetidoException {
		Cuenta cuenta = modificarClaveCuenta(generarClave(), persona);
		if (cuenta != null) {
			return cuenta.getContrasenia();
		}
		return "";
	}
	/**
	 * metodo que permite insetar un comentario
	 * @param comentario
	 */
	public void insertarComentario(Comentario comentario) {
		try {
			entityManager.persist(comentario);
		} catch (Exception e) {
			System.out.println("No se pudo registrar el comentario");
		}
	}
	/**
	 * metodo que permite listar los comentarios
	 * @param p
	 * @return
	 */
	public List<Comentario> listarComentarios(Planta p) {
		try {
			TypedQuery<Comentario> query = entityManager.createNamedQuery(Comentario.LISTAR_COMENTARIOS_PLANTA,
					Comentario.class);
			query.setParameter("nombrePlanta", p.getNombre());
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * metodo que permite buscar una persona por su usuario
	 * 
	 * @param usuario
	 * @return
	 */
	private Persona buscarPorUsuario(String usuario) {
		try {
			TypedQuery<Persona> persona = entityManager.createNamedQuery(Persona.OBTENER_POR_USUARIO, Persona.class);
			persona.setParameter("usuario", usuario);
			return persona.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
			return null;
		}

	}

	/**
	 * metodo que permite devolver una cadena de 10 digitos aleatoria
	 * 
	 * @return
	 */
	private String generarClave() {
		return (UUID.randomUUID().toString()).substring(0, 10);
	}

	/**
	 * metodo que permite moficar el usuario de la cuenta de una persona
	 * 
	 * @param clave   - nueva
	 * @param persona
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoRepetidoException
	 */
	private Cuenta modificarClaveCuenta(String clave, Persona persona)
			throws ElementoNoEncontradoException, ElementoRepetidoException {
		if (buscarPorUsuario(persona.getCuenta().getUsuario()) == null) {
			throw new ElementoNoEncontradoException("No se ha encontrado el usuario");
		}
		try {
			Cuenta cuenta = entityManager.find(Cuenta.class, persona.getCuenta().getUsuario());
			cuenta.setContrasenia(clave);
			entityManager.merge(cuenta);
			return entityManager.find(Cuenta.class, cuenta.getUsuario());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	/**
	 * metodo que permite moficar el usuario de la cuenta de una persona
	 * 
	 * @param usuario -nuevo
	 * @param persona
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoRepetidoException
	 */
	private Cuenta modificarUsuarioCuenta(String usuario, Persona persona)
			throws ElementoNoEncontradoException, ElementoRepetidoException {
		if (buscarPorUsuario(persona.getCuenta().getUsuario()) == null) {
			throw new ElementoNoEncontradoException("No se ha encontrado el usuario");
		} else if (buscarPorUsuario(usuario) != null) {
			throw new ElementoRepetidoException("El usuario ya esta tomado");
		}
		try {
			Cuenta cuenta = entityManager.find(Cuenta.class, persona.getCuenta().getUsuario());
			cuenta.setUsuario(usuario);
			entityManager.merge(cuenta);
			return entityManager.find(Cuenta.class, cuenta.getUsuario());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
}
