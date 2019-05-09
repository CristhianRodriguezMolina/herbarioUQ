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

@RunWith(Arquillian.class)
public class pruebaAdministrador {
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

		// System.out.println("Cantidad de administradores " +
		// listaAdministradores.size());

//		Iterator<Administrador> iterator = listaAdministradores.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next().getNombre());
//		}

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
		// Assert.assertEquals(listaAdministradores.get(0).getNombre(), "nombre");
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
		TypedQuery<Planta> query = entityManager.createNamedQuery(Administrador.LISTAR_PLANTAS, Planta.class);
		List<Planta> listaPlantas = query.getResultList();

		Assert.assertEquals(listaPlantas.size(), 4);

//		Iterator<Planta> iterator = listaPlantas.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next().getNombre());
//		}
	}

	/**
	 * Test para listar todas las plantas de una familia
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasFamiliaTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Administrador.LISTAR_PLANTAS_POR_FAMILIA,
				Planta.class);
		query.setParameter("familia", "xnoramos");
		List<Planta> listaPlantas = query.getResultList();
		Assert.assertEquals(listaPlantas.size(), 1);
		// System.out.println("La familia toxocodarmus tiene " + listaPlantas.size() + "
		// plantas");
		// Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

//		Iterator<Planta> iterator = listaPlantas.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next().getNombre());
//		}
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
		TypedQuery<Planta> query = entityManager.createNamedQuery(Administrador.LISTAR_PLANTAS_POR_APROVACION,
				Planta.class);
		query.setParameter("aprovacion", 1);
		List<Planta> listaPlantas = query.getResultList();
		Assert.assertEquals(listaPlantas.size(), 2);
		// Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

//		Iterator<Planta> iterator = listaPlantas.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next().getNombre());
//		}
	}

	/**
	 * lista todos los recolectores, desde el rol de administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void LisatarRecolectores() {
		TypedQuery<Recolector> query = entityManager.createNamedQuery(Administrador.LISTAR_RECOLECTORES,
				Recolector.class);
		List<Recolector> listarecolectores = query.getResultList();

		Assert.assertEquals(listarecolectores.size(), 3);
		// Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

//		Iterator<Recolector> iterator = listarecolectores.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next().getNombre());
//		}
	}

	/**
	 * lista todos los empleados, desde el rol de administrador
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void ListarEmpleados() {
		TypedQuery<Empleado> query = entityManager.createNamedQuery(Administrador.LISTAR_EMPLEADOS, Empleado.class);
		List<Empleado> listaempleados = query.getResultList();

		Assert.assertEquals(listaempleados.size(), 2);

//		Iterator<Empleado> iterator = listaempleados.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next().getNombre());
//		}
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasPorGeneroFromEmpleadoTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Administrador.LISTAR_PLANTAS_POR_GENERO,
				Planta.class);
		query.setParameter("genero", "curativas");
		List<Planta> listaPlantas = query.getResultList();

		Assert.assertEquals(listaPlantas.size(), 1);

//		Iterator<Planta> iterator = listaPlantas.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next().getNombre());
//		}
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
