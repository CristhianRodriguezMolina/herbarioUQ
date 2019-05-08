package co.gov.jsasociados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.derby.tools.sysinfo;
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
 * Clase de pruebas dedicada para la pruebas de las entidades
 * 
 * @author Cristhian Rodriguez Molina
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class pruebaCristhian {

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
	public void listarEmpleadosTest() {
		TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.LISTAR_EMPLEADOS, Empleado.class);
		List<Empleado> listaEmpleados = query.getResultList();

		Assert.assertEquals(listaEmpleados.size(), 2);

		Iterator<Empleado> iterator = listaEmpleados.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}
	
//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasAprovadasFromEmpleadoTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Empleado.LISTAR_PLANTAS_POR_APROVACION, Planta.class);
		query.setParameter("aprovacion", -1);
		List<Planta> listaPlantas = query.getResultList();

		Assert.assertEquals(listaPlantas.size(), 1);

		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}
	
//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasPorGeneroFromEmpleadoTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Empleado.LISTAR_PLANTAS_POR_GENERO, Planta.class);
		query.setParameter("genero", "genero2");
		List<Planta> listaPlantas = query.getResultList();

		Assert.assertEquals(listaPlantas.size(), 1);

		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}
	
//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasPorFamiliaFromEmpleadoTest() {
		TypedQuery<Planta> query = entityManager.createNamedQuery(Empleado.LISTAR_PLANTAS_POR_FAMILIA, Planta.class);
		query.setParameter("familia", "familia3");
		List<Planta> listaPlantas = query.getResultList();

		Assert.assertEquals(listaPlantas.size(), 2);

		Iterator<Planta> iterator = listaPlantas.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNombre());
		}
	}
	
//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void persistirFamiliaAndGeneroTest() {

		Familia familia = new Familia();
		familia.setIdFamilia("familia4");
		familia.setFamilia("Rubiaceae");
		
		entityManager.persist(familia);
		
		Genero genero = new Genero();
		genero.setFamilia(entityManager.find(Familia.class, "familia4"));
		genero.setIdGenero("genero4");
		genero.setGenero("Naucleaceae");
		familia.addGenero(genero);
		
		entityManager.persist(familia);
		
		Familia a = entityManager.find(Familia.class, "familia4");
		Assert.assertEquals("Naucleaceae", a.getGeneros().get(0).getGenero());
	}
	
//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void persistirFamiliaTest() {

		Familia familia = new Familia();
		familia.setIdFamilia("familia4");
		familia.setFamilia("Rubiaceae");
		
		entityManager.persist(familia);
		
		Genero genero = new Genero();
		genero.setFamilia(entityManager.find(Familia.class, "familia4"));
		genero.setIdGenero("genero4");
		genero.setGenero("Naucleaceae");
		familia.addGenero(genero);
		
		entityManager.persist(familia);
		
		Familia a = entityManager.find(Familia.class, "familia4");
		Assert.assertEquals("Naucleaceae", a.getGeneros().get(0).getGenero());
	}
	
//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void mostrarInformacionPlantaTest() {

		Planta p = entityManager.find(Planta.class, "846");
	
		Assert.assertEquals("846", p.getIdPlanta());
		
		System.out.println(p.toString());
	}
	
//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void persistirPlantaTest() {

		Familia familia = new Familia();
		familia.setIdFamilia("familia4");
		familia.setFamilia("Rubiaceae");
		
		entityManager.persist(familia);
		
		Genero genero = new Genero();
		genero.setFamilia(entityManager.find(Familia.class, "familia4"));
		genero.setIdGenero("genero4");
		genero.setGenero("Naucleaceae");
		familia.addGenero(genero);
		
		entityManager.persist(familia);
		
		Planta planta = new Planta();
			
		try {
			planta.setGenero(genero);
			planta.setIdPlanta("666");
			planta.setNombre("Rubia peregrina");
			File f = new File("C:\\Users\\Rodriguez\\Documents\\GitHub\\herbarioUQ\\herbario\\pruebasHerbario\\src\\main\\resources\\imagenes\\YoSoyIronMan.png"); //asociamos el archivo fisico
			InputStream is = new FileInputStream(f);
			byte[] buffer = new byte[(int) f.length()]; //creamos el buffer
			int readers = is.read(buffer); //leemos el archivo al buffer
//			planta.setImagen(buffer); //lo guardamos en la entidad
			entityManager.persist(planta); //y lo colocamos en el EntityManager
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {		
			e.printStackTrace();
		}
		
		entityManager.persist(planta);
		
		Planta a = entityManager.find(Planta.class, "666");
		Assert.assertEquals("Naucleaceae", a.getGenero().getGenero());
	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void modificarInformacionPlantaTest() {

		Planta p = entityManager.find(Planta.class, "846");
		
		System.out.println(entityManager.find(Planta.class, "846").getGenero().getGenero());
		
		p.setGenero(entityManager.find(Genero.class, "genero2"));
	
		entityManager.merge(p);
		
		System.out.println(entityManager.find(Planta.class, "846").getGenero().getGenero());

		p = entityManager.find(Planta.class, "846");
		
		Assert.assertEquals("genero2", p.getGenero().getIdGenero());
	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void modificarInformacionGeneroTest() {

		Genero p = entityManager.find(Genero.class, "genero3");
		
		System.out.println(entityManager.find(Genero.class, "genero3").getFamilia().getFamilia());
		
		p.setFamilia(entityManager.find(Familia.class, "familia2"));
	
		entityManager.merge(p);
		
		System.out.println(entityManager.find(Genero.class, "genero3").getFamilia().getFamilia());
		
		p = entityManager.find(Genero.class, "genero3");

		Assert.assertEquals("familia2", p.getFamilia().getIdFamilia());
	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void modificarInformacionFamiliaTest() {

		Familia p = entityManager.find(Familia.class, "familia1");
		
		System.out.println(entityManager.find(Familia.class, "familia1").getFamilia());
		
		p.setFamilia("prueba");
	
		entityManager.merge(p);
		
		System.out.println(entityManager.find(Familia.class, "familia1").getFamilia());
		
		p = entityManager.find(Familia.class, "familia1");

		Assert.assertEquals("prueba", p.getFamilia());
	}
}
