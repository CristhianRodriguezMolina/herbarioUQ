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

import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Genero;
import co.gov.jsasociados.Persona;
import co.gov.jsasociados.Planta;

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
	 * metodo test que permiet saber la cantidad de familias registradas
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void devolverCantidadDeFamilias() {
		TypedQuery<Integer> query = entityManager.createNamedQuery(Familia.DECIR_CANTIDAD_FAMILIA, Integer.class);
		System.out.println("Existen la cantidad de familias " + query.getSingleResult());
	}


	/**
	 * test para persistir una planta (mirar bien esto)
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void persistirPlantaTest() {

		Familia familia = new Familia();
		familia.setFamilia("Rubiaceae");

		entityManager.persist(familia);

		Genero genero = new Genero();
		genero.setFamilia(entityManager.find(Familia.class, "familia4"));
		genero.setGenero("Naucleaceae");
		familia.addGenero(genero);

		entityManager.persist(genero);

		Planta planta = new Planta();
		planta.setGenero(genero);
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
		familia.setFamilia("Rubiaceae");

		entityManager.persist(familia);

		Genero genero = new Genero();
		genero.setFamilia(entityManager.find(Familia.class, "familia4"));
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
		familia.setFamilia("Rubiaceae");

		entityManager.persist(familia);

		Genero genero = new Genero();
		genero.setFamilia(entityManager.find(Familia.class, "familia4"));
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

	/**
	 * test para obtener una familia de una planta
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void obtenerFamiliaPlantaTest() {

		TypedQuery<Familia> query = entityManager.createNamedQuery(Planta.OBTENER_FAMILIA_PLANTA, Familia.class);
		query.setParameter("idPlanta", "851");
		List<Familia> lista = query.getResultList();

		Assert.assertEquals("familia1", ((Familia) lista.get(0)).getIdFamilia());

		Familia f = ((Familia) lista.get(0));
	}

	/**
	 * test para obtener una familia de una planta
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void obtenerPlantasPorGeneroTest() {

		TypedQuery<Planta> query = entityManager.createNamedQuery(Genero.OBTENER_PLANTAS, Planta.class);
		query.setParameter("idGenero", "genero1");
		List<Planta> lista = query.getResultList();

		Assert.assertEquals("familia1", lista.get(0).getGenero().getFamilia().getIdFamilia());

		Iterator it = lista.iterator();

		while (it.hasNext()) {

			Object o = it.next();

			System.out.println(o.toString());
		}
	}

	/**
	 * test para obtener la familia con mas plantas
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void FamiliaConMasEspeciesTest() {
		TypedQuery<Object> query = entityManager.createNamedQuery(Familia.FAMILIA_CON_MAS_ESPECIES_2, Object.class);
		List<Object> listaFamilia = query.getResultList();

		Assert.assertEquals("familia2", ((Familia) (((Object[]) listaFamilia.get(0))[0])).getIdFamilia());
		
		Iterator iterator = listaFamilia.iterator();
		while (iterator.hasNext()) {
			System.out.println(((Familia) (((Object[]) iterator.next())[0])).getFamilia());
		}
	}
}
