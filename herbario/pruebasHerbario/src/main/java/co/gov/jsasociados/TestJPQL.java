package co.gov.jsasociados;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void FamiliaConMasEspecies2Test() {
		TypedQuery<Object> query = entityManager.createNamedQuery(Familia.FAMILIA_CON_MAS_ESPECIES_2,
				Object.class);
		List<Object> listaFamilia = query.getResultList();

			Iterator iterator= listaFamilia.iterator();
			while (iterator.hasNext()) {
				System.out.println( ( (Familia)( ( (Object[])iterator.next() )[0] ) ).getFamilia() );
			}
	}
	
//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void FamiliaConMasEspeciesTest() {
		TypedQuery<Object> query = entityManager.createNamedQuery(Familia.FAMILIA_CON_MAS_ESPECIES,
				Object.class);
		List<Object> listaFamilia = query.getResultList();

			Iterator iterator= listaFamilia.iterator();
			while (iterator.hasNext()) {
				System.out.println( ( (Familia)( ( (Object[])iterator.next() )[0] ) ).getFamilia() );
			}
	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void Test() {
		TypedQuery<Object> query = entityManager.createNamedQuery(Familia.TEST,
				Object.class);
		List<Object> listaFamilia = query.getResultList();

			Iterator iterator= listaFamilia.iterator();
			while (iterator.hasNext()) {
				System.out.println( ( (Object[])iterator.next() )[0].toString() );
			}
	}

	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void Test103() {
		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.PERSONA_SIN_REGISTRO, Persona.class);
		List<Persona> listaPersonas = query.getResultList();

		Iterator<Persona> iterator = listaPersonas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	// 1 del 10
//		@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void devolverCantidadDeFamilias() {
		TypedQuery<Integer> query = entityManager.createNamedQuery(Administrador.DECIR_CANTIDAD_FAMILIA, Integer.class);

		System.out.println("Existen la cantidad de familias " + query.getSingleResult());
	}

//	 //@Test
//		//no funciona
//		@Transactional(value = TransactionMode.ROLLBACK)
//		@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", 
//			"empleado.json", "familia.json", "genero.json", "recolector.json", "planta.json" })
//		public void Test104() {
//			TypedQuery<DtoRegistroPersno> query = entityManager.createNamedQuery(Persona.DTO, DtoRegistroPersno.class);
//			List<DtoRegistroPersno> listaPersonas = query.getResultList();
//
//
//			Iterator<DtoRegistroPersno> iterator= listaPersonas.iterator();
//			while (iterator.hasNext()) {
//				System.out.println(iterator.next());
//			}
//		}

//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public <E> void guia9() {
		TypedQuery<E> query = (TypedQuery<E>) entityManager.createNamedQuery(Registro.OBTENER_DATOS_REGISTRO);
		query.setParameter("fechaRegistro", "1999-03-30 00:00:00.000");
		List<E> list = query.getResultList();

//		for(Planta p: ((ArrayList<Planta>)list.get(2)))
//		{
//			System.out.println(p.toString());
//		}

		Iterator it = list.iterator();

		while (it.hasNext()) {

			Object o = it.next();

			System.out.println(o.toString());
		}
	}

//	@Test
//	@Transactional(value = TransactionMode.ROLLBACK)
//	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
//			"familia.json", "genero.json", "recolector.json", "planta.json" })
//	public void consultaDTOTest()
//	{
//		TypedQuery<DTO> query = entityManager.createNamedQuery(Registro.OBTENER_DATOS_REGISTRO_DTO, DTO.class);
//		query.setParameter("fechaRegistro", "1999-03-30");
//		List<DTO> list = query.getResultList();
//		
//		Assert.assertEquals(list.get(0).getGenero().getGenero(), "curativas");
//	}

	/*
	 * Test para listar todas las personas
	 */
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
	 * Test para listar todas las personas
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
//	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", 
//		"empleado.json", "familia.json", "genero.json", "recolector.json", "planta.json" })
	@UsingDataSet({ "persona.json", "registro.json" })
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
	@UsingDataSet({ "persona.json", "registro.json" })
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
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
//	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", 
//		"empleado.json", "familia.json", "genero.json", "recolector.json", "planta.json" })
	@UsingDataSet({ "persona.json", "registro.json" })
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
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
//	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", 
//		"empleado.json", "familia.json", "genero.json", "recolector.json", "planta.json" })
	@UsingDataSet({ "persona.json", "registro.json" })
	public void listarPlantasAdministradorTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Administrador.LISTAR_PLANTAS, Planta.class);
		List<Planta> listaPlantas = query.getResultList();

		Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}

//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Administrador.LISTAR_PLANTAS_POR_FAMILIA,
				Planta.class);
		query.setParameter("familia", "toxocodarmus");
		List<Planta> listaPlantas = query.getResultList();

		// Assert.assertEquals(listaPlantas.get(2).getNombre(), "Buganvilla");

		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}

}
