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
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class pruebaRecolector {
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
	// @Test
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
