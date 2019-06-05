package co.gov.jsasociados.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.gov.jsasociados.entidades.Cuenta;
import co.gov.jsasociados.entidades.Familia;
import co.gov.jsasociados.entidades.Genero;
import co.gov.jsasociados.entidades.Persona;
import co.gov.jsasociados.entidades.Planta;
import co.gov.jsasociados.entidades.Recolector;
import co.gov.jsasociados.entidades.Registro;
import co.gov.jsasocioados.exeption.ElementoNoExiste;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;

/**
 * @author Sergio Osorio
 * @author Cristian Rodriguez
 * @author Jhontan Hidalgo
 *
 */
@Stateless
@LocalBean
public class RecolectorEJB implements RecolectorEJBRemote{
	
	/**
	 * Aca se programa todo lo que puede hacer un recolector
	 */
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	/**
	 * Contrustor predeterminado
	 */
	public RecolectorEJB() {
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.RecolectorEJBRemote#insertarRecolector(co.gov.jsasociados.Recolector)
	 */
	public Recolector insertarRecolector(Recolector recolector)throws ElementoRepetidoException{
		if(entityManager.find(Persona.class, recolector.getCedula())!=null) {
			throw new ElementoRepetidoException("La persona con esta cedula ya se encuentra registrada");
		}else if(entityManager.find(Cuenta.class, recolector.getCuenta().getUsuario())!=null) {
			throw new ElementoRepetidoException("Este usuario ya se encuentra asignado a otra persona");
		}else {
			try {
				entityManager.persist(recolector);
				return recolector;
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.RecolectorEJBRemote#modificarRecolector(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Recolector modificarRecolector(String nombre, String apellido, String telefono, String correo, String direccion,
			String cedula)throws PersonaNoRegistradaException, TipoClaseException{
		
		Persona recolector=entityManager.find(Persona.class, cedula);
		
		if(recolector==null) {
			throw new PersonaNoRegistradaException("La persona con esta cedula no se encuentra registrada");
		}else if(!(recolector.getClass().equals(Recolector.class))) {
			throw new TipoClaseException("La persona la cual desea modificarle datos no es un recolector");
		}else {
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
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.RecolectorEJBRemote#insertarPlanta(co.gov.jsasociados.Planta)
	 */
	//FALTA VALIDAR LO DE LA IMAGEN
	public Planta insertarPlanta(Planta planta)throws ElementoRepetidoException, ElementoNoExiste{
		if(entityManager.find(Planta.class, planta.getIdPlanta())!=null) {
			throw new ElementoRepetidoException("Ya existe una planta con este ID");
		}else if(entityManager.find(Genero.class, planta.getGenero().getIdGenero())==null) {
			throw new ElementoNoExiste("El genero de esta planta no se encuentra registrado");
		}else if(entityManager.find(Familia.class, planta.getGenero().getFamilia().getIdFamilia())==null) {
			throw new ElementoNoExiste("La familia de esta planta no se encuentra registrada");
		}else {
			try {
				entityManager.persist(planta);
				return planta;
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.RecolectorEJBRemote#buscarPlanta(java.lang.String)
	 */
	public Planta buscarPlanta(String idPlanta)throws ElementoNoExiste{
		Planta planta=entityManager.find(Planta.class, idPlanta);
		if(planta==null) {
			throw new ElementoNoExiste("La planta con este id no se encuentra registrada aun");
		}
		return planta;
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.RecolectorEJBRemote#listarEspeciesAceptadas()
	 */
	public List<Planta> listarEspeciesAceptadas(){
			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_ACEPTADAS,
					Planta.class);
			List<Planta> plantas = query.getResultList();
			return plantas;
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.RecolectorEJBRemote#listarRegistros(java.lang.String)
	 */
	public ArrayList<Registro> listarRegistros(String cedula)throws PersonaNoRegistradaException{
		Persona recolector=entityManager.find(Persona.class, cedula);
		if(recolector==null) {
			throw new PersonaNoRegistradaException("No se encuentra registrada una persona con esta cedula");
		}else if(!(recolector.getClass().equals(Recolector.class))) { 
			throw new PersonaNoRegistradaException("Esta cedula no pertenece a un recolector");
		}else {
			return recolector.getRegistro();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.RecolectorEJBRemote#listarPlantasAceptadasPorFamilia(java.lang.String)
	 */
	public List<Planta>listarPlantasAceptadasPorFamilia(String idFamilia)throws ElementoNoExiste{
		Familia familia=entityManager.find(Familia.class, idFamilia);
		if(familia==null) {
			throw new ElementoNoExiste("No se encuentra ninguna familia registrada con este id");
		}else {
			TypedQuery<Planta>query=entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_ACEPTADAS_POR_FAMILIA, Planta.class);
			query.setParameter("familia", familia.getFamilia());
			List<Planta>plantasAprovadas=query.getResultList();
			return plantasAprovadas;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.gov.jsasociados.ejb.RecolectorEJBRemote#listarPlantasAceptasPorGenero(java.lang.String)
	 */
	public List<Planta>listarPlantasAceptasPorGenero(String idGenero)throws ElementoNoExiste{
		Genero genero=entityManager.find(Genero.class, idGenero);
		if(genero==null) {
			throw new ElementoNoExiste("No se encuentra ningun genero registrado con este id");
		}else {
			try {
				TypedQuery<Planta>query=entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_ACEPTADAS_POR_GENERO, Planta.class);
				query.setParameter("genero", genero.getGenero());
				List<Planta>plantasAprovadas=query.getResultList();
				return plantasAprovadas;
			} catch (Exception e) {
				return null;
			}
			
		}
	}
	
}
