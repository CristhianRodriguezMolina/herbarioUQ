package co.gov.jsasociados.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.gov.jsasociados.Cuenta;
import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.Recolector;
import co.gov.jsasociados.Registro;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
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
public class RecolectorEJB extends ComunEJB implements RecolectorEJBRemote {

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
	 * 
	 * @see co.gov.jsasociados.ejb.RecolectorEJBRemote#listarEspeciesAceptadas()
	 */
	public List<Planta> listarEspeciesAceptadas() {
		try {
			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_ACEPTADAS, Planta.class);
			List<Planta> plantas = query.getResultList();
			return plantas;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.gov.jsasociados.ejb.RecolectorEJBRemote#listarPlantasAceptasEnviadas(java.
	 * lang.String)
	 */
	public List<Planta> listarPlantasAceptasEnviadas(String cedulaRecolector) {
		try {
			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_ENVIADAS, Planta.class);
			query.setParameter("cedula", cedulaRecolector);
			query.setParameter("aprovacion", 1);
			List<Planta> plantas = query.getResultList();
			return plantas;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.gov.jsasociados.ejb.RecolectorEJBRemote#listarPlantasRechazadasEnviadas(
	 * java.lang.String)
	 */
	public List<Planta> listarPlantasRechazadasEnviadas(String cedulaRecolector) {
		try {
			TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_ENVIADAS, Planta.class);
			query.setParameter("cedula", cedulaRecolector);
			query.setParameter("aprovacion", -1);
			List<Planta> plantas = query.getResultList();
			return plantas;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
