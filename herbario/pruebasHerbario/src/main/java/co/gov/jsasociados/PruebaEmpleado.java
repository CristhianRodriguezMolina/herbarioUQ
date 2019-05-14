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
 * Clase que permite relizar los test de Empleado
 * 
 * @author Sergio Osorio
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 *
 */
@RunWith(Arquillian.class)
public class PruebaEmpleado {
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
	 * lista todos los empleados
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarEmpleadosTest() {
		TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.LISTAR_EMPLEADOS, Empleado.class);
		List<Empleado> listaEmpleados = query.getResultList();

		Assert.assertEquals(listaEmpleados.size(), 2);
	}

	/**
	 * lista todas las plantas por aprovacion
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasAprovadasFromEmpleadoTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Empleado.LISTAR_PLANTAS_POR_APROVACION, Planta.class);
		query.setParameter("aprovacion", 1);
		List<Planta> listaPlantas = query.getResultList();

		Assert.assertEquals(listaPlantas.size(), 2);
	}

	/**
	 * lista todas las plantas por familia
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasPorFamiliaFromEmpleadoTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Empleado.LISTAR_PLANTAS_POR_FAMILIA, Planta.class);
		query.setParameter("familia", "telepiatos");
		List<Planta> listaPlantas = query.getResultList();

		Assert.assertEquals(listaPlantas.size(), 2);
	}

	/**
	 * listar plantas por genero
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasPorGeneroFromEmpleadoTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Empleado.LISTAR_PLANTAS_POR_GENERO, Planta.class);
		query.setParameter("genero", "carnivoras");
		List<Planta> listaPlantas = query.getResultList();

		Assert.assertEquals(listaPlantas.size(), 2);
	}

	/**
	 * buscar empleado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void buscarEmpleadoTest() {
		Empleado empleado = entityManager.find(Empleado.class, "128");
		Assert.assertNotNull(empleado);
	}

	/**
	 * agregar empleado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void agregarEmpleadoTest() {
		Empleado emplado = new Empleado();
		emplado.setCedula("12354");
		emplado.setApellidos("Franco");
		emplado.setNombre("Carlos");
		emplado.setCorreo("ad@correo.com");
		emplado.setDireccion("dire");
		emplado.setTelefono("1298834");

		Cuenta cuenta = new Cuenta();
		cuenta.setContrasenia("123");
		cuenta.setUsuario("123");

		cuenta.setPersona(emplado);
		emplado.setCuenta(cuenta);

		entityManager.persist(cuenta);
		entityManager.persist(emplado);

		entityManager.find(emplado.getClass(), emplado.getCedula());
	}

	/**
	 * test para eliminar un empleado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void eliminarEmpladoTest() {
		Empleado ad = entityManager.find(Empleado.class, "125");
		Assert.assertNotNull(ad);
		entityManager.remove(ad);
		Assert.assertNull("No se ha eliminado", entityManager.find(Administrador.class, "125"));
	}

	/**
	 * test para eliminar un empleado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void modificarEmpleadoTest() {
		Empleado ad = entityManager.find(Empleado.class, "125");
		Assert.assertNotNull(ad);
		ad.setNombre("Alfredo");
		entityManager.merge(ad);
		ad = null;
		ad = entityManager.find(Empleado.class, "125");
		Assert.assertEquals("Alfredo", ad.getNombre());
	}
}
