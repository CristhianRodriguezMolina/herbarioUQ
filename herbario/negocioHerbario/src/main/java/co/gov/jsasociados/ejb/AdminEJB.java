package co.gov.jsasociados.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.gov.jsasociados.Cuenta;
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
			return buscarEmpleado(empleado.getCedula());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(String.format("Error al insertar un empleado %", empleado.toString()));
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
			entityManager.persist(recolector.getCuenta());
			entityManager.persist(recolector);
			return buscarRecolector(recolector.getCedula());
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
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public Empleado modificarEmpleado(String nombre, String apellido, String telefono, String correo, String direccion,
			String cedula, String usuario, String clave) throws Exception {
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

//		Cuenta cuenta = modificarCuenta(usuario, clave, empleado);
//		if (cuenta == null) {
//			throw new Exception("Error al asignar la nueva cuenta");
//		}
//		cuenta.setPersona(empleado);
//		empleado.setCuenta(cuenta);
		try {
			entityManager.merge(empleado);
			return buscarEmpleado(cedula);
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
	 * java.lang.String, java.lang.String, java.lang.String)
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
		} catch (NullPointerException e) {
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
			throw new PersonaNoRegistradaException("La persona a la que quiere buscar no esta registrada");
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
			throw new PersonaNoRegistradaException("La persona a la que quiere buscar no esta registrada");
		} else if (!(recolector.getClass().equals(Recolector.class))) {
			throw new TipoClaseException("La persona a la que busca no es un recolector");
		}
		return (Recolector) recolector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.gov.jsasociados.ejb.AdminEJBRemote#insertarFamilia(co.gov.jsasociados.
	 * Familia)
	 */
	public Familia insertarFamilia(Familia familia) throws FamiliaYaRegistradaExeption {
		if (buscarFamilia(familia.getFamilia()) != null) {
			throw new FamiliaYaRegistradaExeption("La familia ya se encuentra registrada");
		} else {
			try {
				entityManager.persist(familia);
				return buscarFamilia(familia.getFamilia());
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}

	}

	//// parte para agregar en empleadoEJB
	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#eliminarFamilia(java.lang.String)
	 */
	public boolean eliminarFamilia(Long idFamilia) throws ElementoNoEncontradoException {
		Familia familia = entityManager.find(Familia.class, idFamilia);
		if (familia == null) {
			throw new ElementoNoEncontradoException("La familia a eliminiar no se encuentra registrada");
		}
		try {
			entityManager.remove(familia);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#modificarFamilia(java.lang.String,
	 * java.lang.String)
	 */
	public Familia modificarFamilia(String nombre, Long idFamilia) throws ElementoNoEncontradoException {
		Familia familia = entityManager.find(Familia.class, idFamilia);
		if (familia == null) {
			throw new ElementoNoEncontradoException(
					"La familia a la que quiere modificar los datos no esta registrada");
		}

		// Esto previamente por interfaz debe de estar validado para que no este vacio
		familia.setFamilia(nombre);
		try {
			entityManager.merge(familia);
			return buscarFamilia(familia.getFamilia());
		} catch (Exception e) {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#listarFamilias()
	 */
	public List<Familia> listarFamilias() throws Exception {
		try {
			TypedQuery<Familia> query = entityManager.createNamedQuery(Familia.LISTAR_FAMILIAS, Familia.class);
			List<Familia> familias = query.getResultList();
			return familias;
		} catch (Exception e) {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#buscarFamilia(java.lang.String)
	 */
	public Familia buscarFamilia(String famila) {
		try {
			TypedQuery<Familia> query = entityManager.createNamedQuery(Familia.OBTENER_POR_NOMBRE, Familia.class);
			query.setParameter("familia", famila);
			return query.getSingleResult();
		}catch(Exception e1) {
			return null;
		}
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#insertarGenero(co.gov.jsasociados.
	 * Genero)
	 */
	public Genero insertarGenero(Genero genero) throws GeneroYaRegistradoExcepcion {
		if (buscarGenero(genero.getGenero()) != null) {
			throw new GeneroYaRegistradoExcepcion("El genero ya se encuentra registrado");
		} else {
			try {
				entityManager.persist(genero);
				return buscarGenero(genero.getGenero());
			} catch (Exception e) {
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#elimiarGenero(java.lang.String)
	 */
	public boolean elimiarGenero(Long idGenero) throws ElementoNoEncontradoException {
		Genero genero = entityManager.find(Genero.class, idGenero);
		if (genero == null) {
			throw new ElementoNoEncontradoException("El genero a eliminar no se encuentra registrado");
		}
		try {
			entityManager.remove(genero);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#modificarGenero(java.lang.String,
	 * java.lang.String)
	 */
	public Genero modificarGenero(String genero, Long idGenero) throws ElementoNoEncontradoException {
		Genero gen = entityManager.find(Genero.class, idGenero);
		if (gen == null) {
			throw new ElementoNoEncontradoException("El genero al que quiere modificar los datos no esta registrado");
		}

		// Esto previamente por interfaz debe de estar validado para que no este vacio
		gen.setGenero(genero);
		try {
			entityManager.merge(gen);
			return buscarGeneroId(gen.getIdGenero());
		} catch (Exception e) {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#listarGenero()
	 */
	public List<Genero> listarGenero() throws Exception {
		try {
			TypedQuery<Genero> query = entityManager.createNamedQuery(Genero.OBTENER_GENEROS, Genero.class);
			List<Genero> generos = query.getResultList();
			return generos;
		} catch (Exception e) {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#buscarGenero(java.lang.String)
	 */
	public Genero buscarGenero(String genero) {
		try {
			TypedQuery<Genero> gen = entityManager.createNamedQuery(Genero.OBTENER_GENERO, Genero.class);
			gen.setParameter("genero", genero);
			return gen.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#insertarEspecie(co.gov.jsasociados.Planta)
	 */
	public Planta insertarEspecie(Planta planta) throws ElementoRepetidoException {
		if (buscarPlanta(planta.getNombre()) != null) {
			throw new ElementoRepetidoException("La planta ya se encuentra registrada");
		} else {
			try {
				entityManager.persist(planta);
				System.out.println(buscarPlantaId(planta.getIdPlanta()));
				return buscarPlantaId(planta.getIdPlanta());
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#elimiarEspecie(java.lang.Long)
	 */
	public boolean elimiarEspecie(Long idPlanta) throws ElementoNoEncontradoException {
		Planta planta = entityManager.find(Planta.class, idPlanta);
		if (planta == null) {
			throw new ElementoNoEncontradoException("La planta a eliminar no se encuentra registrada");
		}
		try {
			entityManager.remove(planta);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#modificarEspecie(java.lang.String,
	 * java.lang.Long)
	 */
	public Planta modificarEspecie(Long idPlanta, String nombrePlanta, Genero genero, String descripcion, byte[] imagen) throws ElementoNoEncontradoException, ElementoRepetidoException {
		Planta planta = entityManager.find(Planta.class, idPlanta);
		if (planta == null) {
			throw new ElementoNoEncontradoException("El genero al que quiere modificar los datos no esta registrado");
		}
		if(planta.getNombre().equals(nombrePlanta)) {
			throw new ElementoRepetidoException("El nombre de la especie a modificar le pertenece a otra planta");
		}

		// Esto previamente por interfaz debe de estar validado para que no este vacio
		planta.setNombre(nombrePlanta);
		planta.setGenero(genero);
		planta.setDescripcion(descripcion);
		planta.setImagen(imagen);		
		try {
			entityManager.merge(planta);
			return buscarPlantaId(planta.getIdPlanta());
		} catch (Exception e) {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#listarPlanta()
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
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#listarNombresPlanta()
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
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#listarNombresGenero()
	 */
	public List<String> listarNombresGenero() throws Exception {
		try {
			TypedQuery<String> query = entityManager.createNamedQuery(Genero.LISTAR_NOMBRES_GENEROS, String.class);
			List<String> generos = query.getResultList();
			return generos;
		} catch (Exception e) {
			return null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#listarNombresFamilia()
	 */
	public List<String> listarNombresFamilia() throws Exception {
		try {
			TypedQuery<String> query = entityManager.createNamedQuery(Familia.LISTAR_NOMBRES_FAMILIAS, String.class);
			List<String> familias = query.getResultList();
			return familias;
		} catch (Exception e) {
			return null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#buscarPlanta(java.lang.String)
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
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.AdminEJBRemote#listarPlantasPorFiltros(co.gov.jsasociados.Familia, co.gov.jsasociados.Genero)
	 */
	public List<Planta> listarPlantasPorFiltros(Familia f, Genero g){
		try {
			TypedQuery<Planta> query = null;
			if(f == null) {
				query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_GENERO, Planta.class);
				query.setParameter("genero", g.getGenero());
			}else if(g == null) {
				query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_FAMILIA, Planta.class);
				query.setParameter("familia", f.getFamilia());
			}else {
				query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_GENERO_Y_FAMILIA, Planta.class);
				query.setParameter("familia", f.getFamilia());
				query.setParameter("genero", g.getGenero());
			}			
			return query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * metodo que permite buscar una planta por su id
	 * 
	 * @param idPlanta
	 * @return
	 */
	private Planta buscarPlantaId(Long idPlanta) {
		try {
			return entityManager.find(Planta.class, idPlanta);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/**
	 * metodo que permite buscar un genenero pos su id
	 * 
	 * @param idGenero
	 * @return
	 */
	private Genero buscarGeneroId(Long idGenero) {
		try {

			return entityManager.find(Genero.class, idGenero);
		} catch (Exception e) {
			// TODO: handle exception
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
	 * metodo que permite moficar la cuenta de una persona
	 * 
	 * @param usuario
	 * @param clave
	 * @param persona
	 * @throws ElementoNoEncontradoException
	 * @throws ElementoRepetidoException
	 */
	private Cuenta modificarCuenta(String usuario, String clave, Persona persona)
			throws ElementoNoEncontradoException, ElementoRepetidoException {
		if (buscarPorUsuario(persona.getCuenta().getUsuario()) == null) {
			throw new ElementoNoEncontradoException("No se ha encontrado el usuario");
		} else if (buscarPorUsuario(usuario) != null) {
			throw new ElementoRepetidoException("El usuario ya esta tomado");
		}
		try {
			entityManager.remove(persona.getCuenta());
			Cuenta cuenta = new Cuenta();
			cuenta.setUsuario(usuario);
			cuenta.setContrasenia(clave);
			entityManager.merge(cuenta);
			return entityManager.find(Cuenta.class, cuenta.getUsuario());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
}
