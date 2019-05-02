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
	

	@Test
	@Transactional(value= TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void listarPersonaTest() {
		Query query= entityManager.createQuery("select p1 from Persona p1");
		List listaPersona= query.getResultList();
		Assert.assertEquals(listaPersona.size(), 3);
//		Iterator iterator= listaPersona.iterator();
//		
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
	}
	
	@Test
	@Transactional(value= TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void listarPersonaNameTest() {
		TypedQuery<Persona> query= entityManager.createNamedQuery(Persona.LISTAR_TODOS, Persona.class);
		List<Persona> listaPersona= query.getResultList();
		Assert.assertEquals(listaPersona.get(2).getNombre(), "nombre3");
		
//		Iterator iterator= listaPersona.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
		
	}
}
