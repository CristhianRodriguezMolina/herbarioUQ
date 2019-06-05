package co.gov.jsasociados;

import java.util.Iterator;
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

import co.gov.jsasociados.Administrador;
import co.gov.jsasociados.Cuenta;
import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Planta;
import co.gov.jsasociados.Recolector;

/**
 * Clase que permite relizar los test de administrador
 * 
 * @author Sergio Osorio
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 *
 */
@RunWith(Arquillian.class)
public class PruebaAdministrador {
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
	 * metodo test para saber que personas no han realizado registros de plantas
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void obtenerPersonaSinRegistro() {
		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.PERSONA_SIN_REGISTRO, Persona.class);
		List<Persona> listaPersonas = query.getResultList();

		Iterator<Persona> iterator = listaPersonas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
	/**
	 * Test para listar todos los administradores
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarAdministradoresTest() {
		TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.LISTAR_ADMINISTRADORES,
				Administrador.class);
		List<Administrador> listaAdministradores = query.getResultList();

		Assert.assertEquals(listaAdministradores.size(), 2);
	}

	/**
	 * Test para buscar un administrador dada su Id
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void buscarAdministradorByIdTest() {
		TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.BUSCAR_POR_ID,
				Administrador.class);
		query.setParameter("cedula", "123");
		Administrador administrador = query.getSingleResult();
		Assert.assertNotNull(administrador);
	}

	/**
	 * Test para listar todas las plantas desde el rol de administrador (Metodo
	 * funciona)
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasAdministradorTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS, Planta.class);
		List<Planta> listaPlantas = query.getResultList();

		Assert.assertEquals(listaPlantas.size(), 5);
	}

	/**
	 * Test para listar todas las plantas de una familia
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasFamiliaTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_FAMILIA,
				Planta.class);
		query.setParameter("familia", "xnoramos");
		List<Planta> listaPlantas = query.getResultList();
		Assert.assertEquals(listaPlantas.size(), 1);
	}

	/**
	 * lista todas las plantas por su estado de aprovacion, desde el rol de
	 * administrador 1 si, 0 enviado, -1 rechazado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void ListarplantasPorAprobacion() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_APROVACION,
				Planta.class);
		query.setParameter("aprovacion", 1);
		List<Planta> listaPlantas = query.getResultList();
		Assert.assertEquals(listaPlantas.size(), 2);
	}

	/**
	 * lista todos los recolectores, desde el rol de administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void LisatarRecolectores() {
		TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.LISTAR_RECOLECTORES,
				Recolector.class);
		List<Recolector> listarecolectores = query.getResultList();

		Assert.assertEquals(listarecolectores.size(), 3);
	}

	/**
	 * lista todos los empleados, desde el rol de administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void ListarEmpleados() {
		TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.LISTAR_EMPLEADOS, Empleado.class);
		List<Empleado> listaempleados = query.getResultList();

		Assert.assertEquals(listaempleados.size(), 2);

	}

	/**
	 * listar plantas por genero desde administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasPorGeneroTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Planta.LISTAR_PLANTAS_POR_GENERO,
				Planta.class);
		query.setParameter("genero", "curativas");
		List<Planta> listaPlantas = query.getResultList();

		Assert.assertEquals(listaPlantas.size(), 1);
	}

	/**
	 * test para agregar un administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void agregarAdministradorTest() {

		Administrador administrador = new Administrador();
		administrador.setCedula("12354");
		administrador.setApellidos("Franco");
		administrador.setNombre("Carlos");
		administrador.setCorreo("ad@correo.com");
		administrador.setDireccion("dire");
		administrador.setTelefono("1298834");

		Cuenta cuenta = new Cuenta();
		cuenta.setContrasenia("123");
		cuenta.setUsuario("123");

		cuenta.setPersona(administrador);
		administrador.setCuenta(cuenta);

		entityManager.persist(cuenta);
		entityManager.persist(administrador);

		entityManager.find(administrador.getClass(), administrador.getCedula());
	}

	/**
	 * test para eliminar un administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void eliminarAdministradorTest() {
		Administrador ad = entityManager.find(Administrador.class, "126");
		Assert.assertNotNull(ad);
		entityManager.remove(ad);
		Assert.assertNull("No se ha eliminado", entityManager.find(Administrador.class, "126"));
	}

	/**
	 * test para eliminar un administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void modificarAdministradorTest() {
		Administrador ad = entityManager.find(Administrador.class, "126");
		Assert.assertNotNull(ad);
		ad.setNombre("Alfredo");
		entityManager.merge(ad);
		ad = null;
		ad = entityManager.find(Administrador.class, "126");
		Assert.assertEquals("Alfredo", ad.getNombre());
	}
	
	
}
