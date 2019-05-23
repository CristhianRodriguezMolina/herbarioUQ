package co.gov.jsasociados;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Clase que permite relizar los test de planta
 * 
 * @author Sergio Osorio
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 *
 */

@RunWith(Arquillian.class)
public class PruebaRecolector {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * general el archivo de depliegue de pruebas
	 * 
	 * @return genera un archivo de configuracion web
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	/**
	 * busca a un recolector por su id
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void buscarRecolectorPorId() {
		Recolector recolector = entityManager.find(Recolector.class, "127");
		Assert.assertNotNull(recolector);
	}

	/**
	 * lista todos los recolectores
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarRecolectores() {
		TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.LISTAR_RECOLECTORES, Recolector.class);
		List<Recolector> listarecolectores = query.getResultList();
		Assert.assertEquals(listarecolectores.size(), 3);
	}

	/**
	 * lista todas las plantas aceptadas
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasAceptadas() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_ACEPTADAS, Planta.class);
		List<Planta> listaPlantas = query.getResultList();
		Assert.assertEquals(listaPlantas.size(), 2);
	}

	/**
	 * lista todas las plantas teniendo en cuenta la cedula del que registro la
	 * planta y el estado del registro
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasEnviadas() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_ENVIADAS, Planta.class);
		query.setParameter("cedula", "127");
		query.setParameter("aprovacion", 1);
		List<Planta> listaPlantas = query.getResultList();
		Assert.assertEquals(listaPlantas.size(), 1);
	}

	/**
	 * lista todas las plantas aceptadas por una cierta familia
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasAceptadasPorFamilia() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_ACEPTADAS_POR_FAMILIA,
				Planta.class);
		query.setParameter("familia", "toxocodarmus");
		List<Planta> listaPlantas = query.getResultList();
		Assert.assertEquals(listaPlantas.size(), 1);
	}

	/**
	 * lista todas las plantas aceptadas por ciero genero
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasAceptadasPorGenero() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_GENERO, Planta.class);
		query.setParameter("genero", "carnivoras");
		List<Planta> listaPlantas = query.getResultList();
		Assert.assertEquals(listaPlantas.size(), 2);

	}

	/**
	 * test para agregar un recolector
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void agregarRecolectorTest() {

		Recolector recolector = new Recolector();
		recolector.setCedula("12354");
		recolector.setApellidos("Franco");
		recolector.setNombre("Carlos");
		recolector.setCorreo("ad@correo.com");
		recolector.setDireccion("dire");
		recolector.setTelefono("1298834");

		Cuenta cuenta = new Cuenta();
		cuenta.setContrasenia("123");
		cuenta.setUsuario("123");

		cuenta.setPersona(recolector);
		recolector.setCuenta(cuenta);

		entityManager.persist(cuenta);
		entityManager.persist(recolector);

		entityManager.find(recolector.getClass(), recolector.getCedula());
	}

	/**
	 * test para eliminar un recolector
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void eliminarRecolectorTest() {
		Recolector ad = entityManager.find(Recolector.class, "129");
		Assert.assertNotNull(ad);
		entityManager.remove(ad);
		Assert.assertNull("No se ha eliminado", entityManager.find(Administrador.class, "129"));
	}

	/**
	 * test para eliminar un recolector
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void modificarRecolectorTest() {
		Recolector ad = entityManager.find(Recolector.class, "129");
		Assert.assertNotNull(ad);
		ad.setNombre("Alfredo");
		entityManager.merge(ad);
		ad = null;
		ad = entityManager.find(Recolector.class, "129");
		Assert.assertEquals("Alfredo", ad.getNombre());
	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void obtenerRecolectoresConRegistroTest() {

		TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.LISTAR_RECOLECTORES_CON_REGISTROS, Recolector.class);
		List<Recolector> lista = query.getResultList();

		Assert.assertEquals(1, lista.size());
	}
}
