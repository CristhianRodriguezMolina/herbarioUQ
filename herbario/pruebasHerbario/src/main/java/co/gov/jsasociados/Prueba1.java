package co.gov.jsasociados;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
public class Prueba1 {
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

	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPersonaTest() {

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
		// Administrador a= entityManager.find(Administrador.class,
		// administrador.getCedula());

		Query query = entityManager.createQuery("select p1 from Persona p1");
		List listaPersona = query.getResultList();
		Assert.assertEquals(listaPersona.size(), 7);

		Iterator iterator = listaPersona.iterator();

		while (iterator.hasNext()) {
			System.out.println(((Persona) iterator.next()).getCedula());
		}
	}

	/*
	 * Test para listar todos los daministradores (metodo funciona)
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarAdministradoresTest() {
		TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.LISTAR_ADMINISTRADORES,
				Administrador.class);
		List<Administrador> listaAdministradores = query.getResultList();

		System.out.println("Cantidad de administradores " + listaAdministradores.size());

		Iterator<Administrador> iterator = listaAdministradores.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}

	}

	/*
	 * Test para buscar un administrador dada su Id (Metodo funciona)
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void buscarAdministradorByIdTest() {
		TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.BUSCAR_POR_ID,
				Administrador.class);
		query.setParameter("cedula", "125");
		List<Administrador> listaAdministradores = query.getResultList();
		System.out.println("Existen " + listaAdministradores.size() + "administradores con la cedula 125");
		Iterator<Administrador> iterator = listaAdministradores.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
		// Assert.assertEquals(listaAdministradores.get(0).getNombre(), "nombre");

	}

	/*
	 * Test para listar todas las plantas desde el rol de administrador (Metodo
	 * funciona)
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasAdministradorTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Administrador.LISTAR_PLANTAS, Planta.class);
		List<Planta> listaPlantas = query.getResultList();

		// Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}

	/**
	 * Test para listar todas las plantas de una familia
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Administrador.LISTAR_PLANTAS_POR_FAMILIA,
				Planta.class);
		query.setParameter("familia", "toxocodarmus");
		List<Planta> listaPlantas = query.getResultList();
		System.out.println("La familia toxocodarmus tiene " + listaPlantas.size() + " plantas");
		// Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}

	/**
	 * lista todas las plantas por su estado de aprovacion, desde el rol de
	 * administrador
	 */
	// 1 si, 0 enviado, -1 rechazado
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void ListarplantasPorAprobacion() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Administrador.LISTAR_PLANTAS_POR_APROVACION,
				Planta.class);
		query.setParameter("aprovacion", 1);
		List<Planta> listaPlantas = query.getResultList();

		// Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}

	/**
	 * lista todos los recolectores, desde el rol de administrador
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void LisatarRecolectores() {
		TypedQuery<Recolector> query = entityManager.createNamedQuery(Administrador.LISTAR_RECOLECTORES,
				Recolector.class);
		List<Recolector> listarecolectores = query.getResultList();

		// Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

		Iterator<Recolector> iterator = listarecolectores.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}

	/**
	 * lista todos los empleados, desde el rol de administrador
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void ListarEmpleados() {
		TypedQuery<Empleado> query = entityManager.createNamedQuery(Administrador.LISTAR_EMPLEADOS, Empleado.class);
		List<Empleado> listaempleados = query.getResultList();

		// Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

		Iterator<Empleado> iterator = listaempleados.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}

	/**
	 * lista todos los comentarios que se han hecho
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarComentarios() {
		TypedQuery<Comentario> query = entityManager.createNamedQuery(Comentario.LISTAR_COMENTARIOS, Comentario.class);
		List<Comentario> listacomentarios = query.getResultList();

//	Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

		Iterator<Comentario> iterator = listacomentarios.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getComentario());
		}
	}
	// Aqui empiezan las pruebas de los query de RECOLETOR
	// -------------------------------------------------------------

	/**
	 * busca a un recolector por su id
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void buscarRecolectorPorId() {
		System.out.println("Entra al metodo");
		TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.BUSCAR_POR_ID, Recolector.class);
		query.setParameter("cedula", "123");
		List<Recolector> listaRecolectores = query.getResultList();
		System.out.println("Tamanio de la lista" + listaRecolectores.size());
		Iterator<Recolector> iterator = listaRecolectores.iterator();
		while (iterator.hasNext()) {
			System.out.println("Se encontro " + iterator.next().getNombre());

		}
	}

	/**
	 * lista todos los recolectores
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarRecolectores() {
		TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.LISTAR_RECOLECTORES, Recolector.class);
		List<Recolector> listarecolectores = query.getResultList();
		System.out.println("tamanio de recolectores totales" + listarecolectores.size());
//		Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

		Iterator<Recolector> iterator = listarecolectores.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}

	/**
	 * lista todas las plantas aceptadas
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasAceptadas() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Recolector.LISTAR_PLANTAS_ACEPTADAS, Planta.class);
		List<Planta> listaPlantas = query.getResultList();

		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}

	/**
	 * lista todas las plantas teniendo en cuenta la cedula del que registro la
	 * planta y el estado del registro
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasEnviadas() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Recolector.LISTAR_PLANTAS_ENVIADAS, Planta.class);
		query.setParameter("cedula", "127");
		query.setParameter("aprovacion", 1);

		List<Planta> listaPlantas = query.getResultList();
		System.out.println("tamanio de la lista" + listaPlantas.size());
		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}

	/**
	 * lista todas las plantas aceptadas por una cierta familia
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasAceptadasPorFamilia() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Recolector.LISTAR_PLANTAS_ACEPTADAS_POR_FAMILIA,
				Planta.class);
		query.setParameter("familia", "toxocodarmus");

		List<Planta> listaPlantas = query.getResultList();
		System.out.println("tamanio de la lista" + listaPlantas.size());
		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}
	/**
	 * lista todas las plantas aceptadas por ciero genero
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasAceptadasPorGenero() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Recolector.LISTAR_PLANTAS_POR_GENERO, Planta.class);
		query.setParameter("genero", "carnivoras");

		List<Planta> listaPlantas = query.getResultList();
		System.out.println("tamanio de la lista" + listaPlantas.size());
		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}

}
