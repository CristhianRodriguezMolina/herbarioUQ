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
public class TestJPQL {

	/**
	 * instancia para realizar las transaciones con las entidades
	 */
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

	/*
	 * Test para listar todas las personas
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", 
		"empleado.json", "familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPersonaTest() {
		
		Administrador administrador= new Administrador();
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
		//Administrador a= entityManager.find(Administrador.class, administrador.getCedula());
		
		Query query = entityManager.createQuery("select p1 from Persona p1");
		List listaPersona = query.getResultList();
		Assert.assertEquals(listaPersona.size(), 7);
		
		Iterator iterator= listaPersona.iterator();
		
		while (iterator.hasNext()) {
			System.out.println(((Persona)iterator.next()).getCedula());
		}
	}

	/*
	 * Test para listar todas las personas
	 */
	//@Test
	@Transactional(value = TransactionMode.ROLLBACK)
//	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", 
//		"empleado.json", "familia.json", "genero.json", "recolector.json", "planta.json" })
	@UsingDataSet({ "persona.json", "registro.json"})
	public void listarPersonaNameTest() {
		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.LISTAR_TODOS, Persona.class);
		List<Persona> listaPersona = query.getResultList();
		
		System.out.println(listaPersona.get(2).getCedula());
		Assert.assertEquals(listaPersona.get(2).getNombre(), "nombre3");

//		Iterator iterator= listaPersona.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
	}

	/*
	 * Test para listar todos los daministradores
	 */
//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
//	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", 
//		"empleado.json", "familia.json", "genero.json", "recolector.json", "planta.json" })
	@UsingDataSet({ "persona.json", "registro.json"})
	public void listarAdministradoresTest() {
		TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.LISTAR_ADMINISTRADORES,
				Administrador.class);
		List<Administrador> listaAdministradores = query.getResultList();

		Assert.assertEquals(listaAdministradores.get(0).getNombre(), "nombre");
		
//		Iterator<Administrador> iterator = listaAdministradores.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next().getNombre());
//		}
	}

	/*
	 * Test para buscar un administrador dada su Id
	 */
	//@Test
	@Transactional(value = TransactionMode.ROLLBACK)
//	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", 
//		"empleado.json", "familia.json", "genero.json", "recolector.json", "planta.json" })
	@UsingDataSet({ "persona.json", "registro.json"})
	public void buscarAdministradorByIdTest() {
		TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.BUSCAR_POR_ID,
				Administrador.class);
		query.setParameter("cedula", "123");
		List<Administrador> listaAdministradores = query.getResultList();
		
		Assert.assertEquals(listaAdministradores.get(0).getNombre(), "nombre");

//		System.out.println("\n"+listaAdministradores.get(0).getNombre()+"\n");
	}
	
	/*
	 * Test para listar todas las plantas de un administrador
	 */
	//@Test
	@Transactional(value = TransactionMode.ROLLBACK)
//	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", 
//		"empleado.json", "familia.json", "genero.json", "recolector.json", "planta.json" })
	@UsingDataSet({ "persona.json", "registro.json"})
	public void listarPlantasAdministradorTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Administrador.LISTAR_PLANTAS,
				Planta.class);
		List<Planta> listaPlantas = query.getResultList();
		
		Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}
	
	
	
	//@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", 
		"empleado.json", "familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Administrador.LISTAR_PLANTAS_POR_FAMILIA,
				Planta.class);
		query.setParameter("familia", "toxocodarmus");
		List<Planta> listaPlantas = query.getResultList();
		
	//	Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}
	
}
