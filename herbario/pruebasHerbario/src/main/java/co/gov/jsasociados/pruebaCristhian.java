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
	
	@Test
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
	
	@Test
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
}
