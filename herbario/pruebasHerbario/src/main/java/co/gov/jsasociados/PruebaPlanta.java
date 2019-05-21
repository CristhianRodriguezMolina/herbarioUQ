package co.gov.jsasociados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
public class PruebaPlanta {
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
	 * test para persistir una planta (mirar bien esto)
	 */
	// @Test
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

		entityManager.persist(genero);

		Planta planta = new Planta();
		planta.setGenero(genero);
		planta.setIdPlanta("666");
		planta.setNombre("Rubia peregrina");
		
		entityManager.persist(planta);

		Planta a = entityManager.find(Planta.class, "666");
		Assert.assertEquals("Naucleaceae", a.getGenero().getGenero());
	}

	/**
	 * muestra toda la informacion de una planta, por su id
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void mostrarInformacionPlantaTest() {

		Planta p = entityManager.find(Planta.class, "846");

		Assert.assertEquals("846", p.getIdPlanta());

	}

	/**
	 * test para modificar informcion de una planta
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void modificarInformacionPlantaTest() {

		Planta p = entityManager.find(Planta.class, "846");

		p.setGenero(entityManager.find(Genero.class, "genero2"));

		entityManager.merge(p);

		p = entityManager.find(Planta.class, "846");

		Assert.assertEquals("genero2", p.getGenero().getIdGenero());
	}

	// pruebas para familia y genero

	/**
	 * test para persistir una familia y un genero(mirar este metodo)
	 */
	@Test
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

	/**
	 * test de persistir familia(mirar esto)
	 */
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

	/**
	 * test para modificar informacion de un genero
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void modificarInformacionGeneroTest() {

		Genero p = entityManager.find(Genero.class, "genero3");

		p.setFamilia(entityManager.find(Familia.class, "familia2"));

		entityManager.merge(p);

		p = entityManager.find(Genero.class, "genero3");

		Assert.assertEquals("familia2", p.getFamilia().getIdFamilia());
	}

	/**
	 * test para modificar la informacion de una familia
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void modificarInformacionFamiliaTest() {

		Familia p = entityManager.find(Familia.class, "familia1");

		p.setFamilia("prueba");

		entityManager.merge(p);

		p = entityManager.find(Familia.class, "familia1");

		Assert.assertEquals("prueba", p.getFamilia());
	}

	/**
	 * test para eliminar una planta
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void eliminarPlantaTest() {

		Planta p = entityManager.find(Planta.class, "846");

		entityManager.remove(p);

		Assert.assertNull("La planta " + p.getNombre() + " no existe", entityManager.find(Planta.class, "846"));
	}

	/**
	 * test para eliminar un genero
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void eliminarGeneroTest() {

		Genero g = entityManager.find(Genero.class, "genero1");

		entityManager.remove(g);

		Assert.assertNull("El genero " + g.getGenero() + " no existe", entityManager.find(Genero.class, "genero1"));
	}

	/**
	 * test para eliminar una familia
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void eliminarFamiliaTest() {

		Familia f = entityManager.find(Familia.class, "familia1");

		entityManager.remove(f);

		Assert.assertNull("La familia " + f.getFamilia() + " no existe", entityManager.find(Familia.class, "familia1"));
	}

}
